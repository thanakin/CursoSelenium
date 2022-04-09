import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {
	
	@Test
	public void teste() {
//		System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
//		WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
//		driver.manage().window().setPosition(new Point(100, 100)); // define posicao inicial
		driver.manage().window().setSize(new Dimension(640, 480)); // define tamanho do browser
//		driver.manage().window().maximize(); // maximiza a tela
//		driver.manage().window().minimize(); // minimiza a tela
		driver.get("https://www.google.com/");
		Assert.assertEquals("Google", driver.getTitle());
		driver.quit();
	}
}

