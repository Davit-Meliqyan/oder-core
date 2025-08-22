package com.odercore.company.scheduler;

import com.odercore.company.entity.CompanyLicense;
import com.odercore.company.repository.CompanyLicenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class LicenseScheduler {

    private final CompanyLicenseRepository repository;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Yerevan")
    @Transactional
    public void dailyCheck() {
        LocalDateTime now = LocalDateTime.now();
        log.info("🔄 LicenseScheduler started at {}", now);

        List<CompanyLicense> licenses = repository.findAll();

        for (CompanyLicense license : licenses) {
            license.setActive(false);

            if (license.getExpiryReminder() != null) {
                switch (license.getExpiryReminder()) {
                    case ONE_MONTH -> {
                        System.out.println("Notify: license '" + license.getName() +
                                "' expired, reminder was 1 month.");
                        // notificationService.sendOneMonthReminder(license);
                    }
                    case TWO_MONTHS -> {
                        System.out.println("Notify: license '" + license.getName() +
                                "' expired, reminder was 2 months.");
                        // notificationService.sendTwoMonthsReminder(license);
                    }
                }
            }
        }

        repository.saveAll(licenses);

        log.info("✅ LicenseScheduler finished at {}", LocalDateTime.now());
    }
}

