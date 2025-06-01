import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

public class Pistas {
    private ArrayList<Aviao> FilaAviao1 = new ArrayList<>();
    private ArrayList<Aviao> FilaAviao2 = new ArrayList<>();
    private ArrayList<Aviao> Decolagem = new ArrayList<>();
    private boolean Clima;

    private int Minutos;
    private int Minutos2;
    private int Minutos3;
    private int quantidade1;
    private int quantidade2;
    private int quantidade3;

    private int TotalPousosEmergencia = 0;

    public int tamanhoTotalAterrisagem = 0;


    public int tamanhoTotalDecolagem = 0;

    public Pistas() {
    }

    public ArrayList<Aviao> getFilaAviao1() {
        return FilaAviao1;
    }

    public void setFilaAviao1(ArrayList<Aviao> filaAviao1) {
        FilaAviao1 = filaAviao1;
    }

    public ArrayList<Aviao> getFilaAviao2() {
        return FilaAviao2;
    }

    public void setFilaAviao2(ArrayList<Aviao> filaAviao2) {
        FilaAviao2 = filaAviao2;
    }

    public ArrayList<Aviao> getDecolagem() {
        return Decolagem;
    }

    public void setDecolagem(ArrayList<Aviao> decolagem) {
        Decolagem = decolagem;
    }

    public boolean isClima() {
        return Clima;
    }

    public void setClima(boolean clima) {
        Clima = clima;
    }

    public int getMinutos() {
        return Minutos;
    }

    public void setMinutos(int minutos) {
        Minutos = minutos;
    }

    public int getMinutos2() {
        return Minutos2;
    }

    public void setMinutos2(int minutos2) {
        Minutos2 = minutos2;
    }

    public int getMinutos3() {
        return Minutos3;
    }

    public void setMinutos3(int minutos3) {
        Minutos3 = minutos3;
    }

    public int getQuantidade1() {
        return quantidade1;
    }

    public void setQuantidade1(int quantidade1) {
        this.quantidade1 = quantidade1;
    }

    public int getQuantidade2() {
        return quantidade2;
    }

    public void setQuantidade2(int quantidade2) {
        this.quantidade2 = quantidade2;
    }

    public int getQuantidade3() {
        return quantidade3;
    }

    public void setQuantidade3(int quantidade3) {
        this.quantidade3 = quantidade3;
    }

    public int getTamanhoTotalAterrisagem() {
        return tamanhoTotalAterrisagem;
    }

    public void setTamanhoTotalAterrisagem(int tamanhoTotalAterrisagem) {
        this.tamanhoTotalAterrisagem = tamanhoTotalAterrisagem;
    }

    public int getTamanhoTotalDecolagem() {
        return tamanhoTotalDecolagem;
    }

    public void setTamanhoTotalDecolagem(int tamanhoTotalDecolagem) {
        this.tamanhoTotalDecolagem = tamanhoTotalDecolagem;
    }

    public int getTotalPousosEmergencia() {
        return TotalPousosEmergencia;
    }

    public void setTotalPousosEmergencia(int totalPousosEmergencia) {
        TotalPousosEmergencia = totalPousosEmergencia;
    }


