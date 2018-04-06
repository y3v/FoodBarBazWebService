package com.foodbarbaz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "FRIENDSHIP")
public class Friendship implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    FBBUser requester;

    @Id
    @ManyToOne
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