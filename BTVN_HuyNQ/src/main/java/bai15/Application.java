package bai15;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MySum mySum = (MySum) context.getBean("mySum");

        System.out.print("Enter first : ");
        String str1 = ((new Scanner(System.in)).nextLine());

        System.out.print("Enter Second : ");
        String str2 = ((new Scanner(System.in)).nextLine());

        String result = mySum.sum(str1, str2);
        System.out.println("Result : " + result);

    }
}
