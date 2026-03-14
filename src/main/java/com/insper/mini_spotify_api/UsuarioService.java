package com.insper.mini_spotify_api;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Service
public class UsuarioService {

    private HashMap<UUID, Usuario> usuarios = new HashMap<>();

    //Espaço para @Autowired

    //POST /usuarios
    public Usuario cadastrarUsuario(Usuario usuario) {

        for (Usuario u : usuarios.values()) {
            if (u.getEmail().equals(usuario.getEmail())) {
                throw new RuntimeException("Um usuário com esse e-mail já existe");
            }
        }

        if (usuario.getNome() == null || usuario.getNome().isBlank() || usuario.getEmail() == null
                || usuario.getEmail().isBlank() || usuario.getTipoPlano() == null) {
            throw new RuntimeException("Dados inválidos");
        }

        usuario.setId(UUID.randomUUID());
        usuario.setAtivo(true);
        usuario.setDataCriacao(LocalDateTime.now());

        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    //GET /usuarios
    public Collection<Usuario> getUsuarios() {

        Collection<Usuario> totalUsuarios = new ArrayList<>();

        for (Usuario u : usuarios.values()) {
            if (u.getEmail() != null && u.isAtivo()) {
                totalUsuarios.add(u);
            }
        }

        return totalUsuarios;
    }

    //GET /usuarios/{id}
    public Usuario getUsuario(UUID id) {

        Usuario usuario = usuarios.get(id);

        if (usuario == null || !usuario.isAtivo()) {
            throw new RuntimeException("Esse usuário não existe ou está inativo");
        }

        return usuario;

    }

    //PUT /usuarios/{id}
    public Usuario editUsuario(UUID id, Usuario dadosAtualizados) {

        Usuario usuario = usuarios.get(id);

        if (usuario == null || !usuario.isAtivo()) {
            throw new RuntimeException("Esse usuário não existe ou não pode ser modificado");
        }

        if (dadosAtualizados.getNome() == null || dadosAtualizados.getNome().isBlank() || dadosAtualizados.getEmail() == null
                || dadosAtualizados.getEmail().isBlank() || dadosAtualizados.getTipoPlano() == null) {
            throw new RuntimeException("Dados inválidos");
        }

        usuario.setNome(dadosAtualizados.getNome());
        usuario.setEmail(dadosAtualizados.getEmail());
        usuario.setTipoPlano(dadosAtualizados.getTipoPlano());

        return usuario;

    }

    //DELETE /usuarios/{id}
    public void deleteUsuario(UUID id) {
        Usuario usuario = usuarios.get(id);

        if (usuario == null || !usuario.isAtivo()) {
            throw new RuntimeException("Esse usuário não existe ou não pode ser modificado");
        }

        usuario.setAtivo(false);
    }

    //PUT /usuarios/{id}
    public Usuario reactivateUsuario(UUID id) {
        Usuario usuario = usuarios.get(id);

        if (usuario == null) {
            throw new RuntimeException("Esse usuário não existe ou não pode ser modificado");
        }

        if (usuario.isAtivo()) {
            throw new RuntimeException("Esse usuário já está ativo");
        }

        usuario.setAtivo(true);
        return usuario;
    }

    public boolean verifyUUID(UUID id) {
        for (Usuario u : usuarios.values()) {
            if (u.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

}
