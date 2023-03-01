package es.hito.servicios;

import es.hito.jpa.Categoria;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class CategoriaService {
    private CategoriaRepositorio categorias;

    public CategoriaService(CategoriaRepositorio categorias) {
        this.categorias = categorias;
    }

    public void guardarCategoria(Categoria categoria) {
        categorias.save(categoria);
    }

    public void borrarCategoriaById(int id) {
        categorias.deleteById(id);
    }

}
