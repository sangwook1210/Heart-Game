# Heart-Game
1. 서론

● 프로젝트 목적
객체지향 프로그래밍 수업에서 지금까지 배웠었던 클래스, ArrayList 등을 응용하여 하트게임 프로그램을 만들어보자

● 프로젝트 내용
하트게임은 트럼프카드를 사용하므로 트럼프카드를 구현할 클래스와 트럼프카드들을 담을 ArrayList가 필요하다.
여러 명의 플레이어가 게임을 플레이하므로 그들이 할 행동을 구현할 클래스와 플레이어들을 담을 ArrayList가 필요하다.
플레이어가 낸 카드를 저장하고 우열을 검사하는 행동이 들어갈 클래스가 필요하다.
라운드를 나타낼 while문, 트릭을 나타낼 while문, 턴을 나타낼 while문이 필요하다.

2. 프로그램의 구성

● 전체 구성도
ArrayList<Trump> deck = new ArrayList<Trump>();에 52장의 트럼프카드를 담는다.
Player클래스의 ArrayList<Trump> hand=new ArrayList<Trump>();에 플레이어에게 분배된 카드들을 담는다.
Player클래스의 ArrayList<Trump> dead_card=new ArrayList<Trump>();에 한 트릭이 끝나고 플레이어가 가져간 카드들을 담는다.
Opened_card의 ArrayList<Trump> opened_card=newArrayList<Trump>();에는 플레이어가 각 턴에 낸 카드들을 담는다.

round: while(true)에서는 지금이 몇 라운드인지 출력하고, 2라운드 이상이면 각 플레이어들의 dead card들을 deck에 섞어넣고 다시 카드를 분배한다.
trick: while(true)에서는 지금이 몇 번째 트릭인지 출력하고 첫 번째로 카드를 낼 플레이어를 정해준다.
turn: while(true)에서는 각 플레이어가 카드를 내고 우열을 비교하여 한 플레이어가 카드를 가져가는 작업이 진행된다.
만약 트릭이 끝났을 때 이번 트릭에서 하트카드가 나왔다면 다음 트릭에서 첫 번째 플레이어가 하트를 낼 수 있도록 can_Heart를 true로 설정한다.
트릭이 끝났을 때 한 플레이어가 Q 4장을 모았다면 break round;한다.
라운드가 끝났을 때, 한 플레이어의 가져간 카드의 점수의 총합이 30점을 넘긴다면 가장 점수가 낮은 플레이어가 우승하고 break round;한다. 

● 프로그램 설명
![image](https://user-images.githubusercontent.com/112921582/221403702-cee2bfd2-bdb4-4b07-b5b8-86bed851d9e2.png)
