package com.iox.common.enumerations;

public enum EmploymentStatusType  implements BaseEnum
{
	NEW("New"), SUSPENDED("Suspended"), DEAD("Dead"), ACTIVE("Active"), 
    RESIGNED("Resigned"), DISMISSED("Dismissed"), RETIRED("Retired"), RETRENCHED("Retrenched"), REJECTED("Rejected"), DISENGAGED("Disengaged");    
    
    /**
     * EmployeeStatusType Constructor
     * @param description  - Displayed description of a EmployeeStatusType
     */
    EmploymentStatusType(final String description)
    {
        this.description = description;
    }
    /**
     * StudentStatusType Description
     */
    private String description;

    /**
     * @return the description
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Returns the name of this enum constant
     * @return - the name of this enum constant
     */
    @Override
    public String getName()
    {
        return name();
    }
}
