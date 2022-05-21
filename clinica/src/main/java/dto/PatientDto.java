package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDto {
    private Integer id;
    @NotBlank(message = ("Isimda hatolik"))
    private String name;
    @NotNull(message = ("Familya mavjud bolish kerak"))
    @NotEmpty(message = ("Familyasi bosh bolsihi munkun emas"))
    private String surname;
    @NotNull(message =  ("Invlid birthday"))
    private LocalDate birthday;
    @NotBlank(message = ("Invalid contact"))
    @Size(min = 12, max = 13, message = ("contact togri kiriting *** 12 - 13 oraligida"))
    private String contact;
    private Integer age;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}