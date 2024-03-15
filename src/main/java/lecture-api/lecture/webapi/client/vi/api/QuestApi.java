package twentyoz.viven.webapi.client.vi.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import twentyoz.viven.feature.vi.service.LectureExamService;
import twentyoz.viven.feature.vi.service.LectureQuestResultService;
import twentyoz.viven.feature.vi.service.LectureQuestService;
import twentyoz.viven.webapi.client.vi.form.LectureExamForm;
import twentyoz.viven.webapi.client.vi.form.LectureQuestForm;
import twentyoz.viven.webapi.client.vi.mapper.LectureQuestFormMapper;
import twentyoz.viven.webapi.client.vi.predicate.LectureExamFormPredicate;
import twentyoz.viven.webapi.dts.vi.form.*;
import twentyoz.viven.webapi.dts.vi.form.QuestRequestForm.Input;
import twentyoz.viven.webapi.dts.vi.form.QuestRequestForm.Output;

import javax.validation.Valid;

@Api(
        value = "DtsQuest",
        tags = {"DtsQuest"})
@RequestMapping(value = "/dts/vi/exam", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class QuestApi {

    private final LectureExamService lectureExamService;
    private final LectureQuestFormMapper formMapper;
    private final LectureQuestService service;
    private final LectureQuestResultService lectureQuestResultService;

    @SneakyThrows
    @ApiOperation("문제 목록 조회")
    @GetMapping("/get-exam-list")
    public DtsLectureExamDto getExamItemList(@Valid LectureExamForm.Input.DtsAll in) {

        return lectureExamService.getDtsSupport(LectureExamFormPredicate.search(in));
    }


    @SneakyThrows
    @ApiOperation("문제 항목 조회")
    @GetMapping("/get-exam-request")
    public LectureQuestForm.Output.Get getExamRequestMultiChoice(@Valid LectureExamForm.Input.DtsGet in) {

        return formMapper.toGet(service.get(in.getLectureQuestId()));
    }

    @SneakyThrows
    @ApiOperation("문제 답변")
    @GetMapping("/get-quest-answer")
    public DtsLectureQuestDto getExamAnswer(@Valid LectureExamForm.Input.DtsGet in) {

        return service.getDtsSupport(in.getLectureQuestId());

    }

    @SneakyThrows
    @ApiOperation("문제 채점")
    @GetMapping("/get-quest-result")
    public DtsLectureQuestResultDto examResultForm(@Valid LectureQuestForm.Input.DtsGet in) {

        return lectureQuestResultService.markingQuests(in.getLectureQuestId(), in.getAnswer());
    }



}
