package springApp.java.com.example.gestoralyc.utils;

import springApp.java.com.example.gestoralyc.dto.InstrumentoDTO;

public class GeneradorCurl {

    public static String generarCurlComando(InstrumentoDTO instrumentoDTO) {
        String tipo = instrumentoDTO.getTipo().toString();
        String nombre = instrumentoDTO.getNombre();
        double precio = instrumentoDTO.getPrecio();
        Double tasaInteres = instrumentoDTO.getTasaInteres();
        Double dividendo = instrumentoDTO.getDividendo();

        StringBuilder curlCommand = new StringBuilder();
        curlCommand.append("curl --location 'http://localhost:5000/api/instrumentos/' \\").append("\n");
        curlCommand.append("--header 'Content-Type: application/json' \\").append("\n");
        curlCommand.append("--data '{").append("\n");
        curlCommand.append("    \"tipo\": \"").append(tipo).append("\",").append("\n");
        curlCommand.append("    \"nombre\": \"").append(nombre).append("\",").append("\n");
        curlCommand.append("    \"precio\": ").append(precio).append(",").append("\n");

        // Añadir sólo los atributos que correspondan
        if (tasaInteres != null) {
            curlCommand.append("    \"tasaInteres\": ").append(tasaInteres).append("\n");
        }
        if (dividendo != null) {
            curlCommand.append("    \"dividendo\": ").append(dividendo).append("\n");
        }

        curlCommand.append("}'");
        return curlCommand.toString();
    }



}
