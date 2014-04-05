package com.iox.lms.model.common;

import java.io.Serializable;
import javax.persistence.Column;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.iox.common.validation.Unique;

@Entity
@Table(name = "COUNTRY")
public class Country  extends ReferenceEntityData implements Serializable
{
	 /**
     * SerialVersionUID
     *
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Code
     *
     */
    private String code;
    /**
     * Description
     *
     */
    private String description;
    /**
     * CountryDialPrefix
     *
     */
    private String countryDialPrefix;
    /**
     * LocalCurrency
     *
     */
    private Currency localCurrency;
   
	
    /**
     *
     * returns Code
     *
     * @return a String
     */
    @Column(name = "CODE", nullable = false, length = 3)
    @NotBlank
    @Unique
    public String getCode()
    {
        return code;
    }

    /**
     * Set the property code
     *
     * @param value -code
     *
     */
    public void setCode(final String value)
    {
        this.code = value;
    }

    /**
     *
     * returns Description
     *
     * @return a String
     */
    @Column(name = "DESCRIPTION", nullable = false, length = 50)
    @NotBlank
    public String getDescription()
    {
        return description;
    }

    /**
     * Set the property description
     *
     * @param value -description
     *
     */
    public void setDescription(final String value)
    {
        this.description = value;
    }

    /**
     *
     * returns CountryDialPrefix
     *
     * @return a String
     */
    @Column(name = "CNTRY_DIAL_PREFIX", nullable = true, length = 10)
    public String getCountryDialPrefix()
    {
        return countryDialPrefix;
    }

    /**
     * Set the property countryDialPrefix
     *
     * @param value -countryDialPrefix
     *
     */
    public void setCountryDialPrefix(final String value)
    {
        this.countryDialPrefix = value;
    }

    /**
     *
     * returns LocalCurrency
     *
     * @return a Long
     */
    @JoinColumn(name = "LOCAL_CRNCY_ID", nullable = true)
    @ManyToOne
    public Currency getLocalCurrency()
    {
        return localCurrency;
    }

    /**
     * Set the property localCurrency
     *
     * @param value -localCurrency
     *
     */
    public void setLocalCurrency(final Currency value)
    {
        this.localCurrency = value;
    }

}
