import javax.swing.*;
import java.io.Serializable;
import java.util.*;

public class DataHandler implements Serializable {

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
            if (set.size() < N) {
                N = set.size();
            }

            for (int i = 1; i <= N; i++) {
                map.remove(set.iterator().next());
                progressBar.setValue(i * 100 / N);
            }
        }
    }
}
