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
<https://github.com/hahha1131/CA_midterm/blob/main/NetworkFlow.java>

___
## 성능 분석
* DFS를 활용한 포드 풀커슨에 관한 소스 코드를 찾아보았으나, 애초에 네트워크 플로우 문제를 해결할 때 에드몬드 카프 알고리즘이 더 효율적이라는 글이 대부분이었고 간신히 DFS로 포드 풀커슨 알고리즘 소스 코드를 발견했다.
* 그러나 java를 활용하여 쓰여져 있었고, BufferedReader / StringBuilder / StringTokenizer 등 아직 내가 모르는 용어들이 많았다.
* 다른 DFS를 활용한 소스코드들은 대부분 C++로 쓰여져 있었고, C++을 모르는 내가 갑자기 짧은 기간에 C++을 쓴다는 것은 말이 되지 않았다.
* 아무리 기본 지식이 부족하여 여러 블로그에서 다른 사람들의 알고리즘 정리 내용을 보고 소스코드를 참고한다고 하더라도 내가 처음 보는 용어를 쓰는 소스코드를 보고 따라 쓰는 것은 이해를 하지 않고 그냥 있는 그대로 복사를 하겠다는 것과 다름이 없다고 생각하여 DFS 포드 풀커슨 알고리즘 소스코드는 그냥 참고하는 것이 더 좋을 것이라고 생각한다.

<https://intrepidgeeks.com/tutorial/til-network-traffic-ford-folkson-edmund-kraft-algorithm-binary-matching>

* 위의 링크에서 DFS를 이용하는 포드 풀커슨 알고리즘의 소스코드를 볼 수 있다.

### 시간 복잡도

* 시간복잡도 면에서 두 소스코드의 성능을 비교하겠다.

#### 포드 풀커슨 알고리즘
* 포드 풀커슨 알고리즘의 시간복잡도는 일반적으로 **O(EF)** 라고 한다.
 * 증가경로의 최대 갯수 F
 * 정점의 갯수 V
 * 간선의 갯수 E
* 정점의 갯수가 작다면 무시가 가능해서 O((E+V)F)가 아니라 O(EF)가 된다.
* 만약 모든 간선에서 (역간선을 이용한 통로 역할의 간선 제외) 유량의 크기가 21478444 수준으로 커진다면 실제 소스코드가 모두 동작하기까지 정말 오랜 시간이 걸릴 수도 있다.

#### 에드몬드 카프 알고리즘
* 에드몬드 카프 알고리즘의 시간복잡도는 일반적으로 **O(VE^2)** 라고 한다.
* 간선을 왜 VE번 찾는지는 수학적으로 파고들어야 하기 때문에 이를 수학적으로 분석한 블로그의 링크를 첨부한다.
* 
<https://gazelle-and-cs.tistory.com/82>

* 실제 에드몬드 카프 알고리즘의 시간복잡도는 **O(EF)** 또는 **O(VE^2)** 라고 한다. 두 복잡도 중 크기가 더 작은 복잡도를 사용하면 되고 두 복잡도의 크기는 간선의 수, 유량의 크기에 따라 달라진다.
* 간선이 유량에 비해 많고, 유량이 간선에 비해 적다면 O(EF) < O(VE^2)가 되어 O(EF)를 사용하는 경우도 있다.
* 에드몬드 카프 알고리즘은 유량이 아무리 커도 BFS 방식으로 증가경로를 찾기 때문에 유량의 크기가 커질수록 불리한 포드 풀커슨 알고리즘보다 더 효율적인 방식이라고 할 수 있다.
___
## 포드 풀커슨 정리
* 네트워크 플로우의 일종인 포드 풀커슨 알고리즘에 대해 조사하며 브루트 포스(brute force: 모든 경우의 수를 대입하여 결과값에 도달하는 방법)의 일종이라는 것을 알게 되었다.  


* 브루트 포스의 장점은 옛날 학교 사물함 자물쇠 비밀번호를 맞추어 볼 때를 예시로 들 수 있다. 모든 숫자들을 맞춰보며 결국 최종적으로 맞는 비밀번호를 얻을 수 있는데, 비밀번호가 생각보다 적은 경우의 수에서 발견될 수도 있고 운에 따라서 한번에 알아낼 수도 있는, 초등학생 시절 친구들 사이에서 가장 좋은 방법으로 불리던 기법이었다.


* 그러나 어쩌면 브루트 포스 기법, 즉 포드 풀커슨 알고리즘의 가장 큰 약점이라고 할 수 있는 것이 있었는데 경우의 수가 만약 100000가지가 된다면 100000가지의 경우의 수를 모두 실행하면서 결과값에 접근해야 한다는 것이었다.


* 포드 풀커슨 알고리즘은 이러한 경우에 시간복잡도가 상당히 커지며 실제 프로그램을 작동시킨다고 했을 때 최종 결과값을 얻는 데에 엄청난 시간이 소요될 것이다.


* 이러한 점에서 같은 네트워크 플로우의 일종이면서 BFS 탐색을 통해 모든 경우의 수를 돌지 않고 결과값에 도달할 수 있는 **Edmonds-Karp Algorithm**(에드몬드 카프 알고리즘)이 더 효율적으로 작동할 수도 있다는 것을 알 수 있다.

___

## Edmonds-Karp Algorithm (에드몬드 카프 알고리즘)
* 에드몬드 카프 알고리즘은 간선을 탐색하는 과정에서 DFS 방식이 아닌 BFS 방식을 사용하여 모든 경우의 수를 알아보지 않으면서 빠르게 최대 유량을 찾을 수 있는 포드 풀커슨 알고리즘과 같은 네트워크 플로우의 일종이면서 특정 경우에서는 포드 풀커슨 알고리즘보다 효율적인 알고리즘이다.

