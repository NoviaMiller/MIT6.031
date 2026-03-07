package matrixexpression;

public class Scalar implements MatrixExpression {
    private final double value;
    //RI: true
    //AF(value) = the real scalar represented by the value

    public Scalar(double value) {
        this.value = value;
    }

    @Override
    public boolean isIdentity() {
        return value == 1;
    }

    @Override
    public MatrixExpression scalars() {
       return this;
    }

    @Override
    public MatrixExpression matrices() {
        return I;
    }

    @Override
    public MatrixExpression optimize() {
        return this;
    }

    @Override
    public boolean equals(Object that) {
        if (that instanceof Scalar) {
            return this.value == ((Scalar) that).value;
        }
    return false;
    }
}
