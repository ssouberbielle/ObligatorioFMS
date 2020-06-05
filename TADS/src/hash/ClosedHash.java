package hash;

import exceptions.HashNodeNotFound;

public class ClosedHash<K, V> implements MyHash<K,V> {

    private float alpha;
    private int numBucket; // Current capacity of array list
    private int size; //Current size of array list
    private ClosedHashNode<K, V>[] buckets;


    public ClosedHash(int sizeArray){
        this.buckets =  (ClosedHashNode<K, V>[]) new Object[sizeArray];
        this.numBucket = sizeArray;
        this.alpha = (float) 0.8;
    }

    public ClosedHash(int sizeArray, float alpha){
        this.buckets =  (ClosedHashNode<K, V>[]) new Object[sizeArray];
        this.numBucket = sizeArray;
        this.alpha = (float) alpha;
    }


    private boolean isEmpty(){
        return size==0;
    }

    private int hashcode(K k){
        return k.hashCode() % buckets.length;
    }

    private int hashFunction(int i){
        return i;
    }

    @Override
    public void put(K k, V t) {
        int positionToInsert = hashcode(k);
        ClosedHashNode<K,V> tempNode  = null;
        ClosedHashNode<K,V> nodeToInsert  = new ClosedHashNode<>(k, t);
        int intentos = 0;


        if((float)size/ numBucket >= alpha){
            ClosedHashNode<K, V>[] newBuckets = (ClosedHashNode<K, V>[]) new Object[numBucket*2];
            for(int i=0; i<buckets.length; i++){
                newBuckets[i] = buckets[i];
            }
            buckets = newBuckets;
            this.numBucket = numBucket*2;
        }

        while(buckets[positionToInsert] != null){
            if(buckets[positionToInsert].equals(positionToInsert) && buckets[positionToInsert].isDelete()){
                break;
            }
            if (positionToInsert < numBucket){
                intentos +=1;
                positionToInsert = positionToInsert + hashFunction(intentos);
            }else{
                positionToInsert = 0;
            }
        }
        buckets[positionToInsert] = nodeToInsert;
        size++;


    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public V get(K k) {
        int position = hashcode(k);
        V valueToReturn = null;
        if(buckets[position] == null){
            return valueToReturn;
        }
        if(contains(k)){
            ClosedHashNode<K,V> tempNode = buckets[position];
            int intentos = 0;
            while(!tempNode.getKey().equals(k)){
                if(position < numBucket){
                    intentos += 1;
                    position += hashFunction(intentos);
                }else{
                    position = 0;
                }
            }
            valueToReturn = tempNode.getValue();

        }else{
            throw new HashNodeNotFound();
        }

        return valueToReturn;


    }

    @Override
    public void remove(K k) {
        int position = k.hashCode();
        if(contains(k)){
            int intentos = 0;
            ClosedHashNode<K,V> tempNode  = buckets[position];
            while(!tempNode.getKey().equals(k)) {
                if(position < numBucket){
                    intentos += 1;
                    position += hashFunction(intentos);
                }else{
                    position = 0;
                }
            }
            tempNode.setDelete(true);

        }else {
            throw new HashNodeNotFound();
        }


    }

    @Override
    public boolean contains(K k) {
        int position = hashcode(k);
        boolean ifind = false;
        if (buckets[position] != null) {
            int intentos = 0;
            ClosedHashNode<K, V> tempNode = buckets[position];
            while (!tempNode.getKey().equals(k) && buckets[position] != null) {
                if(position < numBucket){
                    intentos += 1;
                    position += hashFunction(intentos);
                }else{
                    position = 0;
                }
            }
            if (buckets[position] == null) {
                ifind = false;
            } else {
                ifind = true;
            }

        }

        return ifind;

    }


}
