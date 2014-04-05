package com.iox.lms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.iox.lms.model.common.ReferenceEntityData;

@Entity
@javax.persistence.Table(name = "User")
public class User  extends ReferenceEntityData   implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String password;
	
	private String lastname;
	
	private String firstname;
	
	private String pictureMimeType;
	 
    private String pictureFileName;

    private byte[] picture;

    @Column(name = "USERNAME", nullable = false, length = 30)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", nullable = false, length = 30)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "LASTNAME", nullable = false, length = 50)
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "FIRSTNAME", nullable = false, length = 50)
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "PICTURE_MIME_TYPE", nullable = true, length = 30)
	public String getPictureMimeType() {
		return pictureMimeType;
	}

	public void setPictureMimeType(String pictureMimeType) {
		this.pictureMimeType = pictureMimeType;
	}

	@Column(name = "PICTURE_FILE_NAME", nullable = true, length = 100)
	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	@Column(name = "PICTURE", nullable = true)
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
        
}
