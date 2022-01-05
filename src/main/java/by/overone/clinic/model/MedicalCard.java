package by.overone.clinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCard {
    private long record_id;
    private String record_data;
    private String doctor;
    private String visit_comment;
    private long pet_id;
    private long user_id;
    private long doctor_id;
}
