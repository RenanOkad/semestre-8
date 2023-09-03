package PAD_02Banheiro;

import java.util.concurrent.Semaphore;

public class Banheiro {

    private Semaphore semaphore = new Semaphore(2);

    public void fazNumero1(){
        String nome = Thread.currentThread().getName();
        try{
            semaphore.acquire();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println( nome + " entrando no banheiro");
        System.out.println( nome + " iniciando atividade rapida");
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(nome + " terminou atividade rapida");
        System.out.println(nome + " dando descarga");
        System.out.println(nome + " lavando a mao");
        System.out.println(nome + " saindo do banheiro");
        semaphore.release();

    }

    public void fazNumero2(){
        String nome = Thread.currentThread().getName();
        try{
            semaphore.acquire();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println( nome + " entrando no banheiro");
        System.out.println( nome + " iniciando atividade rapida");
        try{
            Thread.sleep(6000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(nome + " terminou atividade rapida");
        System.out.println(nome + " dando descarga");
        System.out.println(nome + " lavando a mao");
        System.out.println(nome + " saindo do banheiro");
        semaphore.release();

    }



}
