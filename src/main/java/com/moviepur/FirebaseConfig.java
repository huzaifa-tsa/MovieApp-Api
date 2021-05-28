package com.moviepur;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {
	
	@Bean
	public void firebaseSetup() {
		try {
			FirebaseOptions options = FirebaseOptions.builder().setCredentials(
					GoogleCredentials.fromStream(this.getClass().getResourceAsStream("/moviepur_firebase.json")))
					.build();

			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}