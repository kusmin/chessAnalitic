package br.com.chess.domain;

import br.com.chess.domain.enums.TipoMaestria;
import br.com.chess.domain.enums.TipoPlataforma;
import br.com.chess.dto.PlayersTituladosDto;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Audited
@Table(name = "lista_titulados", indexes = {@Index(unique = true, columnList = "tipo, titulo")})
public class ListaJogadoresTitulados extends BasePlataforma {

    private static final long serialVersionUID = -6342446922137796866L;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> jogadores;

    public ListaJogadoresTitulados() {
        // Construtor padrao
    }

    public ListaJogadoresTitulados(TipoMaestria titulo, TipoPlataforma plataforma, PlayersTituladosDto playersTituladosDto) {
        super(plataforma, titulo);
        this.jogadores = new HashSet<>(playersTituladosDto.getPlayers());
    }

    public Set<String> getJogadores() {
        return jogadores;
    }

    public void setJogadores(Set<String> jogadores) {
        this.jogadores = jogadores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ListaJogadoresTitulados that = (ListaJogadoresTitulados) o;
        return Objects.equals(getJogadores(), that.getJogadores());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getJogadores());
    }
}
