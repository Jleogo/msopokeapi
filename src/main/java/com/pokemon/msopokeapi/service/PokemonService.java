package com.pokemon.msopokeapi.service;

import java.util.List;

import com.pokemon.msopokeapi.dao.PokemonSearch;
import com.pokemon.msopokeapi.dto.PokemonResponse;

public interface PokemonService {
	PokemonResponse obtenerPokemon(String nombre);
	List<PokemonSearch> obtenerHistorial();
}
