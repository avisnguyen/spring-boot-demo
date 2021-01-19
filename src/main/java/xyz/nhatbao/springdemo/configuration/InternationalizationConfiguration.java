package xyz.nhatbao.springdemo.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class InternationalizationConfiguration implements WebMvcConfigurer {
    //  to determine the locale, using cookie
    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver() {
        //  define new cookie locale resolver
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieDomain("localeCookie");
        //  cookie will expire after 60 minutes
        resolver.setCookieMaxAge(60 * 60);
        //  set default locale
        resolver.setDefaultLocale(Locale.US);
        return resolver;
    }

    //  to determine message source
    @Bean(name = "messageSource")
    public MessageSource getMessageResource() {
        //  define the resource bundle
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
        messageResource.setBasename("classpath:i18n/messages");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }

    //  to set locale param name and add locale interceptor
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("language");
        registry.addInterceptor(localeInterceptor).addPathPatterns("/*");
    }
}
