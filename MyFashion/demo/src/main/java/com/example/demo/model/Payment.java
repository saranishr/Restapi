package com.example.demo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
 
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentid")  
    private long id;
    private long payementamount;
    private String paymentMethod;
    public Payment() {}
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getPayementamount() {
        return payementamount;
    }
    public void setPayementamount(long payementamount) {
        this.payementamount = payementamount;
    }
    public Payment(long id, long payementamount, String paymentMethod) {
        this.id = id;
        this.payementamount = payementamount;
        this.paymentMethod = paymentMethod;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
