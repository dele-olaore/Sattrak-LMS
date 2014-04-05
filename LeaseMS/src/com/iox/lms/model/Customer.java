package com.iox.lms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.iox.common.enumerations.CustomerType;
import com.iox.common.enumerations.EmploymentStatusType;
import com.iox.common.enumerations.IdentificationType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.iox.common.validation.EntityIdNotNull;
import com.iox.lms.model.common.Bank;
import com.iox.lms.model.common.BaseCustomerData;
import com.iox.lms.model.common.Sector;

@Entity
@javax.persistence.Table(name = "Customer")
public class Customer extends BaseCustomerData  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
    private Bank bankDetails1;
    
    private String accountNo1;
    
	private Bank bankDetails2;
	    
	private String accountNo2;
	    
	private Bank bankDetails3;
	    
	private String accountNo3;
    
    private java.sql.Date birthDate;

    private IdentificationType identificationType;

    private java.sql.Date hireDate;
   
    private String pictureMimeType;
 
    private String pictureFileName;

    private byte[] picture;

    private EmploymentStatusType employeeStatus;
    
    private String image;
 
    private java.sql.Date confirmationDate;
    
    private CustomerType customerType;
    
    private String employersFullname;
    
    private String employersAddress;
    
    private String employersPhone1;
    
    private String employersPhone2;
    
    private String employersEmail;
    
   private String gurantorsFullname;
    
    private String gurantorsAddress;
    
    private String gurantorsPhone1;
    
    private String gurantorsPhone2;
    
    private String gurantorsEmail;
    
    private String directorsFullname;
    
    private String directorsAddress;
    
    private String directorsPhone1;
    
    private String directorsPhone2;
    
    private String directorsEmail;
    
    private Sector customerSector;
    
	public Customer()
	{}

	@JoinColumn(name = "BANK_1", nullable = false)
	@ManyToOne
	@EntityIdNotNull
	@NotNull
	public Bank getBankDetails1() {
		return bankDetails1;
	}

	public void setBankDetails1(Bank bankDetails1) {
		this.bankDetails1 = bankDetails1;
	}

	@Column(name = "ACCOUNT_1", nullable = false, length = 10)
	@NotBlank
	public String getAccountNo1() {
		return accountNo1;
	}

	public void setAccountNo1(String accountNo1) {
		this.accountNo1 = accountNo1;
	}

	@JoinColumn(name = "BANK_2", nullable = true)
	@ManyToOne
	public Bank getBankDetails2() {
		return bankDetails2;
	}

	public void setBankDetails2(Bank bankDetails2) {
		this.bankDetails2 = bankDetails2;
	}

	@Column(name = "ACCOUNT_2", nullable = true, length = 10)
	public String getAccountNo2() {
		return accountNo2;
	}

	public void setAccountNo2(String accountNo2) {
		this.accountNo2 = accountNo2;
	}

	@JoinColumn(name = "BANK_3", nullable = true)
	@ManyToOne
	public Bank getBankDetails3() {
		return bankDetails3;
	}

	public void setBankDetails3(Bank bankDetails3) {
		this.bankDetails3 = bankDetails3;
	}

	@Column(name = "ACCOUNT_3", nullable = true, length = 10)
	public String getAccountNo3() {
		return accountNo3;
	}

	public void setAccountNo3(String accountNo3) {
		this.accountNo3 = accountNo3;
	}

	@Column(name = "BIRTH_DATE", nullable = false)
    @NotNull
	public java.sql.Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(java.sql.Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "IDENTIFICATION_TYPE", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
	public IdentificationType getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(IdentificationType identificationType) {
		this.identificationType = identificationType;
	}

	@Column(name = "HIRE_DATE", nullable = false)
    @NotNull
	public java.sql.Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(java.sql.Date hireDate) {
		this.hireDate = hireDate;
	}

	@Column(name = "PICTURE_MIME_TYPE", nullable = true, length = 100)
	public String getPictureMimeType() {
		return pictureMimeType;
	}

	public void setPictureMimeType(String pictureMimeType) {
		this.pictureMimeType = pictureMimeType;
	}

	@Column(name = "PICTURE_FILE_NAME", nullable = true, length = 100)
	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	@Column(name = "PICTURE", nullable = true)
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@Column(name = "EMPLOYMENT_STATUS", nullable = true)
    @NotNull
    @Enumerated(EnumType.STRING)
	public EmploymentStatusType getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmploymentStatusType employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	@Transient
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "CONFIRMATION_DATE", nullable = true)
	public java.sql.Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(java.sql.Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	@Column(name = "CUSTOMER_TYPE", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	@Column(name = "EMPLOYERS_FULL_NAME", nullable = true, length = 100)
	public String getEmployersFullname() {
		return employersFullname;
	}

	public void setEmployersFullname(String employersFullname) {
		this.employersFullname = employersFullname;
	}

	@Column(name = "EMPLOYERS_ADDRESS", nullable = true, length = 100)
	public String getEmployersAddress() {
		return employersAddress;
	}

	public void setEmployersAddress(String employersAddress) {
		this.employersAddress = employersAddress;
	}

	@Column(name = "EMPLOYERS_PHONE1", nullable = true, length = 16)
	public String getEmployersPhone1() {
		return employersPhone1;
	}

	public void setEmployersPhone1(String employersPhone1) {
		this.employersPhone1 = employersPhone1;
	}

	@Column(name = "EMPLOYERS_PHONE2", nullable = true, length = 16)
	public String getEmployersPhone2() {
		return employersPhone2;
	}

	public void setEmployersPhone2(String employersPhone2) {
		this.employersPhone2 = employersPhone2;
	}

	@Column(name = "EMPLOYERS_EMAIL", nullable = true, length = 50)
    @Email
	public String getEmployersEmail() {
		return employersEmail;
	}

	public void setEmployersEmail(String employersEmail) {
		this.employersEmail = employersEmail;
	}

	@Column(name = "GURANTORS_FULL_NAME", nullable = true, length = 100)
	public String getGurantorsFullname() {
		return gurantorsFullname;
	}

	public void setGurantorsFullname(String gurantorsFullname) {
		this.gurantorsFullname = gurantorsFullname;
	}

	@Column(name = "GURANTORS_ADDRESS", nullable = true, length = 100)
	public String getGurantorsAddress() {
		return gurantorsAddress;
	}

	public void setGurantorsAddress(String gurantorsAddress) {
		this.gurantorsAddress = gurantorsAddress;
	}

	@Column(name = "GURANTORS_PHONE1", nullable = true, length = 16)
	public String getGurantorsPhone1() {
		return gurantorsPhone1;
	}

	public void setGurantorsPhone1(String gurantorsPhone1) {
		this.gurantorsPhone1 = gurantorsPhone1;
	}

	@Column(name = "GURANTORS_PHONE2", nullable = true, length = 16)
	public String getGurantorsPhone2() {
		return gurantorsPhone2;
	}

	public void setGurantorsPhone2(String gurantorsPhone2) {
		this.gurantorsPhone2 = gurantorsPhone2;
	}

	@Column(name = "EMAIL_ADDRESS", nullable = true, length = 50)
    @Email
	public String getGurantorsEmail() {
		return gurantorsEmail;
	}

	public void setGurantorsEmail(String gurantorsEmail) {
		this.gurantorsEmail = gurantorsEmail;
	}

	@Column(name = "DIRECTORS_FULL_NAME", nullable = true, length = 100)
	public String getDirectorsFullname() {
		return directorsFullname;
	}

	public void setDirectorsFullname(String directorsFullname) {
		this.directorsFullname = directorsFullname;
	}

	@Column(name = "DIRECTORS_ADDRESS", nullable = true, length = 100)
	public String getDirectorsAddress() {
		return directorsAddress;
	}

	public void setDirectorsAddress(String directorsAddress) {
		this.directorsAddress = directorsAddress;
	}

	@Column(name = "DIRECTORS_PHONE1", nullable = true, length = 16)
	public String getDirectorsPhone1() {
		return directorsPhone1;
	}

	public void setDirectorsPhone1(String directorsPhone1) {
		this.directorsPhone1 = directorsPhone1;
	}

	@Column(name = "DIRECTORS_PHONE2", nullable = true, length = 16)
	public String getDirectorsPhone2() {
		return directorsPhone2;
	}

	public void setDirectorsPhone2(String directorsPhone2) {
		this.directorsPhone2 = directorsPhone2;
	}

	@Column(name = "DIRECTORS_EMAIL_ADDRESS", nullable = true, length = 50)
    @Email
	public String getDirectorsEmail() {
		return directorsEmail;
	}

	public void setDirectorsEmail(String directorsEmail) {
		this.directorsEmail = directorsEmail;
	}

	@JoinColumn(name = "SECTOR", nullable = true)
	@ManyToOne
	public Sector getCustomerSector() {
		return customerSector;
	}

	public void setCustomerSector(Sector customerSector) {
		this.customerSector = customerSector;
	}
	
}
