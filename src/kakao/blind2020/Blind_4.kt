package kakao.blind2020

import java.util.HashMap

//https://programmers.co.kr/learn/courses/30/lessons/60060?language=kotlin
class Blind_4 {
    fun solution(words: Array<String>, queries: Array<String>): IntArray {
        var answer = IntArray(queries.size)

        val trie = Trie()
        val invTrie = Trie()
        words.forEach {
            trie.insert(it)
            invTrie.insert(it.reversed())
        }

        queries.forEachIndexed { index, querie ->
            var count: Int
            val targetStr = querie.replace("?", "")
            val wildCardCount = querie.length - targetStr.length
            count = if(querie.startsWith("?")){
                invTrie.getRemainLength(targetStr.reversed())[wildCardCount]?:0
            } else {
                trie.getRemainLength(targetStr)[wildCardCount]?:0
            }

            answer[index] = count
        }


        return answer
    }

    class Trie internal constructor() {
        private val root: TrieNode
        fun insert(word: String) {
            var current = root
            var remainLength = word.length

            val count = current.remainLength.computeIfAbsent(remainLength) {0}
            current.remainLength[remainLength] = count+1

            for (l in word.toCharArray()) {
                remainLength--
                current = current.children.computeIfAbsent(l) {
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
            val remainLength: MutableMap<Int, Int> = HashMap()    //남은 길이 <남은 글자 수, 갯수>
            var isEndOfWord = false
        }
    }

}

fun main() {
    val s = Blind_4()
    val r = s.solution(
        arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao"), arrayOf(
            "fro??",
            "????o",
            "fr???",
            "fro???",
            "frod?"
        )
    )
    println(r.contentToString())
}