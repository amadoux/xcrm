package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.ContractType;
import com.mycompany.myapp.domain.enumeration.Level;
import com.mycompany.myapp.domain.enumeration.Pays;
import com.mycompany.myapp.domain.enumeration.SalaryType;
import com.mycompany.myapp.domain.enumeration.TypeEmployed;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The Employee entity.
 */
@Schema(description = "The Employee entity.")
@Entity
@Table(name = "employee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(name = "identity_card", nullable = false)
    private String identityCard;

    @Column(name = "date_inspiration")
    private String dateInspiration;

    @Enumerated(EnumType.STRING)
    @Column(name = "nationality")
    private Pays nationality;

    @Lob
    @Column(name = "upload_identity_card")
    private byte[] uploadIdentityCard;

    @Column(name = "upload_identity_card_content_type")
    private String uploadIdentityCardContentType;

    @Column(name = "company_name")
    private String companyName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_employed")
    private TypeEmployed typeEmployed;

    @Column(name = "city_agency")
    private String cityAgency;

    @Column(name = "residence_city")
    private String residenceCity;

    @Column(name = "address")
    private String address;

    @Column(name = "social_security_number")
    private String socialSecurityNumber;

    @Column(name = "birth_date")
    private Instant birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "entry_date")
    private Instant entryDate;

    @Column(name = "release_date")
    private Instant releaseDate;

    @Column(name = "workstation")
    private String workstation;

    @Column(name = "description_workstation")
    private String descriptionWorkstation;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;

    @Column(name = "coefficient")
    private Long coefficient;

    @Column(name = "employed_manager")
    private String employedManager;

    @Column(name = "number_hours")
    private String numberHours;

    @Column(name = "average_hourly_cost")
    private String averageHourlyCost;

    @Column(name = "monthly_gross_amount")
    private Long monthlyGrossAmount;

    @Column(name = "commission_amount")
    private Long commissionAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "contract_type")
    private ContractType contractType;

    @Enumerated(EnumType.STRING)
    @Column(name = "salary_type")
    private SalaryType salaryType;

    @Column(name = "hire_date")
    private Instant hireDate;

    @JsonIgnoreProperties(value = { "employee" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Enterprise enterprise;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "tasks", "employee", "jobHistory" }, allowSetters = true)
    private Set<Job> jobs = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "enterprise", "jobs", "manager", "department", "jobHistory" }, allowSetters = true)
    private Employee manager;

    /**
     * Another side of the same relationship
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "location", "employees", "jobHistory" }, allowSetters = true)
    private Department department;

    @JsonIgnoreProperties(value = { "job", "department", "employee" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "employee")
    private JobHistory jobHistory;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Employee id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Employee firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Employee lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public Employee email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Employee phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentityCard() {
        return this.identityCard;
    }

    public Employee identityCard(String identityCard) {
        this.setIdentityCard(identityCard);
        return this;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getDateInspiration() {
        return this.dateInspiration;
    }

    public Employee dateInspiration(String dateInspiration) {
        this.setDateInspiration(dateInspiration);
        return this;
    }

    public void setDateInspiration(String dateInspiration) {
        this.dateInspiration = dateInspiration;
    }

    public Pays getNationality() {
        return this.nationality;
    }

    public Employee nationality(Pays nationality) {
        this.setNationality(nationality);
        return this;
    }

    public void setNationality(Pays nationality) {
        this.nationality = nationality;
    }

    public byte[] getUploadIdentityCard() {
        return this.uploadIdentityCard;
    }

    public Employee uploadIdentityCard(byte[] uploadIdentityCard) {
        this.setUploadIdentityCard(uploadIdentityCard);
        return this;
    }

    public void setUploadIdentityCard(byte[] uploadIdentityCard) {
        this.uploadIdentityCard = uploadIdentityCard;
    }

    public String getUploadIdentityCardContentType() {
        return this.uploadIdentityCardContentType;
    }

    public Employee uploadIdentityCardContentType(String uploadIdentityCardContentType) {
        this.uploadIdentityCardContentType = uploadIdentityCardContentType;
        return this;
    }

    public void setUploadIdentityCardContentType(String uploadIdentityCardContentType) {
        this.uploadIdentityCardContentType = uploadIdentityCardContentType;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public Employee companyName(String companyName) {
        this.setCompanyName(companyName);
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public TypeEmployed getTypeEmployed() {
        return this.typeEmployed;
    }

    public Employee typeEmployed(TypeEmployed typeEmployed) {
        this.setTypeEmployed(typeEmployed);
        return this;
    }

    public void setTypeEmployed(TypeEmployed typeEmployed) {
        this.typeEmployed = typeEmployed;
    }

    public String getCityAgency() {
        return this.cityAgency;
    }

    public Employee cityAgency(String cityAgency) {
        this.setCityAgency(cityAgency);
        return this;
    }

    public void setCityAgency(String cityAgency) {
        this.cityAgency = cityAgency;
    }

    public String getResidenceCity() {
        return this.residenceCity;
    }

    public Employee residenceCity(String residenceCity) {
        this.setResidenceCity(residenceCity);
        return this;
    }

    public void setResidenceCity(String residenceCity) {
        this.residenceCity = residenceCity;
    }

    public String getAddress() {
        return this.address;
    }

    public Employee address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSocialSecurityNumber() {
        return this.socialSecurityNumber;
    }

    public Employee socialSecurityNumber(String socialSecurityNumber) {
        this.setSocialSecurityNumber(socialSecurityNumber);
        return this;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Instant getBirthDate() {
        return this.birthDate;
    }

    public Employee birthDate(Instant birthDate) {
        this.setBirthDate(birthDate);
        return this;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public Employee birthPlace(String birthPlace) {
        this.setBirthPlace(birthPlace);
        return this;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Instant getEntryDate() {
        return this.entryDate;
    }

    public Employee entryDate(Instant entryDate) {
        this.setEntryDate(entryDate);
        return this;
    }

    public void setEntryDate(Instant entryDate) {
        this.entryDate = entryDate;
    }

    public Instant getReleaseDate() {
        return this.releaseDate;
    }

    public Employee releaseDate(Instant releaseDate) {
        this.setReleaseDate(releaseDate);
        return this;
    }

    public void setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getWorkstation() {
        return this.workstation;
    }

    public Employee workstation(String workstation) {
        this.setWorkstation(workstation);
        return this;
    }

    public void setWorkstation(String workstation) {
        this.workstation = workstation;
    }

    public String getDescriptionWorkstation() {
        return this.descriptionWorkstation;
    }

    public Employee descriptionWorkstation(String descriptionWorkstation) {
        this.setDescriptionWorkstation(descriptionWorkstation);
        return this;
    }

    public void setDescriptionWorkstation(String descriptionWorkstation) {
        this.descriptionWorkstation = descriptionWorkstation;
    }

    public Level getLevel() {
        return this.level;
    }

    public Employee level(Level level) {
        this.setLevel(level);
        return this;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Long getCoefficient() {
        return this.coefficient;
    }

    public Employee coefficient(Long coefficient) {
        this.setCoefficient(coefficient);
        return this;
    }

    public void setCoefficient(Long coefficient) {
        this.coefficient = coefficient;
    }

    public String getEmployedManager() {
        return this.employedManager;
    }

    public Employee employedManager(String employedManager) {
        this.setEmployedManager(employedManager);
        return this;
    }

    public void setEmployedManager(String employedManager) {
        this.employedManager = employedManager;
    }

    public String getNumberHours() {
        return this.numberHours;
    }

    public Employee numberHours(String numberHours) {
        this.setNumberHours(numberHours);
        return this;
    }

    public void setNumberHours(String numberHours) {
        this.numberHours = numberHours;
    }

    public String getAverageHourlyCost() {
        return this.averageHourlyCost;
    }

    public Employee averageHourlyCost(String averageHourlyCost) {
        this.setAverageHourlyCost(averageHourlyCost);
        return this;
    }

    public void setAverageHourlyCost(String averageHourlyCost) {
        this.averageHourlyCost = averageHourlyCost;
    }

    public Long getMonthlyGrossAmount() {
        return this.monthlyGrossAmount;
    }

    public Employee monthlyGrossAmount(Long monthlyGrossAmount) {
        this.setMonthlyGrossAmount(monthlyGrossAmount);
        return this;
    }

    public void setMonthlyGrossAmount(Long monthlyGrossAmount) {
        this.monthlyGrossAmount = monthlyGrossAmount;
    }

    public Long getCommissionAmount() {
        return this.commissionAmount;
    }

    public Employee commissionAmount(Long commissionAmount) {
        this.setCommissionAmount(commissionAmount);
        return this;
    }

    public void setCommissionAmount(Long commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public ContractType getContractType() {
        return this.contractType;
    }

    public Employee contractType(ContractType contractType) {
        this.setContractType(contractType);
        return this;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public SalaryType getSalaryType() {
        return this.salaryType;
    }

    public Employee salaryType(SalaryType salaryType) {
        this.setSalaryType(salaryType);
        return this;
    }

    public void setSalaryType(SalaryType salaryType) {
        this.salaryType = salaryType;
    }

    public Instant getHireDate() {
        return this.hireDate;
    }

    public Employee hireDate(Instant hireDate) {
        this.setHireDate(hireDate);
        return this;
    }

    public void setHireDate(Instant hireDate) {
        this.hireDate = hireDate;
    }

    public Enterprise getEnterprise() {
        return this.enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Employee enterprise(Enterprise enterprise) {
        this.setEnterprise(enterprise);
        return this;
    }

    public Set<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(Set<Job> jobs) {
        if (this.jobs != null) {
            this.jobs.forEach(i -> i.setEmployee(null));
        }
        if (jobs != null) {
            jobs.forEach(i -> i.setEmployee(this));
        }
        this.jobs = jobs;
    }

    public Employee jobs(Set<Job> jobs) {
        this.setJobs(jobs);
        return this;
    }

    public Employee addJob(Job job) {
        this.jobs.add(job);
        job.setEmployee(this);
        return this;
    }

    public Employee removeJob(Job job) {
        this.jobs.remove(job);
        job.setEmployee(null);
        return this;
    }

    public Employee getManager() {
        return this.manager;
    }

    public void setManager(Employee employee) {
        this.manager = employee;
    }

    public Employee manager(Employee employee) {
        this.setManager(employee);
        return this;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee department(Department department) {
        this.setDepartment(department);
        return this;
    }

    public JobHistory getJobHistory() {
        return this.jobHistory;
    }

    public void setJobHistory(JobHistory jobHistory) {
        if (this.jobHistory != null) {
            this.jobHistory.setEmployee(null);
        }
        if (jobHistory != null) {
            jobHistory.setEmployee(this);
        }
        this.jobHistory = jobHistory;
    }

    public Employee jobHistory(JobHistory jobHistory) {
        this.setJobHistory(jobHistory);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return getId() != null && getId().equals(((Employee) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", identityCard='" + getIdentityCard() + "'" +
            ", dateInspiration='" + getDateInspiration() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", uploadIdentityCard='" + getUploadIdentityCard() + "'" +
            ", uploadIdentityCardContentType='" + getUploadIdentityCardContentType() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", typeEmployed='" + getTypeEmployed() + "'" +
            ", cityAgency='" + getCityAgency() + "'" +
            ", residenceCity='" + getResidenceCity() + "'" +
            ", address='" + getAddress() + "'" +
            ", socialSecurityNumber='" + getSocialSecurityNumber() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", birthPlace='" + getBirthPlace() + "'" +
            ", entryDate='" + getEntryDate() + "'" +
            ", releaseDate='" + getReleaseDate() + "'" +
            ", workstation='" + getWorkstation() + "'" +
            ", descriptionWorkstation='" + getDescriptionWorkstation() + "'" +
            ", level='" + getLevel() + "'" +
            ", coefficient=" + getCoefficient() +
            ", employedManager='" + getEmployedManager() + "'" +
            ", numberHours='" + getNumberHours() + "'" +
            ", averageHourlyCost='" + getAverageHourlyCost() + "'" +
            ", monthlyGrossAmount=" + getMonthlyGrossAmount() +
            ", commissionAmount=" + getCommissionAmount() +
            ", contractType='" + getContractType() + "'" +
            ", salaryType='" + getSalaryType() + "'" +
            ", hireDate='" + getHireDate() + "'" +
            "}";
    }
}
