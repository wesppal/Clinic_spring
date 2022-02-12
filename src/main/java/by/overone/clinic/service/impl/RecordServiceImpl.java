package by.overone.clinic.service.impl;

import by.overone.clinic.dao.PetDao;
import by.overone.clinic.dao.RecordDao;
import by.overone.clinic.dao.UserDao;
import by.overone.clinic.dto.rec.RecordDTO;
import by.overone.clinic.exception.EntityNotFoundException;
import by.overone.clinic.exception.ExceptionCode;
import by.overone.clinic.model.Record;
import by.overone.clinic.model.User;
import by.overone.clinic.service.RecordService;
import by.overone.clinic.util.RecordStatus;
import by.overone.clinic.util.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordDao recordDao;
    private final ModelMapper modelMapper;
    private final UserDao userDao;
    private final PetDao petDao;

    @Override
    public List<RecordDTO> getAllRecords() {
        return recordDao.getAllRecords().stream().map(rec -> new by.overone.clinic.dto.rec.RecordDTO(rec.getRecord_date(),
                rec.getAdmission_date(), rec.getVisit_comment(), rec.getPet_id(),
                rec.getDoctor_id(), rec.getStatus())).collect(Collectors.toList());
    }

    @Override
    public RecordDTO addRecord(RecordDTO recordDTO) {

        User doctor = userDao.getUserById(recordDTO.getDoctor_id())
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
        if (!doctor.getRole().equals(Role.DOCTOR)) {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_DOCTOR.getErrorCode());
        }
        petDao.getPetById(recordDTO.getPet_id())
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_PET.getErrorCode()));
        recordDTO.setStatus(RecordStatus.RECORDED);
        recordDTO.setVisit_comment("The pet is " + recordDTO.getStatus().toString().toLowerCase(Locale.ROOT)
                + " on time: " + recordDTO.getAdmission_date().toString().replace('T', ' '));
        Record record;
        record = modelMapper.map(recordDTO, Record.class);
        recordDao.addRecord(record);
        recordDTO.setRecord_date(record.getRecord_date());
        return recordDTO;
    }

    @Override
    public List<RecordDTO> getRecordsByPet(long id) {
        petDao.getPetById(id);
        List<RecordDTO> recordsDTO = new ArrayList<>();
        modelMapper.map(recordDao.getRecordsByPet(id), RecordDTO.class);
        List<Record> records = recordDao.getRecordsByPet(id);
        for (int i = 0; i < records.size(); i++) {
            recordsDTO.add(modelMapper.map(records.get(i), RecordDTO.class));
        }
        return recordsDTO;
    }

    @Override
    public List<RecordDTO> getRecordsByDoctor(long id) {
        User doctor = userDao.getUserById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
        if (!doctor.getRole().equals(Role.DOCTOR)) {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_DOCTOR.getErrorCode());
        }
        List<Record> records = recordDao.getRecordsByDoctor(id);
        List<RecordDTO> recordsDTO = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            recordsDTO.add(modelMapper.map(records.get(i), RecordDTO.class));
        }
        return recordsDTO;
    }
}
