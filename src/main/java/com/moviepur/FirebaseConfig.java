package com.moviepur;

// import javax.annotation.PostConstruct;
// import org.springframework.context.annotation.Configuration;
// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;

// @Configuration
// public class  {
	
// 	@PostConstruct
// 	public void firebaseSetup() {
// 		try {
// 			FirebaseOptions options = FirebaseOptions.builder().setCredentials(
// 					GoogleCredentials.fromStream(this.getClass().getResourceAsStream("/moviepur_firebase.json")))
// 					.build();

// 			FirebaseApp.initializeApp(options);
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
// 	}
	
// }


import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {
	
    @PostConstruct
	public void firebaseSetup() {
		try {
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(this.getClass().getResourceAsStream("/moviepur_firebase.json")))
                    .build();
			if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            } else {
               FirebaseApp.getInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
