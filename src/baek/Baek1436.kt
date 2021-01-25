package baek

class Baek1436 {

    fun main() {
        val n = readLine()!!.toInt()
        var num = 665
        var currentN = 0
        while (n != currentN) {
            num++
            if(num.toString().contains("666")){
                currentN++
            }
        }
        println(num)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val r = Baek1436().main()
        }
    }
}
// 걸린 시간(분):