
package org.nsu.vectoreditor;

public class ShapeList {

    public void add(Shape shape) {
        if(array == null) {
            array = new Shape[1];
            array[0] = shape;
            return;
        }

        Shape [] newArray = new Shape[array.length + 1];
        for(int i = 0; i < array.length; ++i) {
            newArray[i] = array[i];
        }

        newArray[array.length] = shape;
        array = newArray;
    }

    public void addBefore(Shape s, ShapeListIterator bef) {

        int index = bef.getIndex();

        Shape [] newArray = new Shape[array.length + 1];
        for(int i = 0; i < index; ++i) {
            newArray[i] = array[i];
        }

        newArray[index] = s;

        for(int i = index; i < array.length; ++i) {
            newArray[i + 1] = array[i];
        }

        array = newArray;
    }

    public void remove(ShapeListIterator it) {

        int index = it.getIndex();

        Shape [] newArray = new Shape[array.length - 1];

        for(int i = 0; i < index; ++i) {
            newArray[i] = array[i];
        }

        for(int i = index + 1; i < array.length; ++i) {
            newArray[i - 1] = array[i];
        }

        array = newArray;
    }

    public ShapeListIterator getFirst() {
        return new ShapeListIterator(array, 0);
    }

    private Shape [] array;
};

