public class RobinHoodHashTableTester {
    public void run () {
        RobinHoodHashTable table = new RobinHoodHashTable(10);

        table.put("apple", 1);
        table.put("banana", 2);
        table.put("cherry", 3);
        table.put("date", 4);
        table.put("phone", 7);
        table.put("bottle", 9);
        table.put("knife", 10);
        table.put("table", 11);
        table.put("chair", 12);

        System.out.println(table.get(1)); // apple
        System.out.println(table.get(2)); // banana
        System.out.println(table.get(3)); // cherry
        System.out.println(table.get(4)); // date

        table.put("apple", 5);
        System.out.println();
        System.out.println(table.remove(2)); // banana
        System.out.println(table.get(2)); // null

        System.out.println(table.get(5));  // apple
        System.out.println(table.get(8));  // null
    }
}