import java.util.Iterator;

public class TestDLL {
    public static void main(String[] args) {
        DoubleLinkedList<Integer> provaLinked = new DoubleLinkedList<>();

        provaLinked.add(22);
        provaLinked.add(30);
        provaLinked.add(31);
        provaLinked.add(32);
        provaLinked.add(6, 33);
        provaLinked.create();
        System.out.println(".get test: " + provaLinked.get(3));
        System.out.println(".lon test: " + provaLinked.lon());
        provaLinked.delete(3);
        System.out.println(".lon test2: " + provaLinked.lon());
        System.out.println(".search test: " + provaLinked.search(33));

        Iterator itLinked = provaLinked.iterator();
        while (itLinked.hasNext()){
            System.out.println(itLinked.next());
        }
    }
}