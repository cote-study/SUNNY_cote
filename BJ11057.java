package coding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//오르막 수
//수의 자리가 오름차순을 이루는 수 >> 수가 같아도 오름차순
public class BJ11057 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //수의 길이
        long[][] arr = new long[N+1][10];
        long[] dp = new long[N+1];
        for(int i=1; i<=N; i++) {
            if(i==1) {
                dp[i] = 10;
                for(int j=0; j<10; j++) {
                    arr[i][j] = 1;
                }
            }
            if(i == 2) {
                dp[i] = 55;
                for(int j=0; j<10; j++) {
                    arr[i][j] = 10-j;
                }
            }
            if(i>=3) {
                arr[i][0] = dp[i-1] % 10007;
                dp[i] = (dp[i] + arr[i][0]) % 10007;
                for(int j=1; j<10; j++) {
                    arr[i][j] = (arr[i][j-1] - arr[i-1][j-1])%10007;

                    dp[i] = (dp[i] + arr[i][j]+10007) % 10007;
                }

            }

        }

        System.out.println(dp[N]);

    }
}
