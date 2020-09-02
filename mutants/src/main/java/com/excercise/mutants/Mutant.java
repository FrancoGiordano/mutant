package com.excercise.mutants;

public class Mutant {
	
	public Mutant()
	{
		
	}
	
	public Mutant(String[] dna) {
		super();
		this.dna = dna;
	}

	String [] dna;

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}

}
