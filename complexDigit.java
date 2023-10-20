import java.util.logging.Level;
import java.util.logging.Logger;

interface ComplexOperation {
    ComplexNumber calculate(ComplexNumber a, ComplexNumber b);
}

class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }
}

class Addition implements ComplexOperation {
    @Override
    public ComplexNumber calculate(ComplexNumber a, ComplexNumber b) {
        double real = a.getReal() + b.getReal();
        double imaginary = a.getImaginary() + b.getImaginary();
        return new ComplexNumber(real, imaginary);
    }
}

class Multiplication implements ComplexOperation {
    @Override
    public ComplexNumber calculate(ComplexNumber a, ComplexNumber b) {
        double real = a.getReal() * b.getReal() - a.getImaginary() * b.getImaginary();
        double imaginary = a.getReal() * b.getImaginary() + a.getImaginary() * b.getReal();
        return new ComplexNumber(real, imaginary);
    }
}

class Division implements ComplexOperation {
    @Override
    public ComplexNumber calculate(ComplexNumber a, ComplexNumber b) {
        double denominator = b.getReal() * b.getReal() + b.getImaginary() * b.getImaginary();
        double real = (a.getReal() * b.getReal() + a.getImaginary() * b.getImaginary()) / denominator;
        double imaginary = (a.getImaginary() * b.getReal() - a.getReal() * b.getImaginary()) / denominator;
        return new ComplexNumber(real, imaginary);
    }
}

class ComplexCalculator {
    private static final Logger logger = Logger.getLogger(ComplexCalculator.class.getName());

    private ComplexOperation operation;

    public ComplexCalculator(ComplexOperation operation) {
        this.operation = operation;
    }

    public ComplexNumber calculate(ComplexNumber a, ComplexNumber b) {
        ComplexNumber result = operation.calculate(a, b);
        logger.log(Level.INFO, "Calculation result: {0} + {1}i", new Object[]{result.getReal(), result.getImaginary()});
        return result;
    }

    public static void main(String[] args) {
        ComplexNumber a = new ComplexNumber(2, 3);
        ComplexNumber b = new ComplexNumber(4, 5);

        ComplexCalculator calculator = new ComplexCalculator(new Addition());
        ComplexNumber result1 = calculator.calculate(a, b);
        System.out.println("Сумма: " + result1.getReal() + " + " + result1.getImaginary() + "i");

        calculator = new ComplexCalculator(new Multiplication());
        ComplexNumber result2 = calculator.calculate(a, b);
        System.out.println("Умножение: " + result2.getReal() + " + " + result2.getImaginary() + "i");

        calculator = new ComplexCalculator(new Division());
        ComplexNumber result3 = calculator.calculate(a, b);
        System.out.println("Деление: " + result3.getReal() + " + " + result3.getImaginary() + "i");
    }
}
