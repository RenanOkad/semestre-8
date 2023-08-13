package PAD_01Threads;

public class ThreadJ2 extends Thread {
	@Override
	public void run() {
		for (int x = 0; x < 3; x++) {
			System.out.println("T2: " + x + " | ");
			Thread.currentThread();
			Thread.yield();
		}
	}
}
