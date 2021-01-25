package baek

class Baek1018 {

    fun main() {
        val board = arrayListOf<String>()
        var answer = 64
        fun check(startN : Int, startM : Int, startColor: Boolean){
            var next = startColor
            var currentCount = 0
            for (i in startN .. startN+7) {
                next = !next
                for (j in startM .. startM+7) {
                    val nextC = if(next) 'W' else 'B'
                    if(board[i][j] != nextC){
                        currentCount++
                        if(currentCount >= answer)
                            return
                    }
                    next = !next
                }
            }
            answer = currentCount
        }

        readLine()!!.split(" ").let {
            val n = it[0].toInt()
            val m = it[1].toInt()

            for (i in 1..n)
                board.add(readLine()!!)
            for (i in 0 until n-7) {
                for (j in 0 until m-7) {
                    check(i, j, true)
                    check(i, j, false)
                }
            }
        }
        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val r = Baek1018().main()
        }
    }
}
// 걸린 시간(분):