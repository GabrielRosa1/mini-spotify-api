package com.insper.mini_spotify_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;


@RestController
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @PostMapping("/artistas")
    public ResponseEntity<Object> criarArtista(@RequestBody Artista artista) {
        try {
            Artista m = artistaService.criarArtista(artista);
            return ResponseEntity.status(HttpStatus.CREATED).body(m);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/artistas")
    public ResponseEntity<Collection<Artista>> getArtistas() {

        Collection<Artista> totalArtistas = artistaService.getArtistas();
        return ResponseEntity.ok(totalArtistas);

    }

    @GetMapping("/artistas/{id}")
    public ResponseEntity<Object> getArtista(@PathVariable UUID id) {
        try {
            Artista artista = artistaService.getArtista(id);
            return ResponseEntity.ok(artista);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/artistas/{id}")
    public ResponseEntity<Object> editArtista(@PathVariable UUID id, @RequestBody Artista dadosAtualizados) {
        try {
            Artista artista = artistaService.editArtista(id, dadosAtualizados);
            return ResponseEntity.ok(artista);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/artistas/{id}")
    public ResponseEntity<Object> deleteArtista(@PathVariable UUID id) {
        try {
            artistaService.deleteArtista(id);
            return ResponseEntity.noContent().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/artistas/reativar/{id}")
    public ResponseEntity<Object> reactivateArtista(@PathVariable UUID id) {
        try {
            Artista artista = artistaService.reactivateArtista(id);
            return ResponseEntity.ok(artista);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
