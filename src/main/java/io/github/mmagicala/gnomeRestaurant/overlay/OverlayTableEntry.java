package io.github.mmagicala.gnomeRestaurant.overlay;

import lombok.Getter;
import lombok.Setter;

public class OverlayTableEntry
{
	// Used by plugin to update entry

	@Getter
	private int itemId;

	// Used by overlay table for output

	@Getter
	private String itemName;

	@Getter
	@Setter
	private int inventoryCount;

	@Getter
	private int requiredCount;

	public OverlayTableEntry(int itemId, String itemName, int inventoryCount, int requiredCount)
	{
		this.itemId = itemId;
		this.itemName = itemName;
		this.inventoryCount = inventoryCount;
		this.requiredCount = requiredCount;
	}

}
