package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.DepartmentTestSamples.*;
import static com.mycompany.myapp.domain.EmployeeTestSamples.*;
import static com.mycompany.myapp.domain.EmployeeTestSamples.*;
import static com.mycompany.myapp.domain.EnterpriseTestSamples.*;
import static com.mycompany.myapp.domain.JobHistoryTestSamples.*;
import static com.mycompany.myapp.domain.JobTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Employee.class);
        Employee employee1 = getEmployeeSample1();
        Employee employee2 = new Employee();
        assertThat(employee1).isNotEqualTo(employee2);

        employee2.setId(employee1.getId());
        assertThat(employee1).isEqualTo(employee2);

        employee2 = getEmployeeSample2();
        assertThat(employee1).isNotEqualTo(employee2);
    }

    @Test
    void enterpriseTest() throws Exception {
        Employee employee = getEmployeeRandomSampleGenerator();
        Enterprise enterpriseBack = getEnterpriseRandomSampleGenerator();

        employee.setEnterprise(enterpriseBack);
        assertThat(employee.getEnterprise()).isEqualTo(enterpriseBack);

        employee.enterprise(null);
        assertThat(employee.getEnterprise()).isNull();
    }

    @Test
    void jobTest() throws Exception {
        Employee employee = getEmployeeRandomSampleGenerator();
        Job jobBack = getJobRandomSampleGenerator();

        employee.addJob(jobBack);
        assertThat(employee.getJobs()).containsOnly(jobBack);
        assertThat(jobBack.getEmployee()).isEqualTo(employee);

        employee.removeJob(jobBack);
        assertThat(employee.getJobs()).doesNotContain(jobBack);
        assertThat(jobBack.getEmployee()).isNull();

        employee.jobs(new HashSet<>(Set.of(jobBack)));
        assertThat(employee.getJobs()).containsOnly(jobBack);
        assertThat(jobBack.getEmployee()).isEqualTo(employee);

        employee.setJobs(new HashSet<>());
        assertThat(employee.getJobs()).doesNotContain(jobBack);
        assertThat(jobBack.getEmployee()).isNull();
    }

    @Test
    void managerTest() throws Exception {
        Employee employee = getEmployeeRandomSampleGenerator();
        Employee employeeBack = getEmployeeRandomSampleGenerator();

        employee.setManager(employeeBack);
        assertThat(employee.getManager()).isEqualTo(employeeBack);

        employee.manager(null);
        assertThat(employee.getManager()).isNull();
    }

    @Test
    void departmentTest() throws Exception {
        Employee employee = getEmployeeRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        employee.setDepartment(departmentBack);
        assertThat(employee.getDepartment()).isEqualTo(departmentBack);

        employee.department(null);
        assertThat(employee.getDepartment()).isNull();
    }

    @Test
    void employeTest() throws Exception {
        Employee employee = getEmployeeRandomSampleGenerator();
        Employee employeeBack = getEmployeeRandomSampleGenerator();

        employee.addEmploye(employeeBack);
        assertThat(employee.getEmployes()).containsOnly(employeeBack);
        assertThat(employeeBack.getManager()).isEqualTo(employee);

        employee.removeEmploye(employeeBack);
        assertThat(employee.getEmployes()).doesNotContain(employeeBack);
        assertThat(employeeBack.getManager()).isNull();

        employee.employes(new HashSet<>(Set.of(employeeBack)));
        assertThat(employee.getEmployes()).containsOnly(employeeBack);
        assertThat(employeeBack.getManager()).isEqualTo(employee);

        employee.setEmployes(new HashSet<>());
        assertThat(employee.getEmployes()).doesNotContain(employeeBack);
        assertThat(employeeBack.getManager()).isNull();
    }

    @Test
    void jobHistoryTest() throws Exception {
        Employee employee = getEmployeeRandomSampleGenerator();
        JobHistory jobHistoryBack = getJobHistoryRandomSampleGenerator();

        employee.setJobHistory(jobHistoryBack);
        assertThat(employee.getJobHistory()).isEqualTo(jobHistoryBack);
        assertThat(jobHistoryBack.getEmployee()).isEqualTo(employee);

        employee.jobHistory(null);
        assertThat(employee.getJobHistory()).isNull();
        assertThat(jobHistoryBack.getEmployee()).isNull();
    }
}
