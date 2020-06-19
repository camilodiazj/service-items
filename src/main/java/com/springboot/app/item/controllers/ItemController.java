package com.springboot.app.item.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.app.item.models.Item;
import com.springboot.app.item.models.Producto;
import com.springboot.app.item.models.service.ItemsService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

  private static Logger log = LoggerFactory.getLogger(ItemController.class);

  @Value("${configuracion.texto}")
  private String texto;

  private final ItemsService itemsService;
  private final Environment environment;

  public ItemController(@Qualifier("service-feign") ItemsService itemsService, Environment environment){
    this.itemsService = itemsService;
    this.environment = environment;
  }

  @GetMapping("/listar")
  public List<Item> listar() {
    return itemsService.findAll();
  }

  @HystrixCommand(fallbackMethod = "metodoAlternativo")
  @GetMapping("/ver/{id}/cantidad/{cantidad}")
  public Item detail(@PathVariable Long id, @PathVariable Integer cantidad) {
    return itemsService.findById(id, cantidad);
  }

  @GetMapping("/obtener-config")
  public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String port) {
    log.info(texto);
    Map<String, String> json = new HashMap<>();
    json.put("texto", texto);
    json.put("port", port);
    if(environment.getActiveProfiles().length>0 && environment.getActiveProfiles()[0].equals("dev")){
      json.put("autor.nombre",environment.getProperty("configuracion.autor.nombre"));
      json.put("autor.email",environment.getProperty("configuracion.autor.email"));
    }
    return new ResponseEntity<>(json, HttpStatus.OK);
  }

  public Item metodoAlternativo(Long id, Integer cantidad){
    Item item = new Item();
    Producto producto = new Producto();
    item.setCantidad(cantidad);
    producto.setId(id);
    producto.setNombre("Camara sony");
    producto.setPrecio(5600.00);
    item.setProducto(producto);
    return item;
  }

}
