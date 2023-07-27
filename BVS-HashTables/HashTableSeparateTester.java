public class HashTableSeparateTester {
    public void run () {
        SeparateChainingHashTable table = new SeparateChainingHashTable(10);

        table.put(1, "apple");
        table.put(2, "banana");
        table.put(3, "cherry");
        table.put(4, "date");
        System.out.println(table.size());
        System.out.println(table.get(1)); // apple
        System.out.println(table.get(2)); // banana
        System.out.println(table.get(3)); // cherry
        System.out.println(table.get(4)); // date

        table.put(5, "apple");
        System.out.println(table.remove(2));
        System.out.println(table.get(2));
    }
}
