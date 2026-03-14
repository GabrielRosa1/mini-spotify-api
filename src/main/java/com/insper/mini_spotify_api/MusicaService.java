package com.insper.mini_spotify_api;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;


@Service
public class MusicaService {

    private final HashMap<UUID, Musica> musicas = new HashMap<>();


    //POST /musicas
    public Musica criarMusica(Musica musica) {

        if (musica == null) {
            throw new RuntimeException("Body inválido");
        }

        if (musica.getTitulo() == null || musica.getTitulo().isBlank()) {
            throw new RuntimeException("Titulo da musica é obrigatório");
        }

        if (musica.getArtista() == null) {
            throw new RuntimeException("Artista é obrigatório");
        }

        if (musica.getAlbum() == null) {
            throw new RuntimeException("Album é obrigatório");
        }

        if (!albumService.verifyUUID(musica.getAlbum().getId())) {
            throw new RuntimeException("Id de um album válido é obrigatório");
        }

        if (!artistaService.verifyUUID(musica.getArtista().getId())) {
            throw new RuntimeException("Id de um artista válido é obrigatório");
        }

        if (musica.getNumeroFaixa() < 0 || musica.getDuracaoSegundos() < 0) {
            throw new RuntimeException("Campos numeroFaixa e duracaoSegundos devem ter valores reais");
        }

        for (Musica m : musicas.values()) {
            if (m.getTitulo().equalsIgnoreCase(musica.getTitulo())) {
                throw new RuntimeException("Já existe uma musica com esse título");
            }
        }

        musica.setId(UUID.randomUUID());
        musica.setAtivo(true);
        musica.setTotalReproducoes(0);

        musicas.put(musica.getId(), musica);
        return musica;

    }

    //GET /musicas
    public Collection<Musica> getMusicas() {

        Collection<Musica> totalMusicas = new ArrayList<>();

        for (Musica m : musicas.values()) {
            if (m.getTitulo() != null && m.isAtivo()) {
                totalMusicas.add(m);
            }
        }

        return totalMusicas;
    }

    //GET /musicas/{id}
    public Musica getMusica(UUID id) {

        Musica musica = musicas.get(id);

        if (musica == null || !musica.isAtivo()) {
            throw new RuntimeException("Essa musica não existe");
        }

        return musica;

    }

    //PUT /musicas/{id}
    public Musica editMusica(UUID id, Musica dadosAtualizados) {

        Musica musica = musicas.get(id);

        if (musica == null || !musica.isAtivo()) {
            throw new RuntimeException("Essa musica não existe ou não pode ser modificada");
        }

        if (dadosAtualizados.getTitulo() == null || dadosAtualizados.getTitulo().isBlank()) {
            throw new RuntimeException("Titulo da musica é obrigatório");
        }

        if (dadosAtualizados.getArtista() == null) {
            throw new RuntimeException("Artista é obrigatório");
        }

        if (dadosAtualizados.getAlbum() == null) {
            throw new RuntimeException("Album é obrigatório");
        }

        if (!albumService.verifyUUID(dadosAtualizados.getAlbum().getId())) {
            throw new RuntimeException("Id de um album válido é obrigatório");
        }

        if (!artistaService.verifyUUID(dadosAtualizados.getArtista().getId())) {
            throw new RuntimeException("Id de um artista válido é obrigatório");
        }

        if (dadosAtualizados.getNumeroFaixa() < 0 || dadosAtualizados.getDuracaoSegundos() < 0) {
            throw new RuntimeException("Campos numeroFaixa e duracaoSegundos devem ter valores reais");
        }

        for (Musica m : musicas.values()) {
            if (m.getTitulo().equalsIgnoreCase(dadosAtualizados.getTitulo())) {
                throw new RuntimeException("Já existe uma musica com esse título");
            }
        }

        musica.setTitulo(dadosAtualizados.getTitulo());
        musica.setAlbum(dadosAtualizados.getAlbum());
        musica.setArtista(dadosAtualizados.getArtista());
        musica.setNumeroFaixa(dadosAtualizados.getNumeroFaixa());
        musica.setDuracaoSegundos(dadosAtualizados.getDuracaoSegundos());

        return musica;

    }

    //DELETE /musicas/{id}
    public void deleteMusica(UUID id) {
        Musica musica = musicas.get(id);

        if (musica == null || !musica.isAtivo()) {
            throw new RuntimeException("Essa musica não existe ou não pode ser modificada");
        }

        musica.setAtivo(false);
    }

    //PUT /musicas/{id}
    public Musica reactivateMusica(UUID id) {
        Musica musica = musicas.get(id);

        if (musica == null) {
            throw new RuntimeException("Essa musica não existe ou não pode ser modificada");
        }

        if (musica.isAtivo()) {
            throw new RuntimeException("Essa musica já está ativa");
        }

        musica.setAtivo(true);
        return musica;
    }

}
