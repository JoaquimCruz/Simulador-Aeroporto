import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



public class Main {

    public static String RandomizadorTempo() {
        String Clima1 = "Nublado";
        String Clima2 = "Ensolarado";
        String Clima3 = "Chuvoso";

        Random Random = new Random();
        int x = Random.nextInt(1, 3);
        switch (x) {
            case 1:
                return Clima1;
            case 2:
                return Clima2;
            case 3:
                return Clima3;
        }

        return null;
    }

    public static ArrayList<Aviao> organizador(ArrayList<Aviao> emergencia1, ArrayList<Aviao> emergencia2) {

        ArrayList<Aviao> emergencia3 = new ArrayList<>();

        emergencia3.addAll(emergencia1);
        emergencia3.addAll(emergencia2);

        int n = emergencia3.size();

        for (int i = 0; i < n - 1; i++) {
            int indiceMenorCombustivel = i;

            // Encontra o índice do menor combustível
            for (int j = i + 1; j < n; j++) {
                if (emergencia3.get(j).getCombustivel() > emergencia3.get(indiceMenorCombustivel).getCombustivel()) {
                    indiceMenorCombustivel = j;
                }
            }

            // Troca os aviões de posição com menor combustível
            Aviao aviaoTemp = emergencia3.get(indiceMenorCombustivel);
            emergencia3.set(indiceMenorCombustivel, emergencia3.get(i));
            emergencia3.set(i, aviaoTemp);
        }

        return emergencia3;
    }

    public static Aviao aleatorizarAviao() {
        Random rd = new Random();
        Aviao a1;
        int combustivel = rd.nextInt(1,15);
        int id = rd.nextInt(1,100);
        String marca = "teste";
        String Marca = "GOL, AZUL, AMERICAN AIRLINES";
        String[] Partes = Marca.split(",");
        String Marca1 = Partes[0];
        String Marca2 = Partes[1];
        String Marca3 = Partes[2];
        int x = rd.nextInt(1,3);
        switch (x){
            case 1:
                marca = Marca1;
                break;
            case 2:
                marca = Marca2;
                break;
            case 3:
                marca = Marca3;
                break;
        }
        boolean passageiropassandomal;
        passageiropassandomal = rd.nextBoolean();
        a1 = new Aviao(combustivel,marca, id, passageiropassandomal);
        return a1;
    }

