package org.springframework.samples.petclinic.playerState;


import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "playerState")
public class playerState extends BaseEntity{

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

    @Column(name="worker1")
    @NotNull
    @Max(value = 9)
    @Min(value = -1)
    public Integer worker1=9;

    @Column(name="worker2")
    @NotNull
    @Max(value = 9)
    @Min(value = -1)
    public Integer worker2=9;

    @Column(name="worker3")
    @NotNull
    @Max(value = 9)
    @Min(value = -1)
    public Integer worker3=-1;

    @Column(name="worker4")
    @NotNull
    @Max(value = 9)
    @Min(value = -1)
    public Integer worker4=-1;
    //9 para los trabajadores disponibles pero no usados
    //-1 para los trabajadores no disponibles 
}
