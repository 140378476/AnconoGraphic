/**
 * 2017-11-16
 */
package cn.ancono.math.geometry.visual2D;

import cn.ancono.math.geometry.analytic.planeAG.curve.SubstituableCurve;

import java.awt.geom.Point2D;
import java.util.function.DoubleFunction;
import java.util.function.ToDoubleFunction;

/**
 * @author liyicheng
 * 2017-11-16 20:59
 */
public interface SubstitutableCurve {

    /**
     * Computes the value of {@code f(x,y)} as double
     *
     * @param x
     * @param y
     */
    double compute(double x, double y);

    /**
     * Returns a double-typed function which is adapted from a SubstituableCurve.
     * @param fromDouble the function to convert double to T
     * @param toDouble  the function to convert T to double
     * @param curve a curve
     * @return a function that accept {@link Point2D.Double} and returns double.
     */
    public static <T> SubstitutableCurve mapToDouble(
            DoubleFunction<T> fromDouble, ToDoubleFunction<T> toDouble,
            SubstituableCurve<T> curve) {
        return (x, y) -> toDouble.applyAsDouble(curve.substitute(fromDouble.apply(x), fromDouble.apply(y)));

    }

    /**
     * Returns a double-typed function which is adapted from a SubstituableCurve.
     * @param curve a curve
     * @return a function that accept {@link Point2D.Double} and returns double.
     */
    public static SubstitutableCurve mapToDouble(
            SubstituableCurve<Double> curve) {
        return curve::substitute;
    }
}
