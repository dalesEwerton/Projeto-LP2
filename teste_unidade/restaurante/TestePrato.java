package restaurante;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestePrato {

	Prato joe;
	Prato burrito;
	Prato parisiense;
	Prato pudim;

	@Before
	public void setUp() throws Exception {
		 joe = new Prato("Joe", 3.00 , "Joe eh um prato celebre da culinaria japonesa, feito de salmao, cream cheese e cebolinha.");
		 
		 burrito = new Prato ("Burrito", 25.99, "O burrito eh um celebre prato tradicional da culinaria do "
		 		+ "Mexico consistindo de uma tortilla de farinha geralmente recheada com carne.");
		 
		 parisiense = new Prato ("Parisiense", 45.99, "O macarrao a parisiense eh um prato tradicional italiano,"
		 		+ " feito de presunto, molho branco, frango, ervilha e champignon.");
		 
		 pudim = new Prato ("Pudim", 9.99, "Pudim eh uma sobremesa tradicional que eh feita com leite,"
		 		+ "leite condensado, ovos e uma calda de caramelo.");
	}

	@Test
	public void testeDecoy(){
		
		
	}
	
	@Test
	public void TestePratoJoe() {
				
		assertEquals("Joe", joe.getNome());
				
		assertEquals("Joe eh um prato celebre da culinaria japonesa, feito de salmao,"
			+ " cream cheese e cebolinha.", joe.getDescricao());
		assertEquals(3.00 , joe.getPreco(), 0.5);
		
		joe.setNome("Super Joe");
		assertEquals("Super Joe", joe.getNome());
		
		joe.setDescricao("Super joe eh um prato apimentado da culinaria japonesa, feito de salmao, "
				+ "crem cheese, cebolinha e wasabi.");
		assertEquals("Super Joe", joe.getNome());
		
		joe.setPreco(5.00);
		assertEquals(5.00 , joe.getPreco(), 0.5);

	}
	
	
		@Test
		public void TestePratoBurrito() {

		assertEquals("Burrito", burrito.getNome());
		assertEquals("O burrito eh um celebre prato tradicional da culinaria do Mexico"
				+ " consistindo de uma tortilla de farinha geralmente recheada com carne.", burrito.getDescricao());
		assertEquals(25.99 , burrito.getPreco(), 0.5);
		
		burrito.setNome("Super Burrito");
		assertEquals("Super Burrito", burrito.getNome());
		
		burrito.setDescricao("O Super Burrito eh um prato tradicional mexicano com molho, consistindo de uma "
				+ "tortilha de farinha geralmente recheada com carne e guacamole.");
		assertEquals("O Super Burrito eh um prato tradicional mexicano com molho, consistindo de uma "
				+ "tortilha de farinha geralmente recheada com carne e guacamole.", burrito.getDescricao());
		
		burrito.setPreco(35.00);
		assertEquals(35.00 , burrito.getPreco(), 0.5);
		
		}
		
		
		@Test
		public void TestePratoParisiense() {
		
		assertEquals("Parisiense", parisiense.getNome());
		assertEquals("O macarrao a parisiense eh um prato tradicional italiano, "
		 		+ "feito de presunto, molho branco, frango, ervilha e champignon.", parisiense.getDescricao());
		assertEquals(45.99 , parisiense.getPreco(), 0.5);
		
		parisiense.setNome("Parisiense Gratinado");
		assertEquals("Parisiense Gratinado", parisiense.getNome());

		parisiense.setDescricao("O Parisiense Gratinado eh um prato tradicional italiado, feito de presunto, "
				+ "molho branco, frango, ervilha e champignon, gratinado com queijo.");
		assertEquals("O Parisiense Gratinado eh um prato tradicional italiado, feito de presunto, "
				+ "molho branco, frango, ervilha e champignon, gratinado com queijo.", parisiense.getDescricao());
		
		parisiense.setPreco(52.00);
		assertEquals(52.00, parisiense.getPreco(), 0.5);
		}
		
		
		@Test
		public void TestePratoPudim() {

		assertEquals("Pudim", pudim.getNome());
		assertEquals("Pudim eh uma sobremesa tradicional que eh feita com leite,"
		 		+ "leite condensado, ovos e uma calda de caramelo.", pudim.getDescricao());
		assertEquals(9.99 , pudim.getPreco(), 0.5);
		
		pudim.setNome("Pudim de Chocolate");
		assertEquals("Pudim de Chocolate", pudim.getNome());

		pudim.setDescricao("Pudim de chocolate eh uma sobremesa tradicional que eh feita com leite, "
		 		+ "leite condensado, chocolate em po, ovos e uma calda de caramelo.");
		assertEquals("Pudim de chocolate eh uma sobremesa tradicional que eh feita com leite, "
		 		+ "leite condensado, chocolate em po, ovos e uma calda de caramelo.", pudim.getDescricao());
		
		pudim.setPreco(12.99);
		assertEquals(12.99 , pudim.getPreco(), 0.5);
				
	}
}