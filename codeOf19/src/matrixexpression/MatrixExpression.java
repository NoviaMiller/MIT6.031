package matrixexpression;

import java.util.Objects;

public interface MatrixExpression {
    //Datatype definition:
    //  MatExpr = Identity + Scalar(value:double)
    //  + Matrix(array:double[][]) + Product(m1:MatExpr, m2:MatExpr)

    public static final MatrixExpression I = new Identity();

    public static MatrixExpression make(double value) {
        return new Scalar(value);
    }

    public static MatrixExpression make(double[][] array) {
        return new Matrix(array);
    }

    public static MatrixExpression times(MatrixExpression m1, MatrixExpression m2) {
        if (m1.isIdentity()) {
            return m2;
        } else if (m2.isIdentity()) {
            return m1;
        } else {
            return new Product(m1, m2);
        }
    }

    /** returns true only if the expression is the multiplicative identity. => loose spec*/
    public boolean isIdentity();

    /**
     * optimize's helper1
     * @return product of all scalars
     */
    public MatrixExpression scalars();

    /**
     * optimize's helper2
     * @return product of all matrices in order
     */
    public MatrixExpression matrices();

    /** a better (same) expression to calculate*/
    public MatrixExpression optimize();

    @Override
    boolean equals(Object that);
}
