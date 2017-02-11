package quarto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import exceptions.QuartoException;

public class testeQuarto {

	private static final String FIM_DE_LINHA = System.lineSeparator();
	
	Quarto quarto1;
	Quarto quarto2;
	Quarto quarto3;

	@Before
	public void setUp() throws Exception {
		quarto1 = new Quarto("263B", "Simples");
		quarto2 = new Quarto("092A", "LuXo");
		quarto3 = new Quarto("505C", "Presidencial");
	}

	@Test
	public void testID() {
		assertEquals(quarto1.getID(), "263B");
		assertEquals(quarto2.getID(), "092A");
		assertEquals(quarto3.getID(), "505C");
	
	}
	
	@Test
	public void testIDException() {		
		try{
			Quarto novoQuarto = new Quarto("E3$32", "SIMPLES");

		}catch(Exception e){
			assertEquals(e.getMessage(), "ID do quarto invalido!");
		}
		
		try{
			Quarto novoQuarto = new Quarto("aa3 3", "luxo");

		}catch(Exception e){
			assertEquals(e.getMessage(), "ID do quarto invalido!");
		}
		
		try{
			Quarto novoQuarto = new Quarto("D34!", "presidencial");

		}catch(Exception e){
			assertEquals(e.getMessage(), "ID do quarto invalido!");
		}
		
	}
	
	@Test
	public void testPrecoDaDiaria() {
		assertEquals(quarto1.getPrecoDaDiaria(), 100.0, 0.5);
		assertEquals(quarto2.getPrecoDaDiaria(), 250.0, 0.5);
		assertEquals(quarto3.getPrecoDaDiaria(), 450.0, 0.5);
	
	}

	@Test
	public void testTipo(){
		assertEquals(quarto1.getTipo(), "SIMPLES");		
		assertEquals(quarto2.getTipo(), "LUXO");
		assertEquals(quarto3.getTipo(), "PRESIDENCIAL");
		
	}
	
	@Test
	public void testEquals() throws QuartoException{
		Quarto quarto4 = new Quarto("263B", "Luxo");
		Quarto quarto5 = new Quarto("092A", "Simples");
		
		assertTrue(quarto4.equals(quarto1));
		assertTrue(quarto5.equals(quarto2));
		
		assertFalse(quarto4.equals(quarto3));
		assertFalse(quarto5.equals(quarto4));
		
	}
	
	@Test
	public void testToString(){
		assertEquals(quarto1.toString(),  "Quarto SIMPLES: 263B" + FIM_DE_LINHA
					+ "Preco da diaria: R$ 100.0" + FIM_DE_LINHA);		
		
		assertEquals(quarto2.toString(), "Quarto LUXO: 092A" + FIM_DE_LINHA
				+ "Preco da diaria: R$ 250.0" + FIM_DE_LINHA);
		
		assertEquals(quarto3.toString(), "Quarto PRESIDENCIAL: 505C" + FIM_DE_LINHA
				+ "Preco da diaria: R$ 450.0" + FIM_DE_LINHA);
		
	}

}
