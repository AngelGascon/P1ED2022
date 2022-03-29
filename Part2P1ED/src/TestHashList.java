public class TestHashList {
    public static void main(String[] args) {
        HashTable<Integer, String> testTable = new HashTable<>();
        testTable.insert(3456, "Daniel");
        testTable.insert(3456, "Marcus");
        System.out.println(testTable.get(3456));
    }
}