package com.avairebot.pokemon;

import com.avairebot.language.Language;
import com.avairebot.plugin.JavaPlugin;
import com.avairebot.pokemon.command.FindPokemonAbilityCommand;
import com.avairebot.pokemon.command.FindPokemonCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PokemonMain extends JavaPlugin {

    public static final Logger LOGGER = LoggerFactory.getLogger(PokemonMain.class);

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        try
        {
            registerI18n(Language.EN_US,getPluginLoader().getResource("langs/en_US.yml"));
        }
        catch (IOException e)
        {
            LOGGER.error("" + e.getMessage() ,e);
        }
        registerCommand(new FindPokemonCommand(this));
        registerCommand(new FindPokemonAbilityCommand(this));
    }

}
