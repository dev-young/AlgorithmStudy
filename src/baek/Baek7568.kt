package baek


//https://www.acmicpc.net/problem/7568
class Baek7568 {

    fun main() {

        val n = readLine()!!.toInt()
        val pariList = ArrayList<Pair<Int, Int>>()
        for (i in 1..n) {
            readLine()?.split(" ")?.let {
                pariList.add(Pair(it[0].toInt(), it[1].toInt()))
            }
        }

        val answer = pariList.map { current ->
            var r = 1
            pariList.forEach {
                if (current.first < it.first && current.second < it.second) {
                    r++
                }
            }
            r
        }.joinToString(" ")
        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val r = Baek7568().main()
        }
    }
}
// 걸린 시간(분):