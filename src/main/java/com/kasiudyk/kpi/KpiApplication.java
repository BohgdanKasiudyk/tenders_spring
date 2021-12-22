package com.kasiudyk.kpi;

import com.kasiudyk.kpi.persistence.TenderRepository;
import com.kasiudyk.kpi.persistence.UserRepository;
import com.kasiudyk.kpi.service.model.Tender;
import com.kasiudyk.kpi.service.model.TenderStatus;
import com.kasiudyk.kpi.service.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class KpiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KpiApplication.class, args);
    }


    @Bean
    public boolean setUpTestDataUser(UserRepository userRepository) {
        userRepository.create(new User()
                .setUsername("User1")
                .setEmail("Email1")
                .setPassword("Password1")
        );
        userRepository.create(new User()
                .setUsername("testUser2")
                .setEmail("testEmail2")
                .setPassword("testPassword2")
        );

        return true;
    }

    @Bean
    public boolean setUpTestDataTender(TenderRepository tenderRepository) {
        tenderRepository.createTender(new Tender()
                .setName("Name1")
                .setDescription("Description1")
                .setStartBet(100L)
                .setActiveBet(0L)
                .setTenderStatus(TenderStatus.ACTIVE)
                .setUser(new User(1L, "User1", "Email1", "Password1"))
        );
        return true;
    }
}
