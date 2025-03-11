package com.inventorymanager.inventario_backend.controller;
import java.util.Collections;
import java.util.List;

import com.inventorymanager.inventario_backend.model.Producto;
import com.inventorymanager.inventario_backend.service.ProductoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:8080")
public class ProductoController {

  private final ProductoService productoService;

  public ProductoController(ProductoService productoService) {
    this.productoService = productoService;
  }
  
  @GetMapping("/all")
  public List<Producto> obtenerProductos() {
    return productoService.obtenerProductos();
  }

  @GetMapping
  public ResponseEntity<List<Producto>> filtrarProductos(
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String category,
    @RequestParam(required = false) String availability) {

    if ((name == null || name.isEmpty()) && 
      (category == null || category.isEmpty()) &&
      (availability == null || availability.isEmpty())) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
    }

    List<Producto> resultado = productoService.filtrarProductos(name, category, availability);
    if (resultado.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultado);
    }
    return ResponseEntity.ok(resultado);
  }

  @PostMapping
  public ResponseEntity<String> agregarProducto(@RequestBody Producto nuevoProducto) {
    String resultado = productoService.agregarProducto(nuevoProducto);
    if (resultado.equals("Producto agregado correctamente.")) {
      return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> editarProducto(@PathVariable int id, @RequestBody Producto productoActualizado) {
    String resultado = productoService.editarProducto(id, productoActualizado);

    if (resultado.equals("Producto actualizado correctamente.")) {
      return ResponseEntity.ok(resultado);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
    }
  }

  @PutMapping("{id}/outofstock")
  public ResponseEntity<String> noStock(@PathVariable int id) {
    String resultado = productoService.noStock(id);
    if (resultado.equals("Stock actualizado correctamente.")) {
      return ResponseEntity.ok(resultado);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultado);
    }
  }

  @PutMapping("{id}/instock")
  public ResponseEntity<String> inStock(@PathVariable int id) {
    String resultado = productoService.inStock(id);
    if (resultado.equals("Stock reestablecido a 10.")) {
      return ResponseEntity.ok(resultado);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultado);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> eliminarProducto(@PathVariable int id) {
    String resultado = productoService.eliminarProducto(id);
    if (resultado.equals("Producto eliminado correctamente.")) {
      return ResponseEntity.ok(resultado);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultado);
    }
  }
}
