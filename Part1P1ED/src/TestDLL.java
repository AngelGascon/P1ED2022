public class TestDLL {
    public static void main(String[] args) {
        DoubleLinkedList<Citizen> provLinked = new DoubleLinkedList<>();
        Citizen num1 = new Citizen("Maria", "Garcia", "334");
        Citizen num2 = new Citizen("Alvaro", "Martin", "124");
        Citizen num3 = new Citizen("Alfred", "Tomas", "204");
        Citizen num4 = new Citizen("Marta", "Vent", "110");
        Citizen num5 = new Citizen("Ruben", "Roda", "112");
        Citizen num6 = new Citizen("Gemma", "Reverte", "122");
        Citizen num7 = new Citizen("Marta", "Sancho", "134");
        Citizen num8 = new Citizen("Daniel", "Rius", "178");
        Citizen num9 = new Citizen("Àlex", "Casanova", "200");
        Citizen num10 = new Citizen("Pere", "Rullo", "300");
        //adding citizens
        provLinked.add(num1);
        provLinked.add(num2);
        provLinked.add(num3);
        provLinked.add(num4);
        provLinked.add(num5);
        provLinked.add(num6);
        provLinked.add(num7);
        provLinked.add(num8);
        provLinked.add(num9);
        provLinked.add(num10);
        provLinked.add(0, num1);
        //Printing DLL
        System.out.println("Printing DoubleLinkedList, Expected result: Every citizen + Maria at position 0 -> Maria(LastAdd), Maria, Alvaro, Alfred, Marta Vent, Ruben, Gemma, Marta Sancho, Daniel, Àlex, Pere");
        for (Citizen x:provLinked) {
            System.out.println(x);
        }
        //Testing operations
        System.out.println(".get(3), Expected result: Alfred Tomas 204 ->  " + provLinked.get(3));
        System.out.println(".lon(), Expected result: 10 -> " + provLinked.lon());
        provLinked.delete(3);
        System.out.println(".lon(), Expected result: 9 -> " + provLinked.lon());
        System.out.println(".search(), Expected result: O(n) -> " + provLinked.search(num6));
    }
}