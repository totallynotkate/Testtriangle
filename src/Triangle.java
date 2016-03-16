/**
 * Created by totallynotkate on 11.03.16.
 */
public class Triangle implements Rtriangle {
    int apexX1;
    int apexY1;
    int apexX2;
    int apexY2;
    int apexX3;
    int apexY3;

    public Triangle(int apexX1, int apexY1, int apexX2, int apexY2, int apexX3, int apexY3) {
        this.apexX1 = apexX1;
        this.apexY1 = apexY1;
        this.apexX2 = apexX2;
        this.apexY2 = apexY2;
        this.apexX3 = apexX3;
        this.apexY3 = apexY3;
    }

    @Override
    public int getApexX1() {
        return apexX1;
    }

    @Override
    public int getApexY1() {
        return apexY1;
    }

    @Override
    public int getApexX2() {
        return apexX2;
    }

    @Override
    public int getApexY2() {
        return apexY2;
    }

    @Override
    public int getApexX3() {
        return apexX3;
    }

    @Override
    public int getApexY3() {
        return apexY3;
    }
}
