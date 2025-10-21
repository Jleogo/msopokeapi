package com.pokemon.msopokeapi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pokemon.msopokeapi.dao.PokemonSearch;
import com.pokemon.msopokeapi.dto.PokemonResponse;
import com.pokemon.msopokeapi.exception.PokemonNotFoundException;
import com.pokemon.msopokeapi.repository.PokemonSearchRepository;
import com.pokemon.msopokeapi.serviceImpl.PokemonServiceImpl;

public class PokemonServiceImplTest {
	@Mock
    private PokemonSearchRepository repository;

    @InjectMocks
    private PokemonServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerPokemonValido() {
        String nombre = "pikachu";

        when(repository.save(any(PokemonSearch.class))).thenAnswer(i -> i.getArgument(0));

        PokemonResponse response = service.obtenerPokemon(nombre);

        assertNotNull(response);
        assertEquals("pikachu", response.getNombre());
        assertTrue(response.getTipos().contains("electric"));

        verify(repository, times(1)).save(any(PokemonSearch.class));
    }

    @Test
    void obtenerPokemonNoValido() {
        String nombre = "desconocido";
        assertThrows(PokemonNotFoundException.class, () -> service.obtenerPokemon(nombre));
        verify(repository, never()).save(any(PokemonSearch.class));
    }
}
