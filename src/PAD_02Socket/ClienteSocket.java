package PAD_02Socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class ClienteSocket {

    public static void main(String[] args) throws IOException {

        Scanner entrada = new Scanner(System.in);

        String texto;

        Socket cliente = null;

        PrintStream saida;

        try{
            cliente =  new Socket("127.0.0.1", 7000);

            saida = new PrintStream(cliente.getOutputStream());

            do {
                texto = entrada.nextLine();

                saida.println(texto);
            } while(!"sair".equals(texto));
        } catch (IOException ioException){
            System.out.println("Algo errado aconteceu");
        } finally {
            Objects.requireNonNull(cliente).close();
        }
    }
}
