package io.github.mmagicala.gnomeRestaurant.order;

import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.gameval.NpcID;

import java.security.InvalidParameterException;

public enum Customer {

    TRAINER_NACKLEPEN(1, "Trainer Nacklepen", CustomerLocation.fixed(NpcID.GNOME_TORTOISE_TRAINER, 2421, 3525)),
    HIS_ROYAL_HIGHNESS_KING_NARNODE(2, "King Narnode Shareen", CustomerLocation.fixed(NpcID.GRANDTREE_NARNODE, 2466, 3497)),
    FEMI(3, "Femi", CustomerLocation.fixed(NpcID.GRANDTREE_FEMI, 2459, 3382)),
    HECKEL_FUNCH(4, "Heckel Funch", CustomerLocation.fixed(NpcID.GNOME_HECKELFUNCH, 2493, 3488)),
    GULLUCK(5, "Gulluck", CustomerLocation.fixed(NpcID.GNOME_GULLUCK, 2468, 3487)),
    ROMETTI(6, "Rometti", CustomerLocation.fixed(NpcID.GNOME_ROMETTI, 2482, 3510)),
    COACH(7, "Gnome Coach", CustomerLocation.fixed(NpcID.GNOME_BALL_COACH, 2406, 3498)),
    WURBEL(8, "Wurbel", CustomerLocation.fixed(NpcID.ALUFT_GNOMEBALL_FAN, 2395, 3477)),
    EEBEL(9, "Eebel", CustomerLocation.fixed(NpcID.ALUFT_CUSTOMER_MALE, 2416, 3414)),
    DAMWIN(10, "Damwin", CustomerLocation.fixed(NpcID.ALUFT_GNOME_CHEERLEADER, 2417, 3496)),
    SARBLE(11, "Sarble", CustomerLocation.fixed(NpcID.ALUFT_TOAD_HANDLER, 2417, 3517)),
    BURKOR(12, "Burkor", CustomerLocation.fixed(NpcID.ALUFT_GNOMEKNIGHT, 2420, 3474)),
    MEEGLE(13, "Meegle", CustomerLocation.fixed(NpcID.ALUFT_GNOME_TERRORBIRD_TRAINER, 2378, 3433)),
    GUARD_VEMMELDO(14, "Guard Vemmeldo", CustomerLocation.fixed(NpcID.GNOME_BANK_GUARD, 2446, 3418)),
    ERMIN(15, "Ermin", CustomerLocation.fixed(NpcID.ALUFT_CUSTOMER_SPINNER, 2488, 3409)),
    FROONO(16, "Froono", CustomerLocation.fixed(NpcID.ALUFT_GNOME_CHILD, 2435, 3403)),
    BRIMSTAIL(17, "Brimstail", CustomerLocation.fixed(NpcID.GNOME_BRIMSTAIL, 2409, 9817)),
    CAPTAIN_ERRDO(18, "Captain Errdo", CustomerLocation.fixed(NpcID.PILOT_DIGSITE, 2464, 3501)),
    PERRDUR(19, "Perrdur", CustomerLocation.fixed(NpcID.ALUFT_GNOME_CUSTOMER_ONE, 2447, 3501)),
    DALILA(20, "Dalila", CustomerLocation.fixed(NpcID.ALUFT_GNOME_CUSTOMER_TWO, 2448, 3500)),

    // Hard NPCs
    HAZELMERE(101, "Hazelmere", CustomerLocation.fixed(NpcID.GRANDTREE_HAZELMERE_MULTI, 2678, 3086)),
    GNORMADIUM_AVLAFRIM(102, "Gnormadium Avlafrim", CustomerLocation.fixed(NpcID.GNORMADIUM_AVLAFRIM_GLIDER, 2544, 2973)),
    CAPTAIN_NINTO(103, "Captain Ninto", CustomerLocation.fixed(NpcID.ALUFT_PILOT_NINTO, 2869, 9877)),
    CAPTAIN_DAERKIN(104, "Captain Daerkin", CustomerLocation.fixed(NpcID.ALUFT_PILOT_DAERKIN, 3362, 3222)),
    BRAMBICKLE(105, "Brambickle", CustomerLocation.fixed(NpcID.ALUFT_GNOME_EXPLORER_MOUNTAIN, 2786, 3862)),
    WINGSTONE(106, "Wingstone", CustomerLocation.fixed(NpcID.ALUFT_GNOME_EXPLORER_SAFARI, 3380, 2893)),
    PENWIE(107, "Penwie", CustomerLocation.fixed(NpcID.ALUFT_GNOME_EXPLORER_JUNGLE, 2932, 2972)),
    AMBASSADOR_GIMBLEWAP(108, "Ambassador Gimblewap", CustomerLocation.fixed(NpcID.ALUFT_GNOME_DIPLOMAT_ARDOUGNE, 2572, 3299)),
    AMBASSADOR_SPANFIPPLE(109, "Ambassador Spanfipple", CustomerLocation.fixed(NpcID.ALUFT_GNOME_DIPLOMAT_FALADOR, 2984, 3342)),
    AMBASSADOR_FERRNOOK(110, "Ambassador Ferrnook", CustomerLocation.fixed(NpcID.ALUFT_GNOME_DIPLOMAT_VARROCK, 3209, 3474)),
    PROFESSOR_IMBLEWYN(111, "Professor Imblewyn", CustomerLocation.fixed(NpcID.ALUFT_GNOME_MAGE_2, 2590, 3092)),
    PROFESSOR_MANGLETHORP(112, "Professor Manglethorp", CustomerLocation.fixed(NpcID.ALUFT_GNOME_INVENTOR, 2868, 10198)),
    PROFESSOR_ONGLEWIP(113, "Professor Onglewip", CustomerLocation.fixed(NpcID.ALUFT_GNOME_MAGE, 3115, 3160)),
    GARKOR(114, "Garkor", new GarkorLocation()),
    KING_BOLREN(115, "King Bolren", CustomerLocation.fixed(NpcID.KING_BOLREN, 2542, 3169)),
    GLO_CARANOCK(116, "G.L.O. Caranock", CustomerLocation.fixed(NpcID.MM_CARANOCK, 2956, 3025)),
    LIEUTENANT_SCHEPBUR(117, "Lieutenant Schepbur", CustomerLocation.fixed(NpcID.GNOME_TORTOISE_COMMANDER, 2515, 3205)),
    CAPTAIN_DALBUR(118, "Captain Dalbur", CustomerLocation.fixed(NpcID.PILOT_AL_KHARID_BASE, 3284, 3212)),
    CAPTAIN_BLEEMADGE(119, "Captain Bleemadge", CustomerLocation.fixed(NpcID.PILOT_WHITE_WOLF_BASE, 2847, 3499)),
    CAPTAIN_KLEMFOODLE(120, "Captain Klemfoodle", CustomerLocation.fixed(NpcID.PILOT_KARAMJA_BASE, 2970, 2973));

    private final int id;
    private final String name;
    private final CustomerLocation location;

    public static Customer forId(int id) {
        for (var recipient : values()) {
            if (recipient.id == id) {
                return recipient;
            }
        }
        throw new InvalidParameterException("No customer found with the id " + id);
    }

    Customer(int id, String name, CustomerLocation location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public OrderDifficulty getDifficulty() {
        return id > 100 ? OrderDifficulty.HARD : OrderDifficulty.EASY;
    }

    public int getNpcTypeId(Client client) {
        return this.location.npcTypeId(client);
    }

    public WorldPoint getLocation(Client client) {
        return this.location.resolve(client);
    }
}
