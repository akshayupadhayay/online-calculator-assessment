package com.xendit.TC;

import com.xendit.base.BasePage;
import com.xendit.pages.FullScreenCalculatorOffsetBasedEvents;
import org.testng.Assert;
import org.testng.annotations.*;

public class FullScreenCalculatorOffsetBasedEventsTest extends BasePage {

    FullScreenCalculatorOffsetBasedEvents calculator;

    public FullScreenCalculatorOffsetBasedEventsTest(){
        super();
    }

    @BeforeClass
    public void setUp(){
        initDriver();
        calculator = new FullScreenCalculatorOffsetBasedEvents();
    }

    @Test(priority = 1)
    public void TC1_calculatorLaunch() {
        Assert.assertTrue(calculator.calculatorLaunch());
    }

    @Test(priority = 2)
    public void TC2_calculatorVisibility(){
        Assert.assertTrue(calculator.calculatorVisibility());
    }

    @Test(priority = 3)
    public void TC3_getTitle(){
        String currentTitle = calculator.getTitle();
        Assert.assertEquals(currentTitle, "Full Screen Calculator - Online Calculator");
    }

    /**
     * Positive number subtraction
     */
    @Test(priority = 4, groups = {"Subtraction"})
    public void TC4_subtraction()  {
        calculator.performArithmetic("subtract", 3, 1);
        calculator.computeResult();
        calculator.clearResult();
    }

    /**
     * Negative number subtraction
     */
    @Test(priority = 5, groups = {"Subtraction"})
    public void TC5_subtraction()  {
        calculator.performNegativeArithmetic("subtract", "-", 3, -1);
        calculator.computeResult();
        calculator.clearResult();
    }

    /**
     * Positive and over Boundary number addition
     */
    @Test(priority = 6, groups = {"Addition"})
    public void TC6_addition()  {
        calculator.performArithmetic("add", 999999999, 1);
        calculator.computeResult();
        calculator.clearResult();
    }

    /**
     * Positive and under Boundary number addition
     */
    @Test(priority = 7, groups = {"Addition"})
    public void TC7_addition()  {
        calculator.performArithmetic("add", 999999998, 1);
        calculator.computeResult();
        calculator.clearResult();
    }

    /**
     * Negative boundary addition
     */
    @Test(priority = 8, groups = {"Addition"})
    public void TC8_addition()  {
        calculator.performNegativeArithmetic("add", "-",-999999999, 1);
        calculator.computeResult();
        calculator.clearResult();
    }

    /**
     * Natural numbers division
     */
    @Test(priority = 9, groups = {"Division"})
    public void TC9_division()  {
        calculator.performArithmetic("divide", 123, 2);
        calculator.computeResult();
        calculator.clearResult();
    }

    /**
     * Undefined division, denominator as zero
     */
    @Test(priority = 10, groups = {"Division"})
    public void TC10_division()  {
        calculator.performArithmetic("divide", 123, 0);
        calculator.computeResult();
        calculator.clearResult();
    }

    /**
     * Division with zero as numerator
     */
    @Test(priority = 11, groups = {"Division"})
    public void TC11_division()  {
        calculator.performArithmetic("divide", 0, 2);
        calculator.computeResult();
        calculator.clearResult();
    }

    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }

}
