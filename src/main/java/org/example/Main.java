package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {  //

    static class Baggage {
        int id;
        String passengerName;
        String flightNo;
        double weightKg;
        String status;

        public Baggage(int id, String passengerName, String flightNo, double weightKg, String status) {
            this.id = id;
            this.passengerName = passengerName;
            this.flightNo = flightNo;
            this.weightKg = weightKg;
            this.status = status;
        }
    }

    private static ArrayList<Baggage> baggageList = new ArrayList<>();
    private static int nextId = 1;
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        while (true) {
            displayMenu();
            try {
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        checkInLuggage();
                        break;
                    case 2:
                        viewLuggage();
                        break;
                    case 3:
                        System.out.println("\nExiting system... Thank you!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.\n");
                scanner.nextLine();
            }
        }
    }

    private static void displayMenu() {
        System.out.println("========================================");
        System.out.println("    AIRPORT LUGGAGE CLAIM REGISTRY");
        System.out.println("========================================");
        System.out.println("[1] Check-In Luggage");
        System.out.println("[2] View Checked Luggage");
        System.out.println("[3] Exit System");
        System.out.println("========================================");
    }

    private static void checkInLuggage() {
        System.out.println("\n--- CHECK-IN LUGGAGE ---");

        System.out.print("Enter Passenger Name: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty! Enter Passenger Name: ");
            name = scanner.nextLine().trim();
        }

        System.out.print("Enter Flight Number: ");
        String flightNo = scanner.nextLine().trim();
        while (flightNo.isEmpty()) {
            System.out.print("Flight Number cannot be empty! Enter Flight Number: ");
            flightNo = scanner.nextLine().trim();
        }

        double weight = 0;
        while (true) {
            try {
                System.out.print("Enter Luggage Weight (KG): ");
                weight = scanner.nextDouble();
                scanner.nextLine();
                if (weight > 0) break;
                else System.out.println("Weight must be greater than 0!");
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                scanner.nextLine();
            }
        }

        String status = "";
        while (true) {
            System.out.print("Enter Status (CHECKED / LOADED / CLAIMED): ");
            status = scanner.nextLine().trim().toUpperCase();
            if (status.equals("CHECKED") || status.equals("LOADED") || status.equals("CLAIMED")) {
                break;
            }
            System.out.println("Invalid status! Please use: CHECKED, LOADED, or CLAIMED");
        }

        Baggage newBaggage = new Baggage(nextId++, name, flightNo, weight, status);
        baggageList.add(newBaggage);
        System.out.println("\nSUCCESS: Baggage successfully checked in!\n");
    }

    private static void viewLuggage() {
        System.out.println("\n========================================");
        System.out.println("        CHECKED LUGGAGE LOGS");
        System.out.println("========================================");

        if (baggageList.isEmpty()) {
            System.out.println("        No luggage records found.");
            System.out.println("========================================\n");
            return;
        }

        System.out.println("+----+----------------------+--------------+----------+----------+");
        System.out.println("| ID | PASSENGER NAME       | FLIGHT NO    | WEIGHT_KG| STATUS   |");
        System.out.println("+----+----------------------+--------------+----------+----------+");

        for (Baggage b : baggageList) {
            System.out.printf("| %-2d | %-20s | %-12s | %-7.1f | %-8s |%n",
                    b.id, b.passengerName, b.flightNo, b.weightKg, b.status);
        }

        System.out.println("+----+----------------------+--------------+----------+----------+");
        System.out.println("Total Records Displayed: " + baggageList.size());
        System.out.println("========================================\n");
    }
}

