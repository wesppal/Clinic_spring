package by.overone.clinic.model;

import by.overone.clinic.util.RecordStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;

@Entity
@Table(name = "medical_card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long record_id;
    private Timestamp record_data;
    private Timestamp admission_data;
    private String visit_comment;
    @NotNull
    private long pet_id;
    @NotNull
    private long doctor_id;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private RecordStatus status;
}
