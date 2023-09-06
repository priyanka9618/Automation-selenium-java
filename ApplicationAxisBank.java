package PracticeAppl;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ApplicationAxisBank {


    public static void main(String[] args) throws IOException {
        // Set the path to the ChromeDriver
    	  System.getProperty("webdriver.chrome.driver","C:\\Users\\gpriy\\Downloads\\chrome-win64\\chrome-win64\\chromedriver.exe");
  		ChromeOptions options=new ChromeOptions () ;
  		options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
  		options. addArguments("--remote-allow-origins=*") ;
  		options.addArguments("--disable-notifications");
  		options.addArguments("--blink-settings=imagesEnabled=false");
  		  options.addArguments("--disable-extensions");
  	        options.addArguments("--disable-gpu");
  	        options.addArguments("--no-sandbox");
  	        options.addArguments("--disable-dev-shm-usage");
  		WebDriver driver=new ChromeDriver(options);
  		Actions action = new Actions(driver);
        
        // Maximize the browser window
        driver.manage().window().maximize();

        // Go to the website
        driver.get("https://www.axisbank.com/");
        driver.manage().window().maximize();	
       // Call the methods with parameter values
        login(driver, "940318205", "180996");
       
       checkAccountStatus(driver);
        createStatement(driver);
       ApplyForCheckBook(driver);
      transferFunds(driver);
      deletePayee(driver);
      addPayees(driver);
     
       Logout(driver);

        // Close the browser
        driver.quit();
    }

    // Method to perform login
    
    public static void login(WebDriver driver, String custID, String mPin) {
        // Your code to login
    	Actions action = new Actions(driver);
		action.click().perform();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Products =driver.findElement(By.xpath("//a[contains(text(),'Bank Smart')]"));
		action.moveToElement(Products).perform();
		WebElement Accounts=driver.findElement(By.xpath("//a[contains(text(),'Remote Banking')]"));
		action.moveToElement(Accounts).click().perform();
		WebElement Login =driver.findElement(By.xpath("//div[contains(text(),'Login')]"));
		action.moveToElement(Login).perform();
		
		// switch to the frame
	     WebElement iframe = driver.findElement(By.xpath("//a[@class='hidden-xs']"));
	     iframe.click();
	   
	     Actions action1 =new Actions(driver);
	     
	    // Duration implicitWaitDuration1 = Duration.ofSeconds(10);
	     //   driver.manage().timeouts().implicitlyWait(implicitWaitDuration1.getSeconds(), TimeUnit.SECONDS);

	     
	    
	     
	     
	     driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	     Set<String> windows = driver.getWindowHandles();
	    String parent = driver.getWindowHandle();
	     windows.remove(parent);
	    Iterator<String> it = windows.iterator(); 
	     String child=null; //This is for referencing specific child window 
	     while(it.hasNext()){ 
	     child=(String)it.next(); 
	     driver.switchTo().window(child); 
	     //perform action that you want to perform on child window 
//Login to validated account
	     
	     WebElement Mpin= driver.findElement(By.id("mat-tab-label-0-2"));
		    Mpin.click();
		     driver.findElement(By.id("custId")).sendKeys(custID);
		     driver.findElement(By.id("mPin")).sendKeys(mPin);
		     driver.findElement(By.xpath("//span[contains(text(),'LOGIN')]")).click();
			
	     }
    	
    	
    	
    	
    	
    	
    }

    // Method to check account status
   
    public static void checkAccountStatus(WebDriver driver) {
        // Your code to check account status
    	
    	
    	
    	 driver.findElement(By.id("navList1")).click();
         JavascriptExecutor js1 = (JavascriptExecutor) driver;
       WebElement ele3= driver.findElement(By.xpath("//div[@id='mat-tab-label-1-4']"));
		    
        //WebElement ele3= driver.findElement(By.id("navList1")) ;
        ele3.click();
		     js1.executeScript("window.scrollBy(0,3000)", "ele3");
		     if(driver.findElement(By.xpath("//div[contains(text(),'Active')]"))!= null){

		    	 System.out.println("Status is Active");

		    	 }else{

		    	 System.out.println("Status is Inactive");

		    	 }
    }

    // Method to create a statement
   
    public static void createStatement(WebDriver driver) {
        // Your code to create a statement
    	
    	  driver.findElement(By.id("navList1")).click();
		     WebElement divClick = driver.findElement(By.xpath("//div[contains(text(),'STATEMENTS')]"));
		     divClick.click();
		     
		 driver.findElement(By.xpath("(//div[@class='mat-select-value'])[2]")).click();
		  List<WebElement> myElements = driver.findElements( (By.xpath("(//div[@class='ng-star-inserted'])[11]")));
	       for(WebElement e : myElements) {
	    	   
	         if(e.getText().equalsIgnoreCase("Detailed statements")) {
	             e.click();
	         }
	    driver.findElement(By.xpath("//a[@id='thisMonth0']")).click();
	    
	    JavascriptExecutor js11 = (JavascriptExecutor) driver;
	    
	    js11.executeScript("window.scrollBy(0,3000);");
	    
	    driver.findElement(By.xpath("//mat-select[@id='topDownload']//div[@class='mat-select-value']")).click();
	    
	    driver.findElement(By.xpath("(//span[normalize-space()='PDF'])[1]")).click();
	    
	  //div[@fxhide.lt-sm='true']//div//a[@id='StatementInputFilter0']
	    
	    driver.findElement(By.xpath("//div[@fxhide.lt-sm='true']//div//a[@id='StatementInputFilter0']")).click();
	    
	       }
    	
    	
    	
    	
    	
    }
    
   
    public static void ApplyForCheckBook(WebDriver driver)
    {
    	//APPLY FOR CHECK BOOK
	     
	     
	     //driver.findElement(By.id("navList1")).click();
	     driver.findElement(By.xpath("//div[normalize-space()='SERVICES']")).click();
	     driver.findElement(By.xpath("//a[@id='5000linkitem_1']")).click();
	   //  driver.findElement(By.cssSelector("#mat-dialog-10")).click();
	     
	     
	     
	  
	     driver.switchTo().frame("iframeview"); 
	 //    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);  
	  
/*				
WebElement checkBoxframe = driver.findElement(By.xpath(" //*[@id=\"root\"]/div[2]/div/div/div/div/div/div/div/div[2]/div/div[3]/div/div/div[1]/span/span/input"));

	   
	     checkBoxframe.click();
	     //write your selenium code here for checkbox
	 
driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/div/div/div/div/div/div[2]/div/div[4]/div/div/div/button[2]/span[1]")).click();

	        // Optionally, switch back to the main content if needed
	        driver.switchTo().defaultContent();
*/
//  driver.findElement(By.xpath("/html/body/div[4]/div[1]")).click();
driver.switchTo().defaultContent();
// driver.switchTo().frame("FirebugUI");
driver.findElement(By.id("close")).click();
    }

    // Method to add payees
    
    public static void addPayees(WebDriver driver) throws IOException {
        // Your code to add payees
    	
    	
		//ENTER PAYEE DETAILS AND TRANSFER FUND
			
			FileInputStream file =new FileInputStream("C:\\Users\\gpriy\\OneDrive\\Documents\\PayeeAxisBankDetails.xlsx");
			XSSFWorkbook workbook=new XSSFWorkbook(file);
			XSSFSheet sheet=workbook.getSheet("sheet1");
			int rowcount=sheet.getLastRowNum();
			int colcount=sheet.getRow(1).getLastCellNum();
	
			
			
			
			for (int i = 1; i <= 2; i++) {
			    XSSFRow celldata = sheet.getRow(i);

			    // Check if the row contains data in all the required columns
			  
			    driver.findElement(By.xpath("//*[@id=\"navList3\"]")).click();
			    // driver.findElement(By.xpath("//a[contains(text(),'Fund Transfer')][1]")).click();
			     driver.findElement(By.linkText("Fund Transfer")).click();
			     driver.findElement(By.id("addpayee0")).click();
			  driver.findElement(By.xpath("  //label[contains(text(),'Other Bank Payee')]")).click();
			  
	    	
			    
			  

			    // Retrieve cell values after ensuring they are not null
			    String PayeeName = celldata.getCell(0).getStringCellValue();
			    String Nickname = celldata.getCell(1).getStringCellValue();
			    int Accountnumber = (int) celldata.getCell(2).getNumericCellValue();
			    int confirmAccountnumber = (int) celldata.getCell(3).getNumericCellValue();
			    String IFSCcode = celldata.getCell(4).getStringCellValue();

			    // Rest of your code to fill the form with the retrieved data...
			    // (same as before)
			    
			     driver.findElement(By.id("PAYEE_NAME")).sendKeys(PayeeName);
			     driver.findElement(By.id("PAYEE_NICKNAME")).sendKeys(Nickname);
			     driver.findElement(By.id("PAYEE_ACCNUM")).sendKeys(String.valueOf(Accountnumber));
			     driver.findElement(By.id("CONFIRM_ACCNUM")).sendKeys(String.valueOf(confirmAccountnumber));
			     driver.findElement(By.id("IFSC")).sendKeys(IFSCcode);
			     Actions act = new Actions(driver);

				   //Double click on element
				   WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'Proceed')]")); 
				   act.click(ele).perform();
				 //  act.click(ele).perform();
	 
	
			}
    }
    	
    	

    	
   

    // Method to transfer funds
    
    public static void transferFunds(WebDriver driver) {
        // Your code to transfer funds
    	
    	driver.findElement(By.xpath("//div[normalize-space()='DASHBOARD']")).click();
		   
		   
		  driver.findElement(By.xpath("//input[@placeholder='enter amount']")).sendKeys("10");
		driver.findElement(By.xpath(" //input[@placeholder='enter payee']")).sendKeys("priya");
		driver.findElement(By.xpath("//span[contains(text(),'Gadde Priyanka')]")).click();
	driver.findElement(By.xpath("//button[@value='PAY']")).click();
	//driver.switchTo().frame("FirebugUI");
	//driver.findElement(By.xpath("//button[@type='button']")).click();
						
				
				
				
    	
    	
    	
    	
    	
    }

    // Method to delete a payee
    
    public static void deletePayee(WebDriver driver) {
        // Your code to delete a payee
    	
    	  driver.findElement(By.id("navList3")).click();
    	  driver.findElement(By.linkText("Fund Transfer")).click();
		     driver.findElement(By.xpath("//div[contains(text(),'MANAGE PAYEES')]")).click();
		//     driver.findElement(By.xpath("//*[name()='use' and contains(@xlink:href, '#delete_payee')]")).click();
		  //   driver.findElement(By.xpath("")).click();
		     
		     
		     // Set the maximum waiting time in seconds
		        

		        try {
		            // Navigate to the webpage containing the SVG element
		        	  // Load the svgSelenium WebDriver extension



// Wait for the flex container to be visible
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10000));


// Find the <use> element using its xlink:href attribute
WebElement useElement = driver.findElement(By.cssSelector("mat-cell:nth-child(6) div:nth-child(1) div:nth-child(1) div:nth-child(2)"));
useElement.click();
		        } catch (Exception e) {
		            // Handle any exceptions that might occur
		            e.printStackTrace();
		        }
		     
  /// driver.findElement(By.xpath("//span[normalize-space()='Delete account details']")).click();
		     
		     

		
    }
    
    
   

	public static void Logout(WebDriver driver)
    {
		
		
		driver.findElement(By.xpath("//div[@id='LOGOUT']")).click();
    	  driver.quit();
		     //LOGOUT FROM ACCOUNT
    }
}
