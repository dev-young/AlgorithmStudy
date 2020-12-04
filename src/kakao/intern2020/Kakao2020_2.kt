package kakao.intern2020

import kotlin.math.absoluteValue

//https://programmers.co.kr/learn/courses/30/lessons/67257?language=kotlin
class Kakao2020_2 {
    fun solution(expression: String): Long {
        var answer: Long = 0
        val regex = "[*+-]".toRegex()
        val exp = arrayListOf<String>()
        val exps = regex.findAll(expression).toList()
        val nums = regex.split(expression)
        exps.forEachIndexed { index, matchResult ->
            exp.add(nums[index])
            exp.add(matchResult.value)
        }
        exp.add(nums.last())

        fun permutation(
            arr: Array<String>,
            r: Int,
            result: ArrayList<Array<String>>,
            temp: Array<String> = Array(arr.size) { "" },
            current: Int = 0,
            visited: BooleanArray = BooleanArray(arr.size)
        ) {
            if (r == current) {
                result.add(temp.clone())
            } else {
                for (i in arr.indices) {
                    if (!visited[i]) {
                        visited[i] = true
                        temp[current] = arr[i]
                        permutation(arr, r, result, temp, current + 1, visited)
                        visited[i] = false
                    }
                }
            }
        }

        val expSet = exps.map { it.value }.toHashSet().toTypedArray()
        val result = arrayListOf<Array<String>>()
        permutation(expSet, expSet.size, result)
        result.forEach {
            val temp = exp.clone() as ArrayList<String>
            it.forEach {
                var idx = 0
                while (idx < temp.size) {
                    if (temp[idx] == it) {
                        val right = temp.removeAt(idx + 1).toLong()
                        temp.removeAt(idx)
                        val left = temp.removeAt(idx - 1).toLong()
                        val r = when (it) {
                            "*" -> right * left
                            "+" -> right + left
                            "-" -> left - right
                            else -> 0
                        }
                        temp.add(idx - 1, r.toString())
                    } else idx++
                }
            }
            temp[0].toLong().absoluteValue.let {
                if (it > answer)
                    answer = it
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
// 걸린 시간: 62분