package com.vk.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy  // don't create at startup — create only when first requested
public class HeavyReportService {

    public HeavyReportService() {
        System.out.println("[Lazy] HeavyReportService constructor called — this is expensive!");
    }

    public void generateReport() {
        System.out.println("[Lazy] Generating heavy report...");
    }
}