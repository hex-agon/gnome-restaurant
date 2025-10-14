package io.github.mmagicala.gnomeRestaurant.order;

import net.runelite.api.coords.WorldPoint;

public class OrderRecipient {

    private final String addressedName, inGameName;

    private final OrderDifficulty difficulty;

    private WorldPoint location;

    /** Gianne jnr uses the same name as this NPC's in-game name **/
    public OrderRecipient(String addressedName, OrderDifficulty difficulty, WorldPoint location) {
        this(addressedName, addressedName, difficulty, location);
    }

    /** Gianne jnr uses a different name from this NPC's in-game name **/
    public OrderRecipient(String addressedName, String inGameName, OrderDifficulty difficulty, WorldPoint location) {
        this.addressedName = addressedName;
        this.inGameName = inGameName;
        this.difficulty = difficulty;
        this.location = location;
    }

    public String getAddressedName() {
        return this.addressedName;
    }

    public String getInGameName() {
        return this.inGameName;
    }

    public OrderDifficulty getDifficulty() {
        return this.difficulty;
    }

    public WorldPoint getLocation() {
        return this.location;
    }
}
