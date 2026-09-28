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
        String mensaje;
        try {
            /*
            misContactos.put("DianaGarcia", new Contacto("Diana", "Garcia", 192832828));
            misContactos.put("AnaLopez", new Contacto("Ana", "Lopez", 192832828));
            misContactos.put("ElitoDiaz", new Contacto("Eliot", "Diaz", 192832828));*/
            System.out.println("Ingresa el nombre del contacto que deseas modificar");
            String nombre = scanner.nextLine();
            System.out.println("Ingresa el apellido del contacto que deseas modificar");
            String apellido = scanner.nextLine();

            keyName = (nombre.trim()) + (apellido.trim());

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
                +("\n contactoUpdate "+ contactoUpdate.getNombre())
                +("\n contactoUpdate "+ contactoUpdate.getApellido())
                +("\n contactoUpdate "+ contactoUpdate.getNumero());
            }else {
                mensaje = ("El contacto no existe");
                return mensaje;
            }
        } catch (InvalidData e) {
            throw new RuntimeException(e);
        }
        return mensaje;
    }

} //Cierre de clase Agenda
