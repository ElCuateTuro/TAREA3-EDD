import java.time.*;
import java.time.format.DateTimeFormatter;

public class Amistad {
    private int id;
    LocalDateTime fecha;
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/mm/YYYY");

    Amistad(int id, LocalDateTime fecha) {
        this.id=id;
        this.fecha=fecha;
    }

    Amistad getAmistad(){
        return this;
    }

    int getId(){
        return id;
    }

    String getFecha(){
        return fecha.format(formato);
    }
}
