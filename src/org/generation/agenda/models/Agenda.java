package org.generation.agenda.models;

import org.generation.agenda.exceptions.InvalidData;

import java.util.Scanner;
import java.util.TreeMap;

public class Agenda {
    // El TreeMap usa el 'número' (String) como Clave única, y el objeto 'Contacto' como Valor
    private TreeMap<String, Contacto> misContactos;

    // Constructor: Inicializa la agenda vacía
    public Agenda() {
        this.misContactos = new TreeMap<>();
    }


    public String updateContacto (){
        Scanner scanner = new Scanner(System.in);
        String keyName ;
        String mensaje = "";
        try {
            /*
            misContactos.put("DianaGarcia", new Contacto("Diana", "Garcia", 192832828));
            misContactos.put("AnaLopez", new Contacto("Ana", "Lopez", 192832828));
            misContactos.put("ElitoDiaz", new Contacto("Eliot", "Diaz", 192832828));*/
            System.out.println("Ingresa el nombre del contacto que deseas modificar");
            String nombre = scanner.nextLine();
            System.out.println("Ingresa el apellido del contacto que deseas modificar");
            String apellido = scanner.nextLine();

            if ((nombre==null || nombre.isEmpty())|| (apellido==null || apellido.isEmpty())) {
                return "Error: Debes ingresar todos los datos";
            }

            keyName = this.createKey( nombre.trim() , apellido.trim() );
            Contacto contactoUpdate = misContactos.get(keyName);
            if(contactoUpdate !=null ){
                System.out.println("Ingresa el nuevo nombre");
                String newNombre = scanner.nextLine();
                System.out.println("Ingresa el nuevo apellido");
                String newApellido = scanner.nextLine();
                System.out.println("Ingresa el nuevo telefono");
                Integer newTelefono = scanner.nextInt();

                /* Guardamos informacion  */
                contactoUpdate = new Contacto(newNombre, newApellido, newTelefono);
                /* Actualizar listata Agenda */
                misContactos.put(keyName, contactoUpdate);
                mensaje = "Contacto actualizado \n"
                +("\n Nombre "+ contactoUpdate.getNombre())
                +("\n Apellido "+ contactoUpdate.getApellido())
                +("\n Numero "+ contactoUpdate.getNumero());
            }else {
                mensaje = ("El contacto no existe");
                return mensaje;
            }
        } catch (InvalidData e) {
            throw new RuntimeException(e);
        }
        return mensaje;
    }

    public String createKey (String nombre, String apellido) {
        String key = "";
        String nombreLetraMayuscula = nombre.substring(0, 1).toUpperCase();
        String nombreLetrasMinuscula = nombre.substring(1).toLowerCase();
        String apellidoLetraMayuscula = apellido.substring(0, 1).toUpperCase();
        String apellidoLetrasMinuscula = apellido.substring(1).toLowerCase();

        key = (nombreLetraMayuscula+nombreLetrasMinuscula) + (apellidoLetraMayuscula+apellidoLetrasMinuscula);

        return key;
    }
} //Cierre de clase Agenda
