package programmers


//https://programmers.co.kr/learn/courses/30/lessons/43162?language=kotlin
class BFSDFS02 {
    fun solution(n: Int, computers: Array<IntArray>): Int {
        var answer = 0

        val visited = BooleanArray(n) { false }

        for (i in 0 until n) {
            if (!visited[i]) {
                answer++
                dfs(computers, visited, i)
            }
        }

        return answer
    }

    private fun dfs(arr: Array<IntArray>, visited: BooleanArray, i: Int) {
        visited[i] = true
        for (j in arr.indices) {
            if (!visited[j] &&      /*방문하지 않았을 경우*/
                arr[i][j] == 1 &&   /*간선이 존재하는 경우*/
                i != j              /*자기 자신이 아닌 경우*/
            ) {
                /*노드 j가 노드 i에 연결되어있음*/
                dfs(arr, visited, j)
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = BFSDFS02()
            val r = s.solution(5,
                arrayOf(intArrayOf(0, 1, 0, 1, 1),
                    intArrayOf(0, 0, 1, 0, 1),
                    intArrayOf(0, 0, 0, 0, 1),
                    intArrayOf(0, 0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0, 0, 0)))
            println(r)
        }
    }
}

// 걸린 시간: 분