package kakao.intern2021

class Kakao2021_4 {
    fun solution(n: Int, start: Int, end: Int, roads: Array<IntArray>, traps: IntArray): Int {
        var answer: Int = Int.MAX_VALUE
        val edges = Array(n + 1) { hashMapOf<Int, Int>() }
        val edgesIn = Array(n + 1) { hashMapOf<Int, Int>() }
//        val edgesRel = Array(n + 1) { hashSetOf<Triple<Int,Int,Int>>() }    //해당 노드와 관련된 모든 간선
        val trapSet = traps.toSet()

        roads.forEachIndexed { index, ints ->
            edges[ints[0]][ints[1]] = Math.min(edges[ints[0]][ints[1]] ?: Int.MAX_VALUE, ints[2])
            edgesIn[ints[1]][ints[0]] = Math.min(edges[ints[1]][ints[0]] ?: Int.MAX_VALUE, ints[2])
//            edgesRel[ints[0]].add(Triple(ints[0],ints[1], ints[2]))
//            edgesRel[ints[1]].add(Triple(ints[0],ints[1], ints[2]))
        }

        fun invers(node: Int) {
            val eOut = HashMap(edges[node])
            val eIn = HashMap(edgesIn[node])
            edges[node].clear()
            edgesIn[node].clear()
            eOut.forEach { t, u ->
                edges[t][node] = u
                edgesIn[node][t] = u
                edgesIn[t].remove(node)
            }
            eIn.forEach { t, u ->
                edges[t].remove(node)
                edgesIn[t].remove(node)
                edges[node][t] = u
                edgesIn[t][node] = u
            }
        }

        val visited = hashMapOf<Int, Int>()
        fun dfs(node: Int, time: Int) {
            if(time > answer) return
            if (node == end) {
                answer = Math.min(time, answer)
                return
            }
            val isInv = trapSet.contains(node)

            if (isInv) invers(node)
            val temp = HashMap(edges[node])
            for ((t, u) in temp) {
                if ((visited[t] ?: 0) > 3)
                    continue
                visited[t] = (visited[t] ?: 0) + 1
                dfs(t, u + time)
            }

            edges[node] = temp

        }

        fun dfs2(node: Int, edgs:HashMap<Int,Int>, time: Int) {
            if(time > answer) return
            if (node == end) {
                answer = Math.min(time, answer)
                return
            }
            val isInv = trapSet.contains(node)

            if (isInv) invers(node)
            val temp = HashMap(edges[node])
            for ((t, u) in temp) {
                if ((visited[t] ?: 0) > 3)
                    continue
                visited[t] = (visited[t] ?: 0) + 1
                dfs(t, u + time)
            }

            edges[node] = temp

        }

        dfs(start, 0)
        return answer
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2021_4()
//            println(s.solution(3, 1, 3, arrayOf(intArrayOf(1, 2, 2), intArrayOf(3, 2, 3)), intArrayOf(2)))
            println(s.solution(4, 1, 4, arrayOf(intArrayOf(1, 2, 1), intArrayOf(3, 2, 1), intArrayOf(2, 4, 1)), intArrayOf(2, 3)))
        }
    }
}
// 걸린 시간(분):