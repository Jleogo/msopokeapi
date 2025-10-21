package com.pokemon.msopokeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemon.msopokeapi.dao.PokemonSearch;

public interface PokemonSearchRepository extends JpaRepository<PokemonSearch, Long>{

}
