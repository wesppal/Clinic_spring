package by.overone.clinic.dao;

import by.overone.clinic.model.Record;

import java.util.List;

public interface RecordDao {
    List<Record> getAllRecords();

    Record addRecord(Record record);

    List<Record> getRecordByPet();

    List<Record> getRecordByDoctor();
}
