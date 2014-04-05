package com.iox.lms.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "BANK")
public class Bank   extends ReferenceEntityData  implements Serializable
{
	/**
     * SerialVersionUID
     *
     */
    private static final long serialVersionUID = 1L;

	
    private String code;
   
    private String description;

    @Column(name = "CODE", nullable = false, length = 10)
    @NotBlank
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

  @Column(name = "DESCRIPTION", nullable = false, length = 100)
   @NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
}
