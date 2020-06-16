package com.springboot.app.item.controllers;

import com.springboot.app.item.models.Item;
import com.springboot.app.item.models.service.ItemsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

  private final ItemsService itemsService;

  public ItemController(@Qualifier("service-feign") ItemsService itemsService){
    this.itemsService = itemsService;
  }

  @GetMapping("/listar")
  public List<Item> listar() {
    return itemsService.findAll();
  }

  @GetMapping("/ver/{id}/cantidad/{cantidad}")
  public Item detail(@PathVariable Long id, @PathVariable Integer cantidad) {
    return itemsService.findById(id, cantidad);
  }
}
