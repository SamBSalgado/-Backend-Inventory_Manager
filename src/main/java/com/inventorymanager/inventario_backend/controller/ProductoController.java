package com.inventorymanager.inventario_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {
  
  @GetMapping("api/hola")
  public String decirHola() {
    return "Hola desde el backend!";
  }
}
