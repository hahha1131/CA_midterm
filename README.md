# Ford-Fulkerson Algorithm (포드 풀커슨 알고리즘)  

___

## 포드 풀커슨 알고리즘이란?  
* 일단 포드 풀커슨 알고리즘을 알기 전에 '**Network Flow(네트워크 플로우[유량])**'이라는 것에 대한 지식이 필요하다.  


* **네트워크 플로우**란?  
  * 그래프에서 어떤 두 정점 사이에서 간선의 길이가 아닌 데이터나 사물이 이동할 수 있는 유량의 관점에서 계산하는 것을 말한다.  


* 네트워크 플로우의 주요 특성 3가지  


  *1. 용량의 제한*
   - 간선에 흐르는 유량은 간선의 최대 유량을 넘을 수 없다.  
   
  *2. 유량의 대칭*
  - 네트워크 플로우의 핵심 특성이다.  
  - 예를 들어 설명하자면 정점 a에서 b로 양의 유량을 보내는 것은 b에서 a로 음의 유량을 보내는 것과 같은 것으로 간주한다.  
  - 추가적인 내용은 아래에서 설명  
  
  *3. 유량의 보존* 
  - 특정 정점을 기준으로 정점으로 들어오는 유량의 총합과 나가는 유량의 총합과 같아야 한다.
  - 단, 시작점(Source)와 도착점(Sink)는 제외다.


___

## 포드 풀커슨 알고리즘
* 네트워크 유량에 대해 알아보았으니 간단히 용어 몇개만 정리하고 알고리즘에 대해 설명하려 한다.  


     **용어**
  * Source: 시작점
  * Sink: 도착점
  * Capacity: 용량 (간선의 최대 유량)
  * Flow: 유량 (간선에 흐르는 데이터 or 사물의 양)
  * Augmenting path: 증가경로


* 포드 풀커슨 알고리즘은 시작점에서 도착점으로 동시에 보낼 수 있는 데이터 or 사물의 최대 양을 구하는 *최초의* 네트워크 플로우 알고리즘이다.
* 다른 네트워크 플로우 알고리즘이 있으나, 추후 포드 풀커슨 알고리즘에 대한 분석이 끝나고 비교할 때 소개할 예정

### 동작원리  
- 시작 부분에서 그래프의 모든 간선의 유량을 0으로 초기화한다.
- 시작점에서 도착점으로 가는 경로(증가경로)를 탐색한다.
  - 해당 경로는 반드시 최대 용량보다 적은 유량이 흐르거나 비워져있어야 한다 (즉, 여유 용량이 있어야 함)
  - 탐색방법은 DFS를 사용

- 찾아낸 경로의 최대 유량을 확인
- 찾아낸 최대 유량만큼의 유량을 흘려보냄
- 더는 증가경로가 없을 때까지 위의 과정을 반복
- **최종적으로 위의 과정이 끝난 후에 구해진 유량의 총합 = 최대 유량**

___

