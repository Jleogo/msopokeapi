package com.pokemon.msopokeapi.serviceImpl;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.msopokeapi.dao.PokemonSearch;
import com.pokemon.msopokeapi.dto.PokemonResponse;
import com.pokemon.msopokeapi.exception.PokemonNotFoundException;
import com.pokemon.msopokeapi.repository.PokemonSearchRepository;
import com.pokemon.msopokeapi.service.PokemonService;

import reactor.core.publisher.Mono;
@Service
public class PokemonServiceImpl implements PokemonService{
	private final PokemonSearchRepository repository;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    
    public PokemonServiceImpl(PokemonSearchRepository repository) {
        this.repository = repository;
        this.webClient = WebClient.builder().baseUrl("https://pokeapi.co/api/v2").build();
        this.objectMapper = new ObjectMapper();
    }
    
    @Override
    public PokemonResponse obtenerPokemon(String nombre) {
        try {
            Mono<Map> responseMono = webClient.get()
                    .uri("/pokemon/{nombre}", nombre.toLowerCase())
                    .retrieve()
                    .bodyToMono(Map.class);

            Map<String, Object> response = responseMono.block();

            if (response == null) {
                throw new PokemonNotFoundException("Pokémon no encontrado: " + nombre);
            }

            String nombrePokemon = (String) response.get("name");

            //Convertir JSON a tipos seguros usando ObjectMapper
            List<Map<String, Object>> tiposList = objectMapper.convertValue(
                    response.get("types"), new TypeReference<List<Map<String, Object>>>() {});

            List<Map<String, Object>> habilidadesList = objectMapper.convertValue(
                    response.get("abilities"), new TypeReference<List<Map<String, Object>>>() {});

            List<Map<String, Object>> ataquesList = objectMapper.convertValue(
                    response.get("moves"), new TypeReference<List<Map<String, Object>>>() {});

            List<Map<String, Object>> statsList = objectMapper.convertValue(
                    response.get("stats"), new TypeReference<List<Map<String, Object>>>() {});

            Map<String, Object> sprites = objectMapper.convertValue(
                    response.get("sprites"), new TypeReference<Map<String, Object>>() {});

            //Mapear datos
            List<String> tipos = tiposList.stream()
                    .map(t -> (String) ((Map<String, Object>) t.get("type")).get("name"))
                    .collect(Collectors.toList());

            List<String> habilidades = habilidadesList.stream()
                    .map(h -> (String) ((Map<String, Object>) h.get("ability")).get("name"))
                    .collect(Collectors.toList());

            List<String> ataques = ataquesList.stream()
                    .limit(5)
                    .map(a -> (String) ((Map<String, Object>) a.get("move")).get("name"))
                    .collect(Collectors.toList());

            Map<String, Integer> estadisticas = statsList.stream()
                    .collect(Collectors.toMap(
                            s -> (String) ((Map<String, Object>) s.get("stat")).get("name"),
                            s -> (Integer) s.get("base_stat")
                    ));

            //Obtener imagen y convertir a Base64
            String imageUrl = (String) sprites.get("front_default");
            byte[] imageBytes = webClient.get()
                    .uri(imageUrl)
                    .retrieve()
                    .bodyToMono(byte[].class)
                    .block();

            String imagenBase64 = Base64.getEncoder().encodeToString(imageBytes);

            //Guardar búsqueda en H2
            repository.save(new PokemonSearch(nombrePokemon));

            return new PokemonResponse(nombrePokemon, tipos, habilidades, ataques, estadisticas, imagenBase64);

        } catch (Exception e) {
            throw new PokemonNotFoundException("Error al obtener información del Pokémon: " + nombre);
        }
    }

	@Override
	public List<PokemonSearch> obtenerHistorial() {
		return repository.findAll();
	}
}
