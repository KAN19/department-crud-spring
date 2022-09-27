package com.dailycodebuffer.springboottutorial.repository;

import com.dailycodebuffer.springboottutorial.entity.Department;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void canFindByDepartmentNameIgnoreCase() {
        //given
        String name = "System";
        Department department = Department.builder()
                .departmentAddress("Block D")
                .departmentCode("SS")
                .departmentName("System")
                .build();
        underTest.save(department);
        //when
        Department foundedDepartment = underTest.findByDepartmentNameIgnoreCase(name);
        //then
        assertThat(foundedDepartment).isEqualTo(department);
    }

    @Test
    void returnNullIfDepartmentNotFound() {
        //given
        String name = "System";
        //when
        Department foundedDepartment = underTest.findByDepartmentNameIgnoreCase(name);
        //then
        assertThat(foundedDepartment).isEqualTo(null);
    }
}