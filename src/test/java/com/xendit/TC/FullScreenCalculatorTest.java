package com.xendit.TC;

import com.xendit.base.BasePage;
import com.xendit.pages.FullScreenCalculator;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FullScreenCalculatorTest extends BasePage {

    FullScreenCalculator calculator;

    public FullScreenCalculatorTest(){
        super();
    }

    @BeforeTest
    public void setUp(){
        initDriver();
        calculator = new FullScreenCalculator();
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

    @Test(priority = 4)
    public void TC4_subtraction()  {
        calculator.performArithmetic("subtract", 3, 1);
        calculator.computeResult();
    }

    @Test(priority = 5)
    public void TC5_addition()  {
        calculator.performArithmetic("add", 3, 1);
        calculator.computeResult();
    }

    @Test(priority = 6)
    public void TC6_division()  {
        calculator.performArithmetic("divide", 123, 2);
        calculator.computeResult();
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }

}
