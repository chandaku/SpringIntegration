package com.spring.integration.service;

import com.spring.integration.message.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private static Map<Integer, Customer> customerStorage = new HashMap<>();
    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    static {
        Customer jack = new Customer(1, "jack", 15);
        Customer tom = new Customer(2, "tom", 25);
        customerStorage.put(jack.getId(), jack);
        customerStorage.put(tom.getId(), tom);
    }

    public void insert(Customer customer){
        customerStorage.put(customer.getId(), customer);
        log.info("Customers after POST:");
        for (Map.Entry<Integer, Customer> entry : customerStorage.entrySet()) {
            log.info(entry.getValue().toString());
        }
    }

    public List<Customer> getAll(){
        return customerStorage.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public void delete(int id){
            customerStorage.remove(id);
    }

    public void change(Customer newCust){
        customerStorage.put(newCust.getId(), newCust);
        log.info("Customers after PUT:");
        for (Map.Entry<Integer, Customer> entry : customerStorage.entrySet()) {
            log.info(entry.getValue().toString());
        }
    }
}
