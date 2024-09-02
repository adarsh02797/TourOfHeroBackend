package com.singh.adarsh.toh.service;

import com.singh.adarsh.toh.repository.HeroRepository;
import com.singh.adarsh.toh.entity.Hero;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {
    private final HeroRepository heroRepository;

    private final SequenceGeneratorService sequenceGeneratorService;

    public HeroService(HeroRepository heroRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.heroRepository = heroRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public List<Hero> getAllHeroes(){
        return heroRepository.findAll();
    }

    public Hero getById(int id){
        return heroRepository.findById(id).orElse(null);
    }

    public Hero saveHero(Hero hero,Boolean isNew){
        try {
            if(isNew)hero.setId(sequenceGeneratorService.generateSequence(Hero.SEQUENCE_NAME));
            return heroRepository.save(hero);
        }catch(Exception e){
            return null;
        }
    }

    public boolean deleteById(int id){
        try{
            heroRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
