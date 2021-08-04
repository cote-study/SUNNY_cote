package coding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pointer {
    int x;
    int y;
    boolean check;
    public Pointer(int x, int y, boolean check) {
        this.x = x;
        this.y = y;
        this.check = check;
    }
}
public class BJ2206 {
    static int N;
    static int M;
    static int[][] map;
    static int[][] visited;
    static int[][] visited2;
    static int[][] move = {{1, 0}, {-1, 0},{0,1}, {0,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new int[N][M];
        visited2 = new int[N][M];
        for (int i=0; i<N; i++) {
            String[] str = br.readLine().split("");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(str[j]);
                visited[i][j] = -1;
                visited2[i][j] = -1;
            }
        }

        Queue<Pointer> queue = new LinkedList<>();
        queue.add(new Pointer(0,0, false));
        visited[0][0] = 1;
        while(!queue.isEmpty()) {
            Pointer point = queue.poll();
            if(point.x == N-1 && point.y == M-1) {
                break;
            }

            for(int i=0; i<4; i++) {
                int nx = point.x + move[i][0];
                int ny = point.y + move[i][1];
                if(nx>=0 && nx<N && ny>=0 && ny<M) {
                    //1. 벽을 뚫지 않았을 때 (check = false)
                    if(visited[nx][ny] == -1 && !point.check && map[nx][ny] == 0) {
                        visited[nx][ny] = visited[point.x][point.y] + 1;
                        queue.offer(new Pointer(nx, ny, point.check));
                    }

                    //2. 벽을 부수기 (check = true)
                    if(visited[nx][ny] == -1 && !point.check && map[nx][ny] == 1) {
                        visited[nx][ny] = visited[point.x][point.y] + 1;
                        visited2[nx][ny] = visited[point.x][point.y] + 1;
                        queue.offer(new Pointer(nx, ny, true));
                    }

                    if(visited2[nx][ny] == -1 && point.check && map[nx][ny] == 0) {
                        visited2[nx][ny] = visited2[point.x][point.y] + 1;
                        queue.offer(new Pointer(nx, ny, point.check));
                    }

                }
            }
        }
//        System.out.println(visited[N-1][M-1]);
//        for (int i=0; i<N; i++) {
//            for(int j=0; j<M; j++) {
//                System.out.print(visited[i][j]+ "\t");
//            }
//            System.out.println();
//        }
//
//        System.out.println("sec: " + visited2[N-1][M-1]);
//        for (int i=0; i<N; i++) {
//            for(int j=0; j<M; j++) {
//                System.out.print(visited2[i][j]+ "\t");
//            }
//            System.out.println();
//        }
        if(visited[N-1][M-1] != -1) {
            System.out.println(visited[N-1][M-1]);
        } else {
            System.out.println(visited2[N-1][M-1]);
        }


    }
}
/*
8 8
01000100
01010100
01010100
01010100
01010100
01010100
01010100
00010100
29

5 10
0000011000
1101011010
0000000010
1111111110
1111000000
 */