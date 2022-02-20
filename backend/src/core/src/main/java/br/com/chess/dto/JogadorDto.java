package br.com.chess.dto;

import br.com.chess.UtilData;
import br.com.chess.domain.Jogador;
import br.com.chess.domain.TipoMaestria;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class JogadorDto {

    private String avatar;

    @NotNull(message = "Deve ser fornecida a plataforma do jogador")
    @NotBlank(message = "Deve ser fornecida a plataforma do jogador")
    private String tipo;

    @JsonProperty("player_id")
    private long jogadorId;

    @JsonProperty("@id")
    private String urlId;

    private String url;

    private String name;

    @NotNull(message = "Deve ser fornecida o username do jogador")
    @NotBlank(message = "Deve ser fornecida o username do jogador")
    private String username;


    private long followers;


    private String location;


    private String country;

    @JsonProperty("last_online")
    private String ultimaVezOnline;

    private String joined;

    private String status;

    private String title;

    private int fide;

    @JsonProperty("is_streamer")
    private boolean streamer;

    @JsonProperty("twitch_url")
    private String twitchUrl;

    public JogadorDto() {
        // Construtor padrao
    }

    public JogadorDto(Jogador jogador) {
        this.avatar = jogador.getAvatarUrl() != null ? jogador.getAvatarUrl():null;
        this.tipo = jogador.getTipo().name();
        this.jogadorId = jogador.getJogadorId();
        this.urlId = jogador.getUrlId() != null ? jogador.getUrlId() : null;
        this.url = jogador.getUrl() != null ? jogador.getUrl() : null;
        this.name = jogador.getNome() != null ? jogador.getNome() : null;
        this.username = jogador.getUsername();
        this.followers = jogador.getSeguidores();
        this.location = jogador.getLocalidade() != null ? jogador.getLocalidade() : null;
        this.country = jogador.getPais() != null ? jogador.getPais() : null;
        this.ultimaVezOnline = UtilData.formatoDataCompletaParticipante().format(jogador.getUltimaVezOnline());
        this.joined = UtilData.formatoDataCompletaParticipante().format(jogador.getDataRegistroPlataforma());
        this.status = jogador.getStatusConta().toString();
        this.title = jogador.getTitulo() != null ? jogador.getTitulo().toString() : null;
        this.fide = jogador.getFide();
        this.streamer = jogador.isStreamer();
        this.twitchUrl = jogador.getTwitchUrl() != null ? jogador.getTwitchUrl() : null;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUltimaVezOnline() {
        return ultimaVezOnline;
    }

    public void setUltimaVezOnline(String ultimaVezOnline) {
        this.ultimaVezOnline = ultimaVezOnline;
    }

    public String getJoined() {
        return joined;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getTwitchUrl() {
        return twitchUrl;
    }

    public void setTwitchUrl(String twitchUrl) {
        this.twitchUrl = twitchUrl;
    }
}
