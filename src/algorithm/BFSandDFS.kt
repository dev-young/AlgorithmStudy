package algorithm

import java.util.*


/**간선의 수가 적은 희소그래프일 경우 인접행렬보다 인접리스트를 사용하는것이 유리 */
class BFSandDFS {

    private fun bfs(arr: Array<IntArray>, root: Int = 0) {
        val visited = BooleanArray(arr.size) { false }
        val que: Queue<Int> = LinkedList()
        que.offer(root)
        while (!que.isEmpty()) {
            val i = que.poll()
            if(visited[i]) continue
            visited[i] = true
            println("방문 노드:$i")
//            for (j in i until arr.size) //방향성이 없는 경우 // TODO: 테스트 필요
            for (j in arr.indices)
            {
                if (arr[i][j] == 1 &&   /*간선이 존재하는경우*/
                    i != j &&           /*자기 자신이 아닌 경우*/
                    !visited[j]         /*방문한적이 없는 경우*/
                ) {
                    que.offer(j)
                }
            }
        }
    }

    private fun bfsTest() {
        val arr = arrayOf(
            intArrayOf(1, 1, 0, 1, 1),
            intArrayOf(0, 1, 1, 0, 1),
            intArrayOf(0, 0, 1, 0, 1),
            intArrayOf(0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 0, 1))

        bfs(arr)

    }

    private fun dfs(arr: Array<IntArray>, visited: BooleanArray, i: Int) {
        visited[i] = true
        println("탐색된 노드:$i")
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

    private fun dfsTest() {
        val arr = arrayOf(
            intArrayOf(1, 1, 0, 1, 1),
            intArrayOf(0, 1, 1, 0, 1),
            intArrayOf(0, 0, 1, 0, 1),
            intArrayOf(0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 0, 1))

        val visited = BooleanArray(arr.size) { false }

        dfs(arr, visited, 0)
    }


    // TODO: 인접리스트를 사용할 경우


    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val test = BFSandDFS()
            test.bfsTest()

        }
    }
}