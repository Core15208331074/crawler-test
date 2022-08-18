package com.example.crawlertestingoffrank.crawler01;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;


@Controller
@RequestMapping(value = "/grab/v1")
public class Crawler01Controller {

    Logger loggerTest = Logger.getLogger("logger_test");

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Result testForGrabbingOfV1(String url, String content) throws Exception{
        //get url
        loggerTest.info("url: " + url);
        //parse page content,timeout=5s
        Document doc = Jsoup.parse(new URL(url), 50000);
        Elements allElements = doc.getAllElements();
        loggerTest.info("all elements: " + allElements);
        Elements elementsContainingText = null;
        if (content != null && !"".equals(content)) {
            elementsContainingText = doc.getElementsContainingText(content);
        }
        loggerTest.info("elements with condition: " + elementsContainingText);
        String text = "";
        if (elementsContainingText != null) {
            text = elementsContainingText.toString();
        }
        //going to return
        Result result = new Result();
        result.setCode(200);
        result.setBody(text);
        result.setMsg("return with no error");
        return result;
    }


}

class Result {

    private int code;
    private Object body;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result() {
    }
}
