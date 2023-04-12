package lab.space.vilki_palki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class StatisticsController {
    @GetMapping({"/admin/statistics/", "/admin/statistics"})
    public String showStatisticsPage(Principal principal) {
        System.out.println(principal);
        return "/admin-panel/pages/statistics/statistics";
    }
}
