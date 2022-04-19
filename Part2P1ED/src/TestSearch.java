import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class TestSearch {
    public static void main(String[] args) throws FileNotFoundException {
        DoubleLinkedList<Integer> DLL = new DoubleLinkedList<>();
        ArrayList<Integer> desviations = new ArrayList<>();

        Random rand = new Random(); //instance of random class
        File csv, csvf = new File("DLL.csv");
        PrintWriter out, outf = new PrintWriter(csvf);

        int adder,current,currentCost,mitj;
        double desv;
        for(int size = 1000; size<=50000; size = size + 1000){ //Loops 1000...50000

            DLL.create();
            csv = new File("DLL"+size+".csv");
            out = new PrintWriter(csv);
            mitj=0;desv=0;

            for(adder=0; adder<size; adder++){ DLL.add(rand.nextInt(size/2));} //Adds Integers to list

            out.println("DLL size: "+size+"\n------------------------------------------------------------------------------------------------------------");

            for(adder=0; adder<size; adder++){  //Search
                current = rand.nextInt(size/2);
                currentCost = DLL.search(current);
                desviations.add(currentCost);
                mitj = mitj + currentCost;
                out.println("Search,"+current+",cost,"+currentCost);
            }
            mitj = mitj/size;
            for (Integer aux:desviations) {
                desv = desv+((aux-mitj)*(aux-mitj));
            }
            desv = Math.sqrt(desv/(size-1));
            outf.println(size+","+mitj+","+desv);
            out.close();
        }
        outf.close();
///////////////////////
        HashTable<Integer,Integer> HashT = new HashTable<>();
        ArrayList<Integer> desviationsh = new ArrayList<>();

        csvf = new File("HashTable.csv");
        outf = new PrintWriter(csvf);


        for(int size = 1000; size<=50000; size = size + 1000){ //Loops 1000...50000

            HashT.create();
            csv = new File("HashTable"+size+".csv");
            out = new PrintWriter(csv);
            mitj=0;desv=0;

            for(adder=0; adder<size; adder++){ HashT.insert(rand.nextInt(size/2), rand.nextInt(size/2));} //Adds Integers to list

            out.println("HashTable size: "+size+"\n------------------------------------------------------------------------------------------------------------");

            for(adder=0; adder<size; adder++){  //Search
                current = rand.nextInt(size/2);
                currentCost = HashT.search(current);
                desviationsh.add(currentCost);
                mitj = mitj + currentCost;
                out.println("Search,"+current+",cost,"+currentCost);
            }
            mitj = mitj/size;
            for (Integer aux:desviationsh) {
                desv = desv+((aux-mitj)*(aux-mitj));
            }
            System.out.println(desv);
            desv = Math.sqrt(desv/(size-1));
            System.out.println(desv);
            outf.println(size+","+mitj+","+desv);
            out.close();
        }
        outf.close();
    }
}
