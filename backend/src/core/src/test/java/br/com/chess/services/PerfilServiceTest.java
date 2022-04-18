package br.com.chess.services;

import br.com.chess.domain.Perfil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PerfilServiceTest {

    @Autowired
    private PerfilService perfilService;

    @Test
    void testFind() {
        List<Perfil> perfis = perfilService.find(null, null, null, null);
        assertNotNull(perfis, "Retornou uma lista nula");
        assertFalse(perfis.isEmpty(), "Retornou uma lista vazia. Deveria retornar todos os perfis");
    }

}
