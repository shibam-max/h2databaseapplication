package com.shibaminmemorydatabase.h2database.dao;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shibaminmemorydatabase.h2database.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByDept(String dept);

}
