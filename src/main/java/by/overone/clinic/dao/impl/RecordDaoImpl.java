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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
@RequiredArgsConstructor
public class RecordDaoImpl implements RecordDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;
    private final PetDao petDao;
    private final static String ADD_RECORD_SQL = "INSERT INTO medical_card VALUES (0,?,?,?,?,?,?)";
    private final static String GET_ALL_RECORD_SQL = "SELECT * from medical_card";

    @Override
    public List<Record> getAllRecords() {
        return jdbcTemplate.query(GET_ALL_RECORD_SQL, new BeanPropertyRowMapper<>(Record.class));
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

        jdbcTemplate.update(ADD_RECORD_SQL, record.getRecord_date(), record.getAdmission_date(),
                record.getVisit_comment(), record.getPet_id(), record.getDoctor_id(), record.getStatus().toString());
        return record;
    }

    @Override
    public List<Record> getRecordByPet() {
        return null;
    }

    @Override
    public List<Record> getRecordByDoctor() {
        return null;
    }
}
