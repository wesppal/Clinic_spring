package by.overone.clinic.dto.rec;

import by.overone.clinic.util.RecordStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RecordDTO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime record_date;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime admission_date;
    private String visit_comment;
    @Min(1)
    private long pet_id;
    @Min(1)
    private long doctor_id;
    private RecordStatus status;
}
