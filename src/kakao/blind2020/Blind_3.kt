package kakao.blind2020

import java.lang.Exception

class Blind_3 {
    fun solution(originalKey: Array<IntArray>, lock: Array<IntArray>): Boolean {
        var answer = false

        val key = Array(lock.size) { IntArray(lock.size) }
        lock.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, n ->
                try {
                    key[i][j] = originalKey[i][j]
                } catch (e:Exception) {
                    key[i][j] = 0
                }
            }
        }
        for (i in -lock.size+1 until  lock.size) {
            for (j in -lock.size+1 until  lock.size) {
                println("$i $j")
                var changedKey = move(key, i, j)
                for (r in 1..4) {
                    changedKey = rotate(changedKey)
                    if(check(changedKey, lock)) return true

                }
            }
        }

        return answer
    }


    // lock를 모두 채울 수 있는지 확인
    fun check(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        lock.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, num ->
                if(num == 0) {
                    if(key[i][j] == 0)
                        return false
                } else {
                    if(key[i][j] == 1)
                        return false
                }
            }
        }

        return true
    }

    // 90도 회전
    fun rotate(arr: Array<IntArray>): Array<IntArray> {
        val n = arr.size
        val m: Int = arr[0].size
        val rotate = Array(m) { IntArray(n) }
        for (i in rotate.indices) {
            for (j in rotate[i].indices) {
                rotate[i][j] = arr[n - 1 - j][i]
            }
        }
        return rotate
    }

    // x칸, y칸 만큼 이동 (빈칸은 0)
    fun move(arr: Array<IntArray>, x:Int, y:Int): Array<IntArray> {
        val  empty = 0  //유효하지 않은 칸 (빈칸)
        val n = arr.size
        val m: Int = arr[0].size
        val moved = Array(m) { IntArray(n) }
        for (i in moved.indices) {
            for (j in moved[i].indices) {
                val movedI = i-y
                val movedJ = j-x
                if(movedI < 0 || movedJ < 0 || movedI > moved.lastIndex || movedJ > moved[0].lastIndex)
                    moved[i][j] = empty
                else
                    moved[i][j] = arr[movedI][movedJ]
            }
        }
        return moved
    }

}

fun main() {
    val s = Blind_3()
    val r = s.solution(
        arrayOf(intArrayOf(0, 0, 0), intArrayOf(1, 0, 0), intArrayOf(0, 1, 1)), arrayOf(
            intArrayOf(
                1,
                1,
                1
            ), intArrayOf(1, 1, 0), intArrayOf(1, 0, 1)
        )
    )
    println(r)
}