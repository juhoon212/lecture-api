package twentyoz.viven.webapi.client.sy.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.util.Locale;
import twentyoz.viven.feature.sy.model.QSyAttachFile;
import twentyoz.viven.webapi.client.sy.form.AttachFileForm.Input.GetAll;

public class AttachFileFormPredicate {

  public static Predicate search(GetAll in) {
    BooleanBuilder builder = new BooleanBuilder();
    QSyAttachFile qSyAttachFile = QSyAttachFile.syAttachFile;

    // 첨부파일그룹식별번호
    builder.and(qSyAttachFile.attachFileGroupId.eq(in.getAttachFileGroupId()));
    builder.and(qSyAttachFile.delYn.eq("N"));

    if (in.getAttachDivVal() != null) {
      builder.and(
          qSyAttachFile
              .attachDivVal
              .lower()
              .contains(in.getAttachDivVal().toLowerCase(Locale.ROOT)));
    }

    if (in.getAttachFileId() != null) {
      builder.and(qSyAttachFile.attachFileId.eq(in.getAttachFileId()));
    }

    return builder;
  }
}
