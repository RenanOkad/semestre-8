package Tarefa_Socket;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2Socket {

    public static void main(String[] args) throws IOException {
        Cliente cliente = new Cliente();
        cliente.CriarCliente();
    }
}
