package baek


//연속합 (동적프로그래밍)
class Baek1912 {

    //걍 생각없이 푼 방법
    fun main() {
        val n = readLine()!!.toInt()
        val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

        var startIdx = 0
        var endIdx = 0
        var current = 0
        var max = arr[0]

        while (startIdx < n && endIdx < n) {
            current += arr[endIdx]
            if(current > max){
                max = current
            }
            endIdx++
            if(current < 0) {
                startIdx = endIdx
                current = 0
            }
        }

        println(max)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek1912().main()
        }
    }
}
// 걸린 시간(분): 55