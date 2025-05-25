package com.ms.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}