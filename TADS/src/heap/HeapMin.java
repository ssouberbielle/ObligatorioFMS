package heap;

import exceptions.NoNullValueAllow;

public class HeapMin<T extends Comparable<T>> implements MyHeap<T>{


    private T[] values;

    private int pointerLastValue;


    public HeapMin(int size) {
        this.values = (T[]) new Comparable[size];
        pointerLastValue = 0;
    }

    private void resize(){
        T[] newValues = (T[]) new Comparable[values.length*2];
        for(int i=0; i<values.length; i++){
            newValues[i] = values[i];
        }
        values = newValues;
    }

    @Override
    public void insert(T value) {
        if (value == null) throw new NoNullValueAllow();
        if (pointerLastValue == values.length) {
            resize();
        }
        this.values[pointerLastValue] = value;
        int valuePosition = pointerLastValue;
        pointerLastValue++;

        while (valuePosition != 0 && value.compareTo(getFather(valuePosition)) < 0) {
            T tempValue = getFather(valuePosition);

            this.values[gettempPosition(valuePosition)] = value;
            this.values[valuePosition] = tempValue;

            valuePosition = gettempPosition(valuePosition);
        }
    }


        private T getFather(int childPosition) {
            return values[(childPosition - 1)/2];
        }

        private int gettempPosition(int childPosition) {
            return (childPosition - 1)/2;
        }

        private T getFirstSon(int tempPosition) { return values[2*tempPosition+1];}

        private T getSecondSon(int tempPosition) {return values[2*tempPosition +2];}

        private int getFirstSonPosition(int tempPosition){ return (2*tempPosition+1);}

        private int getSecondSonPosition(int tempPosition){ return (2*tempPosition+2);}




    public T getMax() {
        T valueToReturn = null;

        if (pointerLastValue != 0) {
            valueToReturn = this.values[0];
        }

        return valueToReturn;
    }

    @Override
    public void deleteMax() {

    }

    @Override
    public int size() {
        return 0;
    }
}
