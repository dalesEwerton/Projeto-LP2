package hospedes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/** Classe Padrao representa um tipo de cartao fidelidade, que no caso eh o tipo padrao. 
 */

public class Padrao implements TipoCartaoFidelidadeIF, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final double RECOMPENSA_PADRAO = 0.1;
	private final double VALOR_PONTO_DE_FIDELIDADE = 0.10;
	
	/**
	 *  Calcula recompensa, a partir de um gasto, e retorna seu valor inteiro
	 */
	@Override
	public int calculaRecompensa(double gasto) {
		return (int)(gasto * RECOMPENSA_PADRAO);
	}
	
	/**
	 *  Calcula desconto em um gasto e retorna seu valor aproximado
	 */
	 
	@Override
	public double calculaDesconto(double gasto) {
		BigDecimal bd = new BigDecimal(gasto).setScale(2, RoundingMode.HALF_EVEN);
		return bd.doubleValue();
	}

	/**
	 * Converte pontos de fidelidade em dinheiro e retorna para o hospede
	 */
	@Override
	public double convertePontos(int qtdPontos) {
		return qtdPontos * VALOR_PONTO_DE_FIDELIDADE;
	}

}
