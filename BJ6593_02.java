package coding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    int dep;
    int row;
    int col;
    int count;

    Node (int dep, int row, int col, int count ) {
        this.col = col;
        this.dep = dep;
        this.row = row;
        this.count = count;
    }
}
public class BJ6593_02 {
    static char[][][] map;
    static int L;
    static int R;
    static int C;
    static boolean[][][] visited;
    static int answer;
    //상하동서남북
    static int [] dx = {0,0,1,-1,0,0,};
    static int [] dy = {0,0,0,0,1,-1};
    static int [] dz = {1,-1,0,0,0,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Node> queue = new LinkedList<>();
        while (true) {
            queue.clear();
            answer = Integer.MAX_VALUE;
            int[] arr = new int[3];
            String[] str = br.readLine().split(" ");
            L = Integer.parseInt(str[0]);
            R = Integer.parseInt(str[1]);
            C = Integer.parseInt(str[2]);

            if(L == 0 && R == 0 && C == 0) {
                br.close();
                break;
            }

            map = new char[L][R][C];
            visited = new boolean[L][R][C];
            for(int i =0; i<map.length; i++) {
                for(int j=0; j<map[i].length; j++) {
                    char[] str2 = br.readLine().toCharArray();
                    for(int k=0; k<map[i][j].length; k++) {
                        map[i][j][k] = str2[k];
                        if(str2[k] == 'S') {
                            arr[0] = i;
                            arr[1] = j;
                            arr[2] = k;
                        }
//                        System.out.print(map[i][j][k] + "\t");
                    }
//                    System.out.println();
                }
                br.readLine();
//                System.out.println();
            }
            queue.offer(new Node(arr[0],arr[1],arr[2], 0));
            while(!queue.isEmpty()) {
                Node node = queue.poll();
                if(map[node.dep][node.row][node.col] == 'E') {
                    answer = node.count;
                    break;
                }
                if(!visited[node.dep][node.row][node.col]) {
                    visited[node.dep][node.row][node.col] = true;
                    for(int i=0; i<6; i++) {
                        int newdep = node.dep + dz[i];
                        int newrow = node.row+ dy[i];
                        int newcol = node.col +dx[i];
                        if(newdep>=0 && newdep<L &&  newcol>=0 &&  newcol<C &&  newrow>=0 &&  newrow<R&& map[newdep][newrow][newcol] != '#'  ) {
                            queue.offer(new Node(newdep, newrow, newcol, node.count+1));
                        }
                    }

                }
            }
//            check(arr[0],arr[1],arr[2], 0);
            if(answer == Integer.MAX_VALUE) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in "+answer+" minute(s).");
            }
        }
    }


}
