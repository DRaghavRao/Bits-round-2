package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "suppliers") // Match the exact table name (lowercase)
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id") // Explicitly map to supplier_id for clarity
    private Long supplierId;

    @Column(nullable = false)
    private String name;

    @Column(name = "contact_person") // Explicitly map to contact_person
    private String contactPerson;

    private String email;

    private String phone;

    private String address;

    // Default constructor
    public Supplier() {
        super();
    }

    // Getters and setters
    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Supplier{" +
               "supplierId=" + supplierId +
               ", name='" + name + '\'' +
               ", contactPerson='" + contactPerson + '\'' +
               ", email='" + email + '\'' +
               ", phone='" + phone + '\'' +
               ", address='" + address + '\'' +
               '}';
    }
}