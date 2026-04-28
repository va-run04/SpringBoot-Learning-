package com.vk.service;

import com.vk.model.Offer;
import java.util.List;

public interface OfferService {
    void addOffer(Offer offer);
    Offer getOffer(Long id);
    List<Offer> getAllOffers();
}