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

public class Parameter {
	WebDriver driver = new ChromeDriver();
	String WebSite = "https://magento.softwaretestingboard.com/";
	String TheFirstName = "Whatever";
	String TheLastName = "Whatever";
	String EamilAddress = "whatever@gmail.com";
	String PasswordCode = "RandomPassword";

	@BeforeTest
	public void SetUpTheWebSite() {
		driver.get(WebSite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	}

	@Test(priority = 1, enabled = false)
	public void SignUpProcess() {
		WebElement CreateAccount = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[3]/a"));
		CreateAccount.click();
		WebElement FirtsName = driver.findElement(By.id("firstname"));
		FirtsName.sendKeys(TheFirstName);
		WebElement LastName = driver.findElement(By.id("lastname"));
		LastName.sendKeys(TheLastName);
		WebElement Eamil = driver.findElement(By.name("email"));
		Eamil.sendKeys(EamilAddress);
		WebElement Password = driver.findElement(By.id("password"));
		Password.sendKeys(PasswordCode);
		WebElement ConfirmPassword = driver.findElement(By.name("password_confirmation"));
		ConfirmPassword.sendKeys(PasswordCode);
		WebElement CreateButton = driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div/div[1]/button"));
		CreateButton.click();
		WebElement TheLogo = driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/a/img"));
		TheLogo.click();
	}

	@Test(priority = 2, enabled = false)
	public void LogInProcess() {
		WebElement LogIn = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a"));
		LogIn.click();
		WebElement Eamil = driver.findElement(By.name("login[username]"));
		Eamil.sendKeys(EamilAddress);
		WebElement Password = driver.findElement(By.name("login[password]"));
		Password.sendKeys(PasswordCode);
		WebElement LogInButton = driver.findElement(By.xpath("//*[@id=\"send2\"]/span"));
		LogInButton.click();
	}

	@Test(priority = 3)
	public void AddTheBagsEvenlyAndAssertTheNumbers() throws InterruptedException {
		WebElement TheList = driver.findElement(By.xpath("//*[@id=\"ui-id-6\"]/span[1]"));
		TheList.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		WebElement Bags = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[4]/div[2]/div[2]/div/ul/li[1]/a"));
		Bags.click();
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,400)");
		List<WebElement> TheBagsList = driver.findElements(By.xpath("//li[@class=\"item product product-item\"]"));
		System.out.println(TheBagsList.size());
		for (int i = 1; i < TheBagsList.size(); i += 2) {
			WebElement TheItem = TheBagsList.get(i);
			TheItem.click();
			WebElement AddToCartButton = driver.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]/span"));
			AddToCartButton.click();
			Thread.sleep(2000);
			driver.navigate().back();
			TheBagsList = driver.findElements(By.xpath("//li[@class=\"item product product-item\"]"));
		}
		Thread.sleep(1000);
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollBy(0,-900)");
		WebElement CartNumberOfItems = driver
				.findElement(By.xpath("/html/body/div[2]/header/div[2]/div[1]/a/span[2]/span[1]"));
		int ItemInTheCart = Integer.parseInt(CartNumberOfItems.getText());
		int ActualHalfOfTheItem = TheBagsList.size() / 2;
		Assert.assertEquals(ActualHalfOfTheItem, ItemInTheCart);
	}

	@AfterTest
	public void End() {

	}

}
