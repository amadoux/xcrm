package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class EmployeeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Employee getEmployeeSample1() {
        return new Employee()
            .id(1L)
            .firstName("firstName1")
            .lastName("lastName1")
            .email("email1")
            .phoneNumber("phoneNumber1")
            .identityCard("identityCard1")
            .dateInspiration("dateInspiration1")
            .companyName("companyName1")
            .cityAgency("cityAgency1")
            .residenceCity("residenceCity1")
            .address("address1")
            .socialSecurityNumber("socialSecurityNumber1")
            .birthPlace("birthPlace1")
            .workstation("workstation1")
            .descriptionWorkstation("descriptionWorkstation1")
            .coefficient(1L)
            .employedManager("employedManager1")
            .numberHours("numberHours1")
            .averageHourlyCost("averageHourlyCost1")
            .monthlyGrossAmount(1L)
            .commissionAmount(1L);
    }

    public static Employee getEmployeeSample2() {
        return new Employee()
            .id(2L)
            .firstName("firstName2")
            .lastName("lastName2")
            .email("email2")
            .phoneNumber("phoneNumber2")
            .identityCard("identityCard2")
            .dateInspiration("dateInspiration2")
            .companyName("companyName2")
            .cityAgency("cityAgency2")
            .residenceCity("residenceCity2")
            .address("address2")
            .socialSecurityNumber("socialSecurityNumber2")
            .birthPlace("birthPlace2")
            .workstation("workstation2")
            .descriptionWorkstation("descriptionWorkstation2")
            .coefficient(2L)
            .employedManager("employedManager2")
            .numberHours("numberHours2")
            .averageHourlyCost("averageHourlyCost2")
            .monthlyGrossAmount(2L)
            .commissionAmount(2L);
    }

    public static Employee getEmployeeRandomSampleGenerator() {
        return new Employee()
            .id(longCount.incrementAndGet())
            .firstName(UUID.randomUUID().toString())
            .lastName(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .phoneNumber(UUID.randomUUID().toString())
            .identityCard(UUID.randomUUID().toString())
            .dateInspiration(UUID.randomUUID().toString())
            .companyName(UUID.randomUUID().toString())
            .cityAgency(UUID.randomUUID().toString())
            .residenceCity(UUID.randomUUID().toString())
            .address(UUID.randomUUID().toString())
            .socialSecurityNumber(UUID.randomUUID().toString())
            .birthPlace(UUID.randomUUID().toString())
            .workstation(UUID.randomUUID().toString())
            .descriptionWorkstation(UUID.randomUUID().toString())
            .coefficient(longCount.incrementAndGet())
            .employedManager(UUID.randomUUID().toString())
            .numberHours(UUID.randomUUID().toString())
            .averageHourlyCost(UUID.randomUUID().toString())
            .monthlyGrossAmount(longCount.incrementAndGet())
            .commissionAmount(longCount.incrementAndGet());
    }
}
