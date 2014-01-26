package com.apofig.tddtrainer.web;

import com.apofig.tddtrainer.service.Player;
import com.apofig.tddtrainer.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {

    @Autowired private PlayerServiceImpl playerService;

    @RequestMapping(value = "/board/{playerName}", method = RequestMethod.GET)
    public String board(ModelMap model, @PathVariable("playerName") String playerName) {
        Player player = playerService.get(playerName);
        if (player == Player.NULL) {
            return "redirect:/register?name=" + playerName;
        } else {
            model.addAttribute("player", player);
        }
        return "board";
    }
}
