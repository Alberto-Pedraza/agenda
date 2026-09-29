import javax.swing.JOptionPane;

import org.generation.agenda.exceptions.InvalidData;
import org.generation.agenda.models.Agenda;
import org.generation.agenda.models.Contacto;


public class Main {

    public static void main(String[] args) throws InvalidData {

        //Variable sin asignacion para decidir que objeto asignarle
        Agenda agenda;

        String respuesta = JOptionPane.showInputDialog(
                "--- CONFIGURACIÓN INICIAL DE LA AGENDA ---\n\n" +
                        "¿Deseas definir un tamaño máximo personalizado? (s/n):"
        );

        if (respuesta.equalsIgnoreCase("s")) {
            String entrada = JOptionPane.showInputDialog(
                    "Introduce el límite de contactos:"
            );
            int limite = Integer.parseInt(entrada);
            agenda = new Agenda(limite);
            JOptionPane.showMessageDialog(
                    null,
                    "Agenda configurada con capacidad para " + limite + " contactos.");
        } else {
            agenda = new Agenda();
            JOptionPane.showMessageDialog(
                    null,
                    "Configurada automáticamente con capacidad para 10 contactos."
            );
        }

        int opcion = 0;

        // =========================
        // MENÚ PRINCIPAL
        // =========================

        do {

            String menu =
                    "===== AGENDA DE CONTACTOS =====\n\n" +
                            "1. Añadir contacto\n" +
                            "2. Comprobar si existe un contacto\n" +
                            "3. Listar contactos\n" +
                            "4. Buscar contacto\n" +
                            "5. Eliminar contacto\n" +
                            "6. Comprobar si la agenda está llena\n" +
                            "7. Ver espacios disponibles\n" +
                            "8. Editar un contacto \n" +
                            "9. Salir";

            // Pedimos una opción
            String entrada = JOptionPane.showInputDialog(menu);

            // Si presiona Cancelar
            if (entrada == null) {
                break;
            }

            // Validamos que solamente ingrese números
            if (!entrada.matches("\\d+")) {

                JOptionPane.showMessageDialog(
                        null,
                        "Solo puedes ingresar números."
                );

                continue;
            }

            // Convertimos String a int
            opcion = Integer.parseInt(entrada);

            switch (opcion) {

                // =========================================
                // OPCIÓN 1: AÑADIR CONTACTO
                // =========================================
                case 1:

                    // Pedimos el nombre
                    String nombre = JOptionPane.showInputDialog(
                            "Ingresa el nombre:"
                    );

                    // Si presiona cancelar
                    if (nombre == null) {
                        break;
                    }

                    // Validamos que no esté vacío
                    if (nombre.trim().isEmpty()) {

                        JOptionPane.showMessageDialog(
                                null,
                                "El nombre no puede estar vacío."
                        );

                        break;
                    }

                    // Pedimos los apellidos
                    String apellidos = JOptionPane.showInputDialog(
                            "Ingresa los apellidos:"
                    );

                    if (apellidos == null) {
                        break;
                    }

                    // Validamos que no estén vacíos
                    if (apellidos.trim().isEmpty()) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Los apellidos no pueden estar vacíos."
                        );

                        break;
                    }

                    // Variable para guardar teléfono
                    String telefono;

                    // Pedimos el teléfono hasta que ingrese solo números
                    do {

                        telefono = JOptionPane.showInputDialog(
                                "Ingresa el teléfono:\n" +
                                        "Solo se permiten números."
                        );

                        // Si presiona cancelar
                        if (telefono == null) {
                            break;
                        }

                        telefono = telefono.trim();

                        // Validar solo números
                        if (!telefono.matches("\\d+")) {

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Teléfono no válido.\n" +
                                            "Solo puedes ingresar números."
                            );
                        }

                    } while (!telefono.matches("\\d+"));

                    // Si canceló
                    if (telefono == null) {
                        break;
                    }

                    // Creamos el contacto
                    Contacto nuevoContacto = new Contacto(
                            nombre,
                            apellidos,
                            telefono
                    );

