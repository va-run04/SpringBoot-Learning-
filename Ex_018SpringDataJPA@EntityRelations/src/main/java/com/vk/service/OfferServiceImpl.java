package com.vk.service;

import com.vk.model.Offer;
import com.vk.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository repo;

    @Override
    public void addOffer(Offer offer) {
        repo.save(offer);
    }

    @Override
    public Offer getOffer(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new RuntimeException("Offer not found: " + id));
    }

    @Override
    public List<Offer> getAllOffers() {
        return repo.findAll();
    }
}