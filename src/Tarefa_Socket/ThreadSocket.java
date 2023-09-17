package Tarefa_Socket;

import java.util.HashMap;
import java.util.Map;

public class ThreadSocket {

    private Map<String, Thread> threads = new HashMap<>();
    private final Object monitor = new Object();


    public void criarThread(String identificador) {
        System.out.println("Executando a Thread: " + identificador);
        Thread novaThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });
        threads.put(identificador, novaThread);
        novaThread.start();
    }

    public void ecerrarThread(String identificador) {
        Thread thread = threads.get(identificador);
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
            System.out.println("Thread de identificador: " + identificador + ", encerrado!");
        } else {
            System.out.println("Ocorreu um erro, Não foi possivel encerrar a Thread!");
        }
    }

    public void listarThreads() {
        System.out.println("Listando Threads existentes na lista: ");
        for (Map.Entry<String, Thread> threads1 : threads.entrySet()) {
            System.out.println("Thread de identificador  " + threads1.getKey());
        }
    }

    public void waitThread(String identificador) throws InterruptedException {
        Thread thread = threads.get(identificador);
        synchronized (thread){
            if (thread.isAlive()) {
                thread.wait(1000);
                System.out.println("Thread de identificador: " + identificador + ", esta no wait!");
            } else {
                System.out.println("Ocorreu um erro, Não foi possivel encerrar a Thread!");
            }
        }
    }

    public void notifyThread(String identificador) {
        synchronized (monitor){
            Thread thread = threads.get(identificador);
            if (thread != null && thread.isAlive()) {
                monitor.notify();
                System.out.println("Thread de identificador: " + identificador + ", deu um Notify!");
            } else {
                System.out.println("Ocorreu um erro, Não foi possivel encerrar a Thread!");
            }
        }
    }
}
