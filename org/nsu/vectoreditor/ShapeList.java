
package org.nsu.vectoreditor;

public class ShapeList {

    public void add(Shape shape) {

        ShapeListNode node = new ShapeListNode(shape);

        if(first == null) {
            // no nodes in the list
            first = node;
            last = node;
        } else {
            last.setNext(node);
            node.setPrev(last);
            last = node;
        }
    }

    public void addBefore(Shape s, ShapeListIterator bef) {

        ShapeListNode before = bef.getNode();
        ShapeListNode newItem = new ShapeListNode(s);

        newItem.setNext(before);

        if(before.getPrev() != null) {
            before.getPrev().setNext(newItem);
            newItem.setPrev(before.getPrev());
        } else {
            first = newItem;
        }

        before.setPrev(newItem);
    }

    public void remove(ShapeListIterator i) {
        ShapeListNode node = i.getNode();

        if(node == first && node == last) {
            first = null;
            last = null;
        } else if(node == first) {
            first = node.getNext();
            node.getNext().setPrev(null);
        } else if(node == last) {
            last = node.getPrev();
            node.getPrev().setNext(null);
        } else {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }
    }

    public ShapeListIterator getFirst() {
        return new ShapeListIterator(first);
    }

    public ShapeListIterator getLast() {
        return new ShapeListIterator(last);
    }

    private ShapeListNode first;
    private ShapeListNode last;
};

