package ch.jpr.hotwire.demo.ui.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping(path = "/")
    public String home(Model model) {
        model.addAttribute("applicationdate", LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.forLanguageTag("de-CH"))));
        return "home";
    }

    @GetMapping(path = "/page1")
    public String page1(Model model) {
        model.addAttribute("applicationdate", LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.forLanguageTag("de-CH"))));
        return "page1";
    }

    @GetMapping(path = "/page2")
    public String page2(Model model) {
        model.addAttribute("applicationdate", LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.forLanguageTag("de-CH"))));
        return "page2";
    }

    @GetMapping(path = "/page3")
    public String page3(Model model) {
        model.addAttribute("applicationdate", LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.forLanguageTag("de-CH"))));
        return "page3";
    }
}
