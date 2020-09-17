package com.company;

public class DictImpl implements Dictionary {
    private int[] array;

    public DictImpl(int[] array) {
        this.array = array;
    }

    // If the index is out of bound, null will be returned
    @Override
    public Integer get(int index) {
        if (array == null || index >= array.length) {
            return null;
        }
        return array[index];
    }
}
