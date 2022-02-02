package by.overone.clinic.service;

import by.overone.clinic.dto.rec.RecordDTO;

import java.util.List;

public interface RecordService {
    List<RecordDTO> getAllRecords();

    RecordDTO addRecord(RecordDTO record);

    List<RecordDTO> getRecordsByPet(long id);

    List<RecordDTO> getRecordByDoctor(long id);
}
