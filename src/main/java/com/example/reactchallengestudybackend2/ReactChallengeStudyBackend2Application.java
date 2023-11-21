package com.example.reactchallengestudybackend2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing     // JPA Auditing 활성화
public class ReactChallengeStudyBackend2Application {

    public static void main(String[] args) {
        SpringApplication.run(ReactChallengeStudyBackend2Application.class, args);
    }

}
