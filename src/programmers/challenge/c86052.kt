package programmers.challenge

import kotlin.concurrent.fixedRateTimer


//https://programmers.co.kr/learn/courses/30/lessons/86052
//level2
class c86052 {
    private val delta = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))   //상우하좌
    fun solution(grid: Array<String>): IntArray {
        val xSize = grid[0].length
        val ySize = grid.size
        val resultList = arrayListOf<Int>()
        val cyclePathSet = hashSetOf<Triple<Int, Int, Int>>()
        var startX = 0
        var startY = 0
        var startDir = 0

        var currentPathSet = hashSetOf<Triple<Int, Int, Int>>()

        fun dfs(x: Int, y: Int, direction: Int, cnt: Int = 0) {
            val currentPath = Triple(x, y, direction)

            if (cnt > 0) {
                if (x == startX && y == startY && startDir == direction) {
                    //새로운 싸이클 발견
                    cyclePathSet.addAll(currentPathSet)
                    cyclePathSet.add(currentPath)
                    resultList.add(cnt)
                    return
                }
            }

            if (currentPathSet.contains(currentPath))
                return

            currentPathSet.add(currentPath)

            if (cyclePathSet.contains(currentPath))
                return

            var nx = x + delta[direction][0]
            var ny = y + delta[direction][1]
            if (nx < 0) nx = xSize - 1
            else if (nx == xSize) nx = 0
            if (ny < 0) ny = ySize - 1
            else if (ny == ySize) ny = 0
            val nd = when (grid[ny][nx]) {
                'R' -> {
                    (direction + 1) % 4
                }
                'L' -> {
                    (direction + 3) % 4
                }
                else -> {
                    direction
                }
            }
            dfs(nx, ny, nd, cnt + 1)
        }

        for (x in 0 until xSize) {
            for (y in 0 until ySize) {
                for (d in 0 .. 3) {
                    currentPathSet.clear()
                    startX = x
                    startY = y
                    startDir = d
                    dfs(x, y, d)
                }
            }
        }

        return resultList.sorted().toIntArray()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c86052()
            println(s.solution(arrayOf("SL","LR")).contentToString())
            println(s.solution(arrayOf("S")).contentToString())
            println(s.solution(arrayOf("R","R")).contentToString())
        }
    }
}
// 걸린 시간(분): 58분 (테케7:런타임, 테케8:시간초과)
