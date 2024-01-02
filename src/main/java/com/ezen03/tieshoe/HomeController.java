/*
 * FileName: HomeController.java
 * Description: index와 detailserach에 대한 Controller입니다.
 * Author: 임석현
 **/

package com.ezen03.tieshoe;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.ezen03.tieshoe.helper.PageData;
import com.ezen03.tieshoe.helper.RegexHelper;
import com.ezen03.tieshoe.helper.WebHelper;
import com.ezen03.tieshoe.model.productBeans;
import com.ezen03.tieshoe.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class HomeController {

    @Autowired
    WebHelper webHelper;

    @Autowired
    RegexHelper regexHelper;

    @Autowired
    ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String index(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
        log.debug("Locale: " + locale.getLanguage());            // 언어코드 (ko)
        log.debug("Locale: " + locale.getCountry());            // 국가코드 (KO)
        log.debug("Locale: " + locale.getDisplayLanguage());    // 언어이름 (한국어)
        log.debug("Locale: " + locale.getDisplayCountry());    // 국가이름 (대한민국)

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);

        return "index";
    }

    @RequestMapping(value = "/detailSearch", method = {RequestMethod.GET})
    public ModelAndView detailSearch(Model model,
                                     @RequestParam(value = "search", required = false) String search,
                                     @RequestParam(value = "page", defaultValue = "1") int nowPage) {

        if (search.equals("")) {
            return webHelper.redirect(null, "검색어가 입력되지 않았습니다. <br> 확인하시고 다시 입력해주세요.");
        }

        return new ModelAndView("detailSearch");
    }

    @RequestMapping(value = "/error", method = {RequestMethod.GET})
    public ModelAndView error() {

        return new ModelAndView("error/error");
    }

    @RequestMapping(value = "/adminError", method = {RequestMethod.GET})
    public ModelAndView adminError() {

        return new ModelAndView("error/adminError");
    }

    @RequestMapping(value = "/closeTab", method = {RequestMethod.GET})
    public ModelAndView closeTab() {

        return new ModelAndView("account/closeTab");
    }


}
