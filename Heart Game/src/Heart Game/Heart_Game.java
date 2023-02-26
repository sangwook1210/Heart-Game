package javaProject;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Heart_Game {

	public static void main(String[] args) {
		ArrayList<Trump> deck = new ArrayList<Trump>();
		
		//52���� Ʈ����ī�� ��������
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
		//52���� Ʈ����ī�� �����Ϸ�

		ArrayList <Player> player = new ArrayList<Player>();	//player ArrayList ����
		Scanner sc=new Scanner(System.in);	//Scanner ��ü ����
		Random random = new Random();	//Random ��ü ����
		
		//������ �÷����� �÷��̾��� ���� �Է¹޴´�
		int p_num=0;
		while(true) {
			System.out.print("������ �÷����� ������� ���� �Է����ּ���(4~6��): ");
			try {
				p_num=sc.nextInt();	//������ �÷����� �÷��̾��� ���� �Է¹���
				if(p_num>=4&&p_num<=6)
					break;
				else {	//4�̸� 6 �ʰ��� ���� �Է¹޾��� ���
					System.out.println("�߸��� �Է��Դϴ�. �ٽ� �Է����ּ���.\n");
					continue;
				}
			}
			catch(Exception e) {	//���� ���� �ٸ� �Է��� �޾��� ���
				sc=new Scanner(System.in);
				System.out.println("�߸��� �Է��Դϴ�. �ٽ� �Է����ּ���.\n");
				continue;
			}
		}
		
		String p_name;	//�÷��̾��� �̸��� �Է¹��� ����
		sc.nextLine();	//���๮��(����)�� �����ϱ� ���Ͽ� �߰�
		
		//��� ������� �̸��� �Է¹޴´�
		for(int i=0;i<p_num;i++) {	//�÷��̾��� �� ��ŭ �ݺ�
			System.out.print((i+1)+"��° ������� �̸��� �Է����ּ���: ");
			p_name=sc.nextLine();
			
			player.add(new Player(p_name));
		}
		System.out.println();
		
		//�÷��̾��� ������ ���Ѵ�
		System.out.println("������ �����ϱ⿡ �ռ� �÷��̾��� ������ ���ϰڽ��ϴ�.");
		for(int i=0;i<p_num;i++) {	//�÷��̾��� ������ ���Ѵ�.
			player.get(i).order=random.nextInt(p_num);	//order�� 0~get(0).player_num-1 �� �ϳ��� ������ �����Ѵ�.
			for(int j=0;j<i;j++) {
				if(player.get(i).order==player.get(j).order)	//���� ������ i�� order ���� ������ order ���� �ߺ��ȴٸ�
					i--;	//�ٽ� order���� �����Ѵ�.
			}
		}
		
		//������ �÷��̾��� ������ ����Ѵ�
		System.out.print("������ ");
		for(int i=0;i<p_num;i++) {
			for(int j=0;j<p_num;j++) {
				if(player.get(j).order==i)
					System.out.print(player.get(j).name+" ");
			}
		}
		System.out.println("������ ����� �����Դϴ�.\n");
		
		//******���� ����******
		int hand_num=0;	//�÷��̾�κ��� �� ī�带 �Է¹��� ����
		int round=0;	//�� ���������� ������ ����
		int trick=0;	//�� Ʈ�������� ������ ����
		int turn=0;	//�÷��̾��� ���ʸ� ��Ÿ���� ����
		int cnt1=0;	//�÷��̾��� ���ʰ� �Դ��� üũ�� ���� 1
		int cnt2=-1;	//�÷��̾��� ���ʰ� �Դ��� üũ�� ���� 2
		int top=0;	//opencard �� ���� ū ī���� �ε����� ������ ����
		boolean can_Heart=false;	//ù ����� ��Ʈ�� �� �� �ִ��� �˻��ϴ� ����
		Opened_card opencard=new Opened_card();	//Opened_card ArrayList ����
		
		round: while(true) {
			round++;
			trick=0;
			System.out.println("\n"+round+"���� ����\n");
			
			//������ 2���� �̻��̸� �÷��̾���� ������ �ִ� ī����� ���� �ٽ� ����ִ´�.
			if(round>=2) {
				for(int i=0;i<p_num;i++) {
					player.get(i).shuffle_dead_card(deck);
				}
			}
			
			//��� �÷��̾���� �Ѹ��� �ݵ�� Clover 2�� ������ �ְԲ� ī�带 �̴´�
			int hand_volume=52/p_num;
			System.out.println("��� �÷��̾ ���� "+hand_volume+"�徿 ī�带 �̽��ϴ�.");
			
			outer: while(true) {
				for(int i=0;i<hand_volume;i++) {	//��� �÷��̾ ���ư��� ���� hand_volume�徿 ī�带 �̴´�.
					for(int j=0;j<p_num;j++) {
						player.get(j).card_draw(deck, random.nextInt(deck.size()));
					}
				}
				
				for(int i=0;i<deck.size();i++) {	//���� ���� ���� Ŭ�ι� 2�� �ִٸ�
					if(deck.get(i).suit.equals("Clover")&&deck.get(i).number.equals("2")) {
						for(int j=0;j<p_num;j++) {	//���� ��� ī�带 �ٽ� ���� �ְ�
							player.get(j).shuffle_hand(deck);
						}
						continue outer;	//�ٽ� ī�带 �̴´�.
					}
				}
				break outer;	//���� ���� Ŭ�ι� 2�� ���ٸ� �����.
			}
				
			for(int i=0;i<p_num;i++) {	//��� �÷��̾��� �и� ����Ѵ�.
				player.get(i).print_hand();
				player.get(i).error=0;
			}
			System.out.println();
			
			//Ŭ�ι� 2�� ������ �ִ� ����� ���� ���� ī�带 ���Եȴ�.
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
			System.out.println("\n"+round+"���� "+trick+"��° Ʈ��\n");
		turn: while(true) {
			//�÷��̾��� ��
			for(int i=0;i<p_num;i++) {
				if(player.get(i).order==turn%p_num) {
					System.out.println(player.get(i).name+"�� ����");
					player.get(i).error=0;
					
					//���� ������ �÷��̾��� �п� ���� ������ ī����� ���
					player.get(i).print_hand();
					opencard.print_opencard();
					
					//���� ������ �÷��̾ �� ī�带 ����
					System.out.print("�� ī���� ��ȣ�� �Է��ϼ���(ī���� ��ȣ�� 1���� �����ؼ� ���������� ������ 1�� �����մϴ�): ");
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
			
			//�� Ʈ���� �����ٸ�
			if(cnt1==p_num) {
				//opencard�� ��Ʈ�� �ִٸ� ���� Ʈ���� ù ī��� ��Ʈ�� �� �� �ִ�
				for(int i=0;i<opencard.opened_card.size();i++) {
					if(opencard.opened_card.get(i).suit.equals("Heart")) {
						can_Heart=true;
						break;
					}
				}
				
				//��� ������ ī�带 ����Ѵ�.
				opencard.print_opencard();
				
				//�쿭�� ���Ͽ� ���� ���� ī�带 �� �÷��̾ ��� ī�带 �������� 
				top=opencard.check_priority();
				
				//��� �÷��̾��� first �ʱ�ȭ
				for(int k=0;k<p_num;k++)
					player.get(k).init_first();
				
				//���� ���� ī�带 �� �÷��̾ ��� opencard�� ������
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
				
				//�� �÷��̾ 4���� Q�� ����� ��� �¸�
				for(int i=0;i<p_num;i++) {
					if(player.get(i).win_4Q()) {
						System.out.println(player.get(i).name+"���� 4���� Q�� ��� �¸��Ͽ����ϴ�. �����մϴ�!!!");
						break round;
					}
				}
				
				//���� Ʈ������ �Ѿ��
				break turn;
			}
		}	//turn
		if(player.get(0).hand.size()==0) {
			//��� �÷��̾��� ������ �ű��
			System.out.println();
			for(int i=0;i<p_num;i++) {
				player.get(i).give_score();
				System.out.println(player.get(i).name+"�� score: "+player.get(i).score);
			}
		
			//������ 30���� �Ѵ� �÷��̾ �����ϴ��� �˻�
			for(int i=0;i<p_num;i++) {
				if(player.get(i).score>30) {
					//���� ���� score�� min�� �����Ѵ�.
					int min=player.get(0).score;
					System.out.println(min);
					for(int j=1;j<p_num;j++) {
						if(min>player.get(j).score) {
							min=player.get(j).score;
						}
					}
					
					//���� ���� score�� ���� �÷��̾ �¸��Ѵ�
					for(int j=0;j<p_num;j++) {
						if(player.get(j).score==min)
							System.out.println(player.get(j).name+"���� �¸��߽��ϴ�. �����մϴ�!!!");
					}
					
					break round;
				}
			}		
			
			//���� ����� �Ѿ��
			break trick;
		}
		}	//trick
		}	//round
	}
}

//Ʈ����ī�带 ����� Ŭ����
class Trump {
	public String suit;	//ī���� ����
	public String number;	//ī���� ��ȣ
	public int priority;	//ī���� �켱����
	
	Trump(String a, String b) {	//ī���� ����� ��ȣ�� �켱������ �������ִ� ������
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

//�÷��̾ ����� Ŭ����
class Player {
	public String name;	//�÷��̾��� �̸�
	Player(String a) {
		name=a;	//�÷��̾��� �̸� ����
	}
	public int error=0;	//�߸��� �Է��� ������ ���, �̸� Ȯ���� �� �ִ� ����
	boolean first=false;	//�� �÷��̾ Ʈ������ ���� ó�� ī�带 ���� ��������� �����ϴ� ����
	public int order;	//�÷��̾��� �÷��� ������ ��Ÿ���� ����
	public int score=0;	//�÷��̾��� ������ ��Ÿ���� ����
	public int check_win=0;	//�¸��� Ȯ���� �� �ִ� ����
	
	ArrayList<Trump> hand=new ArrayList<Trump>();	//�÷��̾��� �տ� �ִ� ī���
	ArrayList<Trump> dead_card=new ArrayList<Trump>();	//�÷��̾ ������ ī���
	
	public void init_first() {	//first ������ false�� �ʱ�ȭ�ϴ� ����
		first=false;
	}
	
	public void print_hand() {	//�÷��̾��� �տ� �ִ� ī����� ���� ����ϴ� �Լ�
		System.out.print(name+": ");
		for(int i=0;i<hand.size();i++) {
			if(i==0)
				System.out.print(hand.get(i).suit+" "+hand.get(i).number);
			else
				System.out.print(", "+hand.get(i).suit+" "+hand.get(i).number);
		}
		System.out.println();
	}
	
	public void card_draw(ArrayList<Trump> deck, int i) {	//������ ī�带 �̴� �Լ�
			hand.add(deck.get(i));	//deck.get(i)�� ���� hand�� �ְ�
			deck.remove(i);	//deck.get(i)�� ���� deck���� �����Ѵ�
	}
	
	public void shuffle_hand(ArrayList<Trump> deck) {	//�տ� �ִ� ��� ī�带 ���� �ٽ� ����ִ� �Լ�
		while(true) {
			if(hand.size()==0)
				break;
			
			deck.add(hand.get(0));
			hand.remove(0);
		}
	}
	
	public void shuffle_dead_card(ArrayList<Trump> deck) {	//�÷��̾ ������ ī����� �ٽ� ���� ����ִ� �Լ�
		while(true) {
			if(dead_card.size()==0)
				break;
			
			deck.add(dead_card.get(0));
			dead_card.remove(0);
		}
	}
	
	public void play_card(Opened_card opencard, int hand_num, boolean can_Heart) {	//���ǿ� ���� �÷��̾ ī�带 ���� �Լ�
		hand_num-=1;	//����ڰ� �Է��ϱ� ���ϵ��� ���ڸ� ����
		if(hand_num>=0&&hand_num<hand.size()) {
		if(opencard.opened_card.size()==0) {	//���� ó�� ī�带 ���� ����̶��
			if(can_Heart)
				opencard.add_opencard(hand, hand_num);	
			else {
				if(hand.get(hand_num).suit.equals("Heart")) {
					System.out.println("�� Ʈ���� Heart�� ������ �ʾ� ù ī��� Heart�� �� �� �����ϴ�.");
					error++;
				}
				else
					opencard.add_opencard(hand, hand_num);
			}
		}
		else	//ó�� ī�带 ���� ����� �ƴ϶��
			opencard.add_opencard(hand, hand_num);
		}
		else {
			System.out.println("�ùٸ� ī���� ��ȣ�� �ƴմϴ�. �ٽ� �Է����ּ���.");
			error++;
		}
	}
	
	public void push_dead_card(Opened_card opencard) {	//opencard�� �ִ� ��� ī�带 dead_card�� �ִ´�.
		while(true) {
			if(opencard.opened_card.size()==0)
				break;
			
			dead_card.add(opencard.opened_card.get(0));
			opencard.opened_card.remove(0);
		}
	}
	
	public void print_dc() {	//�� �÷��̾ ������ ī����� ǥ���ϴ� �Լ�
		System.out.print(name+"��(��) ������ ī��: ");
		for(int i=0;i<dead_card.size();i++) {
			if(i==0)
				System.out.print(dead_card.get(i).suit+" "+dead_card.get(i).number);
			else
				System.out.print(", "+dead_card.get(i).suit+" "+dead_card.get(i).number);
		}
		System.out.println();
	}
	
	public void give_score() {	//�� �÷��̾��� ������ �ű�� �Լ�
		for(int i=0;i<dead_card.size();i++) {
			if(dead_card.get(i).suit.equals("Heart"))
				score+=1;
			else if(dead_card.get(i).suit.equals("Spade")&&dead_card.get(i).number.equals("Q"))
				score+=3;
			else if(dead_card.get(i).suit.equals("Spade")&&dead_card.get(i).number.equals("A"))
				score+=5;
		}
	}
	
	public boolean win_4Q() {	//4���� Q�� ��Ҵ��� Ȯ���ϴ� �Լ�
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

//�÷��̾���� �� ī��鿡 ���õ� Ŭ����
class Opened_card {
	ArrayList<Trump> opened_card=new ArrayList<Trump>();	//�� �÷��̾���� �� �徿 �� ī��� 
	
	public void print_opencard() {	//������ ī�尡 �������� ����ϴ� �Լ�
		System.out.print("������ ī��: ");
		for(int i=0;i<opened_card.size();i++) {
			if (i==0)
				System.out.print(opened_card.get(i).suit+" "+opened_card.get(i).number);
			else
				System.out.print(", "+opened_card.get(i).suit+" "+opened_card.get(i).number);
		}
		System.out.println();
	}
	
	public void add_opencard(ArrayList<Trump> hand, int i) {	//�÷��̾ ī�带 ���� �Լ�
		opened_card.add(hand.get(i));	//������ ī�忡 �÷��̾ �� ī�带 �߰�
		hand.remove(i);	//�п��� ������ ī�忡 �÷����� ī�带 �����Ѵ�.
	}
	
	public int check_priority() {	//�켱������ Ȯ���ϴ� �Լ�
		int top=0;
		for(int i=1;i<opened_card.size();i++) {
			if(opened_card.get(0).suit.equals(opened_card.get(i).suit)) {	//ù ��° opencard�� i ��° opencard�� ������ ���ٸ�
				if(opened_card.get(top).priority<opened_card.get(i).priority)
					top=i;
			}
		}
		return top;
	}
}