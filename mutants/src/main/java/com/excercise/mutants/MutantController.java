package com.excercise.mutants;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MutantController {
	
	@Autowired
	private MutantService service;
	
	@PostMapping(value="/mutant", consumes="application/json")
	public ResponseEntity<Void> esMutante(@RequestBody  Mutant mutant)
	{

		 if(service.isMutant(mutant.getDna()))
			{
				return new ResponseEntity<>(HttpStatus.OK);

				
			}else
			{
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			} 
	}
	
	

}
