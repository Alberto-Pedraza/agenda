package org.generation.agenda.models;

import org.generation.agenda.exceptions.InvalidData;

import java.util.Scanner;
import java.util.TreeMap;

public class Agenda {
    // El TreeMap usa el 'número' (String) como Clave única, y el objeto 'Contacto' como Valor
    private TreeMap<String, Contacto>  misContactos;
    private int MAX_SIZE;
  
    //Constructor por defecto (tamaño 10 por defecto)
    public Agenda () {
        this.misContactos = new TreeMap<>();
        this.MAX_SIZE = 10;
    }
  
    // Constructor: con tamaño personalizado del usuario
    public Agenda(int MAX_SIZE) {
        this.misContactos = new TreeMap<>();
        this.MAX_SIZE = MAX_SIZE;
    }

    public void eliminarContacto(String numero) {
        misContactos.remove(numero);
        System.out.println(String.format("Se elimino el contacto %s", numero));
    }
    public Boolean agendaLlena () {
        if (misContactos.size() == MAX_SIZE) {
            System.out.println("No hay espacio disponible para nuevos contactos.");
            return true;
        } else {
            return false;
        }
    } // Fin agendaLlena

    //Metodo de espacios libres, recibe el treemap de contactos y el numero limite
    //Ejemplo para imprimir
    //System.out.println("Hay " + espaciosLibres(contactos, var_limite) + " espacio(s) disponible(s)");
    public static int espaciosLibres(TreeMap<String, Contacto> contactos, int limite){
        return limite - contactos.size();
    }


    //Apertura de metodo listar contactos
    public void listarContactos() {
        System.out.println("LISTA DE CONTACTOS (ORDENADOS DE LA A - Z)");
        if(misContactos.isEmpty()){
            System.out.println("La agenda esta vacía");
        } else {
            for (Contacto c : misContactos.value()){
                System.out.println(c);
            }
        }
    } //Cierre de metodo listar

    // Verifica si un contacto ya existe en la agenda
    public boolean existeContacto(Contacto c) {

        // Recorremos todos los contactos guardados
        for (Contacto contactoGuardado : misContactos.values()) {

            // Comparamos nombre y apellido ignorando mayúsculas/minúsculas
            if (c.getNombre().equalsIgnoreCase(contactoGuardado.getNombre())
                    && c.getApellido().equalsIgnoreCase(contactoGuardado.getApellido())) {

                return true;
            }
        }

        // Si terminó de buscar y no encontró coincidencias
        return false;
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
