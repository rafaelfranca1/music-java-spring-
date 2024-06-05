package br.com.alura.musics.repository;

import br.com.alura.musics.model.Artista;
import br.com.alura.musics.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository  extends JpaRepository<Artista, Long> {

    @Query("select a from Artista a where a.nome ilike :nomeArtista")
    Optional<Artista> buscarArtistaPorNome(String nomeArtista);

    @Query("select a.musicas from Artista a where a.nome ilike :nomeArtista")
    List<Musica> buscarMusicasPorArtistas(String nomeArtista);
}
