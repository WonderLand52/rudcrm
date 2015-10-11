package com.rudenkoinc.crm.config;

import com.rudenkoinc.crm.config.root.AppSecurityConfig;
import com.rudenkoinc.crm.config.root.DevelopmentConfiguration;
import com.rudenkoinc.crm.config.root.RootContextConfig;
import com.rudenkoinc.crm.config.servlet.ServletContextConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Illia Rudenko
 */

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootContextConfig.class, DevelopmentConfiguration.class,
                AppSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {ServletContextConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
