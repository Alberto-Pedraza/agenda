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

    //Metodo de espacios libres
    public int espaciosLibres(){
        return MAX_SIZE - misContactos.size();
    }


    //Apertura de metodo listar contactos
    public void listarContactos() {
        System.out.println("LISTA DE CONTACTOS (ORDENADOS DE LA A - Z)");
        if(misContactos.isEmpty()){
            System.out.println("La agenda esta vacía");
        } else {
            for (Contacto c : misContactos.values()){
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

    public String updateContacto (String keyName, String newNombre,  String newApellido, String newTelefono){
        String mensaje = "";
        try {
                /* Guardamos informacion  */
                Contacto contactoUpdate = new Contacto(newNombre, newApellido, newTelefono);
                /* Actualizar listata Agenda */
                misContactos.put(keyName, contactoUpdate);
                mensaje = "Contacto actualizado \n"
                        +("\n Nombre "+ contactoUpdate.getNombre())
                        +("\n Apellido "+ contactoUpdate.getApellido())
                        +("\n Numero "+ contactoUpdate.getNumero());

        } catch (InvalidData e) {
            System.out.println("Error al actualizar " + e.getMessage());
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

    public String buscarContacto(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }

        // Buscamos entre todos los contactos guardados
        for (Contacto c : misContactos.values()) {
            if (c.getNombre().equalsIgnoreCase(nombre.trim())) {
                return String.valueOf(c.getNumero());
            }
        }

        return null; // Si no lo encuentra
    }

    public void anadirContacto(Contacto nuevoContacto){
        String key = createKey(nuevoContacto.getNombre(),nuevoContacto.getApellido());
        this.misContactos.put(key,nuevoContacto);
    }

    public TreeMap<String, Contacto> getMisContactos() {
        return misContactos;
    }

    public Boolean getExistContactoInMisContactos (String keyName){
        Contacto contacto = misContactos.get(keyName);
        return ((contacto!=null) ? true : false);
    }
} //Cierre de clase Agenda
