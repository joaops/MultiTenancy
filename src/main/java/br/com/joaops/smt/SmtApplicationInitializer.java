package br.com.joaops.smt;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author João Paulo
 */
public class SmtApplicationInitializer implements WebApplicationInitializer {
    
    private static final String APPLICATION_NAME = "Sample Multi Tenancy";
    private static final String CONFIG_LOCATION = "br.com.joaops.smt.configuration";
    private static final String MAPPING_URL = "/*";
    
    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        WebApplicationContext context = getContext();
        sc.addListener(new ContextLoaderListener(context));
        sc.addFilter("CharacterEncodingFilter", getCharacterEncodingFilter()).addMappingForUrlPatterns(null, true, MAPPING_URL);
        sc.addFilter("RequestContextFilter", getRequestContextFilter()).addMappingForUrlPatterns(null, true, MAPPING_URL);
        sc.addFilter("OpenEntityManagerInViewFilter", getOpenEntityManagerInViewFilter()).addMappingForUrlPatterns(null, true, MAPPING_URL);
        
        //EnumSet<DispatcherType> types = EnumSet.of(DispatcherType.ERROR, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.REQUEST);
        sc.addFilter("securityFilter", getDelegatingFilterProxy()).addMappingForUrlPatterns(null, false, MAPPING_URL);
        
        //FilterRegistration.Dynamic securityFilter = sc.addFilter(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME, getDelegatingFilterProxy());
        //securityFilter.addMappingForUrlPatterns(types, false, MAPPING_URL);
        
        ServletRegistration.Dynamic dispatcher = sc.addServlet("LoversBookServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.setAsyncSupported(Boolean.TRUE);
        dispatcher.addMapping(MAPPING_URL);
    }
    
    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setDisplayName(APPLICATION_NAME);
        context.setConfigLocation(CONFIG_LOCATION);
        return context;
    }
    
    private CharacterEncodingFilter getCharacterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
    
    private RequestContextFilter getRequestContextFilter() {
        RequestContextFilter requestContextFilter = new RequestContextFilter();
        return requestContextFilter;
    }
    
    private OpenEntityManagerInViewFilter getOpenEntityManagerInViewFilter() {
        OpenEntityManagerInViewFilter openEntityManagerInViewFilter = new OpenEntityManagerInViewFilter();
        openEntityManagerInViewFilter.setEntityManagerFactoryBeanName("entityManagerFactory");
        return openEntityManagerInViewFilter;
    }
    
    //Esse filtro não está sendo usado!
    private DelegatingFilterProxy getDelegatingFilterProxy() {
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy("springSecurityFilterChain");
        return delegatingFilterProxy;
    }
    
}