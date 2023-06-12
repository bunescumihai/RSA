import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RSA {
    private String textPentruCriptare;
    private List<Long> textCriptat;
    private String textDecriptat = "";
    private long nrCaractereCriptate;
    private long timpCriptare;
    private long p;
    private long q;
    private long n;
    private long fiDeN;
    private long e;
    private long d;
    private List<Integer> nrPrime;
    private HashMap<Character, Long> caractere;
    private long caracterMaxim;

    public RSA(String textPentruCriptare){
        this.textPentruCriptare = textPentruCriptare;
        nrPrime = new ArrayList<>();
        generareNrPrime();

        this.textCriptat = new ArrayList<>();

        this.caractere = new HashMap<>();
        for(int i = 0; i < textPentruCriptare.length(); i++){
            char c = textPentruCriptare.charAt(i);
            this.caractere.put(c, (long) i);
            if(c > this.caracterMaxim)
                this.caracterMaxim = c;
        }

        this.nrCaractereCriptate = this.caractere.size();

        Random random = new Random();
        int indexP = random.nextInt(0,this.nrPrime.size());
        int indexQ = random.nextInt(0,this.nrPrime.size());
        this.p = nrPrime.get(indexP);
        this.q = nrPrime.get(indexQ);
        //cautare p si q
        while(p == q || p * q < caracterMaxim){
            indexQ = random.nextInt(0,nrPrime.size());
            this.q = nrPrime.get(indexQ);
        }

        this.n = this.q * this.p;
        this.fiDeN = (this.q - 1) * (this.p - 1);

        //cautare epsilon
        int indexE = random.nextInt(0,nrPrime.size());
        this.e = nrPrime.get(indexE);

        while (cmmdc(this.e, this.fiDeN) != 1 || this.e > fiDeN){
            indexE = random.nextInt(0,nrPrime.size());
            this.e = nrPrime.get(indexE);
        }

        //cautare d
        long fiDeNAuxiliar = this.fiDeN % this.e;
        long k = 1;
        for(long i = 2; i<= this.e; i++){
            if((1+i*fiDeNAuxiliar) % this.e == 0){
                k = i;
                break;
            }
        }

        this.d = (1 + k * fiDeN)/this.e;

        criptare();
        decriptare();
    }

    private void generareNrPrime(){
        for(int i = 31; i<= 1000; i+=2)
            if(estePrim(i))
                this.nrPrime.add(i);
    }

    private boolean estePrim(long nr){
        for(long i = 2; i*i<= nr; i++)
            if(nr % i == 0)
                return false;
        return true;
    }

    private long cmmdc(long a, long b){
        while(b != 0){
            a %=b;
            long aux = a;
            a = b;
            b = aux;
        }
        return a;
    }

    private void criptare(){
        for(Character c: this.caractere.keySet()){
            long numarCaracter = c;
            long exponent = this.e;
            long produs = 1;

            while (exponent != 1){
                if(exponent%2 == 1){
                    exponent--;
                    produs = (produs * numarCaracter) % this.n;
                }

                numarCaracter = numarCaracter % this.n;
                numarCaracter *= numarCaracter;
                exponent /=2;
            }

            this.caractere.put(c, (numarCaracter%this.n * produs%this.n) % this.n);
        }

        for(int i = 0; i < this.textPentruCriptare.length(); i++){
            this.textCriptat.add(this.caractere.get(this.textPentruCriptare.charAt(i)));
        }
    }

    private void decriptare(){

        HashMap<Long, Character> caractereCriptate = new HashMap<>();

        for(int i = 0; i < this.textCriptat.size(); i++){
            caractereCriptate.put(textCriptat.get(i), '0');
        }

        for(Long c: caractereCriptate.keySet()){
            long numarCaracter = c;
            long exponent = this.d;
            long produs = 1;
            while (exponent != 1){
                if(exponent%2 == 1){
                    exponent--;
                    produs = (produs * numarCaracter) % this.n;
                }
                numarCaracter =  numarCaracter % this.n;
                numarCaracter *= numarCaracter;
                exponent /=2;
            }
            caractereCriptate.put(c, (char) ((char) (numarCaracter%this.n * produs%this.n) % this.n));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < textCriptat.size(); i++){
            sb.append(caractereCriptate.get(textCriptat.get(i)));
        }

        this.textDecriptat = sb.toString();
    }


    public String getTextPentruCriptare() {
        return this.textPentruCriptare;
    }

    public List<Long> getTextCriptat() {
        return this.textCriptat;
    }

    public String getTextDecriptat() {
        return this.textDecriptat;
    }

    public long getNrCaractereCriptate() {
        return nrCaractereCriptate;
    }

    public long getTimpCriptare() {
        return timpCriptare;
    }

    public long getP() {
        return p;
    }

    public long getQ() {
        return q;
    }

    public long getFiDeN() {
        return fiDeN;
    }

    public long getN() {
        return n;
    }

    public long getD() {
        return d;
    }

    public long getE() {
        return e;
    }

    public List<Integer> getNrPrime() {
        return nrPrime;
    }

    public HashMap<Character, Long> getCaractere() {
        return caractere;
    }

    public long getCaracterMaxim() {
        return caracterMaxim;
    }
}
