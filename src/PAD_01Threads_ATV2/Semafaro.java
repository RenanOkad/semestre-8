package PAD_01Threads_ATV2;

public class Semafaro {
    private String nome;
    private boolean estaAberto = false;


    public Semafaro(String nome) {
        this.nome = nome;
    }

    public synchronized void abrir(){
        estaAberto = true;
        System.out.println(nome + " está aberto!");
        notifyAll();
    }

    public synchronized void fechar(){
        estaAberto = false;
        System.out.println(nome + " está fechado!");
    }

    public synchronized void esperar() throws InterruptedException{
        while (!estaAberto){
            wait();
        }
    }

}
