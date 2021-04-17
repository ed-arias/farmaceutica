package com.edu.javeriana.farmaceutica.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Pedido implements Serializable{

    private static final long serialVersionUID = -5699353163921471396L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPedido;
    private LocalDateTime fechaPedido;
    private String direccionEntrega;
    private Float totalPedido;

    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<Item> items = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<Oferta> ofertas = new ArrayList<>();

    public void agregarItem(Item item){
        item.setPedido(this);
        this.items.add(item);
    }

    public void agregarOferta(Oferta oferta){
        oferta.setPedido(this);
        this.ofertas.add(oferta);
    }

    public void calcularTotalPedido(){
        this.totalPedido = this.items.stream()
            .map(Item::getTotalItem)
            .reduce(0f, (a, b) -> a + b);
    }
}
