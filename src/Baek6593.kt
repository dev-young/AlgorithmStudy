import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

//상범 빌딩
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    while(true){
        val lrc = readLine().split(' ')
        var level: Int
        var row: Int
        var col: Int
        var start : Node? = null
        var end : Node? = null
        level = lrc[0].toInt() //level
        row = lrc[1].toInt() //row
        col = lrc[2].toInt() //col
        if(level == 0 && row == 0 && col == 0) break
        val totalArray =
            Array(level){
                Array(row){
                    CharArray(col)
                }
            }
        for(l in 0 until level){
            for(r in 0 until row){
                val temp = readLine()
                for(c in 0 until col){
                    totalArray[l][r][c] = temp[c]
                    if(temp[c] == 'S')
                        start = Node(l, r, c)

                    if(temp[c] == 'E')
                        end = Node(l, r, c)

                }
            }

            readLine()  //층 나누기
        }


        var resultMessage = "Trapped!"
        if(start != null && end != null){
            val time = findShortestTime(start, end, totalArray)
            if(time > 0)
                resultMessage = "Escaped in $time minute(s)."
        }
        println(resultMessage)
    }
}

fun findShortestTime(start: Node, end: Node, totalArray: Array<Array<CharArray>>): Int {
//    println(totalArray.contentDeepToString())
    val queue : Queue<Node> = LinkedList()
    queue.add(start)

    val totalL = totalArray.size
    val totalR = totalArray[0].size
    val totalC = totalArray[0][0].size

    val distance =
        Array(totalL){
            Array(totalR){
                IntArray(totalC)
            }
        }

    val moveL = intArrayOf(1, -1, 0, 0, 0, 0)
    val moveR = intArrayOf(0, 0, 1, -1, 0, 0)
    val moveC = intArrayOf(0, 0, 0, 0, 1, -1)

    while (!queue.isEmpty()){
        val now = queue.poll()

        for(i in 0 until  6){
            val nL = now.lev + moveL[i]
            val nR = now.row + moveR[i]
            val nC = now.col + moveC[i]

            if (nL < 0 || nL >= totalL || nR < 0 || nR >= totalR || nC < 0 || nC >= totalC) continue
            if(totalArray[nL][nR][nC] == '#') continue

            if(distance[nL][nR][nC] == 0){
                distance[nL][nR][nC] = distance[now.lev][now.row][now.col] + 1
                queue.add(Node(nL, nR, nC))
            }
        }

    }

    return distance[end.lev][end.row][end.col]

}

class Node (val lev:Int,val  row:Int,val  col:Int){
    override fun toString(): String {
        return "lev=$lev, row=$row, col=$col"
    }
}