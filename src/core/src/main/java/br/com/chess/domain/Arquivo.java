package br.com.chess.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import java.util.Objects;

@Entity
@Audited
@Table(name="arquivo")
public class Arquivo extends BaseDomain {
	
	@Column(name="bucket", nullable=false, length=64)
	private String bucket;
	
	@Column(name="original_name", nullable=false, length=128)
	private String originalName;
	
	@ManyToOne @JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario;
	
	@Column(name="excluido", nullable=false)
	private boolean excluido = false;
	
	@Column(name="url", nullable=true, length=255)
	private String url;


	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Arquivo)) return false;
		if (!super.equals(o)) return false;
		Arquivo arquivo = (Arquivo) o;
		return isExcluido() == arquivo.isExcluido() && Objects.equals(getBucket(), arquivo.getBucket()) && Objects.equals(getOriginalName(), arquivo.getOriginalName()) && Objects.equals(getUsuario(), arquivo.getUsuario()) && Objects.equals(getUrl(), arquivo.getUrl());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getBucket(), getOriginalName(), getUsuario(), isExcluido(), getUrl());
	}
}
