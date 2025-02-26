import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class GamifyIt {
    private static int coins = 0;
    private static int level = 1;
    private static Map<String, String[]> concepts = new HashMap<>();

    static {
        // Simulated import (e.g., Feynman Vol 1, Ch 1: Motion)
        concepts.put("Level1", new String[]{
            "Drop an object 20m (g=10m/s²). Guess the time!", // Puzzle
            "Why does it fall?", // Question
            "The earth pulls it down" // Answer
        });
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Welcome to GamifyIt!");
        System.out.println("We make concepts fun—starting with physics!");

        while (true) {
            playLevel(scanner, random);
            if (!playAgain(scanner)) break;
        }
        System.out.println("Game Over! Coins: " + coins);
    }

    private static void playLevel(Scanner scanner, Random random) {
        String levelKey = "Level" + level;
        if (!concepts.containsKey(levelKey)) {
            System.out.println("Level locked! Buy more for $0.99?");
            return;
        }

        String[] levelData = concepts.get(levelKey);
        System.out.println("\nLevel " + level + ": " + levelData[0]);
        double correctTime = Math.sqrt(20.0 / 5.0); // t = sqrt(2h/g)
        int tries = 3;

        while (tries > 0) {
            System.out.print("Time (s), " + tries + " tries left: ");
            double guess = scanner.nextDouble();
            if (Math.abs(guess - correctTime) < 0.1) {
                System.out.println("Boom! Got it! Time ≈ " + String.format("%.1f", correctTime) + "s");
                coins += 10;
                break;
            } else {
                System.out.println("Nope! Too " + (guess < correctTime ? "fast" : "slow") + "!");
                tries--;
            }
        }
        if (tries == 0) System.out.println("Crash! It was ≈ " + String.format("%.1f", correctTime) + "s");

        System.out.println("\n" + levelData[1]);
        scanner.nextLine(); // Clear buffer
        String explanation = scanner.nextLine().toLowerCase();
        if (explanation.contains("pull") || explanation.contains("down")) {
            System.out.println("Sweet! +10 coins");
            coins += 10;
        } else {
            System.out.println("Hint: " + levelData[2]);
        }

        System.out.println("Coins: " + coins);
        level++;
    }

    private static boolean playAgain(Scanner scanner) {
        System.out.print("\nNext level or shop? (y/n/buy): ");
        String choice = scanner.nextLine().trim().toLowerCase();
        if (choice.equals("buy")) {
            System.out.println("Buy next pack for $0.99? (y/n)");
            return scanner.nextLine().equals("y");
        }
        return choice.equals("y");
    }
}