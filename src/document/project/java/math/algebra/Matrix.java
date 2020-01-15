package document.project.java.math.algebra;

public class Matrix {

    public static Integer[][] wrap (int[][] matrix){
        Integer[][] wrapped = new Integer[matrix.length][];
        for (int i = 0; i < matrix.length; i++){
            wrapped[i] = new Integer[matrix[i].length];
            for (int j = 0; j < matrix[i].length; j ++){
                wrapped[i][j] = matrix[i][j];
            }
        }
        return wrapped;
    }

    public static Double[][] wrap (double[][] matrix){
        Double[][] wrapped = new Double[matrix.length][];
        for (int i = 0; i < matrix.length; i++){
            wrapped[i] = new Double[matrix[i].length];
            for (int j = 0; j < matrix[i].length; j ++){
                wrapped[i][j] = matrix[i][j];
            }
        }
        return wrapped;
    }

    public static Character[][] wrap (char[][] matrix){
        Character[][] wrapped = new Character[matrix.length][];
        for (int i = 0; i < matrix.length; i++){
            wrapped[i] = new Character[matrix[i].length];
            for (int j = 0; j < matrix[i].length; j ++){
                wrapped[i][j] = matrix[i][j];
            }
        }
        return wrapped;
    }

    public static Boolean[][] wrap (boolean[][] matrix){
        Boolean[][] wrapped = new Boolean[matrix.length][];
        for (int i = 0; i < matrix.length; i++){
            wrapped[i] = new Boolean[matrix[i].length];
            for (int j = 0; j < matrix[i].length; j ++){
                wrapped[i][j] = matrix[i][j];
            }
        }
        return wrapped;
    }

    public static <E> E[][] transpose (E[][] matrix){
        new Matrix().validateSquareMatrix(matrix);
        E[][] transposed = copyMatrix(matrix);
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++){
            for (int j = i+1; j < cols; j++){
                transposed[i][j] = matrix[j][i];
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }

    public static <E> E[][] copyMatrix(E[][] matrix){
        E[][] copy = matrix.clone();
        for (int i = 0; i < matrix.length; i++){
            copy[i] = matrix[i].clone();
        }
        return copy;
    }

    public static <E> E[][] exchangeLine (E[][] matrix, int line1, int line2){
        new Matrix().validateRegularMatrix(matrix);
        E[][] exchanged = copyMatrix(matrix);
        exchanged[line1] = matrix[line2].clone();
        exchanged[line2] = matrix[line1].clone();
        return exchanged;
    }

    public static <E> E[][] exchangeColumn (E[][] matrix, int col1, int col2){
        E[][] exchanged = copyMatrix(matrix);
        for (int i = 0; i < matrix.length; i++) {
            exchanged[i][col1] = matrix[i][col2];
            exchanged[i][col2] = matrix[i][col1];
        }
        return exchanged;
    }

    private <E> void validateRegularMatrix (E[][] matrix){
        int length = matrix[0].length;
        for (int i = 1; i < matrix.length; i++){
            if (matrix[i].length != length){
                throw new IllegalArgumentException("__THE ROWS HAVEN'T REGULAR LENGTHS!__");
            }
        }
    }

    private <E> void validateSquareMatrix (E[][] matrix) {
        validateRegularMatrix(matrix);
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (rows != cols) {
            throw new IllegalArgumentException("__THE NUMBER OF ROWS AND COLUMNS AREN'T EQUAL!__");
        }
    }

}
