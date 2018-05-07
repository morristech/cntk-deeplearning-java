package com.infosupport.machinelearning.digitclassifier;

import com.infosupport.machinelearning.digitclassifier.services.DigitClassifier;
import com.infosupport.machinelearning.digitclassifier.services.DigitClassifierImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DigitclassifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitclassifierApplication.class, args);
	}

	@Bean
	public DigitClassifier digitClassifier() {
		return DigitClassifierImpl.create();
	}
}
