package tree.trie;


/**
 * 字典树：
 *    每个节点代表一个字母，每个节点下面都有26个英文字母。
 *    空间复杂度: O(word.length ^ 26)
 */
public class TrieNode {

    /**
     * 每个节点下的26个children，用TrieNode数组来存储
     */
    TrieNode[] children;

    /**
     * 在搜索的过程中，我们需要判断，当前这个节点能否组成一个单词，如果可以则为true，不行则为false
     */
    boolean isWord;

    public TrieNode() {
        this.children = new TrieNode[26]; // 注意，这里26个不一定只有26个。这里只是针对Leetcode211这一题
        isWord = false;
    }
}
