package com.pokemon.msopokeapi.dao;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PokemonSearch {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private LocalDateTime fechaBusqueda;

    public PokemonSearch() {}

    public PokemonSearch(String nombre) {
        this.nombre = nombre;
        this.fechaBusqueda = LocalDateTime.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDateTime getFechaBusqueda() {
		return fechaBusqueda;
	}

	public void setFechaBusqueda(LocalDateTime fechaBusqueda) {
		this.fechaBusqueda = fechaBusqueda;
	}
    
    
}
