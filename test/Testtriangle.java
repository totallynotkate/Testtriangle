import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Testtriangle {

    Rtriangle triangle;

    @Before
    public void setUp() {
        triangle = RtriangleProvider.getRtriangle();
    }

    /**
     * Test if the triangle provided by RtriangleProvider is right. This method accounts for integer overflow and
     * tests if the triangle provided can exist in Euclidean geometry.
     */
    @Test
    public void test() {
        //Use larger primitives to prevent integer overflow
        long apexX1 = triangle.getApexX1();
        long apexY1 = triangle.getApexY1();
        long apexX2 = triangle.getApexX2();
        long apexY2 = triangle.getApexY2();
        long apexX3 = triangle.getApexX3();
        long apexY3 = triangle.getApexY3();

        //Check the input for validity
        Assert.assertTrue(isTrianglePossible(apexX1, apexY1, apexX2, apexY2, apexX3, apexY3));

        /*
         * To prevent floating point precision issues the Pythagorean theorem is not used. Instead, check if
         * an angle is right by treating triangle sides as vectors and using the Dot Product formula:
         * vectorA • vectorB = |A| * |B| * cos(angleAB). Since it is already asserted that |A| != 0 && |B| != 0, the
         * only case when dot product equals 0 is when the cosine equals 0 and this only happens when the angle
         * equals 90 degrees, and the triangle is right. */

        long dotProduct1 = calculateVectorsDotProduct(apexX1, apexY1, apexX2, apexY2, apexX1, apexY1, apexX3, apexY3);
        long dotProduct2 = calculateVectorsDotProduct(apexX2, apexY2, apexX1, apexY1, apexX2, apexY2, apexX3, apexY3);
        long dotProduct3 = calculateVectorsDotProduct(apexX3, apexY3, apexX2, apexY2, apexX3, apexY3, apexX1, apexY1);
        Assert.assertTrue(dotProduct1 == 0 || dotProduct2 == 0 || dotProduct3 == 0);
    }

    private boolean isTrianglePossible(long apexX1, long apexY1, long apexX2, long apexY2, long apexX3, long apexY3) {
        //may be moved to the @Before method and stored as an array of triangle sides if needed for other tests
        double side12 = calculateTriangleSideLength(apexX1, apexY1, apexX2, apexY2);
        double side23 = calculateTriangleSideLength(apexX2, apexY2, apexX3, apexY3);
        double side31 = calculateTriangleSideLength(apexX3, apexY3, apexX1, apexY1);

        /*
         * Check if a triangle can exist in Euclidean geometry using the rule that any triangle side must be smaller
         * than the sum of two other sides. This method does not check if any of the sides is equal to 0 because if
         * it is, the sum of 0 and the smaller side is less or equal than the bigger side, hence the method will
         * return false anyway. The same applies to three dots lying on the same line: the bigger side will be equal to
         * the  sum of the smaller sides so the method will return false. */
        if (side12 >= side23 + side31 ||
                side23 >= side12 + side31 ||
                side31 >= side12 + side23) {
            return false;
        }
        return true;
    }

    private double calculateTriangleSideLength(long X1, long Y1, long X2, long Y2) {
        /* The length of a line is calculated as square root of ((x2 - x1)^2 + (y2 - y1)^2). The line is effectively
        * the hypotenuse of a right triangle whose legs are line components on the X and Y axes hence the standard
        * hypot function is applicable. */
        return Math.hypot((X2 - X1), (Y2 - Y1));
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