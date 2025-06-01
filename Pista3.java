import java.util.ArrayList;
import java.util.Iterator;

public class Pista3 {
    private ArrayList<Aviao> FilaAviao3 = new ArrayList<Aviao>();
    private ArrayList<Aviao> FilaPrioridade = new ArrayList<Aviao>();
    public int tamanhoDecolagem = 0;
    private boolean Clima;

    private int quantidade1;
    private int quantidade2;

    public Pista3() {
    }

    public ArrayList<Aviao> getFilaPrioridade() {
        return FilaPrioridade;
    }

    public void setFilaPrioridade(ArrayList<Aviao> filaPrioridade) {
        FilaPrioridade = filaPrioridade;
    }

    public int getTamanhoDecolagem() {
        return tamanhoDecolagem;
    }

    public void setTamanhoDecolagem(int tamanhoDecolagem) {
        this.tamanhoDecolagem = tamanhoDecolagem;
    }

    public boolean isClima() {
        return Clima;
    }

    public void setClima(boolean clima) {
        Clima = clima;
    }


    public void DiminuirCombustivel() {
        Iterator<Aviao> remover = FilaPrioridade.iterator();
        while(remover.hasNext()){
            Aviao i = remover.next();
            i.setCombustivel(i.getCombustivel()-1);
            if(i.getCombustivel() == 0){
                System.out.println("O seguinte aviao caiu!!");
                i.ImprimirInformacoes();
                System.out.println("\n\n");
                remover.remove();
            }
        }

    }

    public ArrayList<Aviao> getFilaAviao3() {
        return FilaAviao3;
    }

    public void setFilaAviao3(ArrayList<Aviao> filaAviao3) {
        FilaAviao3 = filaAviao3;
    }

    public void VerificarAviaoCaiu1() {
        Iterator<Aviao> remover = FilaPrioridade.iterator();
        while(remover.hasNext()){
            Aviao i = remover.next();
            if(i.getCombustivel() == 0){
                System.out.println("Este aviao caiu!!");
                i.ImprimirInformacoes();
                remover.remove();
            }
        }
    }

    public void AdcionarDecolagem(Aviao A1) {
        if (A1.getID() % 2 != 0) {
            System.out.println("O aviao nao pode ser adicionado na pista de Decolagem");
            return;
        }
        A1.ImprimirInformacoes();
        int temp = 0;
        temp = tamanhoDecolagem;
        System.out.println("Tempo de espera estimado em: " + temp);
        FilaAviao3.add(A1);
        System.out.println("Aviao adicionado a pista de decolagem 1!");
        System.out.println("-------------------------------------");
    }

    public void AdicionarAterrisagem(ArrayList<Aviao> emergencia) {
        FilaPrioridade.addAll(emergencia);
    }

    public void ImprimirAvioesFIlaEmergencia() {
        System.out.println("Imprimindo lista de avioes na fila de emergencia");
        if(FilaPrioridade.isEmpty()){
            System.out.println("A fila de prioridade da pista 3 esta vazia!");
            System.out.println("--------------------------------------------");
            return;
        }
        for (Aviao aux : FilaPrioridade) {
            aux.ImprimirInformacoes();
            System.out.println("-----------------");
        }
    }

    public int TamanhoFiladeEmergencia() {
        return FilaAviao3.size();
    }

    /*public void RemoverAviaoFilaPrioridade() {
        Aviao aux = new Aviao();
        aux = FilaPrioridade.get(0);
        System.out.println("O primeiro aviao da fila de emergencia pousou com sucesso!");
        aux.ImprimirInformacoes();
        quantidade1++;
        FilaPrioridade.remove(0);
    }*/


    public void organizar() {
        //fila 1

        int n = FilaPrioridade.size();

        for (int i = 0; i < n - 1; i++) {
            int indiceMenorCombustivel = i;

            // Encontra o índice do menor combustível
            for (int j = i + 1; j < n; j++) {
                if (FilaPrioridade.get(j).getCombustivel() > FilaPrioridade.get(indiceMenorCombustivel).getCombustivel()) {
                    indiceMenorCombustivel = j;
                }
            }

            // Troca os aviões de posição com menor combustível
            Aviao aviaoTemp = FilaPrioridade.get(indiceMenorCombustivel);
            FilaPrioridade.set(indiceMenorCombustivel, FilaPrioridade.get(i));
            FilaPrioridade.set(i, aviaoTemp);
        }
    }

    public void PousoDecolagem() {
        if (FilaPrioridade.isEmpty() && FilaAviao3.isEmpty()) {
            System.out.println("As filas estao vazias");
            return;
        }
        if (!FilaPrioridade.isEmpty()) {
            System.out.println("O aviao pousou na pista de emergencia!");
            FilaPrioridade.get(0).ImprimirInformacoes();
            FilaPrioridade.remove(0);
            quantidade1++;
        } else {
            System.out.println("O aviao decolou na pista 3");
            FilaAviao3.get(0).ImprimirInformacoes();
            FilaAviao3.remove(0);
            quantidade2++;
        }
    }

    public int tempoEsperaAterrissagem(){
        int temp = FilaPrioridade.size();
        return temp;
    }

    public int tempoEsperaDecolagem(){
        int temp = 1;
        int tam = FilaAviao3.size();
        if(tam > FilaPrioridade.size()){
            temp ++;
        }
        return temp;
    }
    public float MediaGlobal(){
        return (float) (quantidade2 + quantidade1)/(tempoEsperaAterrissagem() + tempoEsperaDecolagem());

    }

    public float MediaDecolagem(){
        return (float)quantidade2/tempoEsperaDecolagem();
    }

    public float MediaAterrissagem(){
        if(quantidade1 == 0){
            System.out.println("Nao aterrissaram avioes!");
        }
            return (float) quantidade1/tempoEsperaAterrissagem();
    }

    public void InformacoesPeriodicas1(){
        System.out.println("-------------------AEROPORTO----------------------");
        System.out.println("Numero de  avioes na fila de emergencia: "+FilaPrioridade.size());
        System.out.println("Numero de avioes na fila de decolagem:  "+FilaAviao3.size());
        System.out.println("Media de avioes por tempo  que aterrissaram: " + MediaAterrissagem());
        System.out.println("Media de avioes por tempo que decolaram: " + MediaDecolagem());
        System.out.println("Media global de pousos e decolagens por tempo: " + MediaGlobal());
        System.out.println("Total de pousos de emergergência: " +quantidade1);
        System.out.println("-----------------------------------------------------");

    }

    public void ImprimirAviaoDecolagem(){
        System.out.println("LISTA AVIOES DECOLAGEM");{
            if(FilaAviao3.isEmpty()){
                System.out.println("A fila de decolagem da pista 3 está vazia!");
                System.out.println("-------------------------------------------");
                return;
            }
            for(Aviao aux : FilaAviao3){
                aux.ImprimirInformacoes();
                System.out.println("---------------------");
            }
        }
    }


}
