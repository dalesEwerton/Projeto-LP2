package estadias;

import java.io.Serializable;

import exceptions.SistemaException;
import quarto.Quarto;

/**
 * Classe Estadia, representa a estadia de um hospede no hotel. Cada estadia
 * possui um quarto e uma quantidade de dias que o hospede passara no quarto.
 */

public class Estadia implements InterfaceEstadia, Serializable{
	
	private static final long serialVersionUID = 1L;

	private final String FIM_DE_LINHA = System.lineSeparator(); 

	private int qtdDias;
	private Quarto quarto;

	public Estadia(int qtdDias, String IDQuarto, String tipoQuarto) throws SistemaException {
		this.quarto = new Quarto(IDQuarto, tipoQuarto);
		this.qtdDias = qtdDias;		
	}

	/**
	 * Metodo precoEstadia retorna o valor que o hospede pagara pela quantidade de dias
	 * que estiver com o quarto. 
	 * 
	 * @return double - valor da estadia
	 */

	public double precoEstadia() {
		double precoQuarto = quarto.getPrecoDaDiaria();
		return precoQuarto * this.qtdDias;
	}

	/**
	 * Metodo getQtdDias retorna a quantidade de dias da estadia
	 * 
	 * @return qtdDias - int
	 */

	public int getQtdDias() {
		return qtdDias;
	}

	/**
	 * Metodo setQtdDias substitui a quantidade de dias da estadia por uma 
	 * nova quantidade passada como parametro.
	 * 
	 * @param qtdDias - int
	 */

	public void setQtdDias(int qtdDias) {
		this.qtdDias = qtdDias;
	}

	/**
	 * Metodo getQuarto retorna o quarto associado a estadia
	 * 
	 * @return quarto - Quarto
	 */

	public Quarto getQuarto() {
		return quarto;
	}

	/**
	 * Metodo setQuarto substitui o quarto associado a estadia por outro 
	 * passado como parametro.
	 * 
	 * @param quarto - Quarto
	 */

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	/**
	 * Metodo hashCode. Duas estadias sao iguais se possuirem o
	 * mesmo quarto e a mesma quantidade de dias.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + qtdDias;
		result = prime * result + ((quarto == null) ? 0 : quarto.hashCode());
		return result;
	}

	/**
	 * Metodo equals. Duas estadias sao iguais se possuirem o
	 * mesmo quarto e a mesma quantidade de dias.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Estadia){
			Estadia outraEstadia = (Estadia) obj;

			if (this.qtdDias == outraEstadia.qtdDias && this.quarto.equals(outraEstadia.getQuarto())) {
				return true;
			}else {
				return false;
			}

		}else {
			return false;
		}
	}

	/**
	 * Representacao String da Estadia.
	 */
	
	@Override
	public String toString() {
		String strFinal = "Estadia de " + this.qtdDias + " dias com valor de: R$ " + this.precoEstadia() + FIM_DE_LINHA;
		strFinal += this.quarto.toString();
		return strFinal;
	}
	
}
