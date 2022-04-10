import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesEJanelas {
	
	private WebDriver driver;
	
	@Before
	public void inicializar() {
		driver = new ChromeDriver(); //WebDriver driver = new FirefoxDriver();
		driver.manage().window().setPosition(new Point(1380, 0)); 
		driver.manage().window().setSize(new Dimension(900, 800));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finalizar() {
		driver.quit();
	}

	@Test
	public void deveInteragirComFrames() {
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);;
	}
	

	@Test
	public void deveInteragirComJanelas() {
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {
		driver.findElement(By.id("buttonPopUpHard")).click();
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
	}
	
}
