package com.apofig.tddtrainer.service;

import com.codenjoy.dojo.transport.screen.PlayerDataSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * User: serhiy.zelenin
 * Date: 9/26/13
 * Time: 2:27 PM
 */
@Component
public class JsonPlayerDataSerializer implements PlayerDataSerializer<Player, PlayerData> {
    private final ObjectMapper objectMapper;

    public JsonPlayerDataSerializer() {
        objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        objectMapper.registerModule(module);
    }

    @Override
    public void writeValue(Writer writer, Map<Player, PlayerData> playerScreens) throws IOException {
        objectMapper.writeValue(writer, playerScreens);
    }
}