package baek

import java.io.BufferedReader
import java.io.InputStreamReader

//트리 순회 (트리)
class Baek1991 {
    /**이진트리에서 순회 구현*/
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()

        val edges = hashMapOf<Char, String>()
        repeat(n) {
            readLine().let {
                edges[it[0]] = it.substring(2)
            }
        }

        fun printPreorder(node: Char) {
            print(node)
            edges[node]?.let {
                if (it[0] != '.') { printPreorder(it[0]) }
                if (it[2] != '.') { printPreorder(it[2]) }
            }
        }

        fun printInorder(node: Char) {
            edges[node]?.let {
                if (it[0] != '.') { printInorder(it[0]) }
                print(node)
                if (it[2] != '.') { printInorder(it[2]) }
            }
        }

        fun printPostorder(node: Char) {
            edges[node]?.let {
                if (it[0] != '.') { printPostorder(it[0]) }
                if (it[2] != '.') { printPostorder(it[2]) }
            }
            print(node)
        }

        printPreorder('A')
        println()
        printInorder('A')
        println()
        printPostorder('A')

        close()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek1991().main()
        }
    }
}
// 걸린 시간(분): 26