package com.iox.common.enumerations;

public enum IdentificationType implements BaseEnum
{
	 NATIONAL_ID("National ID"), INT_PASS("International Passport"), DRIVERS_LIC("Driver's Licence"), VOTERS_CARD("Voter's Card");

	    /**
	     * IdentificationType Constructor
	     *
	     * @param description - Displayed description of a IdentificationType
	     */
	    IdentificationType(final String description)
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
	     *
	     * @return - the name of this enum constant
	     */
	    @Override
	    public String getName()
	    {
	        return name();
	    }
}
