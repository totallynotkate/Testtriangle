/**
 * Created by totallynotkate on 11.03.16.
 */
public class Triangle implements Rtriangle {
    int ApexX1;
    int ApexY1;
    int ApexX2;
    int ApexY2;
    int ApexX3;
    int ApexY3;

    public Triangle(int apexX1, int apexY1, int apexX2, int apexY2, int apexX3, int apexY3) {
        ApexX1 = apexX1;
        ApexY1 = apexY1;
        ApexX2 = apexX2;
        ApexY2 = apexY2;
        ApexX3 = apexX3;
        ApexY3 = apexY3;
    }

    @Override
    public int getApexX1() {
        return ApexX1;
    }

    @Override
    public int getApexY1() {
        return ApexY1;
    }

    @Override
    public int getApexX2() {
        return ApexX2;
    }

    @Override
    public int getApexY2() {
        return ApexY2;
    }

    @Override
    public int getApexX3() {
        return ApexX3;
    }

    @Override
    public int getApexY3() {
        return ApexY3;
    }
}
