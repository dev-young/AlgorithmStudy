package baek

//트리와 쿼리 (트리에서의 동적 계획법 (Tree DP))
class Baek15681 {
    fun main() {

        class Node(val v: Int, val parent: Int) {
            val child = arrayListOf<Int>()
        }

        val temp = readLine()!!.split(" ")
        val n = temp[0].toInt()
        val root = temp[1].toInt()
        val qCount = temp[2].toInt()
        val edges = arrayListOf<Pair<Int, Int>>()
        val queries = arrayListOf<Int>()
        for (i in 1 until n)
            readLine()!!.split(" ").let {
                edges.add(Pair(it[0].toInt(), it[1].toInt()))
            }

        for (i in 0 until qCount)
            queries.add(readLine()!!.toInt())

        val tree = hashMapOf<Int, Int>()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek15681().main()
        }
    }
}
// 걸린 시간(분):