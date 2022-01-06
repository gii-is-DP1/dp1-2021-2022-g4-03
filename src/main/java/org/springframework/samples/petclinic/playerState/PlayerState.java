package org.springframework.samples.petclinic.playerState;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "active")
    @NotNull
    public Boolean active=false;

    //12 para los trabajadores disponibles pero no usados
    //-1 para los trabajadores no disponibles
    //[0-8] posiciones de la mina, contando desde la esquina izquierda superior en dirección de lectura
    //[9-11] posiciones montones cartas especiales

    @Column(name = "worker1")
    @NotNull
    @Max(value = 9)
    @Min(value = -1)
    public Integer worker1 = 12;

    @Column(name = "worker2")
    @NotNull
    @Max(value = 9)
    @Min(value = -1)
    public Integer worker2 = 12;

    @Column(name = "worker3")
    @NotNull
    @Max(value = 9)
    @Min(value = -1)
    public Integer worker3 = -1;

    @Column(name = "worker4")
    @NotNull
    @Max(value = 9)
    @Min(value = -1)
    public Integer worker4 = -1;

}
