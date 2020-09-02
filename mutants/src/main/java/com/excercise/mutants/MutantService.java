package com.excercise.mutants;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MutantService {
	
	public boolean check(String[] aux)
	{
		String[] matchesMutants = {"AAAA","CCCC","TTTT","GGGG"};
		
		for(int i = 0; i < aux.length; i++)
		{
		
		for(int c = 0; c < matchesMutants.length; c++)
		   {
			   if( aux[i].contains(matchesMutants[c]))
			   {
				   return true;
			   }
		   }
		}
		
		return false;
	}
	
	
	public String[] transformToVertical(String[] aux)
	{
		List<String> dnaVertical = new ArrayList<>();
		
		for(int i = 0; i < aux.length; i++)
		   {
			StringBuilder buffer = new StringBuilder(aux[i].length());
			for(int c = 0; c < aux[i].length(); c++)
			{
				char character= aux[c].charAt(i);
				
				buffer.append(character);
			}
			
			
			
			dnaVertical.add(buffer.toString());
		   }
		
		return dnaVertical.toArray(new String[0]);
	}
	
	public String[] transformToDiagonalAsc(String[] aux)
	{
		List<String> dnaDiagonal = new ArrayList<>();
		int x = 0;
		
		//Consigue las diagonales de [0][0] a [0][2]
		
		for(int i = 0; i < (aux.length-i); i++)
		{
			x = i;
			StringBuilder buffer = new StringBuilder(aux.length);
			
			for(int c =0; c < (aux[i].length()-i); c++)
			{
				char diag1= aux[c].charAt(x);
				buffer.append(diag1);
				x++;
				
			}
			
			dnaDiagonal.add(buffer.toString());
		}
		
		//Consigue las diagonales de [0][0] a [2][0]
		for(int i = 0; i < (aux.length-i); i++)
		{
			x = i;
			StringBuilder buffer = new StringBuilder(aux.length);
			
			for(int c =0; c < (aux[i].length()-i); c++)
			{
				char diag1= aux[x].charAt(c);
				buffer.append(diag1);
				x++;
				
			}
			
			//Para no agregar al array strings repetidos.
			if(!dnaDiagonal.contains(buffer.toString()))
			{
				dnaDiagonal.add(buffer.toString());
			}
		}
		
		return dnaDiagonal.toArray(new String[0]);
	}
	
	public String[] transformToDiagonalDesc(String[] aux)
	{
		List<String> dnaDiagonal = new ArrayList<>();
		int count= 0;
		
		//Consigue las diagonales [5][0] a [3][0]
		for(int i = (aux.length-1); i > 0+count; i--,count++)  // Loopea el array mientras que i sea mayor a la cantidad de veces loopeado, para no pasar de limite
		{
			int x = i;
			StringBuilder buffer = new StringBuilder(aux.length);
			
			for(int c =0; c < (aux[i].length()-count); c++)
			{
				char diag1= aux[x].charAt(c);
				buffer.append(diag1);
				x--	;
				
			}
			
			dnaDiagonal.add(buffer.toString());
		}
		
		
		count=0;
		
		//Consigue las diagonales [0][5] a [0][3]
		for(int i = (aux.length-1); i > 0+count; i--,count++)  // Loopea el array mientras que i sea mayor a la cantidad de veces loopeado, para no pasar de limite
		{
			StringBuilder buffer = new StringBuilder(aux.length);
			int x=(aux.length-1); 				// Posicion de arranque (Diagonal , abajo)
			
			for(int c =0; c < (aux[i].length()-count); c++)
			{
				char diag1= aux[x].charAt(c+count); // De diagonal abajo, en el charAt 0 + cantidad de veces que loopeo, para avanzar.
				buffer.append(diag1);
				x--;
				
			}
			
			if(!dnaDiagonal.contains(buffer.toString()))
			{
				dnaDiagonal.add(buffer.toString());
			}
		}
		
		return dnaDiagonal.toArray(new String[0]);
	}
	
	@Bean
	public boolean isMutant (String[] aux)
	{
		// Llamar a chequear
		boolean horizontal=check(aux);
		
		// Llamar a Transformar a Vertical y pasarlo a Chequear
		String[] vert=transformToVertical(aux);
		boolean vertical=check(vert);
		
		// Lo mismo con Diagonales.
		String[] diagAsc = transformToDiagonalAsc(aux);
		boolean ascendente=check(diagAsc);
		
		String[] diagDesc = transformToDiagonalDesc(aux);
		boolean descendente=check(diagDesc);
		
		//Si en algun momento detecta mutante devolver true,sino devuelve false

		if(horizontal || vertical || ascendente || descendente )
		{
			return true;
		}else
		{
			return false;
		}
		
	}

}