                    // Revisamos si ya existe
                    if (agenda.existeContacto(nuevoContacto)) {

                        JOptionPane.showMessageDialog(
                                null,
                                "El contacto ya existe."
                        );

                    } else if (agenda.agendaLlena()) {

                        JOptionPane.showMessageDialog(
                                null,
                                "No se puede agregar.\n" +
                                        "La agenda está llena."
                        );

                    } else {

                        // Agregamos contacto
                        agenda.anadirContacto(nuevoContacto);

                        JOptionPane.showMessageDialog(
                                null,
                                "Contacto agregado correctamente.\n\n" +
                                        nuevoContacto.getNombre()+"\n"+nuevoContacto.getApellido()+"\n"+nuevoContacto.getNumero()
                        );
                    }

                    break;

                // =========================================
                // OPCIÓN 3: ENLISTAR CONTACTO
                // =========================================
                case 3:
                    agenda.listarContactos();
                    break;

                // =========================================
                // OPCIÓN 4: BUSCAR CONTACTO
                // =========================================
                case 4:
                    String nombreBuscar = JOptionPane.showInputDialog("Ingresa el nombre del contacto que deseas buscar:");

                    if (nombreBuscar == null || nombreBuscar.trim().isEmpty()) {
                        break;
                    }

                    String telefonoEncontrado = agenda.buscarContacto(nombreBuscar);

                    if (telefonoEncontrado != null) {
                        JOptionPane.showMessageDialog(
                                null,
                                "El teléfono de " + nombreBuscar + " es: " + telefonoEncontrado
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "No se encontró ningún contacto con el nombre: " + nombreBuscar
                        );
                    }
                    break;

                // =========================================
                // OPCIÓN 7: VER ESPACIOS DISPONIBLES
                // =========================================
                case 7:
                    int espacios = agenda.espaciosLibres();
                    JOptionPane.showMessageDialog(
                            null,
                            "Hay " + espacios + " espacio(s) disponible(s)"
                    );
                    break;

                case 8:
                    // =========================================
                    // OPCIÓN 9: UPDATE CONTACTO
                    // ========================================
                    String mensaje = "";
                    // 1. Pedimos el nombre y el Apellido
                    String name = JOptionPane.showInputDialog("Ingresa el nombre del contacto que deseas modificar:");
                    String apellido = JOptionPane.showInputDialog("Ingresa el apellido del contacto que deseas modificar:");

                    name= name.trim();
                    apellido= apellido.trim();

                    // 2. Validar datos ingresados
                    if ((name==null || name.isEmpty())|| (apellido==null || apellido.isEmpty())) {
                        JOptionPane.showMessageDialog(null,"Error: Debes ingresar todos los datos");
                    } else {
                        // 2. Validar si exite el registro en Agenda
                        String keyName = agenda.createKey( name.trim() , apellido.trim() );
                        Boolean exite = agenda.getExistContactoInMisContactos(keyName);
                        if(exite==true){
                            String newNombre = JOptionPane.showInputDialog("Ingresa el nuevo nombre:");
                            String newApellido = JOptionPane.showInputDialog("Ingresa el nuevo apellido:");
                            String newTelefono = JOptionPane.showInputDialog("Ingresa el nuevo telefono:");

                            // 3. Modificar registro y actualizar en agenda
                            mensaje = agenda.updateContacto(keyName,newNombre,newApellido,newTelefono);
                        }else {
                            mensaje = "Error: El contacto no existe";
                        }
                            JOptionPane.showMessageDialog(null,mensaje);

                    }
                    break;

                case 9:
                    // =========================================
                    // OPCIÓN 9: SALIR
                    // =========================================
                    JOptionPane.showMessageDialog(
                            null,
                            "Saliendo de la agenda..."
                    );

                    break;
                default:

                    JOptionPane.showMessageDialog(
                            null,
                            "Opción no válida, ingrese una opción del menú."
                    );
            }

        } while (opcion != 9);
    }
}
