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
![image](https://user-images.githubusercontent.com/112921582/221406767-6e8427c4-effe-40e2-939f-93e636d6b823.png)
  게임을 플레이할 플레이어의 수를 p_num 변수에 입력받는다.
만약 잘못된 입력이 들어오면 잘못된 입력임을 출력하고 다시 입력을 받는다.
입력된 플레이어의 수만큼 각각의 name을 입력받는다.
  ![image](https://user-images.githubusercontent.com/112921582/221406777-da0dfd82-0f3e-45bd-af05-762a23ca29af.png)
게임을 플레이하는 플레이어의 순서를 무작위로 정한다. 만약 이전의 플레이어와 같은 순서가 나온다면 i--;를 사용하여 for문을 한번 더 실행하여 다시 순서를 정한다. 모든 순서가 정해진 후에는 정해진 순서를 출력한다.
  
게임을 시작하기 전, 게임 진행에 필요한 변수들을 선언한다.
hand_num 변수는 플레이어로부터 낼 카드를 입력받을 변수이다.
round 변수는 지금이 몇 라운드인지를 저장할 변수이다.
trick 변수는 지금이 몇 트릭인지를 저장할 변수이다.
turn 변수는 플레이어의 차례를 나타내는 변수이다.
cnt1과 cnt2 변수는 플레이어의 차례가 왔는지 체크할 변수들이다. 
top 변수는 opencard 중 가장 큰 카드의 인덱스를 저장할 변수이다. 
can_Heart 변수는 처음으로 카드를 내는 사람이 하트를 낼 수 있는지 검사하는 변수이다.
Opened_card opencard=new Opened_card();로 Opened_card ArrayList 를 생성한다.


round: while(true)로 round 무한루프가 시작된다.
라운드가 시작했으니 round 변수를 증가시켜주고 trick 변수를 0으로 초기화시켜주고 라운드가 시작했음을 출력한다.
만약 지금이 2라운드 이상이면 플레이어들이 가지고 있던 모든 카드를 deck에 다시 섞어넣는다.
모든 플레이어가 한 명씩 돌아가며 무작위로 player 클래스의 card_draw 함수를 이용하여 hand_volume만큼 카드를 뽑는다.
hand_volume은 52/p_num의 정수값이다.
다 뽑고 나면, 남은 deck에 클로버 2가 있는지 검사한다.
만약 있다면 뽑은 모든 카드를 deck에 돌려넣고 continue outer;를 사용하여 다시 카드를 뽑는다. 이와 같은 과정을 계속 반복하여 남아있는 deck에 클로버 2가 없다면 break outer;를 사용하여 카드뽑기를 완료한 후 모든 플레이어의 hand를 출력한다.


turn을 0으로 초기화한 후, 어느 플레이어가 클로버 2를 가지고 있는지 검사한다.
그 플레이어의 first 변수를 true로 만들어준다.


trick: while(true)로 trick 무한루프가 시작된다.
trick을 1 증가시켜준 후, cnt1을 0, cnt2를 –1로 초기화시킨다.
first가 true인 플레이어를 찾아내 turn 변수를 그 플레이어의 order로 변경시킨 후, 몇 번째 라운드의 몇 번째 트릭인지를 출력한다.


turn: while(true)로 turn 무한루프가 시작된다.
turn 변수를 trick에서 처음 플레이하는 플레이어의 order로 변경시켰으므로 turn%p_num은 첫 번째 플레이어의 order와 같을 것이다. 이 상태에서 turn을 1 증가시킨다면 turn%p_num은 두 번째 플레이어의 order와 같을 것이다. 이런 방식으로 한 플레이어의 턴이 끝나면 turn 변수를 1 증가시킨다.
플레이어의 턴이 시작되면 그 플레이어의 차례임을 출력하고 error를 0으로 초기화한다.
현재 차례인 플레이어의 hand와 현재 opencard를 출력한다.
플레이어로부터 낼 카드를 입력받는다.
만약 잘못된 입력이 들어온다면 error를 1 증가시킨다.
만약 error가 0보다 크다면 플레이어의 턴을 다시 반복한다.
플레이어의 행동이 끝나면 차례를 변경시켜주기 위해 turn을 1 증가시키고, 몇 명의 플레이어가 행동을 완료했는지 세는 변수인 cnt을 1 증가시키고, can_Heart 변수를 false로 초기화시킨다.


만약 턴을 완료한 사용자의 명수를 세는 변수인 cnt1과 사용자 수가 일치한다면 한 트릭이 끝났다는 뜻이다.
모든 opencard를 검사해 Heart가 있다면 can_Heart를 true로 만들어준다.
모든 opencard를 출력한 후, opencard 객체의 check_priority() 메소드를 이용하여 top 변수에 우선순위가 가장 높은 opencard의 인덱스를 저장한다.
모든 플레이어의 first를 초기화한다.
플레이어가 낸 순서대로 opencard 객체에 저장되므로, cnt2가 각 플레이어가 카드를 낸 순서를 저장하는 변수이다.
위에서 플레이어의 턴을 결정하는 방식과 똑같이 
if(player.get(i).order==turn%p_num)과 turn++을 사용하여 플레이어가 냈던 순서대로 cnt2를 -1부터 증가시킨다.
만약 top이 cnt2가 아니라면 cnt2를 또다시 증가시켜 top과 비교한다.
이를 통하여 몇 번째로 카드를 낸 플레이어가 카드를 가져갈지 알 수 있다.
어느 플레이어가 카드를 가져갈지가 정해졌다면, 그 플레이어의 first를 true로 바꾸고 break examin;을 한 후, 모든 플레이어의 dead_card를 출력한다.


공개된 카드들을 한 플레이어가 가져가는 과정이 끝난 후, Player 클래스의 win_4Q() 메소드를 이용하여 4개의 Q를 모은 플레이어가 있는지 검사한다.
만약 있다면, 모은 플레이어가 게임을 승리하였다고 출력한 후 break round;로 프로그램을 종료한다.


한 트릭이 끝나면 플레이어의 손에 있는 카드의 개수가 0이 되었는지 확인한다.
만약 0이 되었다면, Player 클래스의 give_score() 메소드를 사용하여 모든 플레이어의 점수를 매긴다.
만약 점수가 30점이 넘는 사람이 있다면, 첫 번째 플레이어의 score를 min에 저장한 후, 다른 플레이어와 비교하여 더 score가 작은 플레이어가 있다면 그 플레이어의 score를 min에 저장한다.
모든 플레이어의 score를 min과 비교하여 min과 score가 같은 플레이어가 승리했다고 출력하고 break round;로 프로그램을 종료한다.
만약 점수가 30점이 넘는 사람이 없다면, break trick;으로 다음 라운드로 넘어간다.

3. 결과

● 프로그램 테스트 결과
1. 인원수를 입력할 때, 4~6 외의 입력을 했을 경우

4~6 외의 숫자나 문자가 들어와도 프로그램이 멈추지 않고 정상적으로 동작하여 플레이어의 이름과 플레이어의 순서를 정한다.

2. 플레이어가 낼 카드를 입력할 때, 범위를 벗어난 입력이 들어왔거나 이전 트릭에서 하트가 나오지 않은 경우

Clover 2를 가지고 있는 a가 가장 먼저 카드를 내게 된다.
사용자로부터 낼 카드의 번호를 입력받을 때, 범위 밖의 숫자를 입력하거나 문자를 입력되면 사용자로부터 다시 입력을 받는다.
이 스크린샷의 a는 1라운드 1번째 트릭의 첫 번째 플레이어이므로 이전 트릭에서 Heart가 나오지 않아 Heart 카드를 낼 수 없다.
a로부터 낼 카드를 입력받으면, 입력된 카드가 a의 hand에서 사라지고 opencard에 표시된다.

3. 한 트릭이 끝나면 첫 사람이 낸 카드와 문양이 같고, 가장 우열이 높은 사람이 모든 카드를 가져간다.

처음에 정해졌던 순서인 cbda에서 d가 가장 먼저 시작했으므로 dacb의 순서대로 트릭이 진행된다.
첫 플레이어가 낸 카드가 Clover 6이고, c가 낸 카드가 Clover A이므로, c가 모든 공개된 카드들을 가져간다.
다음 트릭에서 카드를 가져간 c가 첫 번째로 시작하고, 전 트릭에서 Heart가 나왔으므로 Heart를 낼 수 있다.

4. 모든 플레이어가 카드를 다 내어 라운드가 끝났을 경우

한 라운드가 끝나면 플레이어가 가져간 카드들의 점수를 계산하여 출력한다.
30점이 넘은 플레이어가 없으므로, 다음 라운드를 시작하고 모든 플레이어에게 카드를 재분배한다.
Clover 2를 가지고 있는 d가 2라운드 1번째 트릭의 첫 번째 플레이어이다.

5. 30점을 넘겨 게임이 종료된 경우

5라운드 13번째 트릭이 끝난 후, b의 스코어가 30을 넘어 게임이 종료되었다.
a가 23점으로 점수가 가장 낮으므로 a가 승리했음을 출력하고 프로그램이 종료된다.

6. 한 사람이 Q 4장을 모아 승리한 경우

1라운드 3번째 트릭에 b가 4장의 Q를 모아 b의 승리를 출력했고, 프로그램이 즉시 종료되었다.
  
  
