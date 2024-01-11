package com.zoho.crm.service;

import com.zoho.crm.responsedto.CustomerAccountDTO;
import com.zoho.crm.responsedto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class JDBCServiceImpl implements JDBCService {

    static final String DB_URL = "jdbc:mysql://localhost:3306/crm";
    static final String USER = "root";
    static final String PASS = "test";
    static final String QUERY = "SELECT * FROM C_ACCOUNT";

    @Override
    public ResponseEntity<?> fetchAllCustomerAccount() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<CustomerAccountDTO> customerAccountDTOList = new ArrayList<>();
        //Not JPA! It's JDBC
        try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);) {

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                CustomerAccountDTO customerAccountDTO = new CustomerAccountDTO();
                customerAccountDTO.setFirstName(rs.getString("first_name"));
                customerAccountDTO.setLastName(rs.getString("last_name"));
                customerAccountDTO.setAge(rs.getInt("age"));
                customerAccountDTO.setPassword(rs.getString("password"));
                customerAccountDTO.setConfirmPassword(rs.getString("confirm_passowrd"));
                customerAccountDTO.setUsername(rs.getString("username"));
                customerAccountDTO.setAddress(rs.getString("address"));
                customerAccountDTOList.add(customerAccountDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        responseDTO.setResponse(customerAccountDTOList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
