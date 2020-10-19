package com.order.dto;

import lombok.Data;

@Data
public class ServiceRequest {

    private String serviceName;

    private String serviceDescription;

    private String resumptionDate;

    private String resumptionTime;

    private Integer duration;

    private String requesterName;

    private String email;

    private String phoneNumber;

    private String address;

    private String details;

}
