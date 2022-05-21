package Service;

import dto.VisitDto;
import exception.BadRequest;
import model.Visit;
import org.springframework.stereotype.Service;
import repository.VisitRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VisitService {

    private VisitRepository visitRepository;
    private DoctorService doctorService;

    public VisitService(VisitRepository visitRepository,
                        DoctorService doctorService,
                        PatientService patientService) {
        this.visitRepository = visitRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    private PatientService patientService;

    public VisitDto get(Integer id){
        Visit visit = getEntity(id);
        VisitDto visitDto = new VisitDto();
        visitDto.setPatient(patientService.get(visit.getPatientId()));
        visitDto.setDoctor(doctorService.get(visit.getDoctorId()));
        visitDto.setDiagnosis(visit.getDiagnosis());
        return visitDto;
    }


    public Visit getEntity(Integer id){
        Optional<Visit> optional = visitRepository.findById(id);
        if (optional.isEmpty()){
            throw new BadRequest("Visit not found");
        }
        return optional.get();
    }
    public VisitDto create(VisitDto dto){
        Visit visit = new Visit();
        doctorService.check(dto.getDoctorId());
        patientService.getEntity(dto.getPatientId());
        visit.setPatientId(dto.getPatientId());

        visit.setDiagnosis(dto.getDiagnosis());
        visit.setCreatedAt(LocalDateTime.now());
        visit.setStatus(true);
        visitRepository.save(visit);
        return dto;
    }
    public boolean update(Integer id, VisitDto dto){
        Visit visit = getEntity(id);
        doctorService.check(dto.getDoctorId());
        patientService.getEntity(dto.getPatientId());
        visit.setPatientId(dto.getPatientId());

        visit.setDiagnosis(dto.getDiagnosis());
        visitRepository.save(visit);
        return true;
    }

    public boolean delete(Integer id){
        Visit visit = getEntity(id);
        visitRepository.delete(visit);
        return true;
    }
}
