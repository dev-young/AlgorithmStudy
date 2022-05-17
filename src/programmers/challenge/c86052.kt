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

        fun start(startX: Int, startY: Int, startDir: Int){
            var x = startX
            var y = startY
            var direction = startDir
            var cnt = 0
            var currentPathSet = hashSetOf<Triple<Int, Int, Int>>()

            while (true) {
                val currentPath = Triple(x, y, direction)
                if (cnt > 0) {
                    if (x == startX && y == startY && startDir == direction) {
                        //새로운 싸이클 발견
                        cyclePathSet.addAll(currentPathSet)
                        cyclePathSet.add(currentPath)
                        resultList.add(cnt)
                        break
                    }
                }

                if (currentPathSet.contains(currentPath))
                    break

                currentPathSet.add(currentPath)

                if (cyclePathSet.contains(currentPath))
                    break

                x += delta[direction][0]
                y += delta[direction][1]
                if (x < 0) x = xSize - 1
                else if (x == xSize) x = 0
                if (y < 0) y = ySize - 1
                else if (y == ySize) y = 0
                direction = when (grid[y][x]) {
                    'R' -> {
                        (direction + 1) % 4
                    }
                    'L' -> {
                        (direction + 3) % 4
                    }
                    else -> direction
                }
                cnt++
            }
        }

        for (x in 0 until xSize) {
            for (y in 0 until ySize) {
                for (d in 0 .. 3) {
                    start(x, y, d)
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
// 걸린 시간(분): 71분
