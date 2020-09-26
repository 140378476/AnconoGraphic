/**
 * 2017-11-16
 */
package cn.ancono.math.geometry.visual2D;

import cn.ancono.math.MathUtils;

/**
 * @author liyicheng
 * 2017-11-16 20:51
 */
public class SimpleDrawPointPredicate implements DrawPointPredicate {
    static final double DEFAULT_PRECISION = 1E-9;
    protected final double precision;

    /**
     *
     */
    SimpleDrawPointPredicate(double precision) {
        this.precision = precision;
    }

    /**
     *
     */
    SimpleDrawPointPredicate() {
        this.precision = DEFAULT_PRECISION;
    }

    private static final SimpleDrawPointPredicate DEFAULT = new SimpleDrawPointPredicate();

    /*
     * @see cn.ancono.math.visual.DrawPointPredicate#shouldDraw(double[][], int, int)
     */
    @Override
    public boolean shouldDraw(double[][] data, int x, int y) {
        boolean should = false;
        double cur = data[x][y];
        double curAbs = Math.abs(cur);
        if (Math.abs(cur) <= precision) {
            should = true;
        }
        if (!should && !Double.isNaN(cur)) {
            double f = data[x - 1][y];
            if (!Double.isNaN(f) && MathUtils.oppositeSignum(cur, f) && curAbs < Math.abs(f)) {
                should = true;
            }
            if (!should) {
                f = data[x + 1][y];
                if (!Double.isNaN(f) && MathUtils.oppositeSignum(cur, f) && curAbs < Math.abs(f)) {
                    should = true;
                }
            }
            if (!should) {
                f = data[x][y - 1];
                if (!Double.isNaN(f) && MathUtils.oppositeSignum(cur, f) && curAbs < Math.abs(f)) {
                    should = true;
                }
            }
            if (!should) {
                f = data[x][y + 1];
                if (!Double.isNaN(f) && MathUtils.oppositeSignum(cur, f) && curAbs < Math.abs(f)) {
                    should = true;
                }
            }
        }
        return should;
    }

    /**
     * Returns a SimpleDrawPointPredicate with the given precision.
     *
     * @param pre
     * @return
     */
    public static SimpleDrawPointPredicate ofPrecision(double pre) {
        return new SimpleDrawPointPredicate(Math.abs(pre));
    }

    public static SimpleDrawPointPredicate getDefault() {
        return DEFAULT;
    }


}
