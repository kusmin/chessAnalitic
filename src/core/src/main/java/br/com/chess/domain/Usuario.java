package br.com.chess.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity @Table(name="usuario")
public class Usuario extends BaseDomain {

		
	@Column(name="nome", nullable=false, unique=false, length=128)
	private String nome;
	
	@Column(name="matricula", nullable=true, length=128)
	private String matricula;
	
	@Column(name="email", nullable=false, unique=true, length=128)
	private String email;
	
	@Column(name="ativo", nullable=false)
	private boolean ativo = false;
	
	@Column(name="hash_senha", nullable=false, length=128)
	private String hashSenha;
	
	@ManyToOne @JoinColumn(name="perfil_id", nullable=false)
	private Perfil perfil;

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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getHashSenha() {
		return hashSenha;
	}

	public void setHashSenha(String hashSenha) {
		this.hashSenha = hashSenha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public Boolean isAdmin(){
		for (Permissao perm : perfil.getPermissoes()) {
			if(perm.getAuthority().equals("ROLE_ADMIN")){
				return true;
			}
		}
		return  false;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}	

}
