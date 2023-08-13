package PAD_01Threads;

public class MainImprimir1 {

	public static void main(String[] args) {
		new Thread(new Imprimir1("A")).start();
		new Thread(new Imprimir1("B")).start();
	}

}
