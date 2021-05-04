package kakao.intern2020

import kotlin.math.absoluteValue
import kotlin.math.exp

//https://programmers.co.kr/learn/courses/30/lessons/67257?language=kotlin
class Kakao2020_2 {
    fun solution(expression: String): Long {
        var answer: Long = 0

        val orders = arrayListOf<String>()
        orders.add("+-*")
        orders.add("+*-")
        orders.add("*+-")
        orders.add("-+*")
        orders.add("-*+")
        orders.add("+*-")
        orders.add("-+*")

        val expList = arrayListOf<String>()
        var temp = ""
        expression.forEach {
            when(it) {
                '*','+','-' -> {
                    expList.add(temp)
                    expList.add(it.toString())
                    temp = ""
                }
                else -> {
                    temp += it
                }
            }
        }
        expList.add(temp)

        fun find(list: ArrayList<String>, target:String): Int {
            list.forEachIndexed { index, s ->
                if (s == target) return index
            }
            return -1
        }
        fun cal(v1:String, o:String, v2:String): Long {
            return when(o) {
                "+" -> v1.toLong() + v2.toLong()
                "-" -> v1.toLong() - v2.toLong()
                else -> v1.toLong() * v2.toLong()
            }
        }

        orders.forEach { order ->
            var v = 0
            val list = ArrayList(expList)
            order.forEach {
                val o = it.toString()
                var i = find(list, o)
                while (i > -1) {
                    val c = cal(list[i-1], o, list[i+1])
                    list.removeAt(i+1)
                    list.removeAt(i)
                    list.removeAt(i-1)
                    list.add(i-1, c.toString())
                    i = find(list, o)
                }
            }
            Math.abs(list[0].toLong()).let {
                if(it > answer) answer = it
            }
        }


        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2020_2()
            var r = s.solution("100-200*300-500+20")
            println(r)
        }
    }
}
// 걸린 시간: 33