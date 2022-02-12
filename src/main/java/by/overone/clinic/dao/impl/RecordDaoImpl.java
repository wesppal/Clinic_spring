package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.PetDao;
import by.overone.clinic.dao.RecordDao;
import by.overone.clinic.dao.UserDao;
import by.overone.clinic.model.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecordDaoImpl implements RecordDao {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("record")
    private SimpleJdbcInsert simpleJdbcInsert;
    private final UserDao userDao;
    private final PetDao petDao;

    private final static String GET_RECORDS_BY_PET_ID_SQL = "SELECT * from medical_card WHERE pet_id=?";
    private final static String GET_RECORDS_BY_DOCTOR_ID_SQL = "SELECT * from medical_card WHERE doctor_id=?";
    private final static String GET_ALL_RECORDS_SQL = "SELECT * from medical_card";

    @Override
    public List<Record> getAllRecords() {
        return jdbcTemplate.query(GET_ALL_RECORDS_SQL, new BeanPropertyRowMapper<>(Record.class));
    }

    @Override
    public Record addRecord(Record record) {
        Number id = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(record));
        record.setRecord_id(id.longValue());
        return record;
    }

    @Override
    public List<Record> getRecordsByPet(long id) {
        return jdbcTemplate.query(GET_RECORDS_BY_PET_ID_SQL, new BeanPropertyRowMapper<>(Record.class), id);
    }

    @Override
    public List<Record> getRecordsByDoctor(long id) {
        return jdbcTemplate.query(GET_RECORDS_BY_DOCTOR_ID_SQL, new BeanPropertyRowMapper<>(Record.class), id);
    }
}
