package twentyoz.viven.webapi.client.vi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;

import twentyoz.viven.feature.common.security.UserInfo;
import twentyoz.viven.webapi.client.vi.form.FrndReqForm;
import twentyoz.viven.webapi.client.vi.mapper.FrndReqFormMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(
    value = "FrndReq",
    tags = {"FrndReq"})
@RequestMapping(value = "/vi/frnd-req", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("FrndReqApi")
@RequiredArgsConstructor
public class FrndReqApi {

  private final FrndReqFormMapper formMapper;

  private final FrndReqService service;

  @SneakyThrows
  @ApiOperation("친구요청 목록 조회")
  @GetMapping("/get-req-list")
  public List<FrndReqDto> getReqList() {
    return service.getSupportReqList(UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("친구요청 페이징 조회")
  @GetMapping("/get-req-page")
  public Page<FrndReqDto> getReqPage(Pageable page) {
    return service.getSupportReqPage(page, UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("친구 요청대기 목록 조회")
  @GetMapping("/get-res-list")
  public List<FrndReqDto> getResList() {
    return service.getSupportResList(UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("친구 요청대기 페이징 조회")
  @GetMapping("/get-res-page")
  public Page<FrndReqDto> getResPage(Pageable page) {
    return service.getSupportResPage(page, UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("친구 요청")
  @PostMapping("/request")
  public FrndReqForm.Output.Get request(@Valid @RequestBody FrndReqForm.Input.Request in) {
    return formMapper.toGet(service.request(in.getResMbrId(), UserInfo.getUserId()));
  }

  @SneakyThrows
  @ApiOperation("친구 요청 수락")
  @PostMapping("/accept")
  public void accept(@Valid @RequestBody FrndReqForm.Input.Respond in) {
    service.acceptAll(in.getIds(), UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("친구 요청 거절")
  @PostMapping("/decline")
  public void decline(@Valid @RequestBody FrndReqForm.Input.Respond in) {
    service.declineAll(in.getIds(), UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("대기중인 친구요청 삭제 (로그인한 회원 본인이 요청한 내역 삭제)")
  @PostMapping("/remove/my-request")
  public void removeReq(@Valid @RequestBody FrndReqForm.Input.Remove in) {
    service.removeReq(in.getIds(), UserInfo.getUserId());
  }

  @SneakyThrows
  @ApiOperation("친구요청 받은 내역 삭제")
  @PostMapping("/remove/request")
  public void removeRes(@Valid @RequestBody FrndReqForm.Input.Remove in) {
    service.removeRes(in.getIds(), UserInfo.getUserId());
  }
}
