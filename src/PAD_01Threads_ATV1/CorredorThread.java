package PAD_01Threads_ATV1;

import java.util.Random;

public class CorredorThread extends Thread {

    int metrosPercorrido = 0;
    String corredorNome;

    public CorredorThread(String nomeCorredor) {
        this.corredorNome = nomeCorredor;
    }

    @Override
    public void run() {
        while (this.metrosPercorrido != 1000 && this.metrosPercorrido <= 1000) {
            System.out.println("Corredor " + corredorNome + " está correndo! " + "E ja percorreu: " + metrosPercorrido
                    + " metros");

            Random random = new Random();
            int numeroAleatorio = random.nextInt(90) + 10;
            this.metrosPercorrido += numeroAleatorio;
            Thread.currentThread();
            Thread.yield();
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        System.out.println(
                "Corredor " + corredorNome + " está correndo! " + "E ja percorreu: " + metrosPercorrido + " metros");
    }

}
