package com.odercore.license;

import com.odercore.common.enums.ExpiryReminder;

import java.time.LocalDateTime;

public interface LicenseLike {

    String getName();

    LocalDateTime getDateOfExpiry();

    ExpiryReminder getExpiryReminder();

    Boolean getActive();

    void setActive(Boolean active);

}
