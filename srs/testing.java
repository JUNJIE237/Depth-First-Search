package srs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testing {
    public static void main(String[] args) {

        Graph graph = new Graph(20);

        BufferedReader reader = null;
        BufferedReader reader2 = null;
        try {
            reader = new BufferedReader(new FileReader("/Users/aishapeng/Python/MalaysianPayGap-master/data/CityName.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String CityName = line.trim();

                graph.addCity(CityName);
            }

            graph.setNumVertices(graph.getCityCount());

            reader2 = new BufferedReader(new FileReader("/Users/aishapeng/Downloads/Vertices.txt"));
            String line2;
            while ((line2 = reader2.readLine()) != null) {
                String[] digits = line2.split(" ");
                if (digits.length == 3) {
                    int source = Integer.parseInt(digits[0]);
                    int destination = Integer.parseInt(digits[1]);
                    int weight = Integer.parseInt(digits[2]);

                    graph.addEdge(source - 1, destination - 1, weight);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*          WELCOME TO THE CITY TRAVEL GUIDE          *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
        
        boolean exit = false;

        while (!exit) {
            System.out.println("* 1. Check Route                                     *");
            System.out.println("* 2. Add / Remove City                               *");
            System.out.println("* 3. Exit                                            *");
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
                        scanner.nextLine();

                        System.out.print("******************************************************");

                        System.out.print("\n* ENTER YOUR DESTINATION: ");
                        int destinationVertex = scanner.nextInt();
                        scanner.nextLine();

                        List<List<Integer>> allPaths = graph.findPathDFS(startVertex - 1, destinationVertex - 1);
                        
                        String startCity = graph.getCity(startVertex - 1);
                        String endCity = graph.getCity(destinationVertex - 1);

                        if (allPaths.isEmpty()) {
                            System.out.println("* NO PATH FROM " + startCity + " -> " + endCity);
                            System.out.println("* PLEASE TRY AGAIN");
                        } else {
                            System.out.println("******************************************************");
                            int count = 1;
                            for (List<Integer> path : allPaths) {
                                List<String> cityPath = new ArrayList<>();
                                for (int vertex : path) {
                                    String city = graph.getCity(vertex);
                                    cityPath.add(city);
                                }
                                System.out.println("* PATH " + count + ": " + cityPath);
                                count++;
                            }
                            System.out.println("* SHORTEST PATH FROM " + startCity + " -> " + endCity + ": ");
                            
                            List<Integer> shortestPath = graph.findShortestPath(allPaths);
                            List<String> shortestCityPath = new ArrayList<>();
                            
                            for (int vertex : shortestPath) {
                                String city = graph.getCity(vertex);
                                shortestCityPath.add(city);
                            }
                            System.out.println(shortestCityPath);
        
                            int totalTime = graph.calculateTotalTime(shortestPath);
                            System.out.println("* ARRIVAL TIME: " + totalTime + " hours");
                            System.out.println("******************************************************");
                            pathFound = true;
                        }
                    }
                    break;
                case 2:
                    System.out.println("******************************************************");
                    boolean valid = false;
                    while(!valid)
                    {
                        System.out.print("* ADD OR DELETE (A/D): ");
                        String answer = scanner.nextLine();
                        if (answer.equalsIgnoreCase("A")) {
                            System.out.print("* ENTER NEW CITY NAME: ");
                            String newCityName = scanner.nextLine();
                            System.out.println("* ENTER ARRIVAL TIME: ");
                            int[] timeTaken = new int[graph.getNumberOfVertices()];
                            for (int i = 0; i < timeTaken.length; i++) {
                                System.out.print("* TIME TAKEN FROM [" + graph.getCity(i) + "] -> [" + newCityName + "]: ");
                                timeTaken[i] = scanner.nextInt();
                                scanner.nextLine();
                            }
                            graph.addNewCity(newCityName, timeTaken);
                            System.out.println("* CITY ADDED! ");
                            System.out.println("******************************************************");
                            valid = true;
                        } else if (answer.equalsIgnoreCase("D")) {
                            System.out.print("* ENTER CITY TO BE REMOVED: ");
                            int removeCityName = scanner.nextInt();
                            scanner.nextLine(); 
                            graph.removeCity(removeCityName - 1);
                            System.out.println("* CITY REMOVED!");
                            System.out.println("******************************************************");
                            valid = true;
                        } else {
                            System.out.println("* Invalid operation.");
                            System.out.println("******************************************************");
                        }
                    }
                    break;
                case 3:
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

}

