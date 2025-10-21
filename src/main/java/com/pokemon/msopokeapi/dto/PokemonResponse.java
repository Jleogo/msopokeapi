package com.pokemon.msopokeapi.dto;

import java.util.List;
import java.util.Map;

public class PokemonResponse {
	private String nombre;
    private List<String> tipos;
    private List<String> habilidades;
    private List<String> ataques;
    private Map<String, Integer> estadisticas;
    private String imagenBase64;
    
	public PokemonResponse() {
	}
	public PokemonResponse(String nombre, List<String> tipos, List<String> habilidades, List<String> ataques,
			Map<String, Integer> estadisticas, String imagenBase64) {
		this.nombre = nombre;
		this.tipos = tipos;
		this.habilidades = habilidades;
		this.ataques = ataques;
		this.estadisticas = estadisticas;
		this.imagenBase64 = imagenBase64;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<String> getTipos() {
		return tipos;
	}
	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}
	public List<String> getHabilidades() {
		return habilidades;
	}
	public void setHabilidades(List<String> habilidades) {
		this.habilidades = habilidades;
	}
	public List<String> getAtaques() {
		return ataques;
	}
	public void setAtaques(List<String> ataques) {
		this.ataques = ataques;
	}
	public Map<String, Integer> getEstadisticas() {
		return estadisticas;
	}
	public void setEstadisticas(Map<String, Integer> estadisticas) {
		this.estadisticas = estadisticas;
	}
	public String getImagenBase64() {
		return imagenBase64;
	}
	public void setImagenBase64(String imagenBase64) {
		this.imagenBase64 = imagenBase64;
	}
    
    
}