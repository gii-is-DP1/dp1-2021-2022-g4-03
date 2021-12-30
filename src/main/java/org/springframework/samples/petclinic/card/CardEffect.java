package org.springframework.samples.petclinic.card;

import java.util.Map;
import java.util.function.Function;

import org.springframework.samples.petclinic.game.Game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardEffect {
    
    private static Map<Integer,Function<Game,Game>> effects;
    
    protected CardEffect() {
        effects.put(1,x->x);
        effects.put(2,x->x);
    }

    public static Map<Integer,Function<Game,Game>> getEffects() {
        return effects;
    }
    
    public static Function<Game,Game> getEffectById(Integer id) {
        return effects.get(id);
    }
    

}
