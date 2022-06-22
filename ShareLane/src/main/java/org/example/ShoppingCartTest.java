package org.example;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingCartTest extends BaseTest {

    public void logIn() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.name("first_name")).sendKeys("Nick");
        driver.findElement(By.name("email")).sendKeys("nick@google.com");
        driver.findElement(By.name("password1")).sendKeys("1111");
        driver.findElement(By.name("password2")).sendKeys("1111");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        String email = driver.findElement(By.xpath("//td[text()='Email']/following::b")).getText();
        String password = driver.findElement(By.xpath("//td[text()='Password']/following::td")).getText();
        driver.findElement(By.cssSelector("img[src='../images/logo.jpg']")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("input[value='Login']")).click();
    }

    public void addToCart() {
        driver.findElement(By.xpath("//a[contains(@href, 'show_book.py?book_id')]")).click();
        driver.findElement(By.cssSelector("img[src='../images/add_to_cart.gif']")).click();
        driver.findElement(By.linkText("Shopping Cart")).click();
        driver.findElement(By.name("q")).clear();
    }

    @Test
    public void discountShouldBeZeroUpToTwentyBooks() {
        logIn();
        addToCart();
        driver.findElement(By.name("q")).sendKeys("19");
        driver.findElement(By.cssSelector("input[value='Update']")).click();
        String discount = "0";
        Assert.assertEquals(driver.findElement(By.xpath("//td[5]/following::b")).getText(), discount);
    }

    @Test
    public void discountShouldBeTwoFromTwentyToFortyNine() {
        logIn();
        addToCart();
        driver.findElement(By.name("q")).sendKeys("20");
        driver.findElement(By.cssSelector("input[value='Update']")).click();
        String discount = "2";
        Assert.assertEquals(driver.findElement(By.xpath("//td[5]/following::b")).getText(), discount);
    }

    @Test
    public void discountShouldBeThreeFromFiftyToNinetyNine() {
        logIn();
        addToCart();
        driver.findElement(By.name("q")).sendKeys("50");
        driver.findElement(By.cssSelector("input[value='Update']")).click();
        String discount = "3";
        Assert.assertEquals(driver.findElement(By.xpath("//td[5]/following::b")).getText(), discount);
    }

    @Test
    public void discountShouldBeThreeFromFiftyToNinetyNine1() {
        logIn();
        addToCart();
        driver.findElement(By.name("q")).sendKeys("51");
        driver.findElement(By.cssSelector("input[value='Update']")).click();
        String discount = "3";
        Assert.assertEquals(driver.findElement(By.xpath("//td[5]/following::b")).getText(), discount);
    }
}

