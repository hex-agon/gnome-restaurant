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

package io.github.mmagicala.gnomeRestaurant.overlay;

import io.github.mmagicala.gnomeRestaurant.GnomeRestaurantPlugin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import javax.inject.Inject;
import net.runelite.api.ItemComposition;
import net.runelite.api.MenuAction;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.OverlayMenuEntry;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.components.ComponentConstants;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class GnomeRestaurantOverlay extends OverlayPanel
{
	// Overlay contents
	private final OverlayHeader header;
	private final ArrayList<OverlayTableEntry> stepIngredientsOverlayTable;
	private final ArrayList<OverlayTableEntry> futureRawIngredientsOverlayTable;

	private final GnomeRestaurantPlugin plugin;
	private static final int PADDING = 10;

	@Inject
	public GnomeRestaurantOverlay(GnomeRestaurantPlugin plugin, OverlayHeader header,
									 ArrayList<OverlayTableEntry> stepIngredientsOverlayTable,
									 ArrayList<OverlayTableEntry> futureRawIngredientsOverlayTable)
	{
		super(plugin);
		this.plugin = plugin;

		this.header = header;
		this.stepIngredientsOverlayTable = stepIngredientsOverlayTable;
		this.futureRawIngredientsOverlayTable = futureRawIngredientsOverlayTable;

		// Padding
		panelComponent.setBorder(new Rectangle(PADDING, PADDING, PADDING, PADDING));

		// Gap between panel items
		panelComponent.setGap(new Point(0, ComponentConstants.STANDARD_BORDER));
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		// Header

		String headerText = "Step " + header.stepNum + "/" + header.totalSteps + ": " +
			header.instruction;

		LineComponent headerComponent = LineComponent.builder().left(headerText).build();
		panelComponent.getChildren().add(headerComponent);

		// Overlay tables

		renderOverlayTable(stepIngredientsOverlayTable, "Current Step");

		if (futureRawIngredientsOverlayTable.size() > 0)
		{
			// Only render future ingredients if there are any left
			renderOverlayTable(futureRawIngredientsOverlayTable, "Needed Later");
		}

		return super.render(graphics);
	}

	private void renderOverlayTable(ArrayList<OverlayTableEntry> overlayTable, String title)
	{
		// Table header

		TitleComponent titleComponent = TitleComponent.builder().text(title).build();
		panelComponent.getChildren().add(titleComponent);

		// Table contents

		for (OverlayTableEntry tableEntry : overlayTable)
		{
			Color ingredientColor;
			if (tableEntry.getInventoryCount() >= tableEntry.getRequiredCount())
			{
				ingredientColor = Color.GREEN;
			}
			else if (tableEntry.getInventoryCount() == 0)
			{
				ingredientColor = Color.RED;
			}
			else
			{
				ingredientColor = Color.YELLOW;
			}

			LineComponent ingredientRow = LineComponent.builder()
				.left(tableEntry.getItemName())
				.leftColor(ingredientColor)
				.right(tableEntry.getInventoryCount() + "/" + tableEntry.getRequiredCount())
				.rightColor(ingredientColor)
				.build();
			panelComponent.getChildren().add(ingredientRow);
		}
	}
}
