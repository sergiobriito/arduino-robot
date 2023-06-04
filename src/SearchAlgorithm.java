import java.util.*;

public class SearchAlgorithm {
    private final int[][] grid;
    private final int gridSize;

    public SearchAlgorithm(int[][] grid) {
        this.grid = grid;
        gridSize = grid.length;
    }

    public String[] findPath(int[] startPoint, int[] endPoint) {
        Set<Node> openSet = new HashSet<>();
        Set<Node> closedSet = new HashSet<>();

        Node startNode = new Node(startPoint[0], startPoint[1], 0, calculateHeuristic(startPoint, endPoint), null);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node currentNode = getLowestFScoreNode(openSet);
            openSet.remove(currentNode);
            closedSet.add(currentNode);

            if (currentNode.x == endPoint[0] && currentNode.y == endPoint[1]) {
                return buildPath(currentNode);
            }

            List<Node> neighbors = getNeighbors(currentNode);
            for (Node neighbor : neighbors) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGScore = currentNode.gScore + 1;

                if (!openSet.contains(neighbor) || tentativeGScore < neighbor.gScore) {
                    neighbor.cameFrom = currentNode;
                    neighbor.gScore = tentativeGScore;
                    neighbor.fScore = neighbor.gScore + calculateHeuristic(new int[]{neighbor.x, neighbor.y}, endPoint);

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return new String[0];
    }

    private Node getLowestFScoreNode(Set<Node> openSet) {
        return openSet.stream()
                .min(Comparator.comparingInt(node -> node.fScore))
                .orElse(null);
    }

    private int calculateHeuristic(int[] startPoint, int[] endPoint) {
        return Math.abs(startPoint[0] - endPoint[0]) + Math.abs(startPoint[1] - endPoint[1]);
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();

        int x = node.x;
        int y = node.y;

        if (isValidCoordinate(x - 1, y)) {
            neighbors.add(new Node(x - 1, y));
        }
        if (isValidCoordinate(x + 1, y)) {
            neighbors.add(new Node(x + 1, y));
        }
        if (isValidCoordinate(x, y - 1)) {
            neighbors.add(new Node(x, y - 1));
        }
        if (isValidCoordinate(x, y + 1)) {
            neighbors.add(new Node(x, y + 1));
        }

        return neighbors;
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < gridSize && y >= 0 && y < gridSize && grid[x][y] != 1;
    }

    private String[] buildPath(Node endNode) {
        List<String> path = new ArrayList<>();
        Node currentNode = endNode;

        while (currentNode.cameFrom != null) {
            Node previousNode = currentNode.cameFrom;
            if (currentNode.x < previousNode.x) {
                path.add("N");
            } else if (currentNode.x > previousNode.x) {
                path.add("S");
            } else if (currentNode.y < previousNode.y) {
                path.add("O");
            } else if (currentNode.y > previousNode.y) {
                path.add("L");
            }
            currentNode = previousNode;
        }

        Collections.reverse(path);
        return path.toArray(new String[0]);
    }

    private static class Node {
        int x;
        int y;
        int gScore;
        int fScore;
        Node cameFrom;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int gScore, int fScore, Node cameFrom) {
            this.x = x;
            this.y = y;
            this.gScore = gScore;
            this.fScore = fScore;
            this.cameFrom = cameFrom;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Node other = (Node) obj;
            return x == other.x && y == other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