### 그림으로 알아보자  
![pic1](https://postfiles.pstatic.net/MjAyMjA0MjFfMTY1/MDAxNjUwNTI3MTgzODQz.DfajTHm6Q4TbPh60gYTY-ikG9PAwd-W0HJcLYQb_oosg.mc_HUOZfgvjAOYuAgvdLXXhE32dAAghsdm7HYquoM4Yg.PNG.jinha081131/%ED%8F%AC%EB%93%9C-%ED%92%80%EC%BB%A4%EC%8A%A8_%EA%B7%B8%EB%9E%98%ED%94%84_1.PNG?type=w966)

* 임시로 그래프를 하나 그렸다 (그래프의 수준이 높지 않은 점은 양해를 바란다)  
* 시작점과 도착점을 정하고 그 사이에 정점을 몇개 넣어주고 일부 정점 사이에 간선을 그린다

![pic2](https://postfiles.pstatic.net/MjAyMjA0MjFfMTgg/MDAxNjUwNTI3MTgzODU5.A4yOjWA1D6Lo9yUMtVe9GIVQYTE6_Mf5UdtEGqYgF1gg.mQB7XfRzaxj5RQN2Nx18aeK0GNOhZkypFZKJT0xW1hQg.PNG.jinha081131/%ED%8F%AC%EB%93%9C-%ED%92%80%EC%BB%A4%EC%8A%A8_%EA%B7%B8%EB%9E%98%ED%94%84_2.PNG?type=w966)

* 일단 탐색 방법에 상관없이 경로를 하나 찾아준다 (단순 동작원리를 알기 위함) [Src -> C -> D -> Sink]  

![pic3](https://postfiles.pstatic.net/MjAyMjA0MjFfMTI3/MDAxNjUwNTI3MTgzODQ0.03Aa1AE7ig2w_6l-YJOZ43-EmDRSXVEnNOJhchEhihMg.T40ohqqd_5TZ4dlXXok-rBr3DZ6H3UDgICSViBlXB2Ig.PNG.jinha081131/%ED%8F%AC%EB%93%9C-%ED%92%80%EC%BB%A4%EC%8A%A8_%EA%B7%B8%EB%9E%98%ED%94%84_3.PNG?type=w966)

* 이후 찾은 경로에 유량을 흘려 보내는데, 경로의 각 간선 중 가장 작은 용량을 가진 간선을 기준으로 보내준다
* 예시로 찾은 경로에서는 Src -> C 간선에서의 최대 유량이 3이므로 해당 경로 전체에 3을 보냄

![pic4](https://postfiles.pstatic.net/MjAyMjA0MjFfMjI0/MDAxNjUwNTI3MTgzODky.oWdMY95Cs-tmDkY26o36_Nfaaq8WsTtA_7t95Ely73sg.qt1Pz09ak9m4_MHHomeFqPjjm0234wUsniUarSuX0Vcg.PNG.jinha081131/%ED%8F%AC%EB%93%9C-%ED%92%80%EC%BB%A4%EC%8A%A8_%EA%B7%B8%EB%9E%98%ED%94%84_4.PNG?type=w966)

* 위의 과정이 끝난 후, 다른 증가경로를 찾아서 위의 과정을 반복한다 (최대 유량 판단 후, 흘려 보냄)
* Src -> A -> D -> Sink 경로 발견, 최대 유량은 Src -> A에서 1이므로 전체경로에 1을 흘려 보냄

![pic5](https://postfiles.pstatic.net/MjAyMjA0MjFfMTA3/MDAxNjUwNTI3MTgzODQz.PQhmEZ7D0uxfpUyrZsm1h5vntErwNReVterCS7SB1l0g.GEOOd4KsHLkPIRDIFCQDhVAeMOg4Ajxbf6Kx73Tc2oYg.PNG.jinha081131/%ED%8F%AC%EB%93%9C-%ED%92%80%EC%BB%A4%EC%8A%A8_%EA%B7%B8%EB%9E%98%ED%94%84_5.PNG?type=w966)

* 마지막 경로 Src -> B -> E -> Sink, 최대 유량 B -> E에서 2이므로 전체경로에 2를 흘려 보냄
* 더 나올 수 있는 증가경로가 없기 때문에 최종 유량을 구한다
* 최종 유량 = 2 + 4 = 6  

___

## 최대 유량은 진짜 6일까?
* 포드 풀커슨 알고리즘을 통해 위의 예시로 그린 그래프의 전체 유량을 구했다
* 추후 점검을 한 결과, 내가 그린 그래프에서는 완벽하게 구해졌지만  
<https://gseok.gitbooks.io/algorithm/content/b124-d2b8-c6cc-d06c-d50c-b85c-c6b0/d3ec-b4dc-d480-cee4-c2a828-ford-fulkerson-c560-b4dc-baac-b4dc-ce74-d50428-edmonds-karp.html>
* 위의 블로그에서 나오는 그래프에서는 위에서 실행한 방법이 완벽한 답을 구하지는 못하는 것으로 나와있다
 * 이유는 역으로 돌아갈 수 있는 간선이 있을 때에만 완벽한 답이 나오는 종류의 그래프가 있기 때문이다
 * 즉, 유량을 조금씩 나누어 흘려 보내면 더 높은 전체 유량을 구할 수 있는 경우가 있다는 뜻
* 따라서 위와 같은 과정을 위해서는 초반에 언급했던 **유량의 대칭** 특성을 이용해야 한다

___
### 유량의 대칭을 이용한 역간선
![reference material.1](https://gseok.gitbooks.io/algorithm/content/assets/network-flow10.png)
* 위의 그림에서 보듯이 최대 유량이 3인 간선의 역간선인 유량이 -3/0인 간선을 만들어서

![reference material.2](https://gseok.gitbooks.io/algorithm/content/assets/network-flow12.png)
* 그림과 같은 역할을 하게 만들어준다
 * 역간선을 취하면서 기존 간선의 유량을 줄이고 다른 간선을 이용하게 되며 더 효율적으로 유량을 분배하여 더 높은 유량을 찾을 수 있게 된다.

___
## 코드 작성
* 코드는 따로 작성하여 리포지토리에 소스코드 파일을 올릴 예정

___
## 성능 분석
* 
___
## 정리
* 네트워크 플로우의 일종인 포드 풀커슨 알고리즘에 대해 조사하며 브루트 포스(brute force: 모든 경우의 수를 대입하여 결과값에 도달하는 방법)의 일종이라는 것을 알게 되었다.  
* 브루트 포스의 장점은 옛날 학교 사물함 자물쇠 비밀번호를 맞추어 볼 때를 예시로 들 수 있다. 모든 숫자들을 맞춰보며 결국 최종적으로 맞는 비밀번호를 얻을 수 있는데, 비밀번호가 생각보다 적은 경우의 수에서 발견될 수도 있고 운에 따라서 한번에 알아낼 수도 있는, 초등학생 시절 친구들 사이에서 가장 좋은 방법으로 불리던 기법이었다.
* 그러나 어쩌면 브루트 포스 기법, 즉 포드 풀커슨 알고리즘의 가장 큰 약점이라고 할 수 있는 것이 있었는데 경우의 수가 만약 100000가지가 된다면 100000가지의 경우의 수를 모두 실행하면서 결과값에 접근해야 한다는 것이었다.
* 포드 풀커슨 알고리즘은 이러한 경우에 시간복잡도가 상당히 커지며 실제 프로그램을 작동시킨다고 했을 때에도 최종 결과값을 얻는 데에 엄청난 시간이 소요될 것이다.
* 이러한 점에서 같은 네트워크 플로우의 일종이면서 BFS 탐색을 통해 모든 경우의 수를 돌지 않고 결과값에 도달할 수 있는 **Edmonds-Karp Algorithm**(에드몬드 카프 알고리즘)이 있다.
