package br.com.chess.domain;

import br.com.chess.UtilData;
import br.com.chess.domain.enums.TipoPlataforma;
import br.com.chess.dto.PuzzleDiarioDto;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Audited
@Entity
@Table(name = "puzzle_diario", indexes = {@Index(unique = true, columnList = "tipo, data_publicacao")})
public class PuzzleDiario extends BaseDomain {
    private static final long serialVersionUID = 1279645996263217777L;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoPlataforma tipo;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "url", nullable = true)
    private String url;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_publicacao", nullable = false)
    private Date dataPublicacao;

    @Column(name = "fen", nullable = true)
    private String fen;

    @Column(name = "pgn", nullable = true)
    private String pgn;

    @Column(name = "image", nullable = true)
    private String image;

    public PuzzleDiario() {
        // Construtor padrao
    }

    public PuzzleDiario(PuzzleDiarioDto puzzleDiarioDto, TipoPlataforma plataforma) {
        super();
        this.tipo = plataforma;
        this.dataPublicacao = UtilData.getDateFromTimestamp(Long.parseLong(puzzleDiarioDto.getDataPublicacao()));
        this.titulo = puzzleDiarioDto.getTitle();
        this.url = puzzleDiarioDto.getUrl();
        this.fen = puzzleDiarioDto.getFen();
        this.pgn = puzzleDiarioDto.getPgn();
        this.image = puzzleDiarioDto.getImage();
    }

    public TipoPlataforma getTipo() {
        return tipo;
    }

    public void setTipo(TipoPlataforma tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getFen() {
        return fen;
    }

    public void setFen(String fen) {
        this.fen = fen;
    }

    public String getPgn() {
        return pgn;
    }

    public void setPgn(String pgn) {
        this.pgn = pgn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PuzzleDiario that = (PuzzleDiario) o;
        return getTipo() == that.getTipo() && Objects.equals(getTitulo(), that.getTitulo()) && Objects.equals(getUrl(), that.getUrl()) && Objects.equals(getDataPublicacao(), that.getDataPublicacao()) && Objects.equals(getFen(), that.getFen()) && Objects.equals(getPgn(), that.getPgn()) && Objects.equals(getImage(), that.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTipo(), getTitulo(), getUrl(), getDataPublicacao(), getFen(), getPgn(), getImage());
    }
}
