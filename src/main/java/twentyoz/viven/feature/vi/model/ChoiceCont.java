package twentyoz.viven.feature.vi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoiceCont {

    private UUID id;
    private Integer sortOrd;
    private String title;
}
