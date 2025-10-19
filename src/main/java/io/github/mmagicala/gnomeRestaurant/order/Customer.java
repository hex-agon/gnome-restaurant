package io.github.mmagicala.gnomeRestaurant.order;

import net.runelite.api.coords.WorldPoint;

import java.security.InvalidParameterException;

public enum Customer {

    TRAINER_NACKLEPEN(1, "Trainer Nacklepen", new WorldPoint(2421, 3525, 0)),
    HIS_ROYAL_HIGHNESS_KING_NARNODE(2, "King Narnode Shareen", new WorldPoint(2466, 3497, 0)),
    FEMI(3, "Femi", new WorldPoint(2459, 3382, 0)),
    HECKEL_FUNCH(4, "Heckel Funch", new WorldPoint(2493, 3488, 0)),
    GULLUCK(5, "Gulluck", new WorldPoint(2468, 3487, 0)),
    ROMETTI(6, "Rometti", new WorldPoint(2482, 3510, 0)),
    COACH(7, "Gnome Coach", new WorldPoint(2406, 3498, 0)),
    WURBEL(8, "Wurbel", new WorldPoint(2395, 3477, 0)),
    EEBEL(9, "Eebel", new WorldPoint(2416, 3414, 0)),
    DAMWIN(10, "Damwin", new WorldPoint(2417, 3496, 0)),
    SARBLE(11, "Sarble", new WorldPoint(2417, 3517, 0)),
    BURKOR(12, "Burkor", new WorldPoint(2420, 3474, 0)),
    MEEGLE(13, "Meegle", new WorldPoint(2378, 3433, 0)),
    GUARD_VEMMELDO(14, "Guard Vemmeldo", new WorldPoint(2446, 3418, 0)),
    ERMIN(15, "Ermin", new WorldPoint(2488, 3409, 0)),
    FROONO(16, "Froono", new WorldPoint(2435, 3403, 0)),
    BRIMSTAIL(17, "Brimstail", new WorldPoint(2409, 9817, 0)),
    CAPTAIN_ERRDO(18, "Captain Errdo", new WorldPoint(2464, 3501, 0)),
    PERRDUR(19, "Perrdur", new WorldPoint(2447, 3501, 0)),
    DALILA(20, "Dalila", new WorldPoint(2448, 3500, 0)),

    // Hard NPCs
    HAZELMERE(101, "Hazelmere", new WorldPoint(2678, 3086, 0)),
    GNORMADIUM_AVLAFRIM(102, "Gnormadium Avlafrim", new WorldPoint(2544, 2973, 0)),
    CAPTAIN_NINTO(103, "Captain Ninto", new WorldPoint(2869, 9877, 0)),
    CAPTAIN_DAERKIN(104, "Captain Daerkin", new WorldPoint(3362, 3222, 0)),
    BRAMBICKLE(105, "Brambickle", new WorldPoint(2786, 3862, 0)),
    WINGSTONE(106, "Wingstone", new WorldPoint(3380, 2893, 0)),
    PENWIE(107, "Penwie", new WorldPoint(2932, 2972, 0)),
    AMBASSADOR_GIMBLEWAP(108, "Ambassador Gimblewap", new WorldPoint(2572, 3299, 0)),
    AMBASSADOR_SPANFIPPLE(109, "Ambassador Spanfipple", new WorldPoint(2984, 3342, 0)),
    AMBASSADOR_FERRNOOK(110, "Ambassador Ferrnook", new WorldPoint(3209, 3474, 0)),
    PROFESSOR_IMBLEWYN(111, "Professor Imblewyn", new WorldPoint(2590, 3092, 0)),
    PROFESSOR_MANGLETHORP(112, "Professor Manglethorp", new WorldPoint(2868, 10198, 0)),
    PROFESSOR_ONGLEWIP(113, "Professor Onglewip", new WorldPoint(3115, 3160, 0)),
    GARKOR(114, "Garkor", new WorldPoint(2434, 3519, 0)),
    KING_BOLREN(115, "King Bolren", new WorldPoint(2542, 3169, 0)),
    GLO_CARANOCK(116, "G.L.O. Caranock", new WorldPoint(2956, 3025, 0)),
    LIEUTENANT_SCHEPBUR(117, "Lieutenant Schepbur", new WorldPoint(2515, 3205, 0)),
    CAPTAIN_DALBUR(118, "Captain Dalbur", new WorldPoint(3284, 3212, 0)),
    CAPTAIN_BLEEMADGE(119, "Captain Bleemadge", new WorldPoint(2847, 3499, 0)),
    CAPTAIN_KLEMFOODLE(120, "Captain Klemfoodle", new WorldPoint(2970, 2973, 0));

    private final int id;
    private final String name;
    private final WorldPoint location;

    public static Customer forId(int id) {
        for (var recipient : values()) {
            if (recipient.id == id) {
                return recipient;
            }
        }
        throw new InvalidParameterException("No customer found with the id " + id);
    }

    Customer(int id, String name, WorldPoint location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public OrderDifficulty getDifficulty() {
        return id > 100 ?  OrderDifficulty.HARD : OrderDifficulty.EASY;
    }

    public WorldPoint getLocation() {
        return this.location;
    }
}
