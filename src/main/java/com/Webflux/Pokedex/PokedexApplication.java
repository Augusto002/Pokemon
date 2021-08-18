package com.Webflux.Pokedex;

import com.Webflux.Pokedex.Model.Pokemon;
import com.Webflux.Pokedex.repository.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations, PokemonRepository repository){
		return args -> {
			Flux<Pokemon> pokemonFlux= Flux.just(
					new Pokemon(null, "Blastoise","Água","Torrent",100.0),
			        new Pokemon(null, "Pikachu","Eletrico","Raio",20.0),
					new Pokemon(null, "Bulbasalro","Planta","Folhas Navalhas",15.0)
			).flatMap(repository::save);
			pokemonFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);
		};
	}
}
