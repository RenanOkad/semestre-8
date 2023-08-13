package PAD_01Threads;

public class MainJ1J2_1 {

	public static void main(String[] args) {
		 ThreadJ1 t1 = new ThreadJ1();
		ThreadJ2 t2 = new ThreadJ2();
		t1.start();
		t2.start();
		System.out.println(" Main Finalizado ");

	}

}
