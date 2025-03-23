package entities;

import java.util.Date;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "products") // Match the exact table name (lowercase)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id") // Explicitly map to product_id for clarity
    private Long productId;

    @Column(nullable = false)
    private String name;

    private String description;

    private String category;

    @Column(nullable = false)
    private Double price;

    @Column(name = "stock_quantity", nullable = false) // Explicitly map to stock_quantity
    private Integer stockQuantity;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = true)
    private Supplier supplier;

    @UpdateTimestamp
    @Column(name = "last_updated") // Explicitly map to last_updated
    private Date lastUpdated;

    // Getters and setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Product{" +
               "productId=" + productId +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", category='" + category + '\'' +
               ", price=" + price +
               ", stockQuantity=" + stockQuantity +
               ", supplier=" + (supplier != null ? supplier.getSupplierId() : null) +
               ", lastUpdated=" + lastUpdated +
               '}';
    }
}