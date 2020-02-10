package com.bookstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * MessageConfiguration Class.
 * 
 * @author shivanimalhotra
 *
 */
@Configuration
public class MessageConfiguration {

	/**
	 * 
	 * This method is used to get the messageSource.
	 * 
	 * @return ResourceBundleMessageSource
	 */
	@Bean(name = "messageSource")
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.addBasenames("message");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

}
