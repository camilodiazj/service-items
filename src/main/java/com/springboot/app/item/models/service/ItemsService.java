package com.springboot.app.item.models.service;

import com.springboot.app.item.models.Item;

import java.util.List;

public interface ItemsService {
    public List<Item> findAll();
    public Item findById(Long id, Integer cantidad);
}
