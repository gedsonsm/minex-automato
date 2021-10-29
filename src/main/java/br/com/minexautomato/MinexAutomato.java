package br.com.minexautomato;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinexAutomato {

	private static final Logger log = LoggerFactory.getLogger(MinexAutomato.class);
	private Properties props = new Properties();

	public MinexAutomato() throws IOException {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

		String path = "./loginInfo.properties";
		FileInputStream fis = new FileInputStream(path);
		BufferedReader in = new BufferedReader(new InputStreamReader(fis));

		props.load(in);
	}

	public void executeGetBonus() {
		boolean sucesso = false;

		for (int i = 0; i <= 5 && !sucesso; i++) {

			try {
				getBonus();

				sucesso = true;
			} catch (Exception e) {
				log.error("Error: {}", e);
			}
		}
	}

	private void getBonus() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		//options.addArguments("--incognito");

		WebDriver driver = new ChromeDriver(options);

		try {
			driver.get("https://minex.world/login/");
			TimeUnit.SECONDS.sleep(10);

			WebElement email = driver.findElement(By.id("InputEmail"));
			email.clear();
			email.sendKeys(props.getProperty("login"));

			WebElement senha = driver.findElement(By.id("InputPass"));
			senha.clear();
			senha.sendKeys(props.getProperty("password"));

			WebElement submeter = driver.findElement(By.cssSelector(".btn.btn-base.mt-4"));
			submeter.click();

			TimeUnit.SECONDS.sleep(10);

			WebElement bonuses = driver.findElement(By.linkText("Bonuses"));
			bonuses.click();

			TimeUnit.SECONDS.sleep(5);

			WebElement getBonus = driver.findElement(By.id("get-bonus"));
			getBonus.click();

			TimeUnit.SECONDS.sleep(10);

			WebElement exit = driver.findElement(By.linkText("Exit"));
			exit.click();

			TimeUnit.SECONDS.sleep(5);

			driver.quit();
		} catch (Exception e) {
			driver.quit();
			throw e;
		}
	}
}
