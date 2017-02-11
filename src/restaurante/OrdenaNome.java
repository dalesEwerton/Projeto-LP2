package restaurante;

import java.util.Comparator;

public class OrdenaNome implements Comparator<Menu> {
	
	@Override
	public int compare(Menu pedido1, Menu pedido2) {
		
		return pedido1.getNome().compareTo(pedido2.getNome());
	}
}