    public void organizar() {
        //fila 1
        ArrayList<Aviao> ajuda = new ArrayList<>();
        if (Clima) {
            Iterator<Aviao> remover = FilaAviao1.iterator();
            while (remover.hasNext()) {
                Aviao j = remover.next();
                if (j.isEmergencia() || j.getPassageiro()) {
                    ajuda.add(j);
                    remover.remove();
                }
            }
        } else {
            Iterator<Aviao> remover = FilaAviao1.iterator();
            while (remover.hasNext()) {
                Aviao j = remover.next();
                if (j.isEmergencia()) {
                    ajuda.add(j);
                    remover.remove();
                }
            }

        }
        //ordena em ordem decrescente

        int n = ajuda.size();
        for (int i = 0; i < n - 1; i++) {
            int indiceMenorCombustivel = i;

            // Encontra o índice do menor combustível
            for (int j = i + 1; j < n; j++) {
                if (ajuda.get(j).getCombustivel() > ajuda.get(indiceMenorCombustivel).getCombustivel()) {
                    indiceMenorCombustivel = j;
                }
            }

            // Troca os aviões de posição com menor combustível
            Aviao aviaoTemp = ajuda.get(indiceMenorCombustivel);
            ajuda.set(indiceMenorCombustivel, ajuda.get(i));
            ajuda.set(i, aviaoTemp);
        }

        for (Aviao k : ajuda) {
            FilaAviao1.add(0, k);
        }

        ajuda.clear();

        //fila 2

        if (Clima) {
            Iterator<Aviao> remover = FilaAviao2.iterator();
            while (remover.hasNext()) {
                Aviao j = remover.next();
                if (j.isEmergencia() || j.getPassageiro()) {
                    ajuda.add(j);
                    remover.remove();
                }
            }
        } else {
            Iterator<Aviao> remover = FilaAviao2.iterator();
            while (remover.hasNext()) {
                Aviao j = remover.next();
                if (j.isEmergencia()) {
                    ajuda.add(j);
                    remover.remove();
                }
            }
        }
        int m = ajuda.size();

        //ordena em forma decrescente
        for (int i = 0; i < m - 1; i++) {
            int indiceMenorCombustivel = i;

            // Encontra o índice do menor combustível
            for (int j = i + 1; j < m; j++) {
                if (ajuda.get(j).getCombustivel() > ajuda.get(indiceMenorCombustivel).getCombustivel()) {
                    indiceMenorCombustivel = j;
                }
            }

            // Troca os aviões de posição com menor combustível
            Aviao aviaoTemp = ajuda.get(indiceMenorCombustivel);
            ajuda.set(indiceMenorCombustivel, ajuda.get(i));
            ajuda.set(i, aviaoTemp);
        }

        for (Aviao k : ajuda) {
            FilaAviao2.add(0, k);
        }

    }

    public ArrayList<Aviao> situacaoCritica() {
        int pos = 1;
        int cont = 0;
        ArrayList<Aviao> emergencia = new ArrayList<>();
        for(Aviao x : FilaAviao1){
            if(x.getCombustivel() == 1){
                cont ++;
            }
        }

        for(Aviao x: FilaAviao2){
            if(x.getCombustivel() == 1){
                cont++;
            }
        }

        if(cont >= 3){
            System.out.println("3 ou mais avioes estão com 1 de combustivel. AEROPORTO EM ESTADO CRITICO!");
        }

        //passa todos aviões em situacao critica para outra pista
        if (Clima) {
            Iterator<Aviao> remover = FilaAviao1.iterator();
            while (remover.hasNext()) {
                Aviao j = remover.next();
                if (j.isEmergencia() || j.getPassageiro()) {
                    if (j.getCombustivel() < pos) {
                        emergencia.add(j);
                        remover.remove();
                        pos--;
                    }
                }
                pos++;
            }
            pos = 1;
            Iterator<Aviao> remover2 = FilaAviao2.iterator();
            while (remover2.hasNext()) {
                Aviao j = remover2.next();
                if (j.isEmergencia() || j.getPassageiro()) {
                    if (j.getCombustivel() < pos) {
                        emergencia.add(j);
                        remover2.remove();
                        pos--;
                    }
                }
                pos++;
            }
        } else {
            Iterator<Aviao> remover = FilaAviao1.iterator();
            while (remover.hasNext()) {
                Aviao j = remover.next();
                if (j.isEmergencia()) {
                    if (j.getCombustivel() < pos) {
                        emergencia.add(j);
                        remover.remove();
                        pos--;
                    }
                }
                pos++;
            }
            pos = 1;
            Iterator<Aviao> remover2 = FilaAviao2.iterator();
            while (remover2.hasNext()) {
                Aviao j = remover2.next();
                if (j.isEmergencia()) {
                    if (j.getCombustivel() < pos) {
                        emergencia.add(j);
                        remover2.remove();
                        pos--;
                    }
                }
                pos++;
            }
        }
        return emergencia;
    }

