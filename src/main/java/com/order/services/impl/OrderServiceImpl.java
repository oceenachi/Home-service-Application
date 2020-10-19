package com.order.services.impl;

import com.order.dto.ServiceRequest;
import com.order.dto.UpdateRequest;
import com.order.model.Request;
import com.order.model.RequestStatus;
import com.order.model.ServiceName;
import com.order.repository.RequestRepository;
import com.order.services.OrderService;
import com.order.utils.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RequestRepository requestRepository;

    public OrderServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public List<Request> getAllOrders() {
        logger.info("Retrieving all orders");
        return requestRepository.findAll();
    }

    @Override
    public Request getSingleOrder(String id) {
        logger.info("Retrieving single service request");
        return requestRepository.findById(Long.parseLong(id)).orElseThrow();
    }

    @Override
    public Map<String, String> createOrder(ServiceRequest serviceRequest) throws Exception {
        Request newRequest = new Request();
        newRequest.setAddress(serviceRequest.getAddress());
        newRequest.setCreatedAt(LocalDateTime.now());
        newRequest.setDetails(serviceRequest.getDetails());
        newRequest.setDuration(Integer.parseInt(serviceRequest.getDuration().toString()));
        newRequest.setServiceDescription(serviceRequest.getServiceDescription());
        newRequest.setEmail(serviceRequest.getEmail());
        newRequest.setPhoneNumber(serviceRequest.getPhoneNumber());
        newRequest.setRequesterName(serviceRequest.getRequesterName());
        newRequest.setResumptionTime(ServiceUtils.formatDate(serviceRequest.getResumptionDate() + " " + serviceRequest.getResumptionTime()));
        newRequest.setRequestStatus(RequestStatus.PENDING);
        newRequest.setServiceName(ServiceName.valueOf(serviceRequest.getServiceName().toUpperCase()));
        Double serviceCharge = ServiceUtils.calculateServiceCharge(serviceRequest.getDuration(), serviceRequest.getServiceName().toUpperCase());
        newRequest.setCost(serviceCharge);
        logger.info("Saving new service request");
        requestRepository.save(newRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Request received. Processing request......");
        response.put("Service Charge", "Your service costs " + serviceCharge);

        return response;
    }

    @Override
    public void deleteOrder(String id) {
        logger.info("Deleting request with id " + id);
        requestRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public void updateRequestStatus(String id, UpdateRequest updateRequest) {
        Request request = requestRepository.findById(Long.parseLong(id)).orElseThrow();
        if(updateRequest.getDuration() != null ) {
            request.setDuration(updateRequest.getDuration());
        }
        if(updateRequest.getServiceDescription() != null ){
            request.setServiceDescription(updateRequest.getServiceDescription());
        }
        if(updateRequest.getServiceName() != null){
            request.setServiceName(ServiceName.valueOf(updateRequest.getServiceName().toUpperCase()));
        }
        logger.info("Updating request");
        requestRepository.save(request);
    }

}
