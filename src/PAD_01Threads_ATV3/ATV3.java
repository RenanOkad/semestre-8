package PAD_01Threads_ATV3;

import javax.swing.*;

public class ATV3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContadorThread::new);
    }
}


