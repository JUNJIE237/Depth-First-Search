package srs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testing {
    public static void main(String[] args) {
        Graph graph = new Graph(20);

        graph.addCity("NEW YORK");
        graph.addCity("LONDON");
        graph.addCity("TOKYO");
        graph.addCity("KUALA LUMPUR");
        graph.addCity("SHANGHAI");
        graph.addCity("PARIS");
        graph.addCity("RIO");
        graph.addCity("BANGKOK");
        graph.addCity("BERLIN");
        graph.addCity("MOSCOW");

        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 3, 4);
        graph.addEdge(1, 4, 6);
        graph.addEdge(2, 3, 10);
        graph.addEdge(4, 5, 2);
        graph.addEdge(2, 6, 1);
        graph.addEdge(2, 8, 11);
        graph.addEdge(7, 8, 5);
        graph.addEdge(5, 2, 9);
        graph.addEdge(6, 9, 9);
        graph.addEdge(7, 3, 3);
        graph.addEdge(10, 2, 4);
        graph.addEdge(9, 4, 3);

        Scanner scanner = new Scanner(System.in);

        System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*          WELCOME TO THE CITY TRAVEL GUIDE          *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
        
        boolean exit = false;

        while (!exit) {
            System.out.println("* 1. Check Route                                     *");
            System.out.println("* 2. Exit                                            *");
            System.out.print("* Enter Your Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    boolean pathFound = false;

                    while (!pathFound) {
                        System.out.println("******************************************************");
                        System.out.println("* 1. NEW YORK                                        *");
                        System.out.println("* 2. LONDON                                          *");
                        System.out.println("* 3. TOKYO                                           *");
                        System.out.println("* 4. KUALA LUMPUR                                    *");
                        System.out.println("* 5. SHANGHAI                                        *");
                        System.out.println("* 6. PARIS                                           *");
                        System.out.println("* 7. RIO                                             *");
                        System.out.println("* 8. BANGKOK                                         *");
                        System.out.println("* 9. BERLIN                                          *");
                        System.out.println("* 10. MOSCOW                                         *");
                        System.out.print("* ENTER YOUR CURRENT LOCATION: ");

                        int startVertex = scanner.nextInt();

                        System.out.print("******************************************************");
                        scanner.nextLine();

                        System.out.print("\n* ENTER YOUR DESTINATION: ");
                        int destinationVertex = scanner.nextInt();
                        scanner.nextLine();

                        List<List<Integer>> allPaths = graph.findPathDFS(startVertex - 1, destinationVertex - 1);

                        String startCity = graph.getCity().get(startVertex - 1);
                        String endCity = graph.getCity().get(destinationVertex - 1);

                        if (allPaths.isEmpty()) {
                            System.out.println("* NO PATH FROM " + startCity + " -> " + endCity);
                            System.out.println("* PLEASE TRY AGAIN");
                            System.out.println("******************************************************");
                        } else {
                            System.out.println("******************************************************");
                            int count = 1;
                            for (List<Integer> path : allPaths) {
                                List<String> cityPath = new ArrayList<>();
                                for (int vertex : path) {
                                    cityPath.add(graph.getCity().get(vertex));
                                }
                                System.out.println("* PATH " + count + ": " + cityPath);
                                count++;
                            }
                            System.out.println("* SHORTEST PATH FROM " + startCity + " -> " + endCity + ": ");
                            List<Integer> shortestPath = graph.findShortestPath(allPaths);
                            List<String> shortestCityPath = new ArrayList<>();
                            for (int vertex : shortestPath) {
                                shortestCityPath.add(graph.getCity().get(vertex));
                            }
                            System.out.println(shortestCityPath);
        
                            int totalTime = calculateTotalTime(shortestPath, graph);
                            System.out.println("* ARRIVAL TIME: " + totalTime + " hours");
                            System.out.println("******************************************************");
                            pathFound = true;
                        }
                    }
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("* Invalid choice. Please try again!                  *");
                    System.out.println("******************************************************");
                    break;
            }
        }

        System.out.println("******************************************************");
        System.out.println("*              ENJOY! HAVE A NICE DAY                *");
        System.out.println("******************************************************");
        scanner.close();
    }

    private static int calculateTotalTime(List<Integer> shortestPath, Graph graph) {
        return 0;
    }
}

