package com.iox.lms.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "CURRENCY")
public class Currency   extends ReferenceEntityData  implements Serializable
{
	private static final long serialVersionUID = 1L;

	
    private String code;
  
    private String isoCode;
  
    private String description;
  
    private String symbol;
   
    private Country country;

    /**
     *
     * returns Code
     *
     * @return a String
     */
    @Column(name = "CODE", nullable = false, length = 10)
    @NotBlank
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

    /**
     *
     * returns IsoCode
     *
     * @return a String
     */
    @Column(name = "ISO_CODE", nullable = false, length = 3)
    @NotBlank
    public String getIsoCode() {
        return isoCode;
    }

    /**
     * Set the property isoCode
     *
     * @param value -isoCode
     *
     */
    public void setIsoCode(final String value) {
        this.isoCode = value;
    }

    /**
     *
     * returns Description
     *
     * @return a String
     */
    @Column(name = "DESCRIPTION", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    /**
     * Set the property description
     *
     * @param value -description
     *
     */
    public void setDescription(final String value) {
        this.description = value;
    }

    /**
     *
     * returns Symbol
     *
     * @return a String
     */
    @Column(name = "SYMBOL", nullable = true, length = 10)
    public String getSymbol() {
        return symbol;
    }

    /**
     * Set the property symbol
     *
     * @param value -symbol
     *
     */
    public void setSymbol(final String value) {
        this.symbol = value;
    }

    /**
     *
     * returns Country
     *
     * @return a Long
     */
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    @ManyToOne
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
}
