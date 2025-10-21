package com.pokemon.msopokeapi.controller;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pokemon.msopokeapi.dao.PokemonSearch;
import com.pokemon.msopokeapi.dto.PokemonResponse;
import com.pokemon.msopokeapi.service.PokemonService;


@RestController
@RequestMapping("/pokemon")
public class PokemonController {
	private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }
    
    @GetMapping("/{nombre}")
    public ResponseEntity<PokemonResponse> obtenerPokemon( @PathVariable String nombre) {
        PokemonResponse response = service.obtenerPokemon(nombre);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/busquedas")
    public ResponseEntity<List<PokemonSearch>> listarBusquedas() {
        List<PokemonSearch> historial = service.obtenerHistorial();
        if (historial.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historial);
    }

}
