package com.xendit.TC;

import com.xendit.base.BasePage;
import com.xendit.pages.FullScreenCalculatorKeyBasedEvents;
import org.testng.Assert;
import org.testng.annotations.*;

public class FullScreenCalculatorKeyBasedEventsTest extends BasePage {

    FullScreenCalculatorKeyBasedEvents calculatorKeyBasedEvents;

    public FullScreenCalculatorKeyBasedEventsTest(){ super(); }

    @BeforeClass
    public void setUp(){
        initDriver();
        calculatorKeyBasedEvents = new FullScreenCalculatorKeyBasedEvents();
    }

    @Test(priority = 1)
    public void TC1_calculatorLaunch() {
        Assert.assertTrue(calculatorKeyBasedEvents.calculatorLaunch());
    }

    @Test(priority = 2)
    public void TC2_calculatorVisibility(){
        Assert.assertTrue(calculatorKeyBasedEvents.calculatorVisibility());
    }

    @Test(priority = 3)
    public void TC3_getTitle(){
        String currentTitle = calculatorKeyBasedEvents.getTitle();
        Assert.assertEquals(currentTitle, "Full Screen Calculator - Online Calculator");
    }

    /**
     * Positive number subtraction
     */
    @Test(priority = 4, groups = {"Subtraction"})
    public void TC4_subtraction()  {
        calculatorKeyBasedEvents.performArithmetic("subtract", 3, 1);
        calculatorKeyBasedEvents.computeResult();
        calculatorKeyBasedEvents.clearResult();
    }

    /**
     * Negative number subtraction
     */
    @Test(priority = 5, groups = {"Subtraction"})
    public void TC5_subtraction()  {
        calculatorKeyBasedEvents.performNegativeArithmetic("subtract", "-", 3, -1);
        calculatorKeyBasedEvents.computeResult();
        calculatorKeyBasedEvents.clearResult();
    }

    /**
     * Positive and over Boundary number addition
     */
    @Test(priority = 6, groups = {"Addition"})
    public void TC6_addition()  {
        calculatorKeyBasedEvents.performArithmetic("add", 999999999, 1);
        calculatorKeyBasedEvents.computeResult();
        calculatorKeyBasedEvents.clearResult();
    }

    /**
     * Positive and under Boundary number addition
     */
    @Test(priority = 7, groups = {"Addition"})
    public void TC7_addition()  {
        calculatorKeyBasedEvents.performArithmetic("add", 999999998, 1);
        calculatorKeyBasedEvents.computeResult();
        calculatorKeyBasedEvents.clearResult();
    }

    /**
     * Negative boundary addition
     */
    @Test(priority = 8, groups = {"Addition"})
    public void TC8_addition()  {
        calculatorKeyBasedEvents.performNegativeArithmetic("add", "-",-999999999, 1);
        calculatorKeyBasedEvents.computeResult();
        calculatorKeyBasedEvents.clearResult();
    }

    /**
     * Natural numbers division
     */
    @Test(priority = 9, groups = {"Division"})
    public void TC9_division()  {
        calculatorKeyBasedEvents.performArithmetic("divide", 123, 2);
        calculatorKeyBasedEvents.computeResult();
        calculatorKeyBasedEvents.clearResult();
    }

    /**
     * Undefined division, denominator as zero
     */
    @Test(priority = 10, groups = {"Division"})
    public void TC10_division()  {
        calculatorKeyBasedEvents.performArithmetic("divide", 123, 0);
        calculatorKeyBasedEvents.computeResult();
        calculatorKeyBasedEvents.clearResult();
    }

    /**
     * Division with zero as numerator
     */
    @Test(priority = 11, groups = {"Division"})
    public void TC11_division()  {
        calculatorKeyBasedEvents.performArithmetic("divide", 0, 2);
        calculatorKeyBasedEvents.computeResult();
        calculatorKeyBasedEvents.clearResult();
    }

    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }

}

