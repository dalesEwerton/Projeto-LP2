package hotel;

/** 
 * A classe HotelController representa o administrador responsavel pelas acoes feitas no hotel. Esta classe contem 
 * uma lista com as transacoes feitas, uma lista de hospedes , um mapa onde a chave sao os hospedes e os valores 
 * suas respectivas estadias, alem do lucro total do hotel assim como um cardapio.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import estadias.Estadia;
import exceptions.SistemaException;
import exceptions.StringInvalidaException;
import hospedes.Hospede;
import quarto.Quarto;
import restaurante.CardapioController;
import restaurante.Menu;

public class HotelController implements HotelControllerIF, Serializable{
	
	private static final long serialVersionUID = 1L;

	private static final String FIM_DE_LINHA = System.lineSeparator();
	
	private ArrayList<Transacao> historicoHotel;
	private List<Hospede> hospedes;
	private HashMap<Hospede, List<Estadia>> mapaDeHospedagens;
	private double totalLucroHotel;
	private CardapioController cardapio;

	public HotelController() {
		
		this.hospedes = new ArrayList<>();
		this.mapaDeHospedagens = new HashMap<>();
		this.historicoHotel = new ArrayList<>();
		this.totalLucroHotel = 0;
		this.cardapio = new CardapioController();
	}

	/**
	 * Cadastra um hospede no hotel e retorna o email desse
	 */
	@Override
	public String cadastraHospede(String nome, String email, String dataNascimento) throws SistemaException, IOException {		
		
		testString(nome, "Nome nao pode ser vazio ou nulo");
		testString(email, "Email nao pode ser vazio ou nulo");
		testString(dataNascimento, "Data nao pode ser nula ou vazia");
		
		//se hospede ja estiver cadastrado ele cadastra novamente
		Hospede novoHospede = new Hospede(nome, email, dataNascimento);
		this.hospedes.add(novoHospede);
		
		this.salvaHospedesEmTxt();
		
		return email;

	}

	/**
	 * Remove, a partir do email, um hospede do hotel
	 */
	@Override
	public void removeHospede(String email)throws Exception {
		Hospede hospede = buscaHospedeEmail(email);
		this.hospedes.remove(hospede);
	}

	/***
	 * O Id vai retornar informar o indice em que o hospede vai estar armazenado 
	 * na lista, basta pegar o hospede da lista e chamar o metodo getInfoHospede dele[
	 * e passar o mesmo atributo
	 * @throws SistemaException 
	 */
	@Override
	public String getInfoHospede(String id, String atributo) throws Exception {
		
		Hospede hospedeAtual = buscaHospedeEmail(id);
		
		return hospedeAtual.getInfoHospede(atributo);
	}

	/***
	 * Aqui é necessario verificar se o atributo é valido(Email, nome, ou dataNascimento)
	 * pode ser um switch, e depois, settar o valor desse atributo chamando o set de 
	 * hospede do atributo pedido
	 * @throws Exception 
	 */
		
	@Override
	public void atualizaCadastro(String id, String atributo, String valor) 
				throws Exception {
		
		String novoAtributo = atributo.trim().toUpperCase();
		Hospede novoHospede = buscaHospedeEmail(id);

		switch(novoAtributo) {
		case "NOME":
			novoHospede.setNome(valor);
			break;
		case "EMAIL":
			novoHospede.setEmail(valor);
			break;
		case "DATA DE NASCIMENTO":
			novoHospede.setDataNascimento(valor);
			break;
		default:
			throw new SistemaException("Atributo de hospede invalido!");
		}

	}

	/***
	 * Realiza o checkin de um hospede
	 * @throws Exception 
	 */
	@Override
	public void realizaCheckin(String email, int dias, String quarto,
			String tipoQuarto) throws Exception {
		
		try{
			this.verificaQuarto(quarto);
		
		}catch(Exception e) {
			throw new SistemaException("Erro ao realizar checkin. " + e.getMessage());
		}
		
		Estadia novaEstadia = new Estadia(dias, quarto, tipoQuarto);
		Hospede novoHospede = this.buscaHospedeEmail(email);

		if(!this.hospedes.contains(novoHospede)){
			throw new SistemaException("Hospede nao cadastrado");
		
		}else{
			
			if(this.verificaHospedeNoHotel(novoHospede)) {
				List<Estadia> estadiasDoHospede = this.mapaDeHospedagens.get(novoHospede);
				estadiasDoHospede.add(novaEstadia);
			
			}else {
				
				ArrayList<Estadia> estadias = new ArrayList<>();
				estadias.add(novaEstadia);
				this.mapaDeHospedagens.put(novoHospede, estadias);
			}	
		}
		
	}
	
	/**
	 * @throws Exception 
	 * Retorna um informacao de um hospede, a partir de seu email, dependendo de um atributo que ira significar 
	 * o tipo de informacao a ser retornada
	 */
	@Override
	public String getInfoHospedagem(String email, String atributo) throws Exception {
		
		Hospede novoHospede = this.buscaHospedeEmail(email);
		
		if(this.mapaDeHospedagens.containsKey(novoHospede)){
			List<Estadia> hospedagensDoHospede = this.mapaDeHospedagens.get(novoHospede);
			
			String novoAtributo = atributo.trim().toUpperCase();
			
			switch(novoAtributo) {
				case "HOSPEDAGENS ATIVAS":
					return String.valueOf(hospedagensDoHospede.size());

				case "QUARTO":
					String idDosquartos = "";
					idDosquartos += hospedagensDoHospede.get(0).getQuarto().getID();

					for (int i = 1; i < hospedagensDoHospede.size(); i++) {
						Quarto quartoAtual = hospedagensDoHospede.get(i).getQuarto();
						idDosquartos += "," + quartoAtual.getID();
					}
				
					return idDosquartos;

				case "TOTAL":
					double valorTotal = 0;
				
					for (Estadia estadia : hospedagensDoHospede) {
						valorTotal += estadia.precoEstadia();
					}
				
					int valorInteiro = (int) valorTotal;
					String valorFinal = String.valueOf(valorInteiro);
					return "R$" + valorFinal + ",00";
				
			}
			
			throw new SistemaException("Atributo Invalido!");
		
		}else{
			throw new SistemaException("Erro na consulta de hospedagem. "
					+ "Hospede " + novoHospede.getNome() + " nao esta hospedado(a).");
		}
	}

	/**
	 * @throws Exception 
	 * Realiza checkout de um hospede
	 */
	@Override
	public String realizaCheckout(String email, String quarto) throws Exception {
		
		Hospede hospede = buscaHospedeEmail(email);
		
		if(this.mapaDeHospedagens.containsKey(hospede)){ 
			
			for(Estadia estadia : mapaDeHospedagens.get(hospede)){
			
				if(estadia.getQuarto().getID().equalsIgnoreCase(quarto)){
					
					double precoComDesconto = hospede.aplicarDesconto(estadia.precoEstadia());
					hospede.adicionarPontos(estadia.precoEstadia());
					
					Transacao transacao = new Transacao(LocalDate.now(),
							hospede.getNome(), estadia.precoEstadia(),
							estadia.getQuarto().getID());
					
					this.historicoHotel.add(transacao);
					this.mapaDeHospedagens.get(hospede).remove(estadia);
					
					totalLucroHotel += estadia.precoEstadia();
					
					//Verificando se o hospede ainda possui hospedagens
					if(this.mapaDeHospedagens.get(hospede).toString().equals("[]")){
						this.mapaDeHospedagens.remove(hospede);
					}
					this.salvaTransacoesEmTxt();
					return String.format("R$%.2f", precoComDesconto);
				}
			}
		
		}else{
			throw new SistemaException("Hospede nao esta cadastrado no sistema");
		}
		
		throw new SistemaException("Hospede nao possui esta estadia");
	}

	/**
	 * Retorna um informacao de todas as transacoes, dependendo de um atributo que ira significar 
	 * o tipo de informacao a ser retornada
	 */
	@Override
	public String consultaTransacoes(String atributo) throws SistemaException {
		
		atributo = atributo.toUpperCase();
		
		switch(atributo){
			case "QUANTIDADE":
				
				String qtd = String.valueOf(this.historicoHotel.size());
				return qtd;
			
				//aqui: se o hotel nn existir hitorico ainda mas ja tiver hospedes cadastrados 
				//ele pega o primeiro
			case "NOME":
				String nomes = this.hospedes.get(0).getNome();
				for(int i = 1 ; i < this.historicoHotel.size() ; i++){
					nomes += ";" + this.historicoHotel.get(i).getNomeHospede();
				}
				return nomes;
			
			case "TOTAL":
				return "R$" + String.valueOf((int)this.totalLucroHotel) + ",00";
			
			default:
				throw new SistemaException("Atributo invalido");
		}
	}
	
	/**
	 * Retorna um informacao de uma transacao, escolhida a partir de seu indice, dependendo de um atributo que ira significar 
	 * o tipo de informacao a ser retornada
	 */
	public String consultaTransacoes(String atributo, int indice) throws SistemaException{
		
		Transacao transacao = this.historicoHotel.get(indice);
		atributo = atributo.toUpperCase();
		
		switch(atributo){
			case "NOME":
				return transacao.getNomeHospede();
			
			case "TOTAL":
				return "R$" + String.valueOf((int)transacao.getTotalPago()) + ",00";
				
			case "DETALHES":
				return transacao.getDetalhes();
			
			default:
				throw new SistemaException("Atributo invalido");
				
		}
	}
	
	/**
	 * Realiza o pedido, de algum prato ou refeicao, do hospede, a partir do seu email e o nome do pedidio.
	 *  O metodo retorna o valor do pedido
	 * @param email
	 * @param nomePedido
	 * @return
	 * @throws Exception
	 */
	public String realizaPedido( String email, String nomePedido ) throws Exception{
		
		Hospede hospede = buscaHospedeEmail(email);
		Menu pedido = cardapio.buscaNoMenu(nomePedido);
		double preco = pedido.calculaPreco();
		
		double precoDesconto = hospede.aplicarDesconto(preco);
		hospede.adicionarPontos(preco);
		this.totalLucroHotel += preco;
		
		Transacao transacao = new Transacao( LocalDate.now(), hospede.getNome(), preco, nomePedido);
		historicoHotel.add(transacao);
		
		return String.format("R$%.2f", precoDesconto);
		
	}

	/**
	 * Verifica se uma string eh invalida
	 */
	public void testString(String stringTest, String erroMsg)throws SistemaException{

		if(stringTest == null || stringTest.trim().isEmpty()){
			throw new StringInvalidaException(erroMsg);
		}
	}
	
	/**
	 * Busca um hospede, a partir de seu email, na lista de hospedes e o retorna
	 */
	public Hospede buscaHospedeEmail(String email) throws Exception{

		for(Hospede hospede : this.hospedes){
			
			if(email.equalsIgnoreCase(hospede.getEmail())){
				return hospede;
			}
		}
		throw new Exception ("Erro na consulta de hospede. "
				+ "Hospede de email " + email + " nao foi cadastrado(a).");
	}
	
	/**
	 * Busca um hospede, a partir de seu nome, na lista de hospedes e o retorna
	 */
	public Hospede buscaHospedeNome(String nome)throws SistemaException{

		for(Hospede hospede : this.hospedes){
			
			if(nome.equalsIgnoreCase(hospede.getNome())){
				return hospede;
			}
		}

		throw new SistemaException("Hospede de nome " + nome +" nao foi cadastrado(a)");
	}

	/**
	 * Busca um hospede, a partir de sua data de nascimento, na lista de hospedes e o retorna
	 */
	public Hospede buscaHospedeDataNascimento(String dataNascimento)throws SistemaException{

		for(Hospede hospede : this.hospedes){
			
			if(dataNascimento.equalsIgnoreCase(hospede.getDataNascimento())){
				return hospede;
			}
		}

		throw new SistemaException("Hospede de data de nascimento" + dataNascimento + " nao foi cadastrado(a)");
	}

	/**
	 * Retorna uma informacao de um hospede, dependendo de um atributo que ira significar 
	 * o tipo de informacao a ser retornada
	 */
	public Hospede consultaHospede(String atributo)throws Exception{
		atributo = atributo.toUpperCase();

		switch(atributo){
		
		case "NOME":
			return buscaHospedeNome(atributo);
		
		case "EMAIL":
			return buscaHospedeEmail(atributo);
		
		case "DATANASCIMENTO":
			return buscaHospedeDataNascimento(atributo);
		
		default:
			throw new StringInvalidaException("Hospede nao possui tal atributo");
		}
	}

	/**
	 * verifica se determinado hospede esta no mapa de hospesdes e se tem estadias ativas
	 * @param hospedeAtual
	 * @return
	 */
	public boolean verificaHospedeNoHotel(Hospede hospedeAtual) {
		
		if(mapaDeHospedagens.containsKey(hospedeAtual)) {
			return true;
		
		}else {
			return false;
		}
	}
	
	/**
	 * Verifica se um determinado quarto eh invalido
	 * @param quarto
	 * @return
	 * @throws SistemaException
	 */
	public boolean verificaQuarto(String quarto) throws SistemaException {
		
		for (Hospede hospede : hospedes) {
		
			if(mapaDeHospedagens.containsKey(hospede)) {
				
				List<Estadia> estadiasDoHospedeAtual = mapaDeHospedagens.get(hospede);
			
				for (Estadia estadia : estadiasDoHospedeAtual) {
				
					if(estadia.getQuarto().getID().equalsIgnoreCase(quarto)) {
						throw new SistemaException("Quarto " + quarto + " ja esta ocupado.");
					}
				}
			
			}	
		}
		return false;
	}
	
	/**
	 * Converte os pontos de fidelidade em dinheiro e retorna esse valor ao hospede, usando o tipo de cartao 
	 * para defenir o comportamento
	 */
	@Override
	public String convertePontos(String email, int qtdPontos) throws Exception {
		Hospede hospedeAtual = this.buscaHospedeEmail(email);
		double pontos = hospedeAtual.convertePontos(qtdPontos);
		
		return String.format("R$%.2f", pontos);
	}
	
	/**
	 * Metodo salvaHospedesEmTxt, registra todas as informações dos hospedes cadastrados
	 * em um arquivo txt.
	 * @throws IOException
	 */
	public void salvaHospedesEmTxt() throws IOException {
		String separator = System.getProperty("file.separator"); 
		String dir_arquivos = System.getProperty("user.dir") + separator + "arquivos_sistema" + separator + "relatorios"
				+ separator;
		
		File arquivo = new File(dir_arquivos + "cad_hospedes.txt");
		if(!arquivo.exists()) {
			arquivo.createNewFile();
		}
		
		FileWriter fw = new FileWriter(arquivo);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Cadastro de Hospedes: " + this.hospedes.size() + " hospedes registrados" + FIM_DE_LINHA);
		for (int i = 0; i < this.hospedes.size(); i++) {
			Hospede hospedeAtual = this.hospedes.get(i);
			bw.write("==> Hospede " + (i + 1) + ":" + FIM_DE_LINHA);
			bw.write("Email: " + hospedeAtual.getEmail() + FIM_DE_LINHA);
			bw.write("Nome: " + hospedeAtual.getNome() + FIM_DE_LINHA);
			
			String[] data = hospedeAtual.getDataNascimento().split("/");			
			String ano = data[2];
			String mes = data[1];
			String dia = data[0];
			bw.write("Data de nascimento: " + ano + "-" + mes + "-" + dia);
			bw.write(FIM_DE_LINHA);
			bw.write(FIM_DE_LINHA);
		}
		
		bw.close();
		fw.close();
	}
	
	/**
	 * Metodo salvaTransacoesEmTxt, registra todas as transacoes do hotel
	 * em um arquivo txt.
	 * @throws IOException
	 */
	public void salvaTransacoesEmTxt() throws IOException {
		String separator = System.getProperty("file.separator"); 
		String dir_arquivos = System.getProperty("user.dir") + separator + "arquivos_sistema" + separator + "relatorios"
				+ separator;
		
		File arquivo = new File(dir_arquivos + "cad_transacoes.txt");
		if(!arquivo.exists()) {
			arquivo.createNewFile();
		}
		
		FileWriter fw = new FileWriter(arquivo);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Historico de Transacoes:" + FIM_DE_LINHA);
		for (int k = 0; k < this.historicoHotel.size(); k++) {
			Transacao transacaoAtual = this.historicoHotel.get(k);
			String nome = transacaoAtual.getNomeHospede();
			double gasto = transacaoAtual.getTotalPago();
			String detalhes = transacaoAtual.getDetalhes();
			bw.write("==> Nome: " + nome + " Gasto: R$" + gasto + " Detalhes: " + detalhes + FIM_DE_LINHA);
		}
		bw.write(FIM_DE_LINHA);
		
		bw.write("===== Resumo de transacoes =====" + FIM_DE_LINHA);
		bw.write("Lucro total:R$" + this.totalLucroHotel + FIM_DE_LINHA);
		bw.write("Total de transacoes:" + this.historicoHotel.size() + FIM_DE_LINHA);
		double lucroMedio = this.totalLucroHotel / this.historicoHotel.size();
		bw.write("Lucro medio por transacao: R$" + lucroMedio + FIM_DE_LINHA);
		
		bw.close();
		fw.close();
	}
	
	/**
	 * Metodo salvaResumoDoHotelEmTxt(). Registra, em arquivo txt, informacoes relevantes do hotel. Informacoes estas contidas em:
	 * hospedes cadastrados, menus disponiveis no restaurante e historico de transacoes; 
	 * @throws IOException
	 */
	public void salvaResumoDoHotelEmTxt() throws IOException {
		String separator = System.getProperty("file.separator"); 
		String dir_arquivos = System.getProperty("user.dir") + separator + "arquivos_sistema" + separator + "relatorios"
				+ separator;
		
		File arquivo = new File(dir_arquivos + "hotel_principal.txt");
		File arquivoHospedes = new File(dir_arquivos + "cad_hospedes.txt");
		File arquivoMenus = new File(dir_arquivos + "cad_restaurante.txt");
		File arquivoTransacoes = new File(dir_arquivos + "cad_transacoes.txt");
		if(!arquivoHospedes.exists()) 
			arquivoHospedes.createNewFile();
		if(!arquivoMenus.exists()) 
			arquivoMenus.createNewFile();
		if(!arquivoTransacoes.exists()) 
			arquivoTransacoes.createNewFile();
		if(!arquivo.exists()) 
			arquivo.createNewFile();
		
		FileWriter fwa = new FileWriter(arquivo);
		BufferedWriter bwa = new BufferedWriter(fwa);
		
		bwa.write("======================================================" + FIM_DE_LINHA);
		this.escreveNoArquivoDeResumoDoHotel(arquivoHospedes);
		this.escreveNoArquivoDeResumoDoHotel(arquivoMenus);
		this.escreveNoArquivoDeResumoDoHotel(arquivoTransacoes);
		bwa.close();
		fwa.close();
	}

	/**
	 * Metodo escreveNoArquivoDeResumoDoHotel. Metodo auxiliar utilizado pelo metodo salvaResumoDoHotelEmTxt
	 * para tranferir as informacoes de um arquivo, passado como parametro, para o arquivo de texto hotel_principal.txt.
	 * @param arquivoLido - arquivo tera suas informacoes copiadas para o hotel_principal.txt
	 * @throws IOException
	 */
	public void escreveNoArquivoDeResumoDoHotel(File arquivoLido) throws IOException {
		String separator = System.getProperty("file.separator"); 
		String dir_arquivos = System.getProperty("user.dir") + separator + "arquivos_sistema" + separator + "relatorios"
				+ separator;
		
		File arquivo2 = new File(dir_arquivos + "hotel_principal.txt");
		FileWriter fwArquivo2 = new FileWriter(arquivo2, true);
		BufferedWriter bwArquivo2 = new BufferedWriter(fwArquivo2);
		
		FileReader fr = new FileReader(arquivoLido);
		BufferedReader br = new BufferedReader(fr);
		bwArquivo2.write("======================================================" + FIM_DE_LINHA);
		String linha = br.readLine(); 
		while (linha != null) {
			bwArquivo2.write(linha + FIM_DE_LINHA);
			linha = br.readLine();
		}
		bwArquivo2.close();
		fwArquivo2.close();
	}
	
	
	/**
	 * o metodo cadastraPratos ira cadastrar um novo prato ao cardapio
	 * utilizando os paramentros para sua composicao
	 */
	public void cadastraPrato(String nome, double preco, String descrisao) throws IOException {
		cardapio.cadastraPrato(nome, preco, descrisao);
	}
	
	/**
	 * o metodo cadastraRefeicao ira cadastrar uma nova refeicao ao cardapio
	 */
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		cardapio.cadastraRefeicao(nome, descricao, componentes);
	}
	
	/**
	 * o metodo consultaRestaurante ira procurar no cardapio se ha um prato ou
	 * uma refeicao com o nome e atributo recebido como parametro
	 */
	public String consultaRestaurante(String nome, String atributo) throws Exception {
		return cardapio.consultaRestaurante(nome, atributo);
	}
	
	/**
	 * o metodo consultaMenuRestaurante ira a retornar a representacao em string dos itens do menu
	 */
	public String consultaMenuRestaurante(){
		return this.cardapio.toString();
	}
	
	/**
	 * O metodo ordenaMenu ira ordenar a lista do menu de acordo com o tipo recebido
	 * como parametro (preco ou nome)
	 */
	public void ordenaMenu(String tipoOrdenacao) throws IOException{
		this.cardapio.ordenaMenu(tipoOrdenacao);
	}

}
