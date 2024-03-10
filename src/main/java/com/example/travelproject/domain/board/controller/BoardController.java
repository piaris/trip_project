package com.example.travelproject.domain.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping({"","/"})
    public String mainBoard(Model model){
        model.addAttribute("menuTitle","게시판");
        return "board/boardMain";
    }
}
