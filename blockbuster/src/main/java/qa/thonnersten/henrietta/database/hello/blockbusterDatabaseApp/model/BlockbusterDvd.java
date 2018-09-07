package qa.thonnersten.henrietta.database.hello.blockbusterDatabaseApp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "rentals")	
public class BlockbusterDvd implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date rentalDate;
	
	private Long renter;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}
	
	public Long getRenter() {
		return renter;
	}

	public void setRenter(Long renter) {
		this.renter = renter;
	}

}
