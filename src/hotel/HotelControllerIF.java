package hotel;

import java.io.File;

/**
 * Interface implementada pelo HotelController
 */

import java.io.IOException;

import exceptions.SistemaException;
import hospedes.Hospede;

public interface HotelControllerIF {
	
	/**
	 * Cadastra um hospede no hotel e retorna o email desse
	 * @param nome
	 * @param email
	 * @param dataNascimento
	 * @return String
	 * @throws SistemaException
	 */
	public String cadastraHospede(String nome, String email, String dataNascimento) throws SistemaException, IOException;
	
	/**
	 *  Remove, a partir do email, um hospede do hotel
	 * @param email
	 * @throws Exception
	 */
	public void removeHospede(String email) throws Exception;
	
	/**
	 *  O Id vai retornar informar o indice em que o hospede vai estar armazenado 
	 * na lista, basta pegar o hospede da lista e chamar o metodo getInfoHospede dele[
	 * e passar o mesmo atributo
	 * @param id
	 * @param atributo
	 * @return String
	 * @throws SistemaException
	 * @throws Exception
	 */
	public String getInfoHospede(String id, String atributo) throws SistemaException, Exception;
	
	/**
	 *  Aqui é necessario verificar se o atributo é valido(Email, nome, ou dataNascimento)
	 * pode ser um switch, e depois, settar o valor desse atributo chamando o set de 
	 * hospede do atributo pedido
	 * @param id
	 * @param atributo
	 * @param valor
	 * @throws SistemaException
	 * @throws Exception
	 */
	public void atualizaCadastro(String id, String atributo, String valor) throws SistemaException, Exception;
	
	/**
	 * Realiza o checkin de um hospede
	 * @param email
	 * @param dias
	 * @param quarto
	 * @param tipoQuarto
	 * @throws SistemaException
	 * @throws Exception
	 */
	public void realizaCheckin(String email, int dias, String quarto,
			String tipoQuarto) throws SistemaException, Exception;
	
	/**
	 * Retorna um informacao de um hospede, a partir de seu email, dependendo de um atributo que ira significar 
	 * o tipo de informacao a ser retornada
	 * @param email
	 * @param atributo
	 * @return String
	 * @throws Exception
	 */
	public String getInfoHospedagem(String email, String atributo) throws Exception;
	
	/**
	 * Realiza checkout de um hospede
	 * @param email
	 * @param quarto
	 * @return String
	 * @throws SistemaException
	 * @throws Exception
	 */
	public String realizaCheckout(String email, String quarto) throws SistemaException, Exception;
	
	/**
	 *  Retorna um informacao de todas as transacoes, dependendo de um atributo que ira significar 
	 * o tipo de informacao a ser retornada
	 * @param atributo
	 * @return String
	 * @throws SistemaException
	 */
	public String consultaTransacoes(String atributo) throws SistemaException;
	
	/**
	 * Retorna um informacao de uma transacao, escolhida a partir de seu indice, dependendo de um atributo que ira significar 
	 * o tipo de informacao a ser retornada
	 * @param atributo
	 * @param indice
	 * @return String
	 * @throws SistemaException
	 */
	public String consultaTransacoes(String atributo, int indice) throws SistemaException;	
	
	/**
	 * Verifica se uma string eh invalida
	 * @param stringTest
	 * @param erroMsg
	 * @throws SistemaException
	 */
	public void testString(String stringTest, String erroMsg)throws SistemaException;
	
	/**
	 *  Busca um hospede, a partir de seu email, na lista de hospedes e o retorna
	 * @param email
	 * @return Hospede
	 * @throws SistemaException
	 * @throws Exception
	 */
	public Hospede buscaHospedeEmail(String email)throws SistemaException, Exception;
	
	/**
	 *  Busca um hospede, a partir de seu nome, na lista de hospedes e o retorna
	 * @param nomel
	 * @return Hospede
	 * @throws SistemaException
	 */
	public Hospede buscaHospedeNome(String nomel)throws SistemaException;
	
