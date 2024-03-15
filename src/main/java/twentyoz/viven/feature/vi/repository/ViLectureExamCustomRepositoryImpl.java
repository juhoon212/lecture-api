package twentyoz.viven.feature.vi.repository;


import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import twentyoz.viven.feature.vi.model.ViLectureExamResult;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ViLectureExamCustomRepositoryImpl implements ViLectureExamCustomRepository{

    private final EntityManager em;
    @Override
    public List<ViLectureExamResult> findByCurrentTime(DateTime time) {

        return em.createQuery("select lm " +
                        "from ViLectureExamResult lm where lm.examStartDt < :now ", ViLectureExamResult.class)
                .setParameter("now", time)
                .getResultList();
    }
}
