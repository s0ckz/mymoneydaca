package mymoney.model.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Operations")
public class Operation implements Serializable {
	
	private static final long serialVersionUID = -907936723747353376L;

	private long id;
	
	private long accId;
	
	private String type;
	
	private String way;
	
	private double amount;
	
	public Operation() {
		
	}
	
	public Operation(long accId, String type, String way, double amount) {
		this.accId = accId;
		this.type = type;
		this.way = way;
		this.amount = amount;
	}

	@Id
	@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "accId")
	public long getAccId() {
		return accId;
	}

	public void setAccId(long accId) {
		this.accId = accId;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "way")
	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	@Column(name = "amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String toString() {
		return "id: " + getId() + ", accId: " + getAccId() + ", " + getType() + ", way: " + getWay() + ", amount: " + getAmount();
	}
	
}
