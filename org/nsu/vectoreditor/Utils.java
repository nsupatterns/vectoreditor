
package org.nsu.vectoreditor;

public class Utils {
    public static boolean pointInRectangle(Point p, Point p1, Point p2) {
        if(p1.x < p2.x) {
            if(p.x < p1.x || p.x > p2.x)
                return false;
        } else {
            if(p.x < p2.x || p.x > p1.x)
                return false;
        }

        if(p1.y < p2.y) {
            if(p.y < p1.y || p.y > p2.y)
                return false;
        } else {
            if(p.y < p2.y || p.y > p1.y)
                return false;
        }

        return true;
    }
}

