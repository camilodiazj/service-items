package com.springboot.app.item.models.service;

import com.springboot.app.item.clientes.ProductoClienteRest;
import com.springboot.app.item.models.Item;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("service-feign")
public class ItemServiceFeign implements ItemsService {

  @Autowired
  private ProductoClienteRest productoClienteRest;

  @Override
  public List<Item> findAll() {
    return productoClienteRest.listar().stream()
        .map(producto -> new Item(producto, 1))
        .collect(Collectors.toList());
  }

  @Override
  public Item findById(Long id, Integer cantidad) {
    return new Item(productoClienteRest.detalle(id), cantidad);
  }
}
