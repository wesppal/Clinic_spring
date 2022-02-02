package by.overone.clinic.service;

import by.overone.clinic.model.Record;

import java.util.List;

public interface RecordService {
    List<Record> getAllRecords();

    Record addRecord(Record record);

    List<Record> getRecordsByPet(long id);

    List<Record> getRecordByDoctor(long id);
}
