package kakao.blind2020

//https://programmers.co.kr/learn/courses/30/lessons/60059
class Blind_3 {

    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val oneSet = hashSetOf<Pair<Int, Int>>()
        fun rotate() {
            oneSet.clear()
            var temp = 0
            val size = key.size
            for (i in 0 until size / 2) {
                for (j in i until size - i - 1) {
                    temp = key[size - j - 1][i]
                    key[size - j - 1][i] = key[size - i - 1][size - j - 1]
                    if (key[size - j - 1][i] == 1) oneSet.add(Pair(size - j - 1, i))
                    key[size - i - 1][size - j - 1] = key[j][size - i - 1]
                    if (key[size - i - 1][size - j - 1] == 1) oneSet.add(Pair(size - i - 1, size - j - 1))
                    key[j][size - i - 1] = key[i][j]
                    if (key[j][size - i - 1] == 1) oneSet.add(Pair(j, size - i - 1))
                    key[i][j] = temp
                    if (key[i][j] == 1) oneSet.add(Pair(i, j))
                }
            }
            if (size % 2 == 1) {
                if(key[size/2][size/2] == 1) oneSet.add(Pair(size/2, size/2))
            }
        }

        val lockRange = key.size - 1 until key.size + lock.size - 1
        val s = lock.size + key.size + key.size - 2
        var zeroCount = 0
        val board = Array(s) { i ->
            IntArray(s) { j ->
                if (i in lockRange && j in lockRange) {
                    lock[i - key.size + 1][j - key.size + 1].let {
                        if (it == 0) zeroCount++
                        it
                    }
                } else -1
            }
        }

        fun check(): Boolean {
            for (i in 0..board.size - key.size) {
                for (j in 0..board.size - key.size) {
                    var count = 0   //홈을 채운 갯수
                    for (pair in oneSet) {
                        val ki = pair.first + i
                        val kj = pair.second + j
                        if (board[ki][kj] == 1)
                            break
                        if (board[ki][kj] == 0) {
                            count++
                        }
                    }
                    if (count == zeroCount)
                        return true
                }
            }
            return false
        }
        for (i in 1..4) {
            rotate()
            if (check()) return true
        }

        return false
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Blind_3()
            val r = s.solution(
                    arrayOf(intArrayOf(0, 0, 0), intArrayOf(1, 1, 0), intArrayOf(0, 1, 1)),
                    arrayOf(intArrayOf(1, 1, 1,1), intArrayOf(1, 1, 1,0), intArrayOf(1, 1, 1,0), intArrayOf(1, 1, 1,1))
            )
//            val r = s.solution(
//                    arrayOf(intArrayOf(0, 0, 0), intArrayOf(1, 0, 0), intArrayOf(0, 1, 1)), arrayOf(
//                    intArrayOf(1, 1, 1), intArrayOf(1, 1, 0), intArrayOf(1, 0, 1)
//            ))
            println(r)
        }
    }
}
// 걸린 시간(분): 77