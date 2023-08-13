package PAD_01Threads_ATV3;

import javax.swing.*;
import java.awt.*;

public class ContadorThread extends JFrame{

    private JLabel contadorALabel;
    private JLabel contadorBLabel;
    
    private JButton startButtonA;
    private JButton startButtonB;
    private JButton stopButtonA;
    private JButton stopButtonB;
    private JButton pauseButtonA;
    private JButton pauseButtonB;
    
    private int contadorAValue = 0;
    private int contadorBValue = 0;
    
    private boolean isRunningA = false;
    private boolean isRunningB = false;
    
    private Thread contadorThreadA;
    private Thread contadorThreadB;
    
    public ContadorThread(){
        setTitle("Contadores");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,3));
        
        contadorALabel = new JLabel("Contador A: 0");
        contadorBLabel = new JLabel("Contador B: 0");
        
        startButtonA = new JButton("Iniciar");
        startButtonB = new JButton("Iniciar");

        pauseButtonA = new JButton("Pausar");
        pauseButtonB = new JButton("Pausar");

        stopButtonA = new JButton("Encerrar");
        stopButtonB = new JButton("Encerrar");

        startButtonA.addActionListener(e -> startContadorA());
        startButtonB.addActionListener(e -> startContadorB());

        stopButtonA.addActionListener(e -> stopContadorA());
        stopButtonB.addActionListener(e -> stopContadorB());

        pauseButtonA.addActionListener(e -> pauseContadorA());
        pauseButtonB.addActionListener(e -> pauseContadorB());

        add(contadorALabel);
        add(startButtonA);
        add(pauseButtonA);

        add(contadorBLabel);
        add(startButtonB);
        add(pauseButtonB);

        add(new JLabel());

        add(stopButtonA);

        add(new JLabel());
        add(new JLabel());

        add(stopButtonB);

        setVisible(true);
    }

    private void startContadorA(){
        if(!isRunningA){
            isRunningA = true;
            contadorThreadA = new Thread(() -> {
                while(isRunningA){
                    try{
                        Thread.sleep(500);
                        contadorAValue++;
                        SwingUtilities.invokeLater(() -> {
                            contadorALabel.setText("Contador A: " + contadorAValue);
                        });
                    } catch (InterruptedException e ){
                        e.printStackTrace();
                    }
                }
            });
            contadorThreadA.start();
        }
    }

    private void pauseContadorA(){
        isRunningA = false;
    }

    private void stopContadorA(){
        isRunningA = false;
        contadorThreadA = null;
        contadorAValue = 0;
        contadorALabel.setText("Contador A: "+ contadorAValue);
    }

    private void startContadorB(){
        if(!isRunningB){
            isRunningB = true;
            contadorThreadB = new Thread(() -> {
                while(isRunningB){
                    try{
                        Thread.sleep(500);
                        contadorBValue++;
                        SwingUtilities.invokeLater(() -> {
                            contadorBLabel.setText("Contador B: " + contadorBValue);
                        });
                    } catch (InterruptedException e ){
                        e.printStackTrace();
                    }
                }
            });
            contadorThreadB.start();
        }
    }


    private void pauseContadorB(){
        isRunningB = false;
    }

    private void stopContadorB(){
        isRunningB = false;
        contadorThreadB = null;
        contadorBValue = 0;
        contadorBLabel.setText("Contador B: "+ contadorBValue);
    }

}
