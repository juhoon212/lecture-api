package twentyoz.viven.feature.vi.mapper;

import org.mapstruct.Mapper;
import twentyoz.viven.feature.vi.model.ViExamItem;
import twentyoz.viven.webapi.client.vi.form.ExamItemForm;


@Mapper
public abstract class ViExamItemMapper {

  public ViExamItem modify(ExamItemForm.Input.ModifyAll.Modify in, ViExamItem out) {
    if (in == null) {
      return null;
    }

    // 배점
    if (in.getScore() != null) {
      out.setScore(in.getScore());
    }

    // 정렬순서

    if(in.getSortOrd() != null) {

      out.setSortOrd(in.getSortOrd());
//      if(out.getSortOrd().equals(in.getSortOrd())) {
//
//        out.setSortOrd(in.getSortOrd() + 1);
//      }
    }
    return out;
  }


}
