#Testtriangle
test task for QA automation school
##The task

######Description

You are given an interface
```java
public interface Rtriangle {   
    int getApexX1();   
    int getApexY1();   
    int getApexX2();   
    int getApexY2();   
    int getApexX3();   
    int getApexY3();   
}
```
The methods return 6 whole numbers which represent the coordinates of three apexes of a right triangle in a Cartesian plane. You have a method which is supposed to return a right triangle:
```java
public final class RtriangleProvider {   
    public static Rtriangle getRtriangle() {   
        ...   
    }   
}
```
You need to write the code of one JUnit test which checks if the method getRtriangle returns a right triangle.

######Input

The example of the code to get a triangle instance:
```java
import org.junit.Before;  
import org.junit.Test;  

public class Testtriangle {  

    @Before  
    public void setUp() {  
        Rtriangle triangle = RtriangleProvider.getRtriangle();  
    }  

    @Test  
    public void test() {  
    }  
}
```
######Output

The test should fail if the triangle isn't right and it should pass without any exceptions and comments if it is right.

##Test results:

Tested code|Test ideas|Test system output
-----------|----------|------------------
Testtriangle.java|Check if triangle can exist by calculating the lengths of the sides and assuring that neither is >= than the sum of the other two|Failed at test 33
TesttriangleAlt.java|Check if a triangle can exist by assuring the dots do not lie on the same line|Failed at test 33
TesttriangleAlt.java|Check if the issue with test 33 is caused by triangle modification in other threads. Unfortunately, code refactoring introduced a logical bug with boolean return value, therefore the test results don't give any useful information.|Failed 23 of 33 tests, including test 33
TesttriangleAlt.java|Add an additional check for NPE|Passed all the tests
