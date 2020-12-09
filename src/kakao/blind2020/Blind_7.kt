package kakao.blind2020

import java.util.*
import kotlin.collections.HashSet


//https://programmers.co.kr/learn/courses/30/lessons/60063?language=kotlin
class Blind_7 {
    data class Point(val row: Int, val col: Int)
    data class Robot(var p1: Point, var p2: Point) {
        init { sort() }

        fun sort() {
            if (p1.row > p2.row || p1.col > p2.col) {
                var temp = p1
                p1 = p2
                p2 = temp
            }
        }

        fun isHorizontal() = p1.row == p2.row

        fun getHLeft() = Robot(Point(p1.row, p1.col - 1), p1)
        fun getHRight() = Robot(p2, Point(p2.row, p2.col + 1))
        fun getHTop() = arrayListOf(
            Robot(Point(p1.row - 1, p1.col), Point(p2.row - 1, p2.col)),
            Robot(Point(p1.row - 1, p1.col), p1),
            Robot(Point(p2.row - 1, p2.col), p2))

        fun getHBottom() = arrayListOf(
            Robot(Point(p1.row + 1, p1.col), Point(p2.row + 1, p2.col)),
            Robot(Point(p1.row + 1, p1.col), p1),
            Robot(Point(p2.row + 1, p2.col), p2))

        fun getVLeft() = arrayListOf(
            Robot(Point(p1.row, p1.col - 1), Point(p2.row, p2.col - 1)),
            Robot(Point(p1.row, p1.col - 1), p1),
            Robot(Point(p2.row, p2.col - 1), p2))

        fun getVRight() = arrayListOf(
            Robot(Point(p1.row, p1.col + 1), Point(p2.row, p2.col + 1)),
            Robot(Point(p1.row, p1.col + 1), p1),
            Robot(Point(p2.row, p2.col + 1), p2))

        fun getVTop() = Robot(Point(p1.row - 1, p1.col), p1)
        fun getVBottom() = Robot(Point(p2.row + 1, p1.col), p2)
    }

    fun solution(board: Array<IntArray>): Int {
        /**현재 로봇의 위치에서 움직일 수 있는 위치의 집합을 반환한다.*/
        fun getNextPositions(current: Robot): HashSet<Robot> {
            val result = hashSetOf<Robot>()
            val p1 = current.p1
            val p2 = current.p2
            if (current.isHorizontal()) {
                if (p1.col > 0 && board[p1.row][p1.col - 1] == 0)
                    result.add(current.getHLeft())   //왼쪽
                if (p2.col < board.size - 1 && board[p2.row][p2.col + 1] == 0)
                    result.add(current.getHRight())    //오른쪽
                if (p1.row > 0 && board[p1.row - 1][p1.col] == 0 && board[p2.row - 1][p2.col] == 0)
                    result.addAll(current.getHTop())    //위
                if (p1.row < board.size - 1 && board[p1.row + 1][p1.col] == 0 && board[p2.row + 1][p2.col] == 0)
                    result.addAll(current.getHBottom())  //아래
            } else {
                if (p1.row > 0 && board[p1.row - 1][p1.col] == 0)
                    result.add(current.getVTop())   //위
                if (p2.row < board.size - 1 && board[p2.row + 1][p2.col] == 0)
                    result.add(current.getVBottom())    //아래
                if (p1.col > 0 && board[p1.row][p1.col - 1] == 0 && board[p2.row][p2.col - 1] == 0)
                    result.addAll(current.getVLeft())    //왼쪽
                if (p1.col < board.size - 1 && board[p1.row][p1.col + 1] == 0 && board[p2.row][p2.col + 1] == 0)
                    result.addAll(current.getVRight())  //오른쪽
            }
            return result
        }

        var answer = 0
        val visited = hashSetOf<Robot>()
        val que: Queue<Robot> = LinkedList()
        que.offer(Robot(Point(0,0), Point(0,1)))

        while (que.isNotEmpty()) {
            val qSize = que.size
            for (n in 1..qSize) {
                val i = que.poll()
                if(i.p2.row == board.size-1 && i.p2.col == board[0].size-1) return answer
                if (visited.contains(i)) continue
                visited.add(i)
                for (j in getNextPositions(i)) {
                    que.offer(j)
                }
            }
            answer++
        }

        return answer
    }

