import java.util.Scanner;
class Main{
    public static void main(String[] args){
        Sistema system = new Sistema();
        String[] nombres = {"Agusto", "Artur", "CLint", "Tomás", "Luis", "Gerardo"};
        String[] correos = {"A@gmail.com", "turo@gmail.com", "Cli@outlook.cl", "tomitoo@gmail.com", "luchitio@gmail.com", "gerardusmagnus@gmail.com"};
        String[] profesiones = {"Agronomo", "Youtuber", "Constructor", "Vividor", "Ing. Electronico", "Constructor Civil"};
        int[] dias = {1, 13, 21, 30, 12, 15};
        int[] meses = {2, 2, 5, 7, 8, 3};

        for(int i=0; i<nombres.length; i++){
            system.agregarUsuario(new Persona(i, nombres[i], dias[i], meses[i], profesiones[i], correos[i]));
        }

    }
}