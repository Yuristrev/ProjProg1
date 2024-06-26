package ps2.restapidb;

import javax.persistence.*;

@Entity
@Table(name="campeonatos")
public class Campeonato {
	@Id @GeneratedValue
	private long id;
	private String nome;
	private int anoFundacao;
		
	public Campeonato() {
		super();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getAnoFundacao() {
		return anoFundacao;
	}
	public void setAnoFundacao(int anoFundacao) {
		this.anoFundacao = anoFundacao;
	}

}