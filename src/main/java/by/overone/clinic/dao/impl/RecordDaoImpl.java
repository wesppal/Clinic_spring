package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.PetDao;
import by.overone.clinic.dao.RecordDao;
import by.overone.clinic.dao.UserDao;
import by.overone.clinic.exception.EntityNotFoundException;
import by.overone.clinic.exception.ExceptionCode;
import by.overone.clinic.model.Record;
import by.overone.clinic.model.User;
import by.overone.clinic.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        if (!doctor.getRole().equals(Role.DOCTOR.toString())) {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_DOCTOR.getErrorCode());
        }
        petDao.getPetById(record.getPet_id())
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_PET.getErrorCode()));
//        LocalDateTime record_date = LocalDateTime.now();
//        LocalDateTime admission_date = LocalDateTime.of(2022, 0, 5, 15, 30);
//        record.setRecord_data(record_date);
//        record.setAdmission_data(admission_date);
        record.setVisit_comment("Will be added after.");
        record.setStatus("recorded");
        jdbcTemplate.update(ADD_RECORD_SQL, record.getRecord_date(), record.getAdmission_date(),
                record.getVisit_comment(), record.getPet_id(), record.getDoctor_id(), record.getStatus());
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
