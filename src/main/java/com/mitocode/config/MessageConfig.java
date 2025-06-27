package com.mitocode.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageConfig {
    //Cargar archivos properties de idioma
    @Bean
    public MessageSource messageResource(){

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //indicando que lea todos los archivos que tengan la palabra messages
        messageSource.setBasename("classpath:messages");
        return messageSource;

    }

    //para resolver las variables en los archivos messages.properties
    @Bean
    public LocalValidatorFactoryBean getValidator(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        //retornando messageResource
        bean.setValidationMessageSource(messageResource());
        return bean;
    }

    //estableciendo un archivo properties por defecto
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver localeResolver =new SessionLocaleResolver();
        //Locale.ROOT hace referencia al archivo y el idioma
        localeResolver.setDefaultLocale(Locale.ROOT);
        return localeResolver;
    }

}
