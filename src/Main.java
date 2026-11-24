import java.time.LocalDate;
import java.util.Scanner;
class Main{
    public static void main(String[] args){
        Sistema system = new Sistema();
        String[] nombres = {"Agusto", "Artur", "CLint", "Tomás", "Luis", "Gerardo"};
        String[] correos = {"A@gmail.com", "turo@gmail.com", "Cli@outlook.cl", "tomitoo@gmail.com",
                "luchitio@gmail.com", "gerardusmagnus@gmail.com"};
        String[] profesiones = {"Agronomo", "Youtuber", "Constructor", "Vividor", "Ing. Electronico", "Constructor Civil"};
        int[] dias = {1, 13, 21, 30, 12, 15};
        int[] meses = {2, 2, 5, 7, 8, 3};

        //Agregando al sistema
        for(int i=0; i<nombres.length; i++){
            system.agregarUsuario(new Persona(i, nombres[i], dias[i], meses[i], profesiones[i], correos[i]));
        }

        //Lista Usuarios
        System.out.println("------Lista de usuarios en el sistema------");
        system.printUsuarios();
        System.out.println("--------------------------------------");
        System.out.println("");

        //Agregar Amigos
        System.out.println("------Agregando amigos a Agusto y Artur------");
        for(int i=1; i<3; i++){
            Persona p = (Persona)system.usuarios.get(0);
            Persona p1 = (Persona)system.usuarios.get(1);
            system.agregarRelacion(p, LocalDate.now(), (Persona)system.usuarios.get(i));
            system.agregarRelacion(p1, LocalDate.now(), (Persona)system.usuarios.get(i+1));
        }
        System.out.println("");

        //Print lista de amigos
        System.out.println("------Probando listas de amigos de Agusto y Artur------");
        ((Persona) system.usuarios.get(0)).printAmigos();
        ((Persona)system.usuarios.get(1)).printAmigos();
        System.out.println("-------------------------------------------");
        System.out.println("");

        //Probando bidirección
        System.out.println("------¿Tomás tiene a Artur de amigo?------");
        ((Persona)system.usuarios.get(3)).printAmigos(); //Tiene que mostrar que lo tiene
        System.out.println("----------------------");
        System.out.println("");


    }
}