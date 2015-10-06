import java.io.Serializable;
import java.util.*;

public class ComplexKey implements Serializable, Comparable{
    public long part1 = 0;
    public String part2  = "";

    public ComplexKey(){
        this.part1 = randPart1();
        this.part2 = randPart2();

    }

    /*public ComplexKey(long numb, String str){
        this.part1 = numb;
        this.part2 = str;
    }*/



    private long randPart1(){
        Random random = new Random();
        part1 = random.nextLong();
        //System.out.println(part1);
        return part1;
    }

    private String randPart2(){
        StringBuilder randString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i<5; i++){
            randString.append((char)(random.nextInt(26)+'a'));
        }
        return randString.toString();
    }

    @Override
    public String toString() {
        return this.part1+"  "+ this.part2;
    }

    @Override
    public int compareTo(Object obj) {
        ComplexKey entry = (ComplexKey)obj;

        if(part1!=entry.part1){
            if(part1<entry.part1){
                return -1;
            }else if(part1>entry.part1){
                return 1;
            }
        }

        long result = part1 - entry.part1;

        if(result!=0){
            return (int)(result/Math.abs(result));
        }

        int res = part2.compareTo(entry.part2);

        if(res!=0){
            return res;
        }

        return 0;
    }


    public static void main(String[] args){
       /*ComplexKey complexKey =  new ComplexKey();
        System.out.println(complexKey.getPart1());
        System.out.println(complexKey.getPart2());*/



        Random random = new Random();
        Map<ComplexKey,Double> ex = new TreeMap<ComplexKey,Double>();

        for (int i = 0; i<10 ; i++){
            ex.put(new ComplexKey(),random.nextDouble());
        }
        /*ex.put(new ComplexKey(-64296425664564535L, "sgdgd"),random.nextDouble());
        ex.put(new ComplexKey(6684568476464566L, "dhkdd"),random.nextDouble());
        ex.put(new ComplexKey(6684568646465L, "ahkdd"),random.nextDouble());
        ex.put(new ComplexKey(35685464645665L, "dhghe"),random.nextDouble());
        ex.put(new ComplexKey(-8456843446465L, "qwfjd"),random.nextDouble());
        ex.put(new ComplexKey(-8456843446465L, "awfjd"),random.nextDouble());*/

        for(Map.Entry x : ex.entrySet()){
            System.out.println(x.getKey() + "   "+ x.getValue());
        }
    }
}