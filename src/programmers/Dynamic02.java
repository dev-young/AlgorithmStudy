package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/43105?language=java
public class Dynamic02 {
    public int solution(int[][] triangle) {
        if(triangle.length > 1) {
            for (int i = triangle.length-2; i >= 0; i--) {
                for (int j = 0 ; j<triangle[i].length ; j++) {
                    int max = Math.max(triangle[i+1][j], triangle[i+1][j+1]);
                    triangle[i][j] += max;
                }
            }
        }
        return triangle[0][0];
    }

    public static void main(String[] args) {

    }
}
