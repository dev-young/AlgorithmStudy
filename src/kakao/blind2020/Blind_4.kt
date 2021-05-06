package kakao.blind2020

//https://programmers.co.kr/learn/courses/30/lessons/60060?language=kotlin
class Blind_4 {
    fun solution(words: Array<String>, queries: Array<String>): IntArray {
        var answer = IntArray(queries.size)

        val trie = Trie()
        val trieInv = Trie()

        words.forEach {
            trie.insert(it)
            trieInv.insert(it.reversed())
        }

        queries.forEachIndexed { i, it ->
            val c = it.count { it == '?' }
            if(it.startsWith("?")) {
                answer[i] = trieInv.getRemainLength(it.replace("?", "").reversed())[c] ?: 0
            } else {
                answer[i] = trie.getRemainLength(it.replace("?", ""))[c] ?: 0
            }
        }
        return answer
    }

    class Trie {
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

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Blind_4()
            val r = s.solution(
                    arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao"), arrayOf(
                    "fro??",
                    "????o",
                    "fr???",
                    "fro???",
                    "frodo",
                    "fro"
            )
            )
            println(r.contentToString())
        }
    }
}
// 걸린 시간(분): 42