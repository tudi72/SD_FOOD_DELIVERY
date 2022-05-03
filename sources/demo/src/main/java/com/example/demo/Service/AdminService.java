package com.example.demo.Service;

import com.example.demo.Model.Customer;
import com.example.demo.Model.DTOs.OneStringDTO;
import com.example.demo.Model.MealCopy;
import com.example.demo.Model.MyOrder;
import com.example.demo.Model.User;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;
import java.util.List;

/**
 * @author Zaharia Tudorita
 * @since May 02, 2022
 */
@Service
public class AdminService {

    @Autowired
    private JavaMailSender mailSender;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final Logger logger = Logger.getLogger(AdminService.class.getName());


    @Autowired
    AdminService(AdminRepository adminRepository, UserRepository userRepository){
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    /**
     * Sends email to admin with infos about last order of the customer
     * @param customerResponse the current customer sending his last order created
     * @param dto body with fields to be sent in the email
     * @return status of the email if it was sent or not
     */
    public HttpStatus sendEmailToAdmin(ResponseEntity<Customer> customerResponse, OneStringDTO dto) {
        try{
            Customer customer = customerResponse.getBody();
            User customerUser = customer.getUser();
            MyOrder currOrder = customer.getOrderList().get(customer.getOrderList().size()-1);
            String message = "Order placed by        : " + customerUser.getName() + "\n" +
                             "having the total price : " + dto.getOneDouble() + " EURO\n" +
                             "Address of delivery    : " + customer.getAddress() + "\n" +
                             "Notes from customer    : " + dto.getOneString() + "\n\n" +
                             "List of meals          : \n" ;

            List<MealCopy> mealCopies = currOrder.getBasket().getMeals();
            if(mealCopies == null || mealCopies.size() == 0) throw new ResourceNotFoundException("nothing found");
            for(MealCopy meal : mealCopies){
                message = message  +  "Meal Name              : " + meal.getMeal().getName() + " having price : " + meal.getMeal().getPrice() + " EURO\n";
            }

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("New order!");
            mailMessage.setText(message);
            mailMessage.setTo("ztudorita@gmail.com");
            mailMessage.setFrom("ztudorita@gmail.com");

            mailSender.send(mailMessage);
            return HttpStatus.ACCEPTED;
        }
        catch (ResourceNotFoundException ex1) {
            logger.warn("AdminService : sendEmailToAdmin -> customer has no orders or no meals ordered");
        } catch (MailAuthenticationException ex3){
            logger.warn("AdminService : sendEmailToAdmin -> email couldn't be sent due to an authentication failure ");
        } catch (MailException ex2){
            logger.warn("AdminService : sendEmailToAdmin -> email couldn't be sent due to a failure ");
        } catch (Exception ex4){
            logger.warn("AdminService : sendEmailToAdmin -> customer is null");
        }
        return HttpStatus.BAD_REQUEST;
    }

}
