package myproject.gui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Olya on 08/06/16.
 */
public class WorkwithWebDriver {
    static WebDriver driver;

    public void openBrowser() {
        try{
            System.setProperty("webdriver.gecko.driver", "D:\\Programming\\search\\lib\\geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } catch (Exception e1) {
            System.out.println("Problem with actionPerformed + openBrowser" );
            e1.printStackTrace();
        }
     }

    public void  openPage(String url){
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (Exception e1) {
            System.out.println("Problem with actionPerformed + openPage");
            e1.printStackTrace();
        }
    }

    public void enterSearchWords(String searchString){
        try {
            WebElement input = driver.findElement(By.id("lst-ib"));
            input.clear();
            input.sendKeys(searchString);
            driver.findElement(By.id("sblsbb")).click();
        }catch (Exception e){
            System.out.println("excaption in enterSearchWords " + e.getMessage());
        }
    }

    public  List<String> findHeaderofArticle() throws InterruptedException {
        List<WebElement> links = null;
        List<String> titles = new ArrayList<String>();

        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            links = driver.findElements(By.xpath("//div[@class='rc']/h3[@class='r']/a"));
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }catch (Exception e) {
            System.out.println("elements links were not found " + e.getMessage());
        }

        for (int i=0; i<links.size(); i++) {
            String test = links.get(i).getText();
//            System.out.println(test);
            titles.add(test) ;
        }

    return titles;
    }

    public void closeBrowser(){
        try{
            driver.quit();
        }catch (Exception e3){
            System.out.println("Problem with closing browser - closeBrowser");
            System.out.println(e3.getMessage());
        }
    }

//    /**
//     *
//     * @param
//     * @param by
//     * @return
//     * @throws Exception
//     */
//    public static WebElement wait(int time, By by) throws Exception
//    {
//        final By b = by;
//        final WebDriver d = driver;
//
//        WebElement elm = (new WebDriverWait(d, time))
//                .until(new ExpectedCondition<WebElement>(){
//                    @Override
//                    public WebElement apply(WebDriver d) {
//                        return d.findElement(b);
//                    }
//                });
//
//        return elm;
//    }

}











