package qa.thonnersten.henrietta.database.hello.blockbusterDatabaseApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private String type;
	private Object id;
	
	public ResourceNotFoundException (String type, Object id) {
		super(String.format("No %s with the id %s was found", type, id));
		this.type = type;
		this.id = id; 
	}

	public String getType() {
		return type;
	}	
	
	public Object getId() {
		return id;
	}	
	
}
