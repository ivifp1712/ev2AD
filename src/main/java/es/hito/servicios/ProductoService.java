package es.hito.servicios;

import es.hito.jpa.Producto;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class ProductoService {
    private ProductoRepositorio productos;

    public ProductoService(ProductoRepositorio productos) {
        this.productos = productos;
    }

    public void guardarProducto(Producto producto) {
        productos.save(producto);
    }

    public void borrarProductoById(int id) {
        productos.deleteById(id);
    }

    public Object listarProductos() {
        return productos.findAll();
    }

    public Producto buscarProductoById(int id) {
        return productos.findById(id).get();
    }

    public void actualizarProducto(Producto producto) {
        productos.save(producto);
    }

    // productos por categoria
    public Object listarProductosPorCategoria(int id) {
        return productos.findByCategoriaIdcategoria(id);
    }
}
