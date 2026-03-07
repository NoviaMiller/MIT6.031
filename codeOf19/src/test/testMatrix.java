package test;

import matrixexpression.MatrixExpression;
import org.junit.Test;

import static org.junit.Assert.*;

public class testMatrix {
    @Test
    public void testIsIdentity() {
        //partition:
        // I
        // scalar: 1 , others
        // Matrix
        // Product (lazy)

        //covers I
        assertTrue(MatrixExpression.I.isIdentity());

        //covers scalar
        assertTrue(MatrixExpression.make(1).isIdentity());
        assertFalse(MatrixExpression.make(7).isIdentity());

        //covers matrix
        double[][] a1 = {{1, 0}, {0, 1}};
        assertTrue(MatrixExpression.make(a1).isIdentity());
        double[][] a2 = {{1}, {0}};
        assertFalse(MatrixExpression.make(a2).isIdentity());
        double[][] a3 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        assertFalse(MatrixExpression.make(a3).isIdentity());
    }

    @Test
    public void testOptimize() {
        /**
         * partition on number of scalars:
         *  0, 1, 2, >2
         *
         * partition on position of scalars:
         *      root
         *    l      r
         * ll  lr  rl  rr
         */

        MatrixExpression a = MatrixExpression.make(7);
        double[][] a1 = {{1, 0, 0}, {0, 1, 0}};
        MatrixExpression X =  MatrixExpression.make(a1);
        double[][] a2 = {{1, 2}, {2, 1}, {0, 0}};
        MatrixExpression Y =  MatrixExpression.make(a2);
        MatrixExpression b = MatrixExpression.make(11);

        //covers: 0
        assertEquals(X, X.optimize());

        //covers: 1, l
        MatrixExpression aX = MatrixExpression.times(a, X);
        assertEquals(a, aX.optimize().scalars());
        assertEquals(X, aX.optimize().matrices());

        //covers: 2, l, rr
        MatrixExpression aXb = MatrixExpression.times(a, MatrixExpression.times(X,b));
        assertEquals(MatrixExpression.times(a, b), aXb.optimize().scalars());
        assertEquals(X, aXb.optimize().matrices());

        //covers: 2, ll, r
        MatrixExpression aXb2 = MatrixExpression.times(MatrixExpression.times(a, X), b);
        assertEquals(MatrixExpression.times(a, b), aXb2.optimize().scalars());
        assertEquals(X, aXb2.optimize().matrices());

        //covers: 2, lr, rl
        MatrixExpression XabY = MatrixExpression.times(
                MatrixExpression.times(X, a), MatrixExpression.times(b, Y)
        );
        assertEquals(MatrixExpression.times(a, b), XabY.optimize().scalars());
        assertEquals(MatrixExpression.times(X, Y), XabY.optimize().matrices());
    }
}
