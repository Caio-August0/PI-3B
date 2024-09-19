public class Registro {

    private int valor;
    private Registro proximo;

    public Registro(int valor) {
        this.valor = valor;
    }

    public Registro getProximo() {
        return proximo;
    }

    public void setProximo(Registro proximo) {
        this.proximo = proximo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

}