___

## 그렇다면 무조건 에드몬드 카프 알고리즘이 좋은가?
* 최대 유량을 구하는 문제에서 무조건 에드몬드 카프 알고리즘이 무조건 좋다고 생각할 수 있는데 이는 앞에서 언급했듯이 **특정 경우**에서만 에드몬드 카프 알고리즘이 효율적이라고 할 수 있다
![EKA pic1](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb1wkEf%2Fbtrn1zzImKX%2FkFqrs5B8VOhgCnV4kjMKI1%2Fimg.png)
* 위의 그림과 같이 간선의 최대 유량이 1000이 된다면 포드 풀커슨 알고리즘은 중앙에 위치한 1이라는 최대 유량을 가진 간선을 용량의 대칭성을 이용하여 수많은 반복을 통해 총 2000번의 탐색으로 최대 유량을 찾아내는 반면
* 에드몬드 카프 알고리즘은 BFS를 통해 2번의 탐색으로만 최대 유량을 찾을 수 있다.
* 이처럼 최대 유량이 지나치게 큰 경우에는 에드몬드 카프 알고리즘이 더 효율적이라고 볼 수 있다.
* 그러나 유량이 아닌 간선의 갯수가 많은 경우에는 DFS를 사용하는 포드 풀커슨 알고리즘이 더 효율적이라고 할 수 있다.

___
## 결론
* 포드 풀커슨 알고리즘은 간선의 갯수가 많은 문제에서, 에드몬드 카프 알고리즘은 간선에 주어진 유량의 최대 용량이 많은 문제에서 각각 더 뛰어난 성능을 보였다는 것을 알 수 있었다.
* 어느 알고리즘이 더 좋은지 따지기 보다는 특정 조건이 주어진 문제에서 어떠한 알고리즘을 사용하는 것이 훨씬 효율적으로 작용하는지 구분하는 것이 중요하다.
___

## 참고 사이트

* 처음 DFS 포드 풀커스 알고리즘을 C로 작성하기 위해 참고  
<https://www.programiz.com/dsa/ford-fulkerson-algorithm>

* 처음으로 접해본 포드 풀커슨 알고리즘에 관한 기초지식  
<https://gseok.gitbooks.io/algorithm/content/b124-d2b8-c6cc-d06c-d50c-b85c-c6b0/d3ec-b4dc-d480-cee4-c2a828-ford-fulkerson-c560-b4dc-baac-b4dc-ce74-d50428-edmonds-karp.html>

* 역간선을 이용하는 것과 포드 풀커스 알고리즘의 약점에 관한 더 자세한 설명  
<https://levenshtein.tistory.com/150>

* 에드몬드 카프 알고리즘과 포드 풀커스 알고리즘 비교  
<https://wooono.tistory.com/401>

* 유량 그래프를 알아본 사이트  
<https://velog.io/@kasterra/%EC%9C%A0%EB%9F%89-%EA%B7%B8%EB%9E%98%ED%94%84-%ED%8F%AC%EB%93%9C-%ED%92%80%EC%BB%A4%EC%8A%A8-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98>

* 네트워크 유량에 대해, 그에 관련한 알고리즘 정리  
<https://velog.io/@bae_mung/TIL-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-%EC%9C%A0%EB%9F%89%ED%8F%AC%EB%93%9C-%ED%92%80%EC%BB%A4%EC%8A%A8-%EC%97%90%EB%93%9C%EB%A8%BC%EB%93%9C-%EC%B9%B4%ED%94%84-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98>

* C언어로 DFS 포드 풀커슨 알고리즘 소스코드 작성 재시도를 위한 참고  
<https://sahebg.github.io/computersceince/Maximux-flow-ford-fulkarson-algorithm-c-program-example/>

* C언어로 작성하려다가 작성할 것이 지나치게 많아져 java를 이용한 포드 풀커슨 알고리즘 작성 방법을 알아봄  
<https://doublezerostone.tistory.com/34>

* java에서 DFS, BFS  
<https://devuna.tistory.com/32>

* java에서 인접행렬, 인접리스트, DFS  
<https://nobilitycat.tistory.com/entry/%EA%B9%8A%EC%9D%B4-%EC%9A%B0%EC%84%A0-%ED%83%90%EC%83%89-DFS-%EC%9D%B8%EC%A0%91-%ED%96%89%EB%A0%AC-%EC%9D%B8%EC%A0%91-%EB%A6%AC%EC%8A%A4%ED%8A%B8>

* DFS 포드 풀커슨   
<https://intrepidgeeks.com/tutorial/til-network-traffic-ford-folkson-edmund-kraft-algorithm-binary-matching>

___

## 후기
* 위쪽 참고 사이트 칸이 지나치게 길어 보인다. 처음 접하는 주제라서 그런지 더 많이 찾아봤던 것 같다. 처음에는 자료구조와 비슷하게 보여서 C로 작성을 시도했으나, DFS로 포드 풀커슨을 작성한 사이트가 잘 보이지 않아서 (본인의 구글링이 형편없었을 수도 있음) 자료구조와 관련하여 다양한 패키지가 많고 실제 네트워크 유량 알고리즘 작성에는 java가 더 어울리는 것 같아서 java를 이용하기로 결정했다. (겸사겸사 java 문법도 익혀보려는 생각)
 
* 간만에 예전에 작성했던 인접리스트, 그래프, DFS, BFS 관련 소스코드들을 찾아보는 유익한 시간이었던 느낌이다. 정보를 찾고 이해하고 소스코드를 작성하기까지 오래 걸렸지만 다양한 알고리즘 지식을 쌓을 수 있었고 java를 C와 같은 수준까지는 아니더라도 좀 더 편하게 다룰 수 있도록 많이 활용해볼 수 있는 시간이었다.
