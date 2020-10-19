package com.order.services;

import com.order.dto.ServiceRequest;
import com.order.dto.UpdateRequest;
import com.order.model.Request;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<Request> getAllOrders();

    Request getSingleOrder(String id);

    Map<String, String> createOrder(ServiceRequest serviceRequest) throws Exception;

    void deleteOrder(String id);

    void updateRequestStatus(String id, UpdateRequest updateRequest);

}
