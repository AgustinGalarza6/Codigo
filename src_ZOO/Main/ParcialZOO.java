/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Config.AppConfig;
import Model.Animal;
import Model.TipoAlimentacion;
import Model.Zoologico;
import java.io.IOException;
import java.util.Comparator;

/**
 *
 * @author Agust
 */
public class ParcialZOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        try { 
            
            // Crear un zoológico de animales 
            
            Zoologico<Animal> zoologico = new Zoologico<>(); 
            zoologico.agregar(new Animal(1, "Leon", "Panthera leo", TipoAlimentacion.CARNIVORO)); 
            zoologico.agregar(new Animal(2, "Elefante", "Loxodonta", TipoAlimentacion.HERBIVORO)); 
            zoologico.agregar(new Animal(3, "Oso", "Ursus arctos", TipoAlimentacion.OMNIVORO)); 
            zoologico.agregar(new Animal(4, "Zorro", "Vulpes vulpes", TipoAlimentacion.CARNIVORO)); 
            zoologico.agregar(new Animal(5, "Gorila", "Gorilla gorilla", TipoAlimentacion.OMNIVORO)); 
 
            // Mostrar todos los animales en el zoológico 
            System.out.println("===========================================================================\n");
            System.out.println("INVENTARIO DE ANIMALES :\n");
            zoologico.paraCadaElemento(animal -> System.out.println(animal)); 
 
            // Filtrar animales por tipo de alimentación CARNIVORO 
            System.out.println("\n===========================================================================\n");
            System.out.println("ANIMALES CARNIVOROS: \n");
            zoologico.filtrar(Animal -> Animal.getTipo() == TipoAlimentacion.CARNIVORO) 
                    .forEach(animal -> System.out.println(animal)); 
 
            // Filtrar animales cuyo nombre contiene "León" 
            System.out.println("\n===========================================================================\n");
            System.out.println("ANIMALES CUYO NOMBRE CONTIENE 'LEON': \n");
 
            zoologico.filtrar((Animal -> Animal.getNombre().contains("Leon"))) 
                    .forEach(animal -> System.out.println(animal)); 
 
            // Ordenar animales de manera natural (por id) 
            System.out.println("\n===========================================================================\n");
            System.out.println("ANIMALES ORDENADOS POR ID: \n");
            zoologico.ordenar(); 
            zoologico.paraCadaElemento(animal -> System.out.println(animal)); 
 
            // Ordenar animales por nombre utilizando un Comparator 
            System.out.println("\n===========================================================================\n");
            System.out.println("ANIMALES ORDENADOS POR NOMBRE: \n");
            zoologico.ordenar(Comparator.comparing(Animal::getNombre));
            zoologico.paraCadaElemento(animal -> System.out.println(animal));

            
            // SERIALIZA
            System.out.println("\n===========================================================================\n");
            System.out.println("SERIALIZANDO Y GUARDANDO PELICULAS...\n");
            zoologico.guardarEnArchivo(AppConfig.getRutaBinString());
            System.out.println("\nCATALOGO GUARDADO CORRECTAMENTE EN: " + AppConfig.getRutaBinString());
            

            // DESERIALIZA
            Zoologico<Animal> zoologicoCargadoBinario = new Zoologico<>(); 
            System.out.println("\n===========================================================================\n");
            System.out.println("DESERIALIZANDO Y CARGANDO PELICULAS DESDE ARCHIVO BINARIO...\n");
            zoologicoCargadoBinario.cargarDesdeArchivo(AppConfig.getRutaBinString());
            zoologicoCargadoBinario.paraCadaElemento(pelicula -> System.out.println(pelicula));
 
            // Guardar el catálogo en archivo CSV
            zoologicoCargadoBinario.guardarEnCSV(AppConfig.getRutaCSVString());
            System.out.println("\nGUARDANDO CATALOGO EN: " + AppConfig.getRutaCSVString());
            
            // Cargar el catálogo desde archivo CSV
            Zoologico<Animal> ZoologicoCargadoCSV = new Zoologico<>();

            ZoologicoCargadoCSV.cargarDesdeCSV(AppConfig.getRutaCSVString(), Animal::fromCSV);
            System.out.println("\n===========================================================================\n");
            System.out.println("\nPELICULAS CARGADAS DESDE ARCHIVO CSV...\n");
            ZoologicoCargadoCSV.paraCadaElemento(pelicula -> System.out.println(pelicula)); // Lambda completada
            System.out.println("\n===========================================================================\n");
 
        } catch (IOException | ClassNotFoundException e) { 
            System.err.println("Error: " + e.getMessage()); 
        } 
    } 
}
    

