package com.iox.lms.model.common;

import com.iox.common.enumerations.GenderType;
import com.iox.common.enumerations.MaritalStatusType;
import com.iox.common.enumerations.TitleType;
import com.iox.common.validation.EntityIdNotNull;
import com.iox.common.validation.Unique;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@MappedSuperclass
public class BaseCustomerData  extends ReferenceEntityData
{
    private String telephone1;

    private String city;
 
    private String telephone2;
  
    private String customerNumber;
 
    private MaritalStatusType maritalStatus;

    private String addressLine1;
    
    private String addressLine2;

    private String postCode;
 
    private GenderType gender;
   
    private String emailAddress;
   
    private String lastName;
   
    private String firstName;
    
    private String middleName;
    
    private String taxPin;
    
    private String identificationNumber;
       
    private Country country;
    
    private TitleType title;
    
    @Column(name = "TELEPHONE1", nullable = false, length = 16)
    @NotNull
    public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	@JoinColumn(name = "COUNTRY_ID", nullable = false)
    @ManyToOne
    @EntityIdNotNull
    @NotNull
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "TITLE", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
	public TitleType getTitle() {
		return title;
	}

	public void setTitle(TitleType title) {
		this.title = title;
	}

    @Column(name = "CITY", nullable = false, length = 100)
    @NotBlank
    public String getCity() {
        return city;
    }

    public void setCity(final String value) {
        this.city = value;
    }

    @Column(name = "TELEPHONE2", nullable = true, length = 16)
    public String getTelephone2() {
        return telephone2;
    }

     public void setTelephone2(final String value) {
        this.telephone2 = value;
    }

    @Column(name = "CUSTOMER_NO", nullable = true, length = 20)
    @Unique
    public String getCustomerNumber() {
        return customerNumber;
    }
    
    public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
    
    @Column(name = "MARITAL_STATUS", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    public MaritalStatusType getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(final MaritalStatusType value) {
        this.maritalStatus = value;
    }

    @Column(name = "ADDRESS_LINE2", nullable = true, length = 100)
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(final String value) {
        this.addressLine2 = value;
    }

    @Column(name = "ADDRESS_LINE1", nullable = false, length = 100)
    @NotBlank
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(final String value) {
        this.addressLine1 = value;
    }

    @Column(name = "POST_CODE", nullable = true, length = 20)
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(final String value) {
        this.postCode = value;
    }

    @Column(name = "GENDER", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    public GenderType getGender() {
        return gender;
    }

    public void setGender(final GenderType value) {
        this.gender = value;
    }

    @Column(name = "EMAIL_ADDRESS", nullable = false, length = 50)
    @NotBlank
    @Email
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String value) {
        this.emailAddress = value;
    }

    @Column(name = "LAST_NAME", nullable = false, length = 30)
    @NotBlank
    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String value) {
        this.lastName = value;
    }

    @Column(name = "IDENTIFICATION_NO", nullable = false, length = 20)
    @NotBlank
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(final String value) {
        this.identificationNumber = value;
    }

    @Column(name = "FIRST_NAME", nullable = false, length = 30)
    @NotBlank
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String value) {
        this.firstName = value;
    }

    @Column(name = "MIDDLE_NAME", nullable = true, length = 30)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(final String value) {
        this.middleName = value;
    }

    @Column(name = "TAX_PIN", nullable = true, length = 20)
	public String getTaxPin() {
		return taxPin;
	}

	public void setTaxPin(String taxPin) {
		this.taxPin = taxPin;
	}
}
