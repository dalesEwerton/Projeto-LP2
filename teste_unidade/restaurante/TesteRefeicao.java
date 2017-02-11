package restaurante;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.SistemaException;

public class TesteRefeicao {

	Menu joe;
	Menu burrito;
	Menu pudim;
	Menu parisiense;
	List<Menu> pratos;
	Refeicao comboInternacional;
	Refeicao comboInternacional2;

	@Before
	public void setUp() throws SistemaException{
        joe = new Prato("Joe", 3.00 , "Joe eh um prato celebre da culinaria japonesa, feito de salmao,"
        		+ " cream cheese e cebolinha.");

        burrito = new Prato ("Burrito", 25.99, "O burrito eh um celebre prato tradicional da culinaria do "
        		+ "Mexico consistindo de uma tortilla de farinha geralmente recheada com carne.");

        parisiense = new Prato ("Parisiense", 45.99, "O macarrao a parisiense eh um prato tradicional italiano,"
        		+ "feito de presunto, molho branco, frango, ervilha e champignon.");

        pudim = new Prato ("Pudim", 9.99, "Pudim eh uma sobremesa tradicional que eh feita com leite,"
        		+ "leite condensado, ovos e uma calda de caramelo.");
        
        
        pratos = new ArrayList<>();
        pratos.add(joe);
        pratos.add(burrito);
        pratos.add(parisiense);
        
        comboInternacional = new Refeicao("Combo internacional", "Esta refeicao lhe proporciona "
        		+ "os melhores pratos tradicionais de cada parte do mundo.", pratos);
	
        comboInternacional2 = new Refeicao("Combo internacional", "Esta refeicao lhe proporciona "
        		+ "os melhores pratos tradicionais de cada parte do mundo.", pratos);
	
	}
	
	@Test
	public void TestRefeicao(){

		assertEquals("Combo internacional", comboInternacional.getNome() );
		assertEquals("Esta refeicao lhe proporciona os melhores pratos tradicionais de cada parte do mundo. "
				+ "Serao servidos: (1) Joe, (2) Burrito, (3) Parisiense.", comboInternacional.getDescricaoCompleta());
		assertEquals(pratos, comboInternacional.getPratos());
		assertEquals(67.482, comboInternacional.calculaPreco(), 0.05);
		comboInternacional.setNome("Super combo internacional");
		assertEquals("Super combo internacional", comboInternacional.getNome());
		
		pratos.add(pudim);
		
		assertEquals(pratos,comboInternacional.getPratos());
		assertEquals("Esta refeicao lhe proporciona os melhores pratos tradicionais de cada parte do mundo. "
				+ "Serao servidos: (1) Joe, (2) Burrito, (3) Parisiense, (4) Pudim.", comboInternacional.getDescricaoCompleta());
		assertEquals(76.473, comboInternacional.calculaPreco(),0.5);
		assertFalse( comboInternacional.equals(comboInternacional2));
		
		comboInternacional.setDescricao("Esta refeicao lhe proporciona degustar os pratos tradicionais de cada parte do mundo.");
		
		assertEquals("Esta refeicao lhe proporciona degustar os pratos tradicionais de cada parte do mundo. "
				+ "Serao servidos: (1) Joe, (2) Burrito, (3) Parisiense, (4) Pudim.", comboInternacional.getDescricaoCompleta());
	}
}