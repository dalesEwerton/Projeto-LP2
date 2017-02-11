package hospedes;

/**
 * Interface implementada por Padrao, Vip e Premium
 */

public interface TipoCartaoFidelidadeIF {
	
	/**
	 * Calcula recompensa, a partir de um gasto, e retorna seu valor inteiro
	 * @param gasto
	 * @return int
	 */
	public int calculaRecompensa(double gasto);
	
	/**
	 * Calcula desconto em um gasto e retorna seu valor aproximado
	 * @param gasto
	 * @return double
	 */
	public double calculaDesconto(double gasto);
	
	/**
	 * Converte pontos de fidelidade em dinheiro e retorna para o hospede
	 * @param qtdPontos
	 * @return double
	 */
	public double convertePontos(int qtdPontos);
}
