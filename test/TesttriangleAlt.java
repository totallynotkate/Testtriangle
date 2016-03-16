import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TesttriangleAlt {

    private ThreadLocal<Rtriangle> triangle;

    @Before
    public void setUp() {
        triangle = new ThreadLocal<Rtriangle>() {
            @Override
            protected Rtriangle initialValue() {
                return RtriangleProvider.getRtriangle();
            }
        };
    }

    /**
     * Test if the triangle provided by RtriangleProvider is right. This method accounts for integer overflow and
     * tests if the triangle provided can exist in Euclidean geometry.
     */
    @Test
    public void test() {
        //Use larger primitives to prevent integer overflow
        long apexX1 = triangle.get().getApexX1();
        long apexY1 = triangle.get().getApexY1();
        long apexX2 = triangle.get().getApexX2();
        long apexY2 = triangle.get().getApexY2();
        long apexX3 = triangle.get().getApexX3();
        long apexY3 = triangle.get().getApexY3();

        //Check the input for validity
        Assert.assertTrue(isTrianglePossible(apexX1, apexY1, apexX2, apexY2, apexX3, apexY3));

        /*
         * To prevent floating point precision issues the Pythagorean theorem is not used. Instead, this method
         * checks if an angle is right by treating triangle sides as vectors and using the Dot Product formula:
         * vectorA • vectorB = |A| * |B| * cos(angleAB). Since it is already asserted that |A| != 0 && |B| != 0, the
         * only case when dot product equals 0 is when the cosine equals 0 and this only happens when the angle
         * equals 90 degrees, and the triangle is right. */

        long dotProduct1 = calculateVectorsDotProduct(apexX1, apexY1, apexX2, apexY2, apexX1, apexY1, apexX3, apexY3);
        long dotProduct2 = calculateVectorsDotProduct(apexX2, apexY2, apexX1, apexY1, apexX2, apexY2, apexX3, apexY3);
        long dotProduct3 = calculateVectorsDotProduct(apexX3, apexY3, apexX2, apexY2, apexX3, apexY3, apexX1, apexY1);
        Assert.assertTrue(dotProduct1 == 0 || dotProduct2 == 0 || dotProduct3 == 0);
    }

    private boolean isTrianglePossible(long apexX1, long apexY1, long apexX2, long apexY2, long apexX3, long apexY3) {
        //If three dots don't lie on the same line they can always form a triangle
        return !doApexesLieOnOneLine(apexX1, apexY1, apexX2, apexY2, apexX3, apexY3);
    }

    private boolean doApexesLieOnOneLine(long apexX1, long apexY1, long apexX2, long apexY2,
                                         long apexX3, long apexY3) {
        /* To determine whether apexes lie on one line the line slope formula is used. If the slopes of the lines
        * apex1apex2 and apex1apex3 are equal the apexes lie on the same line.
        * The line slope can be calculated as (y2 - y1)/(x2 - x1) where it doesn't actually matter if x2, y2 are the
        * coordinates of the endpoint or the starting point. In order to avoid the issues of floating point
        * calculation precision and, more importantly, to avoid division by 0 which happens if the apexes x
        * coordinates are equal, the formula is transformed the following way:
        * if (y2 - y1)/(x2 - x1) == (y3 - y1)/(x3 - x1) then (y2 - y1)(x3 - x1) == (y3 - y1)(x2 - x1)
        * */
        return ((apexY2 - apexY1) * (apexX3 - apexX1) == (apexY3 - apexY1) * (apexX2 - apexX1));
    }

    private long calculateVectorsDotProduct(long vector1StartX, long vector1StartY, long vector1EndX, long vector1EndY,
                                            long vector2StartX, long vector2StartY, long vector2EndX, long vector2EndY) {
        /*
         * To calculate the dot product this method uses another version  of the dot product formula:
         * vectorA<ax, ay> • vectorB<bx, by> =  ax * bx + ay * by. Note that numbers in angle brackets are not
         * coordinates but vector components calculated as <endpointX - startingPointX, endpointY - startingPointY> */
        return (vector1EndX - vector1StartX) * (vector2EndX - vector2StartX) + (vector1EndY - vector1StartY) *
                (vector2EndY - vector2StartY);
    }
}