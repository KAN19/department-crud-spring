package com.dailycodebuffer.springboottutorial.repository;

import com.dailycodebuffer.springboottutorial.entity.Department;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository underTest;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @Disabled
    void canFindByDepartmentNameIgnoreCase() {
        //given

        //when
        //then
    }
}