package com.Webflux.Pokedex.repository;

import com.Webflux.Pokedex.Model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokemonRepository extends ReactiveMongoRepository<Pokemon,String> {
}
