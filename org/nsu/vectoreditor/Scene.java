
package org.nsu.vectoreditor;

public class Scene
    extends java.awt.Panel
    implements java.awt.event.MouseListener, 
               java.awt.event.MouseMotionListener {

    public Scene(ShapeList slist) {
        shapes = slist;

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paint(java.awt.Graphics graphics) {

        if(bufferWidth != getSize().width || bufferHeight != getSize().height)
            initBuffer();

        java.awt.Graphics bufGraphics = buffer.getGraphics();
        bufGraphics.clearRect(0, 0, bufferWidth, bufferHeight);

        ShapeListIterator it = shapes.getFirst();
        while(!it.isEnd()) {
            it.getShape().draw(bufGraphics);
            it = it.getNext();
        }

        if(selectedTool != null) {
            selectedTool.draw(bufGraphics);
        }

        graphics.drawImage(buffer, 0, 0, this);
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }


    public void addShapeBefore(Shape s, Shape before) {

        ShapeListIterator it = shapes.getFirst();
        while(!it.isEnd()) {
            if(it.getShape() == before) {
                shapes.addBefore(s, it);
                break;
            }

            it = it.getNext();
        }
    }

    public void removeShape(Shape s) {

        ShapeListIterator it = shapes.getFirst();
        while(!it.isEnd()) {
            if(it.getShape() == s) {
                shapes.remove(it);
            }

            it = it.getNext();
        }
    }


    public java.awt.Dimension getPrefferedSize() {
        return new java.awt.Dimension(500, 500);
    }

    public java.awt.Dimension getMinimumSize() {
        return new java.awt.Dimension(100, 100);
    }

    public void selectTool(Tool tool) {
        selectedTool = tool;
        repaint();
    }

    public void mousePressed(java.awt.event.MouseEvent event) {
        if(selectedTool != null) {
            selectedTool.onMouseDown(event.getX(), event.getY());
            repaint();
        }
    }

    public void mouseReleased(java.awt.event.MouseEvent event) {
        if(selectedTool != null) {
            selectedTool.onMouseUp(event.getX(), event.getY());
            repaint();
        }
    }

    public void mouseClicked(java.awt.event.MouseEvent event) {
    }

    public void mouseEntered(java.awt.event.MouseEvent event) {
    }

    public void mouseExited(java.awt.event.MouseEvent event) {
    }

    public void mouseMoved(java.awt.event.MouseEvent event) {
        if(selectedTool != null) {
            selectedTool.onMouseMove(event.getX(), event.getY());
            repaint();
        }
    }

    public void mouseDragged(java.awt.event.MouseEvent event) {
        if(selectedTool != null) {
            selectedTool.onMouseMove(event.getX(), event.getY());
            repaint();
        }
    }


    public void update(java.awt.Graphics graphics) {
        paint(graphics);
    }

    private void initBuffer() {
        bufferWidth = getSize().width;
        bufferHeight = getSize().height;

        if(buffer != null) {
            buffer.flush();
        }

        buffer = createImage(bufferWidth, bufferHeight);
    }

    
    java.awt.Image buffer;
    int bufferWidth;
    int bufferHeight;

    ShapeList shapes;
    Tool selectedTool;
}

