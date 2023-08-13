package PAD_01Threads_ATV2;

public class SemafaroThread extends Thread {

    private Semafaro[] semafaros;

    public SemafaroThread(Semafaro[] semafaros) {
        this.semafaros = semafaros;
    }

    @Override
    public void run() {
        int index = 0;
        while (true) {
            semafaros[index].abrir();
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semafaros[index].fechar();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            index = (index + 1) % semafaros.length;
        }
    }

}
