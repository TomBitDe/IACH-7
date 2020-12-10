package com.mycompany.iach7.dummy.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Items test entity
 */
@Entity
@Table(name = "ITEMS", schema = "IACH7_TEST")
public class Items implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false) // for integrity
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY for bettter performance
    @JoinColumn(name = "cart_id", nullable = false) // nullable = false for integrity
    private Cart cart;

    public Items() {
        super();
    }

    public Items(Cart cart) {
        super();
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Items{id=").append(id);
        sb.append(", cart=").append(cart);
        sb.append('}');
        return sb.toString();
    }
}
