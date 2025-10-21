package com.pokemon.msopokeapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.pokemon.msopokeapi.dao.PokemonSearch;
import com.pokemon.msopokeapi.dto.PokemonResponse;
import com.pokemon.msopokeapi.service.PokemonService;

public class PokemonControllerTest {
	 @Mock
	    private PokemonService service;

	    @InjectMocks
	    private PokemonController controller;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void obtenerPokemon() {
	        PokemonResponse pokemon = new PokemonResponse("pikachu", List.of("electric"), 
	                List.of("static"), List.of("thunder-shock"), Map.of("hp", 35), "imagenBase64");

	        when(service.obtenerPokemon("pikachu")).thenReturn(pokemon);

	        ResponseEntity<PokemonResponse> response = controller.obtenerPokemon("pikachu");

	        assertEquals(200, response.getStatusCodeValue());
	        assertEquals("pikachu", response.getBody().getNombre());
	    }

	    @Test
	    void listarBusquedas() {
	        List<PokemonSearch> historial = Arrays.asList(
	                new PokemonSearch("pikachu"),
	                new PokemonSearch("charizard")
	        );

	        when(service.obtenerHistorial()).thenReturn(historial);

	        ResponseEntity<List<PokemonSearch>> response = controller.listarBusquedas();

	        assertEquals(200, response.getStatusCodeValue());
	        assertEquals(2, response.getBody().size());
	    }

	    @Test
	    void listarBusquedasVacio() {
	        when(service.obtenerHistorial()).thenReturn(Collections.emptyList());

	        ResponseEntity<List<PokemonSearch>> response = controller.listarBusquedas();

	        assertEquals(204, response.getStatusCodeValue());
	        assertNull(response.getBody());
	    }
}
