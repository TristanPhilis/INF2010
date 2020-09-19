package tp1;

import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

public class BaseShape implements Cloneable {
    private Collection<Point2d> coords;

    // TODO Initialiser la liste de points.
    public BaseShape() {
        this.coords = new ArrayList<>();
    }


    // TODO prendre une liste de points et creer une nouvelle forme.
    public BaseShape(Collection<Point2d> coords) {
        this.coords = new ArrayList<>(coords);
    }


    // TODO ajouter ou retirer des coordonnees a la liste de points.
    public BaseShape add(Point2d coord) {
        this.coords.add(coord);
        return this;
    }


    public BaseShape add(BaseShape shape) {
        Collection<Point2d> temp = shape.getCoords();
        this.addAll(temp);
        return this;
    }


    public BaseShape addAll(Collection<Point2d> coords) {
        this.coords.addAll(coords);
        return this;
    }


    public BaseShape remove(Point2d coord) {
        coords.remove(coord);
        return this;
    }


    public BaseShape remove(BaseShape shape) {
        for(Point2d iter : shape.coords){
            remove(iter);
        }
        return this;
    }


    public BaseShape removeAll(Collection<Point2d> coords) {
        this.coords.removeAll(coords);
        return this;
    }


    // TODO retourner les coordonnees de la liste.
    public Collection<Point2d> getCoords() {
        Collection<Point2d> copy = new ArrayList<>(coords);
        return copy;
    }


    // TODO retourner une nouvelle liste ou tous les points sont des copy
    public Collection<Point2d> getCoordsDeepCopy() {
        Collection<Point2d> deepCopy = new ArrayList<>();
        for(Point2d iter : coords){
            deepCopy.add(iter.clone());
        }
        return deepCopy;
    }


    // TODO appliquer la translation sur la forme.
    public BaseShape translate(Point2d point) {
        //watch out if point and coords have different dimensions, it doesn't work
        for(Point2d iter : coords){
            iter.translate(point);
        }
        return this;
    }


    // TODO appliquer la rotation sur la forme.
    public BaseShape rotate(Double angle) {
        for(Point2d iter : coords){
            iter.rotate(angle);
        }
        return this;
    }


    // TODO donner la plus grande valeur en X
    public Double getMaxX() {
        Double maxValue = Double.MIN_VALUE;
        for(Point2d iter : coords){
            if(iter.X() > maxValue){
                maxValue = iter.X();
            }
        }
        return maxValue;
    }


    // TODO donner la plus grande valeur en Y
    public Double getMaxY() {
        Double maxValue = Double.MIN_VALUE;
        for(Point2d iter : coords){
            if(iter.Y() > maxValue){
                maxValue = iter.Y();
            }
        }
        return maxValue;
    }


    // TODO donner les plus grandes valeurs en X et Y
    public Point2d getMaxCoord() {
        return new Point2d(getMaxX(), getMaxY());
    }


    // TODO donner la plus petite valeur en X
    public Double getMinX() {
        Double minValue = Double.MAX_VALUE;
        for(Point2d iter : coords){
            if(iter.X() < minValue){
                minValue = iter.X();
            }
        }
        return minValue;
    }


    // TODO donner la plus petite valeur en Y
    public Double getMinY() {
        Double minValue = Double.MAX_VALUE;
        for(Point2d iter : coords){
            if(iter.Y() < minValue){
                minValue = iter.Y();
            }
        }
        return minValue;
    }

    // TODO donner les plus petites valeurs en X et Y
    public Point2d getMinCoord() {
        return new Point2d(getMinX(), getMinY());
    }

    // TODO retourner une nouvelle forme.
    public BaseShape clone() {
        return new BaseShape(getCoordsDeepCopy());
    }
}
