package com.chenjian.controller;



import com.chenjian.entity.GameStart;
import com.chenjian.entity.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GameController {

    /**
     *
      * @param httpServletRequest
     * @param httpServletResponse
     */
    @RequestMapping( value = "/game", method = RequestMethod.GET)
    @ResponseBody
    public Message start(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        Message message = new Message();

        String name = httpServletRequest.getParameter("name");

        if (name == null || name.trim().length() < 1) {

            message.setMsg("名字不能为空,请重新输入...");
            message.setSuccess(false);

            return message;
        }else{
            new GameStart(name).start();
        }
        message.setSuccess(true);
        return message;
    }


    @RequestMapping(value = "/star")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/greeting");
        return mv;
    }
}
