package dtos;

import entities.LectureType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LectureTypeDto {
    private Integer id;
    private String lectureType;

    public LectureTypeDto(LectureType lectureType) {
        this.id = lectureType.getId();
        this.lectureType = lectureType.getLectureTypeName();
    }

    public LectureType toEntity() {
        return new LectureType(id, lectureType);
    }
}
