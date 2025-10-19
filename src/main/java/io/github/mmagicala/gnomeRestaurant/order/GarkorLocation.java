package io.github.mmagicala.gnomeRestaurant.order;

import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.gameval.NpcID;
import net.runelite.api.gameval.VarbitID;

public class GarkorLocation implements CustomerLocation {

    @Override
    public int npcTypeId(Client client) {
        var mm2progress = client.getVarbitValue(VarbitID.MM2_PROGRESS);

        if (mm2progress >= 195 && mm2progress <= 200) {
            // Post mm2
            return NpcID.MM_GARKOR_GS;
        } else if (mm2progress >= 140 && mm2progress <= 175) {
            // Gnome Stronghold Fence
            return NpcID.MM2_GARKOR_BREACH;
        } else {
            // Ape Atoll
            return NpcID.MM_GARKOR_AA;
        }
    }

    @Override
    public WorldPoint resolve(Client client) {
        var mm2progress = client.getVarbitValue(VarbitID.MM2_PROGRESS);

        if (mm2progress >= 195 && mm2progress <= 200) {
            // Post mm2
            return new WorldPoint(2479, 3463, 0);
        } else if (mm2progress >= 140 && mm2progress <= 175) {
            // Gnome Stronghold Fence
            return new WorldPoint(2434, 3519, 0);
        } else {
            // Ape Atoll
            return new WorldPoint(2805, 2762, 0);
        }
    }
}
