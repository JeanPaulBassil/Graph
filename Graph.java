package Graph;

import java.util.*;

public class Graph {
    ArrayList<Character> vertices;
    ArrayList<LinkedList<Character>> adjacency;

    public Graph() {
        vertices = new ArrayList<>();
        adjacency = new ArrayList<>();
    }

    public void addVertex(Character c) {
        vertices.add(c);
        adjacency.add(new LinkedList<>());
    }

    public void addEdge(Character c, Character d) {
        adjacency.get(vertices.indexOf(c)).add(d);
        adjacency.get(vertices.indexOf(d)).add(c);
    }

    public void removeEdge(Character c, Character d) {
        adjacency.get(vertices.indexOf(c)).remove(d);
        adjacency.get(vertices.indexOf(d)).remove(c);
    }

    public int degreeOf(Character c) {
        return adjacency.get(vertices.indexOf(c)).size();
    }

    public void BFS(Character start) {
        Queue<Character> q = new LinkedList<>();
        boolean[] isVisited = new boolean[vertices.size()];
        q.add(start);
        while (!q.isEmpty()) {
            char character = q.poll();
            if (!isVisited[vertices.indexOf(character)]) {
                System.out.println("Explored: " + character);
                isVisited[vertices.indexOf(character)] = true;
                LinkedList<Character> current = adjacency.get(vertices.indexOf(character));
                q.addAll(current);
            }

        }
    }

    public void complement() {
        for (LinkedList<Character> characters : adjacency) {
            boolean[] isThere = new boolean[vertices.size()];
            for (Character character : characters) {
                int index = 0;
                for (int j = 0; j < vertices.size(); j++) {
                    if (vertices.get(j) == character) {
                        index = j;
                        break;
                    }
                }
                isThere[index] = true;
            }
            for (int y = 0; y < isThere.length; y++) {
                if (!isThere[y]) {
                    characters.add(vertices.get(y));
                } else characters.remove(vertices.get(y));
            }
        }
    }

    public void DFS(char start) {
        Stack<Character> stack = new Stack<>();
        boolean[] isVisited = new boolean[vertices.size()];
        stack.push(start);
        isVisited[vertices.indexOf(start)] = true;
        while (!stack.isEmpty()) {
            char current = stack.pop();
            System.out.println("Explored: " + current);
            for(int i = 0; i < adjacency.get(vertices.indexOf(current)).size(); i++){
                if (!isVisited[vertices.indexOf(adjacency.get(vertices.indexOf(current)).get(i))]) {
                    stack.push(adjacency.get(vertices.indexOf(current)).get(i));
                    isVisited[vertices.indexOf(adjacency.get(vertices.indexOf(current)).get(i))] = true;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Vertices" + vertices.toString() + "\n");
        output.append("Edges:");
        for (LinkedList<Character> list : adjacency) {
            output.append("\n".concat(list.toString()));
        }
        return output.toString();
    }

}
