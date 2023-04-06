package lab.space.vilki_palki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsController {
    @GetMapping({"/admin/statistics/", "/admin/statistics"})
    public String showStatisticsPage() {
        return "/admin-panel/pages/statistics/statistics";
    }
}
