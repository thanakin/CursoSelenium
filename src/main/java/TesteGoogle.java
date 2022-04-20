import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {
	
	private WebDriver driver;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(640, 480));
//		System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
//		WebDriver driver = new FirefoxDriver();
//		driver.manage().window().setPosition(new Point(100, 100)); // define posicao inicial
//		driver.manage().window().maximize(); // maximiza a tela
//		driver.manage().window().minimize(); // minimiza a tela
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void teste() {
		driver.get("https://www.google.com/");
		Assert.assertEquals("Google", driver.getTitle());
	}
}

