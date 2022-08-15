package com.dev.basic.repository;


import com.dev.basic.entity.UserPrivacyAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPrivacyAgreementRepository extends JpaRepository <UserPrivacyAgreement, Long> {

}
