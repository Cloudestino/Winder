package com.example.pidev.Controller;

import com.example.pidev.Service.OptionServiceC;
import com.example.pidev.entity.Option;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/options")
public class OptionRestController {

    OptionServiceC optionService;


    // http://localhost:8089/pidev/options/retrieve-all-options
    @GetMapping("/retrieve-all-options")
    public List<Option> getOptions() {
        List<Option> listOptions = optionService.getAllOptions();
        return listOptions;
    }


    // http://localhost:8089/pidev/options/retrieve-option/8
    @GetMapping("/retrieve-option/{option-id}")
    public Option retrieveOption(@PathVariable("option-id") Long OptionId) {
        return optionService.getOptionById(OptionId);
    }


    // http://localhost:8089/pidev/options/add-option
    @PostMapping("/add-option")
    public Option addOption(@RequestBody Option option) {
        Option option1 = optionService.createOption(option);
        return option1;
    }

    // http://localhost:8089/pidev/options/remove-option/1
    @DeleteMapping("/remove-option/{option-id}")
    public void removeOption(@PathVariable("option-id") Long optionId) {
        optionService.deleteOption(optionId);
    }

    // http://localhost:8089/pidev/options/update-option
    @PutMapping("/update-option")
    public Option updateOption(@RequestBody Option option) {
        Option option1 = optionService.updateOption(option);
        return option1;
    }






















}
