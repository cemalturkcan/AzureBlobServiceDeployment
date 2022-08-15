package com.dev.basic.service;

import com.dev.basic.dto.UserPrivacyAgreementDto;
import com.dev.basic.entity.UserPrivacyAgreement;
import com.dev.basic.repository.UserPrivacyAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPrivacyAgreementService {
    @Autowired
    UserPrivacyAgreementRepository userPrivacyAgreementRepository;

    public UserPrivacyAgreement toEntity(UserPrivacyAgreementDto dto) {
        UserPrivacyAgreement userPrivacyAgreement = new UserPrivacyAgreement();
        userPrivacyAgreement.setUserPrivacyAgreementId(dto.userPrivacyAgreementId);
        userPrivacyAgreement.setAgreement(dto.agreement);
        return userPrivacyAgreement;
    }

    public UserPrivacyAgreementDto toResource(UserPrivacyAgreement userPrivacyAgreement){
        UserPrivacyAgreementDto userPrivacyAgreementDto = new UserPrivacyAgreementDto();
        userPrivacyAgreementDto.agreement = userPrivacyAgreement.isAgreement();
        return userPrivacyAgreementDto;
    }

}
