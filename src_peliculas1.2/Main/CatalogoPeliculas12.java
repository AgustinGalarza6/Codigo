/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Config.AppConfig;
import Model.Catalogo;
import Model.Genero;
import Model.Pelicula;
import java.io.IOException;
import java.util.Comparator;

/**
 *
 * @author Agust
 */
public class CatalogoPeliculas12 { // Asegúrate de que el nombre de tu clase principal sea este
    public static void main(String[] args) {
        
        try { 
            
            // Crear un catálogo de películas
            Catalogo<Pelicula> catalogoPeliculas = new Catalogo<>();
            catalogoPeliculas.agregar(new Pelicula(1, "El Padrino", "Francis Ford Coppola", Genero.DRAMA));
            catalogoPeliculas.agregar(new Pelicula(2, "La La Land", "Damien Chazelle", Genero.COMEDIA));
            catalogoPeliculas.agregar(new Pelicula(3, "Guerra Mundial Z", "Marc Forster", Genero.TERROR));
            catalogoPeliculas.agregar(new Pelicula(4, "Toy Story", "John Lasseter", Genero.ANIMACION));
            catalogoPeliculas.agregar(new Pelicula(5, "The Social Dilemma", "Jeff Orlowski", Genero.DOCUMENTAL));

            // Mostrar todas las películas en el catálogo
            System.out.println("===========================================================================\n");
            System.out.println("CATALOGO DE PELICULAS :\n");
            catalogoPeliculas.paraCadaElemento(pelicula -> System.out.println(pelicula)); 

            // Filtrar películas por género TERROR
            System.out.println("\n===========================================================================\n");
            System.out.println("PELICULAS DE GENERO TERROR: \n");
            catalogoPeliculas.filtrar(pelicula -> pelicula.getGenero() == Genero.TERROR)
                    .forEach(pelicula -> System.out.println(pelicula));

            // Filtrar películas cuyo título contiene "Guerra"
            System.out.println("\n===========================================================================\n");
            System.out.println("PELICULAS CUYO TITULO CONTIENE 'GUERRA': \n");
            catalogoPeliculas.filtrar(pelicula -> pelicula.getTitulo().contains("Guerra"))
                    .forEach(pelicula -> System.out.println(pelicula));

            // Ordenar películas de manera natural (por id)
            System.out.println("\n===========================================================================\n");
            System.out.println("PELICULAS ORDENADAS POR ID: \n");
            catalogoPeliculas.ordenar();
            catalogoPeliculas.paraCadaElemento(pelicula -> System.out.println(pelicula));

            // Ordenar películas por titulo usando Comparator
            System.out.println("\n===========================================================================\n");
            System.out.println("PELICULAS ORDENADAS POR TITULO: \n");
            catalogoPeliculas.ordenar(Comparator.comparing(Pelicula::getTitulo));
            catalogoPeliculas.paraCadaElemento(pelicula -> System.out.println(pelicula));

            // SERIALIZA
            System.out.println("\n===========================================================================\n");
            System.out.println("SERIALIZANDO Y GUARDANDO PELICULAS...\n");
            //catalogoPeliculas.guardarEnArchivo(AppConfig.BINARY_FILE_PATH); // APP CONFIG
            //System.out.println("\nCATALOGO GUARDADO CORRECTAMENTE EN: " + AppConfig.BINARY_FILE_PATH);
            catalogoPeliculas.guardarEnArchivo(AppConfig.getRutaBinString());
            System.out.println("\nCATALOGO GUARDADO CORRECTAMENTE EN: " + AppConfig.getRutaBinString());

            // DESERIALIZA
            Catalogo<Pelicula> catalogoCargadoBinario = new Catalogo<>();
            System.out.println("\n===========================================================================\n");
            System.out.println("DESERIALIZANDO Y CARGANDO PELICULAS DESDE ARCHIVO BINARIO...\n");
            //catalogoCargadoBinario.cargarDesdeArchivo(AppConfig.BINARY_FILE_PATH); // APP CONFIG
            catalogoCargadoBinario.cargarDesdeArchivo(AppConfig.getRutaBinString());
            catalogoCargadoBinario.paraCadaElemento(pelicula -> System.out.println(pelicula));
            
            // Guardar el catálogo en archivo CSV
            //catalogoPeliculas.guardarEnCSV(AppConfig.CSV_FILE_PATH); // APP CONFIG
            catalogoPeliculas.guardarEnCSV(AppConfig.getRutaCSVString());
            //System.out.println("\nGUARDANDO CATALOGO EN: " + AppConfig.CSV_FILE_PATH);
            System.out.println("\nGUARDANDO CATALOGO EN: " + AppConfig.getRutaCSVString());
            
            // Cargar el catálogo desde archivo CSV
            Catalogo<Pelicula> catalogoCargadoCSV = new Catalogo<>();
            
            // Se pasa Pelicula::fromCSV como la función para construir el objeto Pelicula desde cada línea CSV
            //catalogoCargadoCSV.cargarDesdeCSV(AppConfig.CSV_FILE_PATH, Pelicula::fromCSV); // Usa AppConfig y lambda completada
            catalogoCargadoCSV.cargarDesdeCSV(AppConfig.getRutaCSVString(), Pelicula::fromCSV);
            System.out.println("\n===========================================================================\n");
            System.out.println("\nPELICULAS CARGADAS DESDE ARCHIVO CSV...\n");
            catalogoCargadoCSV.paraCadaElemento(pelicula -> System.out.println(pelicula)); // Lambda completada
            System.out.println("\n===========================================================================\n");

        } catch (IOException | ClassNotFoundException e) { 
            System.err.println("Error: " + e.getMessage());
        }
    }
}
