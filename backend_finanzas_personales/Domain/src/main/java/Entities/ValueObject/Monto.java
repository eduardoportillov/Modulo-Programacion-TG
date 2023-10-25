package Entities.ValueObject;

import Rule.StringNotNullOrEmptyRule;
import core.BussinessRuleValidateExeption;
import core.ValueObject;

public class Monto extends ValueObject {
    private double monto;

    public Monto(double monto) {
        // this.validateMontoNotCero(monto);
        this.monto = monto;
    }

    public Monto(String monto) throws BussinessRuleValidateExeption {
        CheckRule(new StringNotNullOrEmptyRule(monto));
        double doubleMonto;
        try {
            doubleMonto = Double.parseDouble(monto);

            // @VALIDACIÓN_NO_MONTO_NEGATIVO
            // this.validateMontoNotCero(doubleMonto);

            this.monto = doubleMonto;
        } catch (NumberFormatException e) {
            System.out.println("No se puede convertir a double. La cadena no es un número válido.");
        }
    }

    public void sumar(double monto) {
        // @VALIDACIÓN_NO_MONTO_NEGATIVO
        // validateMontoNotCero(monto);
        this.monto += monto;
    }

    public void restar(double monto) {
        // @VALIDACIÓN_NO_MONTO_NEGATIVO
        // validateMontoNotCero(monto);
        this.monto -= monto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    // @VALIDACIÓN_NO_MONTO_NEGATIVO
    // private void validateMontoNotCero(double monto) {
    // if (monto < 0) {
    // throw new IllegalArgumentException("El monto no puede ser negativo");
    // }
    // }

}
