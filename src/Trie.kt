import java.util.*
import java.util.function.Function

class Trie internal constructor() {
    private val root: TrieNode
    fun insert(word: String) {
        var current = root
        for (l in word.toCharArray()) {
            current = current.children.computeIfAbsent(l) { TrieNode() }
        }
        current.isEndOfWord = true
    }

    fun containsNode(word: String): Boolean {
        var current = root
        for (ch in word) {
            val node = current.children[ch] ?: return false
            current = node
        }
        return current.isEndOfWord
    }

    val isEmpty: Boolean
        get() = root == null

    fun delete(word: String): Boolean {
        return delete(root, word, 0)
    }

    private fun delete(current: TrieNode, word: String, index: Int): Boolean {
        if (index == word.length) {
            if (!current.isEndOfWord) {
                return false
            }
            current.isEndOfWord = false
            return current.children.isEmpty()
        }
        val ch = word[index]
        val node = current.children[ch] ?: return false
        val shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEndOfWord
        if (shouldDeleteCurrentNode) {
            current.children.remove(ch)
            return current.children.isEmpty()
        }
        return false
    }

    init {
        root = TrieNode()
    }

    internal inner class TrieNode {
        val children: MutableMap<Char, TrieNode> = HashMap()
        var isEndOfWord = false
    }
}