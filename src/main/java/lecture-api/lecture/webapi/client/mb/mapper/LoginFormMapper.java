package twentyoz.viven.webapi.client.mb.mapper;

import twentyoz.viven.feature.mb.model.MbMbr;
import twentyoz.viven.feature.mb.model.MbrDto;
import twentyoz.viven.webapi.client.mb.form.LoginForm.Output.UserInfo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(implementationName = "LoginFormMapperImpl", builder = @Builder(disableBuilder = true))
public abstract class LoginFormMapper {

  public abstract UserInfo toUserInfo(MbMbr in);

  public abstract UserInfo toUserInfo(MbrDto in);
}
