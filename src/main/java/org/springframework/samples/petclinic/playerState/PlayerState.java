package org.springframework.samples.petclinic.playerState;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.petclinic.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "PlayerState")
public class PlayerState extends BaseEntity {

    @Column(name = "iron")
    @NotNull
    @Min(value = 0)
    public Integer iron= 0;

    @Column(name = "gold")
    @NotNull
    @Min(value = 0)
    public Integer gold=0;

    @Column(name = "steel")
    @NotNull
    @Min(value = 0)
    public Integer steel=0;

    @Column(name = "object")
    @NotNull
    @Min(value = 0)
    public Integer object=0;

    @Column(name = "medal")
    @NotNull
    @Min(value = 0)
    public Integer medal=0;

    //12 para los trabajadores disponibles pero no usados
    //-13 para los trabajadores no disponibles
    //[0-8] posiciones de la mina, contando desde la esquina izquierda superior en dirección de lectura
    //[9-11] posiciones montones cartas especiales
    //Las posiciones anteriores en negativo indican que ya han realizado la acción, utilizar valor absoluto para posición en el canvas

    @Column(name = "worker0")
    @NotNull
    @Max(value = 12)
    @Min(value = -13)
    public Integer worker0 = 12;

    @Column(name = "worker1")
    @NotNull
    @Max(value = 12)
    @Min(value = -13)
    public Integer worker1 = 12;

    @Column(name = "worker2")
    @NotNull
    @Max(value = 12)
    @Min(value = -13)
    public Integer worker2 = -13;

    @Column(name = "worker3")
    @NotNull
    @Max(value = 12)
    @Min(value = -13)
    public Integer worker3 = -13;

    @JsonIgnore
    public List<Integer> getWorkerList(){
        return new ArrayList<>(List.of(worker0, worker1, worker2, worker3));
    }
    
    @JsonIgnore
    public void setWorkerList(List<Integer> workerList){
        this.worker0=workerList.get(0);
        this.worker1=workerList.get(1);
        this.worker2=workerList.get(2);
        this.worker3=workerList.get(3);
    }

    @JsonIgnore
    public List<Integer> getResourcesList(){
        return new ArrayList<>(List.of(iron, gold, steel, object, medal));
    }
}
