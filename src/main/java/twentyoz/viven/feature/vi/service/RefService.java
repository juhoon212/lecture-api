package twentyoz.viven.feature.vi.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twentyoz.viven.feature.Code;
import twentyoz.viven.feature.vi.mapper.RefMapper;
import twentyoz.viven.feature.vi.model.QViRef;
import twentyoz.viven.feature.vi.model.ViRef;
import twentyoz.viven.feature.vi.model.ViRefDto;
import twentyoz.viven.feature.vi.repository.ViRefRepository;
import twentyoz.viven.feature.vi.repository.ViRefRepositorySupport;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RefService {

    private final ViRefRepository repository;
    private final ViRefRepositorySupport repositorySupport;
    private final RefMapper mapper;

    /** 자료 추가 */

    public ViRef addRef(ViRef entity, UUID mbrId) {
        entity.setMbrId(mbrId);
        return repository.save(entity);
    }
    /** 자료 조회 */
    @Transactional(readOnly = true)
    @SneakyThrows
    public ViRef get(UUID id) {
        return repository.findOne(
                new BooleanBuilder(QViRef.viRef.refId.eq(id))
                        .and(QViRef.viRef.delYn.eq("N"))
        ).orElseThrow(() -> new IllegalStateException("자료가 없습니다"));
    }




    /** 자료 수정 */
    public ViRef modifyRef(ViRef entity, UUID mbrId) {
        ViRef findRef = get(entity.getRefId());

        entity.setMbrId(mbrId);

        if(findRef == null) {
            throw new IllegalStateException("자료가 없습니다");
        }


        return repository.save(mapper.modify(entity, findRef));
    }



    /** 자료 삭제 */
    public ViRef delete(UUID id) {
        ViRef findRef = get(id);

        if(findRef == null) {
            throw new IllegalStateException("삭제할 자료가 없습니다");
        }

        findRef.delTrue();
        return repository.save(findRef);
    }

    @Transactional(readOnly = true)
    public List<ViRefDto> getList(Predicate search, UUID mbrId) {
        return repositorySupport.getList(search, mbrId);
    }



    @Transactional(readOnly = true)
    public Page<ViRefDto> getPage(Predicate search, Pageable page, UUID mbrId) {
        return repositorySupport.getPage(search, page, mbrId);
    }



}

