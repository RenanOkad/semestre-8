package PAD_01Threads_ATV2;

public class ATV2 {
    public static void main(String[] args) {

        Semafaro[] semaphores = new Semafaro[4];
        semaphores[0] = new Semafaro("Semafaro 1");
        semaphores[1] = new Semafaro("Semafaro 2");
        semaphores[2] = new Semafaro("Semafaro 3");
        semaphores[3] = new Semafaro("Semafaro 4");

        SemafaroThread thread = new SemafaroThread(semaphores);
        thread.start();
    }
}
