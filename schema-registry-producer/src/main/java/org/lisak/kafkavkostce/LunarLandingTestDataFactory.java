package org.lisak.kafkavkostce;

import java.util.List;
import java.util.Random;

public class LunarLandingTestDataFactory {

    private static final List<LunarLandingTestData> LANDINGS = List.of(
            new LunarLandingTestData("1966-06-02", "Surveyor 1", "USA"),
            new LunarLandingTestData("1966-12-24", "Luna 13", "SSSR"),
            new LunarLandingTestData("1967-09-11", "Surveyor 5", "USA"),
            new LunarLandingTestData("1967-11-10", "Surveyor 6", "USA"),
            new LunarLandingTestData("1968-01-10", "Surveyor 7", "USA"),
            new LunarLandingTestData("1970-09-20", "Luna 16", "SSSR"),
            new LunarLandingTestData("1970-11-17", "Luna 17", "SSSR"),
            new LunarLandingTestData("1972-02-21", "Luna 20", "SSSR"),
            new LunarLandingTestData("1973-01-15", "Luna 21", "SSSR"),
            new LunarLandingTestData("1976-08-18", "Luna 24", "SSSR"),
            new LunarLandingTestData("2013-12-14", "Chang’e 3", "Čína"),
            new LunarLandingTestData("2019-01-03", "Chang’e 4", "Čína"),
            new LunarLandingTestData("2020-12-01", "Chang’e 5", "Čína"),
            new LunarLandingTestData("2023-08-23", "Chandrayaan-3", "Indie"),
            new LunarLandingTestData("2024-01-19", "SLIM", "Japonsko"),
            new LunarLandingTestData("2024-02-22", "IM-1 “Odysseus”", "USA"),
            new LunarLandingTestData("2024-06-01", "Chang’e 6", "Čína")
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

        public LunarLandingTestData(String date, String probeName, String country) {
            this.date = date;
            this.probeName = probeName;
            this.country = country;
        }

        public String getDate() {
            return date;
        }

        public String getProbeName() {
            return probeName;
        }

        public String getCountry() {
            return country;
        }

        @Override
        public String toString() {
            return "LunarLandingTestData{" +
                    "date='" + date + '\'' +
                    ", probeName='" + probeName + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }
    }
}
