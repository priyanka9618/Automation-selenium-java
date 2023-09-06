package Ecommerce;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonEC
{
	

public static void  main(String args[])
{
	
	
	   // Set up WebDriver
	WebDriverManager.edgedriver().setup(); // Downloads Edge WebDriver executable
    EdgeOptions edgeOptions = new EdgeOptions();
    // Add any desired options to the edgeOptions
   WebDriver  driver = new EdgeDriver();
    Actions action = new Actions(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // Open Amazon website
    driver.get("https://www.amazon.in");
    driver.manage().window().maximize();

    // Perform actions
    login(driver);
   whishlistprod(driver);
    EditProfile(driver);
    WriteReview(driver);
    searchProduct(driver);

   navigateToCartPage(driver);
  

}





public static void login(WebDriver driver) {
    // Your login code here...





driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']")).click();
driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("8431664853");
driver.findElement(By.xpath("//input[@id='continue']")).click();
driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("Gpriya@1996");
driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
//   driver.findElement(By.xpath("(//span[@id='nav-link-accountList-nav-line-1'])[1]")).click();
// driver.findElement(By.xpath("//span[normalize-space()='Sign Out']")).click();
	//	driver.close();
		

}

public static void WriteReview(WebDriver driver)
{
	
	
	 String[] productsToSearch = {
	            "samsung m14 5g"
	        };
	 
	 for (String product : productsToSearch) {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
         WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='twotabsearchtextbox']")));
         searchBtn.clear();
         searchBtn.sendKeys(product);
         searchBtn.sendKeys(Keys.ENTER);

         WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
         By linkLocator = By.xpath("//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']");
         WebElement linkElement = wait3.until(ExpectedConditions.presenceOfElementLocated(linkLocator));

         String parentWindowHandle = driver.getWindowHandle(); // Store the parent window handle

         linkElement.click();

         for (String windowHandle : driver.getWindowHandles()) {
             if (!windowHandle.equals(parentWindowHandle)) {
                 driver.switchTo().window(windowHandle);
                 
                long windowHeight = (long) ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight"));
                 long totalHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
                 long scrollTo = Math.round(totalHeight * 0.72);

                 // Scroll to the calculated position
                 JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                 jsExecutor.executeScript("window.scrollTo(0, arguments[0]);", scrollTo);


                 // Perform interactions on the new window/tab
                 WebElement writereview =   wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Write a product review")));

                 // Scroll to the element
                 Actions actions = new Actions(driver);
                 actions.moveToElement(writereview).perform();

                 // Wait for the element to be visible
                 WebElement visibleWriteReview = wait.until(ExpectedConditions.visibilityOf(writereview));

                 // Click the visible element
                 visibleWriteReview.click();
                 //writereview.click();
                 
                 driver.findElement(By.xpath("//img[@alt='select to rate item four star.']")).click();
                 driver.findElement(By.xpath("//div[@class='a-section a-spacing-none ryp__card-frame ryp__card-frame--focus']//div[2]//div[1]//div[1]//div[2]//div[1]//button[4]//img[1]")).click();
                 driver.findElement(By.xpath("//div[@class='a-section a-spacing-none ryp__card-frame ryp__card-frame--focus']//div[3]//div[1]//div[1]//div[2]//div[1]//button[3]//img[1]")).click();
                 driver.findElement(By.xpath("//div[4]//div[1]//div[1]//div[2]//div[1]//button[4]//img[1]")).click();
                 driver.findElement(By.xpath("//input[@id='scarface-review-title-label']")).sendKeys("best smart phone in budget");
                 driver.findElement(By.xpath("//textarea[@id='scarface-review-text-card-title']")).sendKeys("camera quality is good ,battery perc is average and stoarage of the samsung phone is enough as per the price");
              //   driver.findElement(By.xpath("//button[@value='Submit']")).click();
                 

             }
         }
	 }
	 
	
	
	
	
}


public static void whishlistprod(WebDriver driver)
{
	
	try {
		
	Actions actions1= new Actions(driver);
	WebElement loginbtn=driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
	actions1.moveToElement(loginbtn).build().perform();
	WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10));
    // Wait for the "Your Wish List" link to be clickable and then click on it
    WebElement wishlistLink = wait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='nav-text'][normalize-space()='Your Wish List']")));
    wishlistLink.click();
	
	
//	driver.findElement(By.xpath("//span[@class='nav-text'][normalize-space()='Your Wish List']")).click();
	
	

    java.util.List<WebElement> wishlistItems = driver.findElements(By.cssSelector("#g-items li"));
    for (WebElement item : wishlistItems) {
        WebElement titleElement = item.findElement(By.cssSelector(".a-list-item"));
        String title = titleElement.getText();
        System.out.println("Item: " + title);
        System.out.println("------------------------");
    }
} catch (Exception e) {
    e.printStackTrace();
} finally {
}


	
	
	
	
}



