package panda.config;

/**
 * Created by Feng on 01-Mar-15.
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = "panda.controller")
public class MvcConfig extends WebMvcConfigurerAdapter {
}