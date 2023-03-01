package es.hito.control;

import es.hito.jpa.Producto;
import es.hito.jpa.Usuario;
import es.hito.servicios.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class Controlador {
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UsuarioService usuarios;

    @Autowired
    ProductoService productos;

    @Autowired
    CategoriaService categorias;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    @RequestMapping("/")
    public ModelAndView peticionRaiz(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if(aut==null) {
            mv.addObject("user", "anonimo");
        }
        else {
            mv.addObject("user", aut.getName());
            System.out.println("Usuario: " + aut.getName());
            System.out.println();
            // buscar usuario por nombre
            Usuario usuario = usuarioRepositorio.findByEmail(aut.getName());
            System.out.println("Usuario: " + usuario.getPermiso());
            mv.addObject("usuario", usuario);
        }
        mv.addObject("productos", productos.listarProductos());
        String mensaje = "123";
        String cifrado = encoder.encode(mensaje);
        System.out.println("Mensaje cifrado: " + cifrado);
        mv.setViewName("index");

        return mv;
    }

    @RequestMapping("/c")
    public ModelAndView peticionCategoria(Authentication aut, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if(aut==null)
            mv.addObject("user", "anonimo");
        else
            mv.addObject("user", aut.getName());
        String categoria = request.getParameter("categoria");
        mv.addObject("productos", productos.listarProductos());
        mv.addObject("categoria", categoria);
        mv.setViewName("categoria");
        return mv;
    }


    @RequestMapping("/denegado")
    public ModelAndView peticionDenegado(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if(aut==null)
            mv.addObject("user", "anonimo");
        else
            mv.addObject("user", aut.getName());

        mv.setViewName("denegado");
        return mv;
    }

    @RequestMapping("login")
    public ModelAndView peticionSesion(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if(aut==null)
            mv.addObject("user", "anonimo");
        else
            mv.addObject("user", aut.getName());

        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("/admin/modStock")
    public ModelAndView peticionModStock(Authentication aut, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if(aut==null)
            mv.addObject("user", "anonimo");
        else
            mv.addObject("user", aut.getName());

        String id = request.getParameter("id");
        String stock = request.getParameter("stock");
        Optional<Producto> producto = productoRepositorio.findById(Integer.parseInt(id));
        producto.get().setStock(Integer.parseInt(stock));
        productoRepositorio.save(producto.get());
        mv.setViewName("redirect:/");
        return mv;
    }
    @RequestMapping("logout")
    public ModelAndView peticionLogout(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if(aut==null)
            mv.addObject("user", "anonimo");
        else
            mv.addObject("user", aut.getName());

        mv.setViewName("logout");
        return mv;
    }


}