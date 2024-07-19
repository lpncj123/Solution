package cn.lp.controller;

import cn.lp.verificationcode.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.controller
 * @Author: lp
 * @CreateTime: 2024-07-09  17:03
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("verificationCodeController")
public class VerificationCodeController {
    @Autowired
    private VerificationCode verificationCode;
    @GetMapping("getVerificationCode")
    public void getVerificationCode() {
        verificationCode.VerificationCode("111111");
    }
}
