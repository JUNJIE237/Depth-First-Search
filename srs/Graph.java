package srs;

import java.util.ArrayList;
import java.util.List;

class Graph {
     private int numVertices;
    private List<Integer>[] adjacencyList;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new ArrayList[numVertices];
        cityName = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(destination, weight);
        adjacencyList[source].add(edge);
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
            for (Edge edge : adjacencyList[vertex]) {
                int adjacent = edge.getDestination();
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

    public void addCity(String newCity) {
        cityName.add(newCity);
    }

    public List<String> getCity() {
        return cityName;
    }
}

class Edge {
    private int destination;
    private int weight;

    public Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public int getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}
