package com.bookstore.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Configuration class for properties defined in applicaton.properties file.
 * 
 * @author shivanimalhotra
 *
 */
@Configuration
@Data
public class CommonConfiguration {

	/**
	 * Value of mediaCoverageUrl variable.
	 */
	@Value("${media.coverage.url}")
	private String mediaCoverageUrl;

}
