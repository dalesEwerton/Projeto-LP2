package hospedes;

import exceptions.HospedeException;
import exceptions.SistemaException;

public interface HospedeIF {
	
	/**
 	 * Retorna a informacao de um parametro especifico de hospede
	 * @param atributo
	 * @return 
	 * @throws SistemaException
	 */
	public String getInfoHospede(String atributo)throws SistemaException;
	
	/**
	 * O metodo getNome ira retorna o nome do hospede
	 * @return
	 */
	public String getNome();
	
	/**
	 * O metodo getEmail ira retorna o email do hospede
	 * @return
	 */
	public String getEmail();
	
	/**
	 * O metodo getDataNascimento retorna a data de nascimento do hospede
	 * @return
	 */
	public String getDataNascimento();
	
	/**
	 * O metedo setNome ira redefine o nome do hospede
	 * @param nome
	 */
	public void setNome(String nome);
	
	/**
	 * O metodo setEmail ira redefine o email do hospede
	 * @param email
	 * @throws HospedeException
	 */
	public void setEmail(String email) throws HospedeException;
	
	/**
	 * O metodo setDataNascimento redefine a data de nascimento do hospede
	 * @param dataNascimento
	 */
	public void setDataNascimento(String dataNascimento);
	
	/**
	 * O metodo adicionaPonto ira adicionar pontos ao cartao do Hospede
	 * de acordo com o gasto recebido como parametro
	 * @param gasto
	 */
	public void adicionarPontos(double gasto);
	
	/**
	 * O metodo aplicarDesconto ira dar um desconto no cartao do Hospede
	 * de acordo com o gasto recebido com parametro
	 * @param gasto
	 * @return
	 */
	public double aplicarDesconto(double gasto);
	
	/**
	 * Metodo que saca dinheiro do cartao de fidelidade do hospede de acordo com sua qtd de pontos
	 * e de acordo com o seu tipo de cartao.
	 * @param qtdPontos
	 * @return valor em dinheiro de acordo com os pontos passados
	 */
	public double convertePontos(int qtdPontos) throws SistemaException;
}
