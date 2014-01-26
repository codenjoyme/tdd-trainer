package com.apofig.tddtrainer.service;

import com.codenjoy.dojo.transport.screen.PlayerDataSerializer;
import com.codenjoy.dojo.transport.screen.ScreenData;
import com.codenjoy.dojo.transport.screen.ScreenRecipient;
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
public class JsonPlayerDataSerializer implements PlayerDataSerializer<ScreenRecipient, ScreenData> {

    @Override
    public void writeValue(Writer writer, Map<ScreenRecipient, ScreenData> playerScreens) throws IOException {

    }
}
