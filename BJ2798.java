package coding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2798 {
    static boolean[] visited;
    static int N;
    static int M;
    static int[] output;
    static int[] arr;
    static int answer = 0; //양의 정수만 주어짐
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        output = new int[3];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        combination(0,0);
        System.out.println(answer);
    }
    private static void combination(int start, int depth) {
        if(depth == 3){
            int sum = 0;
            for(int i=0; i<3; i++) {
                sum += output[i];
//                System.out.println("i: " + i + " output: " + output[i]);
            }
//            System.out.println("sum: " + sum);
            if((M-sum) == 0) {
                answer = sum;
            } else if(M-sum > 0 && sum > answer) {
                answer = sum;
            }
            return;
        }
        for(int i=start; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                combination(i+1, depth+1);
                visited[i] = false;
            }
        }

    }
}
