package restaurante;

import java.io.Serializable;
import java.util.List;

import exceptions.RefeicaoException;
import exceptions.SistemaException;

public class Refeicao extends Menu implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final double DESCONTO = 0.9;
	private final int TAMANHO_MIN_REFEICAO = 3;
	private final int TAMANHO_MAX_REFEICAO = 4;
			
	private List<Menu> listaPratos;
	
	public Refeicao (String nome, String descricao, List<Menu> pratos) 
			throws SistemaException{
		
		super(nome, descricao);
		
		verificaTamanhoRefeicao(pratos);
		
		this.listaPratos = pratos;
	}
	
	/**
	 * O metodo getDescricaoCompleta ira retornar a descricao da refeicao
	 * juntamente com a lista de pratos que a mesma possui
	 * @return
	 */
	public String getDescricaoCompleta(){
		
		 return this.toString();
	}
	
	/**
	 * o metodo getPrato retorna a lista de pratos que contem na refeicao
	 * @return listaPratos - String
	 */
	public List<Menu> getPratos() {
		return listaPratos;
	}

	/**
	 * o metodo setPrato tem a funcao de alterar a lista de pratos que esta contida na refeicao
	 * @param pratos
	 */
	public void setPratos(List<Menu> pratos) {
		this.listaPratos = pratos;
	}
	
	/**
	 * o metodo calculaPreco percorre a lista de pratos pegando o preco individual que cada prato,
	 * somando a um preco total e aplicando um desconto no preco final da refeicao
	 * @return precoFinal - double
	 */
	public double calculaPreco(){
		double precoTotal = 0;
		double precoFinal;
		
		for (Menu prato : listaPratos){
			precoTotal += prato.calculaPreco();
		}
		
		precoFinal = precoTotal * DESCONTO;
		return precoFinal;
	}
	
	/**
	 * o metodo verificaTamanhoRefeicao ira verificar a quantidade de pratos em
	 * uma refeicao
	 * 
	 * @param pratos
	 * @throws SistemaException
	 */
	public void verificaTamanhoRefeicao(List<Menu> pratos) throws SistemaException {

		if (pratos.size() < TAMANHO_MIN_REFEICAO
				|| pratos.size() > TAMANHO_MAX_REFEICAO) {

			throw new RefeicaoException("Uma refeicao deve possuir de 3 a 4 pratos.");
		}
	}
	
	/**
	 * O metodo toString retorna a representacao em String da Refeicao
	 */
	public String toString(){
		
		String stringRefeicao = "";
		String stringFinal;
		int count = 1;
		
		for (Menu prato : listaPratos){
			
			if (count < listaPratos.size()){
				stringRefeicao += " (" + count + ") " + prato.getNome() + ",";
			
			}else{
				stringRefeicao += " (" + count + ") " + prato.getNome() + ".";
			}
			
			count ++;
		}
		
		stringFinal = String.format("%s Serao servidos:%s", super.getDescricao(), stringRefeicao);
		return stringFinal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((listaPratos == null) ? 0 : listaPratos.hashCode());
		return result;
	}
	
	/**
	 * no metodo equals comparamos a refeicao por nome, descricao e os pratos que a mesma possui
	 */
	@Override
	public boolean equals(Object outroObjeto) {
		
		if (outroObjeto instanceof Refeicao){
			Refeicao outraRefeicao = (Refeicao) outroObjeto;
			
			if (super.getNome().equalsIgnoreCase(outraRefeicao.getNome()) 
					&& this.getDescricaoCompleta().equalsIgnoreCase(outraRefeicao.getDescricaoCompleta())
					&& this.getPratos().equals(outraRefeicao.getPratos())){
				
				return true;
			} 
		}
		
		return false;
	}
}
