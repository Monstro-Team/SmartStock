package services;

public class Validator {
	
	public static boolean validateStringEmpty(String string){
		if(string.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean validateIsNumberIntegerPositiveNotNull(String number){
		int numberInt;
		try{
			numberInt = Integer.valueOf(number).intValue();
		}catch(NumberFormatException ex){
			return false;
		}
		if(numberInt <= 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean validateIsNumberFloatPositiveNotNull(String number){
		float numberFloat;
		try{
			numberFloat = Float.valueOf(number).floatValue();
		}catch(NumberFormatException ex){
			return false;
		}
		if(numberFloat <= 0){
			return false;
		}
		else{
			return true;
		}
	}

	public static String validadeIsProductCorrect(String name, String description, 
			String location, String quantityMin){
		StringBuffer result = new StringBuffer();
		if(!validateStringEmpty(name)){
			result.append("<br>Digite o nome do produto corretamente.");
		}
		if(!validateStringEmpty(description)){
			result.append("<br>Digite a descrição corretamente.");
		}
		if(!validateStringEmpty(location)){
			result.append("<br>Digite a localização corretamente.");
		}
		if(!validateIsNumberIntegerPositiveNotNull(quantityMin)){
			result.append("<br>A quantidade mínima tem que ser um número e maior que zero.");
		}
		return result.toString();
	}
	public static String validadeIsStockCorrect(String supplier, String quantity, 
			String price){
		StringBuffer result = new StringBuffer();
		
		if(!validateStringEmpty(supplier)){
			result.append("<br>Digite o fornecedor corretamente.");
		}
		if(!validateIsNumberIntegerPositiveNotNull(quantity)){
			result.append("<br>A quantidade tem que ser um número e maior que zero.");
		}
		if(!validateIsNumberFloatPositiveNotNull(price)){
			result.append("<br>O preço do produto tem que ser um número e maior que zero.");
		}
		return result.toString();
	}
}
