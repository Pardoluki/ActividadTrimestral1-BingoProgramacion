# 🎱 Simulación de Bingo en Java

Este proyecto es una simulación de un **juego de bingo** en **Java** que permite a los jugadores generar un cartón de bingo, realizar una apuesta y jugar hasta conseguir **Bingo**. Se implementa un menú iterativo para continuar jugando.

## 📌 Características

- Generación automática de un cartón de **2 filas x 5 columnas** con números únicos del **1 al 99**.
- Representación visual del cartón en la consola usando **bordes y separaciones Unicode**.
- Posibilidad de ingresar una **apuesta** (entre 5 € y 500 €) y un número de intentos estimado.
- Sorteo aleatorio de números hasta que se complete el cartón.
- Comparación del número de intentos con la apuesta para determinar si el jugador **gana** o **pierde**.
- Menú interactivo para jugar nuevamente o salir del juego.

## 📂 Estructura del Código

El código se encuentra en el archivo `Entrada.java` y consta de los siguientes métodos principales:

1. **`main()`**  
   - Controla el flujo del juego con un bucle `do-while`.
   - Llama a los métodos de generación del cartón, ingreso de apuesta y proceso de bingo.
   
2. **`cartonBingo()`**  
   - Genera el cartón con **números únicos aleatorios**.
   - Representa visualmente el cartón en consola con **Unicode y colores ANSI**.

3. **`apuestaJugador()`**  
   - Solicita al usuario ingresar su **apuesta** y **número de intentos** esperados.
   - Valida que los valores ingresados estén en los rangos permitidos.

4. **`proceso()`**  
   - Realiza el **sorteo de números** aleatorios.
   - Verifica si los números sorteados coinciden con los del cartón.
   - Cuenta la cantidad de intentos necesarios hasta obtener **Bingo**.
   - Determina si el jugador **gana** o **pierde** según la apuesta.

## 🎨 Representación del Cartón
El cartón de bingo generado tendrá el siguiente formato:
|  12  |  45  |  78  |   5  |  99  |
|------|------|------|------|------|
|  23  |  67  |  81  |  34  |   9  |

Los números sorteados aparecerán en azul si coinciden con los del cartón.

## 🎮 Controles
Durante el juego, el usuario deberá:
   - Introducir la apuesta (mínimo 5 €, máximo 500 €).
   - Elegir el número de intentos esperados (entre 10 y 99).
   - Esperar al sorteo de números aleatorios.
   - Ver si gana o pierde en función de la cantidad de intentos requeridos.
   - Decidir si jugar de nuevo:
        - Enviar `1` para una partida nueva.
        - Enviar `2` para terminar la ejecución del código.

### 📢 Proyecto creado por Lucas Pardo. 
