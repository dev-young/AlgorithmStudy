package programmers.skillcheck

class Level3_2 {

    fun solution(tickets: Array<Array<String>>): Array<String> {
        fun isLinked(fromIdx:Int, toIdx:Int): Boolean {
            return (tickets[fromIdx][1] == tickets[toIdx][0]).let {
                it
            }
        }
        val answers = arrayListOf<Array<String>>()
        val path = arrayListOf<String>("ICN")
        val visited = BooleanArray(tickets.size) {false}
        fun dfs(i:Int){
            visited[i] = true
            path.add(tickets[i][1])
            if(path.size == tickets.size+1){
                answers.add(path.toTypedArray())
            } else {
                tickets.forEachIndexed { j, strings ->
                    if(i != j
                        && !visited[j]
                        && isLinked(i, j))
                        dfs(j)
                }
            }
            visited[i] = false
            path.removeAt(path.lastIndex)
        }
        tickets.forEachIndexed { index, strings ->
            if(strings[0] == "ICN")
                dfs(index)
        }
        return answers.sortedBy { it.joinToString("") }[0]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Level3_2()
//            val strs = arrayOf(
//                arrayOf("ICN", "S"),
//                arrayOf("ICN", "A"),
//                arrayOf("S", "A"),
//                arrayOf("A", "ICN"),
//                arrayOf("A", "S")
//
//            )
//
            val strs = arrayOf(
                arrayOf("ICN", "A"),
                arrayOf("A", "C"),
                arrayOf("B", "A"),
                arrayOf("A", "D"),
                arrayOf("D", "B"))
            val r = s.solution(strs)
//            val r = Solution().solution(strs)
            println(r.contentDeepToString())

        }
    }

}