/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cep;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
//import com.github.uliss3s.ceputil.Util;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.*;

/**
 * Classe para recuperar informaÃ§Ãµes do WS do viacep.com.br
 */
public class ClienteWs {

    private static final Set<String> CAMPOS = new HashSet<String>(Arrays.asList(
            "cep",
            "logradouro",
            "complemento",
            "bairro",
            "localidade",
            "uf",
            "unidade",
            "ibge",
            "gia"
    ));

    /**
     * Recupera objeto Endereco pelo CEP
     *
     * @param cep String no formato 00000000
     * @return instancia de br.com.viacep.Endereco
     */
    public static Endereco getEnderecoPorCep(String cep) {

        JsonObject jsonObject = getCepResponse(cep);
        Endereco endereco = null;

        JsonValue erro = jsonObject.get("erro");

        if (erro == null) {

            endereco = new Endereco()
                    .setCep(jsonObject.getString("cep"))
                    .setLogradouro(jsonObject.getString("logradouro"))
                    .setComplemento(jsonObject.getString("complemento"))
                    .setBairro(jsonObject.getString("bairro"))
                    .setCidade(jsonObject.getString("localidade"))
                    .setUf(jsonObject.getString("uf"))
                    .setIbge(jsonObject.getString("ibge"));
        }

        return endereco;
    }

    /**
     * Recupera Map<String,String> pelo CEP
     *
     * @param cep String no formato 00000000
     * @return instancia de Map<String,String>
     */
    public static Map<String, String> getMapPorCep(String cep) {

        JsonObject jsonObject = getCepResponse(cep);

        JsonValue erro = jsonObject.get("erro");

        Map<String, String> mapa = null;
        if (erro == null) {
            mapa = new HashMap<String, String>();

            for (Iterator<Map.Entry<String, JsonValue>> it = jsonObject.entrySet().iterator(); it.hasNext();) {
                Map.Entry<String, JsonValue> entry = it.next();
                mapa.put(entry.getKey(), entry.getValue().toString());
            }
        }

        return mapa;
    }

    private static JsonObject getCepResponse(String cep) {

        JsonObject responseJO = null;

        try {
            if (!validaCep(cep)) {
                throw new RuntimeException("Formato de CEP invÃ¡lido");
            }

            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("https://viacep.com.br/ws/" + cep + "/json");
            HttpResponse response = httpclient.execute(httpGet);

            HttpEntity entity = response.getEntity();

            responseJO = Json.createReader(entity.getContent()).readObject();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return responseJO;
    }

    public static boolean validaCep(String cep) {
        if (!cep.matches("\\d{8}")) {
            return false;
        }

        return true;
    }
}
