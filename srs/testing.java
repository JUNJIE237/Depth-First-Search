package srs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import srs.Graph;



public class testing {
    public static void main(String[] args) {
        Graph graph = new Graph(7);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);

        Scanner scanner = new Scanner(System.in);

        System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*          WELCOME TO THE CITY TRAVEL GUIDE          *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");

        boolean exit = false;

        while (!exit) {
            System.out.println("Press 1 for Check Route");
            System.out.println("Press 2 for Exit\n");
            System.out.print("Enter Your Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    boolean pathFound = false;

                    while (!pathFound) {
                        System.out.print("\n* ENTER YOUR CURRENT LOCATION: ");
                        int startVertex = scanner.nextInt();

                        System.out.print("******************************************************");
                        scanner.nextLine();

                        System.out.print("\n* ENTER YOUR DESTINATION: ");
                        int destinationVertex = scanner.nextInt();

                        System.out.print("******************************************************\n");
                        scanner.nextLine();

                        List<List<Integer>> allPaths = graph.findPathDFS(startVertex, destinationVertex);

                        if (allPaths.isEmpty()) {
                            System.out.println("* NO PATH FROM " + startVertex + " -> " + destinationVertex);
                            System.out.println("* PLEASE TRY AGAIN");
                            System.out.println("******************************************************");
                        } else {
                            int count = 1;
                            for (List<Integer> path : allPaths) {
                                System.out.println("* PATH " + count + ": " + path);
                                count++;
                            }
                            System.out.println("* SHORTEST PATH FROM " + startVertex + " -> " + destinationVertex + ": "
                                    + graph.findShortestPath(allPaths));
                            pathFound = true;
                        }
                    }
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again!");
                    break;
            }
        }

        System.out.println("******************************************************");
        System.out.println("*              ENJOY! HAVE A NICE DAY                *");
        System.out.println("******************************************************");
        scanner.close();
    }
}
