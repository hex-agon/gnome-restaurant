package io.github.mmagicala.gnomeRestaurant.data;

import javax.annotation.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UniqueRecipient
{
	NINTO("Captain Ninto"),
	DAERKIN("Captain Daerkin"),
	WINGSTONE("Wingstone"),
	PENWIE("Penwie"),
	BRAMBICKLE("Brambickle"),
	MANGLETHORP("Professor Manglethorp");

	private static final UniqueRecipient[] VALUES = UniqueRecipient.values();
	private final String name;

	public static @Nullable UniqueRecipient byName(final String name)
	{
		for (final var v : VALUES)
		{
			if (v.name.equals(name))
			{
				return v;
			}
		}

		return null;
	}
}
