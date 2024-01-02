/*
 * FileName: MailController.java
 * Description: 메일을 보내기 위한 Controller 입니다.
 * Author: 임석현
 */

package com.ezen03.tieshoe;

import com.ezen03.tieshoe.helper.MailHelper;
import com.ezen03.tieshoe.helper.RegexHelper;
import com.ezen03.tieshoe.helper.WebHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MailController {

    @Autowired
    WebHelper webHelper;

    @Autowired
    RegexHelper regexHelper;

    @Autowired
    MailHelper mailHelper;

    /**
     * 발송 폼 구성 페이지
     */
    @RequestMapping(value = "/admin/sendMail/write.do", method = RequestMethod.GET)
    public String write() {
        return "/admin/send_mail/send_mail";
    }

    /**
     * action 페이지
     */
    @RequestMapping(value = "/admin/sendMail/send.do", method = RequestMethod.POST)
    public ModelAndView send(Model model,
                             @RequestParam(defaultValue = "") String to,
                             @RequestParam(defaultValue = "") String subject,
                             @RequestParam(defaultValue = "") String content) {

        /** 입력여부 검사후, 입력되지 않은 경우 이전 페이지로 보내기 */
        // 받는 메일 주소 검사하기
        if (!regexHelper.isValue(to)) {
            return webHelper.redirect(null, "받는 사람의 이메일 주소를 입력하세요.");
        }

        if (!regexHelper.isEmail(to)) {
            return webHelper.redirect(null, "받는 사람의 이메일 주소가 잘못되었습니다.");
        }

        // 메일 제목 --> null체크도 입력 여부를 확인할 수 있다.
        if (subject == null) {
            return webHelper.redirect(null, "메일 제목을 입력하세요.");
        }

        // 메일 내용 --> null체크도 입력 여부를 확인할 수 있다.
        if (content == null) {
            return webHelper.redirect(null, "메일의 내용을 입력하세요.");
        }

        /** 메일 발송 처리 */
        try {
            // sendMail() 메서드 선언시 throws를 정의했기 때문에 예외처리가 요구된다.
            mailHelper.sendMail(to, subject, content);
        } catch (Exception e) {
            e.printStackTrace();
            return webHelper.redirect(null, "메일 발송에 실패했습니다.");
        }

        /** 결과처리 */
        return webHelper.redirect("writeTest.do", "메일 발송에 성공했습니다.");
    }

    @RequestMapping(value = "/admin/sendMail/writeTest.do", method = RequestMethod.GET)
    public String writeTest() {
        return "/admin/send_mail/send_mail_test";
    }

    /**
     * action 페이지
     */
    @RequestMapping(value = "/admin/sendMail/sendTest.do", method = RequestMethod.POST)
    public ModelAndView sendTest(Model model,
                                 @RequestParam(defaultValue = "") String to,
                                 @RequestParam(defaultValue = "") String subject,
                                 @RequestParam(defaultValue = "") String content) {

        /** 입력여부 검사후, 입력되지 않은 경우 이전 페이지로 보내기 */
        // 받는 메일 주소 검사하기
        if (!regexHelper.isValue(to)) {
            return webHelper.redirect(null, "받는 사람의 이메일 주소를 입력하세요.");
        }

        if (!regexHelper.isEmail(to)) {
            return webHelper.redirect(null, "받는 사람의 이메일 주소가 잘못되었습니다.");
        }

        // 메일 제목 --> null체크도 입력 여부를 확인할 수 있다.
        if (subject == null) {
            return webHelper.redirect(null, "메일 제목을 입력하세요.");
        }

        // 메일 내용 --> null체크도 입력 여부를 확인할 수 있다.
        if (content == null) {
            return webHelper.redirect(null, "메일의 내용을 입력하세요.");
        }

        /** 메일 발송 처리 */
        try {
            // sendMail() 메서드 선언시 throws를 정의했기 때문에 예외처리가 요구된다.
            mailHelper.sendMail(to, subject, content);
        } catch (Exception e) {
            e.printStackTrace();
            return webHelper.redirect(null, "메일 발송에 실패했습니다.");
        }

        /** 결과처리 */
        return webHelper.redirect("writeTest.do", "메일 발송에 성공했습니다.");

    }
}
