package context;

import dtos.LecturerDto;
import lombok.Getter;
import lombok.Setter;


public class ContextHandler {

    private ContextHandler() {

    }

    @Getter
    @Setter
    public static LecturerDto lecturerDto;

}
