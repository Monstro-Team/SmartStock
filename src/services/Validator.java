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
	
	public static boolean validateIsNumberNotNull(String number){
		try{
			int numberInt = Integer.valueOf(number).intValue();
		}catch(NumberFormatException ex){
			return false;
		}
		
		return true;
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
			result.append("<br>Digite a descrição corretamente.");
		}
		if(!validateStringNotEmpty(location)){
			result.append("<br>Digite a localização corretamente.");
		}
		if(!validateIsNumberIntegerPositiveNotNull(quantityMin)){
			result.append("<br>A quantidade mínima tem que ser um número e maior que zero.");
		}
		return result.toString();
	}
	public static String validadeIsStockCorrect(String supplier, String quantity, 
			String price,boolean stockModified){
		StringBuffer result = new StringBuffer();
		if(stockModified){
			result.append("<br>O estoque já foi transferido(parte ou todo), logo não pode ser editado.");
		}
		
		if(!validateStringNotEmpty(supplier)){
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
	public static String validadeIsProviderCorrect(String company, String salesman, 
			String salesmanPhone){
		StringBuffer result = new StringBuffer();
		
		if(!validateStringNotEmpty(company)){
			result.append("<br>Digite o nome empresa corretamente.");
		}
		if(!validateIsNumberIntegerPositiveNotNull(salesmanPhone)){
			result.append("<br>O telefone do vendedor está incorreto.");
		}
		if(!validateStringNotEmpty(salesman)){
			result.append("<br>ODigite o nome do vendedor corretamente.");
		}
		return result.toString();
	}
	public static String validadeIsTransactionCorrect(String stock_id, String quantityMoved, 
			String transactionType, int stockQuantity){
		StringBuffer result = new StringBuffer();
		
		if(!validateIsNumberNotNull(stock_id)){
			result.append("<br>Selecione o estoque corretamente.");
		}
		if(!validateIsNumberIntegerPositiveNotNull(quantityMoved)){
			result.append("<br>A quantidade transferida tem que ser maior do que zero.");
		}else{
			if( stockQuantity < Integer.valueOf(quantityMoved).intValue()){
				result.append("<br>A quantidade movida não pode ser maior que o a do estoque.");
			}
		}
		if(!validateIsNumberNotNull(transactionType)){
			result.append("<br>Selecione o tipo da tranferencia.");
		}
		return result.toString();
	}
}
