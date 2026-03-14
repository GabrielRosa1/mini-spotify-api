package com.insper.mini_spotify_api;

import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ArtistaService {

    private final AlbumService albumService;
    private final HashMap<UUID, Artista> artistas = new HashMap<>();

    public ArtistaService(AlbumService albumService) {
        this.albumService = albumService;
    }

    //POST /artistas
    public Artista criarArtista(Artista artista) {

        if (artista == null) {
            throw new RuntimeException("Body inválido");
        }

        if (artista.getNome().isBlank() || artista.getNome() == null) {
            throw new RuntimeException("Nome do artista é obrigatório");
        }

        if (artista.getGeneroMusical().isBlank() || artista.getGeneroMusical() == null) {
            throw new RuntimeException("Gênero musical do artista é obrigatório");
        }

        if (artista.getPaisOrigem().isBlank() || artista.getPaisOrigem() == null) {
            throw new RuntimeException("País de origem do artista é obrigatório");
        }

        List<Album> albunsReais = new ArrayList<>();
        if (artista.getAlbuns() != null) {
            List<Album> albuns = artista.getAlbuns();
            for (Album a : albuns) {
                for (Album b : albumService.getAlbuns().values()) {
                    if (a.getId() == b.getId()) {
                        albunsReais.add(a);
                    }
                }
            }
        }

        artista.setId(UUID.randomUUID());
        artista.setAtivo(true);
        artista.setAlbuns(albunsReais);

        artistas.put(artista.getId(), artista);
        return artista;
    }

    //GET /artistas
    public Collection<Artista> getArtistas() {

        Collection<Artista> totalArtistas = new ArrayList<>();

        for (Artista a : artistas.values()) {
            if (a.getNome() != null && a.isAtivo()) {
                totalArtistas.add(a);
            }
        }

        return totalArtistas;
    }

    //GET /artistas/{id}
    public Artista getArtista(UUID id) {

        Artista artista = artistas.get(id);

        if (artista == null || !artista.isAtivo()) {
            throw new RuntimeException("Esse artista não existe");
        }

        return artista;

    }

    //PUT /artistas/{id}
    public Artista editArtista(UUID id, Artista dadosAtualizados) {

        Artista artista = artistas.get(id);

        if (artista == null || !artista.isAtivo()) {
            throw new RuntimeException("Essa artista não existe ou não pode ser modificada");
        }

        if (dadosAtualizados.getNome().isBlank() || dadosAtualizados.getNome() == null) {
            throw new RuntimeException("Nome do artista é obrigatório");
        }

        if (dadosAtualizados.getGeneroMusical().isBlank() || dadosAtualizados.getGeneroMusical() == null) {
            throw new RuntimeException("Gênero musical do artista é obrigatório");
        }

        if (dadosAtualizados.getPaisOrigem().isBlank() || dadosAtualizados.getPaisOrigem() == null) {
            throw new RuntimeException("País de origem do artista é obrigatório");
        }

        for (Artista a : artistas.values()) {
            if (a.getNome().equalsIgnoreCase(dadosAtualizados.getNome())) {
                throw new RuntimeException("Já existe um artista com esse nome");
            }
        }

        List<Album> albunsReaisUpdate = new ArrayList<>();
        if (dadosAtualizados.getAlbuns() != null) {
            List<Album> albuns = dadosAtualizados.getAlbuns();
            for (Album a : albuns) {
                for (Album b : albumService.getAlbuns().values()) {
                    if (a.getId() == b.getId()) {
                        albunsReaisUpdate.add(a);
                    }
                }
            }
        }

        artista.setNome(dadosAtualizados.getNome());
        artista.setGeneroMusical(dadosAtualizados.getGeneroMusical());
        artista.setPaisOrigem(dadosAtualizados.getPaisOrigem());
        artista.setAlbuns(albunsReaisUpdate);

        return artista;
    }

    //DELETE /artistas/{id}
    public void deleteArtista(UUID id) {
        Artista artista = artistas.get(id);

        if (artista == null || !artista.isAtivo()) {
            throw new RuntimeException("Essa artista não existe ou não pode ser modificada");
        }

        artista.setAtivo(false);
    }

    //PUT /artistas/{id}
    public Artista reactivateArtista(UUID id) {
        Artista artista = artistas.get(id);

        if (artista == null) {
            throw new RuntimeException("Esse artista não existe ou não pode ser modificado");
        }

        if (artista.isAtivo()) {
            throw new RuntimeException("Esse artista já está ativado");
        }

        artista.setAtivo(true);
        return artista;
    }

    public boolean verifyUUID(UUID id) {
        for (Artista a : artistas.values()) {
            if (a.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

}
