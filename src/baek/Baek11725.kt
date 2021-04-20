package baek

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

//트리의 부모 찾기 (트리)
class Baek11725 {
    fun main() {
        //백준의 경우 입출력 실행 횟수가 많은 문제는 따로 선언해서 해야 속도가 빠르다!
        var br = BufferedReader(InputStreamReader(System.`in`))
        var bw = BufferedWriter(OutputStreamWriter(System.out))

        val n = br.readLine().toInt()
        val edges = Array(n+1) { hashSetOf<Int>() }
        for (i in 1 until n)
            br.readLine().split(" ").let {
                val l = it[0].toInt()
                val r = it[1].toInt()
                edges[l].add(r)
                edges[r].add(l)
            }

        val tree: HashMap<Int, Int> = hashMapOf()
        fun makeTree(node: Int, parent: Int = -1, ) {
            tree[node] = parent
            for (child in edges[node]) {
                if(child != parent) {
                    makeTree(child, node)
                }
            }
        }

        makeTree(1)
        for (i in 2 .. n) {
            bw.write("${tree[i]}\n")
        }
        br.close()
        bw.close()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek11725().main()
        }
    }
}
// 걸린 시간(분):