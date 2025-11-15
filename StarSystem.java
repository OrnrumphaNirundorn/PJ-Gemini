package edu.gemini.model;

/*
 * Copyright (c) 2021.
 * Chaiyong Ragkhitwetsagul, Morakot Choetkiertikul, Pipatpong Niyommungmee
 * All rights reserved.
 */

/**
 * The class containing all the star systems supported by Gemini.
 * Implemented as an enum named CONSTELLATIONS (immutable data holder).
 */
public final class StarSystem {

    /**
     * Quadrant enum (moved here for self-containment).
     */
    public enum QUADRANT {
        NQ1, NQ2, NQ3, NQ4,
        SQ1, SQ2, SQ3, SQ4
    }

    /**
     * This is an enum containing all the star systems supported in Gemini.
     * Each enum constant holds the data for one star system.
     */
    public enum CONSTELLATIONS {
        Andromeda("Andromeda", 722.278, QUADRANT.NQ1, 90, 40, 11),
        Antlia("Air Pump", 238.901, QUADRANT.SQ2, 45, 90, 4),
        Apus("Bird of Paradise", 206.327, QUADRANT.SQ3, 5, 90, 7),
        Aquarius("Water Bearer", 979.854, QUADRANT.SQ4, 65, 90, 10),
        Aquila("Eagle", 652.473, QUADRANT.NQ4, 90, 75, 8),
        Ara("Altar", 237.057, QUADRANT.SQ3, 25, 90, 7),
        Aries("Ram", 441.395, QUADRANT.NQ1, 90, 60, 12),
        Auriga("Charioteer", 657.438, QUADRANT.NQ2, 90, 40, 2),
        Bootes("Herdsman", 906.831, QUADRANT.NQ3, 90, 50, 6),
        Caelum("Chisel", 124.865, QUADRANT.SQ1, 40, 90, 1),
        Camelopardalis("Giraffe", 756.828, QUADRANT.NQ2, 90, 10, 2),
        Cancer("Crab", 505.872, QUADRANT.NQ2, 90, 60, 3),
        CanesVenatici("Hunting Dogs", 465.194, QUADRANT.NQ3, 90, 40, 5),
        CanisMajor("Greater Dog", 380.118, QUADRANT.SQ2, 60, 90, 2),
        CanisMinor("Lesser Dog", 183.367, QUADRANT.NQ2, 90, 75, 3),
        Capricornus("Sea Goat", 413.947, QUADRANT.SQ4, 60, 90, 9),
        Carina("Keel", 494.184, QUADRANT.SQ2, 20, 90, 3),
        Cassiopeia("Cassiopeia", 598.407, QUADRANT.NQ1, 90, 20, 11),
        Centaurus("Centaur", 1060.422, QUADRANT.SQ3, 25, 90, 5),
        Cepheus("Cepheus", 587.787, QUADRANT.NQ4, 90, 10, 11),
        Cetus("Whale (or Sea Monster)", 1231.411, QUADRANT.SQ1, 70, 90, 11),
        Chamaeleon("Chameleon", 131.592, QUADRANT.SQ2, 0, 90, 4),
        Circinus("Compass (drafting tool)", 93.353, QUADRANT.SQ3, 30, 90, 7),
        Columba("Dove", 270.184, QUADRANT.SQ1, 45, 90, 2),
        ComaBerenices("Berenice’s Hair", 386.475, QUADRANT.NQ3, 90, 70, 5),
        CoronaAustralis("Southern Crown", 127.696, QUADRANT.SQ4, 40, 90, 8),
        CoronaBorealis("Northern Crown", 178.71, QUADRANT.NQ3, 90, 50, 7),
        Corvus("Crow", 183.801, QUADRANT.SQ3, 60, 90, 5),
        Crater("Cup", 282.398, QUADRANT.SQ2, 65, 90, 4),
        Crux("Southern Cross", 68.447, QUADRANT.SQ3, 20, 90, 5),
        Cygnus("Swan", 803.983, QUADRANT.NQ4, 90, 40, 9),
        Delphinus("Dolphin", 188.549, QUADRANT.NQ4, 90, 70, 9),
        Dorado("Dolphinfish", 179.173, QUADRANT.SQ1, 20, 90, 1),
        Draco("Dragon", 1082.952, QUADRANT.NQ3, 90, 15, 7),
        Equuleus("Little Horse (Foal)", 71.641, QUADRANT.NQ4, 90, 80, 9),
        Eridanus("Eridanus (river)", 1137.919, QUADRANT.SQ1, 32, 90, 12),
        Fornax("Furnace", 397.502, QUADRANT.SQ1, 50, 90, 12),
        Gemini("Twins", 513.761, QUADRANT.NQ2, 90, 60, 2),
        Grus("Crane", 365.513, QUADRANT.SQ4, 34, 90, 10),
        Hercules("Hercules", 1225.148, QUADRANT.NQ3, 90, 50, 7),
        Horologium("Pendulum Clock", 248.885, QUADRANT.SQ1, 30, 90, 12),
        Hydra("Hydra", 1302.844, QUADRANT.SQ2, 54, 83, 4),
        Hydrus("Water Snake", 243.035, QUADRANT.SQ1, 8, 90, 11),
        Indus("Indian", 294.006, QUADRANT.SQ4, 15, 90, 9),
        Lacerta("Lizard", 200.688, QUADRANT.NQ4, 90, 40, 10),
        Leo("Lion", 946.964, QUADRANT.NQ2, 90, 65, 4),
        LeoMinor("Lesser Lion", 231.956, QUADRANT.NQ2, 90, 45, 4),
        Lepus("Hare", 290.291, QUADRANT.SQ1, 63, 90, 1),
        Libra("Scales", 538.052, QUADRANT.SQ3, 65, 90, 6),
        Lupus("Wolf", 333.683, QUADRANT.SQ3, 35, 90, 6),
        Lynx("Lynx", 545.386, QUADRANT.NQ2, 90, 55, 3),
        Lyra("Lyre", 286.476, QUADRANT.NQ4, 90, 40, 8),
        Mensa("Table Mountain (Mons Mensae)", 153.484, QUADRANT.SQ1, 4, 90, 1),
        Microscopium("Microscope", 209.513, QUADRANT.SQ4, 45, 90, 9),
        Monoceros("Unicorn", 481.569, QUADRANT.NQ2, 75, 90, 2),
        Musca("Fly", 138.355, QUADRANT.SQ3, 10, 90, 5),
        Norma("Level", 165.29, QUADRANT.SQ3, 30, 90, 7),
        Octans("Octant", 291.045, QUADRANT.SQ4, 0, 90, 10),
        Ophiuchus("Serpent Bearer", 948.34, QUADRANT.SQ3, 80, 80, 7),
        Orion("Orion (the Hunter)", 594.12, QUADRANT.NQ1, 85, 75, 1),
        Pavo("Peacock", 377.666, QUADRANT.SQ4, 30, 90, 8),
        Pegasus("Pegasus", 1120.794, QUADRANT.NQ4, 90, 60, 10),
        Perseus("Perseus", 614.997, QUADRANT.NQ1, 90, 35, 12),
        Phoenix("Phoenix", 469.319, QUADRANT.SQ1, 32, 80, 11),
        Pictor("Easel", 246.739, QUADRANT.SQ1, 26, 90, 1),
        Pisces("Fishes", 889.417, QUADRANT.NQ1, 90, 65, 11),
        PiscisAustrinus("Southern Fish", 245.375, QUADRANT.SQ4, 55, 90, 10),
        Puppis("Stern", 673.434, QUADRANT.SQ2, 40, 90, 2),
        Pyxis("Compass (mariner’s compass)", 220.833, QUADRANT.SQ2, 50, 90, 3),
        Reticulum("Reticle", 113.936, QUADRANT.SQ1, 23, 90, 1),
        Sagitta("Arrow", 79.932, QUADRANT.NQ4, 90, 70, 8),
        Sagittarius("Archer", 867.432, QUADRANT.SQ4, 55, 90, 8),
        Scorpius("Scorpion", 496.783, QUADRANT.SQ3, 40, 90, 7),
        Sculptor("Sculptor", 474.764, QUADRANT.SQ1, 50, 90, 11),
        Scutum("Shield (of Sobieski)", 109.114, QUADRANT.SQ4, 80, 90, 8),
        Serpens("Snake", 636.928, QUADRANT.NQ3, 80, 80, 7),
        Sextans("Sextant", 313.515, QUADRANT.SQ2, 80, 90, 4),
        Taurus("Bull", 797.249, QUADRANT.NQ1, 90, 65, 1),
        Telescopiu("Telescope", 251.512, QUADRANT.SQ4, 40, 90, 8),
        Triangulum("Triangle", 131.847, QUADRANT.NQ1, 90, 60, 12),
        TriangulumAustrale("Southern Triangle", 109.978, QUADRANT.SQ3, 25, 90, 7),
        Tucana("Toucan", 294.557, QUADRANT.SQ4, 25, 90, 11),
        UrsaMajor("Great Bear", 1279.66, QUADRANT.NQ2, 90, 30, 4),
        UrsaMinor("Little Bear", 255.864, QUADRANT.NQ3, 90, 10, 6),
        Vela("Sails", 499.649, QUADRANT.SQ2, 30, 90, 3),
        Virgo("Virgin (Maiden)", 1294.428, QUADRANT.SQ3, 80, 80, 5),
        Volans("Flying Fish", 141.354, QUADRANT.SQ2, 15, 90, 3),
        Vulpecula("Fox", 268.165, QUADRANT.NQ4, 90, 55, 11);

