package PAD_02Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class ServidorSocket {

    public static void main(String[] args) throws IOException {

        ServerSocket servidor = null;

        Socket  conexao = null;

        BufferedReader entrada;

        try{
            servidor = new ServerSocket(7000);

            conexao = servidor.accept();

            entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

            do{
                String texto = entrada.readLine();

                if(texto == null){
                    break;
                }

                System.out.println(texto);
            } while (!"sair".equals(entrada.toString()));

        }catch (IOException e){
            System.out.println("Algo errado aconteceu");
        } finally {
            Objects.requireNonNull(conexao).close();

            servidor.close();
        }

    }

}
