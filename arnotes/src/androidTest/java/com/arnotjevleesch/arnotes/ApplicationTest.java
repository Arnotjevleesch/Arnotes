package com.arnotjevleesch.arnotes;

import android.app.Application;
import android.test.ApplicationTestCase;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
/*
    public void testName() throws Exception {
        Assert.assertTrue(true);

    }

    public void testQuickSortWithIndices() throws Exception {

        BigDecimal[] t = new BigDecimal[] {
                BigDecimal.valueOf(20),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(30),
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(40)
        };

        int[] indexes = Computing.triRapideIndex(t);

        Assert.assertEquals(t[0], BigDecimal.valueOf(10));
        Assert.assertEquals(t[1], BigDecimal.valueOf(20));
        Assert.assertEquals(t[2], BigDecimal.valueOf(30));
        Assert.assertEquals(t[3], BigDecimal.valueOf(40));
        Assert.assertEquals(t[4], BigDecimal.valueOf(50));

        Assert.assertEquals(indexes[0], 4);
        Assert.assertEquals(indexes[1], 1);
        Assert.assertEquals(indexes[2], 3);
        Assert.assertEquals(indexes[3], 5);
        Assert.assertEquals(indexes[4], 2);


    }
  */
}