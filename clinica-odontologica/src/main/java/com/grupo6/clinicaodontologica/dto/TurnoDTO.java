package com.grupo6.clinicaodontologica.dto;



import com.grupo6.clinicaodontologica.persistence.model.Paciente;
import com.grupo6.clinicaodontologica.persistence.model.Turno;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Data
public class TurnoDTO implements Serializable {

    private Integer id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fecha;

    private PacienteDTO paciente;
    private OdontologoDTO odontologo;


    public TurnoDTO() {}


    public TurnoDTO(Integer id, LocalDateTime fecha, PacienteDTO paciente, OdontologoDTO odontologo) {
        this.id = id;
        this.fecha = fecha;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public TurnoDTO(Turno t){
        this.id=t.getId();
        this.fecha=t.getFecha();
        this.paciente = new PacienteDTO(t.getPaciente());
        this.odontologo = new OdontologoDTO(t.getOdontologo());
    }

    public Turno toEntity(){
        Turno entity = new Turno();

        entity.setId(id);
        entity.setFecha(fecha);

        entity.setOdontologo(odontologo.toEntity());
        entity.setPaciente(paciente.toEntity());

        return entity;
    }






}