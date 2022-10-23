package readQRCode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;



public class AutomateQRCode {
	
	@Test
	public void barCode() throws IOException, NotFoundException{
		
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://testautomationpractice.blogspot.com/");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		((JavascriptExecutor)driver).executeScript("window.scroll(0, 3000);");
		
		String qRCodeURL=driver.findElement(By.xpath("//h2[text()='QR Code']/following::img[1]")).getAttribute("src");
		System.out.println(qRCodeURL);
		
		URL url=new URL(qRCodeURL);
		BufferedImage bufferedImage=ImageIO.read(url);
		
		LuminanceSource  luminanceSource=new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(luminanceSource));
		
		Result result=new MultiFormatReader().decode(binaryBitmap);
		System.out.println("QRCode Value is: "+ result.getText());
		
		
	}

}
