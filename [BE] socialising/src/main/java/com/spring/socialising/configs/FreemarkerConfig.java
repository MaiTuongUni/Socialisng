package com.spring.socialising.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

public class FreemarkerConfig {
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreemarkerConfig() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("/templates/");
        return bean;
    }
}