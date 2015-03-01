package panda.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * Created by Feng on 01-Mar-15.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Throwable {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
    }

}
