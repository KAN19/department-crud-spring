package com.dailycodebuffer.springboottutorial.service;

import com.dailycodebuffer.springboottutorial.entity.Department;
import com.dailycodebuffer.springboottutorial.error.DepartmentNotFoundException;
import com.dailycodebuffer.springboottutorial.repository.DepartmentRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    private DepartmentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new DepartmentServiceImpl(departmentRepository);
    }

    @Test
    void canSaveDepartment() {
        //given
        Department department =
                Department.builder()
                        .departmentName("IT")
                        .departmentCode("IT-06")
                        .departmentAddress("Hello")
                        .departmentId(1L)
                        .build();
        //when
        underTest.saveDepartment(department);
        //then
        ArgumentCaptor<Department> departmentArgumentCaptor
                = ArgumentCaptor.forClass(Department.class);
        verify(departmentRepository).save(departmentArgumentCaptor.capture());

        Department capturedDepartment = departmentArgumentCaptor.getValue();

        assertThat(capturedDepartment).isEqualTo(department);
    }

    @Test
    void canFetchDepartmentList() {
        //when
        underTest.fetchDepartmentList();
        //then
        verify(departmentRepository).findAll();
    }

    @Test
    void canFetchDepartmentById() throws DepartmentNotFoundException {
        //given
        Department department =
                Department.builder()
                        .departmentName("IT")
                        .departmentCode("IT-06")
                        .departmentAddress("Hello")
                        .departmentId(1L)
                        .build();
        given(departmentRepository.findById(department.getDepartmentId())).willReturn(Optional.of(department));
        //when
        Department testingDepartment = underTest.fetchDepartmentById(department.getDepartmentId());
        //then
        assertThat(testingDepartment).isEqualTo(department);
    }

    @Test
    void willThrowWhenIdNotExisted() {
        //given
        Long departmentId = 1L;

        given(departmentRepository.findById(departmentId)).willReturn(Optional.empty());
        //when
        //then
        assertThatThrownBy(() -> underTest.fetchDepartmentById(departmentId))
                .isInstanceOf(DepartmentNotFoundException.class)
                .hasMessage("Department not found");
    }

    @Test
    @Disabled
    void deleteDepartmentById() {
    }

    @Test
    @Disabled
    void updateDepartment() {
    }

    @Test
    @Disabled
    void fetchDepartmentByName() {
    }
}