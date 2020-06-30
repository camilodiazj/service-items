package com.springboot.app.item.clientes;

import com.springboot.app.commons.models.entity.Producto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {

  @GetMapping("/listar")
  List<Producto> listar();

  @GetMapping("/ver/{id}")
  Producto detalle(@PathVariable Long id);

  @PostMapping("/crear")
  Producto crear(@RequestBody Producto producto);

  @PutMapping("/editar/{id}")
  Producto editar(@RequestBody Producto producto, @PathVariable Long id);

  @DeleteMapping("/eliminar/{id}")
  void deleteById(@PathVariable Long id);

}
