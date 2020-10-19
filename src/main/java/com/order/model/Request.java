package com.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "request_order")
@Data
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    @Column
    private ServiceName serviceName;

    @Column
    private String serviceDescription;

    @Column
    private LocalDateTime resumptionTime;

    @Column
    private Integer duration;

    @Column
    private String requesterName;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String details;

    @Column
    private Double cost;

    @Column
    @Enumerated
    private RequestStatus requestStatus;

}
