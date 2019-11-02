package com.a2937.pokemon.handlers;

import com.a2937.pokemon.PokemonMain;
import com.a2937.pokemon.data.PokemonAbilityModel;
import com.a2937.pokemon.data.PokemonModel;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PokemonDataRetriever {

    private PokemonDataRetriever()
    {

    }

    public static String getPokemonFromId(int number) throws IOException {
        InputStream pokeStream = PokemonMain.class.getClassLoader().getResourceAsStream("data/name-id_map.json");
        InputStreamReader streamReader = null;
        StringBuilder builder = new StringBuilder();
        if (pokeStream != null)
        {
            streamReader = new InputStreamReader(pokeStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);

            for (String line; (line = reader.readLine()) != null; )
            {
                builder.append(line);
            }
            JSONArray ids = new JSONArray(builder.toString());
            return ids.getJSONObject(number - 1).getString("Name");
        }
        else
        {
            return "";
        }
    }

    public static PokemonModel getPokemonFromName(String pokemonName) throws IOException
    {

        InputStream pokeStream = PokemonMain.class.getClassLoader().getResourceAsStream("data/pokemon_list.json");
        InputStreamReader streamReader = null;
        StringBuilder builder = new StringBuilder();

        if (pokeStream != null)
        {
            streamReader = new InputStreamReader(pokeStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);

            for (String line; (line = reader.readLine()) != null; )
            {
                builder.append(line);
            }
            JSONObject pokemonList = new JSONObject(builder.toString());
            if(pokemonList.has(pokemonName))
            {
                JSONObject object = pokemonList.getJSONObject(pokemonName);
                Gson gson = new Gson();
                return gson.fromJson(object.toString(), PokemonModel.class);
            }
            else
            {
                throw new IOException(pokemonName + " not found.");
            }

        }
        else
        {
            throw new IOException("data/pokemon_list.json not found");
        }

    }


    public static PokemonAbilityModel getPokemonAbility(String abilityName) throws IOException {
        InputStream pokeStream = PokemonMain.class.getClassLoader().getResourceAsStream("data/pokemon_abilities.json");
        InputStreamReader streamReader = null;
        StringBuilder builder = new StringBuilder();

        if (pokeStream != null)
        {
            streamReader = new InputStreamReader(pokeStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);

            for (String line; (line = reader.readLine()) != null; )
            {
                builder.append(line);
            }
            JSONObject pokemonList = new JSONObject(builder.toString());
            if(pokemonList.has(abilityName))
            {
                JSONObject object = pokemonList.getJSONObject(abilityName);
                Gson gson = new Gson();
                return gson.fromJson(object.toString(), PokemonAbilityModel.class);
            }
            else
            {
                throw new IOException(abilityName + " not found");
            }
        }
        else
        {
            throw new IOException("data/pokemon_abilities.json not found");
        }
    }
}
