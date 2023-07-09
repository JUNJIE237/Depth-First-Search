package srs;

import java.util.ArrayList;
import java.util.List;

class Graph {
    private int numVertices;
    private List<Edge>[] adjacencyList;
    private List<String> cityName;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new ArrayList[numVertices + 1];
        cityName = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new ArrayList[numVertices + 1];
        for (int i = 0; i < numVertices; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public int getCityCount() {
        return cityName.size();
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge1 = new Edge(destination, weight);
        adjacencyList[source].add(edge1);
        
        Edge edge2 = new Edge(source, weight);
        adjacencyList[destination].add(edge2);
    }

    public void addNewCity(String newCity, int[] time) {
        cityName.add(newCity);
        int newCityIndex = cityName.size() - 1;

        for (int i = 0; i < numVertices; i++) {
            if (i != newCityIndex) {
                Edge edge = new Edge(i, time[i - 1]);
                adjacencyList[newCityIndex].add(edge);
                adjacencyList[i].add(new Edge(newCityIndex, time[i - 1]));
            }
        }
    }

    public void removeCity(int index) {
        if (index >= 0 && index < cityName.size()) {
            cityName.remove(index);
            adjacencyList[index].clear();

            for (List<Edge> edges : adjacencyList) {
                for (int i = 0; i < edges.size(); i++) {
                    if (edges.get(i).getDestination() == index) {
                        edges.remove(i);
                        i--;
                    } else if (edges.get(i).getDestination() > index) {
                        edges.get(i).setDestination(edges.get(i).getDestination() - 1);
                    }
                }
            }
        }
    }

    public List<List<Integer>> findPathDFS(int start, int destination) {
        boolean[] visited = new boolean[numVertices + 1];
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

    public int calculateTotalTime(List<Integer> path) {
        int totalTime = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int source = path.get(i);
            int destination = path.get(i + 1);
            for (Edge edge : adjacencyList[source]) {
                if (edge.getDestination() == destination) {
                    totalTime += edge.getWeight();
                    break;
                }
            }
        }
        return totalTime;
    }

    public List<Integer> findShortestPath(List<List<Integer>> allPath) {
        List<Integer> shortestPath = new ArrayList<>();
        int shortestLength = Integer.MAX_VALUE;

        for (List<Integer> path : allPath) {
            int pathLength = calculateTotalTime(path);
            if (pathLength < shortestLength) {
                shortestPath = path;
                shortestLength = pathLength;
            }
        }

        for (int i = 0; i < shortestPath.size(); i++) {
            shortestPath.set(i, shortestPath.get(i) + 1);
        }
        return shortestPath;
    }

    public void addCity(String newCity) {
        cityName.add(newCity);
    }

    public String getCity(int index) {
            return cityName.get(index);
    }

    public int getNumberOfVertices() {
        return numVertices;
    }
}



