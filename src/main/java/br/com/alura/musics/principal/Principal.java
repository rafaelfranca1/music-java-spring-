package br.com.alura.musics.principal;

import br.com.alura.musics.model.Artista;
import br.com.alura.musics.model.Musica;
import br.com.alura.musics.model.TipoArtista;
import br.com.alura.musics.repository.ArtistaRepository;
import br.com.alura.musics.service.ConsultaChatGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ArtistaRepository repositorio;

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                    \n*** Screen Sound Musicas ***
                    
                    1 - Cadastrar artistas
                    2 - Cadastrar músicas
                    3 - Listar músicas
                    4 - Buscar músicas por artistas
                    5 - Pesquisar dados sobre um artista
                    0 - Sair
                    
                    opcao:\s""";

            System.out.print(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    pesquisarDadosDoArtista();
                    break;
                case 0:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarArtistas() {
        var continuar = "s";
        while (continuar.equalsIgnoreCase("S")){
            System.out.print("Informe o nome do artista: ");
            var nomeArtista = scanner.nextLine();

            System.out.print("Informe o tipo do artista (solo, dupla, banda): ");
            var tipoArtista = scanner.nextLine();
            var tipo = TipoArtista.fromUser(tipoArtista);

            Artista artista = new Artista(nomeArtista, tipo);
            repositorio.save(artista);

            System.out.print("\nArtista cadastrado com sucesso!\nDeseja cadastrar outro artista? (S/N): ");
            continuar = scanner.nextLine();
        }
    }

    private void cadastrarMusicas() {
        System.out.print("Informe o nome do artista da música que você quer cadastrar: ");
        var nomeArtista = scanner.nextLine();

        Optional<Artista> artista = repositorio.buscarArtistaPorNome(nomeArtista);

        if (artista.isPresent()) {
            Artista artistaEncontrado = artista.get();

            System.out.print("Informe o nome da música: ");
            var nomeMusica = scanner.nextLine();

            Musica musica = new Musica(nomeMusica, artistaEncontrado);
            artistaEncontrado.getMusicas().add(musica);

            repositorio.save(artistaEncontrado);
        } else {
            System.out.println("Artista nao encontrado");
        }
    }

    private void listarMusicas() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(System.out::println);
    }

    private void buscarMusicasPorArtista() {
        System.out.print("Informe o nome do artista para ver suas músicas: ");
        var nomeArtista = scanner.nextLine();

        Optional<Artista> artista = repositorio.buscarArtistaPorNome(nomeArtista);

        if (artista.isPresent()) {
            Artista artistaEncontrado = artista.get();
            System.out.println("Artista: " + artistaEncontrado.getNome());

            List<Musica> musicas = repositorio.buscarMusicasPorArtistas(artistaEncontrado.getNome());
            musicas.forEach(System.out::println);
        }
    }

    private void pesquisarDadosDoArtista() {
        System.out.print("Pesquise por um artista: ");
        var nomeArtista = scanner.nextLine();

        System.out.println("Informações sobre o artista: " + ConsultaChatGPT.obterDadosArtistas(nomeArtista));
    }
}
