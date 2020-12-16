package programmers.skillcheck

class Level1_2 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val (a, b) = readLine()!!.split(' ').map(String::toInt)
            for (i in 1..b) {
                for (j in 1..a) {
                    print("*")
                }
                println()
            }
        }
    }

}