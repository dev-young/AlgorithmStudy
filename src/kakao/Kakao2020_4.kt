package kakao

class Kakao2020_4 {
    fun solution(board: Array<IntArray>): Int {
        var answer = findShortestPath(
            Node(0, 0),
            Node(board.size - 1, board.size - 1), board)

        return answer
    }



    fun findShortestPath(start: Node, end: Node, totalArray: Array<IntArray>): Int {
//    println(totalArray.contentDeepToString())
        val queue = arrayListOf<Node>()
        queue.add(start)

        val totalR = totalArray.size
        val totalC = totalArray[0].size

        val distance =
            Array(totalR){
                IntArray(totalC)
            }

        val moveR = intArrayOf(1, -1, 0, 0)
        val moveC = intArrayOf(0, 0, 1, -1)

        while (!queue.isEmpty()){
            val now = queue.removeAt(0)

            for(i in 0 until  4){
                val nR = now.row + moveR[i]
                val nC = now.col + moveC[i]

                if (nR < 0 || nR >= totalR || nC < 0 || nC >= totalC) continue
                if(totalArray[nR][nC] == 1) continue

                if(distance[nR][nC] == 0){
                    //todo 아.. 여기서 뭘 해야할까..
                    distance[nR][nC] = distance[now.row][now.col] + 100
                    queue.add(Node(nR, nC))
                }
            }

        }

        return distance[end.row][end.col]

    }

    class Node (val row:Int, val col:Int){
    }
}

fun main() {
    val s = Kakao2020_4()
    val p = arrayOf<IntArray>(intArrayOf(0,0,0), intArrayOf(0,0,0), intArrayOf(0,0,0))
    var r = s.solution(p)
    println(r)
}