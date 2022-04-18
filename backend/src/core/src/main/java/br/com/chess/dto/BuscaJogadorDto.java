package br.com.chess.dto;

public class BuscaJogadorDto extends BuscaDto {

    private String title;

    private String type;

    private String username;

    private Boolean streamer;

    private String status;

    private String nome;

    private Integer fideFinal;

    private Integer fideInicial;

    private String dataRegistroInicial;

    private String dataRegistroFinal;

    public BuscaJogadorDto(int page, int pageSize, String title, String type, String username, boolean streamer, String status, String nome, int fideFinal, int fideInicial, String dataRegistroInicial, String dataRegistroFinal) {
        super(page, pageSize);
        this.title = title;
        this.type = type;
        this.username = username;
        this.streamer = streamer;
        this.status = status;
        this.nome = nome;
        this.fideFinal = fideFinal;
        this.fideInicial = fideInicial;
        this.dataRegistroInicial = dataRegistroInicial;
        this.dataRegistroFinal = dataRegistroFinal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean isStreamer() {
        return streamer;
    }

    public void setStreamer(Boolean streamer) {
        this.streamer = streamer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getFideFinal() {
        return fideFinal;
    }

    public void setFideFinal(Integer fideFinal) {
        this.fideFinal = fideFinal;
    }

    public Integer getFideInicial() {
        return fideInicial;
    }

    public void setFideInicial(Integer fideInicial) {
        this.fideInicial = fideInicial;
    }

    public String getDataRegistroInicial() {
        return dataRegistroInicial;
    }

    public void setDataRegistroInicial(String dataRegistroInicial) {
        this.dataRegistroInicial = dataRegistroInicial;
    }

    public String getDataRegistroFinal() {
        return dataRegistroFinal;
    }

    public void setDataRegistroFinal(String dataRegistroFinal) {
        this.dataRegistroFinal = dataRegistroFinal;
    }
}
