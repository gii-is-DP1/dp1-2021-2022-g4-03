package org.springframework.samples.petclinic.estadoJugador;


import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Table(name = "entidades")
public class EstadoJugador extends BaseEntity{

    @Column(name="iron")
    @NotNull
    @Min(value = 0)
    public Integer iron;

    @Column(name="gold")
    @NotNull
    @Min(value = 0)
    public Integer gold;

    @Column(name="steel")
    @NotNull
    @Min(value = 0)
    public Integer steel;

    @Column(name="object")
    @NotNull
    @Min(value = 0)
    public Integer object;

    @Column(name="medal")
    @NotNull
    @Min(value = 0)
    public Integer medal;   

    @Column(name="active")
    @NotNull
    public Boolean active;

    @Column(name="workers")
    @NotNull
    @Length(min = 4,max = 4)
    public Integer[] workers = {9, 9, -1, -1};
    //9 para los trabajadores disponibles pero no usados
    //-1 para los trabajadores no disponibles 
}
