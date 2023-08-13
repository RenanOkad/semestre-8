package PAD_01Threads;

class Imprimir1 implements Runnable {
	String str;

	public Imprimir1(String str) {
		this.str = str;
	}

	public void run() {
		for (int x = 0; x < 10; x++) {
			System.out.print(str);
			Thread.currentThread();
			Thread.yield();
		}
	}
}
