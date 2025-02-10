// En este proyecto simularemos el funcionamiento de un bingo.

import java.util.Scanner;

public class Entrada {
    // Dimensiones del cartón (2x5)
    static int filasTotales = 2;
    static int columnasTotales = 5;

    // Variables para recorrer filas y columnas al generar el cartón
    static int filaActual, columnaActual;

    // Array bidimensional para representar los números en el cartón
    static int[][] numerosCartonBidimensional = new int[filasTotales][columnasTotales];

    // Array unidimensional que contiene los mismos números que el bidimensional
    // Facilita operaciones como búsquedas
    static int[] numerosCartonUnidimensional = new int[filasTotales * columnasTotales];

    // Array booleano que marca qué números ya fueron usados al crear el cartón
    // Índices del 1 al 99; el índice 0 no se usa
    static boolean[] numerosCartonUsados = new boolean[100];

    // Variables relacionadas con la apuesta
    static double apuesta; // Cantidad de dinero apostada
    static int intentosEsperados; // Número de intentos que el jugador cree necesitar para ganar
    static int opcion; // Opción escogida en el menú iterativo

    public static void main(String[] args) {
        // Jugamos al bingo hasta que el usuario desee salir
        do {
            cartonBingo(); // Genera el cartón con números únicos y lo imprime en consola
            apuestaJugador(); // Solicita al usuario ingresar su apuesta y los intentos esperados
            proceso(); // Ejecuta el sorteo y determina si el jugador gana o pierde
        } while (opcion != 2);
        // Mensaje de despedida
        System.out.println("\n\uD83D\uDE04 ¡Gracias por jugar! ¡Hasta la próxima!");
    }

    public static void cartonBingo() {
        /* Símbolos utilizados para dibujar el cartón en la consola.
           Se utilizan caracteres Unicode para generar bordes y separaciones elegantes.
           Los nombres de las variables están en inglés para mayor eficiencia al escribir */
        final String horizontalLine = "═";
        final String verticalLine = "║";
        final String topLeft = "╔";
        final String topRight = "╗";
        final String bottomLeft = "╚";
        final String bottomRight = "╝";
        final String crossTop = "╦";
        final String crossBottom = "╩";
        final String crossLeft = "╠";
        final String crossRight = "╣";
        final String crossMiddle = "╬";

        int numeroCartonAleatorio; // Almacena números generados aleatoriamente
        int indice = 0; // Índice para llenar el array unidimensional

        // Generación del cartón con números únicos
        for (filaActual = 0; filaActual < filasTotales; filaActual++) {
            for (columnaActual = 0; columnaActual < columnasTotales; columnaActual++) {
                do {
                    numeroCartonAleatorio = (int) (Math.random() * 99) + 1; // Número entre 1 y 99
                } while (numerosCartonUsados[numeroCartonAleatorio]); // Reintenta si el número ya se usó

                // Almacena el número en el array bidimensional y lo marca como usado
                numerosCartonBidimensional[filaActual][columnaActual] = numeroCartonAleatorio;
                numerosCartonUsados[numeroCartonAleatorio] = true;

                // Copia el número al array unidimensional para facilitar búsquedas
                numerosCartonUnidimensional[indice] = numeroCartonAleatorio;
                indice++; // Avanza al siguiente índice
            }
        }

        // Imprime el cartón con bordes y separación visual
        System.out.println("\n\uD83D\uDC4B ¡Bienvenido al bingo de Lucas Pardo! Vamos a jugar una partida.");
        System.out.println("Aquí tiene su cartón:");

        // Dibuja la línea superior del cartón
        System.out.print(topLeft);
        for (columnaActual = 0; columnaActual < columnasTotales; columnaActual++) {
            System.out.print(horizontalLine.repeat(5)); // Línea horizontal con ancho fijo
            if (columnaActual < columnasTotales - 1) {
                System.out.print(crossTop); // Separador entre columnas
            }
        }
        System.out.println(topRight);

        // Dibuja filas con números y separadores
        for (filaActual = 0; filaActual < filasTotales; filaActual++) {
            System.out.print(verticalLine);
            for (columnaActual = 0; columnaActual < columnasTotales; columnaActual++) {
                // Imprime cada número alineado con 3 espacios reservados. Coloreamos el número con códigos ANSI
                System.out.printf("\u001B[1m\u001B[34m" + "%3d" + "\u001B[0m  ", numerosCartonBidimensional[filaActual][columnaActual]);
                System.out.print(verticalLine); // Separador entre celdas
            }
            System.out.println();

            // Dibuja una línea intermedia entre filas
            if (filaActual == 0) {
                System.out.print(crossLeft);
                for (columnaActual = 0; columnaActual < columnasTotales - 1; columnaActual++) {
                    System.out.print(horizontalLine.repeat(5));
                    System.out.print(crossMiddle); // Cruces entre celdas
                }
                System.out.println(horizontalLine.repeat(5) + crossRight);
            }
        }

        // Dibuja la línea inferior del cartón
        System.out.print(bottomLeft);
        for (columnaActual = 0; columnaActual < columnasTotales; columnaActual++) {
            System.out.print(horizontalLine.repeat(5));
            if (columnaActual < columnasTotales - 1) {
                System.out.print(crossBottom); // Separador entre columnas
            }
        }
        System.out.println(bottomRight); // Cierra el cartón
    }

