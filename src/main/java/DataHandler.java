import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class DataHandler implements Serializable{

    private static TreeMap<ComplexKey,Double> map;
    public DataHandler(){
        map = new TreeMap<>();
    }

    public static void addRecords(int N){
        Random random = new Random();
       synchronized (map) {
           for (int i = 0; i < N; i++) {
               map.put(new ComplexKey(), random.nextDouble());
           }
       }
    }

    public static void deleteRecords(int N){

        Set<ComplexKey> set = new HashSet<>();
        synchronized (map) {
            set.addAll(map.keySet());
            if (set.size() < N) {
                N = set.size();
            }

            for (int i = 0; i < N; i++) {
                map.remove(set.iterator().next());
            }
        }
    }
}
