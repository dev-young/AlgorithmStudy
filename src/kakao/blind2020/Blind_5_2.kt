package kakao.blind2020

import java.util.*
import kotlin.collections.ArrayList

//다른놈이 푼거
class Blind_5_2 {
    val _X = 0   //X 좌표
    val _Y = 1   //Y 좌표
    val FRAME = 2   //구조물
    val METHOD = 3   //동작

    val DELETE = 0  //제거
    val ADD = 1     //추가

    val PILLAR = 0 //기둥
    val ROOF = 1    // 보

    val GRID_NONE = 0b00    // 빈공간
    val GRID_PILLAR = 0b01 //기둥
    val GRID_ROOF = 0b10    // 보

    fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
        var size = n + 1
        var board = Array<IntArray>(size) { IntArray(size) { GRID_NONE } }

        var count = 0
        root@ build_frame.forEach { build ->
            when (build[METHOD]) {
                ADD -> {
                    var buildingNow = if(build[FRAME] == PILLAR) GRID_PILLAR else GRID_ROOF
                    if(canBuild(board, build[_X], build[_Y], buildingNow)) {
                        board[build[_Y]][build[_X]] = board[build[_Y]][build[_X]] or buildingNow
                        count++
                        println("ADD success : " + Arrays.toString(build))
                    } else {
                        //실패!
                        println("ADD fail : " + Arrays.toString(build))
                    }
                }
                DELETE -> {
                    var rolback: Int = board[build[_Y]][build[_X]]
                    var isRolback = false
                    val checks = ArrayList<IntArray>()
                    if (build[FRAME] == PILLAR) {
                        board[build[_Y]][build[_X]] = board[build[_Y]][build[_X]] and GRID_PILLAR.inv() //선 삭제
                        if(build[_Y] + 2 < size) {
                            checks.add(intArrayOf(build[_X], build[_Y] + 1, board[build[_Y] + 1][build[_X]]))
                            if(build[_X] - 1 >= 0) {
                                checks.add(intArrayOf(build[_X] - 1, build[_Y] + 1, board[build[_Y] + 1][build[_X] - 1]))
                            }
                        }
                    } else {
                        board[build[_Y]][build[_X]] = board[build[_Y]][build[_X]] and GRID_ROOF.inv() //선 삭제
                        checks.add(intArrayOf(build[_X], build[_Y], board[build[_Y]][build[_X]]))
                        if(build[_X] - 1 >= 0) {
                            checks.add(intArrayOf(build[_X] - 1, build[_Y], board[build[_Y]][build[_X] - 1]))
                        }
                        if(build[_X] + 2 < size) {
                            checks.add(intArrayOf(build[_X] + 1, build[_Y], board[build[_Y]][build[_X] + 1]))
                        }
                    }

                    checks.forEach {check-> //문제점 체크
                        if(!canBuild(board, check[_X], check[_Y], check[FRAME])) {
                            isRolback = true
                            return@forEach
                        }
                    }

                    if(isRolback) {   //후 롤백
                        board[build[_Y]][build[_X]] = rolback
                        println("DELETE fail : " + Arrays.toString(build) + " rolback " + rolback)
                    } else {
                        count--
                        println("DELETE success : " + Arrays.toString(build))
                    }
                }
            }
        }
        board.forEach {
            println(Arrays.toString(it))
        }
        println()

        var results = Array<IntArray>(count) { IntArray(3) }
        var curCnt = 0

        for (x in 0 until size) {
            for (y in 0 until size) {
                if (board[y][x] and GRID_PILLAR > 0) {
                    results[curCnt][_X] = x
                    results[curCnt][_Y] = y
                    results[curCnt][FRAME] = PILLAR
                    curCnt++
                }
                if (board[y][x] and GRID_ROOF > 0) {
                    results[curCnt][_X] = x
                    results[curCnt][_Y] = y
                    results[curCnt][FRAME] = ROOF
                    curCnt++
                }
            }
        }

        return results
    }

    fun canBuild(board: Array<IntArray>, x:Int, y:Int, gridFrame:Int): Boolean {
        var curGrid = GRID_NONE
        if(gridFrame and GRID_PILLAR > 0) {
            //기둥 설치 가능 case
            //ALL. Y < size - 1
            //1.바닥 인경우
            //2.기둥 위인 경우
            //3.지붕 오른쪽 인경우
            //4.지붕 위 인경우
            if (y < board.size - 1
                && (y == 0
                        || y >= 1 && board[y - 1][x] and GRID_PILLAR > 0
                        || x >= 1 && board[y][x - 1] and GRID_ROOF > 0
                        || board[y][x] and GRID_ROOF > 0)) {
                curGrid = curGrid or GRID_PILLAR
            }
        }
        if(gridFrame and GRID_ROOF > 0) {
            //지붕 설치 가능 case
            //ALL. Y > 0 && X < size - 1
            //1.밑에 기둥이 있는 경우
            //2.우측 밑에 기둥이 있는 경우
            //3.우측과 좌측에 지붕이 있는 경우
            if (y > 0 && x < board.size - 1
                && (board[y - 1][x] and GRID_PILLAR > 0
                        || board[y - 1][x + 1] and GRID_PILLAR > 0
                        || x >= 1 && board[y][x - 1] and GRID_ROOF > 0 && board[y][x + 1] and GRID_ROOF > 0)) {
                curGrid = curGrid or GRID_ROOF
            }
        }
        return curGrid == gridFrame
    }

}

fun main() {
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