import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class Sistema {
    TreeMap<Integer, Persona> usuarios;
    private int siguienteId;
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Sistema() {
        usuarios = new TreeMap<>();
        siguienteId = 1;
    }

    public Persona insertarPersona(String nombre, int dia, int mes, String profesion, String email) {
        Persona nueva = new Persona(siguienteId, nombre, dia, mes, profesion, email);
        usuarios.put(siguienteId, nueva);
        siguienteId++;
        return nueva;
    }

    public void agregarUsuario(Persona persona) {
        if (persona.getId() < 1 || usuarios.containsKey(persona.getId())) {
            System.out.println("Error al agregar usuario: ID inválido o ya existente.");
            return;
        }
        usuarios.put(persona.getId(), persona);
        if (persona.getId() >= siguienteId) {
            siguienteId = persona.getId() + 1;
        }
    }

    public boolean editarPersona(int id, String nombre, int dia, int mes, String profesion, String email) {
        Persona p = usuarios.get(id);
        if (p == null) {
            return false;
        }
        p.setNombre(nombre);
        p.setDia(dia);
        p.setMes(mes);
        p.setProfesion(profesion);
        p.setEmail(email);
        return true;
    }

    public void agregarRelacion(Persona p1, LocalDate fecha, Persona p2) {
        if (p1 == null || p2 == null) {
            System.out.println("Una o ambas personas no existen.");
            return;
        }
        if (p1.getId() == p2.getId()) {
            System.out.println("No puedes agregar a la misma persona como amiga.");
            return;
        }
        if (p1.estaBloqueado(p2) || p2.estaBloqueado(p1)) {
            System.out.println("No se puede establecer amistad: existe un bloqueo entre " + p1.getNombre() + " y " + p2.getNombre() + ".");
            return;
        }

        boolean posible = p1.addAmigo(p2, fecha);
        if (posible) {
            p2.addAmigo(p1, fecha);
            recomendarAmigos(p1, p2, fecha);
            recomendarAmigos(p2, p1, fecha);
        } else {
            System.out.println(p1.getNombre() + " y " + p2.getNombre() + " ya se encuentran agregados como amigos.");
        }
    }

    public void recomendarAmigos(Persona p, Persona x, LocalDate fecha) {
        ArrayList<Amistad> amistades = p.getAmistades();
        for (Amistad a : amistades) {
            Persona pe = a.getPersona();
            if (pe.getId() != x.getId() && !pe.esAmigoDirecto(x) && !pe.estaBloqueado(x) && !x.estaBloqueado(pe)) {
                System.out.println("===============");
                System.out.println("Para: " + pe.getNombre() + " <" + pe.getEmail() + ">");
                System.out.println("Fecha: " + fecha.format(formato));
                System.out.println("Mensaje: Hola " + pe.getNombre() + ", tal parece que " + p.getNombre()
                        + " acaba de agregar a " + x.getNombre() + " a su lista de amigos, ¡puede que lo conozcas!");
                System.out.println("===============");
            }
        }
    }

    public void bloquearAmigo(Persona p1, LocalDate fecha, Persona p2) {
        if (p1 == null || p2 == null || p1.getId() == p2.getId()) {
            System.out.println("Operación de bloqueo inválida.");
            return;
        }
        p1.bloquear(p2, fecha);
        System.out.println(p1.getNombre() + " ha bloqueado a " + p2.getNombre() + " con fecha " + fecha.format(formato) + ".");
    }

    public ArrayList<Persona> cumpleanosProximos(int k, LocalDate fechaActual) {
        ArrayList<Persona> festejados = new ArrayList<>();
        if (k <= 0) {
            return festejados;
        }

        for (Persona p : usuarios.values()) {
            LocalDate cumple = obtenerProximoCumpleanos(p.getDia(), p.getMes(), fechaActual);
            if (cumple != null) {
                long diasFaltantes = ChronoUnit.DAYS.between(fechaActual, cumple);
                if (diasFaltantes >= 0 && diasFaltantes <= k) {
                    festejados.add(p);
                    enviarCorreoCumpleanos(p, cumple, fechaActual);
                }
            }
        }
        return festejados;
    }

    private LocalDate obtenerProximoCumpleanos(int dia, int mes, LocalDate hoy) {
        int anio = hoy.getYear();
        LocalDate cumple = crearFechaSegura(anio, mes, dia);
        if (cumple.isBefore(hoy)) {
            cumple = crearFechaSegura(anio + 1, mes, dia);
        }
        return cumple;
    }

    private LocalDate crearFechaSegura(int anio, int mes, int dia) {
        if (mes == 2 && dia == 29 && !LocalDate.of(anio, 1, 1).isLeapYear()) {
            return LocalDate.of(anio, 2, 28);
        }
        return LocalDate.of(anio, mes, dia);
    }

    private void enviarCorreoCumpleanos(Persona festejado, LocalDate fechaCumple, LocalDate fechaEnvio) {
        for (Amistad a : festejado.getAmistades()) {
            Persona amigo = a.getPersona();
            System.out.println("===============");
            System.out.println("Para: " + amigo.getNombre() + " <" + amigo.getEmail() + ">");
            System.out.println("Fecha: " + fechaEnvio.format(formato));
            System.out.println("Asunto: Recordatorio de cumpleaños");
            System.out.println("Mensaje: Hola " + amigo.getNombre() + ", te recordamos que tu amigo/a "
                    + festejado.getNombre() + " estará de cumpleaños el día " + fechaCumple.format(formato) + ".");
            System.out.println("===============");
        }
    }

    public int nivelAmistad(Persona p1, Persona p2) {
        if (p1 == null || p2 == null || p1.getId() == p2.getId()) {
            return -1;
        }

        HashMap<Integer, Integer> distancias = new HashMap<>();
        Queue<Persona> cola = new LinkedList<>();

        distancias.put(p1.getId(), 0);
        cola.add(p1);

        while (!cola.isEmpty()) {
            Persona actual = cola.poll();
            int distActual = distancias.get(actual.getId());

            if (actual.getId() == p2.getId()) {
                return distActual;
            }

            for (Amistad a : actual.getAmistades()) {
                Persona vecino = a.getPersona();
                if (!distancias.containsKey(vecino.getId())) {
                    distancias.put(vecino.getId(), distActual + 1);
                    cola.add(vecino);
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public ArrayList<Persona> amigosComunes(Persona p1, Persona p2) {
        ArrayList<Persona> comunes = new ArrayList<>();
        if (p1 == null || p2 == null || p1.getId() == p2.getId()) {
            return comunes;
        }

        HashSet<Integer> idsAmigosP1 = new HashSet<>();
        for (Amistad a : p1.getAmistades()) {
            idsAmigosP1.add(a.getId());
        }

        for (Amistad a : p2.getAmistades()) {
            if (idsAmigosP1.contains(a.getId())) {
                comunes.add(a.getPersona());
            }
        }
        return comunes;
    }

    public ArrayList<Persona> personasConMasAmigosHastaNivel3() {
        ArrayList<Persona> resultado = new ArrayList<>();
        int maxAmigos = -1;

        for (Persona p : usuarios.values()) {
            int cantidad = contarAmigosHastaNivel(p, 3);
            if (cantidad > maxAmigos) {
                maxAmigos = cantidad;
                resultado.clear();
                resultado.add(p);
            } else if (cantidad == maxAmigos) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public int contarAmigosHastaNivel(Persona origen, int nivelMax) {
        HashMap<Integer, Integer> distancias = new HashMap<>();
        Queue<Persona> cola = new LinkedList<>();

        distancias.put(origen.getId(), 0);
        cola.add(origen);

        while (!cola.isEmpty()) {
            Persona actual = cola.poll();
            int distActual = distancias.get(actual.getId());

            if (distActual < nivelMax) {
                for (Amistad a : actual.getAmistades()) {
                    Persona vecino = a.getPersona();
                    if (!distancias.containsKey(vecino.getId())) {
                        distancias.put(vecino.getId(), distActual + 1);
                        cola.add(vecino);
                    }
                }
            }
        }
        return distancias.size() - 1;
    }

    public void printUsuarios() {
        for (Map.Entry<Integer, Persona> entry : usuarios.entrySet()) {
            Persona persona = entry.getValue();
            System.out.println("ID: " + persona.getId() + ", Nombre: " + persona.getNombre()
                    + ", Cumpleaños: " + persona.getDia() + "/" + persona.getMes()
                    + ", Profesión: " + persona.getProfesion() + ", Email: " + persona.getEmail());
        }
    }
}