package br.com.eduardo.spring.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Eduardo Oliveira
 * @date 13/06/2023
 */
@Component
@ConfigurationProperties("greeting-service")
public class GreetingConfiguration {

	private String greeting;
	private String defaultValue;

	public GreetingConfiguration() {
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
