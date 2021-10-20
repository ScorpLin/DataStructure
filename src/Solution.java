import java.util.*;

/**
 思考方式：只有一个字母不相同的两个单词，互相连接，然后我们用BFS来找最短的距离
 **/
public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Graph graph = new Graph();
        for (String word : wordList) {
            graph.addNode(new Node(word));
        }
        // 如果beginWord不能存在wordList中的话，我们需要另外将其加入到graph中
        List<String> wordList2 = new LinkedList<>();
        if (!wordList.contains(beginWord)) {
            graph.addNode(new Node(beginWord));
            wordList2.add(beginWord);
            wordList2.addAll(wordList);
        }

        // 将wordList中的word依次遍历
        for (String word : wordList2) {
            Node node = graph.getNode(word);
            for (int i = 0; i < word.length(); i++) {
                // 没遍历一个word就将该String类型的word转换成char[]数组
                char[] wordUnit = word.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    // word中的每个位置分别用a-z替换一下
                    wordUnit[i] = j;
                    String temp = new String(wordUnit);
                    // 然后判断graph中是否存在这个节点，并且当前node的neighbors中是否存在temp，并且该temp是否和当前节点一样
                    if (graph.getNode(temp) != null && !node.neighbors.contains(temp) && !temp.equals(word)) {
                        Node debug = graph.getNode(temp);
                        node.neighbors.add(graph.getNode(temp));
                    }
                }
            }
        }
        // 图构建完毕以后，就直接可以使用BFS
        HashSet<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        visited.add(graph.getNode(beginWord));
        queue.offer(graph.getNode(beginWord));

        int res = 0; // 作为最后的结果返回

        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                // 如果此时遍历的node已经和endWord相等了，那么就直接返回res
                if (node.word.equals(endWord)) {
                    return res;
                }
                for (Node neighbor : node.neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";

        List<String> wordList = new ArrayList<>();
        String[] list = new String[]{"hot", "dot", "lot", "log", "cog"};
        wordList.addAll(Arrays.asList(list));

        Solution solution = new Solution();
        int res = solution.ladderLength(beginWord, endWord, wordList);

        System.out.println(res);
    }
}

// 这个Graph类用来存储各个节点之间的关系
class Graph {
    List<Node> nodes = new ArrayList<>();
    HashMap<String, Integer> map; // map中的key用来存储index

    public Graph() {
        this.nodes = nodes;
        this.map = new HashMap<>();
    }

    public void addNode(Node node) {
        map.put(node.word, nodes.size()); // nodes.size()是每个node在nodes list中存放的位置
        nodes.add(node);
    }

    /**
     用来在Graph中找到相应的node节点
     **/
    public Node getNode(String word) {
        // 寻找方式：首先判断map中是否存在该node，如果存在，就通过map中的key获得该node在nodes list中存放的index
        //         然后通过这个index去nodes list中寻找
        if (map.containsKey(word)) {
            return nodes.get(map.get(word));
        }
        // 如果没有找到就返回null
        return null;
    }
}

class Node {
    String word;
    List<Node> neighbors;
    public Node(String word) {
        this.word = word;
        this.neighbors = new ArrayList<>();
    }
}

