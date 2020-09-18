package naver

class Line0913_4 {

    fun solution(maze: Array<IntArray>): Int {
        val UP = 0
        val DOWN = 2
        val LEFT = 3
        val RIGHT = 1
        var answer: Int = 0

        var currentX = 0
        var currentY = 0
        var ahead = DOWN
        val goal = maze.lastIndex

        fun checkLeft() :Boolean{
            var x = currentX
            var y = currentY
            when(ahead){
                DOWN -> y++
                UP -> y--
                LEFT -> x++
                RIGHT -> x--
            }

            if(x > goal || y > goal || x < 0 || y < 0 || maze[x][y] == 1)
                return true
            return false
        }

        fun checkRight() :Boolean{
            var x = currentX
            var y = currentY
            when(ahead){
                DOWN -> y--
                UP -> y++
                LEFT -> x--
                RIGHT -> x++
            }

            if(x > goal || y > goal || x < 0 || y < 0 || maze[x][y] == 1)
                return true
            return false
        }

        fun checkAhead() :Boolean{
            var x = currentX
            var y = currentY
            when(ahead){
                DOWN -> x++
                UP -> x--
                LEFT -> y--
                RIGHT -> y++
            }

            if(x > goal || y > goal || x < 0 || y < 0 || maze[x][y] == 1)
                return true
            return false
        }

        fun turnLeft() {
            ahead--
            if(ahead < 0) ahead = 3
        }

        fun turnRight() {
            ahead++
            if(ahead > 3) ahead = 0
        }

        fun goAhead(){
            when(ahead){
                DOWN -> currentX++
                UP -> currentX--
                LEFT -> currentY--
                RIGHT -> currentY++
            }
            answer++
        }

        while (currentX != goal || currentY != goal) {
            if(checkLeft()){
                if(checkAhead()){
                    if(checkRight()) {
                        turnLeft()
                    } else {
                        turnRight()
                        goAhead()
                    }
                } else {
                    goAhead()
                }
            } else {
                turnLeft()
                goAhead()
            }
        }


        /*
        if(왼쪽벽 있나?){
            if(앞에 벽 있나?){
                if(오른쪽 벽인가?) {
                    왼쪽으로 방향 전환
                } else {
                    오른쪽 전환후 직진
                }
            } else {
                직진
            }
        } else {
            왼쪽으로 전환 후 직진
        }
         */

        return answer
    }
}

fun main() {
    val s = Line0913_4()
    val r = s.solution(arrayOf(intArrayOf(0, 1, 0, 1), intArrayOf(0, 1, 0, 0), intArrayOf(0, 0, 0, 0), intArrayOf(1, 0, 1, 0)))
    println(r)
}