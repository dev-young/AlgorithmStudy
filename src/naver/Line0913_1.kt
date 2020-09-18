package naver

class Line0913_1 {
    fun solution(boxes: Array<IntArray>): Int {
        var answer: Int = 0

        for(i in boxes.indices){
            if(boxes[i][0] != boxes[i][1]){
                for(j in i+1 .. boxes.lastIndex) {
                    if(boxes[i][0] == boxes[j][0]) {
                        swap(boxes, i, j, 1, 0)
                        break
                    } else if(boxes[i][1] == boxes[j][0]) {
                        swap(boxes, i, j, 0, 0)
                        break
                    } else if(boxes[i][0] == boxes[j][1]) {
                        swap(boxes, i, j, 1, 1)
                        break
                    } else if(boxes[i][1] == boxes[j][1]) {
                        swap(boxes, i, j, 0, 1)
                        break
                    }
                }

                if(boxes[i][0] != boxes[i][1]){
                    answer++
                }
            }


        }

        return answer
    }

    fun swap(boxes: Array<IntArray>, box1Index:Int, box2Index: Int, box1Pos:Int, box2Pos:Int){
        val temp = boxes[box1Index][box1Pos]
        boxes[box1Index][box1Pos] = boxes[box2Index][box2Pos]
        boxes[box2Index][box2Pos] = temp
    }
}

fun main() {
    val s = Line0913_1()
    val r = s.solution(arrayOf(intArrayOf(1, 3), intArrayOf(3, 2), intArrayOf(2,1)))
    println(r)
}