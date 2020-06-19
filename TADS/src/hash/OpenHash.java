package hash;

import LinkedList.LinkedList;
import LinkedList.MyList;

import java.util.Iterator;

public class OpenHash<K,V> implements MyHash<K ,V> {


    private MyList<HashNode<K,V>>[] entryArray;
    private int size = 0;


    public OpenHash(int sizeArray){
        this.entryArray = new MyList[sizeArray];

    }

    private int hashcode(K k){
        return k.hashCode() % entryArray.length;
    }

    @Override
    public void put(K k, V t) {
        int position = hashcode(k);

        MyList<HashNode<K, V>> listForPosition = entryArray[position];

        if (listForPosition == null) {

            listForPosition = new LinkedList<HashNode<K, V>>();
            entryArray[position] = listForPosition;

        }


        Iterator<HashNode<K,V>> iterator = listForPosition.iterator();
        HashNode<K,V> searchElement = null;

        while(iterator.hasNext() && searchElement == null){
            HashNode<K,V> tempValue = iterator.next();

            if (tempValue.getKey().equals(k)) {
                searchElement = tempValue;
            }

        }
        // Si encontre el elemento actualizo la value
        if (searchElement != null) {
            searchElement.setValue(t);
        } else {
            // si no existe lo creo y lo agrego a la lista
            HashNode<K, V> hashEntry = new HashNode<>(k, t);

            listForPosition.addFirst(hashEntry);
            size++;
        }



    }

    @Override
    public int getSize() { return size; }

    @Override
    public V get(K k) {
        int position = hashcode(k);
        V valueToReturn = null;

        MyList<HashNode<K, V>> listForPosition = entryArray[position];

        if (listForPosition != null) {
            // Buscar si existe la entrada con clave k
            for (HashNode<K, V> tempEntry : listForPosition) {

                if (tempEntry.getKey().equals(k)) {
                    valueToReturn = tempEntry.getValue();
                    break;
                }

            }
        }

        return valueToReturn;
    }

    public MyList<HashNode<K, V>> getList(K k) {
        int position = hashcode(k);

        MyList<HashNode<K, V>> list = entryArray[position];



        return list;
    }
    @Override
    public void remove(K k) {
        int position = hashcode(k);

        MyList<HashNode<K, V>> listForPosition = entryArray[position];

        if (listForPosition != null) {
            // Buscar si existe la entrada con clave k
            for (HashNode<K, V> tempEntry : listForPosition) {

                if (tempEntry.getKey().equals(k)) {
                    listForPosition.remove(tempEntry);
                    size--;
                    break;

                }

            }
        }

    }

    public boolean contains(K k) {
        int position = hashcode(k);
        boolean ifind = false;

        MyList<HashNode<K, V>> listForPosition = entryArray[position];
        if(listForPosition != null){
            for (HashNode<K, V> tempEntry : listForPosition) {

                if (tempEntry.getKey().equals(k)) {
                    ifind = true;
                    break;
                }
            }

        }
        return ifind;
    }
}
