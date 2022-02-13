package br.com.chess.domain;

import br.com.chess.UtilData;
import br.com.chess.UtilMetodo;
import br.com.chess.dto.JogadorDto;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Audited
@Table(name="jogador")
public class Jogador extends BaseDomain{
    private static final long serialVersionUID = -3883063181789221462L;

    @Column(name = "avatar", nullable = true )
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoPlataforma tipo;

    @Column(name = "jogador_id", nullable = false)
    private long jogadorId;

    @Column(name ="url_id", nullable = true)
    private String urlId;

    @Column(name = "url", nullable = true)
    private String url;

    @Column(name = "nome", nullable = true, length = 128)
    private String nome;

    @Column(name = "username", nullable = false, length = 64)
    private String username;

    @Column(name ="seguidores", nullable = false)
    private long seguidores = 0;

    @Column(name = "localidade", nullable = true)
    private String localidade;

    @Column(name = "pais", nullable = true)
    private String pais;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ultima_vez_online", nullable = false)
    private Date ultimaVezOnline;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_registro_plataforma", nullable = false)
    private Date dataRegistroPlataforma;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusContaPlataforma statusConta;

    @Column(name = "titulo", nullable = true)
    private String titulo;

    @Column(name = "fide", nullable = true )
    private int fide = 0;

    @Column(name = "streamer")
    private boolean streamer = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_ultima_atualizacao", nullable = false)
    private Date dataUltimaAtualizacao;

    @Column(name = "twitch", nullable = true, length = 128)
    private String twitchUrl;

    public Jogador() {
        // Construtor padrao
    }

    public Jogador(JogadorDto jogadorDto, TipoPlataforma tipoPlataforma) {
        super();
        this.avatarUrl = jogadorDto.getAvatar() != null ? jogadorDto.getAvatar() : null;
        this.tipo = tipoPlataforma;
        this.jogadorId = jogadorDto.getJogadorId();
        this.urlId = jogadorDto.getUrlId() != null ? jogadorDto.getUrlId() : null;
        this.url = jogadorDto.getUrl() != null ? jogadorDto.getUrl() : null;
        this.nome = jogadorDto.getName() != null ? jogadorDto.getUrl() : null;
        this.username = jogadorDto.getUsername();
        this.seguidores = jogadorDto.getFollowers();
        this.localidade = jogadorDto.getLocation() != null ? jogadorDto.getLocation() : null;
        this.pais = jogadorDto.getCountry() != null ? jogadorDto.getCountry() : null;
        this.ultimaVezOnline = UtilData.getDateFromTimestamp(Long.parseLong(jogadorDto.getUltimaVezOnline()));
        this.dataRegistroPlataforma = UtilData.getDateFromTimestamp(Long.parseLong(jogadorDto.getJoined()));
        this.statusConta = UtilMetodo.retornarStatusContaPlataforma(jogadorDto.getStatus());
        this.titulo = jogadorDto.getTitle() != null ? jogadorDto.getTitle() : null;
        this.fide = jogadorDto.getFide();
        this.streamer = jogadorDto.isStreamer();
        this.twitchUrl = jogadorDto.getTwitchUrl() != null ? jogadorDto.getTwitchUrl() : null;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public TipoPlataforma getTipo() {
        return tipo;
    }

    public void setTipo(TipoPlataforma tipo) {
        this.tipo = tipo;
    }

    public long getJogadorId() {
        return jogadorId;
    }

    public void setJogadorId(long jogadorId) {
        this.jogadorId = jogadorId;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(long seguidores) {
        this.seguidores = seguidores;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getUltimaVezOnline() {
        return ultimaVezOnline;
    }

    public void setUltimaVezOnline(Date ultimaVezOnline) {
        this.ultimaVezOnline = ultimaVezOnline;
    }

    public Date getDataRegistroPlataforma() {
        return dataRegistroPlataforma;
    }

    public void setDataRegistroPlataforma(Date dataRegistroPlataforma) {
        this.dataRegistroPlataforma = dataRegistroPlataforma;
    }

    public StatusContaPlataforma getStatusConta() {
        return statusConta;
    }

    public void setStatusConta(StatusContaPlataforma statusConta) {
        this.statusConta = statusConta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getFide() {
        return fide;
    }

    public void setFide(int fide) {
        this.fide = fide;
    }

    public boolean isStreamer() {
        return streamer;
    }

    public void setStreamer(boolean streamer) {
        this.streamer = streamer;
    }

    public Date getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public String getTwitchUrl() {
        return twitchUrl;
    }

    public void setTwitchUrl(String twitchUrl) {
        this.twitchUrl = twitchUrl;
    }

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
        if (this.dataUltimaAtualizacao == null) {
            this.dataUltimaAtualizacao = new Date();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Jogador jogador = (Jogador) o;
        return getJogadorId() == jogador.getJogadorId() && getSeguidores() == jogador.getSeguidores() && getFide() == jogador.getFide() && isStreamer() == jogador.isStreamer() && Objects.equals(getAvatarUrl(), jogador.getAvatarUrl()) && getTipo() == jogador.getTipo() && Objects.equals(getUrlId(), jogador.getUrlId()) && Objects.equals(getUrl(), jogador.getUrl()) && Objects.equals(getNome(), jogador.getNome()) && Objects.equals(getUsername(), jogador.getUsername()) && Objects.equals(getLocalidade(), jogador.getLocalidade()) && Objects.equals(getPais(), jogador.getPais()) && Objects.equals(getUltimaVezOnline(), jogador.getUltimaVezOnline()) && Objects.equals(getDataRegistroPlataforma(), jogador.getDataRegistroPlataforma()) && getStatusConta() == jogador.getStatusConta() && Objects.equals(getTitulo(), jogador.getTitulo()) && Objects.equals(getDataUltimaAtualizacao(), jogador.getDataUltimaAtualizacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAvatarUrl(), getTipo(), getJogadorId(), getUrlId(), getUrl(), getNome(), getUsername(), getSeguidores(), getLocalidade(), getPais(), getUltimaVezOnline(), getDataRegistroPlataforma(), getStatusConta(), getTitulo(), getFide(), isStreamer(), getDataUltimaAtualizacao());
    }
}
