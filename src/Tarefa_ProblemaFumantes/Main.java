package Tarefa_ProblemaFumantes;


import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore tabaco = new Semaphore(0);
        Semaphore papel = new Semaphore(0);
        Semaphore fosforo = new Semaphore(0);
        Semaphore representante = new Semaphore(1);

        Thread representanteThread = new Thread(new RepresentanteCigarrosThread(tabaco, papel, fosforo, representante));
        Thread fumanteTabacoThread = new Thread(new FumanteThread("Tabaco", tabaco, representante));
        Thread fumantePapelThread = new Thread(new FumanteThread("Papel", papel, representante));
        Thread fumanteFosforoThread = new Thread(new FumanteThread("Fosforo", fosforo, representante));

        fumanteFosforoThread.start();
        fumanteTabacoThread.start();
        fumantePapelThread.start();
        representanteThread.start();
    }
}
