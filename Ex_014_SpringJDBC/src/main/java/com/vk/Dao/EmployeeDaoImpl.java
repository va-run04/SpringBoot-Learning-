package com.vk.Dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.vk.model.Employee;

@Repository("dao")
public class EmployeeDaoImpl implements IEmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getEmployeeInfo() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("city")
            )
        );
    }
}