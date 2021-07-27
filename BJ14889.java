package coding;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ14889 {
    static int N;
    static int[][] score;
    static boolean[] visited;
    static int[] output;
    static int[] output2;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        score = new int[N][N];
        output = new int[N/2];
        output2 = new int[N/2];
        for(int i=0; i<N; i++) {
            String[] str = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                score[i][j] = Integer.parseInt(str[j]);
            }
        }
        combination(0, 0);
        System.out.println("answer: " + answer);
    }

    static void combination(int depth, int start) {
        if(depth == N/2) {
            int A = 0;
            int count = 0;
            for(int i=0; i<N; i++) {
                if(!visited[i]) {
                    output2[count] = i;
                    count ++;
                }else {
                    for(int j=0; j< output.length; j++) {
                        A += score[i][output[j]];
                    }
                }
            }
            int B =0;
            for(int i=0; i<output2.length; i++) {
                for(int j=0; j< output2.length; j++) {
                    B+= score[output2[i]][output2[j]];
                }
            }

            int minus = Math.abs(A-B);
            if(answer > minus) answer = minus;
            return;
        }
        for(int i=start; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                output[depth] = i;
                combination(depth+1, i+1);
                visited[i] = false;
            }
        }
    }


}
