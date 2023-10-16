package Entities.Cuenta.ValueObject;

import Rule.StringNotNullOrEmptyRule;
import core.BussinessRuleValidateExeption;
import core.ValueObject;

public class Monto extends ValueObject {
    double monto;

    public Monto(double monto) {
        this.validateMontoNotCero(monto);
        this.monto = monto;
    }

    public Monto(String monto) throws BussinessRuleValidateExeption {
        CheckRule(new StringNotNullOrEmptyRule(monto));
        double doubleMonto;
        try {
            doubleMonto = Double.parseDouble(monto);

            this.validateMontoNotCero(doubleMonto);

            this.monto = doubleMonto;
        } catch (NumberFormatException e) {
            System.out.println("No se puede convertir a double. La cadena no es un número válido.");
        }
    }

    public void sumar(double monto) {
        validateMontoNotCero(monto);
        this.monto += monto;
    }

    public void restar(double monto) {
        validateMontoNotCero(monto);
        this.monto -= monto;
    }

    public double getMonto() {
        return monto;
    }

    private void validateMontoNotCero(double monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
    }

}
