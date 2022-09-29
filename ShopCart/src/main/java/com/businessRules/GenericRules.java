package com.businessRules;

import java.security.SecureRandom;

public class GenericRules {
	
	
	//Criacao de um Token
	public static String newToken(int idElement) {
		
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		int lengtoken = 5;
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";  //$NON-NLS-1$
		int index;

			sb.append("CartToken" + Integer.toString(idElement)); //$NON-NLS-1$
			
			for(int i = 0; i < lengtoken; i++)
			{
				index = random.nextInt(chars.length());
				
				sb.append(chars.charAt(index));
				
			}
				
			return sb.toString();
		}
	
	
	public static int incrementValue(int value) {
		
		return value+1;
	}
	
	public static int decrementValue(int value) {
		
		return value-1;
	}
	
}
