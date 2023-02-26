package javaProject;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Heart_Game {

	public static void main(String[] args) {
		ArrayList<Trump> deck = new ArrayList<Trump>();
		
		//52장의 트럼프카드 생성시작
		for(int i=0;i<13;i++) {
			if (i==0)
				deck.add(new Trump("Spade","A"));
			else if (i==10)
				deck.add(new Trump("Spade","J"));
			else if (i==11)
				deck.add(new Trump("Spade","Q"));
			else if (i==12)
				deck.add(new Trump("Spade","K"));
			else
				deck.add(new Trump("Spade",String.valueOf(i+1)));
		}
		for(int i=0;i<13;i++) {
			if (i==0)
				deck.add(new Trump("Heart","A"));
			else if (i==10)
				deck.add(new Trump("Heart","J"));
			else if (i==11)
				deck.add(new Trump("Heart","Q"));
			else if (i==12)
				deck.add(new Trump("Heart","K"));
			else
				deck.add(new Trump("Heart",String.valueOf(i+1)));
		}
		for(int i=0;i<13;i++) {
			if (i==0)
				deck.add(new Trump("Clover","A"));
			else if (i==10)
				deck.add(new Trump("Clover","J"));
			else if (i==11)
				deck.add(new Trump("Clover","Q"));
			else if (i==12)
				deck.add(new Trump("Clover","K"));
			else
				deck.add(new Trump("Clover",String.valueOf(i+1)));
		}
		for(int i=0;i<13;i++) {
			if (i==0)
				deck.add(new Trump("Diamond","A"));
			else if (i==10)
				deck.add(new Trump("Diamond","J"));
			else if (i==11)
				deck.add(new Trump("Diamond","Q"));
			else if (i==12)
				deck.add(new Trump("Diamond","K"));
			else
				deck.add(new Trump("Diamond",String.valueOf(i+1)));
		}
		//52장의 트럼프카드 생성완료

		ArrayList <Player> player = new ArrayList<Player>();	//player ArrayList 생성
		Scanner sc=new Scanner(System.in);	//Scanner 객체 생성
		Random random = new Random();	//Random 객체 생성
		
		//게임을 플레이할 플레이어의 수를 입력받는다
		int p_num=0;
		while(true) {
			System.out.print("게임을 플레이할 사용자의 수를 입력해주세요(4~6인): ");
			try {
				p_num=sc.nextInt();	//게임을 플레이할 플레이어의 수를 입력받음
				if(p_num>=4&&p_num<=6)
					break;
				else {	//4미만 6 초과의 수를 입력받았을 경우
					System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n");
					continue;
				}
			}
			catch(Exception e) {	//정수 말고 다른 입력을 받았을 경우
				sc=new Scanner(System.in);
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n");
				continue;
			}
		}
		
		String p_name;	//플레이어의 이름을 입력받을 변수
		sc.nextLine();	//개행문자(엔터)를 제거하기 위하여 추가
		
		//모든 사용자의 이름을 입력받는다
		for(int i=0;i<p_num;i++) {	//플레이어의 수 만큼 반복
			System.out.print((i+1)+"번째 사용자의 이름을 입력해주세요: ");
			p_name=sc.nextLine();
			
			player.add(new Player(p_name));
		}
		System.out.println();
		
		//플레이어의 순서를 정한다
		System.out.println("게임을 시작하기에 앞서 플레이어의 순서를 정하겠습니다.");
		for(int i=0;i<p_num;i++) {	//플레이어의 순서를 정한다.
			player.get(i).order=random.nextInt(p_num);	//order를 0~get(0).player_num-1 중 하나의 값으로 설정한다.
			for(int j=0;j<i;j++) {
				if(player.get(i).order==player.get(j).order)	//지금 설정한 i의 order 값이 이전의 order 값과 중복된다면
					i--;	//다시 order값을 설정한다.
			}
		}
		
		//정해진 플레이어의 순서를 출력한다
		System.out.print("게임은 ");
		for(int i=0;i<p_num;i++) {
			for(int j=0;j<p_num;j++) {
				if(player.get(j).order==i)
					System.out.print(player.get(j).name+" ");
			}
		}
		System.out.println("순으로 진행될 예정입니다.\n");
		
		//******게임 시작******
		int hand_num=0;	//플레이어로부터 낼 카드를 입력받을 변수
		int round=0;	//몇 라운드인지를 저장할 변수
		int trick=0;	//몇 트릭인지를 저장할 변수
		int turn=0;	//플레이어의 차례를 나타내는 변수
		int cnt1=0;	//플레이어의 차례가 왔는지 체크할 변수 1
		int cnt2=-1;	//플레이어의 차례가 왔는지 체크할 변수 2
		int top=0;	//opencard 중 가장 큰 카드의 인덱스를 저장할 변수
		boolean can_Heart=false;	//첫 사람이 하트를 낼 수 있는지 검사하는 변수
		Opened_card opencard=new Opened_card();	//Opened_card ArrayList 생성
		
		round: while(true) {
			round++;
			trick=0;
			System.out.println("\n"+round+"라운드 시작\n");
			
			//지금이 2라운드 이상이면 플레이어들이 가지고 있던 카드들을 덱에 다시 섞어넣는다.
			if(round>=2) {
				for(int i=0;i<p_num;i++) {
					player.get(i).shuffle_dead_card(deck);
				}
			}
			
			//모든 플레이어들중 한명이 반드시 Clover 2를 가지고 있게끔 카드를 뽑는다
			int hand_volume=52/p_num;
			System.out.println("모든 플레이어가 각각 "+hand_volume+"장씩 카드를 뽑습니다.");
			
			outer: while(true) {
				for(int i=0;i<hand_volume;i++) {	//모든 플레이어가 돌아가며 각각 hand_volume장씩 카드를 뽑는다.
					for(int j=0;j<p_num;j++) {
						player.get(j).card_draw(deck, random.nextInt(deck.size()));
					}
				}
				
				for(int i=0;i<deck.size();i++) {	//만약 남은 덱에 클로버 2가 있다면
					if(deck.get(i).suit.equals("Clover")&&deck.get(i).number.equals("2")) {
						for(int j=0;j<p_num;j++) {	//뽑은 모든 카드를 다시 덱에 넣고
							player.get(j).shuffle_hand(deck);
						}
						continue outer;	//다시 카드를 뽑는다.
					}
				}
				break outer;	//남은 덱에 클로버 2가 없다면 멈춘다.
			}
				
			for(int i=0;i<p_num;i++) {	//모든 플레이어의 패를 출력한다.
				player.get(i).print_hand();
				player.get(i).error=0;
			}
			System.out.println();
			
			//클로버 2를 가지고 있는 사람이 가장 먼저 카드를 내게된다.
			turn=0;
			for(int i=0;i<hand_volume;i++) {
				for(int j=0;j<p_num;j++) {
					if(player.get(j).hand.get(i).suit.equals("Clover")&&player.get(j).hand.get(i).number.equals("2")) {
						player.get(j).first=true;
					}	
				}
			}
		trick: while(true) {
			trick++;
			cnt1=0;
			cnt2=-1;
			for(int i=0;i<p_num;i++) {
				if(player.get(i).first==true)
					turn=player.get(i).order;
			}
			System.out.println("\n"+round+"라운드 "+trick+"번째 트릭\n");
		turn: while(true) {
			//플레이어의 턴
			for(int i=0;i<p_num;i++) {
				if(player.get(i).order==turn%p_num) {
					System.out.println(player.get(i).name+"의 차례");
					player.get(i).error=0;
					
					//현재 차례인 플레이어의 패와 현재 공개된 카드들을 출력
					player.get(i).print_hand();
					opencard.print_opencard();
					
					//현재 차례인 플레이어가 낼 카드를 정함
					System.out.print("낼 카드의 번호를 입력하세요(카드의 번호는 1부터 시작해서 오른쪽으로 갈수록 1씩 증가합니다): ");
					try { hand_num=sc.nextInt(); }
					catch(Exception e) { sc=new Scanner(System.in); }
					player.get(i).play_card(opencard, hand_num, can_Heart);
					System.out.println();
					
					if(player.get(i).error>0)
						i--;
				}
			}
			cnt1++;
			turn++;
			can_Heart=false;
			
			//한 트릭이 끝났다면
			if(cnt1==p_num) {
				//opencard에 하트가 있다면 다음 트릭에 첫 카드로 하트를 낼 수 있다
				for(int i=0;i<opencard.opened_card.size();i++) {
					if(opencard.opened_card.get(i).suit.equals("Heart")) {
						can_Heart=true;
						break;
					}
				}
				
				//모든 공개된 카드를 출력한다.
				opencard.print_opencard();
				
				//우열을 비교하여 가장 높은 카드를 낸 플레이어가 모든 카드를 가져간다 
				top=opencard.check_priority();
				
				//모든 플레이어의 first 초기화
				for(int k=0;k<p_num;k++)
					player.get(k).init_first();
				
				//가장 높은 카드를 낸 플레이어가 모든 opencard를 가져감
				examin: while (true) {
				for(int i=0;i<p_num;i++) {
					if(player.get(i).order==turn%p_num) {
						cnt2++;
						if(top==cnt2) {
							player.get(i).push_dead_card(opencard);
							player.get(i).first=true;
							break examin;
						}
					}
				}
				turn++;
				}
				
				for(int i=0;i<p_num;i++) {
					player.get(i).print_dc();
				}
				
				//한 플레이어가 4개의 Q를 모았을 경우 승리
				for(int i=0;i<p_num;i++) {
					if(player.get(i).win_4Q()) {
						System.out.println(player.get(i).name+"님이 4장의 Q를 모아 승리하였습니다. 축하합니다!!!");
						break round;
					}
				}
				
				//다음 트릭으로 넘어가기
				break turn;
			}
		}	//turn
		if(player.get(0).hand.size()==0) {
			//모든 플레이어의 점수를 매긴다
			System.out.println();
			for(int i=0;i<p_num;i++) {
				player.get(i).give_score();
				System.out.println(player.get(i).name+"의 score: "+player.get(i).score);
			}
		
			//점수가 30점이 넘는 플레이어가 존재하는지 검사
			for(int i=0;i<p_num;i++) {
				if(player.get(i).score>30) {
					//가장 작은 score를 min에 저장한다.
					int min=player.get(0).score;
					System.out.println(min);
					for(int j=1;j<p_num;j++) {
						if(min>player.get(j).score) {
							min=player.get(j).score;
						}
					}
					
					//가장 작은 score를 가진 플레이어가 승리한다
					for(int j=0;j<p_num;j++) {
						if(player.get(j).score==min)
							System.out.println(player.get(j).name+"님이 승리했습니다. 축하합니다!!!");
					}
					
					break round;
				}
			}		
			
			//다음 라운드로 넘어가기
			break trick;
		}
		}	//trick
		}	//round
	}
}