public static void EditProfile(WebDriver driver)
{
	Actions actions2= new Actions(driver);
	WebElement loginbtn=driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
	actions2.moveToElement(loginbtn).build().perform();
	WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	String hrefValue = "/gp/css/homepage.html?ref_=nav_AccountFlyout_ya";
    String linkText = "Your Account";

    // Wait for the element to be clickable
    WebElement element = wait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='" + hrefValue + "' and .//span[text()='" + linkText + "']]")));

    // Use Actions to move to the element and then click on it
    Actions actions = new Actions(driver);
    actions.moveToElement(element).click().perform();
    WebElement loginSecurityHeading = driver.findElement(By.xpath("//h2[contains(., 'Login & security')]"));



    loginSecurityHeading.click();
driver.findElement(By.xpath("//input[@id='auth-cnep-edit-name-button']")).click();
driver.findElement(By.xpath("//input[@id='ap_customer_name']")).clear();
driver.findElement(By.xpath("//input[@id='ap_customer_name']")).sendKeys("PRIYANKA");
driver.findElement(By.xpath("//input[@id='cnep_1C_submit_button']")).click();






}

public static void searchProduct(WebDriver driver) {
    JavascriptExecutor js = (JavascriptExecutor) driver;

    String[] productsToSearch = {
        "samsung m14 5g","headphones"
    };
    for (String product : productsToSearch) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='twotabsearchtextbox']")));
        searchBtn.clear();
        searchBtn.sendKeys(product);
        searchBtn.sendKeys(Keys.ENTER);

        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        By linkLocator = By.xpath("//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']");
        WebElement linkElement = wait3.until(ExpectedConditions.presenceOfElementLocated(linkLocator));

        String parentWindowHandle = driver.getWindowHandle(); // Store the parent window handle

        linkElement.click();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);

                // Perform interactions on the new window/tab
                WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10));
                By addToCartButtonLocator = By.xpath("//input[@id='add-to-cart-button']");

                try {
                    WebElement addToCartButton = wait5.until(ExpectedConditions.visibilityOfElementLocated(addToCartButtonLocator));
                    js.executeScript("arguments[0].scrollIntoView();", addToCartButton);
                    addToCartButton.click();
                } catch (TimeoutException e) {
                    System.out.println("Add to Cart button is not visible within the timeout.");
                }

                driver.close(); // Close the new window/tab
            }
        }

        driver.switchTo().window(parentWindowHandle); // Switch back to the parent window

        driver.navigate().back();
    }
}

/*WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='twotabsearchtextbox']")));
WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofMillis(5000));
searchBtn.clear();
searchBtn.sendKeys("samsung m14 5g");
searchBtn.sendKeys(Keys.ENTER);
         WebElement product=      driver.findElement(By.xpath("//div[contains(@class,'rush-component s-featured-result-item')]//span[@class='a-size-medium a-color-base a-text-normal'][contains(text(),'Samsung Galaxy M14 5G (ICY Silver, 6GB, 128GB Stor')]"));
         product.click();
           
               

*/
           
           
       /*    for (WebElement productLink : productLinks) {
                js.executeScript("arguments[0].scrollIntoView();", productLink);
                productLink.click();
      s          
*/
             
              
           
           
        
        
                // Go back to the search results page
                //driver.navigate().to("https://www.amazon.in");
                
             
                
            
        

        // Go to the shopping cart page
    
        
       

        
    
  
	        
		



	




public static void navigateToCartPage(WebDriver driver) {
    // Click on the cart icon in the header
    WebElement cartIcon = driver.findElement(By.id("nav-cart-count"));
    cartIcon.click();

    // Perform actions on the cart page if needed
    // ...
driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();


   
WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofMillis(10000));
WebElement addnewadrees = wait4.until(ExpectedConditions.elementToBeClickable(By.id("add-new-address-popover-link")));
// driver.findElement(By.xpath("(//a[normalize-space()='Add a new address'])[1]")).click();

