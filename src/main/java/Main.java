import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("title");
        frame.setSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());



        JTextField textField = new JTextField();


        JButton addButton = new JButton("Добавить");
        JButton deleteButton = new JButton("Удалить");

        JLabel clockLabel = new JLabel();

        JProgressBar progressBar = new JProgressBar();

        frame.add(textField, new GridBagConstraints(1,1,2,1,50,3,GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL,new Insets(1,1,1,1),5,5));
        frame.add(addButton, new GridBagConstraints(1, 2, 1, 1, 50, 3, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 20, 5));
        frame.add(deleteButton, new GridBagConstraints(2, 2, 1, 1, 50, 3, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 20, 5));
        frame.add(clockLabel, new GridBagConstraints(1, 3, 1, 1, 50, 3, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 20, 5));
        frame.add(progressBar, new GridBagConstraints(2, 3, 1, 1, 50, 3, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 20, 5));

        frame.setVisible(true);
        frame.pack();



        class ClockHere implements Runnable{

            private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
            @Override
            public void run() {
                while (true){
                    clockLabel.setText(simpleDateFormat.format(new Date()));

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        Thread clockThread = new Thread(new ClockHere());
        clockThread.start();


    }
}