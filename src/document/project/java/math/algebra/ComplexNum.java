package document.project.java.math.algebra;

import java.util.Objects;

/**
 * A Class made to store, represent and handle complex numbers.
 *
 * @author Hilson Alexandre Wojcikiewicz Junior
 */
public final class ComplexNum {

    /**
     * the part of the complex number that belongs to the real numbers set.
     */
    private double a;

    /**
     * the real number that represents the imaginary part of the complex number.
     */
    private double b;

    /**
     * the imaginary unity exponent.
     */
    private int exp;

    /**
     * The most complete constructor to store a complex number.
     * @param a the real part of the complex number;
     * @param b the imaginary part of the complex number;
     * @param exp the imaginary unity exponent.
     */
    public ComplexNum(double a, double b, int exp){
        this.exp = exp;
        this.a = a;
        this.b = b;
        simplify();
    }

    /**
     * The constructor for pure imaginary numbers
     * (the real part (a) == 0).
     * @param b the imaginary number;
     * @param exp the imaginary unity exponent.
     */
    public ComplexNum(double b, int exp){
        this(0, b, exp);
    }

    /**
     * Initializes a Complex Number with the imaginary
     * unity exponent == 1.
     * @param a the real part of the complex number;
     * @param b the imaginary part of the complex number.
     */
    public ComplexNum(double a, double b){
        this(a, b, 1);
    }

    /**
     * Initializes a pure imaginary number
     * with the exponent == 1.
     * @param b the imaginary number.
     */
    public ComplexNum (double b){
        this(b, 1);
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
        return new ComplexNum(a + comp.getReal(), b + comp.getImaginary(), exp);
    }

    /**
     * Subtract a complex number from another.
     * @param comp the subtrahend complex number.
     * @return the complex number resulting from the subtract.
     */
    public ComplexNum minus (ComplexNum comp){
        int exp = (this.exp > comp.getExp()) ? this.exp : comp.getExp();
        return new ComplexNum(a - comp.getReal(), b - comp.getImaginary(), exp);
    }

    /**
     * Multiply a complex number by another.
     * @param comp the complex number to be multiplied .
     * @return the complex number resulting from the multiplication.
     */
    public ComplexNum times (ComplexNum comp){
        ComplexNum[] comps = new ComplexNum[3];
        comps[0] = new ComplexNum(a * comp.getReal(), a * comp.getImaginary(), comp.getExp());
        comps[1] = new ComplexNum(b * comp.getReal(), exp);
        comps[2] = new ComplexNum(b * comp.getImaginary(), exp + comp.getExp());
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
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    /**
     * Computes the norm of a complex number.
     * @return the norm of the complex number.
     */
    public double norm (){
        return Math.pow(a, 2) + Math.pow(b, 2);
    }

    /**
     * Computes the conjugate of a complex number.
     * @return the conjugate of the complex number.
     */
    public ComplexNum conjugate (){
        return new ComplexNum(a, -b, exp);
    }

    /**
     * Computes the opposite of a complex number.
     * @return the opposite of the complex number.
     */
    public ComplexNum opposite (){
        return new ComplexNum(-a, -b, exp);
    }

    /**
     * Get the part of the complex number that belongs
     * to the real numbers set.
     * @return the real part of the complex number.
     */
    public double getReal () {
        return a;
    }

    /**
     * Get the part of the complex number that belongs
     * to the imaginary numbers set.
     * @return the imaginary part of the complex number.
     */
    public double getImaginary(){
        return b;
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
        return b == 0;
    }

    @Override
    public String toString() {
        String coef = ((b < 0 ) ? " " + b : " +" + b);
        return (exp == 0) ? String.valueOf(a) : a + coef + " i";
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
        return Objects.hash(a, b, exp);
    }

    /**
     * Simplifies a complex number to its very reduced form.
     */
    private void simplify(){
        b = (Math.abs(exp % 4) < 2) ? b : -b;
        exp = (b == 0) ? 0 : exp % 2;
        if(exp == 0){
            a = a+b;
            b = 0;
        }
        if( exp < 0){
            b = -b;
            exp = - exp;
        }
    }
}
