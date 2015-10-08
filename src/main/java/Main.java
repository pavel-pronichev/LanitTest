import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    static DataHandler dataHandler;
    static JFrame frame;
    static JTextField textField;
    static JButton addButton;
    static JButton deleteButton;
    static JLabel clockLabel;
    static JProgressBar progressBar;
    static Date date;
    static JLabel addLabel;

    public Main() {

        date = new Date();
        dataHandler = new DataHandler();
        frame = new JFrame("App for Lanit");
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
        addLabel = new JLabel();
        addLabel.setText("Ready");

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setValue(100);

        /*progressBar1 = new JProgressBar();
        progressBar1.setStringPainted(true);
        progressBar1.setValue(0);*/

        frame.add(textField, new GridBagConstraints(1, 1, 4, 1, 50, 3, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 5, 5));
        frame.add(addButton, new GridBagConstraints(1, 2, 3, 1, 50, 3, GridBagConstraints.NORTH,
                GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 20, 5));
        frame.add(deleteButton, new GridBagConstraints(4, 2, 1, 1, 50, 3, GridBagConstraints.NORTH,
                GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 20, 5));
        frame.add(clockLabel, new GridBagConstraints(1, 3, 1, 1, 50, 3, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 20, 5));
        frame.add(addLabel, new GridBagConstraints(2, 3, 2, 1, 50, 3, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 20, 5));
        frame.add(progressBar, new GridBagConstraints(4, 3, 1, 1, 50, 3, GridBagConstraints.SOUTH,
                GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 20, 5));


        frame.setVisible(true);
        frame.pack();

        TimeWorker timeWorker = new TimeWorker();
        //timeWorker.addPropertyChangeListener(this);
        timeWorker.execute();
    }


    class TimeWorker extends SwingWorker<Void, Void> {
        @Override
        protected Void doInBackground() throws Exception {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");

            while (true) {
                clockLabel.setText(simpleDateFormat.format(new Date()));
                Thread.sleep(1000);

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
            addLabel.setText("Добавление");
            try {
                int N = Integer.parseInt(textField.getText());
                progressBar.setValue(0);
                dataHandler.addRecords(N, progressBar);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "You type in not number");
            }
            return null;

        }

        @Override
        protected void done() {
            super.done();
            qwerty();
            addLabel.setText("Готово");

        }
    }

    private void qwerty() {
        SerializeDataHandler serializeDataHandler = new SerializeDataHandler();
        serializeDataHandler.execute();
    }

    class DeleteRecordsWorker extends SwingWorker<Void, Void> {
        @Override
        protected Void doInBackground() throws Exception {
            addLabel.setText("Удаление");
            try {
                int N = Integer.parseInt(textField.getText());
                progressBar.setValue(0);
                dataHandler.deleteRecords(N, progressBar);
            } catch (NumberFormatException e) {
                System.out.println("This in not number");
                JOptionPane.showMessageDialog(null, "You Type in not number");
            }
            return null;
        }

        @Override
        protected void done() {
            super.done();
            SerializeDataHandler serializeDataHandler = new SerializeDataHandler();
            serializeDataHandler.execute();
            addLabel.setText("Готово");
        }
    }

    class SerializeDataHandler extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            if ((new Date().getTime() - date.getTime()) > 10000) {
                synchronized (dataHandler) {
                    FileOutputStream fileOutputStream = new FileOutputStream("dataSave");
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(dataHandler.getMap());
                    fileOutputStream.close();
                    objectOutputStream.close();
                    date = new Date();

                }

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

    public class DeleteRecordsButtonActionListener implements ActionListener {
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


}
