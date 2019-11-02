package com.a2937.pokemon.command;

import com.avairebot.commands.Category;
import com.avairebot.commands.CategoryHandler;
import com.avairebot.commands.CommandMessage;
import com.avairebot.contracts.commands.Command;
import com.avairebot.plugin.JavaPlugin;
import com.a2937.pokemon.data.PokemonModel;
import com.a2937.pokemon.handlers.PokemonDataRetriever;
import com.avairebot.utilities.NumberUtil;
import com.avairebot.utilities.RandomUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindPokemonCommand extends Command
{


    public FindPokemonCommand(JavaPlugin plugin)
    {
        super(plugin);
    }



    @Override
    public String getName()
    {
        return "FindPokemon Command";
    }

    @Override
    public String getDescription()
    {
        return "Searches for a pokemon using the id or name.";
    }

    @Override
    public List<String> getExampleUsage()
    {
        return Arrays.asList("`:command 1`","`:command Bulbasaur`");
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList("`:command <PokemonId>` - Searches for a Pokemon with that id",
                "`:command <PokemonName>` - Searches for a Pokemon with that name");
    }

    @Override
    public List<String> getMiddleware()
    {
        return super.getMiddleware();
    }

    @Override
    public List<String> getTriggers()
    {
        return Collections.singletonList("pokemon");
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
            return sendErrorMessage(context, "errors.missingArgument", "pokemon");
        }

        if(StringUtils.isBlank(args[0]))
        {
            return sendErrorMessage(context, "errors.missingArgument", "pokemon");
        }

        String pokemon = args[0].trim();


        if(NumberUtil.isNumeric(pokemon))
        {
            try
            {
                String pokemonName = PokemonDataRetriever.getPokemonFromId(NumberUtil.parseInt(pokemon,1));
                PokemonModel pokemonModel =  PokemonDataRetriever.getPokemonFromName(pokemonName);
                sendPokemon(context,pokemonModel);
            }
            catch (IOException e)
            {
                return sendErrorMessage(context, context.i18n("missingPokemonFile",pokemon));
            }

        }
        else if(pokemon.equalsIgnoreCase("random"))
        {
            int pokemonId = RandomUtil.getInteger(807) + 1;
            try
            {
                String pokemonName = PokemonDataRetriever.getPokemonFromId(pokemonId);
                PokemonModel pokemonModel =  PokemonDataRetriever.getPokemonFromName(pokemonName);
                sendPokemon(context,pokemonModel);
            }
            catch (IOException e)
            {
                return sendErrorMessage(context, context.i18n("missingPokemonFile",pokemon));
            }

        }
        else
        {
            try
            {
                PokemonModel pokemonModel = PokemonDataRetriever.getPokemonFromName(pokemon.toLowerCase());
                sendPokemon(context,pokemonModel);
            }
            catch (IOException e)
            {
                return sendErrorMessage(context, context.i18n("missingPokemonFile",pokemon));
            }
        }

        return true;
    }

    private void sendPokemon(CommandMessage context, PokemonModel pokemon)
    {
        MessageBuilder messageBuilder = new MessageBuilder();

        String stats = " Stats: \n" +  pokemon.getStats().toString();
        String abilities = context.i18n("statsDisplay.physicalAttributes",pokemon.getAbilitiesFormatted());
        float height = pokemon.getHeightm();
        float weight = pokemon.getWeightkg();

        String heightWeight = context.i18n("statsDisplay.physicalAttributes",weight,height);

        EmbedBuilder embedBuilder = context.makeEmbeddedMessage()

                .setImage("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/"+String.format("%03d",pokemon.getNum()) + ".png")
                .setColor(Color.decode("#3a71c1"))
                .setTitle(pokemon.getName())
                .setDescription(abilities + "\n" + heightWeight + "\n" + stats )
                .requestedBy(context)
                .build();
        messageBuilder.setEmbed(embedBuilder.build());
        context.getMessageChannel().sendMessage(messageBuilder.build()).queue();

    }




}
