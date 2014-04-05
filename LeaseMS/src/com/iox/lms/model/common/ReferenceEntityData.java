package com.iox.lms.model.common;

import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

import com.iox.common.util.Util;


@MappedSuperclass
public class ReferenceEntityData 
{

	 /**
     * Data identifier (Usually holds Primary Key)
     */
    private Long id = Long.MIN_VALUE;
    /**
     * Entity Status Code
     */
    private String status;
    /**
     * User Login Id
     */
    private String loginId;
    /**
     * Entity Status Description
     */
    private String statusDesc;
    
    /**
     * Entity Creation TimeStamp
     */
    private Timestamp creationTimeStamp;

    /**
     * Entity Creation date
     */
    private Date createdDate;
    
    /**
     * Version No
     */
    private Integer versionNo;

    /**
     * @return the id
     */
	@Id
	@NotNull
    @GeneratedValue  
    public Long getId()
    {
        return id;
    }

    /**
     * @param value the id to set
     */
    public void setId(final Long value)
    {
        this.id = value;
    }

    /**
     * @return the status
     */
    @Column(name="status", length=1, nullable=false)
    @NotBlank
    @NotNull
    public String getStatus()
    {
        return status;
    }

    /**
     * @param value the status to set
     */
    public void setStatus(final String value)
    {
        this.status = value;
    }

    /**
     * @return the loginId
     */
    @Column(name="created_by", length=100, nullable=false)
    //@JSON(serialize = false)
    public String getLoginId()
    {
        return loginId;
    }

    /**
     * @param value the loginId to set
     */
    public void setLoginId(final String value)
    {
        this.loginId = value;
    }

    /**
     * @return the statusDesc
     */
    @Transient
    public String getStatusDesc()
    {
        return statusDesc;
    }

    /**
     * @param value the statusDesc to set
     */
    public void setStatusDesc(final String value)
    {
        this.statusDesc = value;
    }

    /**
     * @return the creationTimeStamp
     */
    
    @Column(name="sys_create_ts", nullable=false)
    public Timestamp getCreationTimeStamp()
    {
        return creationTimeStamp;
    }

    /**
     * @param value the creationTimeStamp to set
     */
    public void setCreationTimeStamp(final Timestamp value)
    {
        this.creationTimeStamp = value;
    }

    /**
     * @return the createdDate
     */
    
    @Column(name="created_date",nullable=false)
    //@JSON(serialize = false)
    public Date getCreatedDate()
    {
        return createdDate;
    }

    /**
     * @param value the createdDate to set
     */
    public void setCreatedDate(final Date value)
    {
        this.createdDate = value;
    }
    
    /**
     * This method returns java.sql.TimeStamp as String
     * @return String strCreationTimeStamp
     */
    @Transient
   // @JSON(serialize = false)
    private String getCreationTimeStampAsString()
    {
        String strCreationTimeStamp = null;

        if (getCreationTimeStamp() != null)
        {
            strCreationTimeStamp = Util.getTimestampAsString(getCreationTimeStamp());
        }
        return strCreationTimeStamp;
    }
    
    /**
     * Returns business createDate in String format
     * @return String strCreateDate
     */
    @Transient
    //@JSON(serialize = false)
    private String getCreateDateAsString()
    {
        String strCreateDate = null;

        if (getCreatedDate() != null)
        {
            strCreateDate = getCreatedDate().toString();
        }
        return strCreateDate;
    }
    
    /**
     * Checks for equality of two Value objects.
     * @param obj
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    @Override
    public boolean equals(final Object obj)
    {
        if (obj instanceof ReferenceEntityData)
        {
            ReferenceEntityData val = (ReferenceEntityData) obj;
            Long result = val.getEntityId() * val.id;
            Long result2 = this.getEntityId() * this.id;            
            return result.equals(result2);
        }
        return false;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        Long pk = this.getEntityId() * this.getId();
        return pk.hashCode();
    }
    
    /**
     * This method returns a Unique entity identifier
     * @return 
     */
    @Transient
    @NotNull
    //@JSON(serialize = false)
    public Long getEntityId()
    {
        return new Long(1);
    }

    /**
     * @return the versionNo
     */
    @Version
    @Column(name="version_no", nullable=false)
    //@JSON(serialize = false)
    public Integer getVersionNo()
    {
        return versionNo;
    }

    /**
     * @param value the versionNo to set
     */
    public void setVersionNo(final Integer value)
    {
        this.versionNo = value;
    } 
}
