package br.com.joaops.smt.configuration;

/**
 * Created by Feng on 01-Mar-15.
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"br.com.joaops.smt.controller"})
public class MvcConfig {
    
}