	/**
	 *  Busca um hospede, a partir de sua data de nascimento, na lista de hospedes e o retorna
	 * @param dataNascimento
	 * @return Hospede
	 * @throws SistemaException
	 */
	public Hospede buscaHospedeDataNascimento(String dataNascimento)throws SistemaException;
	
	/**
	 * Retorna uma informacao de um hospede, dependendo de um atributo que ira significar 
	 * o tipo de informacao a ser retornada
	 * @param atributo
	 * @return Hospede
	 * @throws SistemaException
	 * @throws Exception
	 */
	public Hospede consultaHospede(String atributo)throws SistemaException, Exception;
	
	/**
	 * Converte os pontos de fidelidade em dinheiro e retorna esse valor ao hospede, usando o tipo de cartao 
	 * para defenir o comportamento
	 * @throws Exception 
	 */
	public String convertePontos(String email, int qtdPontos) throws SistemaException, Exception;
	
	/**
	 * Realiza o pedido, de algum prato ou refeicao, do hospede, a partir do seu email e o nome do pedidio.
	 *  O metodo retorna o valor do pedido
	 * @param email
	 * @param nomePedido
	 * @return Hospede
	 * @throws Exception
	 */
	public String realizaPedido( String email, String nomePedido ) throws Exception;
	
	/**
	 * Metodo salvaHospedesEmTxt, registra todas as informações dos hospedes cadastrados
	 * em um arquivo txt.
	 * @throws IOException
	 */
	public void salvaHospedesEmTxt() throws IOException;
	
	/**
	 * Metodo salvaTransacoesEmTxt, registra todas as transacoes do hotel
	 * em um arquivo txt.
	 * @throws IOException
	 */
	public void salvaTransacoesEmTxt() throws IOException;
	
	/**
	 * Metodo salvaResumoDoHotelEmTxt(). Registra, em arquivo txt, informacoes relevantes do hotel. Informacoes estas contidas em:
	 * hospedes cadastrados, menus disponiveis no restaurante e historico de transacoes; 
	 * @throws IOException
	 */
	public void salvaResumoDoHotelEmTxt() throws IOException;
	
	/**
	 * Metodo escreveNoArquivoDeResumoDoHotel. Metodo auxiliar utilizado pelo metodo salvaResumoDoHotelEmTxt
	 * para tranferir as informacoes de um arquivo, passado como parametro, para o arquivo de texto hotel_principal.txt.
	 * @param arquivoLido - arquivo tera suas informacoes copiadas para o hotel_principal.txt
	 * @throws IOException
	 */
	public void escreveNoArquivoDeResumoDoHotel(File arquivoLido) throws IOException;
	
	/**
	 * o metodo cadastraPratos ira cadastrar um novo prato ao cardapio
	 * utilizando os paramentros para sua composicao
	 * @param nome
	 * @param preco
	 * @param descrisao
	 * @throws IOException
	 */
	public void cadastraPrato(String nome, double preco, String descrisao) throws IOException;
	
	/**
	 * o metodo cadastraRefeicao ira cadastrar uma nova refeicao ao cardapio
	 * @param nome
	 * @param descricao
	 * @param componentes
	 * @throws Exception
	 */
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception ;
	
	/**
	 * o metodo consultaRestaurante ira procurar no cardapio se ha um prato ou
	 * uma refeicao com o nome e atributo recebido como parametro
	 * @param nome
	 * @param atributo
	 * @return String
	 * @throws Exception
	 */
	public String consultaRestaurante(String nome, String atributo) throws Exception;
	
	/**
	 * O consultaMenuRestaurante ira retornar a representacao em string do menu
	 * @return String
	 */
	public String consultaMenuRestaurante();
	
	/**
	 * O metodo ordena menu ira ordenar a lista de Menu de acordo com o tipo 
	 * de ordenacao recebida como paramentro, seja por nome ou por preco
	 * @param tipoOrdenacao
	 * @throws IOException
	 */
	public void ordenaMenu(String tipoOrdenacao) throws IOException;
}