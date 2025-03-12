package com.inventorymanager.inventario_backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inventorymanager.inventario_backend.model.Producto;

@Service
public class ProductoService {
  private List<Producto> productos = new ArrayList<>();
  private static Long idCounter = 1L;

  public ProductoService() {}

  //Obtener todos los productos
  public List<Producto> obtenerProductos() {
    return productos;
  }

  //Filtrar productos
  public List<Producto> filtrarProductos(String name, List<String> categories, String availability) {
    return productos.stream()
      .filter(producto -> (name == null || name.isEmpty() || producto.getName().toLowerCase().contains(name.toLowerCase())))
      .filter(producto -> (categories == null || categories.isEmpty() || categories.stream().anyMatch(cat -> producto.getCategory().equalsIgnoreCase(cat))))
      .filter(producto -> {
        if (availability == null || availability.isEmpty() || "all".equalsIgnoreCase(availability)) {
          return true;
        } else if ("available".equalsIgnoreCase(availability)) {
          return producto.getQuantityInStock() > 0;
        } else if ("unavailable".equalsIgnoreCase(availability)) {
          return producto.getQuantityInStock() <= 0;
        }
        return true;
      })
      .collect(Collectors.toList());
  }

  //Agregar un nuevo producto
  public String agregarProducto(Producto nuevProducto) {
    for (Producto producto : productos) {
      if (producto.getName().equalsIgnoreCase(nuevProducto.getName())) {
        return "El producto " + nuevProducto.getName() + " ya existe.";
      }
    }
    if (!esCategoriaValida(nuevProducto.getCategory())) {
      return "Categoría no válida.";
    }

    if ("food".equalsIgnoreCase(nuevProducto.getCategory()) && nuevProducto.getExpirationDate() == null) {
      return "Los productos de categoría food deben tener fecha de caducidad.";
    }

    nuevProducto.setId(idCounter++);
    productos.add(nuevProducto);
    return "Producto agregado correctamente.";
  }

  //Editar producto
  public String editarProducto(int id, Producto productoActualizado) {
    for (Producto producto : productos) {
      if (producto.getId() == id) {
        //validación de caducidad en food
        if ("food".equalsIgnoreCase(productoActualizado.getCategory())) {
          if (productoActualizado.getExpirationDate() == null) {
            return "El producto categoría food debe tener fecha de caducidad";
          }
          producto.setName(productoActualizado.getName());
          producto.setCategory(productoActualizado.getCategory());
          producto.setQuantityInStock(productoActualizado.getQuantityInStock());
          producto.setUnitPrice(productoActualizado.getUnitPrice());
          producto.setExpirationDate(productoActualizado.getExpirationDate());
        } else {
          producto.setName(productoActualizado.getName());
          producto.setCategory(productoActualizado.getCategory());
          producto.setQuantityInStock(productoActualizado.getQuantityInStock());
          producto.setUnitPrice(productoActualizado.getUnitPrice());
          producto.setExpirationDate(null);
        }
        return "Producto actualizado correctamente.";
      }
    }
    return "Producto no encontrado.";
  }

  public String noStock(int id) {
    for (Producto producto : productos) {
      if (producto.getId() == id) {
        producto.setQuantityInStock(0);
        return "Stock actualizado correctamente.";
      }
    }
    return "Producto no encontrado.";
  }

  public String inStock(int id) {
    for (Producto producto : productos) {
      if (producto.getId() == id) {
        producto.setQuantityInStock(10);
        return "Stock reestablecido a 10.";
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
