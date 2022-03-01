package br.com.chess.domain.estaticas;

import br.com.chess.UtilData;
import br.com.chess.domain.BaseDomain;
import br.com.chess.dto.estatisticas.BaseEstatisticasDto;
import br.com.chess.dto.estatisticas.EstatisticaGameDto;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Audited
@Table(name="estatistica_modalidade")
public class EstatisticaModalidade extends BaseDomain {

    private static final long serialVersionUID = 8291965229903074856L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data", nullable = false)
    private Date data;

    @Column(name = "rating", nullable = false)
    private long rating;

    @Column(name ="rd", nullable = true)
    private long rd;

    @Column(name ="game")
    private String game;


    public EstatisticaModalidade() {
        // Construtor padrão
    }

    public EstatisticaModalidade( EstatisticaGameDto estatisticaGameDto) {
        super();
        this.data = UtilData.getDateFromTimestamp(estatisticaGameDto.getDate());
        this.rating = estatisticaGameDto.getRating();
        this.rd = estatisticaGameDto.getRd();
        this.game = estatisticaGameDto.getGame() != null ? estatisticaGameDto.getGame() : null;
    }

    public EstatisticaModalidade(BaseEstatisticasDto baseEstatistDto) {
        super();
        this.data = UtilData.getDateFromTimestamp(baseEstatistDto.getDate());
        this.rating = baseEstatistDto.getRating();
    }


    /**
     * get field @Temporal(TemporalType.TIMESTAMP)
     @Column(name = "data", nullable = false)

      *
      * @return data @Temporal(TemporalType.TIMESTAMP)
     @Column(name = "data", nullable = false)

     */
    public Date getData() {
        return this.data;
    }

    /**
     * set field @Temporal(TemporalType.TIMESTAMP)
     @Column(name = "data", nullable = false)

      *
      * @param data @Temporal(TemporalType.TIMESTAMP)
     @Column(name = "data", nullable = false)

     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * get field @Column(name = "rating", nullable = false)
     *
     * @return rating @Column(name = "rating", nullable = false)

     */
    public long getRating() {
        return this.rating;
    }

    /**
     * set field @Column(name = "rating", nullable = false)
     *
     * @param rating @Column(name = "rating", nullable = false)

     */
    public void setRating(long rating) {
        this.rating = rating;
    }

    /**
     * get field @Column(name ="rd", nullable = true)
     *
     * @return rd @Column(name ="rd", nullable = true)

     */
    public long getRd() {
        return this.rd;
    }

    /**
     * set field @Column(name ="rd", nullable = true)
     *
     * @param rd @Column(name ="rd", nullable = true)

     */
    public void setRd(long rd) {
        this.rd = rd;
    }

    /**
     * get field @Column(name ="game")
     *
     * @return game @Column(name ="game")

     */
    public String getGame() {
        return this.game;
    }

    /**
     * set field @Column(name ="game")
     *
     * @param game @Column(name ="game")

     */
    public void setGame(String game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EstatisticaModalidade that = (EstatisticaModalidade) o;
        return getRating() == that.getRating() && getRd() == that.getRd() && Objects.equals(getData(), that.getData()) && Objects.equals(getGame(), that.getGame());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getData(), getRating(), getRd(), getGame());
    }
}
