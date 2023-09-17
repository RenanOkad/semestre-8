package Tarefa_Socket;

import java.io.*;
import java.net.Socket;

public class ClienteHandler implements Runnable {
    private final Socket conexao;
    private final BufferedReader entrada;
    private final PrintStream saida;
    private final ThreadSocket thread;
    private final String inputImagePath = "Documentos/arquivosNomes";

    public ClienteHandler(Socket conexao) throws IOException {
        this.conexao = conexao;
        this.entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        this.saida = new PrintStream(conexao.getOutputStream());
        this.thread = new ThreadSocket();
    }

    @Override
    public void run() {
        try {
            int texto;
            int threadCount = 1;
            File pasta = new File(inputImagePath);
            File[] arquivos = pasta.listFiles();

            do {
                texto = Integer.parseInt(entrada.readLine());
                if (texto == 1) {
                    int i = threadCount++;
                    thread.criarThread(String.valueOf(i));
                    saida.println("Thread de identificador " + i + " criada!");
                } else if (texto == 2) {
                    thread.listarThreads();
                    saida.println("Informe qual Thread deseja dar um Sleep");
                    int i = Integer.parseInt(entrada.readLine());
                    thread.ecerrarThread(String.valueOf(i));
                } else if (texto == 3) {
                    thread.listarThreads();
                    saida.println("Informe qual Thread deseja dar um Wait");
                    int i = Integer.parseInt(entrada.readLine());
                    thread.waitThread(String.valueOf(i));
                } else if (texto == 4) {
                    thread.listarThreads();
                    saida.println("Informe qual Thread deseja dar um Notify");
                    int i = Integer.parseInt(entrada.readLine());
                    thread.notifyThread(String.valueOf(i));
                } else if (texto == 5) {
                    thread.listarThreads();
                } else if (texto == 6) {
                    processarNomesR(arquivos);
                }
                } while (texto != 0) ;
            } catch(IOException | InterruptedException e){
                System.out.println("Algo errado aconteceu");
            } finally{
                try {
                    conexao.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    private void processarNomesR(File[] arquivos) {
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile() && arquivo.getName().endsWith(".txt")) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                        String linha;
                        while ((linha = reader.readLine()) != null) {
                            if (linha.toUpperCase().startsWith("R")) {
                                System.out.println(linha);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}