package Ford_Fulkerson;

/*
   이번에 JAVA 를 이용하면서 알게된 것, import 문에서
   java.~~~.*을 입력하면 패키지 전체를 불러온다
 */

import java.lang.*;
import java.util.LinkedList;    // 연결리스트 관련 패키지 임포트
import java.util.Arrays;    // 배열 관련 패키지 임포트
import java.util.Queue; // 큐 관련 패키지 임포트

public class NetworkFlow {
    static int INF = Integer.MAX_VALUE; // 무한대에 가까운 정수 INF 설정
    static int V;   // 정점의 갯수 V
    static int[][] Capacity = new int[V][V];    // 간선의 용량 배열
    static int[][] Flow = new int[V][V];    // 간선의 유량 배열

    int flowFunc(int src, int sink) {
        for (int i = 0; i < V; i++) {   // 유량 배열 초기화
            Arrays.fill(Flow[i], 0);
        }

        int totalFlow = 0;  // 전체 유량 초기화

        while (true) {  // 더는 경로를 찾을 수 없을 때까지 반복(BFS)
            int[] parent = new int[V];  // BFS 실행을 위한 Queue 에 활용되는 parent 배열
            Arrays.fill(parent, 0); // parent 배열 초기화
            Queue<Integer> q = new LinkedList<Integer>();   // q에 인접리스트 적용

            parent[src] = src;  // parent 배열의 src 인덱스에는 src 입력
            q.add(src); // Queue 에 src 추가

            while (!q.isEmpty() && parent[sink] == -1) {    // Queue 가 비어있는 상태가 아니고, 도착점에 도달할 때까지 반복
                int now = q.poll(); // poll 이란 Queue 내에서 데이터를 하나 뽑아오는 명령어, now 에다가 Queue 에 저장된 데이터를 넣어준다

                for (int next = 0; next < V; ++next) {  // 다음 간선으로 넘어가는 정수 next 를 설정하고 정점의 갯수만큼 반복
                    if (Capacity[now][next] - Flow[now][next] > 0 && parent[next] == -1) {  // 최대 용량에서 현재 흐름을 뺀 값이 0보다 크고 도착점이 아닐 때
                        q.add(next);    // Queue 에 next 삽입
                        parent[next] = next;    // parent 의 next 인덱스에 next 를 삽입
                    }
                }
            }

            if (parent[sink] == -1) // 만약 도착점에 도달했으면,
                break;  // break

            int amount = INF;   // 양(amount)를 INF 로 초기화한다.
            for (int p = sink; p != src; p = parent[p]) {   // 도착점 sink 에서 시작점 src 가 아닐 때까지, p 를 parent[p]로 설정하며 반복
                amount = Math.min(Capacity[parent[p]][p] - Flow[parent[p]][p], amount); // amount 를 용량에서 유량을 뺀 값과 amount 중 최솟값으로 설정
            }

            for (int p = sink; p != src; p = parent[p]) {   // 도착점에서 시작점으로 가면서
                Flow[parent[p]][p] += amount;   // 정방향으로 간선을 지나면 +
                Flow[p][parent[p]] -= amount;   // 역방향으로 간선을 지나면 -
            }

            totalFlow += amount; // 전체 용량은 위에서 계산하던 amount 가 된다.
        }

        System.out.print(totalFlow); // 출력
        return 0;
    }
}
