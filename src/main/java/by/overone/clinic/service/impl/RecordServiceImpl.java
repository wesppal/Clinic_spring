package by.overone.clinic.service.impl;

import by.overone.clinic.dao.RecordDao;
import by.overone.clinic.dto.rec.RecordDTO;
import by.overone.clinic.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordDao recordDao;

    @Override
    public List<RecordDTO> getAllRecords() {
        return recordDao.getAllRecords().stream().map(rec -> new RecordDTO(rec.getRecord_date(),
                rec.getAdmission_date(), rec.getVisit_comment(), rec.getPet_id(),
                rec.getDoctor_id(), rec.getStatus())).collect(Collectors.toList());
    }

    @Override
    public RecordDTO addRecord(RecordDTO record) {
        return null;
    }

    @Override
    public List<RecordDTO> getRecordsByPet(long id) {
        return null;
    }

    @Override
    public List<RecordDTO> getRecordByDoctor(long id) {
        return null;
    }
}
