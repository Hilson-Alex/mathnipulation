package document.project.java.math.algebra;

import java.util.Objects;

/**
 * A Class made to store, represent and handle complex numbers
 * with basic operations.
 *
 * @author Hilson Alexandre Wojcikiewicz Junior
 * @version 1.0.1
 */
public final class ComplexNum {

    /**
     * the part of the complex number that belongs to the real numbers set.
     */
    private double real;

    /**
     * the real number that represents the imaginary part of the complex number.
     */
    private double imaginary;

    /**
     * the imaginary unity exponent.
     */
    private int exp;

    /**
     * The most complete constructor to store a complex number.
     * @param real the real part of the complex number;
     * @param imaginary the imaginary part of the complex number;
     * @param exp the imaginary unity exponent.
     */
    public ComplexNum(double real, double imaginary, int exp){
        this.exp = exp;
        this.real = real;
        this.imaginary = imaginary;
        simplify();
    }

    /**
     * The constructor for pure imaginary numbers
     * (the real part (a) == 0).
     * @param imaginary the imaginary number;
     * @param exp the imaginary unity exponent.
     */
    public ComplexNum(double imaginary, int exp){
        this(0, imaginary, exp);
    }

    /**
     * Initializes a Complex Number with the imaginary
     * unity exponent == 1.
     * @param real the real part of the complex number;
     * @param imaginary the imaginary part of the complex number.
     */
    public ComplexNum(double real, double imaginary){
        this(real, imaginary, 1);
    }

    /**
     * Initializes a pure imaginary number
     * with the exponent == 1.
     * @param imaginary the imaginary number.
     */
    public ComplexNum (double imaginary){
        this(imaginary, 1);
    }

    /**
     * Cast a real number to a complex number with
     * the imaginary part == 0.
     * @param real the number to be casted.
     * @return a complex number without the imaginary part.
     */
    public static ComplexNum asComplex (double real){
        return new ComplexNum(real, 0.0);
    }

    /**
     * Add a complex number to another.
     * @param comp the complex number to be added.
     * @return the complex number resulting from the sum.
     */
    public ComplexNum add (ComplexNum comp){
        int exp = (this.exp > comp.getExp()) ? this.exp : comp.getExp();
        return new ComplexNum(real + comp.getReal(), imaginary + comp.getImaginary(), exp);
    }

    /**
     * Subtract a complex number from another.
     * @param comp the subtrahend complex number.
     * @return the complex number resulting from the subtract.
     */
    public ComplexNum minus (ComplexNum comp){
        int exp = (this.exp > comp.getExp()) ? this.exp : comp.getExp();
        return new ComplexNum(real - comp.getReal(), imaginary - comp.getImaginary(), exp);
    }

    /**
     * Multiply a complex number by another.
     * @param comp the complex number to be multiplied .
     * @return the complex number resulting from the multiplication.
     */
    public ComplexNum times (ComplexNum comp){
        ComplexNum[] comps = new ComplexNum[3];
        comps[0] = new ComplexNum(real * comp.getReal(), real * comp.getImaginary(), comp.getExp());
        comps[1] = new ComplexNum(imaginary * comp.getReal(), exp);
        comps[2] = new ComplexNum(imaginary * comp.getImaginary(), exp + comp.getExp());
        return comps[0].add(comps[1].add(comps[2]));
    }

    /**
     * Divide a complex number by another.
     * @param comp the divider complex number.
     * @return the complex number resulting from the division.
     */
    public ComplexNum dividedBy (ComplexNum comp) {
        ComplexNum num = this;
        ComplexNum complexDenom = comp;
        if (!complexDenom.isReal()){
            num = num.times(complexDenom.conjugate());
            complexDenom = complexDenom.times(complexDenom.conjugate());
        }
        double denom = complexDenom.getReal();
        return new ComplexNum(num.getReal()/denom, num.getImaginary()/denom, num.getExp());
    }

    /**
     * Computes the modulus of a complex number.
     * @return the modulus of the complex number.
     */
    public double modulus (){
        return Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
    }

    /**
     * Computes the norm of a complex number.
     * @return the norm of the complex number.
     */
    public double norm (){
        return Math.pow(real, 2) + Math.pow(imaginary, 2);
    }

    /**
     * Computes the conjugate of a complex number.
     * @return the conjugate of the complex number.
     */
    public ComplexNum conjugate (){
        return new ComplexNum(real, -imaginary, exp);
    }

    /**
     * Computes the opposite of a complex number.
     * @return the opposite of the complex number.
     */
    public ComplexNum opposite (){
        return new ComplexNum(-real, -imaginary, exp);
    }

    /**
     * Get the part of the complex number that belongs
     * to the real numbers set.
     * @return the real part of the complex number.
     */
    public double getReal () {
        return real;
    }

    /**
     * Get the part of the complex number that belongs
     * to the imaginary numbers set.
     * @return the imaginary part of the complex number.
     */
    public double getImaginary(){
        return imaginary;
    }

    /**
     * Get the imaginary unity exponent.
     * @return the imaginary unity exponent.
     */
    public int getExp() {
        return exp;
    }

    /**
     * Verifies if the complex number exists in the
     * real numbers set.
     * @return true if the complex number is a real number
     */
    public boolean isReal () {
        return imaginary == 0;
    }

    @Override
    public String toString() {
        String coef = ((imaginary < 0 ) ? " " + imaginary : " +" + imaginary);
        return (exp == 0) ? String.valueOf(real) : real + coef + " i";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexNum that = (ComplexNum) o;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary, exp);
    }

    /**
     * Simplifies a complex number to its very reduced form.
     */
    private void simplify(){
        imaginary = (Math.abs(exp % 4) < 2) ? imaginary : -imaginary;
        exp = (imaginary == 0) ? 0 : exp % 2;
        if(exp == 0){
            real = real + imaginary;
            imaginary = 0;
        }
        if( exp < 0){
            imaginary = -imaginary;
            exp = - exp;
        }
    }
}
