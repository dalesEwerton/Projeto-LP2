package estadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import estadias.Estadia;
import exceptions.QuartoException;
import exceptions.SistemaException;
import quarto.Quarto;

public class testeEstadia {

	private static final String FIM_DE_LINHA = System.lineSeparator();
	
	Estadia estadia1;
	Estadia estadia2;
	Estadia estadia3;

	@Before
	public void setUp() throws Exception {
		estadia1 = new Estadia(10, "263B", "Simples");
		estadia2 = new Estadia(20, "092A", "Luxo");
		estadia3 = new Estadia(30, "505C", "Presidencial");
	}

	@Test
	public void testQtdDeDias() {
		assertEquals(estadia1.getQtdDias(), 10);
		assertEquals(estadia2.getQtdDias(), 20);
		assertEquals(estadia3.getQtdDias(), 30);

	}

	@Test
	public void testQuarto() throws QuartoException {
		Quarto quarto1 = new Quarto("263B", "Simples");
		Quarto quarto2 = new Quarto("092A", "Luxo");
		Quarto quarto3 = new Quarto("505C", "Presidencial");
		
		assertEquals(estadia1.getQuarto(), quarto1);
		assertEquals(estadia2.getQuarto(), quarto2);
		assertEquals(estadia3.getQuarto(), quarto3);

	}

	@Test
	public void testPrecoEstadia() {
		assertEquals(estadia1.precoEstadia(), 1000.0, 0.5);
		assertEquals(estadia2.precoEstadia(), 5000.0, 0.5);
		assertEquals(estadia3.precoEstadia(), 13500.0, 0.5);

	}

	@Test
	public void testEquals() throws SistemaException{
		Estadia estadia4 = new Estadia(10, "263B", "Simples");
		Estadia estadia5 = new Estadia(10, "9A", "Simples");
		
		assertTrue(estadia4.equals(estadia1));
		assertFalse(estadia5.equals(estadia1));
		
	}
	
	@Test
	public void testToString(){
		assertEquals(estadia1.toString(), "Estadia de 10 dias com valor de: R$ 1000.0" + FIM_DE_LINHA
				+ "Quarto SIMPLES: 263B" + FIM_DE_LINHA + "Preco da diaria: R$ 100.0" + FIM_DE_LINHA);		
		
		assertEquals(estadia2.toString(), "Estadia de 20 dias com valor de: R$ 5000.0" + FIM_DE_LINHA
				+ "Quarto LUXO: 092A" + FIM_DE_LINHA + "Preco da diaria: R$ 250.0" + FIM_DE_LINHA);		
	
		assertEquals(estadia3.toString(), "Estadia de 30 dias com valor de: R$ 13500.0" + FIM_DE_LINHA
				+ "Quarto PRESIDENCIAL: 505C" + FIM_DE_LINHA + "Preco da diaria: R$ 450.0" + FIM_DE_LINHA);	
	
	}

}

