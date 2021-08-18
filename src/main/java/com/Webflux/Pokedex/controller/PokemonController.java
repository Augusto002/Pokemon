package com.Webflux.Pokedex.controller;


import com.Webflux.Pokedex.Model.Pokemon;
import com.Webflux.Pokedex.Model.PokemonEvent;
import com.Webflux.Pokedex.repository.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private PokemonRepository repository;
    public PokemonController(PokemonRepository repository){this.repository=repository;}

    @GetMapping()
    public Flux<Pokemon> getAllPokemon(){return repository.findAll();}

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Pokemon>> getPokemon(@PathVariable String id){
        return repository.findById(id).map(pokemon -> ResponseEntity.ok(pokemon)).
                defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pokemon> savePokemon(@RequestBody Pokemon pokemon){
        return repository.save(pokemon);
    }

    @PutMapping("({id})")
    public Mono<ResponseEntity<Pokemon>> updatePokemon(@PathVariable(value = "id") String id,
                                                       @RequestBody Pokemon pokemon){
        return repository.findById(id)
                .flatMap(existePokemon ->{
                    existePokemon.setNome(pokemon.getNome());
                    existePokemon.setHabilidade(pokemon.getHabilidade());
                    existePokemon.setHabilidade(pokemon.getCategoria());
                    existePokemon.setPeso(pokemon.getPeso());
                 return repository.save(existePokemon);
                })
                .map(updatePokemon -> ResponseEntity.ok(updatePokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Object>> deletepokemon(@PathVariable(value = "id") String id) {
        return  repository.findById(id)
                .flatMap(existingPokemon ->
                        repository.delete(existingPokemon)
                                .then(Mono.just(ResponseEntity.ok().build()))
                        )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @DeleteMapping
    public Mono<Void> deleteAllPokemon(){ return repository.deleteAll();}

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PokemonEvent> getPokemonEvents(){
        return Flux.interval(Duration.ofSeconds(5))
                .map(val ->
                        new PokemonEvent(val,"Pokemonzitooss"));
    }

}
