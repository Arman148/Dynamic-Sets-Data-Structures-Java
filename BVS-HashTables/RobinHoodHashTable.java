import java.util.Arrays;

public class RobinHoodHashTable {
    private final int size;
    private final String[] keys;
    private final int[] values;
    private final int[] distances;

    public RobinHoodHashTable(int size) {
        this.size = size;
        keys = new String[size];
        values = new int[size];
        distances = new int[size];
        Arrays.fill(distances, -1);
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % size;
    }

    public void put(String key, int value) {
        int index = hash(key);
        int currentDistance = 0;

        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                values[index] = value;
                return;
            }

            int distance = getAbsoluteDistance(index, hash(keys[index]));

            if (distance < distances[index]) {
                swap(index, distance);
                currentDistance = distances[index];
            }

            index = (index + 1) % size;
            currentDistance++;
        }

        keys[index] = key;
        values[index] = value;
        distances[index] = currentDistance;
    }

    public String remove(int value) {
        int index = -1;

        // Find the index of the key-value pair with the given value
        for (int i = 0; i < size; i++) {
            if (values[i] == value) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            // Key-value pair with the given value not found
            return null;
        }

        // Found the key-value pair with the given value, remove it
        String removedKey = keys[index];
        keys[index] = null;
        values[index] = 0;
        distances[index] = -1;

        // Shift subsequent elements backward to fill the gap
        int nextIndex = (index + 1) % size;
        while (keys[nextIndex] != null && distances[nextIndex] > 0) {
            swap(nextIndex - 1, 1);
            nextIndex = (nextIndex + 1) % size;
        }

        // Return the name of the removed key-value pair
        return removedKey;
    }

    // Function to calculate the absolute distance between two indices
    private int getAbsoluteDistance(int a, int b) {
        int distance = Math.abs(b - a);
        return (distance <= size / 2) ? distance : size - distance;
    }

    // Function to swap the key-value pairs at two indices
    private void swap(int index, int distance) {
        String tempKey = keys[index];
        int tempValue = values[index];
        int tempDistance = distances[index];

        keys[index] = keys[(index + distance) % size];
        values[index] = values[(index + distance) % size];
        distances[index] = distances[(index + distance) % size];

        keys[(index + distance) % size] = tempKey;
        values[(index + distance) % size] = tempValue;
        distances[(index + distance) % size] = tempDistance;
    }

    public String getKey(int index) {
        return keys[index];
    }


    public int getValue(int index) {
        return values[index];
    }

    // Function to get the key corresponding to a given value
    public String get(int num) {
        for (int i = 0; i < size; i++) {
            if (values[i] == num) {
                return keys[i];
            }
        }

        return null;
    }
}
