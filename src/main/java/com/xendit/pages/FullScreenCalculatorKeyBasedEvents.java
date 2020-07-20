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

public class FullScreenCalculatorKeyBasedEvents extends BasePage{
    /**
     * Logger for info logs, WebDriverWait for Frame & canvas to load and Action API to move to buttons and click
     */
    Logger logger = LogManager.getLogger(FullScreenCalculatorOffsetBasedEvents.class.getName());
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
    public FullScreenCalculatorKeyBasedEvents(){
        logger.info("Driver initialized");
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
            logger.info("Online calculator is launched");
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
        logger.info("Checking visibility of frame and canvas");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorFrame));
        wait.until(ExpectedConditions.elementToBeClickable(calculatorCanvas));
        return calculatorCanvas.isDisplayed();
    }

    /**
     * Validate the title of the webpage
     * @return webpage title status
     */
    public String getTitle(){ logger.info("Get page title"); return driver.getTitle(); }


    /**
     * Switch case for num keys desired positive value calculations
     * @param keyPressed    Is a the number key pressed by user either through mouse clicks or through numpad
     */
    public void doPositiveCalculations(int keyPressed) {
        logger.info("Waiting for key click event");
        click = new Actions(driver);
        switch (keyPressed) {

                case 9: click.sendKeys(Keys.NUMPAD9).build().perform();
                break; case 6: click.sendKeys(Keys.NUMPAD6).build().perform();
                break; case 3: click.sendKeys(Keys.NUMPAD3).build().perform();
                break; case 7: click.sendKeys(Keys.NUMPAD7).build().perform();
                break; case 4: click.sendKeys(Keys.NUMPAD4).build().perform();
                break; case 1: click.sendKeys(Keys.NUMPAD1).build().perform();
                break; case 0: click.sendKeys(Keys.NUMPAD0).build().perform();
                break; case 8: click.sendKeys(Keys.NUMPAD8).build().perform();
                break; case 5: click.sendKeys(Keys.NUMPAD5).build().perform();
                break; case 2: click.sendKeys(Keys.NUMPAD2).build().perform();
        }
    }

    /**
     * Switch case for num keys desired negative value calculations
     * @param keyPressed    Is a the number key pressed by user either through mouse clicks or through numpad
     */
    public void doNegativeCalculations(String negativeValue, int keyPressed) {
        logger.info("Waiting for key click event");
        click = new Actions(driver);

        switch (negativeValue) {
			case "subtract": click.moveToElement(calculatorCanvas, 0, 0).moveByOffset((267 / 5) * 2,(345 / 6) * 3).click().build().perform();
			break;
		}

        switch (keyPressed) {

                case 9: click.sendKeys(Keys.NUMPAD9).click().build().perform();
                break; case 6: click.sendKeys(Keys.NUMPAD6).click().build().perform();
                break; case 3: click.sendKeys(Keys.NUMPAD3).click().build().perform();
                break; case 7: click.sendKeys(Keys.NUMPAD7).click().build().perform();
                break; case 4: click.sendKeys(Keys.NUMPAD4).click().build().perform();
                break; case 1: click.sendKeys(Keys.NUMPAD1).click().build().perform();
                break; case 0: click.sendKeys(Keys.NUMPAD0).click().build().perform();
                break; case 8: click.sendKeys(Keys.NUMPAD8).click().build().perform();
                break; case 5: click.sendKeys(Keys.NUMPAD5).click().build().perform();
                break; case 2: click.sendKeys(Keys.NUMPAD2).click().build().perform();
        }


    }

    /**
     * Switch case for desired type of calculations
     * @param operation Is a the function key pressed by user either through mouse clicks or through numpad
     */
    public void selectFunctionality(String operation) {
        logger.info("Waiting for operation key event");
		click = new Actions(driver);
		switch (operation) {

		    case "divide": click.sendKeys(Keys.DIVIDE).click().build().perform();
			break; case "add": click.sendKeys(Keys.ADD).click().build().perform();
			break; case "subtract": click.sendKeys(Keys.SUBTRACT).click().build().perform();
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
	    logger.info("Performing arithmetic operation");
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
	    logger.info("Performing arithmetic operation");
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
	    logger.info("Performing arithmetic operation when input value is not single digit");
		if (number > 0) {
			multipleDigits(number / 10);
			doPositiveCalculations(number % 10);
		}
	}

    /**
     * FinaL result of calculations performed by clicking on Equals(=) sign and then clearing the result
     */
	public void computeResult() {
	    logger.info("Performing arithmetic operation to evaluate results");
		click = new Actions(driver);
		// Equals '='
        waitForElement(calculatorCanvas, 1000);
		click.sendKeys(Keys.EQUALS).click().build().perform();
	}

    /**
     * Clear result console
     */
	public void clearResult() {
	    logger.info("Clearing result console");
	    click = new Actions(driver);
		// CE
        waitForElement(calculatorCanvas, 1000);
        click.sendKeys(Keys.SHIFT, "C").click().build().perform();
    }

}

