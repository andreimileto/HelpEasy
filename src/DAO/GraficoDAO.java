/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.ConexaoBD;
import entidade.Grafico;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class GraficoDAO {

    public ArrayList<Grafico> consultar() {

//        this.fat = fat;
//        this.cli = cli;
//        ArrayList<Faturamento> vendas = new ArrayList<>();
        ArrayList<Grafico> graficos = new ArrayList<>();
//        String sql = "";

        //if (fat.getId() == 0) {
        String sql = "   select count(tarefa.id), projeto.descricao \n"
                + "  from tarefa left join projeto on tarefa.id_projeto = projeto.id\n"
                + "  group by projeto.descricao";

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
//                System.out.println(sql);
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Grafico graf = new Grafico();
                // Cidade cid = new Cidade();
                //Cliente cliente = new Cliente(cid);

                graf.setQuantidade(resultado.getInt("count"));
                graf.setDescricaoProjeto(resultado.getString("descricao"));

                graficos.add(graf);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar tarefas " + e);
        }
        System.out.println("tamanho array = "+graficos.size());

        return graficos;

    }

}
