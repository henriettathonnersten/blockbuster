package qa.thonnersten.henrietta.database.hello.blockbusterDatabaseApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class ResourceRentedException extends RuntimeException {

	private String title;
	
	public ResourceRentedException (String title) {
		super(String.format("%s is already rented", title));
		this.title = title;
	}

	public String gettitle() {
		return title;
	}	
	
}
