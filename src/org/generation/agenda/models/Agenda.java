package org.generation.agenda.models;

import java.util.TreeMap;

public class Agenda {
    // El TreeMap usa el 'número' (String) como Clave única, y el objeto 'Contacto' como Valor
    private TreeMap<String, Contacto>  contactos;
    private int MAX_SIZE;
  
    //Constructor por defecto (tamaño 10 por defecto)
    public Agenda () {
        this.contactos = new TreeMap<>();
        this.MAX_SIZE = 10;
    }
  
    // Constructor: con tamaño personalizado del usuario
    public Agenda(int MAX_SIZE) {
        this.contactos = new TreeMap<>();
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
        if(contactos.isEmpty()){
            System.out.println("La agenda esta vacía");
        } else {
            for (Contacto c : contactos.value()){
                System.out.println(c);
            }
        }

        }
    } //Cierre de metodo listar

} //Cierre de clase Agenda
