package by.overone.clinic.model;

import by.overone.clinic.util.RecordStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private long record_id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime record_date = LocalDateTime.now();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime admission_date;
    private String visit_comment;
    private long pet_id;
    private long doctor_id;
    private RecordStatus status;
}
