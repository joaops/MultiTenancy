package br.com.joaops.smt.configuration;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import panda.repo.User;

/**
 * Created by Feng on 02-Mar-15.
 */
public class MyTenantIdentifierResolver implements CurrentTenantIdentifierResolver {
    
    @Override
    public String resolveCurrentTenantIdentifier() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            System.out.println("Fall to default identifer: master");
            return "master";
        } else {
            System.out.println(((User) authentication.getPrincipal()).getDb());
            return ((User) authentication.getPrincipal()).getDb();
        }
    }
    
    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }
    
}