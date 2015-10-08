import javax.swing.*;
import java.io.*;
import java.util.*;

public class DataHandler implements Externalizable {

    private static TreeMap<ComplexKey, Double> map;

    public DataHandler() {
        map = new TreeMap<>();
    }

    public static void addRecords(int N, JProgressBar progressBar) {
        Random random = new Random();
        synchronized (map) {
            for (int i = 1; i <= N; i++) {
                map.put(new ComplexKey(), random.nextDouble());
                progressBar.setValue(i * 100 / N);
            }
        }
    }

    public static void deleteRecords(int N, JProgressBar progressBar) {

        Set<ComplexKey> set = new HashSet<>();

        synchronized (map) {
            set.addAll(map.keySet());
            Iterator<ComplexKey> it = set.iterator();
            if (set.size() < N) {
                N = set.size();
            }

            for (int i = 1; i <= N; i++) {
                map.remove(it.next());
                it.hasNext();
                progressBar.setValue(i * 100 / N);
            }
        }
    }

    public static TreeMap<ComplexKey, Double> getMap() {
        return map;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        //for(int )

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }

    /*public static void serializeRecords(JProgressBar progressBar) throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream("dataSave");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(dataHandler);
        fileOutputStream.close();
        objectOutputStream.close();
    }*/
}
