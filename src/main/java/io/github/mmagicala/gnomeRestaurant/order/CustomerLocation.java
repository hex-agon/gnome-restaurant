package io.github.mmagicala.gnomeRestaurant.order;

import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;

public interface CustomerLocation {

    int npcTypeId(Client client);

    WorldPoint resolve(Client client);

    static CustomerLocation fixed(int npcTypeId, int x, int y) {
        return fixed(npcTypeId, x, y, 0);
    }

    static CustomerLocation fixed(int npcTypeId, int x, int y, int plane) {
        return new StaticCustomerLocation(npcTypeId, new WorldPoint(x, y, plane));
    }

    class StaticCustomerLocation implements CustomerLocation {

        private final int npcTypeId;
        private final WorldPoint worldPoint;

        private StaticCustomerLocation(int npcTypeId, WorldPoint worldPoint) {
            this.npcTypeId = npcTypeId;
            this.worldPoint = worldPoint;
        }

        @Override
        public int npcTypeId(Client client) {
            return npcTypeId;
        }

        @Override
        public WorldPoint resolve(Client client) {
            return worldPoint;
        }
    }
}
