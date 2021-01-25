package baek

//N-Queen (백트래킹)
class Baek9663 {

    fun main() {
        val n = readLine()!!.toInt()
        var answer = 0
        val row = IntArray(n) { -1 }
        fun rec(x: Int, y: Int): Int {
            if (row[y] != -1)    //가로 확인
                return 0
            for (i in 0 until y) {   //세로 및 대각선 확인
                if (row[i] == x || Math.abs(x - row[i]) == Math.abs(y - i))
                    return 0
            }
            if (y == n - 1) return 1

            var r = 0
            for (i in 0 until n) {
                row[y] = x
                r += rec(i, y + 1)
                row[y] = -1
            }

            return r
        }

        for (i in 0 until n) {
            answer += rec(i, 0)
        }

        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val r = Baek9663().main()
        }
    }
}
// 걸린 시간(분):