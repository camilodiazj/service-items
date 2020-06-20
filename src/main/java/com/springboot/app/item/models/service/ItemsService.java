package com.springboot.app.item.models.service;

import com.springboot.app.item.models.Item;

import com.springboot.app.item.models.Producto;
import java.util.List;

public interface ItemsService {
    List<Item> findAll();
    Item findById(Long id, Integer cantidad);
    Producto save(Producto producto);
    Producto update(Producto producto, Long id);
    void delete(Long id);
}
