package com.opticamarcosweb.service;

import com.opticamarcosweb.model.Ficha;
import com.opticamarcosweb.model.Usuario;
import com.opticamarcosweb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario addUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}
