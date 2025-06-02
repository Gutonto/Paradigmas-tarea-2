
TaxonomiaApp - Configuración y Ejecución

1. Descripción del Proyecto:
   TaxonomiaApp es una aplicación de escritorio en Java 
   que permite al usuario seleccionar una asignatura (Programación, Geografía, Conocimientos Generales) 
   y responder un cuestionario de 10 preguntas de Verdadero/Falso, asociadas a diferentes niveles de 
   la Taxonomía de Bloom. Al finalizar la prueba, se muestran los resultados detallados (1–10, Correcto/Incorrecto), 
   el total de respuestas correctas/malas, el porcentaje global y el porcentaje desglosado por cada nivel de taxonomía.

2. Estructura de Paquetes:
   - backend/
       • TaxonomiaApp.java         → Punto de entrada (main), abre el SplashScreenForm.
       • LectorPreguntas.java      → Lee las preguntas desde el CSV (data/preguntas.csv).
       • PreguntaVF.java           → Modelo de pregunta V/F con campos: asignatura, taxonomía, enunciado, respuesta, ponderación.
   - frontend/
       • SplashScreenForm.java     → JDialog de bienvenida sin bordes (3 segundos).
       • PrincipalForm.java        → JFrame principal con opciones de asignatura.
       • PruebaForm.java           → JFrame de la prueba: muestra preguntas, radios, botones numéricos, navegación.
       • ResultadoForm.java        → JFrame de resultados: tabla de detalle 1–10, totales y porcentajes.
   - data/
       • preguntas.csv            → Archivo de texto plano (UTF-8) que contiene las preguntas.

3. Supuestos:
   - El usuario debe tener instalado JDK 17 como minimo
   - El archivo de preguntas está guardado en UTF-8.
   - El directorio de trabajo al ejecutar la aplicación es la raíz del proyecto, por lo que la ruta relativa “data/preguntas.csv”.

4. Instrucciones de Ejecución:
   1. Abre NetBeans IDE 17 y carga el proyecto "TaxonomiaApp".
   2. Asegúrate de que bajo **Files** exista la carpeta “data” con el archivo `preguntas.csv`.
   3. Verifica que las clases en **Source Packages** estén organizadas en `backend` y `frontend`.
   4. Compila y ejecuta el proyecto con **Run Project (F6)**.
      - Aparecerá el **SplashScreen** por 3 segundos.
      - Luego se mostrará la ventana principal (**PrincipalForm**).
   5. En **PrincipalForm**, selecciona una asignatura con los radio buttons y haz clic en “Iniciar Prueba”.
   6. En **PruebaForm**, responde cada pregunta usando “Verdadero” o “Falso”. Puedes navegar con:
      - Botones “Anterior” / “Siguiente” (se guardan respuestas solo si seleccionas algo).
      - Botones numerados (1–10): saltan directamente a la pregunta indicada.
      - Al responder, el botón numérico se pinta de verde para indicar que ya fue respondido.
      - El JLabel indica “Pregunta X de 10” para saber la posición.
   7. Cuando termines la prueba, presiona “Terminar Prueba”.
      - Se abrirá **ResultadoForm**, donde se muestra:
         • Detalle de cada pregunta con “Correcto” / “Incorrecto”.
         • QLabel “Buenas: N”, “Malas: M”, “Puntaje: P%” (porcentaje global).
         • En una segunda tabla: “Nivel | % Correctas” desglosado por nivel de taxonomía.
   8. Para volver al menú principal, cierra **ResultadoForm**. Si quieres ejecutar nuevamente, reinicia el proyecto.

5. Formato del archivo `data/preguntas.csv`:
   - **Formato**: CSV, codificado en UTF-8, separador por comas, las cadenas de texto que contengan comas deben estar entre comillas dobles.
   - **Columnas** (en este orden):
     1. **asignatura** (String): “Programacion” / “Geografia” / “Conocimientos Generales”
     2. **taxonomia** (String): uno de los niveles: “Conocimiento”, “Comprensión”, “Aplicación”, “Análisis”, “Síntesis”, “Evaluación”
     3. **enunciado** (String): texto completo de la pregunta V/F, entre comillas si contiene comas.
     4. **respuesta** (String): “V” para Verdadero o “F” para Falso.
     5. **ponderacion** (int): entero que indica la dificultad/nivel de peso (1–5).
   - **Ejemplo** (primas tres filas, incluyendo encabezado):
     asignatura,taxonomia,enunciado,respuesta,ponderacion
     Programacion,Conocimiento,"Java es un lenguaje orientado a objetos",V,1
     Programacion,Comprensión,"Un ArrayList extiende la interfaz List",V,2
     Geografia,Aplicación,"Para calcular la distancia entre dos coordenadas se usa Haversine",V,3

6. Personalización y Mantenimiento:
   - Para **modificar** preguntas, edita `preguntas.csv` y reinicia la aplicación, no es necesario cambiar código.
   - Para **agregar** nuevas asignaturas o niveles, ajusta el CSV y actualiza la lógica en `PruebaForm` y `LectorPreguntas` si fuera necesario.
   - Para garantizar la integridad, no renombres la carpeta `data` ni los nombres de columnas del CSV.


