package utils;

import com.github.javafaker.Faker;

import java.util.concurrent.ThreadLocalRandom;
public class Random {
    private static final Faker faker = new Faker();

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        return getRandomItemFromArray(genders);
    }

    public static String getRandomItemFromArray(String[] array) {
        int index = getRandomInt(0, array.length - 1);
        return array[index];
    }


    private static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomSubject() {
        String[] subjects = {"Accounting", "Maths", "Arts", "Social Studies",
                "Chemistry", "Computer Science", "Commerce",
                "Physics", "Economics"};

        return getRandomItemFromArray(subjects);
    }

    public static String getUserHobbies() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        return getRandomItemFromArray(hobbies);
    }

    public static String getRandomState() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return states[faker.random().nextInt(states.length)];
    }

    public static String getRandomCity(String state) {
        switch (state) {
            case "NCR":
                return faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh":
                return faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana":
                return faker.options().option("Karnal", "Panipat");
            case "Rajasthan":
                return faker.options().option("Jaipur", "Jaiselmer");
            default:
                return "City not found";
        }
    }

    public static String getRandomPicture() {
        String[] pictures = {"picture.png", "picture1.png", "picture2.png"};
        return faker.options().option(pictures);
    }

    public static class TestDataGenerator {
        private static final Faker faker = new Faker();
        private static final Random random = new Random();

        public static String[] getRandomDateOfBirth() {
            int day = getRandomInt(1, 28);
            int monthIndex = getRandomInt(0, 11);
            String[] months = {
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
            };
            String month = months[monthIndex];
            int year = getRandomInt(1900, 2024);
            return new String[]{String.valueOf(day), month, String.valueOf(year)};
        }
    }
}

