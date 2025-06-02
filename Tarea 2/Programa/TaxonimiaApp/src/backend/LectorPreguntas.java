package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * LectorPreguntas: lee preguntas Verdadero/Falso desde un CSV.
 */
public class LectorPreguntas {

    public static List<PreguntaVF> leerPreguntasCSV(String rutaCSV) throws IOException {
        List<PreguntaVF> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String linea = br.readLine(); // salta cabecera
            while ((linea = br.readLine()) != null) {
                // Partimos en 5 trozos, pero solo en las comas que no estén dentro de comillas
                String[] p = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", 5);
                if (p.length < 5) continue;
                String asign     = p[0].trim();
                String tax       = p[1].trim();
                // Limpia comillas envolventes del enunciado
                String enunciado = p[2].trim();
                if (enunciado.startsWith("\"") && enunciado.endsWith("\"")) {
                    enunciado = enunciado.substring(1, enunciado.length() - 1);
                }
                boolean resp     = p[3].trim().equalsIgnoreCase("V");
                int ponderacion  = Integer.parseInt(p[4].trim());
                lista.add(new PreguntaVF(asign, tax, enunciado, resp, ponderacion));
            }
        }
        return lista;
    }
}

