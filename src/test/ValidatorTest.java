package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.Validator;

public class ValidatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidateStringEmpty() {
		assertFalse(Validator.validateStringNotEmpty(""));
	}

	@Test
	public void testValidateStringNotEmpty() {
		assertTrue(Validator.validateStringNotEmpty("Test"));
	}
	
	@Test
	public void testValidateIsNumberIntegerPositiveNotNull() {
		assertTrue(Validator.validateIsNumberIntegerPositiveNotNull("10"));
	}

	@Test
	public void testValidateIsNumberIntegerPositiveNull() {
		assertFalse(Validator.validateIsNumberIntegerPositiveNotNull("A"));
	}
	
	@Test
	public void testValidateIsNumberIntegerNotPositive() {
		assertFalse(Validator.validateIsNumberIntegerPositiveNotNull("-1"));
	}

	@Test
	public void testValidateIsNumberFloatPositiveNotNull() {
		assertTrue(Validator.validateIsNumberFloatPositiveNotNull("10.0"));
	}

	@Test
	public void testValidateIsNumberFloatPositiveNull() {
		assertFalse(Validator.validateIsNumberFloatPositiveNotNull("X"));
	}

	@Test
	public void testValidateIsNumberFloatNotPositiveNot() {
		assertFalse(Validator.validateIsNumberFloatPositiveNotNull("-10.0"));
	}

	@Test
	public void testValidadeIsProductCorrect() {
		String result = Validator.validadeIsProductCorrect("Produto", "Descrição", "A1", "10"); 
		assertTrue(result.isEmpty());
	}

	@Test
	public void testValidadeIsProductCorrectInvaidName() {
		String result = Validator.validadeIsProductCorrect("", "Descrição", "A1", "10"); 
		assertTrue(result.equals("<br>Digite o nome do produto corretamente."));
	}

	@Test
	public void testValidadeIsProductCorrectInvaidDescription() {
		String result = Validator.validadeIsProductCorrect("Produto", "", "A1", "10"); 
		assertTrue(result.equals("<br>Digite a descrição corretamente."));
	}

	@Test
	public void testValidadeIsProductCorrectInvaidLocation() {
		String result = Validator.validadeIsProductCorrect("Produto", "Descrição", "", "10"); 
		assertTrue(result.equals("<br>Digite a localização corretamente."));
	}

	@Test
	public void testValidadeIsProductCorrectInvaidQuantityMin() {
		String result = Validator.validadeIsProductCorrect("Produto", "Descrição", "A1", ""); 
		assertTrue(result.equals("<br>A quantidade mínima tem que ser um número e maior que zero."));
	}

	@Test
	public void testValidadeIsStockCorrect() {
		String result = Validator.validadeIsStockCorrect("Supplier", "5", "7.0", false);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testValidadeIsStockCorrectInvalidSupplier() {
		String result = Validator.validadeIsStockCorrect("", "5", "7.0", false);
		assertTrue(result.equals("<br>Digite o fornecedor corretamente."));
	}

	@Test
	public void testValidadeIsStockCorrectInvalidQuantity() {
		String result = Validator.validadeIsStockCorrect("Supplier", "0", "7.0", false);
		assertTrue(result.equals("<br>A quantidade tem que ser um número e maior que zero."));
	}

	@Test
	public void testValidadeIsStockCorrectInvalidPrice() {
		String result = Validator.validadeIsStockCorrect("Supplier", "5", "0", false);
		assertTrue(result.equals("<br>O preço do produto tem que ser um número e maior que zero."));
	}

	@Test
	public void testValidadeIsProviderCorrect() {
		String result = Validator.validadeIsProviderCorrect("Company", "Salesman", "12345678");
		assertTrue(result.isEmpty());
	}

	@Test
	public void testValidadeIsProviderCorrectInvalidCompany() {
		String result = Validator.validadeIsProviderCorrect("", "Salesman", "12345678");
		assertTrue(result.equals("<br>Digite o nome empresa corretamente."));
	}

	@Test
	public void testValidadeIsProviderCorrectInvalidSalesman() {
		String result = Validator.validadeIsProviderCorrect("Company", "", "12345678");
		assertTrue(result.equals("<br>ODigite o nome do vendedor corretamente."));
	}

	@Test
	public void testValidadeIsProviderCorrectInvalidSalesmanPhone() {
		String result = Validator.validadeIsProviderCorrect("Company", "Salesman", "");
		assertTrue(result.equals("<br>O telefone do vendedor está incorreto."));
	}
}
