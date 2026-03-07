package matrixexpression;

import java.util.Objects;

class Identity implements MatrixExpression {
    public Identity() {}

    @Override
    public boolean isIdentity() {
        return true;
    }

    @Override
    public MatrixExpression scalars() {
        return this;
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
        return that instanceof MatrixExpression && ((MatrixExpression) that).isIdentity();
    }
}
