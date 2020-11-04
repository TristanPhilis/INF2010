public class Interview<ValueType extends Comparable<? super ValueType> > {
    // 2D array of any shape which contains elements sorted from left to right, top to bottom
    private final ValueType[][] matrix;

    public Interview(ValueType[][] matrix) {
        this.matrix = matrix.clone();
    }

    /** TODO Worst case : O ( max(log n, log m) )
     *
     * Verifies if the value is contained within the 2D array
     * @param value value to verify
     * @return if value is in matrix
     */
    public boolean contains(ValueType value) {
        boolean foundRow = false;
        boolean foundColumn = false;
        int rowIndex = findMiddleIndex(0, matrix.length);
        int columnIndex;

        //initial border check
        if (matrix[matrix.length - 1][matrix[matrix.length - 1].length - 1].compareTo(value) < 0 ||
                matrix[0][0].compareTo(value) > 0){
            return false;
        }

        //find row index
        while (!foundRow) {
            if (rowIndex >= matrix.length) {
                return false;
            }
            if (matrix[rowIndex][0].equals(value)) {//landed on value
                return true;
            } else if (matrix[rowIndex][0].compareTo(value) < 0) {//matrix beneath value
                if (rowIndex == matrix.length - 1) {//on the edge
                    foundRow = true;
                } else if (matrix[rowIndex + 1][0].compareTo(value) > 0){//value is between current and next row
                    foundRow = true;
                } else {
                    rowIndex = findMiddleIndex(rowIndex, matrix.length);
                }
            } else {//matrix above value
                if (rowIndex == 0) {//on the edge
                    return false;
                } else if (matrix[rowIndex - 1][0].compareTo(value) < 0){//value is between current and last row
                    foundRow = true;
                    rowIndex--;
                } else {
                    rowIndex = findMiddleIndex(0, rowIndex);
                }
            }
        }

        ValueType[] correctRow = matrix[rowIndex];
        columnIndex = findMiddleIndex(0, correctRow.length);

        /* Does not cause an infinite loop seeing as it will always return either true or false as the matrix iterates
         * through its values
         * */
        //find the value or lack thereof
        while (!foundColumn) {
            if (columnIndex >= correctRow.length) {
                return false;
            }
            if (correctRow[columnIndex].equals(value)) {//landed on value
                return true;
            } else if (correctRow[columnIndex].compareTo(value) < 0) {//matrix beneath value
                if (columnIndex == correctRow.length - 1) {//on the edge
                    return false;
                } else if (correctRow[columnIndex + 1].compareTo(value) > 0) {//next matrix position is bigger
                    return false;
                } else {
                    columnIndex = findMiddleIndex(columnIndex, correctRow.length);
                }
            } else {//matrix above value
                if (columnIndex == 0) {//on the edge
                    return false;
                } else if (correctRow[columnIndex - 1].compareTo(value) < 0) {//last matrix position is smaller
                    return false;
                } else {
                    columnIndex = findMiddleIndex(0, columnIndex);
                }
            }
        }


        return false;
    }

    private int findMiddleIndex(int start, int end){
        return (start + end)/2;
    }

}