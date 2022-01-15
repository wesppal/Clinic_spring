package by.overone.clinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private long record_id;
    private Timestamp record_data;
    private Timestamp admission_data;
    private String visit_comment;
    private long pet_id;
    private long doctor_id;
    private String status;
}
