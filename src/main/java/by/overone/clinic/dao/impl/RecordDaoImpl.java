package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.PetDao;
import by.overone.clinic.dao.RecordDao;
import by.overone.clinic.dao.UserDao;
import by.overone.clinic.exception.EntityNotFoundException;
import by.overone.clinic.exception.ExceptionCode;
import by.overone.clinic.model.Record;
import by.overone.clinic.model.User;
import by.overone.clinic.util.RecordStatus;
import by.overone.clinic.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

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
        User doctor = userDao.getUserById(record.getDoctor_id())
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
        if (!doctor.getRole().equals(Role.DOCTOR)) {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_DOCTOR.getErrorCode());
        }
        petDao.getPetById(record.getPet_id())
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_PET.getErrorCode()));
        record.setStatus(RecordStatus.RECORDED);
        record.setVisit_comment("The pet is " + record.getStatus().toString().toLowerCase(Locale.ROOT)
                + " on time: " + record.getAdmission_date().toString().replace('T',' '));

        Number id = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(record));
        record.setRecord_id(id.longValue());
        return record;
    }

    @Override
    public List<Record> getRecordsByPet(long id) {
        petDao.getPetById(id);
        return jdbcTemplate.query(GET_RECORDS_BY_PET_ID_SQL, new BeanPropertyRowMapper<>(Record.class), id);
    }

    @Override
    public List<Record> getRecordByDoctor(long id) {
        User doctor = userDao.getUserById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
        if (!doctor.getRole().equals(Role.DOCTOR)) {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_DOCTOR.getErrorCode());
        }
        return jdbcTemplate.query(GET_RECORDS_BY_DOCTOR_ID_SQL, new BeanPropertyRowMapper<>(Record.class), id);
    }
}
