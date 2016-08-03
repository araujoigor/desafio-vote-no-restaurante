package br.com.igoraraujo91.voteNoRestaurante.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="restaurants")
public class Restaurant {
	
	@Id
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="imgURL")
	private String imgUrl;
	
	@Column(name="votes")
	private int votes;
	
	public Restaurant(){}

	public Restaurant(String name, String address, String imgUrl, int votes) {
		this.name = name;
		this.address = address;
		this.imgUrl = imgUrl;
		this.votes = votes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votesReceived) {
		this.votes = votesReceived;
	}		

}
