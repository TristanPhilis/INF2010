package tp1;

import java.util.Collection;

public class Ellipse extends BaseShape {
    // TODO creer une ellipse avec une largeur et une longueur.
    public Ellipse(Double widthRadius, Double heightRadius) {
        for(double i = -widthRadius; i < widthRadius; i++){
            for(double j = -heightRadius; j < heightRadius; j++){
                if(Math.pow(i/widthRadius, 2) + Math.pow(j/heightRadius, 2) <= 1){
                    add(new Point2d(i,j));
                }
            }
        }
    }


    // TODO creer une ellipse avec les dimensions contenu dans un Point.
    public Ellipse(Point2d dimensions) {
        this(dimensions.X(), dimensions.Y());
    }


    // TODO initialiser le parent.
    private Ellipse(Collection<Point2d> coords) {
        super(coords);
    }


    // TODO appliquer la translation sur la forme.
    @Override
    public Ellipse translate(Point2d point) {
        super.translate(point);
        return this;
    }


    // TODO appliquer la rotation sur la forme.
    @Override
    public Ellipse rotate(Double angle) {
        super.rotate(angle);
        return this;
    }


    // TODO retourner une nouvelle forme.
    @Override
    public Ellipse clone() {
        return (Ellipse) super.clone();
    }
}
