package com.milkit.app.api.userinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.milkit.app.api.AbstractApiController;
import com.milkit.app.common.exception.handler.ApiResponseEntityExceptionHandler;
import com.milkit.app.common.response.GenericResponse;
import com.milkit.app.domain.userinfo.UserInfo;
import com.milkit.app.domain.userinfo.service.UserInfoServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@Api(tags = "2. 사용자 정보 (ROLE_ADMIN 접근가능)", value = "UserInfoController")
public class UserInfoController extends AbstractApiController {

    @Autowired
    private UserInfoServiceImpl memberServie;

    @GetMapping("/api/userinfo")
    @ApiOperation(value = "사용자정보 전체조회 ", notes = "사용자정보 전체 목록을 조회한다.")
    public ResponseEntity<GenericResponse<List<UserInfo>>> userinfo() throws Exception {
        return apiResponse(() -> memberServie.selectAll());
    }
    
}