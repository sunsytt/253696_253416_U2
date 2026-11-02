import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

public class GestorSolicitud {

    private Queue<Solicitud> colaSolicitudes = new LinkedList<>();
    private int siguienteId = 1;

    public Solicitud registrarSolicitud(String nombre) {
        Solicitud nueva = new Solicitud(
                siguienteId++,
                nombre,
                LocalDate.now().toString(),
                Solicitud.EstadoSolicitud.PENDIENTE
        );
        colaSolicitudes.add(nueva);
        return nueva;
    }

    public Solicitud atenderSiguiente(Solicitud.EstadoSolicitud nuevoEstado) {
        Solicitud s = colaSolicitudes.poll(); 
        if (s != null) {
            s.setEstado(nuevoEstado);
        }
        return s;
    }

    public boolean cambiarEstado(int id, Solicitud.EstadoSolicitud nuevoEstado) {
        Solicitud s = buscarPorId(id);
        if (s == null) return false;
        s.setEstado(nuevoEstado);
        return true;
    }

    public Solicitud buscarPorId(int id) {
        for (Solicitud s : colaSolicitudes) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void listarSolicitudes() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes registradas.");
            return;
        }
        for (Solicitud s : colaSolicitudes) {
            System.out.println("ID: " + s.getId()
                    + " | Asignatura: " + s.getNombre()
                    + " | Fecha: " + s.getFecha()
                    + " | Estado: " + s.getEstado().getEtiqueta());
        }
    }
}