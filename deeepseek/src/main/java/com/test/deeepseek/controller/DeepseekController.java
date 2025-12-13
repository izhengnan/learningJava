package com.test.deeepseek.controller;

import com.test.deeepseek.service.DeepseekService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deepseek")
@CrossOrigin
@Slf4j
public class DeepseekController {
    @Autowired
    private DeepseekService deepseekService;

    @GetMapping

}
