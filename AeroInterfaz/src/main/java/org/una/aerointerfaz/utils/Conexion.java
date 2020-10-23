package org.una.aerointerfaz.utils;

import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public class Conexion {

    private Client client;
    private Invocation.Builder builder;
    private WebTarget webTarget;
    private Response response;
    private final String apiUrl = "http://localhost:8888/";

    public Conexion(String direccion) {
        this.client = ClientBuilder.newClient();
        this.webTarget = client.target(apiUrl + direccion);
        this.builder = webTarget.request(MediaType.APPLICATION_JSON);
        MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("Accept", "application/json");
        headers.add("Authorization", Autenticacion.getInstance().getToken());
        builder.headers(headers);
    }

    public Conexion(String direccion, String parametros, Map<String, Object> valores) {
        this.client = ClientBuilder.newClient();
        this.webTarget = client.target(apiUrl + direccion).path(parametros).resolveTemplates(valores);
        this.builder = webTarget.request(MediaType.APPLICATION_JSON);
        MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("Accept", "application/json");
        headers.add("Authorization", Autenticacion.getInstance().getToken());
        builder.headers(headers);
    }

    public void get() {
        response = builder.get();
    }

    public void post(Object clazz) {
        Entity<?> entity = Entity.entity(clazz, "application/json; charset=UTF-8");
        response = builder.post(entity);
    }

    public void put(Object clazz) {
        Entity<?> entity = Entity.entity(clazz, "application/json; charset=UTF-8");
        response = builder.put(entity);
    }

    public void delete() {
        response = builder.delete();
    }

    public int getStatus() {
        return response.getStatus();
    }

    public Boolean isError() {
        return getStatus() != HttpServletResponse.SC_OK && getStatus() != HttpServletResponse.SC_CREATED;
    }

    public String getError() {
        if (response.getStatus() != HttpServletResponse.SC_OK) {
            String mensaje;
            if (response.getMediaType().equals(MediaType.APPLICATION_JSON_TYPE)) {
                mensaje = response.readEntity(String.class);
            } else {
                mensaje = response.getStatusInfo().getReasonPhrase();
            }
            return mensaje;
        }
        return null;
    }
    
    public String getMensajeRespuesta(){
        if(response.getStatus() == HttpServletResponse.SC_NO_CONTENT)
            return "Sin registros";
        else if(response.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            return "Sin comunicacion con el servidor";
        else if(response.getStatus() == HttpServletResponse.SC_BAD_REQUEST)
            return "Credenciales invalidas";
        else if(response.getStatus() == HttpServletResponse.SC_UNAUTHORIZED)
            return "Solicitud rechazada";
        else if(response.getStatus() == HttpServletResponse.SC_CREATED)
            return "Éxito al crear";
        else if(response.getStatus() == HttpServletResponse.SC_NOT_FOUND)
            return "El registro no existe";
        else if(response.getStatus() == HttpServletResponse.SC_CONFLICT)
            return "Conflicto de datos";
        else
            return "Error :( ";
    }

    public Object readEntity(Class clazz) {
        return response.readEntity(clazz);
    }

    public Object readEntity(GenericType<?> genericType) {
        return response.readEntity(genericType);
    }
}
