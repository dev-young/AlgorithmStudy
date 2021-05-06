package algorithm

class Trie {
    private val root: TrieNode

    fun insert(word: String) {
        var current = root
        var remainLength = word.length

        val count = current.remainLength.computeIfAbsent(remainLength) {0}
        current.remainLength[remainLength] = count+1

        for (c in word) {
            remainLength--
            current = current.children.computeIfAbsent(c) {
                TrieNode()
            }
            val count = current.remainLength.computeIfAbsent(remainLength) {0}
            current.remainLength[remainLength] = count+1
        }
        current.isEndOfWord = true
    }

    fun getRemainLength(word: String): MutableMap<Int, Int> {
        var current = root
        for (ch in word) {
            val node = current.children[ch] ?: return hashMapOf()
            current = node
        }
        return current.remainLength
    }

    init {
        root = TrieNode()
    }

    inner class TrieNode {
        val children: MutableMap<Char, TrieNode> = HashMap()
        val remainLength: MutableMap<Int, Int> = HashMap()    //남은 길이 <남은 글자 수, 갯수> ex)[1,4][3,3] -> 현재 노드에서 만들어진 문자열보다 1글자 더 많은 문자열의 갯수는 4개, 3글자 더 많은 문자열의 갯수는 3개
        var isEndOfWord = false
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

        }
    }
}