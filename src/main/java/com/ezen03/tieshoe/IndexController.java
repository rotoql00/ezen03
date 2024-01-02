package com.ezen03.tieshoe;

import com.ezen03.tieshoe.helper.*;
import com.ezen03.tieshoe.model.*;
import com.ezen03.tieshoe.service.*;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.*;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Slf4j
@RestController
public class IndexController {

    @Autowired
    WebHelper webHelper;

    @Autowired
    RetrofitHelper retrofitHelper;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    indexService indexService;

    @Value("#{servletContext.contextPath}")
    String contextPath;

    // 브랜드별 4개씩 정보와 이미지를 가져옵니다.
    @RequestMapping(value = "/getBrand.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> brandInfo(Model model) {

        List<productBeans> adidas = null;
        List<productBeans> nike = null;
        List<productBeans> converse = null;
        List<productBeans> jordan = null;
        List<productBeans> newArrival = null;
        List<productBeans> bestProduct = null;

        productBeans input = new productBeans();

        try {
            //데이터 조회
            adidas = indexService.getAdidas(input);
            nike = indexService.getNike(input);
            converse = indexService.getConverse(input);
            jordan = indexService.getJordan(input);
            newArrival = indexService.newArrival(input);
            bestProduct = indexService.bestProduct(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        log.info("working");

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("adidas", adidas);
        data.put("nike", nike);
        data.put("converse", converse);
        data.put("jordan", jordan);
        data.put("newArrival", newArrival);
        data.put("bestProduct", bestProduct);
        return webHelper.getJsonData(data);
    }

    // 인기상품을 가져옵니다.
    @RequestMapping(value = "/getPopular.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> popularInfo(Model model) {

        popularBeans popularInput = new popularBeans();
        productBeans productInput = new productBeans();

        List<productBeans> output = null;

        List<String> codeList = new ArrayList<String>();


        try {
            popularInput = indexService.getPopularRank(popularInput);
            popularInput = indexService.getPopularRank(popularInput);
            String list = popularInput.getPopular_product();
            String[] array = list.split(",");

            for (int i = 0; i < array.length; i++) {
                codeList.add(array[i]);
            }
            output = indexService.getPopular(codeList);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }


        Map<String, Object> data = new HashMap<String, Object>();
        data.put("popular", output);
        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/getMdPick.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> mdPick(Model model,
                                      @RequestParam(required = true) int ID) {

        productBeans productInput = new productBeans();
        userBeans userInput = new userBeans();

        List<productBeans> output = null;

        List<String> codeList = new ArrayList<String>();

        String json = null;


        //ID를 객체에 삽입합니다.
        userInput.setID(ID);
        try {
            userInput = indexService.getSurvey(userInput);

            //JSON 형식을 가져오기
            json = userInput.getUserForm();

            //GSON을 사용해 Parsing 합니다.
            JsonParser Parser = new JsonParser();
            JsonObject jsonObj = (JsonObject) Parser.parse(json);
            JsonArray jsonArray = (JsonArray) jsonObj.get("luv shoe model");

            Type listType = new TypeToken<List<String>>() {
            }.getType();
            List<String> array = new Gson().fromJson(jsonArray, listType);

            for (int i = 0; i < array.size(); i++) {
                codeList.add(array.get(i));
                log.info(array.get(i));
            }

            output = indexService.getMdPick(codeList);

        } catch (
                Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("mdPick", output);
        return webHelper.getJsonData(data);
    }
}