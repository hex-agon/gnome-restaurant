/*
 * Copyright (c) 2020, MMagicala <https://github.com/MMagicala>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package io.github.mmagicala.gnomeRestaurant;

import com.google.inject.Provides;
import io.github.mmagicala.gnomeRestaurant.data.OrderRecipients;
import io.github.mmagicala.gnomeRestaurant.data.Recipes;
import io.github.mmagicala.gnomeRestaurant.order.OrderDifficulty;
import io.github.mmagicala.gnomeRestaurant.order.OrderRecipient;
import io.github.mmagicala.gnomeRestaurant.overlay.GnomeRestaurantOverlay;
import io.github.mmagicala.gnomeRestaurant.overlay.OverlayHeader;
import io.github.mmagicala.gnomeRestaurant.overlay.OverlayTableEntry;
import io.github.mmagicala.gnomeRestaurant.recipe.Ingredient;
import io.github.mmagicala.gnomeRestaurant.recipe.Recipe;
import java.security.InvalidParameterException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;
import net.runelite.api.ItemID;
import net.runelite.api.NPC;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.ui.overlay.infobox.Timer;
import net.runelite.client.ui.overlay.worldmap.WorldMapPoint;
import net.runelite.client.ui.overlay.worldmap.WorldMapPointManager;

@Slf4j
@PluginDescriptor(
	name = "Gnome Restaurant"
)
public class GnomeRestaurantPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private GnomeRestaurantConfig config;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private ItemManager itemManager;

	@Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private ClientThread clientThread;

	@Inject
	private WorldMapPointManager worldMapPointManager;

	// UI

	private Timer timer;
	private Overlay overlay;
	private WorldMapPoint worldMapPoint;

	private String tooltipText;

	// Flags

	@Inject
	@Named("developerMode")
	boolean developerMode;

	private static final int VARBIT_IS_DELIVERING = 2478;

	private boolean isDelayed = false;

	// Current order information

	private Recipe recipe;
	private OrderRecipient recipient;
	private int stepIdx;

	// Overlay

	private final OverlayHeader overlayHeader = new OverlayHeader("null", -1, -1);
	private final ArrayList<OverlayTableEntry> stepIngredientsOverlayTable = new ArrayList<>();
	private final ArrayList<OverlayTableEntry> nextRawIngredientsOverlayTable = new ArrayList<>();

	// Gianne jr. dialogue

	private static final Pattern DELIVERY_START_PATTERN = Pattern.compile("([\\w .]+) wants (?:some|a) ([\\w ]+)");
	private static final String EASY_DELIVERY_CANCEL_TEXT = "Fine, your loss. If you want another easy job one come " +
		"back in five minutes and maybe I'll be able to find you one.";
	private static final String HARD_DELIVERY_CANCEL_TEXT = "Fine, your loss. I may have an easier job for you, since" +
		" you chickened out of that one, If you want another hard one come back in five minutes and maybe I'll be" +
		" able to find you a something.";

	@Override
	protected void shutDown() throws Exception
	{
		resetPlugin();
	}

	private void resetPlugin()
	{
		removeTimer();
		removeOverlay();
		removeWorldMapPoint();
		client.clearHintArrow();

		stepIdx = 0;

		isDelayed = false;

		recipe = null;
		recipient = null;
	}

	// Monitor dialogue for Gianne jnr's new order
	@Subscribe
	public void onGameTick(GameTick event)
	{
		boolean isDialogueOpen = client.getWidget(WidgetInfo.DIALOG_NPC_NAME) != null;
		if (!isDialogueOpen)
		{
			return;
		}

		boolean isTalkingToGianneJnr = client.getWidget(WidgetInfo.DIALOG_NPC_NAME).getText().equals("Gianne jnr.");
		if (!isTalkingToGianneJnr)
		{
			return;
		}

		String dialog = client.getWidget(WidgetInfo.DIALOG_NPC_TEXT).getText();
		// Treat dialogue as a single line
		dialog = dialog.replace("<br>", " ");
		Matcher matcher = DELIVERY_START_PATTERN.matcher(dialog);

		if (matcher.find())
		{
			final String matchedRecipient = matcher.group(1);
			final String matchedRecipe = matcher.group(2);

			if (recipient != null && recipient.getAddressedName().equals(matchedRecipient)
				&& recipe != null && recipe.getName().equals(matchedRecipe))
			{
				// We are still viewing the same delivery as is already recorded
				return;
			}

			// Configure plugin
			resetPlugin();
			startPlugin(matchedRecipient, matchedRecipe);
		}

		boolean playerCancelledOrder = dialog.contains(EASY_DELIVERY_CANCEL_TEXT) ||
			dialog.contains(HARD_DELIVERY_CANCEL_TEXT);

		if (config.showDelayTimer() && !isDelayed && playerCancelledOrder)
		{
			cancelOrder();
		}
	}

	private void cancelOrder()
	{
		resetPlugin();

		setupDelayTimer();
		isDelayed = true;
	}

	private void startPlugin(String recipientName, String recipeName)
	{
		parseOrder(recipientName, recipeName);
		updateStep();
		setupUI();
	}

	private void setupDelayTimer()
	{
		timer = new Timer(5, ChronoUnit.MINUTES, itemManager.getImage(ItemID.ALUFT_ALOFT_BOX), this);
		timer.setTooltip("Cannot place an order at this time");
		infoBoxManager.addInfoBox(timer);
	}

	private void parseOrder(String addressedName, String recipeName)
	{
		recipe = Recipes.getRecipe(recipeName);
		recipient = OrderRecipients.getRecipient(addressedName);
	}

	/**
	 * Update the step we are working on in the recipe
	 * Returns whether step has been updated or not
	 **/
	private boolean updateStep()
	{
		// Go from beginning of recipe to final item produced

		int newStepIdx = 0;

		for (int i = 0; i < recipe.getSteps().size() - 1; i++)
		{
			int producedId = recipe.getSteps().get(i).getProducedItemId();
			if (getItemCount(producedId) > 0)
			{
				// We produced the item for this step
				newStepIdx = i + 1;
			}
		}

		boolean stepUpdated = false;

		if (newStepIdx != stepIdx)
		{
			stepUpdated = true;
		}

		stepIdx = newStepIdx;
		return stepUpdated;
	}

	private int getItemCount(int itemId)
	{
		// Assume the inventory is empty (and player just logged in) if it is null
		ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);

		if (inventory == null)
		{
			return 0;
		}
		else
		{
			return inventory.count(itemId);
		}
	}

	private void setupUI()
	{
		tooltipText = "Deliver " + recipe.getName() + " to " + recipient.getInGameName();

		if (config.showOverlay())
		{
			setupOverlay();
		}

		if (config.showOrderTimer())
		{
			setupOrderTimer();
		}

		// Draw hint arrow if we can immediately find the NPC

		if (config.showHintArrow())
		{
			showHintArrow();
		}

		if (config.showWorldMapPoint())
		{
			showWorldMapPoint();
		}
	}

	private void setupOverlay()
	{
		// Overlay contents
		updateOverlayHeader();
		rebuildOverlayTables();

		// Display overlay
		overlay = new GnomeRestaurantOverlay(this, overlayHeader, stepIngredientsOverlayTable,
			nextRawIngredientsOverlayTable);
		overlayManager.add(overlay);
	}

	private void setupOrderTimer()
	{
		int numSecondsLeft;

		if (recipient.getDifficulty() == OrderDifficulty.HARD)
		{
			numSecondsLeft = 660;
		}
		else
		{
			numSecondsLeft = 360;
		}

		timer = new Timer(numSecondsLeft, ChronoUnit.SECONDS, itemManager.getImage(recipe.getItemId()),
			this);
		timer.setTooltip(tooltipText);

		infoBoxManager.addInfoBox(timer);
	}

	private void showHintArrow()
	{
		if (!markNPCInCache())
		{
			// NPC not in sight
			// Show hint arrow in mini map instead
			client.setHintArrow(recipient.getLocation());
		}
	}

	private void showWorldMapPoint()
	{
		worldMapPoint = new WorldMapPoint(recipient.getLocation(), itemManager.getImage(ItemID.ALUFT_ALOFT_BOX));
		worldMapPoint.setSnapToEdge(true);
		worldMapPoint.setJumpOnClick(true);
		worldMapPoint.setTooltip(tooltipText);
		worldMapPointManager.add(worldMapPoint);
	}

	/**
	 * Mark the recipient if they are in the surrounding area
	 *
	 * @return true if recipient was marked
	 */
	private boolean markNPCInCache()
	{
		List<NPC> npcs = client.getNpcs();

		for (NPC npc : npcs)
		{
			if (npc.getName() == null)
			{
				continue;
			}

			if (npc.getName().equals(recipient.getInGameName()))
			{
				client.setHintArrow(npc);
				return true;
			}
		}
		return false;
	}

	// Mark matching NPC that shows up

	@Subscribe
	public void onNpcSpawned(final NpcSpawned event)
	{
		// TODO: use ID instead of name

		if (event.getNpc() == null || event.getNpc().getName() == null)
		{
			return;
		}

		if (recipe != null && event.getNpc().getName().equals(recipient.getInGameName()) && config.showHintArrow())
		{
			client.setHintArrow(event.getNpc());
		}
	}

	// Revert to marking world point if NPC disappears

	@Subscribe
	public void onNpcDespawned(final NpcDespawned event)
	{
		if (event.getNpc() == null || event.getNpc().getName() == null)
		{
			return;
		}

		if (recipe != null && event.getNpc().getName().equals(recipient.getInGameName()) && config.showHintArrow())
		{
			client.setHintArrow(recipient.getLocation());
		}
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		if (recipe != null && client.getVarbitValue(VARBIT_IS_DELIVERING) == 0)
		{
			// Delivery successful or failed
			resetPlugin();
		}
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		if (overlay != null)
		{
			if (event.getContainerId() != InventoryID.INVENTORY.getId())
			{
				return;
			}

			// Has player finished a step?

			if (updateStep())
			{
				updateOverlayHeader();
				rebuildOverlayTables();
			}
			else
			{
				updateOverlayTables();
			}
		}
	}

	private void updateOverlayHeader()
	{
		overlayHeader.instruction = recipe.getSteps().get(stepIdx).getInstruction().getOverlayDirections();
		overlayHeader.stepNum = stepIdx + 1;
		overlayHeader.totalSteps = recipe.getSteps().size();
	}

	private void rebuildOverlayTables()
	{
		clearOverlayTables();

		ArrayList<Ingredient> stepIngredients = recipe.getSteps().get(stepIdx).getIngredients();
		rebuildOverlayTable(stepIngredients, stepIngredientsOverlayTable);

		ArrayList<Ingredient> nextRawIngredients = recipe.getNextRawIngredients(stepIdx);
		rebuildOverlayTable(nextRawIngredients, nextRawIngredientsOverlayTable);
	}

	private void rebuildOverlayTable(ArrayList<Ingredient> ingredients,
									 ArrayList<OverlayTableEntry> overlayTable)
	{
		for (Ingredient ingredient : ingredients)
		{
			int itemId = ingredient.getItemId();
			String itemName = itemManager.getItemComposition(itemId).getName();
			int inventoryCount = getItemCount(itemId);
			int expectedCount = ingredient.getCount();

			overlayTable.add(new OverlayTableEntry(itemId, itemName, inventoryCount, expectedCount));
		}
	}

	private void updateOverlayTables()
	{
		updateOverlayTable(stepIngredientsOverlayTable);
		updateOverlayTable(nextRawIngredientsOverlayTable);
	}

	private void updateOverlayTable(ArrayList<OverlayTableEntry> overlayTable)
	{
		for (OverlayTableEntry entry : overlayTable)
		{
			int inventoryCount = getItemCount(entry.getItemId());
			if (entry.getInventoryCount() != inventoryCount)
			{
				entry.setInventoryCount(inventoryCount);
			}
		}
	}

	// Config

	@Provides
	GnomeRestaurantConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(GnomeRestaurantConfig.class);
	}

	// Config group keys

	public static final String SHOW_DELAY_TIMER = "showDelayTimer";
	public static final String SHOW_ORDER_TIMER = "showOrderTimer";
	public static final String SHOW_OVERLAY = "showOverlay";
	public static final String SHOW_HINT_ARROW = "showHintArrow";
	public static final String SHOW_WORLD_MAP_POINT = "showWorldMapPoint";

	/**
	 * Monitor changes to plugin config and update the plugin accordingly
	 *
	 * @param event indicates the setting that was changed and its new value
	 */
	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (recipe != null)
		{
			if (event.getKey().equals(SHOW_ORDER_TIMER))
			{
				if (event.getNewValue().equals("false"))
				{
					removeTimer();
				}
			}
			else if (event.getKey().equals(SHOW_OVERLAY))
			{
				if (event.getNewValue().equals("false"))
				{
					removeOverlay();
				}
				else
				{
					// Must be called from client thread
					clientThread.invoke(this::setupOverlay);
				}
			}
			else if (event.getKey().equals(SHOW_HINT_ARROW))
			{
				if (event.getNewValue().equals("false"))
				{
					client.clearHintArrow();
				}
				else
				{
					showHintArrow();
				}
			}
			else if (event.getKey().equals(SHOW_WORLD_MAP_POINT))
			{
				if (event.getNewValue().equals("false"))
				{
					removeWorldMapPoint();
				}
				else
				{
					showWorldMapPoint();
				}
			}
		}
		else
		{
			if (!event.getGroup().equals("gnomerestaurant"))
			{
				// This plugin's config has not changed
				return;
			}
			else if (event.getKey().equals(SHOW_DELAY_TIMER) && isDelayed)
			{
				if (event.getNewValue().equals("false"))
				{
					removeTimer();
				}
			}
		}
	}

	// UI cleaning

	private void removeTimer()
	{
		infoBoxManager.removeInfoBox(timer);
		timer = null;
	}

	private void removeOverlay()
	{
		clearOverlayTables();
		overlayManager.remove(overlay);
		overlay = null;
	}

	private void removeWorldMapPoint()
	{
		worldMapPointManager.remove(worldMapPoint);
		worldMapPoint = null;
	}

	private void clearOverlayTables()
	{
		stepIngredientsOverlayTable.clear();
		nextRawIngredientsOverlayTable.clear();
	}

	// Plugin testing

	@Subscribe
	public void onCommandExecuted(CommandExecuted commandExecuted)
	{
		// Must be in developer mode to send command

		if (!developerMode)
		{
			return;
		}

		// Not the right command

		if (!commandExecuted.getCommand().equals("gnome"))
		{
			return;
		}

		// Invalid command usage

		if (commandExecuted.getArguments().length < 1)
		{
			printUsage();
			return;
		}

		if (commandExecuted.getArguments()[0].equals("reset"))
		{
			resetTestOrder();
			return;
		}
		else if (commandExecuted.getArguments()[0].equals("cancel"))
		{
			cancelTestOrder();
			return;
		}

		if (commandExecuted.getArguments().length < 2)
		{
			printUsage();
			return;
		}

		if (recipe != null)
		{
			printChatMessage("Cannot test an order while another delivery is in progress");
			return;
		}

		String recipientName = commandExecuted.getArguments()[0].replace("_", " ");
		String recipeName = commandExecuted.getArguments()[1].replace("_", " ");

		printChatMessage("Testing order with recipient: '" + recipientName + "' and recipe '" + recipeName + "'");

		try
		{
			startPlugin(recipientName, recipeName);
		}
		catch (InvalidParameterException e)
		{
			resetTestOrder();
			printChatMessage("Error: InvalidParameterException caught: " + e.getMessage());
		}

		// Manually set varbit to simulate real order
		client.setVarbitValue(client.getVarps(), VARBIT_IS_DELIVERING, 1);
	}

	private void cancelTestOrder()
	{
		cancelOrder();
		client.setVarbitValue(client.getVarps(), VARBIT_IS_DELIVERING, 0);
		printChatMessage("Order cancelled");
	}

	private void resetTestOrder()
	{
		resetPlugin();
		client.setVarbitValue(client.getVarps(), VARBIT_IS_DELIVERING, 0);
		printChatMessage("Plugin reset");
	}

	private void printUsage()
	{
		printChatMessage("Usage '::gnome npc_addressed_name recipe_name'");
		printChatMessage("Usage '::gnome reset'");
	}

	private void printChatMessage(String message)
	{
		chatMessageManager.queue(QueuedMessage.builder().type(ChatMessageType.GAMEMESSAGE).value(message).build());
	}
}
