package com.example;
import java.util.Scanner;
import java.util.*;

public class cinema {

    public static String[][] seats;
    public static int numOfRows;
    public static int numOfSeats;
    public static int rowNumber;
    public static int seatNumber;
    public static int count = 0;
    public static int currentIncome = 0;
    public static int ticketPrice = 0;

    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        createCinema();

        int choice;

        do {
            
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Show Statistics");
            System.out.println("0. Exit");

            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showTheSeats();
                    break;
                case 2:
                    buyATicket();
                    break;
                case 3:
                    showStats();
                    break;
                case 0:
                    return;
            }
        } while (choice != 0);

    }

    public static void createCinema() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        numOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        numOfSeats = scanner.nextInt();

        seats = new String[numOfRows][numOfSeats];
        for (String[] arr : seats) {
            Arrays.fill(arr, "S");
        }

    }
    public static void showTheSeats() {
        Scanner scanner = new Scanner(System.in);

        int rowCount = 1;
        int seatCount = 1;
        System.out.println("Cinema:");
        System.out.print(" ");

        while (seatCount <= seats[0].length) {
            System.out.print(" " + seatCount);
            seatCount++;
        }

        System.out.println();

        for (String[] arr : seats) {
            System.out.print(rowCount);
            for (String c : arr) {
                System.out.print(" " + c);
            }
            System.out.println();
            rowCount++;
        }


    }

    public static void showStats() {
        int totalIncome = 0;
        if (numOfRows * numOfSeats < 60) {
            totalIncome = numOfRows * numOfSeats * 10;
        } else if (numOfRows * numOfSeats > 60 && (numOfRows <= 9 && numOfSeats <= 9)) {

            if (numOfRows % 2 == 0) {
                totalIncome = (numOfRows / 2) * numOfSeats * 10 + ((numOfRows / 2) * numOfSeats * 8);
            } else {
                totalIncome = (numOfRows / 2) * numOfSeats * 10 + ((numOfRows / 2 + 1) * numOfSeats * 8);
            }
        }


        if (rowNumber == 0 && seatNumber == 0) {
            count = 0;
        }
        
        System.out.println();
        System.out.printf("Number of purchased tickets: %d %n", count);
        double percent = ( (double) count / (numOfRows * numOfSeats)) * 100;
        String percent_str = String.format("Percentage: %.2f", percent);
        System.out.println(percent_str + "%");
        System.out.println("Current Income: " + "$" + currentIncome);
        System.out.println("Total Income: " + "$" + totalIncome);
        System.out.println();

    }
    public static void buyATicket() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number: ");
        rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        seatNumber = scanner.nextInt();



        if (rowNumber > numOfRows || seatNumber > numOfSeats) {
            System.out.println("Wrong input!");
        } else {
            System.out.println();
            

            if ((numOfRows * numOfSeats) < 60) {
                ticketPrice = 10;
            } else if ((numOfRows * numOfSeats) > 60 && (numOfRows <= 9 && numOfSeats <= 9)) {
                if (numOfRows % 2 == 0) {

                    for (int i = (numOfRows / 2); i >= 1; i--) {
                        if (i >= 1) {
                            ticketPrice = 10;
                        }
                    }

                    if (((numOfRows/2) >= (numOfRows/2) + 1) && (numOfRows / 2) <= numOfRows) {
                        for (int i = ((numOfRows/2) + 1); i <= numOfRows; i++) {
                            if (i < numOfRows) {
                                ticketPrice = 8;
                            }
                        }
                    }
                } else if (numOfRows % 2 != 0) {
                    if (((numOfRows / 2) + 1) >= rowNumber) {
                        ticketPrice = 10;
                    }
                    if (((numOfRows / 2) + 1) <= numOfRows && ((numOfRows / 2) + 1) <= rowNumber) {
                        ticketPrice = 8;
                    }
                }
            }
            if (seats[(rowNumber - 1)][(seatNumber - 1)] == "B") {
                System.out.println("That ticket has already been purchased!");
                System.out.println();
                buyATicket();
            } else {
                seats[(rowNumber - 1)][(seatNumber - 1)] = "B";
                System.out.println("Ticket Price: " + "$" + ticketPrice);
                count++;
                currentIncome += ticketPrice;
            }

        }

    }

}

