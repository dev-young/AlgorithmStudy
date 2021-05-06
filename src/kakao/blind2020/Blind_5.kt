package kakao.blind2020

//https://programmers.co.kr/learn/courses/30/lessons/60061
class Blind_5 {
    fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
        var answer = arrayListOf<IntArray>()
        val board = Array(n+1) { IntArray(n+1) { -1 } }

        fun check(x: Int, y: Int, s: Int): Boolean {
            if (s == 0) {   //기둥
                if (
                        y == 0 || /**바닥 위*/
                        (board[x][y - 1] == 0 || board[x][y - 1] == 2) ||   /**기둥 위*/
                        (x > 0 && board[x - 1][y] > 0) ||  /**보자기 왼쪽 위*/
                        (board[x][y] > 0) /**보자기 오른쪽 위*/
                ) {
                    return true
                }
            } else {    //보
                if(
                        (board[x][y - 1] == 0 || board[x][y - 1] == 2) ||   /**기둥 위*/
                        (x < board.size-1 && (board[x+1][y - 1] == 0 || board[x+1][y - 1] == 2)) ||   /**기둥 위*/
                        (x in 1 until board.size-1 && board[x+1][y] > 0 && board[x-1][y] > 0)  /**보자기 왼쪽 위*/
                ) {
                    return true
                }
            }
            return false
        }

        fun add(x: Int, y: Int, s: Int) {
            answer.add(intArrayOf(x, y, s))
            if (board[x][y] != -1) {
                board[x][y] = 2
            } else board[x][y] = s
        }

        fun remove(x: Int, y: Int, s: Int) {
            for (i in answer.indices) {
                val arr = answer[i]
                if (arr[0] == x && arr[1] == y && (arr[2] == s)) {
                    answer.removeAt(i)
                    break
                }
            }
            if (board[x][y] == 2) {
                if (s == 1) board[x][y] = 0
                else board[x][y] = 1
            } else board[x][y] = -1
        }

        build_frame.forEach {
            val x = it[0]
            val y = it[1]
            val s = it[2]
            val build = it[3] == 1

            if (build) {
                if(check(x,y,s)) add(x,y,s)
            } else {
                remove(x,y,s)
                for (a in answer) {
                    if(!check(a[0], a[1], a[2])) {
                        add(x,y,s)
                        break
                    }
                }
            }
        }

        return answer.sortedWith(compareBy({it[0]}, {it[1]}, {it[2]})).toTypedArray()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Blind_5()
            val ints = arrayOf(
                    intArrayOf(0, 0, 0, 1),
                    intArrayOf(2, 0, 0, 1),
                    intArrayOf(4, 0, 0, 1),
                    intArrayOf(0, 1, 1, 1),
                    intArrayOf(1, 1, 1, 1),
                    intArrayOf(2, 1, 1, 1),
                    intArrayOf(3, 1, 1, 1),
                    intArrayOf(2, 0, 0, 0),
                    intArrayOf(1, 1, 1, 0),
                    intArrayOf(2, 2, 0, 1)
            )
//    println(s.solution(
//        5, arrayOf(
//            intArrayOf(1, 0, 0, 1),
//            intArrayOf(1, 1, 1, 1),
//            intArrayOf(2, 1, 0, 1),
//            intArrayOf(2, 2, 1, 1),
//            intArrayOf(5, 0, 0, 1),
//            intArrayOf(5, 1, 0, 1),
//            intArrayOf(4, 2, 1, 1),
//            intArrayOf(3, 2, 1, 1)
//        )
//    ).contentDeepToString())

            println(s.solution(5, ints).contentDeepToString())
        }
    }
}
// 걸린 시간(분): 95