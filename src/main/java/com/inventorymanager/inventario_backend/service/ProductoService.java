package com.inventorymanager.inventario_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inventorymanager.inventario_backend.model.Producto;

@Service
public class ProductoService {
  private List<Producto> productos = new ArrayList<>();

  //Constructor para inicializar algunos productos de base
  public ProductoService() {
    productos.add(new Producto(1, "Botella de agua", "food", 10, 10));
    productos.add(new Producto(2, "Monitor", "electronics", 4, 4000.50));
    productos.add(new Producto(3, "Jeans", "clothing", 50, 499.99));
  }

  //Obtener todos los productos
  public List<Producto> obtenerProductos() {
    return productos;
  }

  //Agregar un nuevo producto
  public String agregarProducto(Producto nuevProducto) {
    if (!esCategoriaValida(nuevProducto.getCategory())) {
      return "Categoría no válida.";
    }

    if ("food".equalsIgnoreCase(nuevProducto.getCategory()) && nuevProducto.getExpirationDate() == null) {
      return "Los productos de categoría food deben tener fecha de caducidad.";
    }

    productos.add(nuevProducto);
    return "Producto agregado correctamente.";
  }

  //Actualizar stock
  public String actualizarStock(int id, int nuevoStock) {
    for (Producto producto : productos) {
      if (producto.getId() == id) {
        producto.setQuantityInStock(nuevoStock);
        return "Stock actualizado correctamente.";
      }
    }
    return "Producto no encontrado.";
  }

  //Eliminar producto
  public String eliminarProducto(int id) {
    Producto productoAEliminar = null;

    for (Producto p: productos) {
      if (p.getId() == id) {
        productoAEliminar = p;
        break;
      }
    }

    if (productoAEliminar != null) {
      productos.remove(productoAEliminar);
      return "Producto eliminado correctamente.";
    } else {
      return "Producto no encontrado.";
    }
  }

  private boolean esCategoriaValida(String categoria) {
    return "food".equalsIgnoreCase(categoria) ||
    "electronics".equalsIgnoreCase(categoria) ||
    "clothing".equalsIgnoreCase(categoria);
  }
}
