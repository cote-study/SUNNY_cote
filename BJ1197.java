package coding;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//크루스칼
// 간선을 추가할 때마다 트리가 사이클을 이루는지 판단 >> Union-Find 자료 구조
public class BJ1197 {
    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int cost;

        Edge(int s, int e, int c) {
            this.cost = c;
            this.end = e;
            this.start = s;
        }
        // 가중치로 오름차순 정렬
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    static int[] parent;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        parent = new int[V+1];
        for(int i=1; i<parent.length; i++) {
            parent[i] = i;
        }
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            queue.add(new Edge(s, e, c));
        }

        long answer = 0, count = 0;
        while(!queue.isEmpty()) {
            Edge edge = queue.poll();
            if(find(edge.start) != find(edge.end)) {
                answer += edge.cost;
                count ++;
                union(edge.start, edge.end);
            }
            if(count == V-1) break;
        }
        System.out.println(answer);
        br.close();
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[a] = b;

    }
    private static int find(int a) {
        if(parent[a] == a) {
            return a;
        } else return parent[a] = find(parent[a]); //노드를 부모의 노드로 동일하게 만듬
    }

}
