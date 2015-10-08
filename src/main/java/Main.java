import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main{

    static DataHandler dataHandler;
    static JFrame frame;
    static JTextField textField;
    static JButton addButton;
    static JButton deleteButton;
    static JLabel clockLabel;
    static JProgressBar progressBar;

    public Main(){

        dataHandler = new DataHandler();
        frame = new JFrame("title");
        frame.setSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());



        textField = new JTextField();


        addButton = new JButton("Добавить");
        addButton.addActionListener(new AddRecordsButtonActionListener());

        deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(new DeleteRecordsButtonActionListener());

        clockLabel = new JLabel();

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

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

    class AddRecordsWorker extends SwingWorker<Void, Void> {
        @Override
        protected Void doInBackground() throws Exception {
           try {
               int N = Integer.parseInt(textField.getText());
               progressBar.setValue(0);
               dataHandler.addRecords( N,progressBar);
           }catch (NumberFormatException e){
               System.out.println("This in not number");
               JOptionPane.showMessageDialog(null, "You Type in not number");
           }
            return null;

        }

        @Override
        protected void done() {
            super.done();
        }
    }

    class DeleteRecordsWorker extends SwingWorker<Void,Void>{
        @Override
        protected Void doInBackground() throws Exception {
            try {
                int N = Integer.parseInt(textField.getText());
                progressBar.setValue(0);
                dataHandler.deleteRecords(N, progressBar);
            }catch (NumberFormatException e){
                System.out.println("This in not number");
                JOptionPane.showMessageDialog(null, "You Type in not number");
            }
            return null;
        }
    }

    public class AddRecordsButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AddRecordsWorker addRecordsWorker = new AddRecordsWorker();
            addRecordsWorker.execute();

        }
    }

    public class DeleteRecordsButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            DeleteRecordsWorker deleteRecordsWorker = new DeleteRecordsWorker();
            deleteRecordsWorker.execute();
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
