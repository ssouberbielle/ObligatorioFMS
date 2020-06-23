package hash;

import java.util.Iterator;

public class ClosedHashImpl<Key, Data> implements MyHash<Key, Data>, Iterable<Data> {

    private static int LINEAL_COLLISION_FUNCTION = 1;

    private HashEntry<Key, Data>[] hashTable;

    private int size;

    private int defaultCollisionFunction = ClosedHashImpl.LINEAL_COLLISION_FUNCTION;

    public ClosedHashImpl(int size) {
        this.hashTable = new HashEntry[size];
    }

    @Override
    public void put(Key k, Data t) {
        int attempt = 0;
        int position = internalHashcodeWithCollision(k, attempt);

        HashEntry<Key, Data> valueToInsert = new HashEntry<>(k, t);

        while (hashTable[position] != null &&
                !hashTable[position].isRemoved() &&
                !hashTable[position].getKey().equals(k) &&
                !(attempt > hashTable.length)) {

            attempt++;
            position = internalHashcodeWithCollision(k, attempt);

        }

        if (attempt > hashTable.length) {

            throw new RuntimeException("No se encontro lugar disponible");
        }

        if (hashTable[position] == null || hashTable[position].isRemoved()) {

            hashTable[position] = valueToInsert;

        } else { // Si sale por aca es porque ya existe un elemento en la clave con lo que hay que sustituir el valor

            hashTable[position].setValue(t);
        }

        size++;
    }
    public int filledBuckets (){
        int res = 0;
        for (HashEntry<Key, Data> elements: hashTable) {
            if (elements != null) {
                res++;
            }

        }
        return res;
    }
    @Override
    public Data get(Key k) {
        int attempt = 0;
        int position = internalHashcodeWithCollision(k, attempt);
        Data valueToReturn = null;

        while (hashTable[position] != null &&
                !hashTable[position].getKey().equals(k) &&
                !(attempt > hashTable.length)) {

            attempt++;
            position = internalHashcodeWithCollision(k, attempt);

        }

        if (hashTable[position] != null &&
                !(attempt > hashTable.length) &&
                hashTable[position].getKey().equals(k) &&
                !hashTable[position].isRemoved()) {

            valueToReturn = hashTable[position].getValue();

        }

        return valueToReturn;
    }


    public int getSize() {
        return this.size;
    }

    @Override
    public void remove(Key k) {

    }

    @Override
    public boolean contains(Key k) {
        return this.get(k) != null;
    }

    private int internalHashcodeWithCollision(Key k, int attempt) {
        return (k.hashCode() + collisionFunction(attempt)) % hashTable.length;
    }

    private int collisionFunction(int i) {
        int value = 0;

        if (defaultCollisionFunction == LINEAL_COLLISION_FUNCTION) {

            value = i;

        }

        return value;
    }

    @Override
    public Iterator<Data> iterator() {

        return new HashIterator(hashTable);
    }

    private class HashIterator implements Iterator<Data> {
        private HashEntry<Key, Data>[] hashTable;
        private int count;
        private int maxIndex;
        private HashEntry<Key, Data> current;

        public HashIterator(HashEntry<Key, Data>[] hashTable) {
            this.hashTable = hashTable;
            this.count = 0;
            this.maxIndex = hashTable.length;
            moveToNext();

        }

        @Override
        public boolean hasNext() {
            return count<maxIndex;
        }

        @Override
        public Data next() {
            Data data = hashTable[count].getValue();
            count++;
            moveToNext();
            return data;
        }

        public void moveToNext(){
            while(count<maxIndex && (hashTable[count] == null || hashTable[count].isRemoved())){
                count++;
            }
        }
    }
}
