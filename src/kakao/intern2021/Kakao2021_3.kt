package kakao.intern2021

class Kakao2021_3 {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        var answer = CharArray(n){'O'}
        var cur = k
        val list = arrayListOf<Int>()
        for (i in 0 until n) {
            list.add(i)
        }
        val deletedSet = hashSetOf<Int>()
        val deleted = arrayListOf<Int>()  // 초기 배열의 idx
        val deletedIdx = arrayListOf<Int>() //list의 idx

        fun run(command:String){
            val s = command.split(" ")
            val c = s[0]
            val target = if(s.size == 1) -1 else s[1].toInt()
            when(c){
                "U" -> {
                    cur -= target
                }
                "D" -> {
                    cur += target
                }
                "C" -> {
                    val r = list.removeAt(cur)
                    deleted.add(r)
                    deletedIdx.add(cur)
                    deletedSet.add(r)
                    if(list.size == cur)
                        cur--
                }
                "Z" -> {
                    val idx = deletedIdx.removeAt(deletedIdx.size-1)
                    val z = deleted.removeAt(deleted.size-1)
                    list.add(idx, z)
                    deletedSet.remove(z)
                    if(idx <= cur)
                        cur++
                }
            }
        }

        for (s in cmd) {
            run(s)
        }

        deletedSet.forEach {
            answer[it] = 'X'
        }

        return answer.joinToString("")
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2021_3()
//            println(s.solution(8,2, arrayOf("D 2","C","U 3","C","D 4","C","U 2","Z","Z")))
            println(s.solution(8,2, arrayOf("D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C")))
        }
    }
}
// 걸린 시간(분):