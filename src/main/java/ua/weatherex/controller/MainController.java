package ua.weatherex.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.weatherex.domain.Message;
import ua.weatherex.domain.User;
import ua.weatherex.repos.MessageRepo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    private final MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public MainController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter,
            Model model) {
        Iterable<Message> messages;
        if(filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String  text,
            @RequestParam String tag,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) {
        Message message = new Message(text, tag, user);
        if(file != null) {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/" +resultFilename));
            } catch (IOException e) {
                e.printStackTrace();
            }
            message.setFilename(resultFilename);
        }
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);

        return "main";
    }

}