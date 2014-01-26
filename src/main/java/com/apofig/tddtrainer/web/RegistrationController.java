package com.apofig.tddtrainer.web;

import com.apofig.tddtrainer.service.Player;
import com.apofig.tddtrainer.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * User: oleksandr.baglai
 * Date: 10/1/12
 * Time: 3:49 AM
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired private PlayerServiceImpl playerService;

    public RegistrationController() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public String openRegistrationForm(HttpServletRequest request, Model model) {
        String ip = getIp(request);

        Player player = new Player();
        player.setName(request.getParameter("name"));
        model.addAttribute("player", player);

        player.setCallbackUrl("http://" + ip + ":8888");

        return "register";
    }

    private String getIp(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitRegistrationForm(Player player, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        playerService.register(player);
        return "redirect:/board/" + player.getName();
    }
}
