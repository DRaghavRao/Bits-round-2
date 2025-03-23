package entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "sales") // Match the exact table name (lower case)
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id") // Explicitly map to sale_id for clarity
    private Long saleId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Column(name = "quantity_sold", nullable = false) // Explicitly map to quantity_sold
    private Integer quantitySold;

    @Column(name = "total_price", nullable = false) // Explicitly map to total_price
    private Double totalPrice;

    @CreationTimestamp
    @Column(name = "sale_date") // Explicitly map to sale_date
    private Date saleDate;

    // Default constructor
    public Sales() {
        super();
    }

    // Getters and setters
    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Integer quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public String toString() {
        return "Sales{" +
               "saleId=" + saleId +
               ", product=" + (product != null ? product.getProductId() : null) +
               ", user=" + (user != null ? user.getId() : null) +
               ", quantitySold=" + quantitySold +
               ", totalPrice=" + totalPrice +
               ", saleDate=" + saleDate +
               '}';
    }
}