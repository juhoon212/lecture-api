package twentyoz.viven.webapi.dts.vi.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtsLectureQuestDto {

    private UUID lectureQuestId;

    private List<String> answer;



}
