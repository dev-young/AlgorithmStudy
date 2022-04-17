package bucketplace

class Solution3 {
    fun solution(tstring: String, variables: Array<Array<String>>): String {
        val set = hashSetOf<String>()
        variables.forEach {
            set.add("{${it[0]}}")
            set.add(it[1])
        }
        val nodeIdx = hashMapOf<String, Int>()
        val nodeString = hashMapOf<Int, String>()
        val nodes = set.toList()
        nodes.forEachIndexed { index, s ->
            nodeIdx[s] = index
            nodeString[index] = s
        }
        val parents = IntArray(nodes.size) { it }

        val cycleNodes = hashSetOf<Int>()   //싸이클이 존재하는 노드들

        variables.forEach {
            val child = nodeIdx["{${it[0]}}"]!!
            val parent = nodeIdx[it[1]]!!
            parents[child] = parent
        }

        fun isParent(node:Int) = parents[node] == node

        nodes.forEachIndexed { index, node ->
            val visited = hashSetOf<Int>()
            visited.add(index)
            var p = parents[index]
            while (p != parents[p]) {
                parents[index] = parents[p]
                if (visited.contains(p)) {
                    cycleNodes.addAll(visited)
                    break
                }
                if (!isParent(p))
                    visited.add(p)
                p = parents[p]
            }
            if (visited.contains(p)) {
                cycleNodes.addAll(visited)
            }
        }

        var answer = tstring
        nodes.forEach {
            val n = nodeIdx[it]!!
            if (it.startsWith("{") && !cycleNodes.contains(n)) {
                val parent = nodeString[parents[n]]
                answer = answer.replace(it, parent!!)

            }
        }

        return answer
    }



    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution3()
            val r = s.solution("this is {template} {template} is {state}",
                    arrayOf(arrayOf("template", "{state}"), arrayOf("state", "{template}"))
            )

//            val r = s.solution("{a} {b} {c} {d} {i}",
//                    arrayOf(arrayOf("b", "{c}"), arrayOf()
//                            , arrayOf()
//                    )
//            )
            println(r)
        }
    }
}
// 걸린 시간(분): 풀이 한번 보고 품