package com.odercore.license;

import com.odercore.administration.company.service.CompanyLicenseService;
import com.odercore.administration.member.service.MemberLicenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class LicenseScheduler {

    private final CompanyLicenseService companyLicenseService;
    private final MemberLicenseService memberLicenseService;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Yerevan")
    @Transactional
    public void dailyCheck() {
        log.info("🔄 LicenseScheduler started");

        companyLicenseService.dailyCheck();
        memberLicenseService.dailyCheck();

        log.info("✅ LicenseScheduler finished");
    }
}
