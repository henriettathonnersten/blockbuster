package qa.thonnersten.henrietta.database.hello.blockbusterDatabaseApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.thonnersten.henrietta.database.hello.blockbusterDatabaseApp.model.BlockbusterDvd;
import qa.thonnersten.henrietta.database.hello.blockbusterDatabaseApp.model.BlockbusterUser;
import qa.thonnersten.henrietta.database.hello.blockbusterDatabaseApp.repository.BlockbusterDvdRepository;
import qa.thonnersten.henrietta.database.hello.blockbusterDatabaseApp.repository.BlockbusterUserRepository;
import qa.thonnersten.henrietta.database.hello.blockbusterDatabaseApp.exception.ResourceNotFoundException;
import qa.thonnersten.henrietta.database.hello.blockbusterDatabaseApp.exception.ResourceNotRentedException;
import qa.thonnersten.henrietta.database.hello.blockbusterDatabaseApp.exception.ResourceRentedException;

@RestController
@RequestMapping("/blockbuster")
public class BlockbusterController {

	@Autowired
	BlockbusterDvdRepository dvdRepo;
	
	@Autowired
	BlockbusterUserRepository userRepo;
	
	//addDVD
	@PostMapping("/dvd/add")
	public BlockbusterDvd addDvd(@Valid @RequestBody BlockbusterDvd dvd) {
		return dvdRepo.save(dvd);
	}
	
	//updateDVD
	@PutMapping("/dvd/{id}")
	public BlockbusterDvd updateDvdInfo(@PathVariable(value = "id") Long dvdID, @Valid @RequestBody BlockbusterDvd dvdInfo) {
		BlockbusterDvd selectedDvd = dvdRepo.findById(dvdID).orElseThrow(()-> new ResourceNotFoundException("dvd", dvdID));
		
		selectedDvd.setTitle(dvdInfo.getTitle());

		BlockbusterDvd updatedDvd = dvdRepo.save(selectedDvd);
		
		return updatedDvd;		
	}
	
	//removeDVD
	@DeleteMapping("dvd/{id}")
	public ResponseEntity<?> deleteDvd(@PathVariable(value = "id") Long dvdID) {
		BlockbusterDvd selectedDvd = dvdRepo.findById(dvdID).orElseThrow(()-> new ResourceNotFoundException("dvd", dvdID));
		
		dvdRepo.delete(selectedDvd);
		return ResponseEntity.ok().build();
	}
	
	//displayDVD
	@GetMapping("/dvd/{id}")
	public BlockbusterDvd displayDvd(@PathVariable(value = "id") Long dvdID) {
		return dvdRepo.findById(dvdID).orElseThrow(()-> new ResourceNotFoundException("dvd", dvdID));
	}
	
	//displayAllDVDs
	@GetMapping("/dvd")
	public List<BlockbusterDvd> displayAllDvds() {
		return dvdRepo.findAll(); 
	}
	
	//addUser
	@PostMapping("/user/add")
	public BlockbusterUser addUser(@Valid @RequestBody BlockbusterUser user) {
		return userRepo.save(user);
	}
	
	//updateUser
	@PutMapping("/user/{id}")
	public BlockbusterUser updateUser(@PathVariable(value = "id") Long userID, @Valid @RequestBody BlockbusterUser userInfo) {
		BlockbusterUser selectedUser = userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("user", userID));
		
		selectedUser.setName(userInfo.getName());
		selectedUser.setAddress(userInfo.getAddress());
		selectedUser.setCardDetails(userInfo.getCardDetails());
		
		BlockbusterUser updatedUser = userRepo.save(selectedUser);
		
		return updatedUser;
	}
	
	//removeUser
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userID) {
		BlockbusterUser selectedUser = userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("user", userID));
		
		userRepo.delete(selectedUser);
		return ResponseEntity.ok().build();
	}
	
	//displayUserInfo
	@GetMapping("/user/{id}")
	public BlockbusterUser displayUser(@PathVariable(value = "id") Long userID) {
		return userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("user", userID));
	}
	
	//displayAllUsers
	@GetMapping("/user")
	public List<BlockbusterUser> displayAllUsers() {
		return userRepo.findAll();
	}
	
	//rentDVD  
	@PutMapping("/dvd/rent/{id}")
	public BlockbusterDvd rentDvd(@PathVariable(value = "id") Long dvdID, @Valid @RequestBody BlockbusterDvd dvdInfo) {
		BlockbusterDvd selectedDvd = dvdRepo.findById(dvdID).orElseThrow(()-> new ResourceNotFoundException("dvd", dvdID));
	
		if (selectedDvd.getRentalDate() == null) {
			selectedDvd.setRentalDate(dvdInfo.getRentalDate());
			selectedDvd.setRenter(dvdInfo.getRenter());
			//Add to composite table?
		}
		else
			throw new ResourceRentedException(selectedDvd.getTitle());
		
		BlockbusterDvd rentedDvd = dvdRepo.save(selectedDvd);
		
		return rentedDvd;	
	}
	
	//returnDVD 
	@PutMapping("/dvd/return/{id}")
	public BlockbusterDvd returnDvd(@PathVariable(value = "id") Long dvdID, @Valid @RequestBody BlockbusterDvd dvdInfo) {
		BlockbusterDvd selectedDvd = dvdRepo.findById(dvdID).orElseThrow(()-> new ResourceNotFoundException("dvd", dvdID));
	
		if (selectedDvd.getRentalDate() != null) {
			selectedDvd.setRentalDate(null);
			selectedDvd.setRenter(null);
			// add return date to composite table  
		}
		else
			throw new ResourceNotRentedException(selectedDvd.getTitle());
		
		BlockbusterDvd rentedDvd = dvdRepo.save(selectedDvd);
		
		return rentedDvd;	
	}
}
