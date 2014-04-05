package com.iox.lms.model.common;

import java.io.Serializable;
import javax.persistence.Column;
import org.hibernate.validator.constraints.NotBlank;

import com.iox.common.validation.Unique;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "STATE")
public class State  extends ReferenceEntityData  implements Serializable
{
	 /**
     * SerialVersionUID
     *
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Description
     *
     */
    private String description;
    /**
     * Code
     *
     */
    private String code;
    /**
     * Country
     *
     */
    private Country country;
    
    /**
     *
     * returns Description
     *
     * @return a String
     */
    @Column(name = "DESCRIPTION", nullable = false, length = 100)
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
     * returns Code
     *
     * @return a String
     */
    @Column(name = "CODE", nullable = false, length = 10)
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
     * returns Country
     *
     * @return a CountryData
     */
    @JoinColumn(name="CNTRY_ID", nullable=false)
    @ManyToOne
    @NotNull
    public Country getCountry()
    {
        return country;
    }

    /**
     * Set the property country
     *
     * @param value -country
     *
     */
    public void setCountry(final Country value)
    {
        this.country = value;
    }

}
