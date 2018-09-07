package qa.thonnersten.henrietta.database.hello.blockbusterDatabaseApp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
public class BlockbusterUser implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;

	@NotBlank
	private String address;
	
	private String cardNumber;	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return name;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCardDetails() {
		return cardNumber;
	}

	public void setCardDetails(String cardNumber) {
		this.cardNumber = cardNumber;
	}

}
