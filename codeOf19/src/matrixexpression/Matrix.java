package matrixexpression;

import java.util.Arrays;

public class Matrix implements MatrixExpression {
    private final double[][] array;
    //RI: array.length > 0 and all array[i] are equal nonzero length
    //AF(array) = the matrix of array[row][col]

    public Matrix(double[][] array){
       this.array = new double[array.length][array[0].length];
       for (int i = 0; i < array.length; i++) {
           System.arraycopy(array[i], 0, this.array[i], 0, array[i].length);
       }
    }

    @Override
    public boolean isIdentity() {
        if (array.length != array[0].length) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                double expect = i == j ? 1 : 0;
                if (array[i][j] != expect) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public MatrixExpression scalars() {
       return I;
    }

    @Override
    public MatrixExpression matrices() {
        return this;
    }

    @Override
    public MatrixExpression optimize() {
        return this;
    }

    @Override
    public boolean equals(Object that) {
        if (that instanceof Matrix) {
            return this.array.equals(((Matrix) that).array);
        }
        return false;
    }
}