//트럼프카드를 만드는 클래스
class Trump {
	public String suit;	//카드의 문양
	public String number;	//카드의 번호
	public int priority;	//카드의 우선순위
	
	Trump(String a, String b) {	//카드의 문양과 번호와 우선순위를 설정해주는 생성자
		suit=a;
		number=b;
		if(b=="A")
			priority=14;
		else if (b=="K")
			priority=13;
		else if (b=="Q")
			priority=12;
		else if (b=="J")
			priority=11;
		else
			priority=Integer.parseInt(b);
	}
}

//플레이어를 만드는 클래스
class Player {
	public String name;	//플레이어의 이름
	Player(String a) {
		name=a;	//플레이어의 이름 저장
	}
	public int error=0;	//잘못된 입력이 들어왔을 경우, 이를 확인할 수 있는 변수
	boolean first=false;	//이 플레이어가 트릭에서 제일 처음 카드를 내는 사람인지를 저장하는 변수
	public int order;	//플레이어의 플레이 순서를 나타내는 변수
	public int score=0;	//플레이어의 점수를 나타내는 변수
	public int check_win=0;	//승리를 확인할 수 있는 변수
	
	ArrayList<Trump> hand=new ArrayList<Trump>();	//플레이어의 손에 있는 카드들
	ArrayList<Trump> dead_card=new ArrayList<Trump>();	//플레이어가 가져간 카드들
	
