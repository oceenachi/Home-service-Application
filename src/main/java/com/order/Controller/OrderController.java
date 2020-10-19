package com.order.Controller;

import com.order.dto.ServiceRequest;
import com.order.dto.UpdateRequest;
import com.order.model.Request;
import com.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/home")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "Successful");
        response.put("message", "We can receive your orders now ....🎆🎆🎆");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Request>> getAllOrders() {
        List<Request> response = orderService.getAllOrders();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Request> getSingleOrder(@PathVariable String id) {
        Request singleRequest = orderService.getSingleOrder(id);
        return new ResponseEntity<>(singleRequest, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createOrder(@RequestBody ServiceRequest serviceRequest) throws Exception {
        Map<String, String> response = orderService.createOrder(serviceRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateOrderStatus(@PathVariable String id, @RequestBody UpdateRequest updateRequest) {
        orderService.updateRequestStatus(id, updateRequest);
        Map<String, String> response = new HashMap<>();
        response.put("status", "Successful");
        response.put("message", "Order has been updated successfully ....😊😊😊");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "Successful");
        response.put("message", String.format("Request with id %s has been deleted successfully....😎😎😎", id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
