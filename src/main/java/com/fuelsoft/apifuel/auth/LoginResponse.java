package com.fuelsoft.apifuel.auth;

//AQUI SE AGREGA TODOS LOS TIPOS DE DATOS QUE REGRESA LA RESPONSE 
public record LoginResponse(String token, String type, long expiresInMinutes,  String username, String rol) {

}
