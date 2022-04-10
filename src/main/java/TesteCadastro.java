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
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
	
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
	public void deveRealizarCadastroComSucesso() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Marcelo");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Martins");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Superior");
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Karate");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Marcelo"));
		Assert.assertEquals("Sobrenome: Martins", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Karate", driver.findElement(By.id("descEsportes")).getText());
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		driver.findElement(By.id("elementosForm:cadastrar")).click();	
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Fulano");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Fulano");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("da Silva");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
	}
	
	@Test
	public void deveValidarComidaVegetariana() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Fulano");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("da Silva");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
	}
	
	@Test
	public void deveValidarEsportistaIndeciso() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Fulano");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("da Silva");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
	}
	
}
