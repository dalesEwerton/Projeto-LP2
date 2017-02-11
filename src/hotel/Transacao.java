package hotel;
/**
 * A classe Transacao representada um transacao feita por um hospede. Esta classe contem uma data, o nome do 
 * hospede q fez a transacao, o total pago pelo hospede por aquilo que a transaco representa, e detalhes da 
 * transacao
 */

import java.io.Serializable;
import java.time.LocalDate;

public class Transacao implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private LocalDate data;
	private String nomeHospede;
	private double totalPago;
	private String detalhes;
	
	
	public Transacao(LocalDate localDate, String nomeHospede, double totalPago, String detalhes){
		
		this.data = localDate;
		this.nomeHospede = nomeHospede;
		this.totalPago = totalPago;
		this.detalhes = detalhes;
		
	}
	
	/**
	 * Retorna a data em que a transacao foi feita
	 * @return
	 */
	public LocalDate getData() {
		return data;
	}
	
	/**
	 * Reedefine a data da transacao
	 * @param dataCheckout
	 */
	public void setData(LocalDate dataCheckout) {
		this.data = dataCheckout;
	}
	
	/**
	 * Retorna o nome do hospede que fez a transacao
	 * @return
	 */

	public String getNomeHospede() {
		return nomeHospede;
	}
	
	/**
	 * Reedefine o nome do hospede
	 * @param nomeHospede
	 */

	public void setNomeHospede(String nomeHospede) {
		this.nomeHospede = nomeHospede;
	}
	
	/**
	 * Retorna o total pago pelo hospede
	 * @return
	 */

	public double getTotalPago() {
		return totalPago;
	}

	/**
	 * Reedefine o total pago
	 * @param totalPago
	 */
	public void setTotalPago(double totalPago) {
		this.totalPago = totalPago;
	}
	
	/**
	 * Retorna os detalhes da transacao
	 * @return
	 */
	public String getDetalhes() {
		return detalhes;
	}

	/** 
	 * Reedefine os detalhes
	 * @param detalhes
	 */
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}	
	
	
	
}
