package br.com.alura.musics.model;

public enum TipoArtista {
    SOLO("solo"),
    DUPLA("dupla"),
    BANDA("banda");

    private String tipoArtista;

    TipoArtista(String tipoArtista) {
        this.tipoArtista = tipoArtista;
    }

    public static TipoArtista fromUser(String text) {
        for (TipoArtista variacao : TipoArtista.values()) {
            if (variacao.tipoArtista.equalsIgnoreCase(text)) {
                return variacao;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
