
public class TestHashList {
    public static void main(String[] args) {
        HashTable<Integer, Citizen> testTable = new HashTable<>();
        Citizen num1 = new Citizen("Maria", "Garcia", "334");
        Citizen num2 = new Citizen("Alvaro", "Martin", "124");
        Citizen num3 = new Citizen("Alfred", "Tomas", "204");
        Citizen num4 = new Citizen("Marta", "Vent", "112");

        testTable.insert(345, num1);
        testTable.insert(340, num2);
        testTable.insert(346, num3);
        testTable.insert(3456, num4);
        testTable.insert(56, num3);
        testTable.insert(2, num4);
        testTable.insert(3, num4);
        testTable.insert(4, num4);
        testTable.insert(5, num4);
        System.out.println(testTable.get(346));
        testTable.delete(346);
        System.out.println(testTable.get(346));
        System.out.println(testTable.lon());
        System.out.println(testTable.search(346));

        DoubleLinkedList<Integer> aux = testTable.getKeys();
        for (Integer obj : aux) {
            System.out.println(obj);
        }
        DoubleLinkedList<Citizen> aux2 = testTable.getValues();
        for (Citizen obj : aux2) {
            System.out.println(obj);
        }
    }
}