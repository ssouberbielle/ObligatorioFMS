package heap;

import exceptions.EmptyHeap;
import exceptions.NoNullValueAllow;

public class HeapMax<T extends Comparable<T>> implements MyHeap<T> {

    private T[] values;

    private int pointerLastValue;

    public HeapMax(T[] values) {
        this.values = values;
        pointerLastValue = 0;
    }


    public HeapMax(int size) {
        this.values = (T[]) new Comparable[size];
    }

    public int size() {
        return pointerLastValue;
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
        if (pointerLastValue == values.length){
            resize();
        }
        this.values[pointerLastValue] = value;
        int valuePosition = pointerLastValue;
        pointerLastValue++;

        while (valuePosition != 0 && value.compareTo(getFather(valuePosition)) > 0) {
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


    @Override
    public T getMax() {
        T valueToReturn = null;

        if (pointerLastValue != 0) {
            valueToReturn = this.values[0];
        }

        return valueToReturn;
    }

    @Override
    public void deleteMax()  {
        if(this.values[0] == null) {
            throw new EmptyHeap();
        }
        else if (pointerLastValue == 0){
            this.values[0] = null;
        }else{
            T lastValue = this.values[pointerLastValue-1];
            pointerLastValue--;
            this.values[pointerLastValue] = null;
            this.values[0] = lastValue;
            int tempPosition = 0;
            orderHeap(tempPosition);

        }

    }

    private void orderHeap(int tempPosition){
        if(getFirstSon(tempPosition) != null && getSecondSon(tempPosition) != null) {
            if (getFirstSon(tempPosition).compareTo(getSecondSon(tempPosition)) > 0) {
                if (getFirstSon(tempPosition).compareTo(this.values[tempPosition]) > 0) {
                    orderHeap(swapWithFirstSon(getFirstSonPosition(tempPosition),tempPosition));
                }else{
                    return;
                }
            } else {
                if (getSecondSon(tempPosition).compareTo(this.values[tempPosition]) > 0) {
                    orderHeap(swapWithSecondSon(getSecondSonPosition(tempPosition), tempPosition));
                }else{
                    return;
                }
            }
        }else if(getSecondSon(tempPosition)  == null && getFirstSon(tempPosition) != null){
            if(getFirstSon(tempPosition).compareTo(this.values[tempPosition])> 0){
                orderHeap(swapWithFirstSon(getFirstSonPosition(tempPosition),tempPosition));
            }else{
                return;
            }
        }else if(getFirstSon(tempPosition)==null && getSecondSon(tempPosition) != null){
            if(getSecondSon(tempPosition).compareTo(this.values[tempPosition])>0){
                orderHeap(swapWithSecondSon(getSecondSonPosition(tempPosition), tempPosition));
            }else{
                return;
            }
        }else{
            return;
        }
    }


    private int swapWithFirstSon(int sonPosition, int fatherPosition){
        T tempValue = this.values[fatherPosition];
        this.values[fatherPosition] = this.values[sonPosition];
        this.values[sonPosition] = tempValue;
        return getFirstSonPosition(fatherPosition);

    }

    private int swapWithSecondSon(int sonPosition, int fatherPosition){
        T tempValue = this.values[fatherPosition];
        this.values[fatherPosition] = this.values[sonPosition];
        this.values[sonPosition] = tempValue;
        return getSecondSonPosition(fatherPosition);

    }




}
