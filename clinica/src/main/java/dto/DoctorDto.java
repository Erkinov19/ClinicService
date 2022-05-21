package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDto {

    private Integer id;

    @NotBlank(message = ("Isimda hatolik, togri kirgizin"))
    private String name;

    @NotBlank(message = ("Familyada hatolik, togri kirgizin"))
    private String surname;

    @NotBlank(message = ("Yonalishda Hatolik"))
    private String direction;

    @NotBlank(message = ("Togri kiriting"))
    @Size(min = 12, max = 13, message =("Nomer uzunligi 12 - 13 bolishi kerak"))
    private String contact;

    private Integer experience;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
