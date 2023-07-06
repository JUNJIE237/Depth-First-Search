package srs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import srs.Graph;
import srs.Edge;

public class testing {
    public static void main(String[] args) {
        Graph graph = new Graph(7);
        graph.addEdge(0, 1, 3);  // Edge from 0 to 1 with weight 3
        graph.addEdge(0, 2, 5);  // Edge from 0 to 2 with weight 5
        graph.addEdge(1, 3, 2);  // Edge from 1 to 3 with weight 2
        graph.addEdge(1, 4, 4);  // Edge from 1 to 4 with weight 4
        graph.addEdge(2, 3, 3);  // Edge from 2 to 3 with weight 3
        graph.addEdge(4, 5, 1);  // Edge from 4 to 5 with weight 1

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
                            List<Integer> shortestPath = graph.findShortestPath(allPaths);
                            System.out.println("* SHORTEST PATH FROM " + startVertex + " -> " + destinationVertex + ": " + shortestPath);

                            int totalTime = calculateTotalTime(shortestPath, graph);
                            System.out.println("* ARRIVAL TIME: " + totalTime + " hours");

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

    public static int calculateTotalTime(List<Integer> path, Graph graph) {
        int totalTime = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int source = path.get(i);
            int destination = path.get(i + 1);
            for (Edge edge : graph.adjacencyList[source]) {
                if (edge.getDestination() == destination) {
                    totalTime += edge.getWeight();
                    break;
                }
            }
        }
        return totalTime;
    }
}
