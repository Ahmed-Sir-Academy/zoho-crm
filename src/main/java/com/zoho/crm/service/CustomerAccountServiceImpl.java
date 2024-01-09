package com.zoho.crm.service;

import com.zoho.crm.dao.CustomerDao;
import com.zoho.crm.dao.LoginDao;
import com.zoho.crm.entity.LoginEntity;
import com.zoho.crm.responsedto.CustomerAccountDTO;
import com.zoho.crm.responsedto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

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
                //Create the customer order
                boolean account = customerDao.createAccount(customerAccountDTO);
                if(account){
                    responseDTO.setResponse("Created Customer Account");
                }else{
                    responseDTO.setResponse("Unable to create customer account");
                }

            } else {
                //Return the response like username and password is not valid
                responseDTO.setResponse("Given Username and Password is Wrong");
            }
        } else {
            responseDTO.setResponse(validateInputResponse);
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