    public void AdicionarAviaoAterrissagem(Aviao A1) {
        if (A1.getID() % 2 == 0) {
            System.out.println("O aviao nao pode ser adicionado a pista de aterrissagem");
            return;
        }
        A1.ImprimirInformacoes();
        if (FilaAviao1.size() <= FilaAviao2.size()) {
            if (A1.isEmergencia()) {
                if (FilaAviao1.isEmpty()) {
                    FilaAviao1.add(A1);
                    System.out.println("Aviao adicionado a fila de aterrissagem 1!");
                    System.out.println("-----------------------------------------");
                    tamanhoTotalAterrisagem++;
                } else {
                    int i = 0;
                    for (Aviao x : FilaAviao1) {
                        if (A1.getCombustivel() < x.getCombustivel()) {
                            FilaAviao1.add(i, A1);
                            System.out.println("Aviao adicionado a fila de aterrissagem 1!");
                            System.out.println("-----------------------------------------");
                            tamanhoTotalAterrisagem++;
                            i = -1;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (i != -1) {
                        FilaAviao1.add(A1);
                        tamanhoTotalAterrisagem++;
                        System.out.println("Aviao adicionado a fila de aterrissagem 1!");
                        System.out.println("-----------------------------------------");

                    }
                }
            } else {
                FilaAviao1.add(A1);
                tamanhoTotalAterrisagem++;
                System.out.println("Aviao adicionado a fila de aterrissagem 1!");
                System.out.println("-----------------------------------------");

            }
        } else {
            if (A1.isEmergencia()) {
                if (FilaAviao2.isEmpty()) {
                    FilaAviao2.add(A1);
                    tamanhoTotalAterrisagem++;
                    System.out.println("Aviao adicionado a fila de aterrissagem 2!");
                    System.out.println("-----------------------------------------");

                } else {
                    int i = 0;

                    for (Aviao x : FilaAviao2) {
                        if (A1.getCombustivel() < x.getCombustivel()) {
                            FilaAviao2.add(i, A1);
                            tamanhoTotalAterrisagem++;
                            System.out.println("Aviao adicionado a fila de aterrissagem 2!");
                            System.out.println("-----------------------------------------");

                            i = -1;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (i != -1) {
                        FilaAviao2.add(A1);
                        tamanhoTotalAterrisagem++;
                        System.out.println("Aviao adicionado a fila de aterrissagem 2!");
                        System.out.println("-----------------------------------------");

                    }
                }
            } else {
                FilaAviao2.add(A1);
                tamanhoTotalAterrisagem++;
                System.out.println("Aviao adicionado a fila de aterrissagem 2!");
                System.out.println("-----------------------------------------");

            }
        }
    }

    public void DiminuirCombustivel() {
        Iterator<Aviao> remover = FilaAviao1.iterator();
        Iterator<Aviao> remover1 = FilaAviao2.iterator();
        while (remover.hasNext()) {
            Aviao i = remover.next();
            i.setCombustivel(i.getCombustivel() - 1);
            if (i.getCombustivel() == 0) {
                System.out.println("-----------------------------------------------");
                System.out.println("O seguinte aviao caiu por falta de combustivel!");
                i.ImprimirInformacoes();
                System.out.println("-----------------------------------------------\n\n");
                remover.remove();
            }
        }

        while (remover1.hasNext()) {
            Aviao i = remover1.next();
            i.setCombustivel(i.getCombustivel() - 1);
            if (i.getCombustivel() == 0) {
                System.out.println("-----------------------------------------------");
                System.out.println("O seguinte aviao caiu por falta de combustivel!");
                i.ImprimirInformacoes();
                System.out.println("-----------------------------------------------\n\n");
                remover1.remove();
            }
        }
    }

    public void AdicionarAviaoDecolagem(Aviao A1) {
        if (A1.getID() % 2 != 0) {
            System.out.println("O aviao nao pode ser adicionado na pista de Decolagem");
            return;
        }
        A1.ImprimirInformacoes();
        int temp = 0;
        temp = tamanhoTotalAterrisagem + tamanhoTotalDecolagem;
        System.out.println("Tempo de espera estimado em: " + temp);
        Decolagem.add(A1);
        tamanhoTotalDecolagem++;
        System.out.println("Aviao adicionado a pista de decolagem!");
        System.out.println();
    }

    public int TamanhoFilasAterrissagem() {
        return FilaAviao1.size() + FilaAviao2.size();
    }

    public int TamanhoFilaDecolagem() {
        return Decolagem.size();
    }


    public Boolean VerificarEmergenciaFila1() {
        int i = 1;
        for (Aviao aux1 : FilaAviao1) {
            if (aux1.getCombustivel() < i) {
                return true;
            }


            i++;
        }
        return false;
    }

    public Boolean VerificarEmergenciaFila2() {
        int i = 1;
        for (Aviao aux1 : FilaAviao2) {
            if (aux1.getCombustivel() < i) {
                return true;
            }
            i++;
        }
        return false;
    }

    public void VerificarAviaoCaiu1() {
        Iterator<Aviao> remover = FilaAviao1.iterator();
        while (remover.hasNext()) {
            Aviao i = remover.next();
            if (i.getCombustivel() == 0) {
                System.out.println("-----------------------------------------------");
                System.out.println("O seguinte aviao caiu por falta de combustivel!");
                i.ImprimirInformacoes();
                System.out.println("-----------------------------------------------\n\n");
                remover.remove();
            }
        }
    }

    public void VerificarAviaoCaiu2() {
        Iterator<Aviao> remover1 = FilaAviao2.iterator();
        while (remover1.hasNext()) {
            Aviao i = remover1.next();
            if (i.getCombustivel() == 0) {
                System.out.println("------------------------------------------------");
                System.out.println("O seguinte aviao caiu por falta de combustivel!");
                i.ImprimirInformacoes();
                System.out.println("--------------------------------------------------\n\n");
                remover1.remove();
            }
        }

    }

    public int TempoEsperaAterrissagem() {
        int x = 1, y = 1;
        int contador = 0;
        if (VerificarEmergenciaFila1()) {
            for (Aviao aux : FilaAviao1) {
                if (aux.isEmergencia()) {
                    contador++;
                }
            }
        }

        if (VerificarEmergenciaFila2()) {
            for (Aviao aux : FilaAviao2) {
                if (aux.isEmergencia()) {
                    contador++;
                }
            }
        }
        int temp = 1;
        int tam = Decolagem.size();

        if (tam < FilaAviao1.size()) {
            temp++;
        }
        if (tam < FilaAviao2.size()) {
            temp++;
        }
        temp += contador;
        return temp;

    }

    public int tempoEsperaDecolagem() {
        int temp = 1;
        int tam = Decolagem.size();
        if (tam > FilaAviao1.size()) {
            temp++;
        }
        if (tam > FilaAviao2.size()) {
            temp++;
        }

        return temp;
    }

    public float MediaPistaAterrissagem1() {
        return (float) quantidade1 / TempoEsperaAterrissagem();
    }

    public float MediaPistaAterrissagem2() {
        return (float) quantidade2 / TempoEsperaAterrissagem();
    }

    public float MediaPistaDecolagem() {
        return (float) quantidade3 / tempoEsperaDecolagem();

    }

    public float MediaGlobal() {
        return (float) (quantidade1 + quantidade2 + quantidade3) / (MediaPistaDecolagem() + MediaPistaAterrissagem2() + MediaPistaAterrissagem1());
    }

    public Aviao PassageiroEmergencia1() {
        Aviao A1 = new Aviao();
        for (Aviao aux : FilaAviao1) {
            if (aux.getPassageiro()) {
                A1 = aux;
                FilaAviao1.remove(aux);
                System.out.println("o aviao que possuia um passageiro passando mal foi transferido para a fila prioritaria!");
                A1.ImprimirInformacoes();
            }
        }
        return A1;
    }

    public Aviao PassageiroEmergencia2() {
        Aviao A1 = new Aviao();
        for (Aviao aux : FilaAviao2) {
            if (aux.getPassageiro()) {
                A1 = aux;
                FilaAviao2.remove(aux);
                System.out.println("O aviao que possuia um passageiro passando mal foi transferido para a fila prioritaria!");
                A1.ImprimirInformacoes();
            }
        }
        return A1;
    }

    public void PousoDecolagem() {

        if(FilaAviao1.isEmpty()){
            System.out.println("A fila de aterrissagem 1 esta vazia!");
            System.out.println("------------------------------------");
        }

        if(FilaAviao2.isEmpty()){
            System.out.println("A fila de aterrissagem 2 esta vazia!");
            System.out.println("------------------------------------");
        }

        if(Decolagem.isEmpty()){
            System.out.println("A fila de decolagem esta vazia!");
            System.out.println("-------------------------------");
        }



        if (FilaAviao1.isEmpty() && FilaAviao2.isEmpty() && Decolagem.isEmpty()) {
            System.out.println("As filas estao vazias");
        } else if (FilaAviao1.isEmpty() && FilaAviao2.isEmpty()) {
            System.out.println("O aviao decolou!");
            Decolagem.get(0).ImprimirInformacoes();
            Decolagem.remove(0);
            quantidade3++;
            System.out.println("-----------------------------");
        } else if (FilaAviao1.isEmpty() && Decolagem.isEmpty()) {
            System.out.println("O avião da fila 2 pousou!");
            FilaAviao2.get(0).ImprimirInformacoes();
            if(FilaAviao2.get(0).getCombustivel() <= 3){
                TotalPousosEmergencia++;
            }
            FilaAviao2.remove(0);
            quantidade2++;
            System.out.println("---------------------------");
        } else if (FilaAviao2.isEmpty() && Decolagem.isEmpty()) {
            System.out.println("o aviao da fila 1 pousou!");
            FilaAviao1.get(0).ImprimirInformacoes();
            if(FilaAviao1.get(0).getCombustivel() <= 3){
                TotalPousosEmergencia++;
            }
            System.out.println("------------------------------");
            FilaAviao1.remove(0);
            quantidade1++;
        } else if (!FilaAviao1.isEmpty() || !FilaAviao2.isEmpty() || !Decolagem.isEmpty()) {
            if (VerificarEmergenciaFila1() || VerificarEmergenciaFila2()) {
                if (FilaAviao1.get(0).getCombustivel() < FilaAviao2.get(0).getCombustivel()) {
                    System.out.println("O aviao de emergencia da fila 1 pousou");
                    FilaAviao1.get(0).ImprimirInformacoes();
                    System.out.println("--------------------------------------");
                    FilaAviao1.remove(0);
                    TotalPousosEmergencia++;
                    quantidade1++;
                } else if (FilaAviao1.get(0).getCombustivel() > FilaAviao2.get(0).getCombustivel()) {
                    System.out.println("O aviao de emergencia da fila 2 pousou");
                    FilaAviao2.get(0).ImprimirInformacoes();
                    System.out.println("--------------------------------------");
                    FilaAviao2.remove(0);
                    TotalPousosEmergencia++;
                    quantidade2++;
                } else if (FilaAviao2.get(0).getCombustivel() == FilaAviao1.get(0).getCombustivel()) {
                    if (FilaAviao1.size() >= FilaAviao2.size()) {
                        System.out.println("O aviao de emergencia da fila 1 pousou");
                        FilaAviao1.get(0).ImprimirInformacoes();
                        System.out.println("--------------------------------------");
                        FilaAviao1.remove(0);
                        TotalPousosEmergencia++;
                        quantidade1++;
                    } else {
                        System.out.println("O aviao de emergencia da fila 2 pousou");
                        FilaAviao2.get(0).ImprimirInformacoes();
                        System.out.println("--------------------------------------");
                        FilaAviao2.remove(0);
                        TotalPousosEmergencia++;
                        quantidade2++;
                    }
                }
            } else if (FilaAviao1.size() >= FilaAviao2.size() && FilaAviao1.size() >= Decolagem.size()) {
                System.out.println("O aviao da fila 1 pousou");
                FilaAviao1.get(0).ImprimirInformacoes();
                if(FilaAviao1.get(0).getCombustivel() <= 3){
                    TotalPousosEmergencia++;
                }
                System.out.println("-----------------------------");
                FilaAviao1.remove(0);
                quantidade1++;
            } else if (FilaAviao2.size() >= FilaAviao1.size() && FilaAviao2.size() >= Decolagem.size()) {
                System.out.println("O aviao da fila 2 pousou");
                FilaAviao2.get(0).ImprimirInformacoes();
                if(FilaAviao2.get(0).getCombustivel() <= 3){
                    TotalPousosEmergencia++;
                }
                System.out.println("-----------------------------");
                FilaAviao2.remove(0);
                quantidade2++;
            } else {
                System.out.println("O aviao decolou!");
                Decolagem.get(0).ImprimirInformacoes();
                System.out.println("-----------------------------");
                Decolagem.remove(0);
                quantidade3++;
            }
        }

    }


    public void AvioesFila1() {
        System.out.println("LISTA DE AVIÕES DA FILA 1");
        if (FilaAviao1.isEmpty()) {
            System.out.println("A fila de aterrissagem 1 esta vazia!");
            System.out.println("------------------------------------");
            return;
        }
        for (Aviao aux : FilaAviao1) {
            aux.ImprimirInformacoes();
            System.out.println("--------------------------------");
        }
    }

    public void AvioesFila2() {
        System.out.println("LISTA DE AVIÕES DA FILA 2");
        if (FilaAviao2.isEmpty()) {
            System.out.println("A fila de aterrissagem 2 esta vazia!");
            System.out.println("-------------------------------------");
            return;
        }
        for (Aviao aux : FilaAviao2) {
            aux.ImprimirInformacoes();
            System.out.println("---------------------------------");
        }
    }

    public void ImprimirAviaoDecolagem() {
        System.out.println("LISTA AVIOES DECOLAGEM");
        {
            if (Decolagem.isEmpty()) {
                System.out.println("A fila de decolagem está vazia");
                System.out.println("--------------------------------");
                return;
            }
            for (Aviao aux : Decolagem) {
                aux.ImprimirInformacoes();
                System.out.println("-------------------------------");
            }
        }
    }

    public void InformacoesPeriodicas() {

        int tam1;


        tam1 = TamanhoFilasAterrissagem();


        System.out.println("--------------------AEROPORTO-------------------------");
        System.out.println("Informacoes sobre as pistas 1 e 2");
        System.out.println("Numero de avioes nas filas de aterrissagem: " + tam1);
        System.out.println("Media de avioes por tempo  que aterrissaram da fila 1: " + MediaPistaAterrissagem1());
        System.out.println("Media de avioes por tempo que aterrissaram da fila 2: " + MediaPistaAterrissagem2());
        System.out.println("Media de avioes por tempo que decolaram: " + MediaPistaDecolagem());
        System.out.println("Media global de pousos e decolagens por tempo: " + MediaGlobal());
        System.out.println("Total de pousos de emergergência das filas 1 e 2: " + TotalPousosEmergencia);
        System.out.println("-------------------------------------------------------");

    }


}
