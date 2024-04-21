package com.ibrahimbayburtlu.RandomDataGenerate.Controller;

import com.ibrahimbayburtlu.RandomDataGenerate.service.RandomDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bundle")
@Slf4j
public class RandomDataController {

    private final RandomDataService randomDataService;

    @Autowired
    public RandomDataController(RandomDataService randomDataService) {
        this.randomDataService = randomDataService;
    }

    @GetMapping("/start-case")
    public void startRandomData(){
        randomDataService.startRandomDataService();
        log.info("Started start random data service");
    }
}
