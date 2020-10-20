package com.order.services;

public interface EmailService {
    void sendEmail(String requesterName, String serviceType, String resumptionTime);
}
