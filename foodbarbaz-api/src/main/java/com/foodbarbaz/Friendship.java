/*package com.foodbarbaz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

//@Entity
//@Table(name = "FRIENDSHIP")
//@IdClass(UserId.class)
public class Friendship implements Serializable {

    *//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;

	@Id
    @ManyToOne(fetch=FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(referencedColumnName = "id")
    FBBUser requester;

    @Id
    @ManyToOne(fetch=FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(referencedColumnName = "id")
    FBBUser friend;

    @Temporal(javax.persistence.TemporalType.DATE)
    Date date;
    
	public FBBUser getRequester() {
		return requester;
	}
	public void setRequester(FBBUser requester) {
		this.requester = requester;
	}
	public FBBUser getFriend() {
		return friend;
	}
	public void setFriend(FBBUser friend) {
		this.friend = friend;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}    
	
}


class UserId implements Serializable{
	
    private Long requester; //  name matches the name of FBBUser.requester
    private Long friend; //  name matches the name of Employee.branch
}*/