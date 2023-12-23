package AddingBagsEvenItemsOnly;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BagsTest extends Parameter {

	@BeforeTest
	public void SetUp() {
		SetUpTheWebSite();
	}

	@Test()
	public void SignUp() {
		SignUpProcess();
	}

	@Test()
	public void LogIn() {
		LogInProcess();
	}

	@Test()
	public void AddingTheBagesTest() throws InterruptedException {
		AddTheBagsEvenlyAndAssertTheNumbers();
	}

	@AfterTest
	public void End() {
	}

}
