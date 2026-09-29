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

    public void eliminarContacto(String nombreApellido) {
        String numero = misContactos.get(nombreApellido).getNumero();
        misContactos.remove(nombreApellido);
        System.out.println(String.format("Se elimino el contacto %s", numero));
    }
    public Boolean agendaLlena () {
        if (misContactos.size() == MAX_SIZE) {
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
    public String listarContactos() {
        StringBuilder listaContactos = new StringBuilder();

        System.out.println("LISTA DE CONTACTOS (ORDENADOS DE LA A - Z)");
        if(misContactos.isEmpty()){
            listaContactos.append("La agenda esta vacía");
            System.out.println(listaContactos);
        } else {
            int index = 1;
            for (Contacto c : misContactos.values()){
                listaContactos.append("=== Contacto ").append(index).append(" ===\n")
                        .append(c.getNombre()).append("\n")
                        .append(c.getApellido()).append("\n")
                        .append(c.getNumero()).append("\n");
                index++;
            }
            System.out.println(listaContactos);
        }

        return listaContactos.toString();
    } //Cierre de metodo listar

    // Verifica si un contacto ya existe en la agenda
    public boolean existeContacto(String nombre, String apellido) {

        // Creamos la clave usando el nombre y apellido del contacto
        String key = createKey(nombre, apellido);

        // Verificamos si esa clave ya existe en el TreeMap
        return misContactos.containsKey(key);
    }


    public String updateTelefono (String keyName, String newTelefono){
        String mensaje = "";
        try {
                /* Guardamos informacion  */
                Contacto contactoUpdate = misContactos.get(keyName);
                contactoUpdate.setNumero(newTelefono);
                /* Actualizar listata Agenda */
                misContactos.put(keyName, contactoUpdate);
                mensaje = "Numero actualizado actualizado"
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

    public String buscarContacto(String nombre, String apellido) {
        if (nombre+apellido == null || (nombre+apellido).trim().isEmpty()) {
            return null;
        }

        String llave = createKey(nombre,apellido);
        Contacto found = misContactos.get(llave);
        if(found!= null){
            return found.getNumero();
        } else {
            return null;
        }
    }

    public Contacto buscarContactoNombreApellido(String nombreApellido) {
        return misContactos.get(nombreApellido);
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
