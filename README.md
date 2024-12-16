# 🎬 Juego de Adivinar la Película 🎥

¡Bienvenido al **Juego de Adivinar la Película**! Este proyecto es un juego interactivo por consola donde el usuario debe adivinar el título de una película letra a letra o intentando acertar el título completo.

---

## 📋 Descripción del Proyecto

Este proyecto es un juego de adivinanza basado en títulos de películas. El juego selecciona aleatoriamente un título de una lista de películas almacenadas en un archivo llamado `movies.txt`. Los jugadores pueden intentar adivinar letras o el título completo dentro de un número limitado de intentos.

Al finalizar el juego, si el jugador obtiene una puntuación alta, puede guardar su nombre en el **Ranking**. Este ranking se guarda en un archivo llamado `ranking.dat` para persistir la información entre ejecuciones.

---

## 🚀 Funcionalidades

1. **Selección Aleatoria de Películas**
    - El juego selecciona de forma aleatoria un título de la lista almacenada en el archivo `movies.txt`.

2. **Adivinar Letras**
    - Los jugadores pueden introducir letras una a una para intentar descubrir el título oculto.
    - El juego muestra qué letras son correctas y cuáles no.

3. **Adivinar el Título Completo**
    - Los jugadores pueden intentar adivinar el título completo en cualquier momento.

4. **Intentos Limitados**
    - El jugador dispone de un máximo de **10 intentos** para adivinar el título.

5. **Sistema de Puntuación**
    - La puntuación se calcula en base al número de intentos restantes y el progreso en el título adivinado.

6. **Revelar el Título**
    - Al finalizar el juego (ya sea por victoria o derrota), el título de la película se revela al jugador.

7. **Sistema de Ranking**
    - Si la puntuación del jugador es positiva, puede ingresar un **nickname** y su puntuación será guardada en un archivo `ranking.dat`.
    - El ranking muestra los jugadores y sus puntuaciones ordenadas de mayor a menor.

8. **Persistencia de Datos**
    - Los archivos `movies.txt` y `ranking.dat` permiten que el juego guarde y recupere información entre ejecuciones.

9. **Interfaz Simple y Amigable**
    - El juego proporciona una interfaz de consola fácil de entender, con mensajes claros y uso de emojis.
