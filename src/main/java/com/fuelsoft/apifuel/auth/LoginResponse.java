package com.fuelsoft.apifuel.auth;


public record LoginResponse(String token, String type, long expiresInMinutes) {

}
