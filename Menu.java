import java.util.Scanner;

public class Menu {

    private GestorSolicitud gestor = new GestorSolicitud();
    private Scanner sc = new Scanner(System.in);

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n SELECCIÓN DE ROL ");
            System.out.println("1. Estudiante");
            System.out.println("2. Tutor");
            System.out.println("0. Salir");
            opcion = leerEntero("Opción: ");

            switch (opcion) {
                case 1 -> menuEstudiante();
                case 2 -> menuTutor();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void menuEstudiante() {
        int opcion;
        do {
            System.out.println("\n ESTUDIANTE ");
            System.out.println("1. Registrar solicitud de asignatura");
            System.out.println("2. Ver solicitudes");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre de la asignatura: ");
                    String nombre = sc.nextLine();
                    if (nombre.isEmpty()) {
                        System.out.println("El nombre no puede estar vacío.");
                    } else {
                        Solicitud s = gestor.registrarSolicitud(nombre);
                        System.out.println("Solicitud registrada con ID " + s.getId());
                    }
                }
                case 2 -> gestor.listarSolicitudes();
                case 0 -> { }
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void menuTutor() {
        int opcion;
        do {
            System.out.println("\n TUTOR");
            System.out.println("1. Ver solicitudes");
            System.out.println("2. Atender la siguiente solicitud");
            System.out.println("3. Cambiar estado por ID");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");

            switch (opcion) {
                case 1 -> gestor.listarSolicitudes();
                case 2 -> {
                    Solicitud.EstadoSolicitud estado = elegirEstado();
                    Solicitud s = gestor.atenderSiguiente(estado);
                    if (s == null) System.out.println("No hay solicitudes por atender.");
                    else System.out.println("Solicitud " + s.getId() + " marcada como " + estado.getEtiqueta());
                }
                case 3 -> {
                    int id = leerEntero("ID de la solicitud: ");
                    Solicitud.EstadoSolicitud estado = elegirEstado();
                    if (gestor.cambiarEstado(id, estado)) System.out.println("Estado actualizado");
                    else System.out.println("No existe una solicitud con ese ID.");
                }
                case 0 -> { }
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private Solicitud.EstadoSolicitud elegirEstado() {
        while (true) {
            System.out.println("1. Aceptada  2. Procesada  3. Rechazada");
            int op = leerEntero("Nuevo estado: ");
            switch (op) {
                case 1: return Solicitud.EstadoSolicitud.ACEPTADA;
                case 2: return Solicitud.EstadoSolicitud.PROCESADA;
                case 3: return Solicitud.EstadoSolicitud.RECHAZADA;
                default: System.out.println("Opción no válida.");
            }
        }
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Escribe un número válido.");
            }
        }
    }
}