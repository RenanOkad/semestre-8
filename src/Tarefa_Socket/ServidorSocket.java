package Tarefa_Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {

    public static void main(String[] args) throws IOException {
        try (ServerSocket servidor = new ServerSocket(7000)) {
            while (true) {
                Socket conexao = servidor.accept();
                ClienteHandler clienteHandler = new ClienteHandler(conexao);
                Thread thread = new Thread(clienteHandler);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Algo errado aconteceu");
        }
    }
}