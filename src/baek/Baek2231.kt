package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//분해합
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()

    var result = 0

    for (i in 1 until n){
        val ds = getDS(i)
        if(ds == n){
            result = i
            break
        }
    }

    print(result)

}

fun getDS(n : Int) : Int{
    var sum = n
    var temp = n
    while(temp > 0){
        sum += temp%10
        temp /= 10
    }

    return sum
}