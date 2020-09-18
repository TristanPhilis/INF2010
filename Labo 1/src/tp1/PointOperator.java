package tp1;

public final class PointOperator {

    // TODO appliquer la translation sur le vecteur d'entree.
    public static void translate(Double[] vector, Double[] translateVector) {
        for(int i = 0; i < vector.length; i++){
            vector[i] = vector[i] + translateVector[i];
        }
    }

    // TODO appliquer la translation sur le vecteur d'entree.
    public static void rotate(Double[] vector, Double[][] rotationMatrix) {
        Double[] tempVector = vector;
        for(int v = 0; v < tempVector.length; v++){
            vector[v] = 0.0;
            for(int m = 0; m < tempVector.length; m++){
                vector[v] += tempVector[m]*rotationMatrix[v][m];
            }
        }
    }

    // TODO appliquer la translation sur le vecteur d'entree.
    public static void divide(Double[] vector, Double divider) {
        for(int i = 0; i < vector.length; i++){
            vector[i] = vector[i]/divider;
        }
    }

    // TODO appliquer la translation sur le vecteur d'entree.
    public static void multiply(Double[] vector, Double multiplier) {
        // ...
    }

    // TODO appliquer la translation sur le vecteur d'entree.
    public static void add(Double[] vector, Double adder) {
        // ...
    }
}
