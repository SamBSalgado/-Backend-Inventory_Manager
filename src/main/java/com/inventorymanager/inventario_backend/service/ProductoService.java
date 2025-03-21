package com.inventorymanager.inventario_backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.inventorymanager.inventario_backend.model.InventoryMetrics;
import com.inventorymanager.inventario_backend.model.Producto;

@Service
public class ProductoService {
  private List<Producto> productos = new ArrayList<>();
  private List<String> categorias = new ArrayList<>();
  private static Long idCounter = 1L;

  public ProductoService() {
  }

  public List<String> obtenerCategorias() {
    return categorias;
  }

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
    if (!categorias.contains(nuevProducto.getCategory().toLowerCase())) {
      categorias.add(nuevProducto.getCategory().toLowerCase());
    }

    if (nuevProducto.getCreationDate() == null) {
      nuevProducto.setCreationDate(LocalDate.now());
    }
    if (nuevProducto.getUpdateDate() == null) {
      nuevProducto.setUpdateDate(LocalDate.now());
    }

    nuevProducto.setId(idCounter++);
    productos.add(nuevProducto);
    return "Producto agregado correctamente.";
  }

  //Editar producto
  public String editarProducto(int id, Producto productoActualizado) {
    for (Producto producto : productos) {
      if (producto.getId() == id) {
        if (!categorias.contains(productoActualizado.getCategory().toLowerCase())) {
          categorias.add(productoActualizado.getCategory().toLowerCase());
        }
        producto.setName(productoActualizado.getName());
        producto.setCategory(productoActualizado.getCategory());
        producto.setQuantityInStock(productoActualizado.getQuantityInStock());
        producto.setUnitPrice(productoActualizado.getUnitPrice());
        producto.setExpirationDate(productoActualizado.getExpirationDate());
        producto.setUpdateDate(LocalDate.now());
        return "Producto actualizado correctamente.";
      }
    }
    return "Producto no encontrado.";
  }

  public String noStock(int id) {
    for (Producto producto : productos) {
      if (producto.getId() == id) {
        producto.setQuantityInStock(0);
        producto.setUpdateDate(LocalDate.now());
        return "Stock actualizado correctamente.";
      }
    }
    return "Producto no encontrado.";
  }

  public String inStock(int id) {
    for (Producto producto : productos) {
      if (producto.getId() == id) {
        producto.setQuantityInStock(10);
        producto.setUpdateDate(LocalDate.now());
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

  //Calcular métricas
  public List<InventoryMetrics> obtenerMetricas() {
    List<String> categorias = productos.stream()
      .map(Producto::getCategory)
      .distinct()
      .collect(Collectors.toList());

      List<InventoryMetrics> metrics = new ArrayList<>();

      for (String categoria : categorias) {
        List<Producto> productCategories = productos.stream()
          .filter(p -> p.getCategory().equals(categoria))
          .collect(Collectors.toList());

        long totalProducts = productCategories.size();

        List<Producto> inStockProducts = productCategories.stream()
          .filter(p -> p.getQuantityInStock() > 0)
          .collect(Collectors.toList());

        int totalInStockUnits = inStockProducts.stream()
          .mapToInt(Producto::getQuantityInStock)
          .sum();

        double totalInStockValue = inStockProducts.stream()
          .mapToDouble(p -> p.getQuantityInStock()*p.getUnitPrice())
          .sum();

        double avgPrice = 0;
        if (totalInStockUnits > 0) {
          avgPrice = totalInStockValue / totalInStockUnits;
        }

        InventoryMetrics metric = new InventoryMetrics(
          categoria,
          totalProducts,
          totalInStockUnits,
          totalInStockValue,
          avgPrice
        );

        metrics.add(metric);
      }

      long totalProductsGlobal = productos.size();
      List<Producto> inStockProductsGlobal  = productos.stream()
        .filter(p -> p.getQuantityInStock() > 0)
        .collect(Collectors.toList());

      int totalInStockUnitsGlobal = inStockProductsGlobal.stream()
        .mapToInt(Producto::getQuantityInStock)
        .sum();
        
      double totalInStockValueGlobal = inStockProductsGlobal.stream()
        .mapToDouble(p -> p.getQuantityInStock()*p.getUnitPrice())
        .sum();

      double avgPriceGlobal = 0;
      if (totalInStockUnitsGlobal > 0) {
        avgPriceGlobal = totalInStockValueGlobal / totalInStockUnitsGlobal;
      }

      InventoryMetrics metricaGlobal = new InventoryMetrics(
        "Total",
        totalProductsGlobal,
        totalInStockUnitsGlobal,
        totalInStockValueGlobal,
        avgPriceGlobal
      );

      metrics.add(metricaGlobal);
      return metrics;
  }
}
