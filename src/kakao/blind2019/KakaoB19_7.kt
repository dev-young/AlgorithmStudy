package kakao.blind2019

import kotlin.math.min

//https://programmers.co.kr/learn/courses/30/lessons/42894?language=kotlin
class KakaoB19_7 {

    class Block(val id: Int) {
        val b = mutableListOf<IntArray>()
        private val top = hashMapOf<Int, Int>() //<col, row>    구성요소중 가장 최상위에 있는 블럭들
        fun add(row: Int, col: Int) {
            b.add(intArrayOf(row, col))
            if (top[col] == null || top[col]!! > row) {
                top[col] = row
            }
        }

        private var removable: Boolean? = null
        fun isRemovable(): Boolean {
            if (removable != null) return removable!!
            //top에 있는 블럭들중 가장 낮은 녀석보다 낮은 블럭이 있으면 제거할 수 없다.
            val minInTop = top.values.max()!! //좌상단이 0,0 이라 클수록 낮은곳이다.
            top.minBy { it.value }?.let { top.remove(it.key) } /**제일 위에있는 top은 제거를 해줘야 isOnTop()에서 제대로 판단한다. (이놈때문에 오지게 삽질함.) */
            for (i in b.size - 1 downTo 0) {
                if (b[i][0] > minInTop) {
                    removable = false
                    return false
                }
            }
            removable = true
            return true
        }

        fun isOnTop(board: Array<IntArray>): Boolean {
            /** 단순히 블럭이 제일 위에 있어야 하는게 아니라 채워야할 부분 위에 다른 블럭이 있는지를 판단해야한다. (채울 필요가 없는 부분 위에는 다른 블럭이 있어도 상관 없다.)*/
            for ((tCol, tRow) in top) {
                for (row in 0 until tRow) {
                    if (board[row][tCol] != 0)
                        return false
                }
            }
            return true
        }

        fun removeInBoard(board: Array<IntArray>) {
            b.forEach {
                board[it[0]][it[1]] = 0
            }
        }
    }

    fun solution(board: Array<IntArray>): Int {
        var answer = 0

        val blocks = linkedMapOf<Int, Block>()

        board.forEachIndexed { row, ints ->
            ints.forEachIndexed { col, i ->
                if (i != 0)
                    blocks.computeIfAbsent(i) { Block(i) }.add(row, col)
            }
        }
        blocks.values.filter { !it.isRemovable() }.forEach { blocks.remove(it.id) }

        while (true) {
            val removeTargets = blocks.values.filter { it.isOnTop(board) }
            if (removeTargets.isEmpty())
                break
            removeTargets.forEach {
                it.removeInBoard(board)
                blocks.remove(it.id)
            }
            answer += removeTargets.size
        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = KakaoB19_7()
            val r = s.solution(arrayOf(
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 2, 2, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 2, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 2, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 1, 0, 0, 0, 0)))
            println(r)
        }
    }
}
// 걸린 시간: 140분