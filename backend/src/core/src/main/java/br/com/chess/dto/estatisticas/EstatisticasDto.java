package br.com.chess.dto.estatisticas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstatisticasDto {
    @JsonProperty("chess_daily")
    private ModalidadeDto chessDaily;

    @JsonProperty("chess_rapid")
    private ModalidadeDto chessRapid;

    @JsonProperty("chess_bullet")
    private ModalidadeDto chessBullet;

    @JsonProperty("chess960_daily")
    private ModalidadeDto chess960Daily;

    @JsonProperty("chess_blitz")
    private ModalidadeDto chessBlitz;

    private long fide;

    private EstudoDto tactics;

    private EstudoDto lessons;

    @JsonProperty("puzzle_rush")
    private PuzzleRushDto puzzle;

    public EstatisticasDto() {
        // Construtor padrao
    }


    public ModalidadeDto getChessDaily() {
        return chessDaily;
    }

    public void setChessDaily(ModalidadeDto chessDaily) {
        this.chessDaily = chessDaily;
    }

    public ModalidadeDto getChessRapid() {
        return chessRapid;
    }

    public void setChessRapid(ModalidadeDto chessRapid) {
        this.chessRapid = chessRapid;
    }

    public ModalidadeDto getChessBullet() {
        return chessBullet;
    }

    public void setChessBullet(ModalidadeDto chessBullet) {
        this.chessBullet = chessBullet;
    }

    public ModalidadeDto getChess960Daily() {
        return chess960Daily;
    }

    public void setChess960Daily(ModalidadeDto chess960Daily) {
        this.chess960Daily = chess960Daily;
    }

    public ModalidadeDto getChessBlitz() {
        return chessBlitz;
    }

    public void setChessBlitz(ModalidadeDto chessBlitz) {
        this.chessBlitz = chessBlitz;
    }

    public long getFide() {
        return fide;
    }

    public void setFide(long fide) {
        this.fide = fide;
    }

    public EstudoDto getTactics() {
        return tactics;
    }

    public void setTactics(EstudoDto tactics) {
        this.tactics = tactics;
    }

    public EstudoDto getLessons() {
        return lessons;
    }

    public void setLessons(EstudoDto lessons) {
        this.lessons = lessons;
    }

    public PuzzleRushDto getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(PuzzleRushDto puzzle) {
        this.puzzle = puzzle;
    }
}
