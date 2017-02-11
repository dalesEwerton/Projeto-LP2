package hospedes;


import java.io.Serializable;

import exceptions.HospedeException;
import exceptions.SistemaException;


public class Hospede implements HospedeIF, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;
	private String dataNascimento;
	private CartaoFidelidade cartao;
	
	/***
	 * Classe que modela os hospedes no sistema
	 * 
	 * @param nome - String
	 * @param email - String
	 * @param dataNascimento - String
	 */
	public Hospede(String nome, String email, String dataNascimento)throws SistemaException{
		
		if(verificaEmailValido(email)){
			
			this.nome = nome;
			this.email = email;
			this.dataNascimento = dataNascimento;
		
			this.cartao = new CartaoFidelidade();
			
		
		}else{
			throw new HospedeException("Email invalido");
		}
	}

	/***
	 * O metodo getNome ira retorna o nome do hospede
	 * @return nome - String
	 */
	public String getNome() {
		return nome;
	}

	/***
	 * O metedo setNome ira redefine o nome do hospede
	 * @param nome - String
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/***
	 * O metodo getEmail ira retorna o email do hospede
	 * @return email - String
	 */
	public String getEmail() {
		return email;
	}

	/***
	 * O metodo setEmail ira redefine o email do hospede
	 * @param email - String
	 * @throws HospedeException 
	 */
	public void setEmail(String email) throws HospedeException {
		
		if(verificaEmailValido(email)){
			this.email = email;	
		
		}else{
			throw new HospedeException("Email invalido");
		}
	}

	/***
	 * O metodo getDataNascimento retorna a data de nascimento do hospede
	 * @return data de nascimento - String
	 */
	public String getDataNascimento() {
		return dataNascimento;
	}

	/***
	 * O metodo setDataNascimento redefine a data de nascimento do hospede
	 * @param data de nascimento - String
	 */
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/***
	 * Retorna a informacao de um parametro especifico de hospede
	 * 
	 * @param atributo - String
	 * @return nome/email/dataNascimento - String
	 * @throws HospedeException
	 */
	public String getInfoHospede(String atributo)throws SistemaException {
		
		atributo = atributo.toUpperCase();
		
		switch(atributo){
			case "NOME":
				return this.getNome();
			
			case "EMAIL":
				return this.getEmail();
			
			case "DATA DE NASCIMENTO":
				return this.getDataNascimento();
			
			case "PONTOS":
				return String.valueOf(this.cartao.getPontosCartao());
			
			default:
				throw new HospedeException("Parametro invalido para hospedes");
		}
		
	}

	/**
	 * O metodo aplicarDesconto ira dar um desconto no cartao do Hospede
	 * de acordo com o gasto recebido com parametro
	 */
	public double aplicarDesconto(double gasto){
		return this.cartao.aplicarDesconto(gasto);
	}

	/**
	 * O metodo adicionaPonto ira adicionar pontos ao cartao do Hospede
	 * de acordo com o gasto recebido como parametro
	 */
	public void adicionarPontos(double gasto){
		this.cartao.adicionarPontos(gasto);
	}

	/***
	 * O metodo VerificaEmailValido ira verifica se o email passado como
	 * parametro eh valido para uso
	 * 
	 * @param email - String
	 * @return boolean
	 */
	private boolean verificaEmailValido(String email){
		
		if(email.contains("@")){
			String[] emailSeparator = email.split("@");
			
			if(emailSeparator[0].length() == 0 || emailSeparator[1].length() == 0){
				return false;
			
			}else{
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Metodo que saca dinheiro do cartao de fidelidade do hospede de acordo com sua qtd de pontos
	 * e de acordo com o seu tipo de cartao.
	 * @param qtdPontos
	 * @return valor em dinheiro de acordo com os pontos passados
	 * @throws SistemaException 
	 */
	@Override
	public double convertePontos(int qtdPontos) throws SistemaException {
		return this.cartao.convertePontos(qtdPontos);
	}
	
}