import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Graph {
    private int numVertices;
    private List<Integer>[] adjacencyList;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new ArrayList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int source, int destination) {
        adjacencyList[source].add(destination);
        adjacencyList[destination].add(source);
    }

    public List<List<Integer>> findPathDFS(int start, int destination) {
        boolean[] visited = new boolean[numVertices];
        List<Integer> currentPath = new ArrayList<>();
        List<List<Integer>> allPath = new ArrayList<>();
        dfs(start, destination, visited, currentPath, allPath);
        return allPath;
    }

    private void dfs(int vertex, int destination, boolean[] visited, List<Integer> currentPath,
            List<List<Integer>> allPath) {
        visited[vertex] = true;
        currentPath.add(vertex);

        if (vertex == destination) {
            allPath.add(new ArrayList<>(currentPath));
        } else {
            for (int adjacent : adjacencyList[vertex]) {
                if (!visited[adjacent]) {
                    dfs(adjacent, destination, visited, currentPath, allPath);
                }
            }
        }

        visited[vertex] = false;
        // If no path found from the current vertex, remove it from the path
        currentPath.remove(currentPath.size() - 1);
    }

    public List<Integer> findShortestPath(List<List<Integer>> allPath) {
        List<Integer> shortestPath = new ArrayList<>();
        int shortestLength = Integer.MAX_VALUE;

        for (List<Integer> path : allPath) {
            if (path.size() < shortestLength) {
                shortestPath = path;
                shortestLength = path.size();
            }
        }
        return shortestPath;
    }
}

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
            System.out.println("1. Check Route");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

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
                            System.out.println("* PLEASETRY AGAIN");
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
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        System.out.println("******************************************************");
        System.out.println("*              ENJOY! HAVE A NICE DAY                *");
        System.out.println("******************************************************");
        scanner.close();
    }
}
