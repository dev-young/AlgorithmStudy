package baek

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

/** BufferedReader 사용 */
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    BufferedWriter(OutputStreamWriter(System.out)).run {
        repeat(readLine().toInt()) {
            val items = mutableMapOf<String, Int>()
            repeat(readLine().toInt()) {
                val (_, item) = readLine().split(" ")
                if (items.containsKey(item))
                    items.replace(item, (items[item]!! + 1))
                else
                    items[item] = 2
            }
            if(!items.values.isEmpty())
                write("${items.values.reduce { a, b -> a*b} -1}\n")
            else
                write("0\n")
        }
        flush()
    }
}
