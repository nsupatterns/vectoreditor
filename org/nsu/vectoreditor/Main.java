
package org.nsu.vectoreditor;

public class Main {
    public static void main(String args[]) {

        ShapeList shapes = new ShapeList();
        Scene scene = new Scene(shapes);
        scene.selectTool(new LineTool(shapes));

        MainWindow mainWindow = new MainWindow(scene, shapes);
        mainWindow.setSize(800, 600);
        mainWindow.setVisible(true);
    }
}

