package com.inventorymanager.inventario_backend.model;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

public class Producto {
  private Long id;
  private String name;
  private String category;
  private int quantityInStock;
  private double unitPrice;
  private LocalDate expirationDate;
  private LocalDate creationDate;
  private LocalDate updateDate;

  public Producto(){
    this.creationDate = LocalDate.now();
    this.updateDate = LocalDate.now();
  }

  //getters y setters
  public Long getId() {return id;}
  public void setId(Long id) {this.id = id;}

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

  public LocalDate getCreationDate() {return creationDate;}
  public void setCreationDate(LocalDate creationDate) {this.creationDate = creationDate;}

  public LocalDate getUpdateDate() {return updateDate;}
  public void setUpdateDate(LocalDate updateDate) {this.updateDate = updateDate;}

  public void updateModificationDate() {
    this.updateDate = LocalDate.now();
  }
}