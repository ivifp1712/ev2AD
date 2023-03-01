package es.hito.jpa;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="categoria")
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private int idcategoria;

    private String categoria;

    @OneToMany(mappedBy="categoria")
    private List<Producto> productos;

    public Categoria() {
    }

    public int getIdcategoria() {
        return this.idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Producto> getProductos() {
        return this.productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Producto addProducto(Producto producto) {
        getProductos().add(producto);
        producto.setCategoria(this);
        return producto;
    }

    public Producto removeProducto(Producto producto) {
        getProductos().remove(producto);
        producto.setCategoria(null);

        return producto;
    }



}