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
import io.github.mmagicala.gnomeRestaurant.order.Customer;
import io.github.mmagicala.gnomeRestaurant.order.OrderDifficulty;
import io.github.mmagicala.gnomeRestaurant.overlay.GnomeRestaurantOverlay;
import io.github.mmagicala.gnomeRestaurant.overlay.OverlayHeader;
import io.github.mmagicala.gnomeRestaurant.overlay.OverlayTableEntry;
import io.github.mmagicala.gnomeRestaurant.recipe.Ingredient;
import io.github.mmagicala.gnomeRestaurant.recipe.Order;
import net.runelite.api.Client;
import net.runelite.api.ItemContainer;
import net.runelite.api.NPC;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.VarbitID;
import net.runelite.client.callback.ClientThread;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@PluginDescriptor(name = "Gnome Restaurant")
public class GnomeRestaurantPlugin extends Plugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(GnomeRestaurantPlugin.class);

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
    private ClientThread clientThread;

    @Inject
    private WorldMapPointManager worldMapPointManager;

    // UI

    private Timer timer;
    private Overlay overlay;
    private WorldMapPoint worldMapPoint;

    private String tooltipText;

    private boolean refusedHardOrder = false;
    private boolean queuedUpdate = false;

    // Current order information

    private Order order;
    private Customer customer;
    private int stepIdx;

    // Overlay

    private OverlayHeader overlayHeader = new OverlayHeader("null", -1, -1);
    private final List<OverlayTableEntry> stepIngredientsOverlayTable = new ArrayList<>();
    private final List<OverlayTableEntry> nextRawIngredientsOverlayTable = new ArrayList<>();

    public OverlayHeader overlayHeader() {
        return overlayHeader;
    }

    @Override
    protected void shutDown() {
        resetPlugin();
    }

    private void resetPlugin() {
        removeTimer();
        removeOverlay();
        removeWorldMapPoint();
        client.clearHintArrow();

        stepIdx = 0;

        refusedHardOrder = false;

        order = null;
        customer = null;
    }

    private void onRefusedHardOrder() {
        resetPlugin();

        setupRefusedOrderTimer();
        refusedHardOrder = true;
    }

    private void updateOrder() {
        updateOrderStep();
        setupUI();
    }

    private void queueUpdateOrder() {
        if (queuedUpdate) {
            return;
        }
        clientThread.invokeAtTickEnd(() -> {
            LOGGER.debug("Updating ui for order={}, customer={}", order, customer);
            updateOrder();
            queuedUpdate = false;
        });
        queuedUpdate = true;
    }

    private void setupRefusedOrderTimer() {
        timer = new Timer(5, ChronoUnit.MINUTES, itemManager.getImage(ItemID.ALUFT_DELIVERY_BOX), this);
        timer.setTooltip("Refused hard order cooldown");
        infoBoxManager.addInfoBox(timer);
    }

    /**
     * Update the step we are working on in the recipe
     * Returns whether a step has been updated or not
     **/
    private boolean updateOrderStep() {
        // Go from the beginning of the recipe to the final item produced

        int newStepIdx = 0;

        for (int i = 0; i < order.getSteps().size() - 1; i++) {
            int producedId = order.getSteps().get(i).getProducedItemId();
            if (getItemCount(producedId) > 0) {
                // We produced the item for this step
                newStepIdx = i + 1;
            }
        }
        boolean stepUpdated = newStepIdx != stepIdx;

        stepIdx = newStepIdx;
        return stepUpdated;
    }

    private int getItemCount(int itemId) {
        // Assume the inventory is empty (and player just logged in) if it is null
        ItemContainer inventory = client.getItemContainer(InventoryID.INV);

        if (inventory == null) {
            return 0;
        } else {
            return inventory.count(itemId);
        }
    }

    private void setupUI() {
        tooltipText = "Deliver " + order.getName() + " to " + customer.getName();

        if (config.showOverlay()) {
            setupOverlay();
        }

        if (config.showOrderTimer()) {
            setupOrderTimer();
        }

        if (config.showHintArrow()) {
            showHintArrow();
        }

        if (config.showWorldMapPoint()) {
            showWorldMapPoint();
        }
    }

    private void setupOverlay() {
        if (overlay != null) {
            removeOverlay();
        }
        updateOverlayHeader();
        rebuildOverlayTables();

        // Display overlay
        overlay = new GnomeRestaurantOverlay(this, stepIngredientsOverlayTable, nextRawIngredientsOverlayTable);
        overlayManager.add(overlay);
    }

    private void setupOrderTimer() {
        LOGGER.debug("Setting up order timer");
        int numSecondsLeft;

        if (customer.getDifficulty() == OrderDifficulty.HARD) {
            numSecondsLeft = 660;
        } else {
            numSecondsLeft = 360;
        }

        if (timer != null) {
            LOGGER.debug("Removing previous order timer");
            removeTimer();
        }
        timer = new Timer(numSecondsLeft, ChronoUnit.SECONDS, itemManager.getImage(order.getItemId()), this);
        timer.setTooltip(tooltipText);

        infoBoxManager.addInfoBox(timer);
    }

    private void showHintArrow() {
        if (!setHintArrowToCustomer()) {
            // NPC not in sight
            // Show hint arrow in minimap instead
            client.setHintArrow(customer.getLocation(client));
        }
    }

    private void showWorldMapPoint() {
        if (worldMapPoint != null) {
            removeWorldMapPoint();
        }

        worldMapPoint = WorldMapPoint.builder().name(customer.getName())
                                     .worldPoint(customer.getLocation(client))
                                     .image(itemManager.getImage(ItemID.ALUFT_DELIVERY_BOX))
                                     .build();
        worldMapPoint.setSnapToEdge(true);
        worldMapPoint.setJumpOnClick(true);
        worldMapPoint.setTooltip(tooltipText);
        worldMapPointManager.add(worldMapPoint);
    }

    private boolean setHintArrowToCustomer() {
        var npcs = client.getTopLevelWorldView().npcs();

        for (NPC npc : npcs) {
            if (npc.getId() == customer.getNpcTypeId(client)) {
                client.setHintArrow(npc);
                return true;
            }
        }
        return false;
    }

    @Subscribe
    public void onNpcSpawned(final NpcSpawned event) {
        var npc = event.getNpc();

        if (order == null || !config.showHintArrow()) {
            return;
        }
        if (npc.getId() == customer.getNpcTypeId(client)) {
            client.setHintArrow(npc);
        }
    }

    @Subscribe
    public void onNpcDespawned(final NpcDespawned event) {
        var npc = event.getNpc();

        if (order == null || !config.showHintArrow()) {
            return;
        }
        if (npc.getId() == customer.getNpcTypeId(client)) {
            client.setHintArrow(customer.getLocation(client));
        }
    }

    @Subscribe
    public void onVarbitChanged(VarbitChanged event) {
        switch (event.getVarbitId()) {
            case VarbitID.ALUFT_MINIGAME_STARTED:
                onAluftMinigameStarted(event.getValue());
                break;
            case VarbitID.ALUFT_CURRENT_CUSTOMER:
                onAluftCustomerChanged(event.getValue());
                break;
            case VarbitID.ALUFT_CURRENT_ORDER:
                onAluftOrderChanged(event.getValue());
                break;
            case VarbitID.ALUFT_REFUSED_HARD_ORDER:
                onAluftRefusedOrderChanged(event.getValue());
                break;
        }
    }

    private void onAluftMinigameStarted(int value) {
        LOGGER.debug("Aluft Minigame started is {}", value);

        if (value == 0) {
            resetPlugin();
        }
    }

    private void onAluftCustomerChanged(int customerId) {
        if (customerId == 0) {
            customer = null;
            return;
        }
        customer = Customer.forId(customerId);
        LOGGER.debug("Customer is now {}", customer);
        queueUpdateOrder();
    }

    private void onAluftOrderChanged(int orderId) {
        if (orderId == 0) {
            order = null;
            return;
        }
        order = Order.forId(orderId);
        LOGGER.debug("Order is now {}", order);
        queueUpdateOrder();
    }

    private void onAluftRefusedOrderChanged(int value) {
        if (value == 1) {
            LOGGER.debug("Refused hard order, setting up timer");
            onRefusedHardOrder();
        }
    }

    @Subscribe
    public void onItemContainerChanged(ItemContainerChanged event) {
        if (event.getContainerId() != InventoryID.INV) {
            return;
        }
        if (order == null) {
            return;
        }
        // Has player finished a step?

        if (updateOrderStep()) {
            updateOverlayHeader();
            rebuildOverlayTables();
        } else {
            updateOverlayTables();
        }
    }

    private void updateOverlayHeader() {
        var instruction = order.getSteps().get(stepIdx).getInstruction().getOverlayDirections();
        overlayHeader = new OverlayHeader(instruction, stepIdx + 1, order.getSteps().size());
    }

    private void rebuildOverlayTables() {
        clearOverlayTables();

        List<Ingredient> stepIngredients = order.getSteps().get(stepIdx).getIngredients();
        rebuildOverlayTable(stepIngredients, stepIngredientsOverlayTable);

        List<Ingredient> nextRawIngredients = order.getNextRawIngredients(stepIdx);
        rebuildOverlayTable(nextRawIngredients, nextRawIngredientsOverlayTable);
    }

    private void rebuildOverlayTable(List<Ingredient> ingredients, List<OverlayTableEntry> overlayTable) {
        for (Ingredient ingredient : ingredients) {
            int itemId = ingredient.getItemId();
            String itemName = itemManager.getItemComposition(itemId).getName();
            int inventoryCount = getItemCount(itemId);
            int expectedCount = ingredient.getCount();

            overlayTable.add(new OverlayTableEntry(itemId, itemName, inventoryCount, expectedCount));
        }
    }

    private void updateOverlayTables() {
        updateOverlayTable(stepIngredientsOverlayTable);
        updateOverlayTable(nextRawIngredientsOverlayTable);
    }

    private void updateOverlayTable(List<OverlayTableEntry> overlayTable) {
        for (OverlayTableEntry entry : overlayTable) {
            int inventoryCount = getItemCount(entry.getItemId());
            if (entry.getInventoryCount() != inventoryCount) {
                entry.setInventoryCount(inventoryCount);
            }
        }
    }

    // Config

    @Provides
    GnomeRestaurantConfig provideConfig(ConfigManager configManager) {
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
    public void onConfigChanged(ConfigChanged event) {
        if (order != null) {
            if (event.getKey().equals(SHOW_ORDER_TIMER)) {
                if (event.getNewValue().equals("false")) {
                    removeTimer();
                }
            } else if (event.getKey().equals(SHOW_OVERLAY)) {
                if (event.getNewValue().equals("false")) {
                    removeOverlay();
                } else {
                    // Must be called from client thread
                    clientThread.invoke(this::setupOverlay);
                }
            } else if (event.getKey().equals(SHOW_HINT_ARROW)) {
                if (event.getNewValue().equals("false")) {
                    client.clearHintArrow();
                } else {
                    showHintArrow();
                }
            } else if (event.getKey().equals(SHOW_WORLD_MAP_POINT)) {
                if (event.getNewValue().equals("false")) {
                    removeWorldMapPoint();
                } else {
                    showWorldMapPoint();
                }
            }
        } else {
            if (!event.getGroup().equals("gnomerestaurant")) {
                // This plugin's config has not changed
                return;
            } else if (event.getKey().equals(SHOW_DELAY_TIMER) && refusedHardOrder) {
                if (event.getNewValue().equals("false")) {
                    removeTimer();
                }
            }
        }
    }

    // UI cleaning

    private void removeTimer() {
        infoBoxManager.removeInfoBox(timer);
        timer = null;
    }

    private void removeOverlay() {
        clearOverlayTables();
        overlayManager.remove(overlay);
        overlay = null;
    }

    private void removeWorldMapPoint() {
        worldMapPointManager.remove(worldMapPoint);
        worldMapPoint = null;
    }

    private void clearOverlayTables() {
        stepIngredientsOverlayTable.clear();
        nextRawIngredientsOverlayTable.clear();
    }
}