    public static void apuestaJugador() {
        Scanner scanner = new Scanner(System.in);

        // Solicita al jugador ingresar su apuesta y valida el rango permitido
        System.out.print("Introduzca su apuesta (5,00 € - 500,00 €): ");
        apuesta = scanner.nextDouble();
        while (apuesta < 5.00 || apuesta > 500.00) {
            System.out.print("El importe apostado no es correcto, inténtelo de nuevo: ");
            apuesta = scanner.nextDouble();
        }

        // Solicita al jugador ingresar el número de intentos esperados
        System.out.print("Introduzca el nº de intentos esperado en el que obtendrá bingo: ");
        intentosEsperados = scanner.nextInt();
        while (intentosEsperados < 10 || intentosEsperados > 99) {
            System.out.print("El nº de intentos debe estar comprendido entre 10 y 99. Inténtelo de nuevo: ");
            intentosEsperados = scanner.nextInt();
        }
    }

    public static void proceso() {
        int contadorAciertos = 0; // Números acertados en el cartón
        int intentosBingo = 0; // Contador de intentos realizados hasta cantar bingo
        boolean bingo = false; // Marca el estado del juego
        boolean[] numerosSorteadosUsados = new boolean[100]; // Números ya sorteados
        int numeroSorteado; // Número generado en cada sorteo

        System.out.println("\n\uD83D\uDC4C De acuerdo, comencemos. ¡Buena suerte!");
        System.out.println("\uD83C\uDFB2 Sorteando números...");

        // Bucle principal del sorteo
        while (!bingo) {
            // Genera un número aleatorio que no haya sido sorteado previamente
            do {
                numeroSorteado = (int) (Math.random() * 99) + 1;
            } while (numerosSorteadosUsados[numeroSorteado]);
            numerosSorteadosUsados[numeroSorteado] = true; // Marca el número como usado
            intentosBingo++; // Incrementa los intentos

            // Verifica si el número está en el cartón
            int i;
            for (i = 0; i < numerosCartonUnidimensional.length; i++) {
                if (numerosCartonUnidimensional[i] == numeroSorteado) {
                    // Imprime el número en azul si coincide con un número del cartón
                    System.out.printf("\u001B[1m\u001B[34m" + "%3d" + "\u001B[0m  ", numeroSorteado);
                    contadorAciertos++; // Incrementa los aciertos
                    break; // Termina el bucle al encontrar el número
                }
            }

            // Si el número generado no coincidió con ninguno en el cartón, entonces:
            if (i == numerosCartonUnidimensional.length) {
                System.out.printf("%3d  ", numeroSorteado); // Imprime el número normal
            }

            // Salto de línea cada 11 números impresos
            if (intentosBingo % 11 == 0) {
                System.out.println();
            }

            // Determina si el jugador ha completado el cartón
            if (contadorAciertos == numerosCartonUnidimensional.length) {
                bingo = true; // Marca el fin del juego
            }
        }

        // Imprime los resultados.
        System.out.println("\n\n\uD83C\uDF89\uD83C\uDF89\uD83C\uDF89 ¡Bingo! \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89\n");
        System.out.println("\uD83C\uDFAF El número de intentos necesarios fue de: " + intentosBingo);
        if (intentosBingo <= intentosEsperados) {
            System.out.println("\uD83C\uDF1F ¡Ha ganado la apuesta! ¡Felicidades!");
            System.out.printf("\uD83D\uDCB0 Aquí tiene su premio: %.2f €", apuesta * 10);
            System.out.println();
        } else {
            System.out.println("\uD83D\uDE1E Ha perdido su apuesta. ¡Qué pena!");
        }

        // Imprimimos por consola el menú iterativo para volver a jugar
        System.out.println("\n¿Desea volver a jugar?\nSí: Escriba '1'\nNo: Escriba '2'");
        System.out.print("Introduzca su opción: ");
        Scanner scanner = new Scanner(System.in);
        do {
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("\n\uD83D\uDE04¡De acuerdo, juguemos de nuevo!");
                    break;
                case 2:
                    break;
                default:
                    System.out.print("Por favor, introduzca una opción válida: ");
                    break;
            }
        } while (opcion != 1 && opcion != 2);
    }
}