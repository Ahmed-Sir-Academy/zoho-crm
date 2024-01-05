package com.zoho.crm.service;

import com.zoho.crm.responsedto.CustomerAccountDTO;
import com.zoho.crm.responsedto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    @Override
    public ResponseEntity<?> create(CustomerAccountDTO customerAccountDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        //Validate the input data
        //password and confirm password should be same
        //All the data should not be null and empty
        String validateInputResponse = validateInputData(customerAccountDTO);
        if (validateInputResponse.equals("")) {
            //do further logic
            //Database validation
            //username should be unique
        } else {
            responseDTO.setResponse(validateInputResponse);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        return null;
    }

    private String validateInputData(CustomerAccountDTO customerAccountDTO) {
        //String Join
        StringBuilder validationMessage = new StringBuilder();
        if (customerAccountDTO.getFirstName() == null || customerAccountDTO.getFirstName().isEmpty()) {
            validationMessage.append("FirstName Cannot be empty or Null");
        }
        if (customerAccountDTO.getLastName() == null || customerAccountDTO.getLastName().isEmpty()) {
            validationMessage.append("LastName Cannot be empty or Null");
        }
        if (customerAccountDTO.getAge() == 0) {
            validationMessage.append("Age cannot be zero");
        }
        if (customerAccountDTO.getUsername() == null || customerAccountDTO.getUsername().isEmpty()) {
            validationMessage.append("UserName Cannot be empty or Null");
        }
        if (customerAccountDTO.getPassword() == null || customerAccountDTO.getPassword().isEmpty()) {
            validationMessage.append("Password Cannot be empty or Null");
        }
        if (customerAccountDTO.getConfirmPassword() == null || customerAccountDTO.getConfirmPassword().isEmpty()) {
            validationMessage.append("Confirm Password Cannot be empty or Null");
        }
        if (!customerAccountDTO.getPassword().equals(customerAccountDTO.getConfirmPassword())) {
            validationMessage.append("Password and Confirm Password are not same");
        }
        if (customerAccountDTO.getAddress() == null || customerAccountDTO.getAddress().isEmpty()) {
            validationMessage.append("Address Cannot be empty or Null");
        }
        return validationMessage.toString();
    }

}
