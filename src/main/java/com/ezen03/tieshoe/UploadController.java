package com.ezen03.tieshoe;

import java.util.List;
import java.util.Map;

import com.ezen03.tieshoe.helper.RegexHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ezen03.tieshoe.model.UploadItem;
import com.ezen03.tieshoe.helper.WebHelper;

@Controller
public class UploadController {
    // -> import study.spring.springhelper.helper.WebHelper;
    @Autowired
    WebHelper webHelper;

    // -> import study.spring.springhelper.helper.RegexHelper;
    @Autowired
    RegexHelper regexHelper;

    /**
     * 업로드 폼을 구성하는 페이지
     */
    @RequestMapping(value = "/upload/upload.do", method = RequestMethod.GET)
    public String upload() {
        return "upload/upload";
    }

    /**
     * 업로드 폼에 대한 action 페이지
     */
    @RequestMapping(value = "/upload/upload_ok.do", method = RequestMethod.POST)
    public ModelAndView uploadOk(Model model) {
        /** 1) 업로드를 수행 */
        try {
            webHelper.upload();
        } catch (Exception e) {
            e.printStackTrace();
            return webHelper.redirect(null, "업로드에 실패했습니다.");
        }

        /** 2) 업로드 된 정보 추출하기 */
        // 파일 정보 추출
        List<UploadItem> fileList = webHelper.getFileList();
        // 그 밖의 일반 데이터를 저장하기 위한 컬렉션
        Map<String, String> paramMap = webHelper.getParamMap();

        // 텍스트 데이터에서 입력값을 추출한다.
        String subject = paramMap.get("subject");

        // 입력값에 대한 유효성 검사
        if (!regexHelper.isValue(subject)) {
            return webHelper.redirect(null, "제목을 입력하세요.");
        }

        if (!regexHelper.isKor(subject)) {
            return webHelper.redirect(null, "제목은 한글로만 입력하세요.");
        }

        /** 3) 업로드 결과를 View에게 전달한다 */
        model.addAttribute("fileList", fileList);
        model.addAttribute("subject", subject);

        // 중단 없이 정상적인 실행 종료를 통해 View를 호출해야 하는 경우
        // View의 경로를 ModelAndView 타입의 객체로 생성하여 리턴한다.
        String viewPath = "upload/upload_ok";
        return new ModelAndView(viewPath);
    }


}