	public void init_first() {	//first 변수를 false로 초기화하는 변수
		first=false;
	}
	
	public void print_hand() {	//플레이어의 손에 있는 카드들을 전부 출력하는 함수
		System.out.print(name+": ");
		for(int i=0;i<hand.size();i++) {
			if(i==0)
				System.out.print(hand.get(i).suit+" "+hand.get(i).number);
			else
				System.out.print(", "+hand.get(i).suit+" "+hand.get(i).number);
		}
		System.out.println();
	}
	
	public void card_draw(ArrayList<Trump> deck, int i) {	//덱에서 카드를 뽑는 함수
			hand.add(deck.get(i));	//deck.get(i)의 값을 hand에 넣고
			deck.remove(i);	//deck.get(i)의 값을 deck에서 제거한다
	}
	
	public void shuffle_hand(ArrayList<Trump> deck) {	//손에 있는 모든 카드를 덱에 다시 집어넣는 함수
		while(true) {
			if(hand.size()==0)
				break;
			
			deck.add(hand.get(0));
			hand.remove(0);
		}
	}
	
	public void shuffle_dead_card(ArrayList<Trump> deck) {	//플레이어가 가져간 카드들을 다시 덱에 섞어넣는 함수
		while(true) {
			if(dead_card.size()==0)
				break;
			
			deck.add(dead_card.get(0));
			dead_card.remove(0);
		}
	}
	
