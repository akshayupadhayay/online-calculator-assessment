package com.xendit.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.xendit.util.utils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	Logger logger = LogManager.getLogger(BasePage.class.getName());
	public static WebDriver driver;
	public static Properties prop;

	public BasePage() {
		prop = new Properties();
		try {
			FileInputStream conf_file = new FileInputStream("src/main/java/com/xendit/config/config.properties");
			prop.load(conf_file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initDriver() {
		String Log4jConfigPath="log4j.properties";
		PropertyConfigurator.configure(Log4jConfigPath);
		String browserName = prop.getProperty("browser");

		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("Please pass valid browser name :"+browserName);
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(utils.IMPLICIT_WAIT,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(utils.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
	}

}
