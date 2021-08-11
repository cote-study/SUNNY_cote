package coding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;
import java.awt.Point;

public class BJ18223 {
    public static class  Node implements Comparable<Node>{
        int point;
        int cost;

        Node(int e, int c) {
            this.point = e;
            this.cost = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    static ArrayList<Node>[] graph;
    static int[] costs;
    static int V;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken()); //건우가 위치한 정점

        graph = new ArrayList[V+1];
        for(int i=1; i<=V; i++) {
            graph[i] = new ArrayList<>();
        }


        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            //양 방향,,,?
            graph[s].add(new Node(e, c));
            graph[e].add(new Node(s,c));
        }
        int value = dijkstra(1, V);
        int check = dijkstra(1, P) + dijkstra(P, V);

        if(value >= check) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }

    }
    private static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        costs = new int[V+1];
        Arrays.fill(costs, 10000);
        costs[start] = 0;
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(now.point == end) break;
            for(int i= 0; i<graph[now.point].size(); i++) {
                Node next = graph[now.point].get(i);
                // 현재 cost보다 지금 가는 경로가 더 작으면
                if(costs[next.point] >= costs[now.point] + next.cost) {
                    costs[next.point] = costs[now.point] + next.cost;
                    pq.offer(next);
                }
            }
        }
        return costs[end];
    }
}
