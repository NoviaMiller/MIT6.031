package matrixexpression;

public class Product implements MatrixExpression {
    private final MatrixExpression m1;
    private final MatrixExpression m2;
    //RI: m1's col length == m2's row length || m1 == scalar || m2 == scalar
    //AF(m1, m2) = the matrix product of m1 * m2

    public Product(MatrixExpression m1, MatrixExpression m2) {
        this.m1 = m1;
        this.m2 = m2;
    }

    @Override
    public boolean isIdentity() {
        return m1.isIdentity() && m2.isIdentity();
    }

    @Override
    public MatrixExpression scalars() {
       return MatrixExpression.times(m1.scalars(), m2.scalars());
    }

    @Override
    public MatrixExpression matrices() {
        return MatrixExpression.times(m1.matrices(), m2.matrices());
    }

    @Override
    public MatrixExpression optimize() {
        return MatrixExpression.times(scalars(), matrices());
    }

    @Override
    public boolean equals(Object that) {
        if (that instanceof Product) {
            return this.m1.equals(((Product) that).m1) && this.m2.equals(((Product) that).m2);
        }
        return false;
    }
}
