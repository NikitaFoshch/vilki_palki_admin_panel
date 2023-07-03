package lab.space.vilki_palki.controller;

import lab.space.vilki_palki.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticService statisticService;

    @GetMapping({"/", ""})
    public String showStatisticsPage(Model model) {
        model.addAttribute("numberOfUsers", statisticService.getCountOfUsers());
        model.addAttribute("numberOfPromotions", statisticService.getAllPromotions().size());
        model.addAttribute("numberOfProducts", statisticService.getAllProducts().size());
        model.addAttribute("numberOfStructures", statisticService.getAllStructures().size());
        model.addAttribute("numberOfStructureCategories", statisticService.getCountOfStructureCategories());
        model.addAttribute("numberOfProductCategories", statisticService.getCountOfProductCategories());
        model.addAttribute("birthMonthData", statisticService.getAllBirthMonth());
        return "/admin-panel/pages/statistic/statistics";
    }
}
