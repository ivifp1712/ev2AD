package es.hito.servicios;

import es.hito.jpa.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class UsuarioService {
    private UsuarioRepositorio usuarios;

    public UsuarioService(UsuarioRepositorio usuarios) {
        this.usuarios = usuarios;
    }

    public void guardarUsuario(Usuario usuario) {
        usuarios.save(usuario);
    }

    public void borrarUsuarioById(int id) {
        usuarios.deleteById(id);
    }

    public Object listarUsuarios() {
        return usuarios.findAll();
    }

    public Usuario buscarUsuarioById(String id) {
        return usuarios.findByEmail(id);
    }


}
