package com.saints.movies.common;

public class ApiConstants {

    // Tags
    public static final String MOVIE_TAG = "Movie";
    public static final String MOVIE_DESCRIPTION = "Operaciones sobre películas";

    // Códigos de respuesta
    public static final String SUCCESSFUL = "200";
    public static final String SUCCESSFUL_MESSAGE = "Operación exitosa";
    public static final String CREATED = "201";
    public static final String CREATED_MESSAGE = "Recurso creado exitosamente";
    public static final String NO_CONTENT = "204";
    public static final String NO_CONTENT_MESSAGE = "Operación exitosa sin contenido";
    public static final String BAD_REQUEST = "400";
    public static final String BAD_REQUEST_MESSAGE = "Solicitud inválida";
    public static final String UNAUTHORIZED = "401";
    public static final String UNAUTHORIZED_MESSAGE = "Token JWT inválido o expirado";
    public static final String FORBIDDEN = "403";
    public static final String FORBIDDEN_MESSAGE = "No tiene permisos para realizar esta operación";
    public static final String RESOURCE_NOT_FOUND = "404";
    public static final String RESOURCE_NOT_FOUND_MESSAGE = "Recurso no encontrado";
    public static final String INTERNAL_ERROR = "500";
    public static final String INTERNAL_ERROR_MESSAGE = "Error interno del servidor";
}