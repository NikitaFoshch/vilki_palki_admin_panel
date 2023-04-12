package lab.space.vilki_palki.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    @GetMapping({"/", ""})
    public String showStatisticsPage(Principal principal) {
        System.out.println(principal);
        return "/admin-panel/pages/statistics/statistics";
    }
}
