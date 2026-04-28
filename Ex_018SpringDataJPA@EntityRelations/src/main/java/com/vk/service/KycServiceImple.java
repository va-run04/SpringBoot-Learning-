package com.vk.service;

import com.vk.model.Kyc;
import com.vk.repository.KycRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KycServiceImple implements KycService {

    @Autowired
    private KycRepository repo;

    @Override
    public void addKyc(Kyc kyc) {
        repo.save(kyc);
    }

    @Override
    public Kyc getKyc(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new RuntimeException("KYC not found: " + id));
    }

    @Override
    public Kyc getKycByCustomer(Long customerId) {
        return repo.findByCustomerId(customerId);
    }

    @Override
    public void deleteKyc(Long id) {
        repo.deleteById(id);
    }
}