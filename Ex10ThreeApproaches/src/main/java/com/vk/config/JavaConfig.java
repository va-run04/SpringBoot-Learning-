package com.vk.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration                              // replaces the XML file
@ComponentScan(basePackages = {"com.vk"})   // replaces <context:component-scan>
public class JavaConfig {
    // No XML needed!
    // @Configuration replaces applicationconfig.xml
    // @ComponentScan replaces <context:component-scan base-package="com.vk"/>
}