package services;

public class Validator {
	
	public static boolean validateStringNotEmpty(String string){
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
		if(!validateStringNotEmpty(name)){
			result.append("<br>Digite o nome do produto corretamente.");
		}
		if(!validateStringNotEmpty(description)){
			result.append("<br>Digite a descri��o corretamente.");
		}
		if(!validateStringNotEmpty(location)){
			result.append("<br>Digite a localiza��o corretamente.");
		}
		if(!validateIsNumberIntegerPositiveNotNull(quantityMin)){
			result.append("<br>A quantidade m�nima tem que ser um n�mero e maior que zero.");
		}
		return result.toString();
	}
	public static String validadeIsStockCorrect(String supplier, String quantity, 
			String price){
		StringBuffer result = new StringBuffer();
		
		if(!validateStringNotEmpty(supplier)){
			result.append("<br>Digite o fornecedor corretamente.");
		}
		if(!validateIsNumberIntegerPositiveNotNull(quantity)){
			result.append("<br>A quantidade tem que ser um n�mero e maior que zero.");
		}
		if(!validateIsNumberFloatPositiveNotNull(price)){
			result.append("<br>O pre�o do produto tem que ser um n�mero e maior que zero.");
		}
		return result.toString();
	}
	public static String validadeIsProviderCorrect(String company, String salesman, 
			String salesmanPhone){
		StringBuffer result = new StringBuffer();
		
		if(!validateStringNotEmpty(company)){
			result.append("<br>Digite o nome empresa corretamente.");
		}
		if(!validateIsNumberIntegerPositiveNotNull(salesmanPhone)){
			result.append("<br>O telefone do vendedor est� incorreto.");
		}
		if(!validateStringNotEmpty(salesman)){
			result.append("<br>ODigite o nome do vendedor corretamente.");
		}
		return result.toString();
	}
}
