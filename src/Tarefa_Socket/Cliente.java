package Tarefa_Socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public void CriarCliente() {
        Scanner entrada = new Scanner(System.in);
        int texto;

        PrintStream saida;
        try (Socket cliente = new Socket("127.0.0.1", 7000)) {
            saida = new PrintStream(cliente.getOutputStream());

            do {
                System.out.println("Informe a acao que deseja realizar");
                System.out.println("0 - Sair ");
                System.out.println("1 - new ");
                System.out.println("2 - sleep ");
                System.out.println("3 - wait ");
                System.out.println("4 - notify ");
                System.out.println("5 - listar Threads ");
                System.out.println("6 - listar individuos que começam o nome com a letra R ");
                texto = entrada.nextInt();
                saida.println(texto);
                if(texto == 2 ||
                        texto == 3 ||
                        texto == 4){
                    texto = entrada.nextInt();
                    saida.println(texto);
                }
            } while (texto != 0);
        } catch (IOException ioException) {
            System.out.println("Algo errado aconteceu");
        }
    }
}
