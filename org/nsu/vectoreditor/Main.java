
package org.nsu.vectoreditor;

public class Main {
    public static void main(String args[]) {

        Scene scene = new Scene();
        scene.addFigure(new Rectangle(50, 100, 260, 200));
        scene.addFigure(new Circle(100, 150, 80));
        scene.addFigure(new Triangle(250, 250, 400, 300, 100, 350));

        MainWindow mainWindow = new MainWindow(scene);
        mainWindow.setSize(800, 600);
        mainWindow.setVisible(true);
    }
}

