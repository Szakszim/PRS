package others;

import enums.SelectionPopupEventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SelectionPopupEvent {

    private SelectionPopupEventType selectionPopupEventType;
    private Integer selectedRow;
}
