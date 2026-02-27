package com.spring.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.app.domain.GenieMusicDTO;
import com.spring.app.service.GenieMusicService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {

    private final GenieMusicService service;

    // --------------------------
    // 기존 GET 메서드
    // --------------------------
    @GetMapping("/")   
    public String main() {
        return "redirect:/list";  
    }

    @GetMapping("list")
    public String list(Model model) {
        List<GenieMusicDTO> musicList = service.musicList();
        model.addAttribute("musicList", musicList);
        return "musicList";
    }

    // --------------------------
    // GitHub Webhook POST 메서드
    // --------------------------
    @PostMapping("/github-webhook")
    @ResponseBody
    public ResponseEntity<String> githubWebhook(@RequestBody String payload) {
        System.out.println("Webhook 수신: " + payload);
        return ResponseEntity.ok("ok");
    }

}