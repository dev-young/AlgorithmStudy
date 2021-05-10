package baek

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

//뱀
class Baek3190 {

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val k = readLine().toInt()
        val apples = hashSetOf<Pair<Int, Int>>()
        val directions = linkedMapOf<Int, String>()
        repeat(k) {
            readLine().split(" ").let {
                apples.add(Pair(it[0].toInt(), it[1].toInt()))
            }
        }
        repeat(readLine().toInt()) {
            readLine().split(" ").let {
                directions[it[0].toInt()] = it[1]
            }
        }
        close()

        //idx : 0123 = 상우하좌
        val di = intArrayOf(-1, 0, 1, 0)
        val dj = intArrayOf(0, 1, 0, -1)

        val snake = LinkedList<Pair<Int, Int>>()
        val snakeSet = hashSetOf<Pair<Int, Int>>()
        snake.add(Pair(1, 1))
        snakeSet.add(Pair(1, 1))
        var direction = 1
        var time = 1
        while (true) {
            val curr = snake.last()
            val next = Pair(curr.first + di[direction], curr.second + dj[direction])
            if (!(next.first in 1 .. n && next.second in 1 .. n)) break
            if (snakeSet.contains(next)) break
            if(apples.contains(next)) {
                apples.remove(next)
            } else {
                snakeSet.remove(snake.removeAt(0))
            }
            snake.add(next)
            snakeSet.add(next)

            directions[time]?.let {
                if (it == "D") direction = (direction+1) % 4
                else direction = (direction+3) % 4
            }
            time++
        }
        println(time)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek3190().main()
        }
    }
}
// 걸린 시간(분): 47