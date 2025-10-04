import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    // Listas para almacenar los datos en memoria
    private static ArrayList<Doctor> doctores = new ArrayList<>();
    private static ArrayList<Paciente> pacientes = new ArrayList<>();
    private static ArrayList<Cita> citas = new ArrayList<>();

    // --- MÉTODOS PARA CARGAR Y GUARDAR DATOS (MANEJO DE ARCHIVOS) ---

    private static void cargarDatos() {
        // Crear carpeta 'db' si no existe
        File dbFolder = new File("db");
        if (!dbFolder.exists()) {
            dbFolder.mkdir();
        }

        // Cargar Doctores
        try (BufferedReader br = new BufferedReader(new FileReader("db/doctores.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                doctores.add(new Doctor(Integer.parseInt(datos[0]), datos[1], datos[2]));
            }
        } catch (IOException e) {
            System.out.println("Archivo doctores.csv no encontrado, se creará uno nuevo al guardar.");
        }

        // Cargar Pacientes (similar a doctores)
        try (BufferedReader br = new BufferedReader(new FileReader("db/pacientes.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                pacientes.add(new Paciente(Integer.parseInt(datos[0]), datos[1]));
            }
        } catch (IOException e) {
            System.out.println("Archivo pacientes.csv no encontrado, se creará uno nuevo al guardar.");
        }

    }

    private static void guardarDatos() {
        // Guardar Doctores
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("db/doctores.csv"))) {
            for (Doctor doc : doctores) {
                bw.write(doc.getId() + "," + doc.getNombre() + "," + doc.getEspecialidad());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar doctores: " + e.getMessage());
        }

        // Guardar Pacientes
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("db/pacientes.csv"))) {
            for (Paciente pac : pacientes) {
                bw.write(pac.getId() + "," + pac.getNombre());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar pacientes: " + e.getMessage());
        }

        System.out.println("Datos guardados exitosamente.");
    }

    // --- MÉTODOS DEL MENÚ ---

    private static void darDeAltaDoctor(Scanner scanner) {
        System.out.print("Ingrese ID del doctor: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese nombre completo del doctor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese especialidad: ");
        String especialidad = scanner.nextLine();
        doctores.add(new Doctor(id, nombre, especialidad));
        System.out.println("¡Doctor registrado exitosamente!");
    }

    private static void darDeAltaPaciente(Scanner scanner) {
        System.out.print("Ingrese ID del paciente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese nombre completo del paciente: ");
        String nombre = scanner.nextLine();
        pacientes.add(new Paciente(id, nombre));
        System.out.println("¡Paciente registrado exitosamente!");
    }

    private static void crearCita(Scanner scanner) {
        if (doctores.isEmpty() || pacientes.isEmpty()) {
            System.out.println("Debe haber al menos un doctor y un paciente registrados para crear una cita.");
            return;
        }

        System.out.print("Ingrese ID de la cita: ");
        int idCita = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese fecha y hora (dd/mm/aaaa hh:mm): ");
        String fechaHora = scanner.nextLine();
        System.out.print("Ingrese motivo de la cita: ");
        String motivo = scanner.nextLine();
        System.out.print("Ingrese ID del doctor para la cita: ");
        int idDoctor = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese ID del paciente para la cita: ");
        int idPaciente = scanner.nextInt();
        scanner.nextLine();

        Doctor doctorAsignado = null;
        for (Doctor doc : doctores) {
            if (doc.getId() == idDoctor) {
                doctorAsignado = doc;
                break;
            }
        }

        Paciente pacienteAsignado = null;
        for (Paciente pac : pacientes) {
            if (pac.getId() == idPaciente) {
                pacienteAsignado = pac;
                break;
            }
        }

        if (doctorAsignado != null && pacienteAsignado != null) {
            citas.add(new Cita(idCita, fechaHora, motivo, doctorAsignado, pacienteAsignado));
            System.out.println("¡Cita creada exitosamente!");
        } else {
            System.out.println("Error: Doctor o Paciente no encontrado.");
        }
    }

    // --- MÉTODO MAIN ---
    public static void main(String[] args) {
        cargarDatos();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID de administrador: ");
        String adminId = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String password = scanner.nextLine();

        if (!adminId.equals("admin") || !password.equals("1234")) {
            System.out.println("Acceso Denegado.");
            return;
        }

        System.out.println("Acceso Concedido.");
        String opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Dar de alta doctores");
            System.out.println("2. Dar de alta pacientes");
            System.out.println("3. Crear una cita");
            System.out.println("4. Ver todas las citas");
            System.out.println("5. Salir y Guardar");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();

            try {
                switch (opcion) {
                    case "1":
                        darDeAltaDoctor(scanner);
                        break;
                    case "2":
                        darDeAltaPaciente(scanner);
                        break;
                    case "3":
                        crearCita(scanner);
                        break;
                    case "4":
                        System.out.println("\n--- Listado de Citas ---");
                        for(Cita c : citas) {
                            System.out.println(c);
                            System.out.println("--------------------");
                        }
                        break;
                    case "5":
                        guardarDatos();
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.err.println("Ocurrió un error: " + e.getMessage() + ". Por favor, intente de nuevo.");
                scanner.nextLine();
            }
        } while (!opcion.equals("5"));
        scanner.close();
    }
}