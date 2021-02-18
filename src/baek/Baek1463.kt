package baek

import kotlin.math.max
import kotlin.math.min

//1로 만들기  (동적프로그래밍)
class Baek1463 {

    fun main() {
        val n = readLine()!!.toInt()
        val arr = LongArray(1000005).apply {
            this[3] = 1
            this[2] = 1
        }
        for (i in 4 .. n) {
            var a = arr[i-1]
            if(i%3 == 0){
                arr[i/3].let {
                    if(it < a)
                        a = it
                }
            }
            if(i%2 == 0){
                arr[i/2].let {
                    if(it < a)
                        a = it
                }
            }
            arr[i] = a+1
        }
        println(arr[n])
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek1463().main()
        }
    }
}
// 걸린 시간(분):13