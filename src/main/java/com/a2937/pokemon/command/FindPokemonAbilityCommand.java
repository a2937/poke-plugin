package com.avairebot.pokemon.command;

import com.avairebot.commands.Category;
import com.avairebot.commands.CategoryHandler;
import com.avairebot.commands.CommandMessage;
import com.avairebot.contracts.commands.Command;
import com.avairebot.plugin.JavaPlugin;
import com.avairebot.pokemon.data.PokemonAbilityModel;
import com.avairebot.pokemon.handlers.PokemonDataRetriever;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindPokemonAbilityCommand extends Command
{


    public FindPokemonAbilityCommand(JavaPlugin plugin)
    {
        super(plugin);
    }



    @Override
    public String getName()
    {
        return "FindPokemonAbility Command";
    }

    @Override
    public String getDescription()
    {
        return "Searches for a pokemon using the id or name.";
    }

    @Override
    public List<String> getExampleUsage()
    {
        return Collections.singletonList("`:command 1`");
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Collections.singletonList("`:command <Ability>` - Searches for a pokemon ability.");
    }

    @Override
    public List<String> getMiddleware()
    {
        return super.getMiddleware();
    }

    @Override
    public List<String> getTriggers()
    {
        return Arrays.asList("pokemonability","pokeab");
    }

    @Override
    public Category getCategory() {
        return CategoryHandler.fromLazyName("search");
    }

    @Override
    public boolean onCommand(CommandMessage context, String[] args)
    {
        if(args.length == 0)
        {

            return sendErrorMessage(context, "errors.missingArgument", "ability");
        }

        if(StringUtils.isBlank(args[0]))
        {

            return sendErrorMessage(context, "errors.missingArgument", "ability");
        }

        String abilityName = String.join("",args).toLowerCase();
        try
        {
            PokemonAbilityModel abilityModel = PokemonDataRetriever.getPokemonAbility(abilityName);
            sendAbility(context,abilityModel);
        } catch (IOException e)
        {
            return sendErrorMessage(context, context.i18n("pokemon_ability_none"));
        }


        return true;
    }

    private void sendAbility(CommandMessage context, PokemonAbilityModel ability)
    {
        MessageBuilder messageBuilder = new MessageBuilder();

        String rating = context.i18n("rating",ability.getRating());
        EmbedBuilder embedBuilder = context.makeEmbeddedMessage()
                .setColor(Color.decode("#3a71c1"))
                .setTitle(ability.getName())
                .setDescription(ability.getDesc() + "\n" + rating)
                .requestedBy(context)
                .build();
        messageBuilder.setEmbed(embedBuilder.build());
        context.getMessageChannel().sendMessage(messageBuilder.build()).queue();

    }
}
