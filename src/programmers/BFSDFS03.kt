package programmers

import java.util.*


//https://programmers.co.kr/learn/courses/30/lessons/43163?language=kotlin
class BFSDFS03 {
    fun solution(begin: String, target: String, words: Array<String>): Int {
        var answer = Int.MAX_VALUE

        words.forEachIndexed { index, s ->
            if(isChangeable(begin, s)) {
                bfs(words, index, target)?.let {
                    if(answer > it)
                        answer = it
                }
            }
        }

        return if(answer == Int.MAX_VALUE) 0 else answer
    }

    fun bfs(arr: Array<String>, root: Int, target: String): Int? {
        val visited = BooleanArray(arr.size) { false }
        val que: Queue<Int> = LinkedList()
        que.offer(root)
        var level = 1
        while (!que.isEmpty()) {
            val qSize = que.size
            for (n in 1..qSize) {
                val i = que.poll()
                if (visited[i]) continue
                visited[i] = true
//                println("방문 노드:${arr[i]}")
                if(arr[i] == target) return level
                for (j in arr.indices) {
                    if (!visited[j] && isChangeable(arr[i], arr[j])) {
                        que.offer(j)
                    }
                }
            }
            level++
        }
        return null
    }

    /**두 문자열이 한 글자만 다른지 확인*/
    private fun isChangeable(s1: String, s2: String): Boolean {
        var diffCount = 0
        s1.forEachIndexed { index, c ->
            if (c != s2[index]) {
                diffCount++
                if (diffCount > 1)
                    return false
            }
        }
        return diffCount == 1
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = BFSDFS03()
            val r = s.solution("hit", "cog", arrayOf("hot", "dot", "dog", "lot", "log", "cog"))
            println(r)
        }
    }
}

// 걸린 시간: 56분