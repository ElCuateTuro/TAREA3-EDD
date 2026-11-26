import java.time.LocalDate;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        Sistema system = new Sistema();
        String[] nombres = {"Agusto", "Artur", "CLint", "Tomás", "Luis", "Gerardo"};
        String[] correos = {"A@gmail.com", "turo@gmail.com", "Cli@outlook.cl", "tomitoo@gmail.com",
                "luchitio@gmail.com", "gerardusmagnus@gmail.com"};
        String[] profesiones = {"Agronomo", "Youtuber", "Constructor", "Vividor", "Ing. Electronico", "Constructor Civil"};
        int[] dias = {1, 13, 28, 30, 12, 15};
        int[] meses = {2, 2, 11, 11, 8, 3};

        //Agregando al sistema
        for (int i = 0; i < nombres.length; i++) {
            system.insertarPersona(nombres[i], dias[i], meses[i], profesiones[i], correos[i]);
        }

        //Lista Usuarios
        System.out.println("------Lista de usuarios en el sistema------");
        system.printUsuarios();
        System.out.println("--------------------------------------");
        System.out.println("");

        //Agregar Amigos
        System.out.println("------Agregando amigos a Agusto y Artur------");
        for (int i = 2; i <= 3; i++) {
            Persona p = system.usuarios.get(1);
            Persona p1 = system.usuarios.get(2);
            system.agregarRelacion(p, LocalDate.now(), system.usuarios.get(i));
            system.agregarRelacion(p1, LocalDate.now(), system.usuarios.get(i + 1));
        }
        System.out.println("");

        //Print lista de amigos
        System.out.println("------Probando listas de amigos de Agusto y Artur------");
        system.usuarios.get(1).printAmigos();
        system.usuarios.get(2).printAmigos();
        System.out.println("-------------------------------------------");
        System.out.println("");

        //Probando bidirección
        System.out.println("------¿Tomás tiene a Artur de amigo?------");
        system.usuarios.get(4).printAmigos(); //Tiene que mostrar que lo tiene
        System.out.println("----------------------");
        System.out.println("");

        System.out.println("------Probando operaciones (c) a (g)------");
        Persona agusto = system.usuarios.get(1);
        Persona artur = system.usuarios.get(2);
        Persona clint = system.usuarios.get(3);
        Persona tomas = system.usuarios.get(4);
        Persona luis = system.usuarios.get(5);
        Persona gerardo = system.usuarios.get(6);

        system.agregarRelacion(tomas, LocalDate.now(), luis);
        system.agregarRelacion(luis, LocalDate.now(), gerardo);

        System.out.println("Amigos comunes entre Agusto y Artur:");
        ArrayList<Persona> comunes = system.amigosComunes(agusto, artur);
        for (Persona c : comunes) {
            System.out.println("- " + c.getNombre());
        }
        System.out.println("");

        int na = system.nivelAmistad(agusto, gerardo);
        System.out.println("Nivel de amistad entre Agusto y Gerardo: " + (na == Integer.MAX_VALUE ? "Infinito" : na));
        System.out.println("");

        System.out.println("Cumpleaños en los próximos 10 días:");
        system.cumpleanosProximos(10, LocalDate.now());
        System.out.println("");

        System.out.println("Mayor cantidad de amigos con na <= 3:");
        ArrayList<Persona> top = system.personasConMasAmigosHastaNivel3();
        for (Persona p : top) {
            System.out.println("- " + p.getNombre() + " (" + system.contarAmigosHastaNivel(p, 3) + " amigos)");
        }
        System.out.println("");

        system.bloquearAmigo(agusto, LocalDate.now(), clint);
        System.out.println("Nivel de amistad entre Agusto y CLint tras bloqueo: "
                + (system.nivelAmistad(agusto, clint) == Integer.MAX_VALUE ? "Infinito" : system.nivelAmistad(agusto, clint)));
    }
}