	public void play_card(Opened_card opencard, int hand_num, boolean can_Heart) {	//조건에 맞춰 플레이어가 카드를 내는 함수
		hand_num-=1;	//사용자가 입력하기 편하도록 숫자를 조정
		if(hand_num>=0&&hand_num<hand.size()) {
		if(opencard.opened_card.size()==0) {	//만약 처음 카드를 내는 사람이라면
			if(can_Heart)
				opencard.add_opencard(hand, hand_num);	
			else {
				if(hand.get(hand_num).suit.equals("Heart")) {
					System.out.println("전 트릭에 Heart가 나오지 않아 첫 카드로 Heart를 낼 수 없습니다.");
					error++;
				}
				else
					opencard.add_opencard(hand, hand_num);
			}
		}
		else	//처음 카드를 내는 사람이 아니라면
			opencard.add_opencard(hand, hand_num);
		}
		else {
			System.out.println("올바른 카드의 번호가 아닙니다. 다시 입력해주세요.");
			error++;
		}
	}
	
	public void push_dead_card(Opened_card opencard) {	//opencard에 있는 모든 카드를 dead_card에 넣는다.
		while(true) {
			if(opencard.opened_card.size()==0)
				break;
			
			dead_card.add(opencard.opened_card.get(0));
			opencard.opened_card.remove(0);
		}
	}
	
	public void print_dc() {	//각 플레이어가 가져간 카드들을 표기하는 함수
		System.out.print(name+"이(가) 가져간 카드: ");
		for(int i=0;i<dead_card.size();i++) {
			if(i==0)
				System.out.print(dead_card.get(i).suit+" "+dead_card.get(i).number);
			else
				System.out.print(", "+dead_card.get(i).suit+" "+dead_card.get(i).number);
		}
		System.out.println();
	}
	
	public void give_score() {	//각 플레이어의 점수를 매기는 함수
		for(int i=0;i<dead_card.size();i++) {
			if(dead_card.get(i).suit.equals("Heart"))
				score+=1;
			else if(dead_card.get(i).suit.equals("Spade")&&dead_card.get(i).number.equals("Q"))
				score+=3;
			else if(dead_card.get(i).suit.equals("Spade")&&dead_card.get(i).number.equals("A"))
				score+=5;
		}
	}
	
	public boolean win_4Q() {	//4개의 Q를 모았는지 확인하는 함수
		int count=0;
		for(int i=0;i<dead_card.size();i++) {
			if(dead_card.get(i).number.equals("Q"))
				count++;
		}
		
		if(count==4)
			return true;
		else
			return false;
	}
}

//플레이어들이 낸 카드들에 관련된 클래스
class Opened_card {
	ArrayList<Trump> opened_card=new ArrayList<Trump>();	//각 플레이어들이 한 장씩 낸 카드들 
	
	public void print_opencard() {	//공개된 카드가 무엇인지 출력하는 함수
		System.out.print("공개된 카드: ");
		for(int i=0;i<opened_card.size();i++) {
			if (i==0)
				System.out.print(opened_card.get(i).suit+" "+opened_card.get(i).number);
			else
				System.out.print(", "+opened_card.get(i).suit+" "+opened_card.get(i).number);
		}
		System.out.println();
	}
	
	public void add_opencard(ArrayList<Trump> hand, int i) {	//플레이어가 카드를 내는 함수
		opened_card.add(hand.get(i));	//공개된 카드에 플레이어가 낸 카드를 추가
		hand.remove(i);	//패에서 공개된 카드에 올려놓은 카드를 제거한다.
	}
	
	public int check_priority() {	//우선순위를 확인하는 함수
		int top=0;
		for(int i=1;i<opened_card.size();i++) {
			if(opened_card.get(0).suit.equals(opened_card.get(i).suit)) {	//첫 번째 opencard와 i 번째 opencard의 문양이 같다면
				if(opened_card.get(top).priority<opened_card.get(i).priority)
					top=i;
			}
		}
		return top;
	}
}