import java.util.Iterator;

public class TestDLL {
    public static void main(String[] args) {
        DoubleLinkedList<Integer> provaLinked = new DoubleLinkedList<>();

        provaLinked.add(22);
        provaLinked.add(30);
        provaLinked.add(30);
        provaLinked.add(30);

        Iterator iterLinked = provaLinked.iterator();

        while (iterLinked.hasNext()){
            System.out.println(iterLinked.next());
        }
    }
}