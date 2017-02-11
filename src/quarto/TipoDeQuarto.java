package quarto;

public enum TipoDeQuarto {

	/**
	 * Constantes representando os tipos de quartos do hotel
	 */

	SIMPLES(100.0), 
	LUXO(250.0), 
	PRESIDENCIAL(450.0);

	/**
	 * Construtor privado para a criacao dos tipos de quarto
	 */

	private double valor;
	private TipoDeQuarto(double valor) {
		this.valor = valor;
	}

	/**
	 * Metodo getValor que retorna o valor do tipo de quarto especificado
	 * @return double - valor
	 */

	public double getValor() {
		return valor;
	}

}
