/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.Objects;


/**
 *
 * @author Agust
 */
public class Animal implements Comparable<Animal>, CSVSerializable, Serializable {

    private static final long serialVersionUID = 1L; 
    private final int id;
    private final String nombre;
    private final String especie;
    private final TipoAlimentacion tipoAlimentacion;

    public Animal(int id, String nombre, String especie, TipoAlimentacion tipo) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.tipoAlimentacion = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public TipoAlimentacion getTipo() {
        return tipoAlimentacion;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Animal a)){
            return false;
        }
        return this.id == a.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    
    @Override
    public int compareTo(Animal other) {
        return Integer.compare(this.id, other.id); // Orden natural por ID
    }

    @Override
    public String toString() {
        return "ANIMAL = " + nombre +
                " | ID = " + id +
                " | ESPECIE = '" + especie + '\'' +
                " | TIPO DE ALIMENTACION = " + tipoAlimentacion;
    }

    @Override
    public String toCSV() { 
        return id + "," + nombre + "," + especie + "," + tipoAlimentacion;
    }

    public static Animal fromCSV(String linea) { // Implementación del método estático fromCSV(String)
        if (linea.endsWith("\n")) {
            linea = linea.substring(0, linea.length() - 1);
        }
        String[] values = linea.split(",");
        if (values.length == 4) {
            int id = Integer.parseInt(values[0]);
            String nombre = values[1];
            String especie = values[2];
            TipoAlimentacion tipoAlimentacion = TipoAlimentacion.valueOf(values[3]);

            return new Animal(id, nombre, especie, tipoAlimentacion);
        }
        return null; // Retorna null si el formato es inválido
    }

    public static String toCSVHeader() {
        return "ID,NOMBRE,ESPECIE,TIPODEALIMENTACION";
    }
}
