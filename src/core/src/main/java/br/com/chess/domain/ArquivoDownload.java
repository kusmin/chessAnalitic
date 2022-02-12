package br.com.chess.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="arquivo_download")
public class ArquivoDownload extends BaseDomain {
	
	@ManyToOne @JoinColumn(name="arquivo_id", nullable=false)
	private Arquivo arquivo;
	
	@ManyToOne @JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario;

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
