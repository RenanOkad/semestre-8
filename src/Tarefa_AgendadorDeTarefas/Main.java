package Tarefa_AgendadorDeTarefas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        String inputImagePathSaida = "libs/arquivosaida.txt";

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputImagePathSaida))) {
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int[] cont = {0};
        executor.scheduleAtFixedRate(() -> {

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputImagePathSaida, true))) {
                bufferedWriter.write("A primeira agenda passou aqui pela "+cont[0]+"° vez!");
                bufferedWriter.newLine();
                cont[0]++;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }, 0, 1, TimeUnit.SECONDS);

        final int[] cont2 = {0};
        executor.scheduleAtFixedRate(() -> {

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputImagePathSaida, true))) {
                bufferedWriter.write("A segunda agenda passou aqui pela "+cont2[0]+"° vez!");
                bufferedWriter.newLine();
                cont2[0]++;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }, 0, 3, TimeUnit.SECONDS);
    }
}