package jTable;

public class ThreadTest {

	static int breads = 20000;
	static int[] steal = new int[3];
	public static void main(String[] args) throws InterruptedException {
		String[] thiefName = {"A","B","C"};
		
		for(int i=0; i<3; i++) {
			Thread thief = new Steal(i);
			thief.start();
			thief.join();
		}
		
		System.out.println("now breads:" + breads);
		for(int i=0; i<3; i++) {
			System.out.println(thiefName[i] + ":" + steal[i]);
		}
	}
}
class Steal extends Thread{
	int name;
	Steal(int number){
		this.name = number;
	}
	
	public void run() {
		int stealed = (int)(Math.random()*ThreadTest.breads);
		ThreadTest.steal[name] = stealed;
		ThreadTest.breads -= stealed;
	}
}
