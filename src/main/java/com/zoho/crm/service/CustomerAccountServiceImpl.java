package com.zoho.crm.service;

import com.zoho.crm.dao.CustomerDao;
import com.zoho.crm.dao.LoginDao;
import com.zoho.crm.entity.CustomerAccountEntity;
import com.zoho.crm.entity.LoginEntity;
import com.zoho.crm.responsedto.CustomerAccountDTO;
import com.zoho.crm.responsedto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    final static Logger log = LoggerFactory.getLogger(CustomerAccountServiceImpl.class);

    @Autowired
    LoginDao loginDao;

    @Autowired
    CustomerDao customerDao;

    @Override
    public ResponseEntity<?> create(CustomerAccountDTO customerAccountDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        //Validate the input data
        String validateInputResponse = validateInputData(customerAccountDTO);

        if (validateInputResponse.equals("")) {

            //Database validation by checking the given username and password is correct or wrong
            LoginEntity loginEntity = loginDao.validateUserNameAndPasswordFromDataBase(customerAccountDTO.getUsername(), customerAccountDTO.getPassword());

            if (loginEntity != null) {
                //Validate the username from customer table
                boolean userNamePresent = customerDao.findByUsername(customerAccountDTO.getUsername());

                if (userNamePresent) {
                    //UserName is present sending a custom message
                    responseDTO.setResponse("Username is already taken");
                } else {
                    //Create the customer order
                    boolean account = customerDao.createAccount(customerAccountDTO);
                    if (account) {
                        responseDTO.setResponse("Created Customer Account");
                    } else {
                        responseDTO.setResponse("Unable to create customer account");
                    }
                }

            } else {
                //Return the response like username and password is not valid
                responseDTO.setResponse("Given Username and Password is Wrong");
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }
        } else {
            responseDTO.setResponse(validateInputResponse);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity viewByUsername(String userName) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (userName == null || userName.isEmpty()) {
            responseDTO.setResponse("Username cannot be empty");
        }

        CustomerAccountEntity customerAccountEntity = customerDao.findByUsernameForView(userName);
        if (customerAccountEntity == null) {
            responseDTO.setResponse("No record found with username: " + userName);
            log.error("No record found with username: " + userName);
            return new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
        }
        responseDTO.setResponse(customerAccountEntity);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteById(int id) {

        ResponseDTO responseDTO = new ResponseDTO();
        try {
            if (id == 0 || id < 0) {
                responseDTO.setResponse("Id cannot be zero or less than zero");
            } else {
                boolean deleted = customerDao.deleteById(id);
                if (deleted) {
                    responseDTO.setResponse("Successfully deleted the user for id " + id);
                } else {
                    responseDTO.setResponse("No user found for id " + id);
                }
            }
        }finally {
            System.out.println("This is without catch block");
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateById(int id, CustomerAccountDTO customerAccountDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (id == 0 || id < 0) {
            responseDTO.setResponse("Id cannot be zero or less than zero");
        } else {
            CustomerAccountEntity updatedEntity = customerDao.updateById(id, customerAccountDTO);
            if (updatedEntity == null) {
                responseDTO.setResponse("The id given is not found");
            } else {
                responseDTO.setResponse(updatedEntity);
            }

        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    private String validateInputData(CustomerAccountDTO customerAccountDTO) {
        //String Join
        StringJoiner validationMessage = new StringJoiner(" and ");
        if (customerAccountDTO.getFirstName() == null || customerAccountDTO.getFirstName().isEmpty()) {
            validationMessage.add("FirstName Cannot be empty or Null");
        }
        if (customerAccountDTO.getLastName() == null || customerAccountDTO.getLastName().isEmpty()) {
            validationMessage.add("LastName Cannot be empty or Null");
        }
        if (customerAccountDTO.getAge() == 0) {
            validationMessage.add("Age cannot be zero");
        }
        if (customerAccountDTO.getUsername() == null || customerAccountDTO.getUsername().isEmpty()) {
            validationMessage.add("UserName Cannot be empty or Null");
        }
        if (customerAccountDTO.getPassword() == null || customerAccountDTO.getPassword().isEmpty()) {
            validationMessage.add("Password Cannot be empty or Null");
        }
        if (customerAccountDTO.getConfirmPassword() == null || customerAccountDTO.getConfirmPassword().isEmpty()) {
            validationMessage.add("Confirm Password Cannot be empty or Null");
        }
        if (!customerAccountDTO.getPassword().equals(customerAccountDTO.getConfirmPassword())) {
            validationMessage.add("Password and Confirm Password are not same");
        }
        if (customerAccountDTO.getAddress() == null || customerAccountDTO.getAddress().isEmpty()) {
            validationMessage.add("Address Cannot be empty or Null");
        }
        return validationMessage.toString();
    }

}
