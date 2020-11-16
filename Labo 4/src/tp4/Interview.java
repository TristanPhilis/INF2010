package tp4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public final class Interview {
    private static class Pair {
        public Integer index;
        public Point point;

        Pair(int index, Point point){
            this.index = index;
            this.point = point;
        }
    }

    static class SortPairByDistance implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.point.compareTo(o2.point);
        }
    }

    /**
     * @param circleSize le nombre d'amis que l'on doit inclure dans le cercle
     * @param centers les indices des centres dans "points"
     * @param points la liste des individus
     * @return les indices dans "points" qui creent l'intersection de deux cercles d'amis.
     *
     * On veut que vous indiquiez la complexite sur chaque ligne en fonction de:
     *  - circleSize -> O(a) a est pour le nombre d' "amis" communs
     *  - centers -> O(c) c est pour le nombre de "centres"
     *  - points -> O(n) n est simplement le nombre de d'individus dans la population
     * Vous n'avez pas besoin d'indiquer la complexite des lignes etant en O(1)
     * Lorsque vous avez des boucles, indiquez clairement la complexite de la boucle, par exemple:
     *   for (Integer p1 : points) { // O(n) * O(n^2*log(n)) -> O(n^3*log(n))
     *     for (Integer p2 : points) { // O(n) * O(n*log(n) -> O(n^2*log(n))
     *       Collections.sort(points); // O(n*log(n)
     *     }
     *   }
     * Ceci est un exemple, on voit clairement que la boucle sur "p2" est en O(n) et puisque son interieur est
     * en O(n*log(n), la complexite totale sera la multiplication, O(n^2*log(n)).
     *
     * O(n^2 * log(n)): ceci est la complexite en "worst case" ou 'a' et 'c' tendent vers 'n'
     * Il est peut etre possible d'avoir une meilleure complexite, soyez clair dans vos explications si vous croyez
     * avoir trouve :)
     */
    // TODO
    public static List<Integer> getFriendsToRemove(Integer circleSize, List<Integer> centers, List<Point> points) {
        /** La complexité totale de l'algorithme est de O(n^2*log(n)) dans le pire des cas où a et c tendent vers n
         *  sans que a ne depasse n */
        List<Integer> indexToRemove = new ArrayList<>();
        if (circleSize <= points.size()) { //O(n^2*log(n)) + O(n) + O(n) + O(n) -> O(n^2*log(n))
            List<Pair> allCentersList = new ArrayList<>();

            for (Integer center : centers) { //O(n) * (O(n)+O(n)+O(n*log(n))+O(n)) -> O(n^2*log(n))
                List<Pair> centeredListPoints = new ArrayList<>();

                for (int i = 0; i < points.size(); i++) { //O(n)
                    centeredListPoints.add(new Pair(i, new Point(points.get(center), points.get(i))));
                }

                //removes the center from the neighbour list
                centeredListPoints.removeIf(obj -> obj.index.equals(center)); //O(n)

                centeredListPoints.sort(new SortPairByDistance()); //O(n*log(n))

                //Takes only the circleSize nearest friends
                for (int i = 0; i < circleSize; i++) { //O(n)
                    allCentersList.add(centeredListPoints.get(i)); //O(1) because ArrayList
                }
            }

            Integer[] numberOfCalls = new Integer[points.size()];
            Arrays.fill(numberOfCalls,0); //O(n)

            for (Pair iter : allCentersList) { //O(n)
                numberOfCalls[iter.index]++;
            }

            for (int i = 0; i < numberOfCalls.length; i++) { //O(n)
                if (numberOfCalls[i] > 1) {
                    indexToRemove.add(i);
                }
            }

            return indexToRemove;
        } else { //O(n) + O(n^2) + O(n) -> O(n^2)
            for (int i = 0; i < points.size(); i++) { //O(n)
                indexToRemove.add(i);
            }
            for(Integer iter : centers) { //O(n^2)
                indexToRemove.remove(iter); //O(n)
            }
            return indexToRemove;
        }
    }
}
