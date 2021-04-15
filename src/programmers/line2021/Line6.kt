package programmers.line2021

class Line6 {

    val alphabetRg = "^[a-zA-Z]*$".toRegex() //알파벳으로만 이루어졌는지 확인히가 위한 regex

    fun solution(program: String, flag_rules: Array<String>, commands: Array<String>): BooleanArray {
        var answer = BooleanArray(commands.size) { true } //기본적으로 다 유효한 명령이라고 가정하고 시작

        // flag_rules을 맵 형태로 변환
        val temp = arrayListOf<List<String>>()
        val flagMap = hashMapOf<String, String>().apply {
            flag_rules.forEach {
                it.split(" ").let {
                    if(it.size > 2) {
                        temp.add(it)
                    } else {
                        this[it[0]] = it[1]
                    }
                }
            }
        }
        temp.forEach {
            flagMap[it[0]] = flagMap[it[2]]!!
        }

        /**각 명령어를 반복문을 통해 확인*/
        for (idx in commands.indices) {
            val command = commands[idx]
            val split = command.split(" ")

            /**프로그램명 확인*/
            if (program != split[0]) {
                answer[idx] = false
                continue
            }

            /**다음에 기대되는 값들이 올바르게 왔는지 확인*/
            var isNextFlagOrType = false //만약 이 값이 true이 아니면 다음 값이 플래그일수도 argument 타입일 수도 있다.
            var isNextFlag = true //다음에 올 값이 플래그인지 아닌지 여부
            var nextType = ""   //다음에 올 값이 argument인 경우 그 타입
            inner@ for (i in 1 until split.size) {
                val current = split[i]  //현재 확인중인 문자열

                if (isNextFlagOrType) {
                    isNextFlag = current.startsWith('-')
                }

                if (isNextFlag) {
                    if (flagMap.containsKey(current)) {
                        nextType = flagMap[current]!!
                        isNextFlag = nextType == "NULL" //argument가 필요없는 플래그인 경우 isNextFlag = true

                        if (i == split.lastIndex && !isNextFlag) {
                            //현재 문자열이 마지막 문자열인데 뒤에 argument가 와야하는 경우
                            answer[idx] = false
                            break@inner
                        }
                    } else {
                        //유효한 플래그 형식이 와야하는데 그렇지 못한 경우
                        answer[idx] = false
                        break@inner
                    }
                } else {
                    //current값으로 argument가 와야하는경우
                    isNextFlag = true   // argument가 온 뒤에는 반드시 플래그가 와야함으로 true로 변경 (NUMBERS, STRINGS 제외)

                    when (nextType) {
                        "NUMBER" -> {
                            isNextFlagOrType = false
                            if (current.toIntOrNull() == null) {
                                //숫자로 변환이 안되는 경우
                                answer[idx] = false
                                break@inner
                            }
                        }
                        "STRING" -> {
                            isNextFlagOrType = false
                            if (!alphabetRg.matches(current)) {
                                //알파벳으로만 이루어진 값이 아닌경우
                                answer[idx] = false
                                break@inner
                            }
                        }
                        "NUMBERS" -> {
                            isNextFlagOrType = true
                            if (current.toIntOrNull() == null) {
                                //숫자로 변환이 안되는 경우
                                answer[idx] = false
                                break@inner
                            }
                        }
                        "STRINGS" -> {
                            isNextFlagOrType = true
                            if (!alphabetRg.matches(current)) {
                                //알파벳으로만 이루어진 값이 아닌경우
                                answer[idx] = false
                                break@inner
                            }
                        }
                        else -> {
                            //이 경우는 존재하지 않는다.
                        }
                    }
                }
            }
        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Line6()
//            val r = s.solution("line", arrayOf("-s STRINGS", "-n NUMBERS", "-e NULL"), arrayOf("line -n 100 102 -s hi -e", "line -n id pwd -n 100"))
            val r = s.solution("trip", arrayOf("-days NUMBERS", "-dest STRING"), arrayOf("trip -days 15 10 -dest Seoul Paris", "trip -days 10 -dest asdf Seoul"))
            println(r.contentToString())

        }
    }
}
