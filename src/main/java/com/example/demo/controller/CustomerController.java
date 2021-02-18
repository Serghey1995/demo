package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller("/customers")
public class CustomerController extends ResourceNotFoundException {
    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    @GetMapping("/customers/{id}")
    public String customer(@PathVariable(name = "id") Integer id, Model model) {

        Customer customer = customerRepository.findById(id);
        if (customer == null) throw new ResourceNotFoundException("Customer with id = " + id + " not found");
        model.addAttribute("customer", customer);
        return "customer";

  }
    @GetMapping("/customers/edit/{id}")
    public String customerForm(@PathVariable(name = "id") Integer id, Model model) {

        model.addAttribute("customer", customerRepository.findById(id));

        return "customer-form";
    }

    @GetMapping("/customers")
    public String customer(Model model) {
        model.addAttribute("customers", customerRepository.getCustomers());
        return "customer-list";
    }

    @PostMapping("/customers")
    public String customerSubmit(@ModelAttribute @Valid Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "customer-form";
        }
        customerRepository.save(customer);
        return "redirect:/customers/" + customer.getId();
    }
    @GetMapping("/customers/add")
    public String customerAdd (Model model){
        model.addAttribute("customer",new Customer());
        return "customer-form";
    }
    @GetMapping("/customers/search")
    public String search(@RequestParam(name = "query", required = false) String  query,
                         Model model) {

        model.addAttribute("customers",customerRepository.findByAllFields(query));

       return "search";
    }
}

