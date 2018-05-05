package org.oregami.transliteratedString.adapter;

import org.oregami.common.ValidationException;
import org.oregami.transliteratedString.application.TransliteratedStringApplicationService;
import org.oregami.transliteratedString.model.TransliteratedStringRepository;
import org.oregami.transliteratedString.readmodel.live.TransliteratedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by sebastian on 17.12.16.
 */
@RequestMapping("/transliteratedStrings")
@Controller
public class TransliteratedStringResource {

    private TransliteratedStringApplicationService transliteratedStringApplicationService;

    private TransliteratedStringRepository transliteratedStringRepository;

    @Autowired
    public TransliteratedStringResource(TransliteratedStringApplicationService transliteratedStringApplicationService, TransliteratedStringRepository transliteratedStringRepository) {
        this.transliteratedStringApplicationService = transliteratedStringApplicationService;
        this.transliteratedStringRepository = transliteratedStringRepository;
    }

    @PostMapping(value = "/create")
    public String create(
            @RequestParam String text,
            @RequestParam String language,
            @RequestParam String script,
            @RequestParam(value = "backUrl", defaultValue = "") String backUrl,
            Model model) {
        String id = UUID.randomUUID().toString();

        model.addAttribute("availableLanguages", availableLanguages());
        model.addAttribute("availableScripts", availableScripts());

        CompletableFuture<Object> completableFuture = transliteratedStringApplicationService.createNewTransliteratedString(id, text, language, script);
        try {
            Object result = completableFuture.get();

        } catch (InterruptedException e) {
            e.printStackTrace();

        } catch (ExecutionException e) {
            if (e.getCause() instanceof ValidationException) {
                model.addAttribute("result", ((ValidationException) e.getCause()).getResult());

                model.addAttribute("text", text);
                model.addAttribute("language", language);
                model.addAttribute("script", script);


                return "transliteratedStrings/create";
            }
        }

        model.addAttribute("transliteratedStringId", id);
        model.addAttribute("backUrl", backUrl);
        return "transliteratedStrings/created";

    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("list", transliteratedStringRepository.findAll());
        return "transliteratedStrings/list";
    }

    @GetMapping(value = "/{transliteratedStringId}")
    public String getOne(@PathVariable String transliteratedStringId, Model model) {
        TransliteratedString transliteratedString = transliteratedStringRepository.findOne(transliteratedStringId);
        model.addAttribute("transliteratedString", transliteratedString);
        return "transliteratedStrings/one";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("availableLanguages", availableLanguages());
        model.addAttribute("availableScripts", availableScripts());

        return "transliteratedStrings/create";
    }


    @GetMapping(value = "/search")
    public String search(
            @RequestParam String searchTitle,
            @RequestParam String backUrl,
            Model model) {
        model.addAttribute("searchTitle", searchTitle);
        model.addAttribute("backUrl", backUrl);

        List<TransliteratedString> transliteratedStrings = transliteratedStringRepository.findByTextIgnoreCaseContaining(searchTitle);
        model.addAttribute("transliteratedStrings", transliteratedStrings);

        return "transliteratedStrings/search";
    }


    private List<String> availableLanguages() {
        List<String> list = new ArrayList<>();
        list.add("ENGLISH");
        list.add("GERMAN");
        list.add("ARABIC");
        list.add("BENGALI");
        list.add("CANTONESE");
        list.add("CHINESE");
        list.add("DUTCH");
        list.add("FRENCH");
        list.add("GREEK");
        list.add("HINDI");
        list.add("ITALIAN");
        list.add("JAPANESE");
        list.add("KOREAN");
        list.add("MANDARIN");
        list.add("PERSIAN");
        list.add("POLISH");
        list.add("PORTUGUESE");
        list.add("PUNJABI");
        list.add("RUSSIAN");
        list.add("SPANISH");
        list.add("TURKISH");
        return list;
    }

    private List<String> availableScripts() {
        List<String> list = new ArrayList<>();
        list.add("LATIN");
        list.add("ARABIC");
        list.add("CHINESE");
        list.add("CYRILLIC");
        list.add("GREEK");
        list.add("JAPANESE");
        list.add("KOREAN");
        return list;
    }




}
