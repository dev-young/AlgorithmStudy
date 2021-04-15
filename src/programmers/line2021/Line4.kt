package programmers.line2021

class Line4 {

    data class Node(val id: Int, val name: String, val parent: Int)

    fun solution(data: Array<String>, word: String): Array<String> {
        var answer: Array<String> = arrayOf<String>()

        val graph = hashMapOf<Int, Node>()
        val leafNodes = hashSetOf<Int>()
        data.forEach {
            it.split(" ").let {
                val id = it[0].toInt()
                val node = Node(id, it[1], it[2].toInt())
                graph[id] = node
                leafNodes.remove(node.parent)
                leafNodes.add(id)
            }
        }
        val resultIds = arrayListOf<Int>()
        //정확히 일치
        leafNodes.forEach {
            if (graph[it]!!.name == word) {
                resultIds.add(it)
            }
        }
        resultIds.sort()
        resultIds.forEach { leafNodes.remove(it) }

        val temp = arrayListOf<Int>()
        leafNodes.forEach {
            if (graph[it]!!.name.contains(word))
                temp.add(it)
        }
        temp.sortWith(Comparator { o1, o2 ->
            var n1 = graph[o1]!!.name
            var n2 = graph[o2]!!.name
            var wordCount1 = 0
            var wordCount2 = 0
            var is1high = false
            var i1 = -1
            var i2 = -1
            while (true) {
                i1 = n1.indexOf(word)
                i2 = n2.indexOf(word)
                if (i1 != -1) {
                    wordCount1++
                    n1 = n1.removeRange(i1, i1+word.length)
                }
                if (i2 != -1) {
                    wordCount2++
                    n2 = n2.removeRange(i2, i2+word.length)
                }
                is1high = i1 < i2
                if (i1 == -1 || i2 == -1) {
                    break
                }
            }
            if (wordCount1 == wordCount2) {
                while (true) {
                    i1 = n1.indexOf(word)
                    i2 = n2.indexOf(word)
                    if (i1 != -1) {
                        wordCount1++
                        n1 = n1.removeRange(i1, i1+word.length)
                    }
                    if (i2 != -1) {
                        wordCount2++
                        n2 = n2.removeRange(i2, i2+word.length)
                    }
                    if (i1 == -1 || i2 == -1) {
                        break
                    }
                }
            }

            -(wordCount1.compareTo(wordCount2))
        })
        resultIds.addAll(temp)
        temp.forEach { leafNodes.remove(it) }




        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Line4()
            val r = s.solution(arrayOf("1 BROWN 0", "2 CONY 0", "3 DOLL 1", "4 DOLL 2", "5 LARGE-BROWN 3", "6 SMALL-BROWN 3", "7 BLACK-CONY 4", "8 BROWN-CONY 4"), "BROWN")
            println(r)

        }
    }
}
