package Tarefa_Socket;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClienteHandler implements Runnable {
    private final Socket conexao;
    private final BufferedReader entrada;
    private final PrintStream saida;
    private final ThreadSocket thread;
    private final String inputImagePath = "Documentos/arquivosNomes";
    private final String outputImagePath = "Documentos/arquivosNomesResultados/nomesLidos.txt";
    private final Lock lock = new ReentrantLock();

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
            List<String> strings = new ArrayList<>();

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
                    if (thread.existeThreads()) {
                        processarNomes(arquivos, strings);
                    }
                } else if (texto == 7) {
                    limparArquivoDeSaida();
                }
            } while (texto != 0);
        } catch (IOException | InterruptedException e) {
            System.out.println("Algo errado aconteceu");
        } finally {
            try {
                conexao.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void limparArquivoDeSaida() {
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(outputImagePath));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(outputImagePath));

            String linha;
            while ((linha = leitor.readLine()) != null) {
                escritor.write(linha);
                escritor.newLine();
            }
            leitor.close();
            escritor.close();

            System.out.println("Arquivo limpo com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processarNomes(File[] arquivos, List<String> strings) {
        if (arquivos != null) {
            StringWriter writer = new StringWriter();

            for (File arquivo : arquivos) {
                if (arquivo.isFile() && arquivo.getName().endsWith(".txt")) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                        String linha;
                        while ((linha = reader.readLine()) != null) {
                            if (linha.toUpperCase().startsWith("R")) {
                                validarNomesExistentes(strings);
                                if (!strings.contains(linha)) {
                                    writer.write(linha);
                                    writer.write("\n");
                                    lock.lock();
                                    try {
                                        escreverNoArquivo(writer.toString());
                                    } finally {
                                        lock.unlock();
                                    }
                                    System.out.println("Nome: " + linha + " inserido na lista de nomes!");
                                    Thread.sleep(3000);
                                }
                            }
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void validarNomesExistentes(List<String> strings) {
        try (BufferedReader readerNomesLidos = new BufferedReader(new FileReader(outputImagePath))) {
            String linha;
            while ((linha = readerNomesLidos.readLine()) != null) {
                if (!strings.contains(linha)) {
                    strings.add(linha);
                } else {
                    //System.out.println("Nome: " + linha + " já foi inserido anteriormente!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void escreverNoArquivo(String conteudo) {
        try (FileWriter fileWriter = new FileWriter(outputImagePath, true)) {
            fileWriter.write(conteudo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}