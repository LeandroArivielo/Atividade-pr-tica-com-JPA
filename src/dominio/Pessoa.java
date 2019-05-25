package dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pessoa implements Serializable {
	@Override
	public String toString() {
		return "Medico [id=" + id + ", nome=" + nome + ", especialidade=" + email + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String email;
	
	public Pessoa(Integer id, String nome, String especialidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = especialidade;
	}
	
	public Pessoa() {
		super();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}