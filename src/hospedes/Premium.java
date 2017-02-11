package hospedes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Premium implements TipoCartaoFidelidadeIF, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Classe Vip representa um tipo de cartao fidelidade, que no caso eh o tipo Vip
	 */
	private final double RECOMPENSA_PREMIUM = 0.3;
	private final double DESCONTO_PREMIUM = 0.1;
	private final double VALOR_PONTO_DE_FIDELIDADE = 0.30;
	
	/**
	 *  Calcula recompensa, a partir de um gasto, e retorna seu valor inteiro
	 */
	@Override
	public int calculaRecompensa(double gasto) {
		int recompensa = (int)(gasto * RECOMPENSA_PREMIUM);
		if(gasto > 100) {
			recompensa += ((int)(gasto / 100)) * 10;
		}
		
		return recompensa;
	}
	/**
	 *  Calcula desconto em um gasto e retorna seu valor aproximado
	 */
	public double calculaDesconto(double gasto) {
		double desconto = gasto * DESCONTO_PREMIUM;
		BigDecimal bd = new BigDecimal(desconto).setScale(2, RoundingMode.HALF_EVEN);
		
		return bd.doubleValue();
	}
	
	/**
	 * Converte pontos de fidelidade em dinheiro e retorna para o hospede
	 */
	@Override
	public double convertePontos(int qtdPontos) {
		double valorAdicional = (qtdPontos/10) * 0.20;
		return (qtdPontos * VALOR_PONTO_DE_FIDELIDADE) + valorAdicional;
	}
}
