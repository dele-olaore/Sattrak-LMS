package com.iox.common.enumerations;

public enum CustomerType  implements BaseEnum
{
	COPERATE("Coperate"), INDIVIDUAL("Individual"), COOPERATIVE("Cooperative");
	   /**
	     * GenderType Constructor
	     * @param description  - Displayed description of a GenderType
	     */
	CustomerType(final String description)
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
