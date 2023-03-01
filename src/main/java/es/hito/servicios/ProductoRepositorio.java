package es.hito.servicios;

import es.hito.jpa.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {

    Object findByCategoriaIdcategoria(int id);

}
