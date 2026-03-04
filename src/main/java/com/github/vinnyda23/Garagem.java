package com.github.vinnyda23;

import com.github.vinnyda23.Veiculo;


import java.util.ArrayList;

public class Garagem {

    private ArrayList<Veiculo> veiculos = new ArrayList<>();


    //Aqi eremos comecar a parte para adicionar veiculos, sendo cria em void para nao retornar nenhum valor
    public void adicionarVeiculos(Veiculo v) {//aqui comecaremos a se referir a veiculos como "v" nosso array, por isso veiculo v
        veiculos.add(v);
        System.out.println("Veiculo Adicionado Com Sucesso!!!");
    }

    public void listarVeiculos() {
        //aqui sera usado for que quando indentifica o numero inserido como "conhecido" assim parando o loop de repetica, dando inicio ao nosso sistema de adicionar os veiculos
        for (Veiculo v : veiculos) { // chamando aqui, nosso arraylist , informando "Veiculo v", isso quer dizer que quando utilizado "v" sera chamado a array veiculos que se trata da nossa classe veiculo
            System.out.println("========LISTAR VEICULOS=========");//Menu Adicionar veiculos, a parte "visual".
            System.out.println("Categoria: " + v.getCategoria());
            System.out.println("Modelo: " + v.getModelo());
            System.out.println("Marca: " + v.getMarca());
            System.out.println("Ano: " + v.getAno());
            System.out.println("Cor:" + v.getCor());
            System.out.println("Valor: R$ " + v.getValor());
        }
    }


    public void removerVeiculos(String modelo) {
        veiculos.removeIf(v -> v.getModelo().equalsIgnoreCase(modelo));
        System.out.println("Veiculo Removido");
    }

    // onde buscaremos pelo modelo do veiculo
    public Veiculo buscarPorModelo(String modelo) {
        for (Veiculo v : veiculos) {
            if (v.getModelo().equalsIgnoreCase(modelo)) {
                return v;
            }
        }
        return null;
    }
}