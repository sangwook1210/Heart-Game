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

● 프로그램 설명<br>
![image](https://user-images.githubusercontent.com/112921582/221403702-cee2bfd2-bdb4-4b07-b5b8-86bed851d9e2.png)
트럼프 카드 객체를 생성하는 Trump 클래스에는 카드의 문양을 저장하는 suit 변수, 카드의 번호를 저장하는 number 변수, 카드의 우선순위를 저장하는 priority 변수가 있다.
Trump 생성자로 카드의 문양, 번호, 우선순위를 정한다.
A카드에는 14, K카드에는 13, Q카드에는 12, J카드에는 11, 나머지 카드에는 각 숫자를 우선순위로 부여한다.<br>
![image](https://user-images.githubusercontent.com/112921582/221406660-c9c11d3a-f9f8-41de-b0df-16f907706ce0.png)
Player 클래스에는 플레이어의 이름을 저장할 name 변수, 오류가 생겼을 경우 이를 확인할 error 변수, 이 플레이어가 트릭에서 제일 처음 카드를 내는 사람인지를 저장할 first 변수, 이 플레이어의 플레이 순서를 저장할 order 변수, 이 플레이어의 점수를 저장할 score 변수, 이 플레이어가 승리했는지를 확인할  check_win 변수, 이 플레이어에게 분배된 카드들을 나타낼 hand ArrayList, 이 플레이어가 가져간 카드들을 나타낼 dead_card ArrayList가 있다.
Player 클래스의 생성자로 name을 정해준다.<br>
  ![image](https://user-images.githubusercontent.com/112921582/221406677-7ee2842a-374a-49e3-b6ed-ecc16f7772b6.png)
init_first() 메소드는 first 변수를 false로 초기화하는 함수이다.
print_hand() 메소드는 플레이어의 손에 있는 카드를 전부 출력하는 함수이다.
card_draw(ArrayList<Trump> deck, int i) 메소드는 플레이어가 deck에서 카드를 뽑는 함수이다.
shuffle_hand(ArrayList<Trump> deck) 메소드는 플레이어의 손에 있는 모든 카드를 deck에 다시 집어넣는 함수이다.<br>
  ![image](https://user-images.githubusercontent.com/112921582/221406686-0fbd9f74-2ce3-4823-84d7-99d3a092144b.png)
  shuffle_dead_card(ArrayList<Trump> deck) 메소드는 플레이어가 가져간 카드들을 다시 deck에 섞어넣는 함수이다.
play_card(Opened_card opencard, int hand_num, boolean can_Heart)메소드는 플레이어가 카드를 내는 메소드이다. 만약 플레이어가 제일 처음 카드를 내는 사람이고, 하트를 내려고 한다면, 이전 트릭에서 하트가 나왔는지 확인한다. 플레이어가 내려고 하는 카드가 플레이어가 가지고 있는 카드가 아니라면 error를 증가시킨다.<br>
  ![image](https://user-images.githubusercontent.com/112921582/221406696-f6c42711-46a3-48fa-a675-b548d3f543a5.png)
push_dead_card(Opened_card opencard) 메소드는 플레이어가 낸 카드를 한 플레이어가 다 가져가는 함수이다.
print_dc() 메소드는 각 플레이어가 가져간 카드를 표기하는 함수이다.
give_score() 메소드는 규칙에 따라 각 플레이어의 점수를 매기는 함수이다.<br>
![image](https://user-images.githubusercontent.com/112921582/221406704-ea9b465f-6fb6-467b-b860-ed7a2016b25b.png)
  win_4Q() 메소드는 플레이어가 4장의 Q를 모았는지 확인하는 함수이다. 만약 4장의 Q를 모았다면 true, 모으지 못했다면 false를 반환한다.<br>
  ![image](https://user-images.githubusercontent.com/112921582/221406717-eface99e-a809-4e91-b3b6-9a82a3cc24b5.png)
Opened_card 클래스는 플레이어들이 낸 카드에 관련된 클래스이다.
ArrayList<Trump> opened_card=new ArrayList<Trump>();는 각 플레이어가 낸 카드를 담을 ArrayList이다.
print_opencard() 메소드는 각 플레이어가 낸 카드가 무엇인지 출력하는 함수이다.
add_opencard(ArrayList<Trump> hand, int i) 메소드는 플레이어가 카드를 내는 함수이다. opened_card에 플레이어가 낸 카드를 추가하고 hand에서 제거한다.
check_priority() 메소드는 플레이어들이 낸 카드의 우선순위를 확인하는 함수이다.<br>
![image](https://user-images.githubusercontent.com/112921582/221406730-13e1f606-0e34-47d5-bf6c-6761c4582022.png)
ArrayList<Trump> deck = new ArrayList<Trump>();를 생성하고, 이에 위와 같이 52장의 트럼프카드를 담는다.<br>
