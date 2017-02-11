package hospedes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Vip implements TipoCartaoFidelidadeIF, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Classe Vip representa um tipo de cartao fidelidade, que no caso eh o tipo Vip
	 */
	private final double RECOMPENSA_VIP = 0.5;
	private final double DESCONTO_VIP = 0.15;
	private final double VALOR_PONTO_DE_FIDELIDADE = 0.70;
	
	/**
	 * Calcula recompensa, a partir de um gasto, e retorna seu valor inteiro
	 */
	@Override
	public int calculaRecompensa(double gasto) {
		return (int)(gasto * RECOMPENSA_VIP);
	}

	/**
	 *  Calcula desconto em um gasto e retorna seu valor aproximado
	 */
	@Override
	public double calculaDesconto(double gasto) {
		double desconto = gasto * DESCONTO_VIP;
		
		if(gasto >= 100){
			desconto -= ((int)gasto/100) * 10;
		}
		
		BigDecimal bd = new BigDecimal(desconto).setScale(2, RoundingMode.HALF_EVEN);
		return bd.doubleValue();
	}
	
	/**
	 * Converte pontos de fidelidade em dinheiro e retorna para o hospede
	 */
	@Override
	public double convertePontos(int qtdPontos) {
		double valorAdicional = (qtdPontos/10) * 0.50;
		return (qtdPontos * VALOR_PONTO_DE_FIDELIDADE) + valorAdicional;
	}

	
}
