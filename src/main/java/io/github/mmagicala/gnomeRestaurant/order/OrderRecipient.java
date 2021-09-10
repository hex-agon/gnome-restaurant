package io.github.mmagicala.gnomeRestaurant.order;

import lombok.Getter;
import net.runelite.api.coords.WorldPoint;

public class OrderRecipient
{
	@Getter
	private final String addressedName, inGameName;

	@Getter
	private final OrderDifficulty difficulty;

	@Getter
	private WorldPoint location;

	/** Gianne jnr uses the same name as this NPC's in-game name **/
	public OrderRecipient(String addressedName, OrderDifficulty difficulty, WorldPoint location)
	{
		this(addressedName, addressedName, difficulty, location);
	}

	/** Gianne jnr uses a different name from this NPC's in-game name **/
	public OrderRecipient(String addressedName, String inGameName, OrderDifficulty difficulty, WorldPoint location)
	{
		this.addressedName = addressedName;
		this.inGameName = inGameName;
		this.difficulty = difficulty;
		this.location = location;
	}
}
