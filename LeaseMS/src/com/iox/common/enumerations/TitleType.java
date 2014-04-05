package com.iox.common.enumerations;

public enum TitleType implements BaseEnum
{
	 MR("Mr"), MRS("Mrs"), MISS("Miss"), MS("Ms");

	    /**
	     * TitleType Constructor
	     *
	     * @param description - Displayed description of a TitleType
	     */
	    TitleType(final String description)
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
