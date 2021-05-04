package kakao.blind2019

//https://programmers.co.kr/learn/courses/30/lessons/42894?language=kotlin
class KakaoB19_7 {

    class Block(val n: Int) {
        val points = hashSetOf<Pair<Int, Int>>()
        private var bottom: Pair<Int, Int>? = null
        private var top: Pair<Int, Int>? = null
        private var isRemovable: Boolean? = null
        fun isRemovable(): Boolean {
            if (isRemovable == null) {
                isRemovable = true
                for (p in points) {
                    if (p.first < getBottom()) {
                        if (!points.contains(Pair(p.first + 1, p.second))) {
                            isRemovable = false
                            break
                        }
                    }
                }
            }
            return isRemovable!!
        }

        fun isRemovable(board: Array<IntArray>): Boolean {
            if (isRemovable()) {
                for (pair in points) {
                    if (pair.second == top!!.second) continue
                    for (i in 0 until pair.first) {
                        if (board[i][pair.second] == 0 || board[i][pair.second] == n) continue
                        else {
                            return false
                        }
                    }
                }
            }
            return isRemovable()
        }

        fun getBottom(): Int {
            if (bottom == null)
                cal()
            return bottom!!.first
        }

        fun getTop(): Int {
            if (top == null)
                cal()
            return top!!.first
        }

        private fun cal() {
            points.forEach {
                if (top?.first ?: 50 > it.first)
                    top = it
                if (bottom?.first ?: 0 < it.first)
                    bottom = it
            }
        }
    }

    fun solution(board: Array<IntArray>): Int {
        var answer = 0
        val map = hashMapOf<Int, Block>()
        val set = linkedSetOf<Int>()
        for (i in board.indices) {
            for (j in board[0].indices) {
                if (board[i][j] == 0) continue
                set.add(board[i][j])
                map.computeIfAbsent(board[i][j]) { Block(board[i][j]) }.points.add(Pair(i, j))
            }
        }

        fun removeFromBoard(n: Int) {
            map[n]?.let {
                it.points.forEach {
                    board[it.first][it.second] = 0
                }
            }
        }

        var removedCount = 1
        while (removedCount > 0) {
            val removed = arrayListOf<Int>()
            map.forEach { t, u ->
                if (u.isRemovable(board)) {
                    removed.add(t)
                }
            }
            removedCount = removed.size
            removed.forEach {
                removeFromBoard(it)
                map.remove(it)
            }
            answer += removedCount
        }


        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = KakaoB19_7()
            val r = s.solution(arrayOf(
                    intArrayOf(0, 6, 0, 0, 0, 0, 0, 0, 0, 3),
                    intArrayOf(6, 6, 0, 2, 2, 0, 0, 3, 3, 3),
                    intArrayOf(0, 6, 7, 2, 1, 0, 5, 4, 4, 4),
                    intArrayOf(0, 0, 7, 2, 1, 0, 5, 5, 4, 0),
                    intArrayOf(0, 7, 7, 0, 1, 1, 5, 0, 0, 0)))
            println(r)
        }
    }
}
// 걸린 시간: 92분