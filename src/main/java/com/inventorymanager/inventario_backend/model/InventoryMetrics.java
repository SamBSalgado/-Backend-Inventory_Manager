package com.inventorymanager.inventario_backend.model;

public class InventoryMetrics {
  private String category;
  private long totalProducts;
  private int totalStockUnits;
  private double totalStockValue;
  private double avgPrice;

  public InventoryMetrics(String category, long totalProducts, int totalStockUnits, double totalStockValue, double avgPrice) {
    this.category = category;
    this.totalProducts = totalProducts;
    this.totalStockUnits = totalStockUnits;
    this.totalStockValue = totalStockValue;
    this.avgPrice = avgPrice;
  }

  //getters y setters
  public String getCategory() {return category;}
  public void setCategory(String category) {this.category = category;}

  public long getTotalProducts() {return totalProducts;}
  public void setTotalProducts(long totalProducts) {this.totalProducts = totalProducts;}

  public int getTotalStockUnits() {return totalStockUnits;}
  public void setTotalStockUnits(int totalStockUnits) {this.totalStockUnits = totalStockUnits;}

  public double getTotalStockValue() {return totalStockValue;}
  public void setTotalStockValue(double totalStockValue) {this.totalStockValue = totalStockValue;}

  public double getAvgPrice() {return avgPrice;}
  public void setAvgPrice(double avgPrice) {this.avgPrice = avgPrice;}
}
