package org.lisak.kafkavkostce;

import java.util.List;
import java.util.Random;

public class LunarLandingTestDataFactory {

    private static final List<LunarLandingTestData> LANDINGS = List.of(
            new LunarLandingTestData("1966-06-02", "Surveyor 1", "USA", 294, "Landing", "Atlas LV-3C Centaur-D"),
            new LunarLandingTestData("1966-12-24", "Luna 13", "SSSR", 1130, "Landing", "Molniya-M"),
            new LunarLandingTestData("1967-04-20", "Surveyor 3", "USA", 296, "Landing", "Atlas-Centaur"),
            new LunarLandingTestData("1967-09-11", "Surveyor 5", "USA", 303, "Landing", "Atlas-Centaur"),
            new LunarLandingTestData("1967-11-10", "Surveyor 6", "USA", 299, "Landing", "Atlas-Centaur"),
            new LunarLandingTestData("1968-01-10", "Surveyor 7", "USA", 295, "Landing", "Atlas-Centaur"),
            new LunarLandingTestData("1970-09-20", "Luna 16", "SSSR", 1880, "Sample return landing", "Proton-K/D"),
            new LunarLandingTestData("1970-11-17", "Luna 17", "SSSR", 5600, "Rover landing", "Proton-K/D"),
            new LunarLandingTestData("1972-02-21", "Luna 20", "SSSR", 1880, "Sample return landing", "Proton-K/D"),
            new LunarLandingTestData("1973-01-15", "Luna 21", "SSSR", 5600, "Rover landing", "Proton-K/D"),
            new LunarLandingTestData("1976-08-18", "Luna 24", "SSSR", 1880, "Sample return landing", "Proton-K/D"),
            new LunarLandingTestData("2013-12-14", "Chang'e 3", "Čína", 1200, "Rover landing", "Long March 3B"),
            new LunarLandingTestData("2019-01-03", "Chang'e 4", "Čína", 1200, "Rover landing", "Long March 3B"),
            new LunarLandingTestData("2020-12-01", "Chang'e 5", "Čína", 8200, "Sample return landing", "Long March 5"),
            new LunarLandingTestData("2023-08-23", "Chandrayaan-3", "Indie", 1749, "Landing + rover", "LVM3 M4"),
            new LunarLandingTestData("2024-01-19", "SLIM", "Japonsko", 700, "Precision landing demo", "H-IIA 202"),
            new LunarLandingTestData("2024-02-22", "IM-1 Odysseus", "USA", 1908, "Commercial landing", "Falcon 9"),
            new LunarLandingTestData("2024-06-01", "Chang'e 6", "Čína", 8200, "Sample return landing", "Long March 5")
    );

    private static final Random RANDOM = new Random();

    public static LunarLandingTestData randomLanding() {
        int index = RANDOM.nextInt(LANDINGS.size());
        return LANDINGS.get(index);
    }

    public static class LunarLandingTestData {
        private final String date;
        private final String probeName;
        private final String country;
        private final int landingMassKg;
        private final String missionGoal;
        private final String launchVehicle;

        public LunarLandingTestData(String date, String probeName, String country,
                                    int landingMassKg, String missionGoal, String launchVehicle) {
            this.date = date;
            this.probeName = probeName;
            this.country = country;
            this.landingMassKg = landingMassKg;
            this.missionGoal = missionGoal;
            this.launchVehicle = launchVehicle;
        }

        public String getDate() { return date; }
        public String getProbeName() { return probeName; }
        public String getCountry() { return country; }
        public int getLandingMassKg() { return landingMassKg; }
        public String getMissionGoal() { return missionGoal; }
        public String getLaunchVehicle() { return launchVehicle; }

        @Override
        public String toString() {
            return "LunarLanding{" +
                    "date='" + date + '\'' +
                    ", probeName='" + probeName + '\'' +
                    ", country='" + country + '\'' +
                    ", landingMassKg=" + landingMassKg +
                    ", missionGoal='" + missionGoal + '\'' +
                    ", launchVehicle='" + launchVehicle + '\'' +
                    '}';
        }
    }
}