    public static boolean converterParaBooleano(String valor) {
        if (valor.equals("S")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Aviao A2 = new Aviao();
        Pista3 PistaEmergencia = new Pista3();
        Scanner scanner = new Scanner(System.in);
        int opc = 0, cont = 0;
        Pistas pista1 = new Pistas();
        Pistas pista2 = new Pistas();
        Pista3 pista3 = new Pista3();
        try {
            do {
                System.out.println("-------------MENU AEROPORTO-------------");
                System.out.println("Digite a opcao desejada: ");
                System.out.println("1-Execucao por arquivo\n2-Execucao por numero aleatorio\n0-Sair do programa");
                System.out.println("Opcao desejada: \n--------------------------------------\n");
                opc = scanner.nextInt();

                switch (opc) {
                    case 0:
                        System.out.println("Saindo do programa...");
                        break;
                    case 1:
                        int contvezes = 0;
                        Scanner aux = new Scanner(new FileReader("C:/Users/joaqu/OneDrive/Documentos/Trabalho 2/aviao.txt")).useDelimiter("\n");
                        while (aux.hasNext()) {
                            contvezes++;
                            String linha = aux.nextLine();
                            String[] partes = linha.split(":");
                            String marca = partes[0];
                            int ID = Integer.parseInt(partes[1]);
                            int Combustivel = Integer.parseInt(partes[2]);
                            boolean passageiroemergencia = converterParaBooleano(partes[3]);

                            Aviao A1 = new Aviao(Combustivel,marca,ID,passageiroemergencia);

                            if (ID % 2 == 0) {
                                if (pista1.tamanhoTotalDecolagem <= pista2.tamanhoTotalDecolagem) {
                                    if (pista3.tamanhoDecolagem <= pista1.tamanhoTotalDecolagem) {
                                        System.out.println("Pista 3:");
                                        pista3.AdcionarDecolagem(A1);
                                    } else {
                                        System.out.println("Pista 1:");
                                        pista1.AdicionarAviaoDecolagem(A1);
                                    }
                                } else {
                                    if (pista3.tamanhoDecolagem <= pista2.tamanhoTotalDecolagem) {
                                        System.out.println("Pista 3:");
                                        pista3.AdcionarDecolagem(A1);
                                    } else {
                                        System.out.println("Pista 2:");
                                        pista2.AdicionarAviaoDecolagem(A1);
                                    }
                                }
                            } else {
                                if (pista1.tamanhoTotalAterrisagem <= pista2.tamanhoTotalAterrisagem) {
                                    System.out.println("Pista 1:");
                                    pista1.AdicionarAviaoAterrissagem(A1);
                                } else {
                                    System.out.println("Pista 2:");
                                    pista2.AdicionarAviaoAterrissagem(A1);
                                }
                            }

                            if (contvezes % 3 == 0) {
                                String l;

                                String clima = RandomizadorTempo();

                                if (Objects.equals(clima, "Nublado")) {
                                    System.out.println("O clima está Nublado!!\n");
                                    pista1.setClima(false);
                                    pista2.setClima(false);
                                    pista3.setClima(false);
                                } else if (Objects.equals(clima, "Ensolarado")) {
                                    System.out.println("O clima está Ensolarado!!\n");
                                    pista1.setClima(false);
                                    pista2.setClima(false);
                                    pista3.setClima(false);
                                } else if (Objects.equals(clima, "Chuvoso")) {
                                    System.out.println("O clima está Chuvoso, Estado de Emergencia Ativado!!\n");
                                    pista1.setClima(true);
                                    pista2.setClima(true);
                                    pista3.setClima(true);
                                }



                                pista1.organizar();
                                pista2.organizar();


                                ArrayList<Aviao> emergencia1 = pista1.situacaoCritica();

                                ArrayList<Aviao> emergencia2 = pista2.situacaoCritica();

                                ArrayList<Aviao> emergencia3 = organizador(emergencia1, emergencia2);

                                pista3.AdicionarAterrisagem(emergencia3);

                                pista3.organizar();

                                System.out.println("Pista 1:");
                                pista1.AvioesFila1();
                                pista1.AvioesFila2();
                                pista1.ImprimirAviaoDecolagem();
                                System.out.println("\n\n\n");


                                System.out.println("Pista 2:");
                                pista2.AvioesFila1();
                                pista2.AvioesFila2();
                                pista2.ImprimirAviaoDecolagem();
                                System.out.println("\n\n\n");


                                System.out.println("Pista 3:");
                                pista3.ImprimirAvioesFIlaEmergencia();
                                pista3.ImprimirAviaoDecolagem();
                                System.out.println("\n\n\n");


                                System.out.println("Pista 1:");
                                pista1.PousoDecolagem();
                                System.out.println("\n\n\n");


                                System.out.println("Pista 2:");
                                pista2.PousoDecolagem();
                                System.out.println("\n\n\n");


                                System.out.println("Pista 3:");
                                pista3.PousoDecolagem();
                                System.out.println("\n\n\n");

                                pista1.DiminuirCombustivel();
                                pista2.DiminuirCombustivel();
                                pista3.DiminuirCombustivel();

                                pista1.VerificarAviaoCaiu1();
                                pista1.VerificarAviaoCaiu2();
                                pista2.VerificarAviaoCaiu1();
                                pista2.VerificarAviaoCaiu2();
                                pista3.VerificarAviaoCaiu1();

                                //INFORMAÇÕES PERIÓDICAS
                                System.out.println("PISTA 1:");
                                pista1.InformacoesPeriodicas();
                                System.out.println("\n\n");

                                System.out.println("PISTA 2:");
                                pista2.InformacoesPeriodicas();
                                System.out.println("\n\n");


                                System.out.println("PISTA 3:");
                                pista3.InformacoesPeriodicas1();
                                System.out.println("\n\n");

                                Scanner scan= new Scanner(System.in);
                                System.out.println("Pressione Enter para continuar...");
                                l = scan.nextLine();
                            }
                            cont++;
                        }

                        break;

                    case 2:
                        int opcao = 0;
                        int contvezes2 = 0;
                        do {
                            contvezes2++;
                            int min = 0;

                            Aviao aviao;

                            aviao = aleatorizarAviao();

                            if (aviao.getID() % 2 == 0) {
                                if (pista1.tamanhoTotalDecolagem <= pista2.tamanhoTotalDecolagem) {
                                    if (pista3.tamanhoDecolagem <= pista1.tamanhoTotalDecolagem) {
                                        System.out.println("Pista 3: ");
                                        pista3.AdcionarDecolagem(aviao);
                                    } else {
                                        System.out.println("Pista 1: ");
                                        pista1.AdicionarAviaoDecolagem(aviao);
                                    }
                                } else {
                                    if (pista3.tamanhoDecolagem <= pista2.tamanhoTotalDecolagem) {
                                        System.out.println("Pista 3: ");
                                        pista3.AdcionarDecolagem(aviao);
                                    } else {
                                        System.out.println("Pista 2: ");
                                        pista2.AdicionarAviaoDecolagem(aviao);
                                    }
                                }
                            } else {
                                if (pista1.tamanhoTotalAterrisagem <= pista2.tamanhoTotalAterrisagem) {
                                    System.out.println("Pista 1: ");
                                    pista1.AdicionarAviaoAterrissagem(aviao);
                                } else {
                                    System.out.println("Pista 1: ");
                                    pista2.AdicionarAviaoAterrissagem(aviao);
                                }
                            }

                            if (contvezes2 % 3 == 0) {
                                min++;
                                String clima = RandomizadorTempo();

                                if (Objects.equals(clima, "Nublado")) {
                                    System.out.println("O clima está Nublado!!");
                                    pista1.setClima(false);
                                    pista2.setClima(false);
                                    pista3.setClima(false);
                                } else if (Objects.equals(clima, "Ensolarado")) {
                                    System.out.println("O clima está Ensolarado!!");
                                    pista1.setClima(false);
                                    pista2.setClima(false);
                                    pista3.setClima(false);
                                } else if (Objects.equals(clima, "Chuvoso")) {
                                    System.out.println("O clima está Chuvoso, Estado de Emergencia Ativado!!");
                                    pista1.setClima(true);
                                    pista2.setClima(true);
                                    pista3.setClima(true);
                                }


                                pista1.organizar();
                                pista2.organizar();


                                ArrayList<Aviao> emergencia1 = pista1.situacaoCritica();

                                ArrayList<Aviao> emergencia2 = pista2.situacaoCritica();

                                ArrayList<Aviao> emergencia3 = organizador(emergencia1, emergencia2);

                                pista3.AdicionarAterrisagem(emergencia3);

                                pista3.organizar();

                                System.out.println("Pista 1:");
                                pista1.AvioesFila1();
                                pista1.AvioesFila2();
                                pista1.ImprimirAviaoDecolagem();
                                System.out.println("\n\n\n");


                                System.out.println("Pista 2:");
                                pista2.AvioesFila1();
                                pista2.AvioesFila2();
                                pista2.ImprimirAviaoDecolagem();
                                System.out.println("\n\n\n");


                                System.out.println("Pista 3:");
                                pista3.ImprimirAvioesFIlaEmergencia();
                                pista3.ImprimirAviaoDecolagem();
                                System.out.println("\n\n\n");


                                System.out.println("Pista 1:");
                                pista1.PousoDecolagem();
                                System.out.println("\n\n\n");


                                System.out.println("Pista 2:");
                                pista2.PousoDecolagem();
                                System.out.println("\n\n\n");


                                System.out.println("Pista 3:");
                                pista3.PousoDecolagem();
                                System.out.println("\n\n\n");



                                pista1.DiminuirCombustivel();


                                pista2.DiminuirCombustivel();


                                pista3.DiminuirCombustivel();



                                pista1.VerificarAviaoCaiu1();
                                pista1.VerificarAviaoCaiu2();


                                pista2.VerificarAviaoCaiu1();
                                pista2.VerificarAviaoCaiu2();

                                pista3.VerificarAviaoCaiu1();

                                //INFORMAÇÕES PERIÓDICAS
                                System.out.println("PISTA 1:");
                                pista1.InformacoesPeriodicas();
                                System.out.println("\n\n");

                                System.out.println("PISTA 2:");
                                pista2.InformacoesPeriodicas();
                                System.out.println("\n\n");


                                System.out.println("PISTA 3:");
                                pista3.InformacoesPeriodicas1();
                                System.out.println("\n\n");


                                System.out.println("Já se passaram " + min + " minutos");
                                System.out.println("Deseja gerar mais um minuto:1-Sim/2-Não");
                                opcao = scanner.nextInt();
                            }


                        } while (opcao != 2);
                        break;

                    default:
                        System.out.println("Digite uma opcao valida!");
                }

            } while (opc != 0);
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}

