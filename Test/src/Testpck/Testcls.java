package Testpck;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;


/* 
 
 
Remote Execution
cd /D C:\Users\pramod\eclipse-workspace\TestNG

set classpath=C:\Users\pramod\eclipse-workspace\TestNG\bin;C:\Users\pramod\eclipse-workspace\TestNG\lib\*

java org.testng.TestNG testng.xml C:\Users\pramod\eclipse-workspace\TestNG\testng.xml  
 
*HUB  -  java -jar E:\Selenium\selenium-server-standalone-2.53.1.jar -port 4444 -role hub -nodeTimeout 1000

NODE  -   
java -jar E:\Selenium\selenium-server-standalone-2.53.1.jar -role node -hub http://192.168.0.166:4444/grid/register -browser "browserName=chrome,version=ANY,platform=WINDOWS,maxInstances=20" -Dwebdriver.chrome.driver="E:\Selenium\chromedriver_win32\chromedriver.exe" -maxSession 4 -port 5556

in above commands change selenium jar location, chnage ip according to ur machin

Steps:
1. Navigate to Selenium satnd alone folder(in cms) and execute following command in one console
=>java -jar E:\Selenium\selenium-server-standalone-2.53.1.jar -port 4444 -role hub -nodeTimeout 1000

2.open another cmd , navigate to selenium jar path n execute following command
=>java -jar E:\Selenium\selenium-server-standalone-2.53.1.jar -role node -hub http://192.168.0.166:4444/grid/register -browser "browserName=chrome,version=ANY,platform=WINDOWS,maxInstances=20" -Dwebdriver.chrome.driver="E:\Selenium\chromedriver_win32\chromedriver.exe" -maxSession 4 -port 5556

3. open browser and browse "http://localhost:<4444>/grid/console"

4. Now keep both cmd open and make changes in code by removing setproprty line according and run code
*/
public class Testcls {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		Testcls obj=new Testcls();
		obj.TakeScreenshot();

	}	
	public WebDriver LaunchBrowser(String br) throws MalformedURLException {
		WebDriver driver;
		//System.setProperty("webdriver.chrome.driver","E:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		//driver=new ChromeDriver();
		
		//DesiredCapabilities cap=DesiredCapabilities.chrome();
		//cap.setBrowserName("chrome");
		//cap.setPlatform(Platform.WINDOWS);
		//String Node="http://192.168.0.166:5556/wd/hub";
		//driver=new RemoteWebDriver(new URL(Node),cap);
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		//HtmlUnitDriver : Headless Browser
		//driver=new HtmlUnitDriver();
		
		//PhontomJS : Headless Browser
		System.setProperty("phantomjs.binary.path", "E:\\Selenium\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
		driver=new PhantomJSDriver();
		
		return driver;
	}

	public void switchtoalert() throws InterruptedException, MalformedURLException {
		WebDriver driver_test;
		Testcls obj=new Testcls();
		driver_test=obj.LaunchBrowser("Chrome");
		driver_test.get("http://demo.guru99.com/selenium/delete_customer.php");
//		driver_test.findElement(By.name("cusid")).sendKeys("53920");					
//		driver_test.findElement(By.name("submit")).submit();
//		Thread.sleep(2000);
//		Alert al=driver_test.switchTo().alert();
//		System.out.println(al.getText());
//		al.accept();
		driver_test.close();
		
		
	}
	
	public void assertnverify() throws MalformedURLException {
		
		WebDriver driver_test;
		Testcls obj=new Testcls();
		driver_test=obj.LaunchBrowser("Chrome");
		driver_test.get("https://www.google.co.in/");
		String actual="Google1";
		String expected=driver_test.getTitle();
		try {
			Assert.assertEquals(actual, expected);
		}
		catch(Throwable  e) {
			System.out.println(e.getMessage());
		}
		
		driver_test.close();
	}
	public void implicitexplicitwait() throws MalformedURLException {
		WebDriver driver_test;
		Testcls obj=new Testcls();
		driver_test=obj.LaunchBrowser("Chrome");
		driver_test.get("https://www.google.co.in/");
		//driver_test.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver_test.findElement(By.xpath("//*[@id=\'lst-ib\']")).sendKeys("vrushali nagawade");
		
//		WebDriverWait wait=new WebDriverWait(driver_test,10);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'lst-ib\']")));
//		driver_test.findElement(By.xpath("//*[@id=\'lst-ib\']")).sendKeys("vrushali nagawade");
		
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver_test).withTimeout(10,TimeUnit.SECONDS).pollingEvery(5,TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'lst-ib\']"))).sendKeys("Vrushali Nagawade");;
		
		driver_test.close();
	}
	public String getcurrentdate() {
		Calendar cal=Calendar.getInstance(TimeZone.getDefault());
		
		int today=cal.get(cal.DAY_OF_MONTH)-1;
		System.out.println(today);
		
		String day=Integer.toString(today);
		return day;
	}
	public void setdate() throws MalformedURLException {
		
		WebDriver driver_test;
		Testcls obj=new Testcls();
		driver_test=obj.LaunchBrowser("Chrome");
		driver_test.get("https://www.makemytrip.com/");
		String day=obj.getcurrentdate();
		
		driver_test.findElement(By.xpath("//*[@id=\'hp-widget__depart\']")).click();
		Actions act=new Actions(driver_test);
		
		driver_test.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		WebElement pop=driver_test.findElement(By.xpath("//*[@id=\'hp-widget__depart\']"));
		driver_test.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		act.moveToElement(pop).build().perform();
		driver_test.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		driver_test.findElement(By.linkText(day)).click();
		driver_test.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		
		driver_test.close();
		
	}
	public void manualxpath() {
		//XPath locators in WebDriver always start with a double forward slash "//" and then followed by the parent element
		//All child elements in XPath are placed to the right of their parent element, separated with one forward slash "/" like the code shown below.
		//eg. //table/tbody/tr[2]/td[2]/table//tbody/tr/td[2]
		//use atrribute as predicate : Attributes are used as predicates by prefixing them with the @ symbol
		//Remember that when we put the XPath code in Java, we should use the escape character backward slash "\" for the double quotation marks on both sides of "270" so that the string argument of By.xpath() will not be terminated prematurely.
		//eg. //table[@width=\"270\"]/tbody/tr/td[4]
	}
	public void dropdownbox() throws MalformedURLException {
		WebDriver driver_test;
		Testcls obj=new Testcls();
		driver_test=obj.LaunchBrowser("Chrome");
		driver_test.get("http://demo.guru99.com/selenium/newtours/register.php");
		
		Select drp=new Select(driver_test.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[11]/td[2]/select")));
		
		drp.selectByVisibleText("AMERICAN SAMOA");
		driver_test.close();
	}
	
	public void dynamictable() throws MalformedURLException {
		
		WebDriver driver_test;
		Testcls obj=new Testcls();
		driver_test=obj.LaunchBrowser("Chrome");
		driver_test.get("http://money.rediff.com/gainers/bsc/daily/groupa?");
		
		List<WebElement> cols=driver_test.findElements(By.xpath("//*[@id=\'leftcontainer\']/table/thead/tr/th"));
		int col_no=cols.size();
		
		List<WebElement> rows=driver_test.findElements(By.xpath("//*[@id=\'leftcontainer\']/table/tbody/tr/td[1]/a"));
		int row_no=rows.size();
		
		System.out.println(col_no);
		System.out.println(row_no);
		driver_test.close();
		
	}
	public void TakeScreenshot() throws IOException{
		String path="E:\\Selenium\\test.png";
		WebDriver driver_test;
		Testcls obj=new Testcls();
		driver_test=obj.LaunchBrowser("Chrome");
		driver_test.get("http://money.rediff.com/gainers/bsc/daily/groupa?");
		driver_test.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		TakesScreenshot scrn=((TakesScreenshot)driver_test);
		File src=scrn.getScreenshotAs(OutputType.FILE);
		File dest=new File(path);
		FileUtils.copyFile(src, dest);
		driver_test.close();
		
	}
}
