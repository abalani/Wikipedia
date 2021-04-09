package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ArticlePage {
    WebDriver driver;
    By contentHeadings = By.cssSelector("div[id='toc'] ul span:nth-child(2)");
    By pageHeadings = By.cssSelector("span[class*='mw-headline']");
    By contentHyperlinks = By.cssSelector("li[class*='toclevel-1'] a");
    By nikePopUpText = By.cssSelector("a[class='mwe-popups-extract'] p");
    By nikeHover = By.cssSelector("div[class='div-col'] a[title='Nike (mythology)']");
    By nikeTitle = By.cssSelector("h1[id='firstHeading']");
    By familyTreeSection = By.cssSelector("[id='Family_tree']");
    By familyTreeTable = By.cssSelector("table[class='toccolours'] td a");

    public ArticlePage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyContentAndPageHeadings() {
        //get text from web elements and store them into two arrays
        List<WebElement> contentBoxList = driver.findElements(contentHeadings);
        ArrayList<String> contentHeadings = new ArrayList<String>();
        for (WebElement element : contentBoxList) {
            contentHeadings.add(element.getText());
        }

        List<WebElement> pageHeadingsList = driver.findElements(pageHeadings);
        ArrayList<String> pageHeadings = new ArrayList<String>();
        for (WebElement webElement : pageHeadingsList) {
            pageHeadings.add(webElement.getText());
        }
        //assert each element in content list equals headings list
        for(int i = 0; i < contentBoxList.size(); i++) {
            Assert.assertEquals(contentHeadings.get(i), pageHeadings.get(i));
        }
        driver.quit();
    }

    public void verifyContentHyperlinks() {
        List<WebElement> contentHref = driver.findElements(contentHyperlinks);
        List<String> errorList = new ArrayList<String>();

        for(WebElement element : contentHref) {
            String href = element.getAttribute("href");
            int respCode;
            HttpURLConnection huc;
            try {
                huc = (HttpURLConnection)(new URL(href).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();
                if (respCode != 200) {
                    errorList.add(href);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (errorList.size()>0) {
            for (String link : errorList) {
                System.out.println("URL = " + link);
            }
        }
        driver.quit();
    }

    public void verifyNikeContent() {
        WebElement personifiedNike = driver.findElement(nikeHover);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(personifiedNike));

        Actions action = new Actions(driver);
        action.moveToElement(personifiedNike).moveToElement(personifiedNike);
        action.build().perform();
        driver.manage().timeouts().implicitlyWait(6000, TimeUnit.MILLISECONDS);
        WebElement nikeHoverText = driver.findElement(nikePopUpText);
        String nikeText = nikeHoverText.getText();

        // Test will pass, pop-up has Greek 'civilization'
        Assert.assertEquals(nikeText, "In ancient Greek civilization, Nike was a goddess who personified victory. " +
                "Her Roman equivalent was Victoria.");
        // Test will fail, pop-up has Greek 'civilization' not 'religion'
        // Assert.assertEquals(nikeText, "In ancient Greek religion, Nike was a goddess who personified victory. " +
        //        "Her Roman equivalent was Victoria.");
        driver.quit();
    }

    public void clickNike(String pageLink) {
        if(pageLink.equals("Nike")) {
            driver.findElement(nikeHover).click();
        }
    }

    public void verifyNikeFamilyTree() {
        WebElement nikePageTitle = driver.findElement(nikeTitle);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(nikePageTitle));

        WebElement familyTree = driver.findElement(familyTreeSection);
        String familyTreeText = familyTree.getText();
        Assert.assertEquals(familyTreeText, "Family tree");

        List<WebElement> familyTreeLinks = driver.findElements(familyTreeTable);
        Assert.assertTrue(familyTreeLinks.size() != 0);
    }
}
