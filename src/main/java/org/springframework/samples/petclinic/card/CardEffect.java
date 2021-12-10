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
    
    public CardEffect() {
        effects.put(1,x->x);
    }

    public static Map<Integer,Function<Game,Game>> getEffects() {
        return effects;
    }


    
    

}
