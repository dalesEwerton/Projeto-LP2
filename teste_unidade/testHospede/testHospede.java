package testHospede;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import exceptions.SistemaException;
import hospedes.Hospede;

public class testHospede {

	Hospede chavez;
	Hospede seuBarriga;
	Hospede kiko;
	
	@Before
	public void setUp() throws Exception {
		chavez = new Hospede("Chavez", "chavez@vila.com", "12/12/84");
		seuBarriga = new Hospede("Seu Barriga", "seupanca@vila.com", "03/03/1955");
		kiko = new Hospede("Kiko", "tesouro@vila.com.br", "29/02/1985");
	}
	
	@Test
	public void testHospedesValidos() throws SistemaException{
		
		assertEquals(chavez.getNome(), "Chavez");
		assertEquals(kiko.getEmail(), "tesouro@vila.com.br");
		assertEquals(seuBarriga.getDataNascimento(), "03/03/1955");
		
		assertEquals(chavez.getInfoHospede("nome"), "Chavez");
		assertEquals(kiko.getInfoHospede("email"), "tesouro@vila.com.br");
	}
	
	@Test
	public void testHospedeExceptions(){
		
		try{
			chavez.setEmail("vouBotaremailFalso");
		
		}catch(Exception e){
			assertEquals(e.getMessage(), "Email invalido");
		}
		
		try{
			chavez.getInfoHospede("Moneys");
		}catch(Exception e){
			assertEquals(e.getMessage(), "Parametro invalido para hospedes");
		}
	}

}