    /**테케 10번 틀린 풀이*/
    fun solution_fail(board: Array<IntArray>): Int {
        val inf = Int.MAX_VALUE
        val nodeRobotMap = hashMapOf<Point, HashSet<Point>>()
        val minPrice = linkedMapOf<Point, Int>().apply {
            board.forEachIndexed { row, ints ->
                ints.forEachIndexed { col, int ->
                    if (int == 0) {
                        Point(row, col).let {
                            put(it, inf)
                            nodeRobotMap[it] = hashSetOf()
                        }
                    }
                }
            }
        }
        val que: Queue<Pair<Point, Int>> = LinkedList()
        val robot = Robot(Point(0, 0), Point(0, 1))
        que.add(Pair(robot.p1, 0))
        nodeRobotMap[robot.p1]?.add(robot.p2)

        /**destination 으로 갈 수 있을 경우 도착시 위치할 수 있는 노드위 위치집합을 반환한다. (destination은 제외하고 반환)*/
        fun isRobotEnable(robot: Robot, destination: Point): HashSet<Point> {
            val result = hashSetOf<Point>()
            if (board[destination.row][destination.col] == 1) return result

            robot.sort()
            val n1 = robot.p1
            val n2 = robot.p2
            if (robot.isHorizontal()) {
                if (n2.row == destination.row) {
                    if (n2.col < destination.col) result.add(n2)
                    else result.add(n1)
                } else {
                    val desRow = destination.row
                    if (board[desRow][n1.col] == board[desRow][n2.col]) {
                        if (n1.col == destination.col) {
                            result.add(n1)
                            result.add(Point(desRow, n2.col))
                        } else {
                            result.add(n2)
                            result.add(Point(desRow, n1.col))
                        }
                    }
                }
            } else {
                if (n2.col == destination.col) {
                    if (n2.row < destination.row) result.add(n2)
                    else result.add(n1)
                } else {
                    val desCol = destination.col
                    if (board[n1.row][desCol] == board[n2.row][desCol]) {
                        if (n1.row == destination.row) {
                            result.add(n1)
                            result.add(Point(n2.row, desCol))
                        } else {
                            result.add(n2)
                            result.add(Point(n1.row, desCol))
                        }
                    }
                }
            }

            return result
        }


        while (que.isNotEmpty()) {
            val current = que.poll()
            val destinations = LinkedHashSet<Point>()
            val nextDistance = current.second + 1
            nodeRobotMap[current.first]?.forEach {
                val currentRobot = Robot(current.first, it).apply { sort() }
                destinations.clear()
                destinations.add(Point(currentRobot.p1.row - 1, currentRobot.p1.col))
                destinations.add(Point(currentRobot.p1.row + 1, currentRobot.p1.col))
                destinations.add(Point(currentRobot.p1.row, currentRobot.p1.col - 1))
                destinations.add(Point(currentRobot.p1.row, currentRobot.p1.col + 1))
                destinations.add(Point(currentRobot.p2.row - 1, currentRobot.p2.col))
                destinations.add(Point(currentRobot.p2.row + 1, currentRobot.p2.col))
                destinations.add(Point(currentRobot.p2.row, currentRobot.p2.col - 1))
                destinations.add(Point(currentRobot.p2.row, currentRobot.p2.col + 1))
                destinations.remove(currentRobot.p1)
                destinations.remove(currentRobot.p2)
                destinations.forEach {
                    if (it.row >= 0 && it.row < board.size && it.col >= 0 && it.col < board[0].size) {
                        isRobotEnable(currentRobot, it).forEach { other ->
                            if (nextDistance < minPrice[it]!!) {
                                minPrice[it] = nextDistance
                                que.add(Pair(it, nextDistance))
                                nodeRobotMap[it]!!.clear()
                                nodeRobotMap[it]!!.add(other)
                            } else if (nextDistance == minPrice[it]!!) {
                                nodeRobotMap[it]!!.add(other)
                            }
                        }
                    }
                }

            }
        }


        return minPrice[Point(board.size - 1, board[0].size - 1)]!!
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Blind_7()
//            println(s.solution(arrayOf(
//                intArrayOf(0, 0, 0, 1, 1),
//                intArrayOf(0, 0, 0, 1, 0),
//                intArrayOf(0, 1, 0, 1, 1),
//                intArrayOf(1, 1, 0, 0, 1),
//                intArrayOf(0, 0, 0, 0, 0)
//            )))
            println(s.solution(arrayOf(
                intArrayOf(0, 0, 0, 0, 0),
                intArrayOf(1, 0, 1, 0, 0),
                intArrayOf(0, 1, 1, 0, 0),
                intArrayOf(0, 0, 1, 0, 0),
                intArrayOf(0, 0, 1, 0, 0)
            )))
        }
    }
}