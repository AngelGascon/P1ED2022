import java.util.Iterator;

public class TestDLL {
    public static void main(String[] args) {
        DoubleLinkedList<Citizen> provLinked = new DoubleLinkedList<>();
        Citizen num1 = new Citizen("Maria", "Garcia", "334");
        Citizen num2 = new Citizen("Alvaro", "Martin", "124");
        Citizen num3 = new Citizen("Alfred", "Tomas", "204");
        Citizen num4 = new Citizen("Marta", "Vent", "112");

        provLinked.add(num1);
        provLinked.add(num2);
        provLinked.add(num3);
        provLinked.add(num4);
        provLinked.add(3, num1);

        System.out.println(".get test: " + provLinked.get(3));
        System.out.println(".lon test: " + provLinked.lon());
        provLinked.delete(3);
        System.out.println(".lon test2: " + provLinked.lon());
        System.out.println(".search test: " + provLinked.search(num2));

        for (Citizen x:provLinked) {
            System.out.println(x);
        }
    }
}