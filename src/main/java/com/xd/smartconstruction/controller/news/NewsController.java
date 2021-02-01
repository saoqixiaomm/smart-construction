package com.xd.smartconstruction.controller.news;

import com.xd.smartconstruction.common.utils.ResultBuilderUtils;
import com.xd.smartconstruction.common.validate.EntityValidator;
import com.xd.smartconstruction.common.validate.Insert;
import com.xd.smartconstruction.pojo.dto.ResultVO;
import com.xd.smartconstruction.pojo.vo.request.account.UserEditReqVO;
import com.xd.smartconstruction.pojo.vo.response.account.UserEditRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mm
 * @Date 2021-02-01 15:19
 */
@Api("新闻模块")
@RestController
@RequestMapping("/news")
@Slf4j
public class NewsController {

    @ApiOperation("添加新闻") //示例具体需要修改
    @PostMapping(value = "/addUser")
    public ResultVO<UserEditRespVO> add(@RequestBody UserEditReqVO requestVO) {
        //根据需要 在插入的这个group下面进行校验
        EntityValidator.validateAndThrow(requestVO, Insert.class);
        return ResultBuilderUtils.buildSuccess(UserEditRespVO.builder().id("").build());
    }
}
