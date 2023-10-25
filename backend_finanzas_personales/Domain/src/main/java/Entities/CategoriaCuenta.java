package Entities;

import java.util.Random;
import java.util.UUID;

import core.AggregateRoot;

import java.awt.Color;

public class CategoriaCuenta extends AggregateRoot<UUID> {
    public String nombre;
    private UUID keyUser;
    private String color;

    public CategoriaCuenta(String nombre, UUID keyUser, String color) {
        this.key = UUID.randomUUID();
        this.nombre = nombre;
        this.keyUser = keyUser;
        this.color = color;
    }

    public void addCategoriaCuentaUser(UUID keyUser) {
        this.keyUser = keyUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UUID getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(UUID keyUser) {
        this.keyUser = keyUser;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static String generarColorPastelAleatorio() {
        Random rand = new Random();
        // Generar componentes RGB con valores entre 150 y 255 para obtener colores pasteles
        int red = 150 + rand.nextInt(106);
        int green = 150 + rand.nextInt(106);
        int blue = 150 + rand.nextInt(106);

        // Crear un objeto Color con los componentes RGB generados
        Color color = new Color(red, green, blue);

        // Convertir el objeto Color a formato hexadecimal
        String colorHex = String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());

        return colorHex;
    }

}
