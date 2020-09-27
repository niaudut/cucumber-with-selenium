package step_definitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefinition {
	public WebDriver driver;
	WebDriverWait wait;

	public LoginStepDefinition() {
		driver = Hooks.driver;
	}

	@Given("^user open the web page$")
	public void user_open_the_web_page() throws Throwable {
		driver.manage().window().maximize();
		driver.get("https://demo.opensourcepos.org/login");
		wait = new WebDriverWait(driver, 10);
		wait.ignoring(WebDriverException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='username']")));
	}

	@When("^user input invalid {string} and {string}")
	public void user_input_invalid_username_and_password(String username, String password) throws Throwable {
		wait = new WebDriverWait(driver, 10);
		wait.ignoring(WebDriverException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='username']")));
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='loginButton']")).click();
	}

	@Then("^user see error message")
	public void user_see_error_message() throws Throwable {
		String errorMessage = driver.findElement(By.xpath("//div[@class='error']")).getText();
		Assert.assertEquals("Invalid Username or Password.", errorMessage);
	}
}
