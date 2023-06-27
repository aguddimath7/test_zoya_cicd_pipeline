import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class zoya_login
{
    WebDriver driver;


    @BeforeSuite
    public void Setup()
    {
        driver = new ChromeDriver();
        driver.get("https://bkck-019.dx.commercecloud.salesforce.com/s/Zoya/homepage?lang=en_IN&redirectTo=home");
    }

    @Test(dataProvider = "testdata")
    public void Login(String keyWord1, String keyWord2) throws InterruptedException
    {
        System.out.println("!!!!!!!!!!!!---------" + keyWord1 + "---------!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!---------" + keyWord2 + "---------!!!!!!!!!!!!!");

        driver.findElement(By.cssSelector(".content-asset.wishlist-icon")).click();
        Thread.sleep(10000);
        WebElement mobileno = driver.findElement(By.cssSelector("[class='modal login-pop-modal p-0 show'] #login-popup #mobile"));
        mobileno.click();
        Thread.sleep(10000);
        mobileno.sendKeys(keyWord1);
        Thread.sleep(10000);

        //Mobile No Click Continue btn
        driver.findElement(By.cssSelector("[class='modal login-pop-modal p-0 show'] #login-popup .text-center.continue button")).click();
        Thread.sleep(10000);

        //Mobile error msg
        String moberro = driver.findElement(By.cssSelector("#valid-error")).getText();
        if(moberro.equals("Please enter valid Mobile Number"))
        {
            driver.navigate().refresh();
        }
        else
        {
            //OTP Related Code
            Thread.sleep(10000);
            WebElement otpinput = driver.findElement(By.cssSelector("[class='modal login-pop-modal p-0 show'] #login-popup .form-control-otp.otp-input.form-control"));
            otpinput.sendKeys(keyWord2);

            WebElement otpbtn = driver.findElement(By.cssSelector("[class='modal login-pop-modal p-0 show'] #login-popup .otppopup-submitbutton.btn.btn-primary.btn-lg"));
            otpbtn.click();
            Thread.sleep(10000);
            try
            {
                Thread.sleep(10000);
                String otperror = driver.findElement(By.cssSelector("#otp-error")).getText();
                if(otperror.equals("The OTP you 've entered is wrong"))
                {
                    System.out.println("OTP if enter success!!!");
                    driver.navigate().refresh();
                }
                else
                {
                    System.out.println("________________________________VALID____________________________________________");
                }
            }
            catch(Exception e)
            {
                System.out.println("!!!!!!!!!!------Element Exception--------!!!!!!!!!!"+e);
            }
            Thread.sleep(10000);
            try {
                System.out.println("---------"
                        +driver.findElement(By.cssSelector(".welcome-txt ")).getText()+"-----------");
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

            Thread.sleep(10000);
            System.out.println("Login Success!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

    @AfterSuite
    public void TearDown()
    {
        driver.close();
    }

    @DataProvider(name = "testdata")
    public Object[][] dataProvFunc()
    {
        return new Object[][]
                {
                        {"82u2iuy32", "na"},
                        {"7020491561", "234243"},
                        {"7020491561", "214263"}
                };
    }
}
