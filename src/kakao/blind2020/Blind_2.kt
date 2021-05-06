package kakao.blind2020

import java.util.*

//https://programmers.co.kr/learn/courses/30/lessons/60058
class Blind_2 {
    fun solution(p: String): String {
        if (p.isEmpty()) return p

        fun f(s:String): String {
            if (s.isEmpty()) return ""
            var l = 0
            var r = 0
            var u = ""
            var v = ""
            for (i in s.indices) {
                val c = s[i]
                if(c == '(') l++
                else r++
                u += c
                if(l == r) {
                    if(i < s.length)
                        v = s.substring(i+1)
                    break
                }
            }
            if(check(u)){
                return u+f(v)
            } else {
                return "(${f(v)})" + u.let {
                    it.substring(1, it.length-1).let {
                        it.map { if(it == '(') ')' else '(' }.joinToString("")
                    }
                }
            }
        }
        return f(p)
    }

    fun check(s:String): Boolean {
        val stack = Stack<Char>()
        for (c in s) {
            if(stack.isEmpty()) stack.push(c)
            else {
                val p = stack.peek()
                if(p == '(' && c == ')') {
                    stack.pop()
                } else {
                    stack.push(c)
                }
            }
        }
        return stack.isEmpty()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Blind_2()
//            println(s.solution("(()())()"))
            println(s.solution("()))((()"))
//            println(s.solution(")("))
        }
    }
}
// 걸린 시간(분): 30