package com.odercore.license;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
public class LicenseProcessor {

    public <T extends LicenseLike> void processLicenses(List<T> licenses) {
        LocalDateTime now = LocalDateTime.now();

        for (T license : licenses) {
            LocalDateTime expiry = license.getDateOfExpiry();

            if (expiry == null) {
                continue;
            }

            if (expiry.isBefore(now)) {
                license.setActive(false);
                continue;
            }

            if (license.getExpiryReminder() != null) {
                LocalDate today = LocalDate.now();
                LocalDate expiryDate = license.getDateOfExpiry().toLocalDate();

                long monthsBetween = ChronoUnit.MONTHS.between(today, expiryDate);

                switch (license.getExpiryReminder()) {
                    case ONE_MONTH -> {
                        if (monthsBetween == 1) {
                            log.info("📢 Notify: license '{}' expires in 1 month.", license.getName());
                            // notificationService.sendOneMonthReminder(license);
                        }
                    }
                    case TWO_MONTHS -> {
                        if (monthsBetween == 2) {
                            log.info("📢 Notify: license '{}' expires in 2 months.", license.getName());
                            // notificationService.sendTwoMonthsReminder(license);
                        }
                    }
                }
            }
        }
    }
}
