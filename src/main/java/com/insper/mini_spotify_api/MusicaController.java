package com.insper.mini_spotify_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;


@RestController
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @PostMapping("/musicas")
    public ResponseEntity<Object> criarMusica(@RequestBody Musica musica) {
        try {
            Musica m = musicaService.criarMusica(musica);
            return ResponseEntity.status(HttpStatus.CREATED).body(m);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/musicas")
    public ResponseEntity<Collection<Musica>> getMusicas() {

        Collection<Musica> totalMusicas = musicaService.getMusicas();
        return ResponseEntity.ok(totalMusicas);

    }

    @GetMapping("/musicas/{id}")
    public ResponseEntity<Object> getMusica(@PathVariable UUID id) {
        try {
            Musica musica = musicaService.getMusica(id);
            return ResponseEntity.ok(musica);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/musicas/{id}")
    public ResponseEntity<Object> editMusica(@PathVariable UUID id, @RequestBody Musica dadosAtualizados) {
        try {
            Musica musica = musicaService.editMusica(id, dadosAtualizados);
            return ResponseEntity.ok(musica);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/musicas/{id}")
    public ResponseEntity<Object> deleteMusica(@PathVariable UUID id) {
        try {
            musicaService.deleteMusica(id);
            return ResponseEntity.noContent().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/musicas/reativar/{id}")
    public ResponseEntity<Object> reactivateMusica(@PathVariable UUID id) {
        try {
            Musica musica = musicaService.reactivateMusica(id);
            return ResponseEntity.ok(musica);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
