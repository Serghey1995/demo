package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerRepository {
  private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public CustomerRepository() {
        customers = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Jack");
        customer.setLastName("Bauer");
        customer.setEmail("jbauer@example.com");
        customers.add(customer);
    }

    public Customer findById(Integer id) {
        Customer customer = null;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == id) {
            customer = customers.get(i);
            }
        }
        return customer;
    }

public Customer save(Customer customer) {
    if (customer.getId() != null) {
        findById(customer.getId());
        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());
        customer.setEmail(customer.getEmail());

    } else if (customer.getId() == null) {
        int max = 0;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() > max) {
                max = customers.get(i).getId();
            }
        }

        customer.setId(max + 1);
        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());
        customer.setEmail(customer.getEmail());
        customers.add(customer);
    }
    return customer;
}
        public List<Customer> findByAllFields(String value){
            List<Customer> searchCustomers = new ArrayList<>();
            if(value == null || value.isEmpty()) {
                searchCustomers.addAll(customers);
            } else {
                for (int i = 0; i < customers.size(); i++) {
                    if (customers.get(i).getFirstName().equals(value) || customers.get(i).getLastName().equals(value) || customers.get(i).getEmail().equals(value)){
                        searchCustomers.add(customers.get(i));
                    }
                }
            }
            return searchCustomers;
        }
}
