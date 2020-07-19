package com.xendit.pages;

import com.xendit.base.BasePage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.xendit.util.utils;

public class FullScreenCalculator extends BasePage{
    /**
     * Logger for info logs, WebDriverWait for Frame & canvas to load and Action API to move to buttons and click
     */
    Logger logger = LogManager.getLogger(FullScreenCalculator.class.getName());
    WebDriverWait wait;
    Actions click;


    /**
     * Page Factory with @CacheLookup for faster retrieval of frame and canvas from DOM
     */
    @CacheLookup
    @FindBy(id = "fullframe") WebElement calculatorFrame;

    @CacheLookup
    @FindBy(id = "canvas") WebElement calculatorCanvas;


    /**
     * Initialize WebDriver and WebDriverWait
     */
    public FullScreenCalculator(){
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver,utils.EXPLICIT_WAIT);
    }


    /**
    * wait until expected element is visible
    *
    * @param   element     element to be expected
    * @param   timeout     Maximum timeout time in milliseconds
    */
    public static void waitForElement(WebElement element, int timeout) {
        try {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));

        } catch (Exception e) {
        e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Validate that online calculator was successfully launched
      * @return validate url status
     */
    public boolean calculatorLaunch(){
            String calculatorUrl = prop.getProperty("onlineCalculatorUrl");
        try {
            driver.get(calculatorUrl);
            return driver.getCurrentUrl().equals(prop.getProperty("onlineCalculatorUrl"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Validate the Canvas for calculator for visible for action
     * @return calculator DOM visibility status
     */
    public boolean calculatorVisibility(){
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorFrame));
        wait.until(ExpectedConditions.elementToBeClickable(calculatorCanvas));
        return calculatorCanvas.isDisplayed();
    }

    /**
     * Validate the title of the webpage
     * @return webpage title status
     */
    public String getTitle(){ return driver.getTitle(); }


    /**
     * Switch case for num keys desired positive value calculations
     * @param keyPressed    Is a the number key pressed by user either through mouse clicks or through numpad
     */
    public void doPositiveCalculations(int keyPressed) {
        click = new Actions(driver);
        switch (keyPressed) {

                case 9: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(0, -(345 / 6) * 1).click().build().perform();
                break; case 6: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(0, (345 / 6) * 1).click().build().perform();
                break; case 3: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(0,(345/6)*3).click().build().perform();
                break; case 7: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(-(267 / 5) * 4, -(345 / 6) * 1).click().build().perform();
                break; case 4: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(-(267 / 5) * 4, (345 / 6) * 1).click().build().perform();
                break; case 1: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(-(267 / 5) * 4, (345 / 6) * 3).click().build().perform();
                break; case 0: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(-(267 / 5) * 4, (345 / 6) * 5).click().build().perform();
                break; case 8: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(-(267 / 5) * 2, -(345 / 6) * 1).click().build().perform();
                break; case 5: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(-(267 / 5) * 2, (345 / 6) * 1).click().build().perform();
                break; case 2: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(-(267 / 5) * 2, (345 / 6) * 3).click().build().perform();
        }
    }

    /**
     * Switch case for num keys desired negative value calculations
     * @param keyPressed    Is a the number key pressed by user either through mouse clicks or through numpad
     */
    public void doNegativeCalculations(String negativeValue, int keyPressed) {
        click = new Actions(driver);

        switch (negativeValue) {
			case "subtract": click.moveToElement(calculatorCanvas, 0, 0).moveByOffset((267 / 5) * 2,(345 / 6) * 3).click().build().perform();
			break;
		}

        switch (keyPressed) {

                case 9: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(0, -(345 / 6) * 1).click().build().perform();
                break; case 6: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(0, (345 / 6) * 1).click().build().perform();
                break; case 3: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(0,(345/6)*3).click().build().perform();
                break; case 7: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(-(267 / 5) * 4, -(345 / 6) * 1).click().build().perform();
                break; case 4: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(-(267 / 5) * 4, (345 / 6) * 1).click().build().perform();
                break; case 1: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(-(267 / 5) * 4, (345 / 6) * 3).click().build().perform();
                break; case 0: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(-(267 / 5) * 4, (345 / 6) * 5).click().build().perform();
                break; case 8: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(-(267 / 5) * 2, -(345 / 6) * 1).click().build().perform();
                break; case 5: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(-(267 / 5) * 2, (345 / 6) * 1).click().build().perform();
                break; case 2: click.moveToElement(calculatorCanvas, 0, 0).moveByOffset(-(267 / 5) * 2, (345 / 6) * 3).click().build().perform();
        }


    }

    /**
     * Switch case for desired type of calculations
     * @param operation Is a the function key pressed by user either through mouse clicks or through numpad
     */
    public void selectFunctionality(String operation) {
		click = new Actions(driver);
		switch (operation) {

		    case "divide": click.moveToElement(calculatorCanvas, 0, 0).moveByOffset((267 / 5) * 2, -(345 / 6) * 1).click().build().perform();
			break; case "add": click.moveToElement(calculatorCanvas, 0, 0).moveByOffset((267 / 5) * 2, (345 / 6) * 4).click().build().perform();
			break; case "subtract": click.moveToElement(calculatorCanvas, 0, 0).moveByOffset((267 / 5) * 2,(345 / 6) * 3).click().build().perform();
			break;
		}

	}

    /**
     * Function to validate single digit operation or multiple digits
     * @param operation Is a the function key pressed by user either through mouse clicks or through numpad
     * @param numOne      Is a the number key pressed by user either through mouse clicks or through numpad
     * @param numTwo      Is a the number key pressed by user either through mouse clicks or through numpad
     */
	public void performArithmetic(String operation, int numOne, int numTwo) {
		logger.info("First Number Pressed : " + numOne);
		if (numOne < 9)
			doPositiveCalculations(numOne);
		else {
			multipleDigits(numOne);
		}

		logger.info("Operation: " + operation);
		selectFunctionality(operation);
		logger.info("Second Number Pressed : " + numTwo);
		if (numTwo < 9)
			doPositiveCalculations(numTwo);
		else {
			multipleDigits(numTwo);
		}
	}

	/**
     * Function to validate single digit operation or multiple digits
     * @param operation     Is a the function key pressed by user either through mouse clicks or through numpad
     * @param negativeValue Denotes the negative value of the number
     * @param numOne        Is a the number key pressed by user either through mouse clicks or through numpad
     * @param numTwo        Is a the number key pressed by user either through mouse clicks or through numpad
     */
	public void performNegativeArithmetic(String operation, String negativeValue,  int numOne, int numTwo) {
		logger.info("First Number Pressed : " + numOne);
		if (numOne < 9)
			doNegativeCalculations(negativeValue, numOne);
		else {
			multipleDigits(numOne);
		}

		logger.info("Operation: " + operation);
		selectFunctionality(operation);
		logger.info("Second Number Pressed : " + numTwo);
		if (numTwo < 9)
			doNegativeCalculations(negativeValue, numTwo);
		else {
			multipleDigits(numTwo);
		}
	}

    /**
     * If user entered numbers which are more than one digit then perform a recursive click operation
     * @param number    Any number which is not single digit
     */
	private void multipleDigits(int number) {
		if (number > 0) {
			multipleDigits(number / 10);
			doPositiveCalculations(number % 10);
		}
	}

    /**
     * FinaL result of calculations performed by clicking on Equals(=) sign and then clearing the result
     */
	public void computeResult() {
		click = new Actions(driver);
		// Equals '='
        waitForElement(calculatorCanvas, 1000);
		click.moveToElement(calculatorCanvas, 0, 0).moveByOffset((267 / 5) * 4, (345 / 6) * 4).click().build().perform();
	}

    /**
     * Clear result console
     */
	public void clearResult() {
	    click = new Actions(driver);
		// CE
        waitForElement(calculatorCanvas, 1000);
        click.moveToElement(calculatorCanvas, 0, 0).moveByOffset((267 / 5) * 4, -(345 / 6) * 2).click().build().perform();
    }

}
