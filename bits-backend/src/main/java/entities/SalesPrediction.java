package entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "sales_predictions") // Match the exact table name (lowercase, with underscore)
public class SalesPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prediction_id") // Explicitly map to prediction_id for clarity
    private Long predictionId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "predicted_sales", nullable = false) // Explicitly map to predicted_sales
    private Integer predictedSales;

    @Column(name = "prediction_date", nullable = false) // Explicitly map to prediction_date
    private Date predictionDate;

    @Column(name = "accuracy_percentage") // Explicitly map to accuracy_percentage
    private Double accuracyPercentage;

    @CreationTimestamp
    @Column(name = "created_at") // Explicitly map to created_at
    private Date createdAt;

    // Default constructor
    public SalesPrediction() {
        super();
    }

    // Getters and setters
    public Long getPredictionId() {
        return predictionId;
    }

    public void setPredictionId(Long predictionId) {
        this.predictionId = predictionId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getPredictedSales() {
        return predictedSales;
    }

    public void setPredictedSales(Integer predictedSales) {
        this.predictedSales = predictedSales;
    }

    public Date getPredictionDate() {
        return predictionDate;
    }

    public void setPredictionDate(Date predictionDate) {
        this.predictionDate = predictionDate;
    }

    public Double getAccuracyPercentage() {
        return accuracyPercentage;
    }

    public void setAccuracyPercentage(Double accuracyPercentage) {
        this.accuracyPercentage = accuracyPercentage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "SalesPrediction{" +
               "predictionId=" + predictionId +
               ", product=" + (product != null ? product.getProductId() : null) +
               ", predictedSales=" + predictedSales +
               ", predictionDate=" + predictionDate +
               ", accuracyPercentage=" + accuracyPercentage +
               ", createdAt=" + createdAt +
               '}';
    }
}