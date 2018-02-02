package com.dream.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Dream
 * 2018/2/2.
 */
@Configuration
@ImportResource(locations = {"classpath:activiti-boot.xml"})
public class ActivitiConfig {
}