((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",  addnewadrees);

Actions actions1 = new Actions(driver);
//addnewadrees.click();
actions1.moveToElement(addnewadrees).click().build().perform();
 
  // driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressFullName']")).sendKeys("Priyanka");
   WebElement fullNameInput = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='address-ui-widgets-enterAddressFullName']")));

   // Once visible, interact with the element.
   fullNameInput.sendKeys("Priyanka");
   driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressPhoneNumber']")).sendKeys("8431664853");
   driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressPostalCode']")).sendKeys("560037");
   
   driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressLine1']")).sendKeys("Sns Pg For Ladies, Pr Residency, Manjunatha Layout");
  driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressLine2']")).sendKeys("marthahalli");
   driver.findElement(By.xpath("//input[@id='address-ui-widgets-landmark']")).sendKeys("Opp Hanuman temple");
  // driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressCity']")).sendKeys("Bangalore");
   
  // driver.findElement(By.xpath("//span[@id='address-ui-widgets-enterAddressStateOrRegion']//span[@role='button']")).click();
/*   WebElement dropdownElement = driver.findElement(By.id("//span[@id='address-ui-widgets-enterAddressStateOrRegion']//span[@role='button']"));

   // Initialize the Select class with the dropdown element
   Select dropdown = new Select(dropdownElement);

   // Select an option by visible text
   dropdown.selectByVisibleText("Karnataka");*/
   driver.findElement(By.id("address-ui-widgets-enterAddressFormContainer")).click();
   
   
// Click the state dropdown
//  WebElement useaddress = driver.findElement(By.xpath("//input[@aria-labelledby='address-ui-widgets-form-submit-button-announce']"));
// Click the state dropdown
// Wait for the element to be present
   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

   // Locate the "Use this address" button
   By useAddressButtonLocator = By.xpath("//span[@id='address-ui-widgets-form-submit-button-announce'][contains(text(),'Use this address')]");
   WebElement useAddressButton = wait.until(ExpectedConditions.visibilityOfElementLocated(useAddressButtonLocator));

   // Scroll the element into view if needed
   ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", useAddressButton);

   // Use Actions class to click using JavaScript
   Actions actions = new Actions(driver);
   actions.doubleClick(useAddressButton).perform();
   //actions.moveToElement(useAddressButton).click().build().perform();
   

//  useAddressButton.click();

/*   By radioButtonLocator = By.cssSelector("input[name='ppw-instrumentRowSelection'][value='SelectableAddCreditCard']");
   
 //  driver.findElement(By.xpath("//input[@value='SelectableAddCreditCard']")).click();
   WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(15));
   WebElement radioButton = wait5.until(ExpectedConditions.visibilityOfElementLocated(radioButtonLocator));
   // Now you can interact with the element

   radioButton.click();
   WebElement creditcard =driver.findElement(By.className("apx-add-pm-trigger-common-image"));
   creditcard.click();
// Locate the iframe element
   WebElement iframeElement = driver.findElement(By.name("ApxSecureIframe"));

   // Switch to the iframe
   driver.switchTo().frame(iframeElement);

 //  Alert alert = driver.switchTo().alert();
    
     
   
     
     WebElement iframeElement = driver.findElement(By.id("pp-IVMNtL-97")); // Replace with actual iframe locator
     driver.switchTo().frame(iframeElement);



   
   
   WebElement creditCardLocator = driver.findElement( By.xpath("//div[contains(@class, 'a-section')][contains(@class, 'a-spacing-none')][contains(@class, 'apx-add-credit-card-number')]"));
   

  

   // Fill in the credit card number
   creditCardLocator.sendKeys("4594530207489527");
                
   WebElement element3=driver.findElement(By.xpath("//input[@name='ppw-accountHolderName']"));
   element3.clear();
   
   element3.sendKeys("Gadde Priyanka");
   WebElement monthDropdown1 = driver.findElement(By.xpath("//select[@name='ppw-expirationDate_month']"));
   Select monthSelect1 = new Select(monthDropdown1);
   monthSelect1.selectByVisibleText("05"); // Replace with the desired month

   // Select the expiration year
   WebElement yearDropdown1 = driver.findElement(By.xpath("//select[@name='ppw-expirationDate_year']"));
   Select yearSelect1 = new Select(yearDropdown1);
   yearSelect1.selectByVisibleText("29"); // Replace with the desired year


   
   
   
   // Switch back to the default content
  
  
   driver.findElement(By.xpath("(//input[@id='pp-QPuFRT-109'])[1]")).sendKeys("755");
   driver.findElement(By.xpath("//input[@type='checkbox']")).click();
   
   */
   
   //name = ppw-expirationDate_month
   //name = ppw-expirationDate_year
   
   
   
  // driver.get("https://www.amazon.in/gp/cart/view.html");
   ////input[@value='instrumentId=amzn1.pm.poa.YW16bjEucG9hOmFtem4xLnBvYS5wb2RhLlVuaWZpZWRQYXltZW50c0ludGVyZmFjZTox.QTFBSDg0V0tKQURDUjE&isExpired=false&paymentMethod=UnifiedPaymentsInterface&tfxEligible=false']
//  driver.findElement(By.xpath("//div[@aria-label='Other UPI Apps']")).click();
   
   
   //WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(10));
 /*  WebElement  labelLocator = driver.findElement(By.xpath("//input[@id='pp-FAGSpF-119']"));

   labelLocator.click();
   
  

   
   driver.findElement(By.xpath("//input[@placeholder='Enter UPI ID']")).sendKeys("8431664853@axl");
   driver.findElement(By.name("ppw-widgetEvent:ValidateUpiIdEvent")).click();
   
   driver.findElement(By.name("ppw-widgetEvent:SetPaymentPlanSelectContinueEvent")).click();
  */  
   
  // WebElement cashondelivery =driver.findElement(By.xpath("//input[@id='pp-aWP5OW-129']"));
  // cashondelivery.click();
   
WebElement usePayment =driver.findElement(By.xpath("//input[@name='ppw-widgetEvent:SetPaymentPlanSelectContinueEvent']"));
usePayment.click();

}



}


