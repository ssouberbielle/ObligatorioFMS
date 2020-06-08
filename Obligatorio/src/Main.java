import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }


    public static void menu() {
        boolean run = true;
        while (run) {
            System.out.println("Seleccione la opción que desee");
            System.out.println("1. Carga de datos");
            System.out.println("2. Ejecutar consultas");
            System.out.println("3. Salir");
            Scanner scanner = new Scanner(System.in);
            int respuesta = scanner.nextInt();
            switch (respuesta) {
                case 1: // metodo de cargar datos e imprimir
                    menu();
                    break;
                case 2:
                    System.out.println("1. Indicar el Top 10 de libros que más lecturas tienen por parte de usuarios.");
                    System.out.println("2. Indicar el Top 20 de libros que más cantidad de lecturas tienen.");
                    System.out.println("3. Indicar el Top 10 de usuarios que realizaron mayor cantidad" +
                            " de evaluaciones a libros y ordenarlo por rating promedio descendente");
                    System.out.println("4. Indicar el Top 5 de los idiomas asociados a libros que han tenido más reservas");
                    System.out.println("5. Indicar el Top 20 de autores que más publicaciones han hecho por año");
                    System.out.println("6. Salir");
                    respuesta = scanner.nextInt();
                    switch (respuesta) {
                        case 1: //consulta 1
                            break;
                        case 2: //consulta 1
                            break;
                        case 3: //consulta 1
                            break;
                        case 4: //consulta 1
                            break;
                        case 5: //consulta 1
                            break;
                        case 6:
                            run = false;
                    }
                    break;
                case 3:
                    run = false;

            }
        }
    }
}
