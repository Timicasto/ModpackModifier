package timicasto.modpackmodifier.tweak.arobj;

import java.awt.*;
import java.util.Map;

public class Astronomical {
    public static class Star {
        public String name;
        public Map<Integer, Planet> planets;
        public int temp, x, y, numPlanets, id;

        public Star(String name, int temp, int x, int y, int numPlanets, int id) {
            this.name = name;
            this.id = id;
            this.temp = temp;
            this.x = x;
            this.y = y;
            this.numPlanets = numPlanets;
        }



    }
    public static class Planet {
        public String name, gas, filterBlock, oceanBlock;
        public boolean hasRing, gasGiant;
        public int atmosphereDensity, gravitationalMultiplier, orbitalDistance, orbitalTheta, orbitalPhi, rotationalPeriod, seaLevel, dimID;
        public String[] artifacts;
        public Color fogColor, skyColor;

        public Planet(String name, boolean hasRing, boolean gasGiant, String gas, String filterBlock, String oceanBlock, int atmosphereDensity, int gravitationalMultiplier, int orbitalDistance, int orbitalTheta, int orbitalPhi, int rotationalPeriod, int seaLevel, int dimID, Color skyColor, Color fogColor, String[] artifacts) {
            this.name = name;
            this.gas = gas;
            this.filterBlock = filterBlock;
            this.artifacts = artifacts;
            this.atmosphereDensity = atmosphereDensity;
            this.dimID = dimID;
            this.oceanBlock = oceanBlock;
            this.hasRing = hasRing;
            this.gasGiant = gasGiant;
            this.gravitationalMultiplier = gravitationalMultiplier;
            this.orbitalDistance = orbitalDistance;
            this.orbitalPhi = orbitalPhi;
            this.orbitalTheta = orbitalTheta;
            this.fogColor = fogColor;
            this.skyColor = skyColor;
            this.rotationalPeriod = rotationalPeriod;
            this.seaLevel = seaLevel;
        }
    }
}
