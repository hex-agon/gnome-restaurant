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

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("gnomerestaurant")
public interface GnomeRestaurantConfig extends Config
{
	@ConfigItem(
		keyName = GnomeRestaurantPlugin.SHOW_OVERLAY,
		name = "Show Overlay",
		description = "Configures whether to show the overlay window",
		position = 0
	)
	default boolean showOverlay()
	{
		return true;
	}

	@ConfigItem(
		keyName = GnomeRestaurantPlugin.SHOW_ORDER_TIMER,
		name = "Show Order Timer",
		description = "Configures whether to show the order timer",
		position = 1
	)
	default boolean showOrderTimer()
	{
		return true;
	}

	@ConfigItem(
		keyName = GnomeRestaurantPlugin.SHOW_DELAY_TIMER,
		name = "Show Delay Timer",
		description = "Configures whether to show the order delay timer",
		position = 2
	)
	default boolean showDelayTimer()
	{
		return true;
	}

	@ConfigItem(
		keyName = GnomeRestaurantPlugin.SHOW_HINT_ARROW,
		name = "Show Hint Arrow",
		description = "Configures whether to show the hint arrow toward the order recipient",
		position = 3
	)
	default boolean showHintArrow()
	{
		return true;
	}

	@ConfigItem(
		keyName = GnomeRestaurantPlugin.SHOW_WORLD_MAP_POINT,
		name = "Show World Map Point",
		description = "Configures whether to show the recipient's location in the world map",
		position = 4
	)
	default boolean showWorldMapPoint()
	{
		return true;
	}
}
