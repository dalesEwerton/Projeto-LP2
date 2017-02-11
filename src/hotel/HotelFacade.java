package hotel;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import exceptions.SistemaException;

public class HotelFacade {

	private HotelController adm;

	public HotelFacade() {
		this.adm = new HotelController();
	}

	/**
	 * o metodo cadastraHospede 
	 * @param nome
	 * @param email
	 * @param dataNascimento
	 * @return
	 * @throws SistemaException
	 * @throws IOException
	 */
	public String cadastraHospede(String nome, String email, String dataNascimento) throws SistemaException, IOException {
		return adm.cadastraHospede(nome, email, dataNascimento);
	}
	
	/**
	 * Remove, a partir do email, um hospede do hotel
	 * @param email
	 */
	public void removeHospede(String email) {
		try {
			adm.removeHospede(email);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 *  O Id vai retornar informar o indice em que o hospede vai estar armazenado 
	 * na lista, basta pegar o hospede da lista e chamar o metodo getInfoHospede dele[
	 * e passar o mesmo atributo
	 * @param id
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getInfoHospede(String id, String atributo) throws Exception {

		return adm.getInfoHospede(id, atributo);
	}

	/**
	 *  Aqui é necessario verificar se o atributo é valido(Email, nome, ou dataNascimento)
	 * pode ser um switch, e depois, settar o valor desse atributo chamando o set de 
	 * hospede do atributo pedido
	 * @param id
	 * @param atributo
	 * @param valor
	 * @throws Exception
	 */
	public void atualizaCadastro(String id, String atributo, String valor) throws Exception {
		adm.atualizaCadastro(id, atributo, valor);
	}

	/**
	 * Realiza o checkin de um hospede
	 * @param email
	 * @param dias
	 * @param quarto
	 * @param tipoQuarto
	 * @throws Exception
	 */
	public void realizaCheckin(String email, int dias, String quarto, String tipoQuarto) throws Exception {
		adm.realizaCheckin(email, dias, quarto, tipoQuarto);
	}

	/**
	 *  Retorna um informacao de um hospede, a partir de seu email, dependendo de um atributo que ira significar 
	 * o tipo de informacao a ser retornada
	 * @param email
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getInfoHospedagem(String email, String atributo) throws Exception {
		return adm.getInfoHospedagem(email, atributo);
	}

	/**
	 * Realiza checkout de um hospede
	 * @param email
	 * @param quarto
	 * @return
	 * @throws Exception
	 */
	public String realizaCheckout(String email, String quarto) throws Exception {
		return adm.realizaCheckout(email, quarto);
	}

	/**
	 *  Retorna um informacao de todas as transacoes, dependendo de um atributo que ira significar 
	 * o tipo de informacao a ser retornada
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String consultaTransacoes(String atributo) throws Exception {
		return adm.consultaTransacoes(atributo);
	}
	
	/**
	 * Retorna um informacao de uma transacao, escolhida a partir de seu indice, dependendo de um atributo que ira significar 
	 * o tipo de informacao a ser retornada
	 * @param atributo
	 * @param indice
	 * @return
	 * @throws Exception
	 */
	public String consultaTransacoes(String atributo, int indice) throws Exception{
		return adm.consultaTransacoes(atributo, indice);
	}


	/**
	 * o metodo cadastraPratos ira cadastrar um novo prato ao cardapio
	 * utilizando os paramentros para sua composicao
	 * 
	 * @param nome
	 * @param preco
	 * @param descricao
	 * @return boolean
	 * @throws IOException 
	 */
	public void cadastraPrato(String nome, double preco, String descrisao) throws IOException {
		adm.cadastraPrato(nome, preco, descrisao);

	}

	/**
	 * o metodo cadastraRefeicao ira cadastrar uma nova refeicao ao cardapio
	 * 
	 * @param nome
	 * @param preco
	 * @param descrisao
	 * @throws Exception
	 */
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		adm.cadastraRefeicao(nome, descricao, componentes);
	}

	/**
	 * o metodo consultaRestaurante ira procurar no cardapio se ha um prato ou
	 * uma refeicao com o nome e atributo recebido como parametro
	 * 
	 * @param nome
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String consultaRestaurante(String nome, String atributo) throws Exception {
		return adm.consultaRestaurante(nome, atributo);
	}
	
	/**
	 * Metodo que converte pontos do cartao de fidelidade, de um usuario especifico, em dinheiro.
	 * 
	 * @param email
	 * @param qtdPontos
	 * @return valor sacado de acordo com os pontos especificados
	 * @throws Exception 
	 */
	public String convertePontos(String email, int qtdPontos) throws Exception {
			return adm.convertePontos(email, qtdPontos);
	}
	
	/**
	 * o metodo consultaMenuRestaurante ira a retornar a representacao em string dos itens do menu
	 * @return
	 */
	public String consultaMenuRestaurante(){
		return this.adm.consultaMenuRestaurante();
	}
	
	/**
	 *  O metodo ordenaMenu ira ordenar a lista do menu de acordo com o tipo recebido
	 * como parametro (preco ou nome)
	 * @param tipoOrdenacao
	 * @throws IOException
	 */
	public void ordenaMenu(String tipoOrdenacao) throws IOException{
		this.adm.ordenaMenu(tipoOrdenacao);
	}
	
	/**
	 * o metodo realizaPedido ira verificar se o email do hospede esta cadastrado no sistema 
	 * e que o item que o mesmo escolheu esta constando no cardapio
	 * @param hospede
	 * @param itemMenu
	 * @return
	 * @throws Exception
	 */
	public String realizaPedido(String hospede, String itemMenu) throws Exception{
		return adm.realizaPedido(hospede, itemMenu);
	}

	/**
	 * Metodo iniciaSistema. Carrega o objeto do tipo HotelController que esta no arquivo hug.dat
	 */
	public void iniciaSistema() {
		try {
			String separator = System.getProperty("file.separator"); 
			String dir_arquivos = System.getProperty("user.dir") + separator + "arquivos_sistema" + separator;
			File arquivo = new File(dir_arquivos + "hug.dat");
			if(!arquivo.exists()) 
				arquivo.createNewFile();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dir_arquivos + "hug.dat"));
			HotelController admSalvo = (HotelController) ois.readObject();
			this.adm = admSalvo;
			ois.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo fechaSistema. Salva o objeto adm, do tipo HotelController, no arquivo hug.dat e
	 * salva resumo do hotel em um arquivo txt
	 */
	public void fechaSistema() throws Exception {
		try {
			String separator = System.getProperty("file.separator"); 
			String dir_arquivos = System.getProperty("user.dir") + separator + "arquivos_sistema" + separator;
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dir_arquivos + "hug.dat"));
			oos.writeObject(adm);
			oos.close();
			this.adm.salvaResumoDoHotelEmTxt();
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}
	}

	public static void main(String[] args) {

		args = new String[] { "hotel.HotelFacade", "easyaccept/testes_uc1.txt",
				"easyaccept/testes_uc2.txt", "easyaccept/testes_uc3.txt", "easyaccept/testes_uc4.txt" ,
				"easyaccept/testes_uc5.txt", "easyaccept/testes_uc6.txt", "easyaccept/testes_uc7.txt"};
		EasyAccept.main(args);
	}
}
