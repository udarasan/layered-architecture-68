package com.example.layeredarchitecture.dao;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();

        while (rst.next()) {
            CustomerDTO customerDTO = new CustomerDTO(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address"));
            allCustomer.add(customerDTO);
        }
        return allCustomer;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.
                execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",
                        dto.getId(),dto.getName(),dto.getAddress());
    }

    @Override
    public void updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",
                dto.getId(),dto.getName(),dto.getAddress());
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT id FROM Customer WHERE id=?",id);
        return rst.next();

    }

    @Override
    public void deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM Customer WHERE id=?",id);
    }

    @Override
    public String genarateId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }
    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?",id);
        rst.next();
        return new CustomerDTO(id + "", rst.getString("name"), rst.getString("address"));
    }
}
