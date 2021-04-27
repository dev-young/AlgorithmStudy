package programmers.practice

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

//https://programmers.co.kr/learn/courses/30/lessons/77486
//다단계 칫솔 판매
//level3
class P77486 {

    /**처음 제출한 코드가 너무 더러워서 다시 짠 코드*/
    fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
        val sumMap = HashMap<String, Int>()

        fun divide(price: Int): Pair<Int, Int> {
            val n = price / 10
            if (n < 1) return Pair(price, 0)
            return Pair(price - n, n)
        }

        val sellerPrice = hashMapOf<String, ArrayList<Int>>()
        seller.forEachIndexed { idx, s ->
            sellerPrice.computeIfAbsent(s){ arrayListOf()}.add(amount[idx] * 100)
        }
        val parentMap = hashMapOf<String, String>()
        val edges = HashMap<String, HashSet<String>>()
        for (i in enroll.indices) {
            val node = enroll[i]
            val parent = referral[i]
            sumMap[node] = 0
            edges.computeIfAbsent(parent){ hashSetOf()}.add(node)
            parentMap[node] = parent

        }
        fun cal(node:String, price:Int){
            if(node == "-") return
            val p = divide(price)
            sumMap[node] = p.first + sumMap[node]!!
            if(p.second > 0) {
                cal(parentMap[node]!!, p.second)
            }
        }

        val visited = hashSetOf<String>()
        val stack = Stack<String>()
        stack.push("-")
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            if (visited.contains(node)) {
                sellerPrice[node]?.let {
                    it.forEach { cal(node, it) }
                }
                continue
            }
            visited.add(node)
            stack.push(node)
            edges[node]?.let {
                for (e in it) {
                    if (visited.contains(e)) continue
                    stack.push(e)
                }
            }
        }

        return enroll.map { sumMap[it]!! }.toIntArray()
    }

    /**처음 제출하여 통과한 코드 (너무 지저분하다)*/
    fun solution2(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
        var answer = IntArray(enroll.size)

        fun divide(price: Int): Pair<Int, Int> {
            val n = price / 10
            if (n < 1) return Pair(price, 0)
            return Pair(price - n, n)
        }

        val map = hashMapOf<String, Int>()
        map["-"] = 0
        enroll.forEachIndexed { index, s ->
            map[s] = index + 1
        }
        val sellerPrice = hashMapOf<Int, ArrayList<Int>>()
        seller.forEachIndexed { idx, s ->
            sellerPrice.computeIfAbsent(map[s]!!){ arrayListOf()}.add(amount[idx] * 100)
        }
        val edges = Array(enroll.size + 2) { hashSetOf<Int>() }
        for (i in enroll.indices) {
            val k1 = enroll[i]
            val k2 = referral[i]
            edges[map[k1]!!].add(map[k2]!!)
            edges[map[k2]!!].add(map[k1]!!)
        }
        val parentArr = IntArray(enroll.size+2)
        fun cal(node:Int, price:Int){
            if(node == 0) return
            val p = divide(price)
            answer[node-1] += p.first
            if(p.second > 0) {
                cal(parentArr[node], p.second)
            }
        }

        val visited = hashSetOf<Int>()
        val stack = Stack<Pair<Int, Int>>()
        stack.push(Pair(0, 0))
        while (stack.isNotEmpty()) {
            val (node, parent) = stack.pop()
            parentArr[node] = parent
            if (visited.contains(node)) {
                if (node > 0) {
                    sellerPrice[node]?.let {
                        it.forEach { cal(node, it) }
                    }
                }
                continue
            }
            visited.add(node)
            stack.push(Pair(node, parent))
            for (e in edges[node]) {
                if (visited.contains(e)) continue
                stack.push(Pair(e, node))
            }
        }



        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = P77486()
            println(s.solution(arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"), arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"), arrayOf("young", "john", "tod", "emily", "mary"), intArrayOf(12, 4, 2, 5, 10)).contentToString())
//            println(s.solution(arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"), arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"), arrayOf("sam", "emily", "jaimie", "edward"), intArrayOf(2, 3, 5, 4)))

        }
    }
}
// 걸린 시간(분): 62
