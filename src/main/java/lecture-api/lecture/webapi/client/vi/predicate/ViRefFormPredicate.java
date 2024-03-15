package twentyoz.viven.webapi.client.vi.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import twentyoz.viven.feature.vi.model.QViRef;


import static twentyoz.viven.webapi.client.vi.form.RefForm.Input.*;

public class ViRefFormPredicate {

    public static Predicate search(GetAll in) {

        BooleanBuilder builder = new BooleanBuilder();
        QViRef qViRef = QViRef.viRef;

        builder.and(qViRef.delYn.eq("N"));

        if (StringUtils.isNotEmpty(in.getRefName())) {
            builder.and(qViRef.refName.containsIgnoreCase(in.getRefName()));
        }

        if(StringUtils.isNotEmpty(in.getDescCont())) {
            builder.and(qViRef.descCont.containsIgnoreCase(in.getDescCont()));
        }

        return builder;
    }
}
