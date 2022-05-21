package Service;

import dto.DoctorDto;
import exception.BadRequest;
import model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DoctorRepository;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
     private DoctorRepository doctorRepository;

    public DoctorDto get(Integer id){
        Doctor doctor = check(id);
        DoctorDto dto = new DoctorDto();
        dto.setName(doctor.getName());
        dto.setSurname(doctor.getSurname());
        dto.setContact(doctor.getContact());
        dto.setExperience(doctor.getExperience());
        dto.setDirection(doctor.getDirection());
        dto.setStatus(doctor.getStatus());
        return dto;
    }

    public DoctorDto create(DoctorDto dto){
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSurname(dto.getSurname());
        doctor.setContact(dto.getContact());
        doctor.setExperience(dto.getExperience());
        doctor.setDirection(dto.getDirection());
        doctor.setCreatedAt(LocalDateTime.now());
        doctor.setStatus(true);
        doctorRepository.save(doctor);
        return dto;
    }
    public boolean update(Integer id, DoctorDto doctor){
        Doctor update = check(id);
        update.setName(doctor.getName());
        update.setSurname(doctor.getSurname());
        update.setContact(doctor.getContact());
        update.setDirection(doctor.getDirection());
        update.setExperience(doctor.getExperience());
        update.setUpdatedAt(LocalDateTime.now());
        doctorRepository.save(update);
        return true;
    }
    public boolean delete(Integer id){
        Doctor doctor = check(id);
        doctor.setDeletedAt(LocalDateTime.now());
        doctorRepository.save(doctor);
        return true;
    }

    public Doctor check(Integer id){
        Optional<Doctor> optional = doctorRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty() || optional.get().getDeletedAt() != null){
            throw new BadRequest("Doctor not found");
        }
        return optional.get();
    }
}
