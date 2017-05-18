package com.codecool.shop.controller;

import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

/**
 * Created by michal on 16/05/2017.
 */
public class BaseController {

    public String render(ModelAndView modelAndView) {
        String rendered = new ThymeleafTemplateEngine().render(modelAndView);

        return rendered;
    }
}
