import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Main{


    static JFrame frame;
    static JTextField textField;
    static JButton addButton;
    static JButton deleteButton;
    static JLabel clockLabel;
    static JProgressBar progressBar;

    public Main(){
        frame = new JFrame("title");
        frame.setSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());



        textField = new JTextField();


        addButton = new JButton("Добавить");
        deleteButton = new JButton("Удалить");

        clockLabel = new JLabel();

        progressBar = new JProgressBar();

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

        TimeWorker timeWorker = new TimeWorker();
        //timeWorker.addPropertyChangeListener(this);
        timeWorker.execute();
    }

    /*@Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt);
    }*/

    class TimeWorker extends SwingWorker<Void,Void> {
        @Override
        protected Void doInBackground() throws Exception {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");

            while (true) {
                clockLabel.setText(simpleDateFormat.format(new Date()));

                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/


            }
            //return null;
        }
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main this_application = new Main();
            }
        });
    }



        /*class ClockHere implements Runnable{

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
        clockThread.start();*/


    }
