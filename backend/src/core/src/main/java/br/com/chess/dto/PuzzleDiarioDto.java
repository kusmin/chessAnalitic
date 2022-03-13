package br.com.chess.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class PuzzleDiarioDto {
    private String title;
    private String url;
    @JsonProperty("publish_time")
    private String dataPublicacao;
    private String fen;
    private String pgn;
    private String image;

    public PuzzleDiarioDto() {
        // Construtor padrao
    }

    public PuzzleDiarioDto(String titulo, String url, String dataPublicacao, String fen, String pgn, String image) {
        this.title = titulo;
        this.url = url;
        this.dataPublicacao = dataPublicacao;
        this.fen = fen;
        this.pgn = pgn;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public String getFen() {
        return fen;
    }

    public String getPgn() {
        return pgn;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public void setFen(String fen) {
        this.fen = fen;
    }

    public void setPgn(String pgn) {
        this.pgn = pgn;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuzzleDiarioDto entity = (PuzzleDiarioDto) o;
        return Objects.equals(this.title, entity.title) &&
                Objects.equals(this.url, entity.url) &&
                Objects.equals(this.dataPublicacao, entity.dataPublicacao) &&
                Objects.equals(this.fen, entity.fen) &&
                Objects.equals(this.pgn, entity.pgn) &&
                Objects.equals(this.image, entity.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url, dataPublicacao, fen, pgn, image);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "titulo = " + title + ", " +
                "url = " + url + ", " +
                "dataPublicacao = " + dataPublicacao + ", " +
                "fen = " + fen + ", " +
                "pgn = " + pgn + ", " +
                "image = " + image + ")";
    }
}
