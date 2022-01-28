package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.RecordDao;
import by.overone.clinic.model.Record;
import by.overone.clinic.util.RecordStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecordDaoImpl implements RecordDao {

    private final JdbcTemplate jdbcTemplate;

    private final static String ADD_RECORD_SQL = "INSERT INTO medical_card SET VALUE (0,?,?,?,?,?,?)";

    @Override
    public List<Record> getAllRecords() {
        return null;
    }

    @Override
    public Record addRecord(Record record) {
        Timestamp record_date = new Timestamp(System.currentTimeMillis());
        Timestamp admission_date = Timestamp.valueOf(LocalDateTime.of(2022, 01, 05, 15, 30));
        record.setRecord_data(record_date);
        record.setAdmission_data(admission_date);
        record.setVisit_comment("Will be added after.");
        record.setDoctor_id(4);
        record.setPet_id(3);
        record.setStatus(RecordStatus.RECORDED);
        jdbcTemplate.update(ADD_RECORD_SQL,record.getRecord_data(),record.getAdmission_data(),
                record.getVisit_comment(),record.getPet_id(),record.getDoctor_id());
        return record;
    }

    @Override
    public List<Record> getRecordByPet() {
        return null;
    }

    @Override
    public List<Record> getRecordByUser() {
        return null;
    }
}
