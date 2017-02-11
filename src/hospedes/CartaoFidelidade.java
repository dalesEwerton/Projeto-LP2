package hospedes;

import java.io.Serializable;

import exceptions.SistemaException;

public class CartaoFidelidade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * A classe CartaoFidelidade representa um cartao fidelidade que cada hospede possui.Esta classe contem pontos 
	 * de fidelidade e o tipo de cartao, tendo em vista que para cada tipo do cartao, o cartao fidelidade 
	 * tera um comportamento diferente
	 */
	private final int PONTOS_PREMIUM = 350;
	private final int PONTOS_VIP = 1000;
	
	private int pontosCartao;
	private TipoCartaoFidelidadeIF tipoCartao;
	
	/**
	 * 
	 */
	public CartaoFidelidade(){
		this.pontosCartao = 0;
		this.tipoCartao = new Padrao();
	}
	
	/**
	 * Adiciona os pontos de fidelidade a partir de um gasto, usando o tipo de cartao para defenir o comportamento. 
	 * Alem de atualizar o tipo de cartao caso necessario
	 * @param gasto
	 */
	public void adicionarPontos(Double gasto){
		this.pontosCartao += tipoCartao.calculaRecompensa(gasto);
		this.atualizaTipoCartao();
	}
	
	/**
	 * Aplica o desconto em um gasto, usando o tipo de cartao para defenir o comportamento
	 * @param gasto
	 * @return double
	 */
	public double aplicarDesconto(double gasto){
		return tipoCartao.calculaDesconto(gasto);
	}
	
	/**
	 * Converte os pontos de fidelidade em dinheiro e retorna esse valor ao hospede, usando o tipo de cartao 
	 * para defenir o comportamento
	 * @return double - valorSacado
	 * @throws SistemaException 
	 */
	public double convertePontos(int qtdPontos) throws SistemaException {
		if(qtdPontos > this.pontosCartao) {
			throw new SistemaException("Hospede nao possui essa quantidade de pontos no cartao!");
		}
		double valorSacado = tipoCartao.convertePontos(qtdPontos);
		this.pontosCartao -= qtdPontos;
		this.atualizaTipoCartao();
		return valorSacado;
	}
	
	/**
	 * Retorna a quantidade de pontos de fidelidade do cartao
	 */
	public int getPontosCartao() {
		return this.pontosCartao;
	}
	
	/**
	 * Atualiza o tipo do cartao dependendo da quantidade de pontos que este contem.
	 */
	public void atualizaTipoCartao() {
		if(this.pontosCartao >= PONTOS_PREMIUM){
			this.tipoCartao = new Premium();
		
		}else if(this.pontosCartao >= PONTOS_VIP){
			this.tipoCartao = new Vip();
		
		}else{
			this.tipoCartao = new Padrao();
		}
	}
}