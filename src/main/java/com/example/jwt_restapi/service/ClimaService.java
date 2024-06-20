package com.example.jwt_restapi.service;

import com.example.jwt_restapi.model.ClimaEntity;
import com.example.jwt_restapi.repository.ClimaRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;

@Service
public class ClimaService {

    @Autowired
    private ClimaRepository climaRepository;

    public String preverTempo() {
        String dadosMeteorologicos = "";
        //Nível BR
        String apiUrl = "https://apiadvisor.climatempo.com.br/api/v1/anl/synoptic/locale/BR?token=9fe25332679ebce952fdd9f7f9a83c3e";
        //Nível BH
        //String apiUrl = "http://apiadvisor.climatempo.com.br/api/v1/weather/locale/6879/current?token=9fe25332679ebce952fdd9f7f9a83c3e";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            dadosMeteorologicos = responseEntity.getBody();
        } else {
            dadosMeteorologicos = "Falha ao obter dados meteorológicos. Código de status: " + responseEntity.getStatusCode();
        }
        inserirDados(dadosMeteorologicos);
        return dadosMeteorologicos;
    }

    public void inserirDados(String dadosMeteorologicos) {
        try {
            JSONArray jsonArray = (JSONArray) JSONValue.parseWithException(dadosMeteorologicos);

            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                String country = (String) jsonObj.get("country");
                String date = (String) jsonObj.get("date");
                String text = (String) jsonObj.get("text");

                ClimaEntity clima = new ClimaEntity();
                clima.setPais(country);
                clima.setDate(date);
                clima.setText(text);
                climaRepository.save(clima);
            }
        } catch (net.minidev.json.parser.ParseException e) {
            e.printStackTrace();
        }
    }

}


