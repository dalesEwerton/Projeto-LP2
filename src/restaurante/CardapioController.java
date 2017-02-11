package restaurante;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.SistemaException;

public class CardapioController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String FIM_DE_LINHA = System.lineSeparator();
	
	private final String NOME = "nome";
	private final String PRECO = "preco";
	private String ordenacao;
	private boolean ordena = false;
	
	private List<Menu> menu;

	public CardapioController( ) {

		this.ordenacao = "nome";
		this.menu = new ArrayList<>();
	}

	public List<Menu> getMenu() {
		return menu;
	}


	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}


	/**
	 * o metodo buscaMenu percorre a lista de menu para conferir se ha um
	 * prato ou refeicao com o mesmo nome recebido com parametro 
	 * 
	 * @param nome
	 * @return prato - Prato
	 * @throws SistemaException 
	 */
	public Menu buscaNoMenu(String nome) throws SistemaException {
	
		for (Menu menuAtual : this.menu) {
			
			if (menuAtual.getNome().equalsIgnoreCase(nome)){			
				return menuAtual;
			}
		}	
		
		throw new SistemaException("Item nao consta no menu");
	}


	/**
	 * o metodo criaRefeicao ira criar uma lista de pratos que ira compor a
	 * refeicao
	 * 
	 * @param componentes
	 * @return prato - ArrayList<Prato>
	 * @throws SistemaException 
	 */
	public ArrayList<Menu> criaRefeicao(String componentes) throws SistemaException {

		ArrayList<Menu> pratos = new ArrayList<>();
		String[] nomes = componentes.split(";");

		for (String nome : nomes) {

			Menu prato = buscaNoMenu(nome);

			if (prato != null) {

				pratos.add(prato);
			}
		}
		return pratos;
	}

	/**
	 * o metodo cadastraPratos ira cadastrar um novo prato ao cardapio
	 * utilizando os paramentros para sua composicao
	 * @param nome
	 * @param preco
	 * @param descricao
	 * @return boolean
	 * @throws IOException 
	 */
	public boolean cadastraPrato(String nome, double preco, String descricao) throws IOException {

		Menu prato = new Prato(nome, preco, descricao);

		menu.add(prato);
		
		if( ordena ){
			
			ordenaMenu(ordenacao);
		}
		this.salvaMenusEmTxt();
		return true;
	}

	/**
	 * o metodo cadastraRefeicao ira cadastrar uma nova refeicao ao cardapio
	 * 
	 * @param nome
	 * @param descricao
	 * @param componentes
	 * @return boolean
	 * @throws SistemaException
	 * @throws IOException 
	 */
	public boolean cadastraRefeicao(String nome, String descricao,
						String componentes) throws SistemaException, IOException {

		ArrayList<Menu> pratos = criaRefeicao(componentes);

		Menu refeicao = new Refeicao(nome, descricao, pratos);

		menu.add(refeicao);
		
		if( ordena ){
			ordenaMenu(ordenacao);
		}
		this.salvaMenusEmTxt();
		return true;
	}

	/**
	 * o metodo removePrato ira remover o prato do cardapio
	 * procurando-a pelo nome recebido como parametro 
	 * @param nome 
	 * @return boolean
	 * @throws SistemaException 
	 * @throws IOException 
	 */
	public boolean removeDoMenu(String nome) throws SistemaException, IOException {

		Menu pedido = buscaNoMenu(nome);

		if (pedido != null) {

			menu.remove(pedido);

			return true;
		}
		this.salvaMenusEmTxt();
		return false;
	}

	/**
	 * o metodo consultaNoMenu ira percorrer a lista de prato para verificar se
	 * ha um prato com o nome e um atributo recebido como parametro
	 * 
	 * @param nome
	 * @param atributo
	 * @return String
	 * @throws SistemaException 
	 */
	public String consultaNoMenu(String nome, String atributo) throws SistemaException {

		Menu pedido = buscaNoMenu(nome);

		if (atributo.equalsIgnoreCase("preco")) {

			String preco = String.format("R$%.2f", pedido.calculaPreco());

			return preco;
		} else if (atributo.equalsIgnoreCase("descricao")) {
			
			if (pedido instanceof Prato){
				return pedido.getDescricao();
			
			}else{
				return pedido.toString();
						
			}
		} else {

			return pedido.getNome();
		}
	}

	/**
	 * o metodo consultaRestaurante ira procurar no cardapio se ha um prato ou
	 * uma refeicao com o nome e atributo recebido como parametro
	 * 
	 * @param nome
	 * @param atributo
	 * @return String
	 * @throws SistemaException
	 */
	public String consultaRestaurante(String nome, String atributo)throws SistemaException {

		verificaAtributoInvalido(atributo);

		if (buscaNoMenu(nome) != null) {
			return consultaNoMenu(nome, atributo);
		
		} else {
			throw new SistemaException("Prato e refeicao inexistente.");
		}
	}

	/**
	 * o metodo verificaAtributoInvalido ira verificar se o atributo recebido
	 * como paramentro eh um preco, uma descricao ou um nome
	 * 
	 * @param atributo
	 * @throws SistemaException
	 */
	public void verificaAtributoInvalido(String atributo) throws SistemaException {

		if (!atributo.equalsIgnoreCase("preco") && !atributo.equalsIgnoreCase("descricao")
				&& !atributo.equalsIgnoreCase("nome")) {

			throw new SistemaException("Atributo invalido.");
		}
	}
	
	/**
	 * @throws IOException 
	 *  Ira ordenar o menu em ordem alfabetica
	 */
	public void ordenaPorNome () throws IOException{
		
		Collections.sort(menu, new OrdenaNome());
		ordenacao = "nome";
		this.salvaMenusEmTxt();
	}
	
	/**
	 * @throws IOException 
	 * ira ordenar o menu de forma crescenta a partir do preco dos pratos e refeicoes que a compoe 
	 */
	public void ordenaPorPreco() throws IOException{
		
		Collections.sort(menu, new OrdenaPreco());
		ordenacao = "preco";
		this.salvaMenusEmTxt();
	}
	/**
	 * ira ordenar o menu a partir da opcao pasada como parametro
	 * @param opcao
	 * @throws IOException
	 */
	public void ordenaMenu (String opcao) throws IOException{
		
		this.ordena = true;
		
		if (opcao.equalsIgnoreCase(PRECO)){
			ordenaPorPreco();
		}else if (opcao.equalsIgnoreCase(NOME)){
			ordenaPorNome();
		}
	}
	
	/**
	 * Metodo salvaMenuEmTxt, registra as informações dos Menus cadastrados
	 * em um arquivo txt.
	 * @throws IOException
	 */
	public void salvaMenusEmTxt() throws IOException {
		String separator = System.getProperty("file.separator"); 
		String dir_arquivos = System.getProperty("user.dir") + separator + "arquivos_sistema" + separator + "relatorios"
				+ separator;
		
		File arquivo = new File(dir_arquivos + "cad_restaurante.txt");
		if(!arquivo.exists()) {
			arquivo.createNewFile();
		}
		
		FileWriter fw = new FileWriter(arquivo);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("Menu do Restaurante: " + this.menu.size() + " itens no cardapio" + FIM_DE_LINHA);
		for (int i = 0; i < this.menu.size(); i++) {
			Menu menuAtual = this.menu.get(i);
			bw.write("==> Item " + (i + 1) + ":" + FIM_DE_LINHA);
			bw.write("Nome: " + menuAtual.getNome() + " Preco: R$" + menuAtual.calculaPreco() + FIM_DE_LINHA);
			bw.write("Descricao: " + menuAtual.getDescricao());
			bw.write(FIM_DE_LINHA);
			
			if(menuAtual.getClass().getSimpleName().equalsIgnoreCase("Refeicao")) {
				bw.write("Pratos: ");
				Refeicao refeicaoAtual = (Refeicao) menuAtual;
				for (int j = 0; j < refeicaoAtual.getPratos().size(); j++) {
					if(j == (refeicaoAtual.getPratos().size() - 1)) {
						bw.write(refeicaoAtual.getPratos().get(j).getNome() + ".");
					}
					bw.write(refeicaoAtual.getPratos().get(j).getNome() + ", ");
				}
				bw.write(FIM_DE_LINHA);
			}
			bw.write(FIM_DE_LINHA);
		}
		bw.close();
		fw.close();
	}
	
	public String toString(){
		
		String stringFinal = "";
		int count = 0;
		
		for (Menu comida : menu) {
			
			if (count < menu.size() - 1){
				stringFinal += comida.getNome() + ";";
			
			}else{
				stringFinal += comida.getNome();
			}
		
			count ++;
		}
		
		return stringFinal;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menu == null) ? 0 : menu.hashCode());
		return result;
	}

	/**
	 * Ira checar se um cardapio eh igual ao outro de acordo com a lista
	 * de menu que o mesmo possui 
	 */
	@Override
	public boolean equals(Object outroObjeto) {
		
		if (outroObjeto instanceof CardapioController){
			CardapioController outroCardapio = (CardapioController) outroObjeto;
			
			if (this.getMenu().equals(outroCardapio.getMenu())){
				return true;
			}
		}
		
		return false;		
	}
	
}