package com.vk.service;

import com.vk.model.Kyc;

public interface KycService {
    void addKyc(Kyc kyc);
    Kyc getKyc(Long id);
    Kyc getKycByCustomer(Long customerId);
    void deleteKyc(Long id);
}