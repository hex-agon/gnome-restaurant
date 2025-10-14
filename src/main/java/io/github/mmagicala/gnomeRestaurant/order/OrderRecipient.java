package io.github.mmagicala.gnomeRestaurant.order;

import net.runelite.api.coords.WorldPoint;

import java.security.InvalidParameterException;

public enum OrderRecipient {

    BURKOR("Burkor", OrderDifficulty.EASY, new WorldPoint(2420, 3474, 0)),
    BRIMSTAIL("Brimstail", OrderDifficulty.EASY, new WorldPoint(2409, 9817, 0)),
    CAPTAIN_ERRDO("Captain Errdo", OrderDifficulty.EASY, new WorldPoint(2464, 3501, 0)),
    COACH("Coach", "Gnome Coach", OrderDifficulty.EASY, new WorldPoint(2406, 3498, 0)),
    DALILA("Dalila", OrderDifficulty.EASY, new WorldPoint(2448, 3500, 0)),
    DAMWIN("Damwin", OrderDifficulty.EASY, new WorldPoint(2417, 3496, 0)),
    EEBEL("Eebel", OrderDifficulty.EASY, new WorldPoint(2416, 3414, 0)),
    ERMIN("Ermin", OrderDifficulty.EASY, new WorldPoint(2488, 3409, 0)),
    FEMI("Femi", OrderDifficulty.EASY, new WorldPoint(2459, 3382, 0)),
    FROONO("Froono", OrderDifficulty.EASY, new WorldPoint(2435, 3403, 0)),
    GUARD_VEMMELDO("Guard Vemmeldo", OrderDifficulty.EASY, new WorldPoint(2446, 3418, 0)),
    GULLUCK("Gulluck", OrderDifficulty.EASY, new WorldPoint(2468, 3487, 0)),
    HIS_ROYAL_HIGHNESS_KING_NARNODE("His Royal Highness King Narnode", "King Narnode Shareen", OrderDifficulty.EASY, new WorldPoint(2466, 3497, 0)),
    MEEGLE("Meegle", OrderDifficulty.EASY, new WorldPoint(2378, 3433, 0)),
    PERRDUR("Perrdur", OrderDifficulty.EASY, new WorldPoint(2447, 3501, 0)),
    ROMETTI("Rometti", OrderDifficulty.EASY, new WorldPoint(2482, 3510, 0)),
    SARBLE("Sarble", OrderDifficulty.EASY, new WorldPoint(2417, 3517, 0)),
    TRAINER_NACKLEPEN("Trainer Nacklepen", OrderDifficulty.EASY, new WorldPoint(2421, 3525, 0)),
    WURBEL("Wurbel", OrderDifficulty.EASY, new WorldPoint(2395, 3477, 0)),
    HECKEL_FUNCH("Heckel Funch", OrderDifficulty.EASY, new WorldPoint(2493, 3488, 0)),

    // Hard NPCs
    AMBASSADOR_FERRNOOK("Ambassador Ferrnook", OrderDifficulty.HARD, new WorldPoint(3209, 3474, 0)),
    AMBASSADOR_GIMBLEWAP("Ambassador Gimblewap", OrderDifficulty.HARD, new WorldPoint(2572, 3299, 0)),
    AMBASSADOR_SPANFIPPLE("Ambassador Spanfipple", OrderDifficulty.HARD, new WorldPoint(2984, 3342, 0)),
    BRAMBICKLE("Brambickle", OrderDifficulty.HARD, new WorldPoint(2786, 3862, 0)),
    CAPTAIN_BLEEMADGE("Captain Bleemadge", OrderDifficulty.HARD, new WorldPoint(2847, 3499, 0)),
    CAPTAIN_DAERKIN("Captain Daerkin", OrderDifficulty.HARD, new WorldPoint(3362, 3222, 0)),
    CAPTAIN_DALBUR("Captain Dalbur", OrderDifficulty.HARD, new WorldPoint(3284, 3212, 0)),
    CAPTAIN_KLEMFOODLE("Captain Klemfoodle", OrderDifficulty.HARD, new WorldPoint(2970, 2973, 0)),
    CAPTAIN_NINTO("Captain Ninto", OrderDifficulty.HARD, new WorldPoint(2869, 9877, 0)),
    GLO_CARANOCK("G.L.O Caranock", "G.L.O. Caranock", OrderDifficulty.HARD, new WorldPoint(2956, 3025, 0)),
    GARKOR("Garkor", OrderDifficulty.HARD, new WorldPoint(2434, 3519, 0)),
    GNORMADIUM_AVLAFRIM("Gnormadium Avlafrim", OrderDifficulty.HARD, new WorldPoint(2544, 2973, 0)),
    HAZELMERE("Hazelmere", OrderDifficulty.HARD, new WorldPoint(2678, 3086, 0)),
    KING_BOLREN("King Bolren", OrderDifficulty.HARD, new WorldPoint(2542, 3169, 0)),
    LIEUTENANT_SCHEPBUR("Lieutenant Schepbur", OrderDifficulty.HARD, new WorldPoint(2515, 3205, 0)),
    PENWIE("Penwie", OrderDifficulty.HARD, new WorldPoint(2932, 2972, 0)),
    PROFESSOR_IMBLEWYN("Professor Imblewyn", OrderDifficulty.HARD, new WorldPoint(2590, 3092, 0)),
    PROFESSOR_MANGLETHORP("Professor Manglethorp", OrderDifficulty.HARD, new WorldPoint(2868, 10198, 0)),
    PROFESSOR_ONGLEWIP("Professor Onglewip", OrderDifficulty.HARD, new WorldPoint(3115, 3160, 0)),
    WINGSTONE("Wingstone", OrderDifficulty.HARD, new WorldPoint(3380, 2893, 0));

    private final String addressedName;
    private final String inGameName;
    private final OrderDifficulty difficulty;
    private final WorldPoint location;

    public static OrderRecipient getRecipient(String addressedName) {
        for (var recipient : values()) {
            if (recipient.getAddressedName().equals(addressedName)) {
                return recipient;
            }
        }
        throw new InvalidParameterException("No recipient found with the name " + addressedName);
    }

    /** Gianne jnr uses the same name as this NPC's in-game name **/
    OrderRecipient(String addressedName, OrderDifficulty difficulty, WorldPoint location) {
        this(addressedName, addressedName, difficulty, location);
    }

    /** Gianne jnr uses a different name from this NPC's in-game name **/
    OrderRecipient(String addressedName, String inGameName, OrderDifficulty difficulty, WorldPoint location) {
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
