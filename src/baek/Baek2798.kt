package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//블랙잭
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val temp = readLine().split(' ')
    val n = temp[0].toInt()
    val m = temp[1].toInt()
    val numbers = IntArray(n)
//    val numbers = readLine().split(" ").map { it.toInt() }.filter { it < m }.toIntArray()
    var result = 0
    var i = 0
    readLine().split(' ').forEach {
        numbers[i++] = it.toInt()
    }

    loop@ for (i in 0 until n-2){
        for (j in i+1 until n-1){
            for(k in j+1 until n){
                val sum = numbers[i] + numbers[j] + numbers[k]
                if(sum == m){
                    result = sum
                    break@loop
                }
                if(result < sum && sum < m)
                    result = sum

            }
        }
    }

    println(result)
}