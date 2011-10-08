
package org.nsu.vectoreditor;

public class ShapeListIterator {

    public ShapeListIterator(Shape [] arr, int i) {
        array = arr;
        index = i;
    }

    public Shape getShape() {
        assert(!isEnd());
        return array[index];
    }

    public ShapeListIterator getNext() {
        assert(!isEnd());
        return new ShapeListIterator(array, index + 1);
    }

    public boolean isEnd() {
        return array == null || index >= array.length;
    }

    // for internal use only
    public int getIndex() {
        return index;
    }

    Shape [] array;
    private int index;
};

