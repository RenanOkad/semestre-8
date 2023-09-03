package Tarefa_ProblemaFumantes;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class RepresentanteCigarrosThread extends Thread {

    private Semaphore tabacoSemphore;
    private Semaphore papelSemphore;
    private Semaphore fosforoSemphore;
    private Semaphore representanteSemphore;

    public RepresentanteCigarrosThread(Semaphore tabacoSemphore,Semaphore papelSemphore, Semaphore fosforoSemphore,Semaphore representanteSemphore){
        this.fosforoSemphore = fosforoSemphore;
        this.representanteSemphore = representanteSemphore;
        this.papelSemphore = papelSemphore;
        this.tabacoSemphore = tabacoSemphore;
    }

    @Override
    public void run() {
        try {
            while (true) {
                representanteSemphore.acquire();

                int random = new Random().nextInt(3);

                if (random == 0) {
                    System.out.println("Representante colocou tabaco e papel na mesa");
                    fosforoSemphore.release();
                } else if (random == 1) {
                    System.out.println("Representante colocou tabaco e fosforo na mesa");
                    papelSemphore.release();
                } else {
                    System.out.println("Representante colocou papel e fosforo na mesa");
                    tabacoSemphore.release();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

