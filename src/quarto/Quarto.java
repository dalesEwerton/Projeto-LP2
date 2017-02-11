package quarto;

import java.io.Serializable;

import exceptions.QuartoException;

/**
 * Classe Quarto. Cada quarto possui um ID(identificador unico de cada quarto) e um preco de diaria, que 
 * varia de acordo com o tipo de quarto: - Simples: 100; - Luxo: 250; - Presidencial: 450.
 */

public class Quarto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private final String FIM_DE_LINHA = System.lineSeparator(); 

	private String ID;
	private double precoDaDiaria;
	private String tipo;

	public Quarto(String IDQuarto, String tipo) throws QuartoException {
		this.verificaValidadeDoID(IDQuarto);
		
		this.ID = IDQuarto;	
		String tipoParaAtributo = tipo.toUpperCase();
		this.tipo = tipoParaAtributo;

		TipoDeQuarto tipoAtual = this.selecionaTipo(tipo);
		this.precoDaDiaria = tipoAtual.getValor();
	}

	/**
	 * Metodo selecionaTipo retorna o tipo do quarto(do tipo TipoDeQuarto) correspondente
	 * ao parametro passado
	 * @param String - tipo
	 * @return TipoDeQuarto - novoTipo
	 * @throws QuartoException
	 */

	public TipoDeQuarto selecionaTipo(String tipo) throws QuartoException {
		String tipoAtual = tipo.toUpperCase();

		for (TipoDeQuarto novoTipo : TipoDeQuarto.values()) {
			if(novoTipo.toString().equals(tipoAtual)) {
				return novoTipo;		
			}
		}

		throw new QuartoException("Tipo de quarto nao existente!");

	}

	public boolean verificaValidadeDoID(String ID) throws QuartoException {
		if(ID.matches("[a-zA-Z0-9]+")) {
			return true;
		}else {
			throw new QuartoException("ID do quarto invalido!");
		}
	}

	/**
	 * Metodo getID
	 * 
	 * @return String - ID
	 */

	public String getID() {
		return ID;
	}

	/**
	 * Metodo setID, substitui ID do quarto por um novo passado como parametro
	 * 
	 * @param iD - String 
	 */

	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * Metodo getPrecoDaDiaria
	 * 
	 * @return double - precoDaDiaria
	 */

	public double getPrecoDaDiaria() {
		return precoDaDiaria;
	}

	/**
	 * Metodo setPrecoDaDiaria, substitui precoDaDiaria do quarto
	 * por um novo preco passado como parametro
	 * 
	 * @param precoDaDiaria - double 
	 */

	public void setPrecoDaDiaria(double precoDaDiaria) {
		this.precoDaDiaria = precoDaDiaria;
	}

	/**
	 * Metodo getTipo
	 * 
	 * @return tipo - String
	 */

	public String getTipo() {
		return tipo;
	}

	/**
	 * Metodo setTipo, substitui tipo do quarto por um novo passado como parametro
	 * 
	 * @param tipo - String 
	 */

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Metodo hashCode do Quarto. Dois quartos sao iguais se possuirem o mesmo ID.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	/**
	 * Metodo equals do Quarto. Dois quartos sao iguais se possuirem o mesmo ID.
	 */

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Quarto){
			Quarto outroQuarto = (Quarto) obj;

			if (this.ID.equals(outroQuarto.ID)) {
				return true;
			}else {
				return false;
			}

		}else {
			return false;
		}
	}

	/**
	 * Representacao String do Quarto.
	 */

	@Override
	public String toString() {
		String strFinal = "Quarto " + this.tipo + ": " + this.ID + FIM_DE_LINHA;
		strFinal += "Preco da diaria: R$ " + this.precoDaDiaria + FIM_DE_LINHA;
		return strFinal;
	}



}
