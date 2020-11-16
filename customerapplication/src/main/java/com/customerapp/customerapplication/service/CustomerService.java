package com.customerapp.customerapplication.service;

import com.customerapp.customerapplication.dao.CustomerDAO;
import com.customerapp.customerapplication.exception.CustomerNotFoundException;
import com.customerapp.customerapplication.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

//    private Integer customerIdCount=1;
//    private List<Customer> customerList = new CopyOnWriteArrayList<>();


    public Customer addCustomer(Customer customer){
//        customer.setCustomerId(customerIdCount);
//        customerList.add(customer);
//        customerIdCount++;
        return customerDAO.save(customer);
//        return customer;
    }

    public List<Customer> getCustomers(){
        return customerDAO.findAll();
//        return customerList;
    }
    public Customer getCustomer(Integer customerId){
//        return customerList.stream().filter(c -> c.getCustomerId() == customerId)
//                .findFirst().get();
       Optional<Customer> optionalCustomer =  customerDAO.findById(customerId);
       if (!optionalCustomer.isPresent()){
           throw new CustomerNotFoundException("Customer Record is not available");
       }
        return optionalCustomer.get();
    }

    public Customer updateCustomer(Integer customerId, Customer customer) {
//        customerList.stream().forEach(c -> {
//            if (c.getCustomerId()==customerId){
//                c.setCustomerFirstName(customer.getCustomerFirstName());
//                c.setCustomerLastName(customer.getCustomerLastName());
//                c.setCustomerEmail(customer.getCustomerEmail());
//            }
//        });
//        return customerList.stream().filter(c->c.getCustomerId()==customerId).findFirst().get();
        customer.setCustomerId(customerId);
        return customerDAO.save(customer);

    }
    public void deleteCustomer(Integer customerId){
//         customerList.stream().forEach(c-> {
//             if (c.getCustomerId()==customerId){
//                 customerList.remove(c);
//             }
//         });
        customerDAO.deleteById(customerId);
    }

}
