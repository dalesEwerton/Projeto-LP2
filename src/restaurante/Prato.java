package restaurante;

import java.io.Serializable;

public class Prato extends Menu implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Classe Prato. Cada prato possui um nome, um preco e uma descricao do mesmo, que 
	 * varia de acordo com o tipo de prato escolhido.
	 */	
	
	private double preco;
	
	public Prato (String nome, double preco, String descricao){
		super(nome, descricao);
		
		this.preco = preco;
	}
	
	/**
	 * O metodo calculaPreco ira retornar o preco do prato
	 */
	@Override
	public double calculaPreco() {
		return getPreco();
	}

	/**
	 * o metodo toString retorna a representacao em String da Refeicao
	 */	
	public String toString(){
		String stringPrato = String.format("%s, %.2f, %s.",
				this.getNome(), this.getPreco(), this.getDescricao());
		
		return stringPrato;
	}

	/**
	 * no metodo equals comparamos o prato por nome, descricao e preco
	 */
	@Override
	public boolean equals(Object outroObjeto) {	
		
		if (outroObjeto instanceof Prato){
			Prato outroPrato = (Prato) outroObjeto;
			
			if (this.getNome().equalsIgnoreCase(outroPrato.getNome()) 
					&& this.getDescricao().equalsIgnoreCase(outroPrato.getDescricao())
					&& this.getPreco() == outroPrato.getPreco()){
				
				return true;
			}
		}
		
		return false;
	}

	/**
	 * o metodo getPreco retorna o preco do prato
	 * @return preco - double
	 */
	public double getPreco() {
		return preco;
	}
	
	/**
	 * o metodo setPreco tem a funcao de alterar o preco do prato
	 * @param novoPreco
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
}
