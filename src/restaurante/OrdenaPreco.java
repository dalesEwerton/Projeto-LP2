package restaurante;

import java.util.Comparator;

public class OrdenaPreco implements Comparator<Menu>{

	@Override
	public int compare(Menu pedido1, Menu pedido2) {
		
		return Double.compare(pedido1.calculaPreco(), pedido2.calculaPreco());
	}
	
}
