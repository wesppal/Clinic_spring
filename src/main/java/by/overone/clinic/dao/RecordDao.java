package by.overone.clinic.dao;

import by.overone.clinic.model.Record;

import java.util.List;

public interface RecordDao {
    List<Record> getAllRecords();

    Record addRecord(Record record);

    List<Record> getRecordsByPet(long id);

    List<Record> getRecordsByDoctor(long id);
}
