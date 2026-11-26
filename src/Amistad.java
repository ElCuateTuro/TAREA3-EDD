import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Amistad {
    private Persona persona;
    private int id;
    private LocalDate fecha;
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Amistad(Persona p, LocalDate fecha) {
        this.persona = p;
        this.fecha = fecha;
        this.id = p.getId();
    }

    public int getId() {
        return id;
    }

    public Persona getPersona() {
        return persona;
    }

    public String getFecha() {
        return fecha.format(formato);
    }

    public LocalDate getFechaRaw() {
        return fecha;
    }
}