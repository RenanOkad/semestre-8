package Tarefa_ProblemaFumantes;

import java.util.concurrent.Semaphore;

public class FumanteThread extends Thread {
    private String ingrediente;
    private Semaphore ingredienteSemaphore;
    private Semaphore representanteSemaphore;

    public FumanteThread(String ingrediente, Semaphore ingredienteSemaphore, Semaphore representanteSemaphore) {
        this.ingredienteSemaphore = ingredienteSemaphore;
        this.ingrediente = ingrediente;
        this.representanteSemaphore = representanteSemaphore;
    }

    @Override
    public void run() {
        try {
            while (true){
                ingredienteSemaphore.acquire();

                System.out.println("O fumante que tem o ingredinte " + ingrediente + " adquiriu e está fazendo um cigarro");
                Thread.sleep(1000);
                System.out.println("O fumante está fumando");
                System.out.println("O fumante terminou de fumar\n");

                representanteSemaphore.release();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
