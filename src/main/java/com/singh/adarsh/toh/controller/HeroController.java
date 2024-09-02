package com.singh.adarsh.toh.controller;

import com.singh.adarsh.toh.service.HeroService;
import com.singh.adarsh.toh.entity.Hero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hero")
@CrossOrigin(origins = "http://localhost:4200")
public class HeroController {
    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping
    public ResponseEntity<List<Hero>> getAll(){
        try{
            List<Hero> heroes = heroService.getAllHeroes();
            if (heroes.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return ResponseEntity.ok(heroes);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getDetails(@PathVariable int id){
        try{
            Hero hero = heroService.getById(id);
            if(hero==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(hero);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping()
    public ResponseEntity<Hero> create(@RequestBody Hero hero){
        try{
            Hero updatedHero = heroService.saveHero(hero, true);
            if(updatedHero==null){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(updatedHero, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hero> update(@PathVariable int id, @RequestBody Hero hero){
        try{
            Hero existingHero = heroService.getById(id);
            if(hero==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            existingHero.setName(hero.getName());
            Hero updatedHero = heroService.saveHero(existingHero, false);
            if(updatedHero==null){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ResponseEntity.ok(updatedHero);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try{
            Hero existingHero = heroService.getById(id);
            if(existingHero==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if(heroService.deleteById(id))return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
