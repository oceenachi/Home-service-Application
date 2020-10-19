package com.order.dto;

import com.order.model.ServiceName;
import lombok.Data;
@Data
public class UpdateRequest {

    private String serviceName;

    private String serviceDescription;

    private Integer duration;

}
