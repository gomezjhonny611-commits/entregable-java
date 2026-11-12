import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProfesoresEstudiantes {

    ArrayList<String> listaProfesores;
    ArrayList<String> listEstudiantes;
    ArrayList<ArrayList<String>> listaGeneralEstudiantes;

    public ProfesoresEstudiantes() {
        listaProfesores = new ArrayList<>();
        listaGeneralEstudiantes = new ArrayList<>();
    }

    public void iniciar() {
        int opcion = 0;
        String menu = "<<<< MENÚ DE OPCIONES >>>>\n"
                + "1. Registrar profesores\n"
                + "2. Registrar estudiantes asociados al profesor\n"
                + "3. Consultar lista total de profesores y sus estudiantes asociados\n"
                + "4. Consultar un profesor e imprimir la lista de estudiantes asociados\n"
                + "5. Consultar un profesor e indicar la cantidad de estudiantes asociados\n"
                + "6. Consultar un estudiante e indicar cuál es su director de grupo\n"
                + "7. Salir\n"
                + "Seleccione una opción:";

        do {
            try {
                String input = JOptionPane.showInputDialog(menu);
                if (input == null) {
                    break;
                }
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        registrarProfesores();
                        break;
                    case 2:
                        registrarEstudiantes();
                        break;
                    case 3:
                        consultarListaProfesoresYEstudiantes();
                        break;
                    case 4:
                        consultaProfesorPorNombre();
                        break;
                    case 5:
                        consultaCantidadEstudiantesPorProfesor();
                        break;
                    case 6:
                        consultarEstudiante();
                        break;
                    case 7:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Intente nuevamente.");
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número entero.");
            }
        } while (opcion != 7);
    }

    private void registrarProfesores() {
        System.out.println("\n<<<< Registro de Profesores >>>>");
        int cant = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la Cantidad de profesores"));
        for (int i = 0; i < cant; i++) {
            String nombreProfesor = JOptionPane.showInputDialog("nombre Profesor " + (i + 1));
            
            if (listaProfesores.contains(nombreProfesor)) {
                JOptionPane.showMessageDialog(null, "El profesor " + nombreProfesor + " ya existe. No se registrará nuevamente.");
                i--; 
            } else {
                listaProfesores.add(nombreProfesor);
            }
        }
        System.out.println("Registro de profesores Exitoso!\n");
    }

    private void registrarEstudiantes() {
        if (listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores.");
            return;
        }

        System.out.println("\n<<<< Registro de Estudiantes >>>>");
        for (int i = 0; i < listaProfesores.size(); i++) {
            JOptionPane.showMessageDialog(null, "Ingrese los estudiantes para el profesor " + listaProfesores.get(i));
            listEstudiantes = new ArrayList<String>();
            int cant = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de estudiantes"));
            
            for (int j = 0; j < cant; j++) {
                String nombreEst = JOptionPane.showInputDialog("nombre estudiante " + (j + 1));
                
                if (listEstudiantes.contains(nombreEst)) {
                    JOptionPane.showMessageDialog(null, "El estudiante " + nombreEst + " ya existe en este grupo. Ingrese otro.");
                    j--;
                } else {
                    listEstudiantes.add(nombreEst);
                }
            }
            
            if (i < listaGeneralEstudiantes.size()) {
                listaGeneralEstudiantes.set(i, listEstudiantes);
            } else {
                listaGeneralEstudiantes.add(listEstudiantes);
            }
        }
        System.out.println("Registro de estudiantes Exitoso!\n");
    }

    private void consultarListaProfesoresYEstudiantes() {
        if (listaProfesores.isEmpty() || listaGeneralEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores y estudiantes.");
            return;
        }

        System.out.println("\n<<<< Lista de Profesores y Estudiantes >>>>");
        ArrayList<String> listaTemporal;
        for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {
            listaTemporal = listaGeneralEstudiantes.get(i);
            System.out.print("Profesor: " + listaProfesores.get(i) + " = ");
            System.out.print("[");
            for (int j = 0; j < listaTemporal.size(); j++) {
                System.out.print(listaTemporal.get(j));
                if (j < listaTemporal.size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("]\n");
        }
    }

    private void consultaProfesorPorNombre() {
        if (listaProfesores.isEmpty() || listaGeneralEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores y estudiantes.");
            return;
        }

        System.out.println("\n<<<< Consulta de Profesor >>>> \n");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor");
        
        if (listaProfesores.contains(nombre)) {
            int posicion = listaProfesores.indexOf(nombre);
            ArrayList<String> listaEstudiantesTemporal = listaGeneralEstudiantes.get(posicion);
            
            System.out.print("Profesor: " + nombre + " ");
            System.out.print("[");
            for (int j = 0; j < listaEstudiantesTemporal.size(); j++) {
                System.out.print(listaEstudiantesTemporal.get(j));
                if (j < listaEstudiantesTemporal.size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("]\n");
        } else {
            System.out.println("No se encuentra el profesor " + nombre + "\n");
        }
    }

    private void consultaCantidadEstudiantesPorProfesor() {
        if (listaProfesores.isEmpty() || listaGeneralEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores y estudiantes.");
            return;
        }

        System.out.println("\n<<<< Consulta cantidad de estudiantes de Profesores >>>> \n");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor para saber la cantidad de estudiantes asociados");
        
        if (listaProfesores.contains(nombre)) {
            int posicion = listaProfesores.indexOf(nombre);
            ArrayList<String> listaEstudiantesTemporal = listaGeneralEstudiantes.get(posicion);
            System.out.println("La cantidad de estudiantes asociados al profesor " + nombre + " es " + listaEstudiantesTemporal.size());
        } else {
            System.out.println("No se encuentra el profesor " + nombre + "\n");
        }
    }

    private void consultarEstudiante() {
        if (listaGeneralEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar estudiantes.");
            return;
        }

        System.out.println("\n<<<< Consulta de Estudiante >>>> \n");
        ArrayList<String> listTemporal;
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante a consultar");
        
        for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {
            listTemporal = listaGeneralEstudiantes.get(i);
            if (listTemporal.contains(nombre)) {
                for (int j = 0; j < listTemporal.size(); j++) {
                    if (listTemporal.get(j).equalsIgnoreCase(nombre)) {
                        System.out.println("Encuentra al nombre: " + nombre + " pertenece al grupo de " + listaProfesores.get(i));
                    }
                }
            } else {
                System.out.println("No se encuentra el nombre en el grupo de " + listaProfesores.get(i));
            }
        }
    }
}