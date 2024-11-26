package midterm_2_practice.dijkstra_algorithm;

import java.util.Random;
import java.util.ArrayList;
import java.util.Locale;

public class Distances {

    private double[][] distances;
    private Random random = new Random();

    public Distances(int n) {
        double[][] distances = new double[n][n];
        this.distances = distances;
        initializeDistances();
    }

    public double getDistance(int i, int j) {
        return distances[i][j];
    }

    private void initializeDistances() {
        int n = distances.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    distances[i][j] = 0;
                } else if (random.nextDouble() < 0.5) {
                    distances[i][j] = Double.POSITIVE_INFINITY;
                    // no connection
                } else {
                    distances[i][j] = random.nextDouble() * 9;
                }
                distances[j][i] = distances[i][j];
            }
        }
    }

    public void printDistances() {
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[i].length; j++) {
                System.out.print(" ");
                if (distances[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.print(" - ");
                } else {
                    System.out.printf(Locale.US,"%.1f", distances[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public ArrayList<Integer> getNeighbors(int j) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < distances[j].length; i++) {
            if (distances[j][i] < Double.POSITIVE_INFINITY && i != j) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    public ArrayList<Integer> randomPath(int start, int end) {

        ArrayList<Integer> path = new ArrayList<>();
        
        int current = start;
        path.add(start);

        int steps = 0;
        int n = distances.length;

        while (current != end && steps < n*n) {
            ArrayList<Integer> neighbors = getNeighbors(current);
            if (neighbors.size() == 0) {
                return null;
            }
            int next = neighbors.get(random.nextInt(neighbors.size()));
            path.add(next);
            current = next;
        }
        return path;
    }

    public ArrayList<Integer> dijkstraPath(int start, int end) {

        int n = distances.length;

        ArrayList<Integer> unvisited = new ArrayList<>();
        for (int i = 0; i < n; i++) {
                unvisited.add(i);
        }
        ArrayList<Integer> visited = new ArrayList<>();

        ArrayList<Double> distanceToStart = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i != start) {
                distanceToStart.add(Double.POSITIVE_INFINITY);
            } else {
                distanceToStart.add(0.0);
            }
        }

        ArrayList<ArrayList<Integer>> pathFromStart = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pathFromStart.add(new ArrayList<>());
            pathFromStart.get(i).add(start);
            if (i != start) pathFromStart.get(i).add(i);
        }
        
        while (!visited.contains(end)) {

            double minDistance = Double.POSITIVE_INFINITY;
            int next = -1;
            for (int i = 0; i < n; i++) {
                if (unvisited.contains(i) && distanceToStart.get(i) < minDistance) {
                    minDistance = distanceToStart.get(i);
                    next = i;
                }
            }
            if (next == -1) return null;

            int current = next;

            for (int neighbor : getNeighbors(current)) {
                if (unvisited.contains(neighbor)) {
                    double newDistance = distanceToStart.get(current) + distances[current][neighbor];
                    if (newDistance < distanceToStart.get(neighbor)) {
                        distanceToStart.set(neighbor, newDistance);
                        ArrayList<Integer> pathToCurrent = pathFromStart.get(current);
                        pathFromStart.set(neighbor, new ArrayList<>(pathToCurrent));
                        pathFromStart.get(neighbor).add(neighbor);
                    }
                }
            }
            visited.add(current);
            unvisited.remove(unvisited.indexOf(current));
        }
        return pathFromStart.get(end);
    }

    public double lengthOfPath(ArrayList<Integer> path) {
        if (path == null) {
            return Double.POSITIVE_INFINITY;
        }
        double length = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            if (distances[path.get(i)][path.get(i + 1)] == Double.POSITIVE_INFINITY) {
                return Double.POSITIVE_INFINITY;
            } else {
                length += distances[path.get(i)][path.get(i + 1)];
            }
        }
        return length;
    }
    
}
