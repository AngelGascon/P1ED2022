
public class TestHashList {
    public static void main(String[] args) {
        HashTable<Integer, Citizen> testTable = new HashTable<>();
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
        //Init Hash Table
        testTable.insert(334, num1);
        testTable.insert(124, num2);
        testTable.insert(204, num3);
        testTable.insert(110, num4);
        testTable.insert(112, num5);
        testTable.insert(122, num6);
        testTable.insert(134, num7);
        testTable.insert(178, num8);
        testTable.insert(200, num9);
        testTable.insert(300, num10);
        //Printing HashTable
        System.out.println("Printing HashTable, Expected result: Every citizen -> Maria, Alvaro, Alfred, Marta Vent, Ruben, Gemma, Marta Sancho, Daniel, Àlex, Pere");
        DoubleLinkedList<Citizen> aux2 = testTable.getValues();
        for (Citizen obj : aux2) {
            System.out.println(obj);
        }
        //Get (Buscar)
        System.out.println(".get(334), Expected result: Maria Garcia 334 -> "+testTable.get(334));
        System.out.println(".get(300), Expected result: Pere Rullo 300 -> "+testTable.get(300));
        System.out.println(".get(3300), Expected result: Error code null -> "+testTable.get(3300));
        //lon (Mida)
        System.out.println(".lon(), Expected result: 10 -> "+testTable.lon());
        //delete(Esborrar)
        testTable.delete(300);
        System.out.println(".lon(), Expected result: 9 (Pere is deleted) -> "+testTable.lon());
        //Printing HashTable again
        System.out.println("Printing HashTable, Expected result: Every citizen -> Maria, Alvaro, Alfred, Marta Vent, Ruben, Gemma, Marta Sancho, Daniel, Àlex, Pere(deleted)");
        aux2 = testTable.getValues();
        for (Citizen obj : aux2) {
            System.out.println(obj);
        }
        System.out.println("Printing HashTable keys, Expected result: Every citizen key(id) -> Maria, Alvaro, Alfred, Marta Vent, Ruben, Gemma, Marta Sancho, Daniel, Àlex, Pere(deleted)");
        DoubleLinkedList<Integer> aux = testTable.getKeys();
        for (Integer obj : aux) {
            System.out.println(obj);
        }
        //.getLoadFactor (ObtenirFactorDeCarrega)
        System.out.println(".getLoadFactor(), Expected result: (10.0 to 9.0, Pere got deleted) 9.0/20, 0.45 -> "+testTable.getLoadFactor());
        System.out.println(".search(), Expected result: O(1) -> "+ testTable.search(124));
        System.out.println(".search(), Expected result: O(1) -> "+ testTable.search(334));
        System.out.println(".search(), Expected result: O(1) -> "+ testTable.search(178));
        System.out.println(".search(), Expected result: O(1) -> "+ testTable.search(200));
    }
}