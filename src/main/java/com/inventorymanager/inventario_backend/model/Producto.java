package com.inventorymanager.inventario_backend.model;

import java.time.LocalDate;

public class Producto {
  private int id;
  private String name;
  private String category;
  private int quantityInStock;
  private double unitPrice;
  private LocalDate expirationDate;

  public Producto(){}

  //Constructor que incluye fecha de caducidad
  public Producto(int id, String name, String category, int quantityInStock, double unitPrice, LocalDate expirationDate) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.quantityInStock = quantityInStock;
    this.unitPrice = unitPrice;
    this.expirationDate = expirationDate;
  }

  //Constructor sin fecha de caducidad
  public Producto(int id, String name, String category, int quantityInStock, double unitPrice) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.quantityInStock = quantityInStock;
    this.unitPrice = unitPrice;
  }

  //getters y setters
  public int getId() {return id;}
  public void setId(int id) {this.id = id;}

  public String getName() {return name;}
  public void setName(String name) {this.name = name;}

  public String getCategory() {return category;}
  public void setCategory(String category) {this.category = category;}

  public int getQuantityInStock() {return quantityInStock;}
  public void setQuantityInStock(int quantityInStock) {this.quantityInStock = quantityInStock;}

  public double getUnitPrice() {return unitPrice;}
  public void setUnitPrice(double unitPrice) {this.unitPrice = unitPrice;}

  public LocalDate getExpirationDate() {return expirationDate;}
  public void setExpirationDate(LocalDate expirationDate) {this.expirationDate = expirationDate;}
}