        private final String engName;
        private final double area;
        private final QUADRANT quadrant;
        private final int startingLatitude;
        private final int endingLatitude;
        private final int month;

        CONSTELLATIONS(String engName, double area, QUADRANT quadrant,
                       int startingLatitude, int endingLatitude, int month) {
            this.engName = engName;
            this.area = area;
            this.quadrant = quadrant;
            this.startingLatitude = startingLatitude;
            this.endingLatitude = endingLatitude;
            this.month = month;
        }

        public String getEngName() {
            return engName;
        }

        public double getArea() {
            return area;
        }

        public QUADRANT getQuadrant() {
            return quadrant;
        }

        public int getStartingLatitude() {
            return startingLatitude;
        }

        public int getEndingLatitude() {
            return endingLatitude;
        }

        public int getMonth() {
            return month;
        }

        @Override
        public String toString() {
            return "CONSTELLATIONS{" +
                    "engName='" + engName + '\'' +
                    ", area=" + area +
                    ", quadrant=" + quadrant +
                    ", startingLatitude=" + startingLatitude +
                    ", endingLatitude=" + endingLatitude +
                    ", month=" + month +
                    '}';
        }

        /**
         * Lookup helper: find a CONSTELLATIONS enum by English name (case-insensitive).
         * @param name english name to search for
         * @return matching enum or null if not found
         */
        public static CONSTELLATIONS lookupByEngName(String name) {
            if (name == null) return null;
            for (CONSTELLATIONS c : values()) {
                if (c.engName.equalsIgnoreCase(name) || c.name().equalsIgnoreCase(name)) {
                    return c;
                }
            }
            return null;
        }
    }

    // Private constructor to prevent instantiation — this class is only a namespace for CONSTELLATIONS
    private StarSystem() {
        throw new UnsupportedOperationException("StarSystem is a utility class containing CONSTELLATIONS.");
    }
}
