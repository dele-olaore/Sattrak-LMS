package com.iox.lms.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SECTOR")
public class Sector  implements Serializable
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
     *
     * returns Code
     *
     * @return a String
     */
    @Column(name = "CODE", nullable = false, length = 10)
    @NotBlank
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
}
