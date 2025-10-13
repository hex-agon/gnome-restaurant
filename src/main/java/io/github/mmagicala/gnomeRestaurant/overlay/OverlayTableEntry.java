package io.github.mmagicala.gnomeRestaurant.overlay;

public class OverlayTableEntry {
    // Used by plugin to update entry

    private final int itemId;
    // Used by overlay table for output
    private final String itemName;
    private final int requiredCount;
    private int inventoryCount;

    public OverlayTableEntry(int itemId, String itemName, int inventoryCount, int requiredCount) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.inventoryCount = inventoryCount;
        this.requiredCount = requiredCount;
    }

    public int getItemId() {
        return this.itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getInventoryCount() {
        return this.inventoryCount;
    }

    public int getRequiredCount() {
        return this.requiredCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }
}
