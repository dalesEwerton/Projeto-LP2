package restaurante;

import java.io.Serializable;

public abstract class Menu implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String descricao;

	public Menu(String nome, String descricao ) {
		this.nome = nome;
		this.descricao = descricao;
	}
    
	public abstract double calculaPreco();

	/** 
	 * o metodo getNome retorna o nome do prato
	 * @return nome - String
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * o metodo setNome tem a funcao de alterar o nome do prato
	 * @param novoNome 
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	

	/**
	 * o metodo getDescricao retorna o nome do prato
	 * @return descricao - String
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * o metodo setDescricao tem a funcao de alterar a descricao do prato
	 * @param novaDescricao
	 */

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/*String str1 = "A";
	String str2 = "B";
	int comp = str1.compareTo(str2);
	if(comp < 0) {
	  System.out.println("str1 menor que str2");
	} else if(comp > 0) {
	  System.out.println("str1 maior que str2");
	} else {
	  System.out.println("str1 e str2 s�o iguais");
	}
	
	public int compareTo (Menu outroMenu){
		int comp = this.getNome().compareTo(outroMenu.getNome());
		
		if (comp > 0){
			return 1;
		
		}else if (comp < 0){
			return -1;
		
		}else{
			return 0;
		}
	}*/
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/*
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}*/

	

	@Override
	public boolean equals(Object objeto) {
		
		if( objeto instanceof Menu){
			
			Menu outroMenu = (Menu) objeto;
			
			if( this.getDescricao().equalsIgnoreCase(outroMenu.getDescricao())
				&& this.getNome().equalsIgnoreCase(outroMenu.getNome())){
				
				return true;
			}
		}
		return false;
	}
		
}
