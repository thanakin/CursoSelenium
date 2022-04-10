import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver; //import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {
	
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
	public void deveInteragirComAlertSimples() {	
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", texto);
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
	}
	
	@Test
	public void deveInteragirComAlertConfirm() {
		driver.findElement(By.id("confirm")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.accept();
		Assert.assertEquals("Confirmado", alerta.getText());
		alerta.accept();
		
		driver.findElement(By.id("confirm")).click();
		alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.dismiss();
		Assert.assertEquals("Negado", alerta.getText());
		alerta.dismiss();
	}
	
	@Test
	public void deveInteragirComAlertPrompt() {
		driver.findElement(By.id("prompt")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("12");
		alerta.accept();
		Assert.assertEquals("Era 12?", alerta.getText());
		alerta.accept();
		Assert.assertEquals(":D", alerta.getText());
		alerta.accept();
	}
	
}
