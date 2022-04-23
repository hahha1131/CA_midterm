package NetworkFlow;

import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

public class edmonds_karp {
    public static int INF = Integer.MAX_VALUE;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("정점의 갯수 입력: \n");
        int V = sc.nextInt();
        System.out.print("간선의 갯수 입력: \n");
        int E = sc.nextInt();
        System.out.print("시작점 입력: \n");
        int src = sc.nextInt();
        System.out.print("도착점 입력: \n");
        int sink = sc.nextInt();

        int[][] Flow = new int[V + 1][V + 1];   // 유량 배열
        int[][] Capacity = new int[V + 1][V + 1];   // 용량 배열
        boolean[][] adj = new boolean[V + 1][V + 1];    // 인접한 정점 사이의 간선

        for(int e = 0; e < E; ++e){
            System.out.print("첫번째 정점 입력: ");
            int a = sc.nextInt();   // 정점 시작
            System.out.print("두번째 정점 입력: ");
            int b = sc.nextInt();   // 정점 도착
            System.out.print("두 정점 사이의 용량 입력: ");
            int c = sc.nextInt();   // 두 정점 사이의 용량
            Capacity[a][b] = c;
            System.out.print("\n" + a + "와 " + b + "사이 간선의 용량은 " + c + "이다.\n");
            adj[a][b] = adj[b][a] = true;   // 두 정점은 인접한 상태이다
            System.out.print(a + "와 " + b + "가 연결되었습니다.\n\n");
        }

        Queue<Integer> q = new LinkedList<Integer>();   // Queue 형식의 연결리스트
        int totalFlow = 0;  // 전체 유량

        // bfs 시작
        while(true){
            int[] back = new int[V + 1];    // 이전 지점

            q.clear();  // Queue 비우기
            q.add(src); // Queue에 추가
            back[src] = src;    // 시작점의 이전 지점은 시작점 자신이다

            while(!q.isEmpty()){    // Queue가 비어있지 않을 동안
                int present = q.poll(); // 현재 정점은 당장 Queue에서 뽑아오는 지점이다

                for(int next = 1; next <= V; ++next){   // 다음 지점이 1이고 V와 같을 때까지 다음지점을 올려간다
                    if(!adj[present][next]) // 만약 현재와 다음 정점이 인접하지 않는다
                        continue;   // 계속
                    if(back[next] != 0) // 만약 다음 정점의 이전 지점이 0이 아니면
                        continue;   // 계속
                    if(Capacity[present][next] - Flow[present][next] > 0){  // 만약 현재와 다음 지점 사이의 용량에서 현재 유량을 뺀 값이 0보다 크면
                        q.add(next);    // Queue에 다음 지점을 넣는다
                        back[next] = present;   // 다음 지점의 이전 지점은 현재의 정점이다
                        if(next == sink)    // 만약 다음 지점이 도착점이라면
                            break;  // 멈춘다
                    }
                }
            }

            if(back[sink] == 0) // 만약 도착점의 이전 지점이 0과 같다면
                break;  // 멈춘다

            int minimumFlow = INF;  // 최소 유량을 지정한다 (가장 큰 값으로)

            for(int vertex = sink; back[vertex] != vertex; vertex = back[vertex]){  // 지점을 도착점으로 지정, 정점의 이전 지점이 현재의 정점이 아닐 때까지 현재의 정점을 정점의 이전 값으로 돌린다
                minimumFlow = Math.min(minimumFlow, Capacity[back[vertex]][vertex] - Flow[back[vertex]][vertex]);   // 최소 유량과 이전 지점과 현재 지점의 용량에서 현재 지점의 유량을 뺀 값의 용량 중 작은 값을 최소 유량으로 정한다
            }

            for(int vertex = sink; back[vertex] != vertex; vertex = back[vertex]){  // 지점을 도착점으로 지정, 정점의 이전 지점이 현재의 정점이 아닐 때까지 현재의 정점을 정점의 이전 값으로 돌린다
                Flow[back[vertex]][vertex] += minimumFlow;  //  이전 지점과 현 지점의 유량에 최소 유량을 더해준다
                Flow[vertex][back[vertex]] -= minimumFlow;  //  현 지점과 이전 지점의 유량에 최소 유량을 빼준다
            }

            totalFlow += minimumFlow;   // 전체 유량을 최소 유량으로 설정한다
        }

        System.out.println("전체 유량은 " + totalFlow + "이다.");  // 전체 유량 출력
    }
}
