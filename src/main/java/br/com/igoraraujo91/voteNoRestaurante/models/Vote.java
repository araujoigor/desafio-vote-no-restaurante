package br.com.igoraraujo91.voteNoRestaurante.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name="votes")
public class Vote {
	
	@Column(name="userName")
	public String userName;
	
	@Id
	@Column(name="userEmail")
	public String userEmail;
	
	//Should be @OneToMany, but it would make userName a unique constraint.
	//And we do not want that
	@ManyToMany
	@OrderColumn
	public Restaurant[] userVotes;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public Restaurant[] getUserVotes() {
		return userVotes;
	}
	
	public void setUserVotes(Restaurant[] userVotes) {
		this.userVotes = userVotes;
	}
	
	public Vote (){}
	
	@Override
	public String toString(){
		return this.userName + " - " + this.userEmail + " - " + this.userVotes.toString();
	}

}
