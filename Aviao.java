
import java.util.Random;

public class Aviao {
    private int Combustivel;
    private String Marca;
    private boolean Emergencia;
    private int ID;
    private boolean Acao; // true = aterrissagem  false = decolagem
    private int TempoEspera;
    private Boolean Passageiro;
    private int NumPassageiros;




    public Aviao() {
    }

    public Aviao(int combustivel, String marca, int id, boolean passageiro) {
        this.Combustivel = combustivel;
        this.Marca = marca;
        this.ID = id;
        if (id % 2 != 0) {
            if (combustivel <= 3) {
                this.Emergencia = true;
            } else {
                this.Emergencia = false;
            }
        } else {
            this.Emergencia = false;
        }
        this.TempoEspera = 0;

        this.Passageiro = passageiro;

        this.NumPassageiros = 0;

    }



    public int getCombustivel() {
        return Combustivel;
    }

    public void setCombustivel(int combustivel) {
        Combustivel = combustivel;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public boolean isEmergencia() {
        return Emergencia;
    }

    public void setEmergencia(boolean emergencia) {
        Emergencia = emergencia;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isAcao() {
        return Acao;
    }

    public void setAcao(boolean acao) {
        Acao = acao;
    }

    public int getTempoEspera() {
        return TempoEspera;
    }

    public void setTempoEspera(int tempoEspera) {
        TempoEspera = tempoEspera;
    }

    public Boolean getPassageiro() {
        return Passageiro;
    }

    public void setPassageiro(Boolean passageiro) {
        Passageiro = passageiro;
    }

    public int getNumPassageiros() {
        return NumPassageiros;
    }

    public void setNumPassageiros(int numPassageiros) {
        NumPassageiros = numPassageiros;
    }

    public void RandomIdImpar() {
        Random rand = new Random();
        int num;
        num = rand.nextInt(60);
        if (num % 2 != 0) {
            setID(num);
        }
    }

    public void RandomIdPar() {
        Random rand = new Random();
        int num;
        num = rand.nextInt(60);
        if (num % 2 == 0) {
            setID(num);
        }
    }

    public void RandomGas() {
        Random rand = new Random();
        setCombustivel(rand.nextInt(1, 15));
    }

    public void ImprimirInformacoes() {
        System.out.println("\tAVIAO");
        if (!Acao) { //Decolagem
            System.out.println("Companhia: " + Marca);
            System.out.println("ID: " + ID);
            System.out.println("Combustivel do aviao: " +Combustivel);
            System.out.println("Passageiro passando mal? "+Passageiro);
        } else {
            System.out.println("Companhia: " + Marca);
            System.out.println("ID: " + ID);
            System.out.println("Combustivel: "+Combustivel);
            System.out.println("Passageiro passando mal? "+Passageiro);

        }
    }


}
