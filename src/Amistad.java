import java.time.*;
import java.time.format.DateTimeFormatter;

public class Amistad {
    private Persona persona;
    private int id;
    LocalDateTime fecha;
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/mm/YYYY");

    Amistad(Persona p, LocalDateTime fecha) {
        this.persona = p;
        this.fecha=fecha;
        this.id = p.getId();
    }
    int getId(){return id;}
    Persona getPersona(){return persona;}
    String getFecha(){
        return fecha.format(formato);
    }
}
