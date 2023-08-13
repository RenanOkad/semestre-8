package PAD_01Threads_ATV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATV1 {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int numCorredores;

		System.out.println("Informe quantos corredores deseja na corrida:");
		numCorredores = in.nextInt();

		List<String> nomeCorredoresInscritos = new ArrayList<>();

		for (int x = 1; x <= numCorredores; x++) {
			System.out.println("Informe o nome do corredor de numero " + (x));
			nomeCorredoresInscritos.add(in.next());
		}

		nomeCorredoresInscritos.forEach(t -> {
			Thread thread = new Thread(new CorredorThread(t));
			thread.start();
		});
		in.close();
	}
}
