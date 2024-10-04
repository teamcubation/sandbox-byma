package springApp.java.com.example.gestoralyc.utils;

import springApp.java.com.example.gestoralyc.dto.AccionDTO;
import springApp.java.com.example.gestoralyc.dto.BonoDTO;

public class GeneradorCurl {

    public static String generarCurlAccion(AccionDTO accionDTO) {
        StringBuilder curl = new StringBuilder();

        curl.append("curl -X POST ");
        curl.append("\"http://localhost:5000/api/acciones/\" ");
        curl.append("-H \"Content-Type: application/json\" ");
        curl.append("-d '{");

        curl.append("\"nombre\":\"").append(accionDTO.getNombre()).append("\", ");
        curl.append("\"precio\":").append(accionDTO.getPrecio()).append(", ");
        curl.append("\"dividendo\":").append(accionDTO.getDividendo());
        curl.append("}'");

        return curl.toString();
    }

    public static String generarCurlBono(BonoDTO bonoDTO) {
        StringBuilder curlCommand = new StringBuilder("curl -X POST ");
        curlCommand.append("http://localhost:5000/api/bonos/ ")
                .append("-H \"Content-Type: application/json\" ")
                .append("-d '{");

        curlCommand.append("\"nombre\":\"").append(bonoDTO.getNombre()).append("\",");
        curlCommand.append("\"precio\":").append(bonoDTO.getPrecio()).append(",");
        curlCommand.append("\"tasaInteres\":").append(bonoDTO.getTasaInteres());

        curlCommand.append("}'");
        return curlCommand.toString();
    }
}


