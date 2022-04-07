package kakao.intern2021

class Kakao2021_3 {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        var cursor = k
        var totalSize = n
        val deleteLog = ArrayDeque<Int>()

        cmd.forEach {
            it.split(" ").let {
                when (it[0]) {
                    "D" -> {
                        cursor += it[1].toInt()
                    }
                    "U" -> {
                        cursor -= it[1].toInt()
                    }
                    "C" -> {
                        deleteLog.addLast(cursor)
                        totalSize--
                        if (cursor == totalSize) cursor--
                    }
                    "Z" -> {
                        val target = deleteLog.removeLast()
                        if (cursor >= target) cursor++
                        totalSize++
                    }
                }
            }
        }
        val sb = StringBuilder()
        repeat(totalSize) { sb.append("O") }
        deleteLog.reversed().forEach {
            sb.insert(it, "X")
        }
        return sb.toString()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2021_3()
//            println(s.solution(8,2, arrayOf("D 2","C","U 3","C","D 4","C","U 2","Z","Z")))
            println(s.solution(8, 2, arrayOf("D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C")))
        }
    }
}
// 걸린 시간(분): 80