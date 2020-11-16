package tp4;

public class Point implements Comparable<Point> {
    // TODO vous pouvez modifier ce que vous voulez, tant que vous ne modifiez pas les tests

    private Integer x;
    private Integer y;

    public Point(String xy) {
        String[] xAndY = xy.split(" +");
        this.x = Integer.parseInt(xAndY[0]);
        this.y = Integer.parseInt(xAndY[1]);
    }

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point origin, Point point) {
        this.x = point.x - origin.x;
        this.y = point.y - origin.y;
    }

    @Override
    public String toString() {
        return String.format("{X: %d, Y: %d}", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }

        Point point = (Point)obj;
        return point.x.equals(x) && point.y.equals(y);
    }

    @Override
    // TODO ceci vous sera peut etre utile
    //We use distance as the sorting value
    public int compareTo(Point point) {
        return Integer.compare(Math.abs(this.x) + Math.abs(this.y), Math.abs(point.x) + Math.abs(point.y));
    }

    private int findDistance(){
        return 0;
    }
}
