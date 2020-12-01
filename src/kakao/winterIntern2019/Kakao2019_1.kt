package kakao

fun main() {
    val board : Array<IntArray> = arrayOf(
        intArrayOf(0,0,0,0,0),
        intArrayOf(0,0,1,0,3),
        intArrayOf(0,2,5,0,1),
        intArrayOf(4,2,4,4,2),
        intArrayOf(3,5,1,3,1))
    val moves : IntArray = intArrayOf(1,5,3,5,1,2,1,4)

    print(solution200506_1(board, moves))   //4
}


fun solution200506_1(board: Array<IntArray>, moves: IntArray): Int {
    var answer = 0
    val basket = arrayListOf<Int>()
    
    for (i in moves) {
        val index = i - 1
        for (arr in board) {
            if (arr[index] != 0) {
                basket.add(arr[index])
                arr[index] = 0

                if (basket.size > 1) {
                    val up = basket.size - 1
                    val down = basket.size - 2
                    if (basket[up] == basket[down]) {
                        basket.removeAt(up)
                        basket.removeAt(down)
                        answer += 2
                    }
                }

                break
            }
        }
    }


    return answer
}