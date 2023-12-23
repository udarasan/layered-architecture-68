package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;

public class CustomerBOImpl {
    CustomerDAO customerDAO=new CustomerDAOImpl();
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException{
        //customer business logic example
        return customerDAO.save(dto);
    }
}
