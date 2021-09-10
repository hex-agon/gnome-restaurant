package io.github.mmagicala.gnomeRestaurant.data;

import io.github.mmagicala.gnomeRestaurant.order.OrderDifficulty;
import io.github.mmagicala.gnomeRestaurant.order.OrderRecipient;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import net.runelite.api.coords.WorldPoint;

public class OrderRecipients
{
	public static final ArrayList<OrderRecipient> list = new ArrayList<OrderRecipient>()
	{
		{
			add(new OrderRecipient("Burkor", OrderDifficulty.EASY, new WorldPoint(2420, 3474, 0)));
			add(new OrderRecipient("Brimstail", OrderDifficulty.EASY, new WorldPoint(2409, 9817, 0)));
			add(new OrderRecipient("Captain Errdo", OrderDifficulty.EASY, new WorldPoint(2464, 3501, 0)));
			add(new OrderRecipient("Coach", "Gnome Coach", OrderDifficulty.EASY, new WorldPoint(2406, 3498, 0)));
			add(new OrderRecipient("Dalila", OrderDifficulty.EASY, new WorldPoint(2448, 3500, 0)));
			add(new OrderRecipient("Damwin", OrderDifficulty.EASY, new WorldPoint(2417, 3496, 0)));
			add(new OrderRecipient("Eebel", OrderDifficulty.EASY, new WorldPoint(2416, 3414, 0)));
			add(new OrderRecipient("Ermin", OrderDifficulty.EASY, new WorldPoint(2488, 3409, 0)));
			add(new OrderRecipient("Femi", OrderDifficulty.EASY, new WorldPoint(2459, 3382, 0)));
			add(new OrderRecipient("Froono", OrderDifficulty.EASY, new WorldPoint(2435, 3403, 0)));
			add(new OrderRecipient("Guard Vemmeldo", OrderDifficulty.EASY, new WorldPoint(2446, 3418, 0)));
			add(new OrderRecipient("Gulluck", OrderDifficulty.EASY, new WorldPoint(2468, 3487, 0)));
			add(new OrderRecipient("His Royal Highness King Narnode", "King Narnode Shareen",
				OrderDifficulty.EASY, new WorldPoint(2466, 3497, 0)));
			add(new OrderRecipient("Meegle", OrderDifficulty.EASY, new WorldPoint(2378, 3433, 0)));
			add(new OrderRecipient("Perrdur", OrderDifficulty.EASY, new WorldPoint(2447, 3501, 0)));
			add(new OrderRecipient("Rometti", OrderDifficulty.EASY, new WorldPoint(2482, 3510, 0)));
			add(new OrderRecipient("Sarble", OrderDifficulty.EASY, new WorldPoint(2417, 3517, 0)));
			add(new OrderRecipient("Trainer Nacklepen", OrderDifficulty.EASY, new WorldPoint(2421, 3525, 0)));
			add(new OrderRecipient("Wurbel", OrderDifficulty.EASY, new WorldPoint(2395, 3477, 0)));
			add(new OrderRecipient("Heckel Funch", OrderDifficulty.EASY, new WorldPoint(2493, 3488, 0)));

			// Hard NPCs
			add(new OrderRecipient("Ambassador Ferrnook", OrderDifficulty.HARD, new WorldPoint(3209, 3474, 0)));
			add(new OrderRecipient("Ambassador Gimblewap", OrderDifficulty.HARD, new WorldPoint(2572, 3299, 0)));
			add(new OrderRecipient("Ambassador Spanfipple", OrderDifficulty.HARD, new WorldPoint(2984, 3342, 0)));
			add(new OrderRecipient("Brambickle", OrderDifficulty.HARD, new WorldPoint(2786, 3862, 0)));
			add(new OrderRecipient("Captain Bleemadge", OrderDifficulty.HARD, new WorldPoint(2847, 3499, 0)));
			add(new OrderRecipient("Captain Daerkin", OrderDifficulty.HARD, new WorldPoint(3362, 3222, 0)));
			add(new OrderRecipient("Captain Dalbur", OrderDifficulty.HARD, new WorldPoint(3284, 3212, 0)));
			add(new OrderRecipient("Captain Klemfoodle", OrderDifficulty.HARD, new WorldPoint(2970, 2973, 0)));
			add(new OrderRecipient("Captain Ninto", OrderDifficulty.HARD, new WorldPoint(2869, 9877, 0)));
			add(new OrderRecipient("G.L.O Caranock", "G.L.O. Caranock", OrderDifficulty.HARD, new WorldPoint(2956, 3025, 0)));
			add(new OrderRecipient("Garkor", OrderDifficulty.HARD, new WorldPoint(2434, 3519, 0)));
			add(new OrderRecipient("Gnormadium Avlafrim", OrderDifficulty.HARD, new WorldPoint(2544, 2973, 0)));
			add(new OrderRecipient("Hazelmere", OrderDifficulty.HARD, new WorldPoint(2678, 3086, 0)));
			add(new OrderRecipient("King Bolren", OrderDifficulty.HARD, new WorldPoint(2542, 3169, 0)));
			add(new OrderRecipient("Lieutenant Schepbur", OrderDifficulty.HARD, new WorldPoint(2515, 3205, 0)));
			add(new OrderRecipient("Penwie", OrderDifficulty.HARD, new WorldPoint(2932, 2972, 0)));
			add(new OrderRecipient("Professor Imblewyn", OrderDifficulty.HARD, new WorldPoint(2590, 3092, 0)));
			add(new OrderRecipient("Professor Manglethorp", OrderDifficulty.HARD, new WorldPoint(2868, 10198, 0)));
			add(new OrderRecipient("Professor Onglewip", OrderDifficulty.HARD, new WorldPoint(3115, 3160, 0)));
			add(new OrderRecipient("Wingstone", OrderDifficulty.HARD, new WorldPoint(3380, 2893, 0)));
		}
	};

	public static OrderRecipient getRecipient(String addressedName)
	{
		for (OrderRecipient npc: list)
		{
			if (npc.getAddressedName().equals(addressedName))
			{
				return npc;
			}
		}

		throw new InvalidParameterException("No recipient found with the name " + addressedName);
	}
}
