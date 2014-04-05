package com.iox.lms.model.common;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;

import org.hibernate.validator.constraints.NotBlank;

import com.iox.common.validation.EntityIdNotNull;
import com.iox.common.validation.Unique;


@Entity
@Table(name = "ORGANIZATION")
public class Organization   extends ReferenceEntityData  implements Serializable
{
	  /**
     * SerialVersionUID
     *
     */
    private static final long serialVersionUID = 1L;
    
    private String website;
  
    private String email;
 
    private String phone3;
   
    private String phone2;

    private String fax;
   
    private String phone;
    
    private String postCode;
  
    private Country country;
 
    private State state;
  
    private String city;
    
    private String addressline4;
  
    private String addressline3;
  
    private String addressline2;

    private String addressline1;
   
    private Sector sector;

    private java.sql.Date incDate;
  
    private String rc;

    private String name;

    private String code;
	
	public Organization()
	{}
	
	 /**
    *
    * returns Website
    *
    * @return a String
    */
   @Column(name = "WEBSITE", nullable = true, length = 100)
   public String getWebsite() {
       return website;
   }

   /**
    * Set the property website
    *
    * @param value -website
    *
    */
   public void setWebsite(final String value) {
       this.website = value;
   }

   /**
    *
    * returns Email
    *
    * @return a String
    */
   @Column(name = "EMAIL", nullable = false, length = 100)
   @NotBlank
   public String getEmail() {
       return email;
   }

   /**
    * Set the property email
    *
    * @param value -email
    *
    */
   public void setEmail(final String value) {
       this.email = value;
   }

   /**
    *
    * returns Phone3
    *
    * @return a String
    */
   @Column(name = "PHONE3", nullable = true, length = 16)
   public String getPhone3() {
       return phone3;
   }

   /**
    * Set the property phone3
    *
    * @param value -phone3
    *
    */
   public void setPhone3(final String value) {
       this.phone3 = value;
   }

   /**
    *
    * returns Phone2
    *
    * @return a String
    */
   @Column(name = "PHONE2", nullable = true, length = 16)
   public String getPhone2() {
       return phone2;
   }

   /**
    * Set the property phone2
    *
    * @param value -phone2
    *
    */
   public void setPhone2(final String value) {
       this.phone2 = value;
   }

   /**
    *
    * returns Fax
    *
    * @return a String
    */
   @Column(name = "FAX", nullable = true, length = 16)
   public String getFax() {
       return fax;
   }

   /**
    * Set the property fax
    *
    * @param value -fax
    *
    */
   public void setFax(final String value) {
       this.fax = value;
   }

   /**
    *
    * returns Phone
    *
    * @return a String
    */
   @Column(name = "PHONE", nullable = false, length = 16)
   @NotBlank
   public String getPhone() {
       return phone;
   }

   /**
    * Set the property phone
    *
    * @param value -phone
    *
    */
   public void setPhone(final String value) {
       this.phone = value;
   }

   /**
    *
    * returns PostCode
    *
    * @return a String
    */
   @Column(name = "POST_CODE", nullable = true, length = 10)
   public String getPostCode() {
       return postCode;
   }

   /**
    * Set the property postCode
    *
    * @param value -postCode
    *
    */
   public void setPostCode(final String value) {
       this.postCode = value;
   }

   /**
    *
    * returns Country
    *
    * @return a Long
    */
   @JoinColumn(name = "COUNTRY_ID", nullable = false)
   @ManyToOne
   @EntityIdNotNull
   @NotNull
   public Country getCountry() {
       return country;
   }

   /**
    * Set the property country
    *
    * @param value -country
    *
    */
   public void setCountry(final Country value) {
       this.country = value;
   }

   /**
    *
    * returns State
    *
    * @return a Long
    */
   @JoinColumn(name = "STATE_ID", nullable = false)
   @ManyToOne
   @EntityIdNotNull
   @NotNull
   public State getState() {
       return state;
   }

   /**
    * Set the property state
    *
    * @param value -state
    *
    */
   public void setState(final State value) {
       this.state = value;
   }

   /**
    *
    * returns City
    *
    * @return a String
    */
   @Column(name = "CITY", nullable = true, length = 50)
   public String getCity() {
       return city;
   }

   /**
    * Set the property city
    *
    * @param value -city
    *
    */
   public void setCity(final String value) {
       this.city = value;
   }

   /**
    *
    * returns Addressline4
    *
    * @return a String
    */
   @Column(name = "ADDRESSLINE4", nullable = true, length = 100)
   public String getAddressline4() {
       return addressline4;
   }

   /**
    * Set the property addressline4
    *
    * @param value -addressline4
    *
    */
   public void setAddressline4(final String value) {
       this.addressline4 = value;
   }

   /**
    *
    * returns Addressline3
    *
    * @return a String
    */
   @Column(name = "ADDRESSLINE3", nullable = true, length = 100)
   public String getAddressline3() {
       return addressline3;
   }

   /**
    * Set the property addressline3
    *
    * @param value -addressline3
    *
    */
   public void setAddressline3(final String value) {
       this.addressline3 = value;
   }

   /**
    *
    * returns Addressline2
    *
    * @return a String
    */
   @Column(name = "ADDRESSLINE2", nullable = true, length = 100)
   public String getAddressline2() {
       return addressline2;
   }

   /**
    * Set the property addressline2
    *
    * @param value -addressline2
    *
    */
   public void setAddressline2(final String value) {
       this.addressline2 = value;
   }

   /**
    *
    * returns Addressline1
    *
    * @return a String
    */
   @Column(name = "ADDRESSLINE1", nullable = false, length = 100)
   @NotBlank
   public String getAddressline1() {
       return addressline1;
   }

   /**
    * Set the property addressline1
    *
    * @param value -addressline1
    *
    */
   public void setAddressline1(final String value) {
       this.addressline1 = value;
   }

   /**
    *
    * returns Sector
    *
    * @return a Long
    */
   @JoinColumn(name = "SECTOR_ID", nullable = false)
   @ManyToOne
   @EntityIdNotNull
   @NotNull
   public Sector getSector() {
       return sector;
   }

   /**
    * Set the property sector
    *
    * @param value -sector
    *
    */
   public void setSector(final Sector value) {
       this.sector = value;
   }

   /**
    *
    * returns IncDate
    *
    * @return a java.sql.Date
    */
   @Column(name = "INC_DT", nullable = false)
   @NotNull
   public java.sql.Date getIncDate() {
       return incDate;
   }

   /**
    * Set the property incDate
    *
    * @param value -incDate
    *
    */
   public void setIncDate(final java.sql.Date value) {
       this.incDate = value;
   }

   /**
    *
    * returns Rc
    *
    * @return a String
    */
   @Column(name = "RC", nullable = false, length = 10)
   @NotBlank
   public String getRc() {
       return rc;
   }

   /**
    * Set the property rc
    *
    * @param value -rc
    *
    */
   public void setRc(final String value) {
       this.rc = value;
   }

   /**
    *
    * returns Name
    *
    * @return a String
    */
   @Column(name = "NAME", nullable = false, length = 100)
   @NotBlank
   @Unique
   public String getName() {
       return name;
   }

   /**
    * Set the property name
    *
    * @param value -name
    *
    */
   public void setName(final String value) {
       this.name = value;
   }

   /**
    *
    * returns Code
    *
    * @return a String
    */
   @Column(name = "CODE", nullable = false, length = 10)
   @NotBlank
   @Unique
   public String getCode() {
       return code;
   }

   /**
    * Set the property code
    *
    * @param value -code
    *
    */
   public void setCode(final String value) {
       this.code = value;
   }

}
