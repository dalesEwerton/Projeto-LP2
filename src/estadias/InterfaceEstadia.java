package estadias;

import quarto.Quarto;

/**
 * Interface implementada por Estadia.
 */

public interface InterfaceEstadia {

	/**
	 * Metodo precoEstadia retorna o valor que o hospede pagara pela quantidade de dias
	 * que estiver com o quarto. 
	 * 
	 * @return double - valor da estadia
	 */
	
	public double precoEstadia();
	
	/**
	 * Metodo getQtdDias retorna a quantidade de dias da estadia
	 * 
	 * @return qtdDias - int
	 */
	
	public int getQtdDias();

	/**
	 * Metodo getQuarto retorna o quarto associado a estadia
	 * 
	 * @return quarto - Quarto
	 */
	
	public Quarto getQuarto();
	
	/**
	 * Metodo setQtdDias substitui a quantidade de dias da estadia por uma 
	 * nova quantidade passada como parametro.
	 * 
	 * @param qtdDias - int
	 */
	
	public void setQtdDias(int qtdDias);
	
	/**
	 * Metodo setQuarto substitui o quarto associado a estadia por outro 
	 * passado como parametro.
	 * 
	 * @param quarto - Quarto
	 */
	
	public void setQuarto(Quarto quarto);
	
}
