package com.opticamarcos.configuration;

import com.opticamarcos.model.entity.Usuario;
import com.opticamarcos.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Usuario usuario = usuarioRepository.findByUserName("Administrador");

        if(usuario != null)
            return;

        usuario = new Usuario();
        usuario.setName("Administrador");
        usuario.setUserName("administrador");
        usuario.setUserPassword("AnaliaCANCH");
        usuario.setEnabled(true);

        usuarioRepository.save(usuario);
    }

}
