package listsOfTasks;

import java.util.Scanner;

public class ListsOfTasks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);

		//----  MANDATORY CODE  ----\\
		int tamMax=100;
		int listasMax=10; // n�mero m�ximo de listas

		String [][] tarefa = new String[listasMax][tamMax];
		boolean [][] temPrazo = new boolean[listasMax][tamMax];
		boolean [][] foiFeita = new boolean[listasMax][tamMax];
		int [][][] data = new int[listasMax][tamMax][3];

		int [] nTarefas = new int[listasMax]; // n�mero de tarefas em cada lista
		String [] nomeListas = new String[listasMax]; // nomes das diversas listas

		int nListas=0;
		int listaAtiva=0;
		//----  MANDATORY CODE  ----\\


		// arrays tempor�rios que servem para guardar as tarefas copiadas
		String [] tarefaTemp = new String[tamMax];
		boolean [] temPrazoTemp = new boolean[tamMax];
		boolean [] foiFeitaTemp = new boolean[tamMax];
		int [][] dataTemp = new int[tamMax][3];

		int nTarefasTemp=0; // guarda o n�mero de tarefas copiadas 


		int [] idTarefas = new int [nTarefas[listaAtiva]]; // cria��o de um array que cont�m o n�mero de posi��es que o nTarefas tem

		int ultimaFeita = 0; // guarda a �ltima tarefa marcada como feita

		int [] atualizaMenuGerir = new int [2]; // array que atualiza as variaveis retornadas do menu Gerir 
		int [] atualizaMenuCopiarColar = new int [2]; // array que atualiza as variaveis retornadas do menu CopiarColar


		char opcaoMenu = 0;

		do {
			System.out.println("************************");
			System.out.println("*  (G)erir Lista       *");
			System.out.println("*  (V)isualizar Lista  *");
			System.out.println("*  (M)arcar            *");
			System.out.println("*  (E)ditar            *");
			System.out.println("*  (C)opiar/colar      *");
			System.out.println("*  (S)air              *");
			System.out.println("************************");

			System.out.print("Intoduza uma op��o: ");
			opcaoMenu = teclado.next().charAt(0);
			teclado.nextLine();

			switch(opcaoMenu) {
			case 'g':
			case 'G':
				// chamada do m�todo menuGerirLista e atualiza��o das vari�veis listaAtiva e nListas
				atualizaMenuGerir = menuGerirLista(listasMax, tarefa, temPrazo, foiFeita, data, nTarefas, nomeListas, nListas, listaAtiva, atualizaMenuGerir);
				listaAtiva = atualizaMenuGerir[0];
				nListas = atualizaMenuGerir[1];
				break;
			case 'v':
			case 'V':
				// chamada do m�todo menuVisualisarLista 
				menuVisualisarLista(tarefa[listaAtiva], temPrazo[listaAtiva], foiFeita[listaAtiva], data[listaAtiva], nTarefas[listaAtiva], nomeListas[listaAtiva], nListas, idTarefas);
				break;
			case 'm':
			case 'M':
				// chamada do m�todo menuMarcar e atualiza��o da variavel ultimaFeita
				ultimaFeita = menuMarcar(tarefa[listaAtiva], temPrazo[listaAtiva], foiFeita[listaAtiva], data[listaAtiva], nTarefas[listaAtiva], nomeListas[listaAtiva], nListas, idTarefas, ultimaFeita);
				break;
			case 'e':
			case 'E':
				// chamada do m�todo menuEditar e atualiza��o da vari�vel nTarefas[listaAtiva]
				nTarefas[listaAtiva] = menuEditar(tamMax, tarefa[listaAtiva], temPrazo[listaAtiva], foiFeita[listaAtiva], data[listaAtiva], nTarefas[listaAtiva], nomeListas[listaAtiva], nListas, idTarefas);
				break;
			case 'c':
			case 'C':
				// chamada do m�todo menuCopiarColar e atualiza��o das vari�veis listaAtiva e nTarefasTemp
				atualizaMenuCopiarColar = menuCopiarColar(tamMax, listasMax, tarefa, temPrazo, foiFeita, data, nTarefas, nomeListas, nListas, listaAtiva, tarefaTemp, temPrazoTemp, foiFeitaTemp, dataTemp, nTarefasTemp, atualizaMenuCopiarColar);
				listaAtiva = atualizaMenuCopiarColar[0];
				nTarefasTemp = atualizaMenuCopiarColar[1];
				break;	
			case 's':
			case 'S':
				System.out.println();
				System.out.println("Terminou o programa!");
				break;
			default:
				System.out.println("Op��o Inv�lida!");
			}
		} while(opcaoMenu != 's' && opcaoMenu != 'S');
	}





	// ----------------------------------- Menu (G)erir Lista ----------------------------------- \\

	static int [] menuGerirLista(int listasMax, String [][] tarefa, boolean [][] temPrazo, boolean [][] foiFeita, int [][][] data, int [] nTarefas, String [] nomeListas, int nListas, int listaAtiva, int [] atualizaMenuGerir) {
		Scanner teclado = new Scanner(System.in);
		int [] atualizaOpcaoApagar = new int [2];	// array que atualiza as variaveis retornadas do metodo apagar lista 
		char opcaoGerir = 0;

		do {
			System.out.println("********************************");
			System.out.println("*  (S)elecionar lista ativa    *");
			System.out.println("*  (L)istar listas             *");
			System.out.println("*  (C)riar nova lista          *");
			System.out.println("*  (A)pagar lista              *");
			System.out.println("*                              *");
			System.out.println("*  (V)oltar                    *");
			System.out.println("********************************");

			System.out.print("Intoduza uma op��o: ");
			opcaoGerir = teclado.next().charAt(0);
			teclado.nextLine();

			switch(opcaoGerir) {
			case 's':
			case 'S':
				//(S)elecionar lista ativa

				// Verifica se existe alguma lista, atribui � vari�vel posicaoLista o valor retornado pelo m�todo introduzPosicaoLista,
				// verifica se o valor da vari�vel posicaoLista � maior que o da nListas, atribui � vari�vel listaAtiva o valor retornado
				// pelo m�todo selecionarListaAtiva e chama o m�todo imprimeListas
				if (existeLista(nListas)) {
					int posicaoLista = introduzPosicaoLista();
					if (posicaoLista > nListas) {
						System.out.println("\nEssa lista n�o existe!\n");
					} else {
						listaAtiva = selecionarListaAtiva(listaAtiva, posicaoLista);
						imprimeListas(foiFeita, nListas, nomeListas, nTarefas, listasMax, listaAtiva);
					}
				} 
				break;
			case 'l':
			case 'L':
				//(L)istar listas

				// Verifica se existe alguma lista e chama o m�todo listarListas
				if (existeLista(nListas)) {
					listarListas(foiFeita,  nListas,  nomeListas, nTarefas, listasMax, listaAtiva);
				}
				break;
			case 'c':
			case 'C':
				//(C)riar nova lista

				// Verifica se o n�mero de listas � maior ou igual ao tamanho m�ximo de listas,
				// atribui � vari�vel nomeLista o valor retornado pelo m�todo introduzNomeLista,
				// atribui � vari�vel nListas o valor retornado pelo m�todo criarLista e chama o m�todo imprimeListas
				if (nListas >= listasMax) {
					System.out.print("\nN�o � possivel adicionar mais listas porque a lista de listas encontra-se cheia!\n");
				} else {
					String nomeLista = introduzNomeLista();
					nListas = criarLista(nomeListas, nListas, nomeLista);
					imprimeListas(foiFeita, nListas, nomeListas, nTarefas, listasMax, listaAtiva);
				}
				break;
			case 'a':
			case 'A':
				//(A)pagar lista

				// Verifica se existe alguma lista, atribui � vari�vel posicaoLista o valor retornado pelo m�todo introduzPosicaoLista,
				// verifica se o valor da vari�vel posicaoLista � maior que o da nListas,
				// atribui ao array atualizaOpcaoApagar os valores retornado pelo m�todo apagarLista e
				// atualiza as vari�veis listaAtiva e nTarefasTemp com esses valores,
				// verifica se o valor da vari�vel nListas � diferente de zero
				// chama o m�todo imprimeListas ou imprime "�ltima lista apagada com sucesso!" conforme o resultado da verifica��o
				if (existeLista(nListas)) {
					int posicaoLista = introduzPosicaoLista();
					if (posicaoLista > nListas) {
						System.out.println("\nN�o existe nenhuma lista nessa posi��o para ser apagada!\n");
					} else {
						atualizaOpcaoApagar = apagarLista(nomeListas, nListas, nTarefas, tarefa, temPrazo, foiFeita, data, listaAtiva, posicaoLista, atualizaOpcaoApagar);
						listaAtiva = atualizaOpcaoApagar[0];
						nListas = atualizaOpcaoApagar[1];
						if (nListas != 0) {
							imprimeListas(foiFeita, nListas, nomeListas, nTarefas, listasMax, listaAtiva);
						} else {
							System.out.println("\n�ltima lista apagada com sucesso!\n");
						}
					}
				}
				break;
			case 'v':
			case 'V':
				break;
			default:
				System.out.println("Op��o Inv�lida!");
			}
		} while (opcaoGerir != 'v' && opcaoGerir != 'V');

		atualizaMenuGerir[0] = listaAtiva;	// atribui o valor da vari�vel listaAtiva ao valor do array atualizaMenuGerir na posi��o 0
		atualizaMenuGerir[1] = nListas;	// atribui o valor da vari�vel nListas ao valor do array atualizaMenuGerir na posi��o 1

		return atualizaMenuGerir; // retorna o array atualizaMenuGerir com as vari�veis atualizadas
	}



	// ----------------------------------- M�todos do Menu (G)erir Lista ----------------------------------- \\

	// (S)elecionar lista ativa
	static int selecionarListaAtiva(int listaAtiva, int posicaoLista) {

		listaAtiva = posicaoLista - 1;	// Altera o valor da vari�vel listaAtiva para o valor introduzido pelo utilizador

		return listaAtiva;	// retorna a vari�vel listaAtiva
	}



	// (L)istar listas       
	static void listarListas(boolean [][] foiFeita, int nListas, String [] nomeListas, int [] nTarefas, int listasMax, int listaAtiva) {

		imprimeListas(foiFeita, nListas, nomeListas, nTarefas, listasMax, listaAtiva);	// Chama o m�todo imprimeListas

	}



	// (C)riar nova lista
	static int criarLista(String [] nomeListas, int nListas, String nomeLista) {

		nListas += 1; // Atualiza o nListas 

		nomeListas[nListas - 1] = nomeLista; // Adiciona uma nova lista a seguir � �ltima lista adicionada

		return nListas;	// retorna a vari�vel nListas
	}



	// (A)pagar lista
	static int [] apagarLista(String [] nomeListas, int nListas, int [] nTarefas, String [][] tarefa, boolean [][] temPrazo, boolean [][] foiFeita, int [][][] data, int listaAtiva, int posicaoLista, int [] atualizaOpcaoApagar) {

		nListas -= 1; // Atualiza o nListas

		// "Elimina" a lista escolhida e as respetivas tarefas dessa mesma lista
		for (int i = posicaoLista - 1; i < nListas; i++) {
			nomeListas[i] = nomeListas[i + 1];
			nTarefas[i] = nTarefas[i + 1];
			for (int j = 0; j < nTarefas[i]; j++) {
				tarefa[i][j] = tarefa[i + 1][j];
				temPrazo[i][j] = temPrazo[i + 1][j];
				foiFeita[i][j] = foiFeita[i + 1][j];
				data[i][j][0] = data[i + 1][j][0];
				data[i][j][1] = data[i + 1][j][1];
				data[i][j][2] = data[i + 1][j][2];
			}

		}

		nTarefas[nListas] = 0; // Reseta o n�mero de tarefas da lista


		if (posicaoLista - 1 < listaAtiva || listaAtiva == nListas) {  // Decrementa o valor da vari�vel listaAtiva, para manter sempre a lista certa ativa 
			listaAtiva -= 1;
		}

		if (nListas == 0) { // atribui o valor 0 � lista ativa, sempre que � apagada a ultima lista e criada uma outra, sendo que � a primeira
			listaAtiva = 0;
		}

		atualizaOpcaoApagar[0] = listaAtiva; // atribui o novo valor da vari�vel listaAtiva � posi��o 0 da vari�vel atualizaOpcaoApagar
		atualizaOpcaoApagar[1] = nListas; // atribui o novo valor da vari�vel nListas � posi��o 1 da vari�vel atualizaOpcaoApagar

		return atualizaOpcaoApagar; // retorna o array atualizaOpcaoApagar com as vari�veis atualizadas
	}





	// ----------------------------------- Menu (V)isualizar Lista ----------------------------------- \\

	static void menuVisualisarLista(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, String nomeListas, int nListas, int [] idTarefas) {
		Scanner teclado = new Scanner(System.in);
		String [] dataSplit = new String [2]; 
		int dataD = 0;
		int dataM = 0;	
		int dataA = 0;	
		char opcaoVisualizar = 0;

		do {
			System.out.println("********************************");
			System.out.println("*  Visualizar (t)odas          *");
			System.out.println("*  Visualizar (d)ia d          *");
			System.out.println("*  Visualizar (a)t� dia d      *");
			System.out.println("*  Visualizar (p)or fazer      *");
			System.out.println("*  Visualizar (f)eitas         *");
			System.out.println("*  Visualizar por pa(l)avra p  *");
			System.out.println("*                              *");
			System.out.println("*  (V)oltar                    *");
			System.out.println("********************************");

			System.out.print("Intoduza uma op��o: ");
			opcaoVisualizar = teclado.next().charAt(0);
			teclado.nextLine();

			switch(opcaoVisualizar) {
			case 't':
			case 'T':
				//Visualizar todas  

				// Verifica se existe alguma lista, verifica se existe alguma tarefa na lista e chama o m�todo visualizarTodas
				if (existeLista(nListas)) {
					if (existeTarefa(nTarefas, nomeListas)) {
						visualizarTodas(tarefa, temPrazo, foiFeita, data, nTarefas, nomeListas);
					}
				}
				break;
			case 'd':
			case 'D':
				//Visualizar dia d  

				// Verifica se existe alguma lista, verifica se existe alguma tarefa na lista,
				// atribui ao array dataSplit o valor retornado do m�todo lerDia "fragmentado",
				// chama o m�todo visualizarDia
				if (existeLista(nListas)) {
					if (existeTarefa(nTarefas, nomeListas)) {
						dataSplit = lerDia().split("/");
						dataD = Integer.parseInt(dataSplit[0]);
						dataM = Integer.parseInt(dataSplit[1]);
						dataA = Integer.parseInt(dataSplit[2]);
						visualizarDia(tarefa, foiFeita, data, nTarefas, nomeListas, idTarefas, dataD, dataM, dataA);
					}
				}
				break;
			case 'a':
			case 'A':
				//Visualizar at� dia d  

				// Verifica se existe alguma lista, verifica se existe alguma tarefa na lista,
				// atribui ao array dataSplit o valor retornado do m�todo lerAteDia "fragmentado",
				// chama o m�todo visualizarAteDia
				if (existeLista(nListas)) {
					if (existeTarefa(nTarefas, nomeListas)) {
						dataSplit = lerAteDia().split("/");
						dataD = Integer.parseInt(dataSplit[0]);
						dataM = Integer.parseInt(dataSplit[1]);
						dataA = Integer.parseInt(dataSplit[2]);
						visualizarAteDia(tarefa, temPrazo, foiFeita, data, nTarefas, nomeListas, idTarefas, dataD, dataM, dataA);
					}
				}
				break;
			case 'p':
			case 'P':
				//Visualizar (p)or fazer 

				// Verifica se existe alguma lista, verifica se existe alguma tarefa na lista
				// chama o m�todo visualizarPorfazer
				if (existeLista(nListas)) {
					if (existeTarefa(nTarefas, nomeListas)) {
						visualizarPorfazer(tarefa, temPrazo, foiFeita, data, nTarefas, nomeListas, idTarefas);
					}
				}
				break;
			case 'f':
			case 'F':
				//Visualizar (f)eitas  

				// Verifica se existe alguma lista, verifica se existe alguma tarefa na lista
				// chama o m�todo visualizarFeitas
				if (existeLista(nListas)) {
					if (existeTarefa(nTarefas, nomeListas)) {
						visualizarFeitas(tarefa, temPrazo, foiFeita, data, nTarefas, nomeListas, idTarefas);
					}
				}
				break;
			case 'l':
			case 'L':
				//Visualizar por pa(l)avra p 

				// Verifica se existe alguma lista, verifica se existe alguma tarefa na lista,
				// atribui � vari�vel palavraIntroduzida a palavra introduzida pelo utilizador
				// chama o m�todo visualizarPorPalavra
				if (existeLista(nListas)) {
					if (existeTarefa(nTarefas, nomeListas)) {
						System.out.print("\nIntroduza a palavra que pretende procurar: ");
						String palavraIntroduzida = teclado.next();
						visualizarPorPalavra(tarefa, temPrazo, foiFeita, data, nTarefas, nomeListas, idTarefas, palavraIntroduzida);
					}
				}
				break;
			case 'v':
			case 'V':
				break;
			default:
				System.out.println("Op��o Inv�lida!");
			}
		} while (opcaoVisualizar != 'v' && opcaoVisualizar != 'V');

	}



	// ----------------------------------- M�todos do Menu (V)isualizar lista----------------------------------- \\

	// Visualizar (t)odas 
	static void visualizarTodas(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, String nomeListas) {

		imprimeTarefas(nomeListas, nTarefas, tarefa, temPrazo, foiFeita, data); // Chama o metodo imprimeTarefas

	}



	// Visualizar (d)ia d  
	static void visualizarDia(String [] tarefa, boolean [] foiFeita, int [][] data, int nTarefas, String nomeListas, int [] idTarefas, int dataD, int dataM, int dataA) {
		System.out.println();

		idTarefas = new int [nTarefas];
		boolean existeDia = false;	

		// este ciclo percorre o nTarefas e sempre que a data introduzida for igual � data existente no array data e cada posi��o no array temPrazo for true,
		// guarda o n�mero da tarefa na posi��o do array idTarefas e coloca a vari�vel existeDia a true
		for (int i = 0; i < nTarefas; i++) {
			if (dataD == data[i][0] && dataM == data[i][1] && dataA == data[i][2]) { 
				idTarefas[i] = i + 1;
				existeDia = true;
			} 
		}

		// se a vari�vel existeDia for true e as posi��es do array idTarefas forem diferentes de 0 imprime as tarefas
		if (existeDia) {
			System.out.println("Lista: " + nomeListas);
			System.out.println();
			System.out.println("\t Tarefa \t\t Data \t\t Feita"); 
			for (int i = 0; i < nTarefas; i++) {
				if (idTarefas[i] != 0) {
					System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
					System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
					System.out.println();
				}
			}
		} else {
			System.out.printf("N�o existe nenhuma tarefa planeada para esse dia na lista \"%s\"!\n", nomeListas);
		}

		System.out.println();
	}



	// Visualizar (a)t� dia d  
	static void visualizarAteDia(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, String nomeListas, int [] idTarefas, int dataD, int dataM, int dataA) {
		System.out.println();

		idTarefas = new int [nTarefas];
		boolean existeAteDia = false;

		// este ciclo percorre o nTarefas e verifica se a data introduzida � maior ou igual � data existente no array data,
		// caso seja verdade, guarda o n�mero da tarefa na posi��o do array idTarefas e coloca a vari�vel existeAteDia a true
		for (int i = 0; i < nTarefas; i++) {
			if (dataA > data[i][2] && temPrazo[i] || 
					dataA == data[i][2] && dataM > data[i][1] && temPrazo[i]|| 
					dataA == data[i][2] && dataM == data[i][1] && dataD >= data[i][0] && temPrazo[i]) {
				idTarefas[i] = i + 1;
				existeAteDia = true;
			}
		}

		// se a vari�vel existeAteDia for true e as posi��es do array idTarefas forem diferentes de 0 imprime, as tarefas at� ao dia introduzido
		if (existeAteDia) {
			System.out.println("Lista: " + nomeListas);
			System.out.println();
			System.out.println("\t Tarefa \t\t Data \t\t Feita"); 
			for (int i = 0; i < nTarefas; i++) {
				if (idTarefas[i] != 0 && temPrazo[i]) {
					System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
					if (!temPrazo[i]) {
						System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
					} else {
						System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
					}
					System.out.println();
				}
			}
		} else {
			System.out.printf("N�o existe nenhuma tarefa planeada at� esse dia na lista \"%s\"!\n", nomeListas);
		}


		System.out.println();
	}



	// Visualizar (p)or fazer
	static void visualizarPorfazer(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, String nomeListas, int [] idTarefas) {
		System.out.println();

		idTarefas = new int [nTarefas];
		boolean existePorFazer = false;

		// este ciclo percorre o nTarefas e verifica se cada tarefa do array foiFeita � false,
		// caso seja false, guarda o n�mero da tarefa na posi��o do array idTarefas e coloca a vari�vel existePorFazer a true
		for (int i = 0; i <  nTarefas; i++) {
			if (!foiFeita[i]) {
				idTarefas[i] = i + 1;
				existePorFazer = true;
			} 
		}

		// se a vari�vel existePorFazer for true e as posi��es do array idTarefas forem diferentes de 0 e as posi��es do array temPrazo forem true,
		// ent�o imprime as tarefas que est�o por fazer
		if (existePorFazer) {
			System.out.println("Lista: " + nomeListas);
			System.out.println();
			System.out.println("\t Tarefa \t\t Data \t\t Feita"); 
			for (int i = 0; i < nTarefas; i++) {
				if (idTarefas[i] != 0) {
					System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
					if (!temPrazo[i]) {
						System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
					} else {
						System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
					}
					System.out.println();
				}
			}
		} else {
			System.out.printf("N�o existe nenhuma tarefa por fazer na lista \"%s\"!\n", nomeListas);
		}

		System.out.println();
	}



	// Visualizar (f)eitas 
	static void visualizarFeitas(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, String nomeListas, int [] idTarefas) {
		System.out.println();

		idTarefas = new int [nTarefas];
		boolean estaFeita = false;

		// este ciclo percorre o nTarefas e verifica se cada tarefa do array foiFeita � true,
		// caso seja true, guarda o n�mero da tarefa na posi��o do array idTarefas e coloca a vari�vel estaFeita a true
		for (int i = 0; i < nTarefas; i++) {
			if (foiFeita[i]) {
				idTarefas[i] = i + 1;
				estaFeita = true;
			} 
		}

		// se a vari�vel estaFeita for true e as posi��es do array idTarefas forem diferentes de 0, ent�o imprime as tarefas que est�o feitas
		if (estaFeita) {
			System.out.println("Lista: " + nomeListas);
			System.out.println();
			System.out.println("\t Tarefa \t\t Data \t\t Feita"); 
			for (int i = 0; i < nTarefas; i++) {
				if (idTarefas[i] != 0) {
					System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
					if (!temPrazo[i]) {
						System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
					} else {
						System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
					}
					System.out.println();
				}
			}
		} else {
			System.out.printf("N�o existe nenhuma tarefa feita na lista \"%s\"!\n", nomeListas);
		}

		System.out.println();
	}



	// Visualizar por pa(l)avra p
	static void visualizarPorPalavra(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, String nomeListas, int [] idTarefas, String palavraIntroduzida) {
		System.out.println();

		idTarefas = new int [nTarefas];
		boolean encontrouPalavra = false;

		// este ciclo percorre o nTarefas e verifica se a palavra introduzida corresponde � que se encontra no array tarefa
		// caso seja true, guarda o n�mero da tarefa na posi��o do array idTarefas e coloca a vari�vel encontrouPalavra a true
		for (int i = 0; i < nTarefas; i++) {
			if (tarefa[i].indexOf(palavraIntroduzida) != -1) {
				idTarefas[i] = i + 1;
				encontrouPalavra = true;
			}
		}

		// se a vari�vel encontrouPalavra for true e as posi��es do array idTarefas forem diferentes de 0, ent�o imprime todas as tarefas que tenham essa palavra na descri��o
		if (encontrouPalavra) {
			System.out.println("Lista: " + nomeListas);
			System.out.println();
			System.out.println("\t Tarefa \t\t Data \t\t Feita");
			for (int i = 0; i < nTarefas; i++) {
				if (idTarefas[i] != 0) {
					System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
					if (!temPrazo[i]) {
						System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
					} else {
						System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
					}
					System.out.println();
				}
			}
		} else {
			System.out.printf("N�o existe nenhuma palavra com esse nome na lista \"%s\"!\n", nomeListas);
		}

		System.out.println();
	}





	// ----------------------------------- Menu (M)arcar ----------------------------------- \\

	static int menuMarcar(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, String nomeListas, int nListas, int [] idTarefas, int ultimaFeita) {
		Scanner teclado = new Scanner(System.in);
		String [] dataSplit = new String [2];
		int marcarTarefa = 0; 
		int dataD = 0;
		int dataM = 0;
		int dataA = 0;
		char opcaoMarcar = 0;

		do {
			System.out.println("************************************");
			System.out.println("*  Marcar como (f)eita por n�mero  *");
			System.out.println("*  Marcar como feita por (t)exto   *");
			System.out.println("*  (D)esmarcar �ltima feita        *");
			System.out.println("*  Marcar todas (n)o dia d         *");
			System.out.println("*                                  *");
			System.out.println("*  (V)oltar                        *");
			System.out.println("************************************");

			System.out.print("Intoduza uma op��o: ");
			opcaoMarcar = teclado.next().charAt(0);
			teclado.nextLine();

			switch(opcaoMarcar) {
			case 'f':
			case 'F':
				// Marcar como (f)eita por n�mero

				// Verifica se existe alguma lista, verifica se existe alguma tarefa na lista,
				// guarda o n�mero da tarefa introduzido pelo utilizador na vari�vel marcarTarefa
				// verifica se o valor da vari�vel marcarTarefa � maior que o nTarefas
				// atribui � vari�vel ultimaFeita o valor retornado pelo m�todo marcarFeitaPorNumero
				if (existeLista(nListas)) {
					if (existeTarefa(nTarefas, nomeListas)) {
						do {
							System.out.print("\nIntroduza o n�mero da tarefa (> 0): ");
							marcarTarefa = lerInt();
						} while (marcarTarefa <= 0);
						if (marcarTarefa > nTarefas) {
							System.out.printf("\nN�o existe nenhuma tarefa com esse n�mero na lista \"%s\"!\n\n", nomeListas);
						} else {
							ultimaFeita = marcarFeitaPorNumero(tarefa, temPrazo, foiFeita, data, nomeListas, ultimaFeita, marcarTarefa);
						}
					}					
				}
				break;
			case 't':
			case 'T':
				// Marcar como feita por (t)exto

				// Verifica se existe alguma lista, verifica se existe alguma tarefa na lista,
				// atribui � vari�vel descric�o o texto a ser procurado introduzido pelo utilizador,
				// atribui � vari�vel ultimaFeita o valor retornado pelo m�todo marcarFeitaPorTexto
				if (existeLista(nListas)) {
					if (existeTarefa(nTarefas, nomeListas)) {
						String descricao = lerNovaDescricao();
						ultimaFeita = marcarFeitaPorTexto(tarefa, temPrazo, foiFeita, data, nTarefas, nomeListas, idTarefas, ultimaFeita, descricao);
					}
				}
				break;
			case 'd':
			case 'D':
				//(D)esmarcar �ltima feita 

				// Verifica se existe alguma lista, verifica se existe alguma tarefa na lista,
				// atribui a vari�vel ultimaFeita o valor retornado pelo m�todo desmarcarUltimaFeita
				if (existeLista(nListas)) {
					if (existeTarefa(nTarefas, nomeListas)) {
						ultimaFeita = desmarcarUltimaFeita(tarefa, temPrazo, foiFeita, data, nTarefas, nomeListas, idTarefas, ultimaFeita);
					}			
				}				
				break;
			case 'n':
			case 'N':
				//Marcar todas (n)o dia d  

				//Verifica se existe alguma lista,verifica se existe alguma tarefa na lista,
				//atribui ao array dataSplit o valor retornado do m�todo lerDia "fragmentado",
				//atribui a vari�vel ultimaFeita o valor retornado pelo m�todo marcarTodasDiaD
				if (existeLista(nListas)) {
					if (existeTarefa(nTarefas, nomeListas)) {
						dataSplit = lerDia().split("/");
						dataD = Integer.parseInt(dataSplit[0]);
						dataM = Integer.parseInt(dataSplit[1]);
						dataA = Integer.parseInt(dataSplit[2]);
						ultimaFeita = marcarTodasDiaD(tarefa, temPrazo, foiFeita, data, nTarefas, nomeListas, idTarefas, ultimaFeita, dataD, dataM, dataA);
					}				
				}																															
				break;
			case 'v':
			case 'V':
				break;
			default:
				System.out.println("Op��o Inv�lida!");	
			}
		} while (opcaoMarcar != 'v' && opcaoMarcar != 'V');

		return ultimaFeita;
	}



	// ----------------------------------- M�todos do Menu (M)arcar ----------------------------------- \\

	// Marcar como (f)eita por n�mero
	static int marcarFeitaPorNumero(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, String nomeListas, int ultimaFeita, int marcarTarefa) {
		System.out.println();

		// verifica se a tarefa na posi��o indicada pelo utilizador j� se encontra marcada como feita,
		// e caso seja false, marca a tarefa como feita
		if (!foiFeita[marcarTarefa - 1]) {
			foiFeita[marcarTarefa - 1] = true;

			// imprime a tarefa marcada como feita
			if (foiFeita[marcarTarefa - 1]) {
				System.out.printf("Tarefa \"%s\" marcada como feita com sucesso!\n", tarefa[marcarTarefa - 1]);
				System.out.println();
				System.out.println("Lista: " + nomeListas);
				System.out.println();
				System.out.println("\t Tarefa \t\t Data \t\t Feita");
				System.out.printf("%d: \t %-20s", marcarTarefa, tarefa[marcarTarefa - 1]);
				if (!temPrazo[marcarTarefa - 1]) {
					System.out.printf("\t Sem Prazo \t %s", foiFeita[marcarTarefa - 1] ? "X" : "");
				} else {
					System.out.printf("\t %02d/%02d/%d \t %s", data[marcarTarefa - 1][0], data[marcarTarefa - 1][1], data[marcarTarefa - 1][2], foiFeita[marcarTarefa - 1] ? "X" : "");
				}

				System.out.println();

				ultimaFeita = marcarTarefa; // guarda a �ltima tarefa feita na vari�vel ultimaFeita

			}
		} else {
			System.out.printf("A tarefa %d na lista \"%s\" j� se encontra marcada como feita!\n", marcarTarefa, nomeListas);
		}

		System.out.println();

		return ultimaFeita; // retorna a vari�vel ultimaFeita
	}



	// Marcar como feita por (t)exto
	static int marcarFeitaPorTexto(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, String nomeListas, int [] idTarefas, int ultimaFeita, String descricao) {
		System.out.println();

		idTarefas = new int [nTarefas];
		boolean existeTarefa = false;

		// este ciclo percorre o nTarefas e verifica se o texto introduzido corresponde ao que se encontra no array tarefa
		// e se � false no array foiFeita. Caso se verifique, guarda o n�mero da tarefa na posi��o do array idTarefas e coloca a vari�vel existeTarefa a true
		for (int i = 0; i < nTarefas; i++) {
			if (tarefa[i].indexOf(descricao) != -1 && !foiFeita[i]) {
				idTarefas[i] = i + 1;
				existeTarefa = true;
			}
		}

		// se a vari�vel existeTarefa for true verifica se a tarefa na posi��o foiFeita tamb�m � true, se for � porque a tarefa j� se encontra
		// marcada, sen�o coloca-a a true e imprime a tarefa marcada como feita
		if (existeTarefa) {
			for (int i = 0; i < nTarefas; i++) {
				if (idTarefas[i] != 0) {
					foiFeita[i] = true;
					System.out.printf("Tarefa \"%s\" marcada como feita com sucesso!\n", tarefa[i]);
					System.out.println();
					System.out.println("Lista: " + nomeListas);
					System.out.println();
					System.out.println("\t Tarefa \t\t Data \t\t Feita");
					System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
					if (!temPrazo[i]) {
						System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
					} else {
						System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
					}

					ultimaFeita = idTarefas[i]; // guarda a �ltima tarefa feita na vari�vel ultimaFeita

					System.out.println();

					break;
				}	
			}
		} else {
			System.out.printf("N�o existe nenhuma tarefa com essa descri�ao na lista \"%s\" para ser marcada!\n", nomeListas);
		}

		System.out.println();

		return ultimaFeita;	// retorna a vari�vel ultimaFeita
	}



	// (D)esmarcar �ltima feita 
	static int desmarcarUltimaFeita (String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, String nomeListas, int [] idTarefas, int ultimaFeita) {
		System.out.println();

		idTarefas = new int [nTarefas];

		// verifica se a vari�vel ultimaFeita � diferente de 0, caso seja percorre o nTarefas e incrementa ao array idTarefas o n�mero e a posi��o que tem a 
		// vari�vel ultimaFeita. Por fim desmarca a �ltima tarefa marcada como feita e imprime
		if (ultimaFeita != 0) {
			for (int i = 0; i < nTarefas; i++) {
				idTarefas[ultimaFeita - 1] = ultimaFeita;
				if (idTarefas[i] != 0) {
					foiFeita[i] = false;
					System.out.printf("Tarefa \"%s\" desmarcada como feita com sucesso!\n", tarefa[i]);
					System.out.println();
					System.out.println("Lista: " + nomeListas);
					System.out.println();
					System.out.println("\t Tarefa \t\t Data \t\t Feita");
					System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
					if (!temPrazo[i]) {
						System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
					} else {
						System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
					}
					System.out.println();

				}
			}
			ultimaFeita = 0; // reseta a vari�vel
		} else {
			System.out.printf("N�o existe nenhuma �ltima tarefa marcada como feita na lista \"%s\" para ser desmarcada!\n", nomeListas);
		}

		System.out.println();

		return ultimaFeita;	// retorna a vari�vel ultimaFeita
	}



	// Marcar todas (n)o dia d 
	static int marcarTodasDiaD(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, String nomeListas, int [] idTarefas, int ultimaFeita, int dataD, int dataM, int dataA) {
		System.out.println();

		idTarefas = new int [nTarefas];
		boolean estaMarcada = false;

		// este ciclo percorre o nTarefas e verifica se a data introduzida � igual aos valores existentes no array data, se as tarefas no array foiFeitas,
		// t�m o valor false e t�m o valor true no array temPrazo, guarando o n�mero da tarefa na posi��o do array idTarefas e coloca a vari�vel estaMarcada a true
		for (int i = 0; i < nTarefas; i++) {
			if (dataD == data[i][0] && dataM == data[i][1] && dataA == data[i][2] && !foiFeita[i]) {
				idTarefas[i] = i + 1;
				estaMarcada = true;
			} 
		}

		// caso a vari�vel estaMarcada seja true, percorre o nTarefas e caso as posi��es do array idTarefas sejam diferentes de 0 coloca as tarefas a true e imprime
		if (estaMarcada) {
			System.out.println("Tarefa(s) marcada(s) como feita(s) com sucesso!");
			System.out.println();
			System.out.println("Lista: " + nomeListas);
			System.out.println();
			System.out.println("\t Tarefa \t\t Data \t\t Feita");
			for (int i = 0; i < nTarefas; i++) {
				if (idTarefas[i] != 0) {
					foiFeita[i] = true;
					System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
					if (!temPrazo[i]) {
						System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
					} else {
						System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
					}

					ultimaFeita = idTarefas[i]; // guarda a �ltima tarefa feita na vari�vel ultimaFeita

					System.out.println();                                    
				}
			}
		} else {
			System.out.printf("N�o existe nenhuma tarefa na lista \"%s\" para ser marcada nesse dia!\n", nomeListas);
		}

		System.out.println();

		return ultimaFeita;	// retorna a vari�vel ultimaFeita
	}





	// ----------------------------------- Menu (E)ditar ----------------------------------- \\

	static int menuEditar(int tamMax, String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, String nomeListas, int nListas, int [] idTarefas) {
		Scanner teclado = new Scanner(System.in);

		String novaDescricao = "";
		String [] dataSplit = new String [2];
		int dataD = 0;
		int dataM = 0;
		int dataA = 0;
		int posicaoTarefa = 0;
		char prazo = 0;
		char opcaoAddDelete = 0;

		do {
			System.out.println("*************************************");
			System.out.println("*  (A)dicionar tarefa               *");
			System.out.println("*  Adicionar (t)arefa na posi��o n  *");
			System.out.println("*  Apagar tarefa na (p)osi��o n     *");
			System.out.println("*  Apagar (f)eitas                  *");
			System.out.println("*  (E)ditar tarefa                  *");
			System.out.println("*                                   *");
			System.out.println("*  (V)oltar                         *");
			System.out.println("*************************************");

			System.out.print("Intoduza uma op��o: ");
			opcaoAddDelete = teclado.next().charAt(0);
			teclado.nextLine();

			switch(opcaoAddDelete) {
			case 'a':
			case 'A':
				//Adicionar tarefa

				// Verifica se existe alguma lista, verifica se a lista est� cheia,
				// atribui � vari�vel novaDescricao o valor retornado pelo m�todo lerNovaDescricao,
				// atribui � vari�vel prazo o valor retornado pelo metodo lerPrazo,
				// se o valor da vari�vel prazo for 's' ou 'S' atribui ao array dataSplit o valor retornado do m�todo lerDia "fragmentado",
				// verifica se a data introduzida � valida usando os metodos validarData e eBissexto
				// adiciona a tarefa utilizando o metodo adicionarTarefa e retorna o n�mero de tarefas atualizado
				// imprime o resultado usando o m�todo imprimeTarefas
				if (existeLista(nListas)) {
					if (!listaCheia(nTarefas, tamMax, nomeListas)) {
						novaDescricao = lerNovaDescricao();
						prazo = lerPrazo();
						if (prazo == 's' || prazo == 'S') {
							dataSplit = lerNovaData().split("/");
							dataD = Integer.parseInt(dataSplit[0]);
							dataM = Integer.parseInt(dataSplit[1]);
							dataA = Integer.parseInt(dataSplit[2]);
						}
						if (validarData(eBissexto(dataA), dataD, dataM, dataA)) {
							nTarefas = adicionarTarefa(tarefa, temPrazo, foiFeita, data, nTarefas, novaDescricao, prazo, dataD, dataM, dataA);
							System.out.printf("\nTarefa \"%s\" adicionada com sucesso!\n", novaDescricao);
							imprimeTarefas(nomeListas, nTarefas, tarefa, temPrazo, foiFeita, data);
						}
					}
				}
				break;
			case 't':
			case 'T':
				//Adicionar tarefa na posi��o n

				// Verifica se existe alguma lista, verifica se a lista est� cheia,
				// atribui a vari�vel posicaoTarefa o valor retornado pelo m�todo lerPosicaoTarefa,
				// verifica se a posi��o introduzida pelo utilizador � maior que o nTarefas
				// l� uma nova descri�ao, l� se o utilizador deseja introduzir um prazo e caso se verifique 
				// atribui ao array dataSplit o valor retornado do m�todo lerNovaData "fragmentado",
				// verifica se a data introduzida � v�lida usando os m�todos validarData e eBissexto
				// adiciona a tarefa utilizando o metodo adicionarTarefaNaPosicao e retorna o n�mero de tarefas atualizado
				// imprime o resultado usando o m�todo imprimeTarefas
				if (existeLista(nListas)) {
					if (!listaCheia(nTarefas, tamMax, nomeListas)) {
						posicaoTarefa = lerPosicaoTarefa();
						if (posicaoTarefa - 1 > nTarefas) {
							System.out.printf("\nN�o � possivel adicionar nenhuma tarefa nessa posi��o na lista \"%s\"!\n\n", nomeListas);
						} else {
							novaDescricao = lerNovaDescricao();
							prazo = lerPrazo();
							if (prazo == 's' || prazo == 'S') {
								dataSplit = lerNovaData().split("/");
								dataD = Integer.parseInt(dataSplit[0]);
								dataM = Integer.parseInt(dataSplit[1]);
								dataA = Integer.parseInt(dataSplit[2]);
							}
							if (validarData(eBissexto(dataA), dataD, dataM, dataA)) {
								nTarefas = adicionarTarefaNaPosicao(tarefa, temPrazo, foiFeita, data, nTarefas, posicaoTarefa, novaDescricao, prazo, dataD, dataM, dataA);
								System.out.printf("\nTarefa \"%s\" adicionada com sucesso na posi��o %d!\n", novaDescricao, posicaoTarefa);
								imprimeTarefas(nomeListas, nTarefas, tarefa, temPrazo, foiFeita, data);
							}
						}
					}
				}

				break;
			case 'p':
			case 'P':
				//Apagar tarefa na posi��o n

				// Verifica se existe alguma lista e verifica se existe alguma tarefa na lista,
				// atribui � vari�vel posicaoTarefa o valor retornado pelo m�todo lerPosicaoTarefa,
				// verifica se a posi��o introduzida pelo utilizador � maior que o nTarefas
				// atualiza a vari�vel nTarefas com o valor retornado pelo m�todo apagarTarefaNaPosicao
				// verifica se existe mais de uma tarefa na lista, caso seja true chama o m�todo imprimeTarefas
				if (existeLista(nListas)) {
					if (existeTarefa(nTarefas, nomeListas)) {
						posicaoTarefa = lerPosicaoTarefa();
						if (posicaoTarefa > nTarefas) {
							System.out.printf("\nN�o � possivel remover nenhuma tarefa nessa posi��o na lista \"%s\"!\n\n", nomeListas);
						} else {
							System.out.printf("\nTarefa \"%s\" apagada com sucesso!\n", tarefa[posicaoTarefa - 1]);
							nTarefas = apagarTarefaNaPosicao(tarefa, temPrazo, foiFeita, data, nTarefas, posicaoTarefa);
							if (nTarefas != 0) {
								imprimeTarefas(nomeListas, nTarefas, tarefa, temPrazo, foiFeita, data);
							} else {
								System.out.println("\n�ltima tarefa apagada com sucesso!\n");
							}
						}
					}
				}
				break;
			case 'f':
			case 'F':
				//Apagar feitas

				// Verifica se existe alguma lista e verifica se existe alguma tarefa na lista,
				// se existir atualiza o valor da vari�vel nTarefas com o valor retornado pelo m�todo apagarFeitas
				if (existeLista(nListas)) {
					if (existeTarefa(nTarefas, nomeListas)) {
						nTarefas = apagarFeitas(tarefa, temPrazo, foiFeita, data, nTarefas, nomeListas, idTarefas);
					}	
				}
				break;
			case 'e':
			case 'E':
				// ----------------------------------- Submenu (E)ditar tarefa ----------------------------------- \\
				char opcaoEditar = 0;

				do {
					System.out.println("******************************");
					System.out.println("*  (E)ditar texto            *");
					System.out.println("*  (R)emover/adicionar data  *");
					System.out.println("*                            *");
					System.out.println("*  (V)oltar                  *");
					System.out.println("******************************");

					System.out.print("Intoduza uma op��o: ");
					opcaoEditar = teclado.next().charAt(0);
					teclado.nextLine();

					switch(opcaoEditar) {
					case 'e':
					case 'E':
						//Editar texto

						// Verifica se existe alguma lista, atribui � vari�vel posicaoTarefa o valor retornado pelo m�todo lerPosicaoTarefa,
						// verifica se a posi��o introduzida pelo utilizador � maior que o nTarefas
						// atualiza a vari�vel novaDescricao com o valor retornado pelo m�todo lerNovaDescricao e chama o m�todo editarTexto
						if (existeLista(nListas)) {
							posicaoTarefa = lerPosicaoTarefa();
							if (posicaoTarefa > nTarefas) {
								System.out.printf("\nN�o existe nenhuma tarefa na lista \"%s\" com esse n�mero!\n\n", nomeListas);
							} else {
								novaDescricao = lerNovaDescricao();
								editarTexto(tarefa, temPrazo, foiFeita, data, nomeListas, posicaoTarefa, novaDescricao);
							}
						}
						break;
					case 'r':
					case 'R':
						//Remover/adicionar data 

						// Verifica se existe alguma lista, atribui � vari�vel posicaoTarefa o valor retornado pelo m�todo lerPosicaoTarefa,
						// verifica se a posi��o introduzida pelo utilizador � maior que o nTarefas
						// verifica se a tarefa n�o tem um prazo, caso se confirme atribui ao array dataSplit o valor retornado do m�todo lerNovaData "fragmentado",
						// verifica se a data introduzida � v�lida usando os m�todos validarData e eBissexto e chama o m�todo adicionarData,
						// se a tarefa j� tiver um prazo chama o m�todo removerData
						if (existeLista(nListas)) {
							posicaoTarefa = lerPosicaoTarefa();
							if (posicaoTarefa > nTarefas) {
								System.out.printf("\nN�o existe nenhuma tarefa na lista \"%s\" com esse n�mero!\n\n", nomeListas);
							} else {
								if (!temPrazo[posicaoTarefa - 1]) {
									dataSplit = lerNovaData().split("/");
									dataD = Integer.parseInt(dataSplit[0]);
									dataM = Integer.parseInt(dataSplit[1]);
									dataA = Integer.parseInt(dataSplit[2]);
									if (validarData(eBissexto(dataA), dataD, dataM, dataA)) {
										adicionarData(tarefa, temPrazo, foiFeita, data, nomeListas, posicaoTarefa, dataD, dataM, dataA);
									}
								} else {
									removerData(temPrazo, data, posicaoTarefa);
								}
							}
						}
						break;
					case 'v':
					case 'V':
						break;
					default:
						System.out.println("Op��o Inv�lida");
					}
				} while (opcaoEditar != 'v' && opcaoEditar != 'V');
				break;
			case 'v':
			case 'V':
				break;
			default:
				System.out.println("Op��o Inv�lida!");
			}
		} while (opcaoAddDelete != 'v' && opcaoAddDelete != 'V');

		return nTarefas; // retorna a vari�vel nTarefas
	}



	// ----------------------------------- M�todos do Menu (E)ditar ----------------------------------- \\

	// (A)dicionar tarefa 
	static int adicionarTarefa(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, String novaDescricao, char prazo, int dataD, int dataM, int dataA) {
		nTarefas += 1; // atualiza o nTarefas

		// adiciona a tarefa no final da lista
		if (prazo == 's' || prazo == 'S') {
			tarefa[nTarefas - 1] = novaDescricao;
			temPrazo[nTarefas - 1] = true;
			foiFeita[nTarefas - 1] = false;
			data[nTarefas - 1][0] = dataD;
			data[nTarefas - 1][1] = dataM;
			data[nTarefas - 1][2] = dataA;
		} else {
			tarefa[nTarefas - 1] = novaDescricao;
			temPrazo[nTarefas - 1] = false;
			foiFeita[nTarefas - 1] = false;
			data[nTarefas - 1][0] = 0;
			data[nTarefas - 1][1] = 0;
			data[nTarefas - 1][2] = 0;
		}

		return nTarefas; // retorna a vari�vel nTarefas
	}



	// Adicionar (t)arefa na posi��o n 
	static int adicionarTarefaNaPosicao(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, int posicaoTarefa, String novaDescricao, char prazo, int dataD, int dataM, int dataA) {

		nTarefas += 1; // atualiza o nTarefas

		// decrementa uma posi��o em cada array at� chegar � posi��o introduzida pelo utilizador (faz um shift para a "direita")
		for (int i = nTarefas - 1; i > posicaoTarefa - 1; i--) {
			tarefa[i] = tarefa[i - 1]; 
			temPrazo[i] = temPrazo[i - 1]; 
			foiFeita[i] = foiFeita[i - 1];
			data[i][0] = data[i - 1][0]; 
			data[i][1] = data[i - 1][1];
			data[i][2] = data[i - 1][2];
		}

		// guarda a nova tarefa na posi��o introduzida
		if (prazo == 's' || prazo == 'S') {
			tarefa[posicaoTarefa - 1] = novaDescricao;
			temPrazo[posicaoTarefa - 1] = true;
			foiFeita[posicaoTarefa - 1] = false;
			data[posicaoTarefa - 1][0] = dataD;
			data[posicaoTarefa - 1][1] = dataM;
			data[posicaoTarefa - 1][2] = dataA;
		} else {
			tarefa[posicaoTarefa - 1] = novaDescricao;
			temPrazo[posicaoTarefa - 1] = false;
			foiFeita[posicaoTarefa - 1] = false;
			data[posicaoTarefa - 1][0] = 0;
			data[posicaoTarefa - 1][1] = 0;
			data[posicaoTarefa - 1][2] = 0;
		}

		return nTarefas; // retorna a vari�vel nTarefas
	}



	// Apagar tarefa na (p)osi��o n 
	static int apagarTarefaNaPosicao(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, int posicaoTarefa) {

		nTarefas -= 1; // atualiza o nTarefas

		// incrementa uma posi��o em cada array desde a posi��o introduzida pelo utilizador at� chegar ao nTarefas (faz um shift para a "esquerda")
		for (int i = posicaoTarefa - 1; i < nTarefas; i++) {
			tarefa[i] = tarefa[i + 1];
			temPrazo[i] = temPrazo[i + 1]; 
			foiFeita[i] = foiFeita[i + 1];
			data[i][0] = data[i + 1][0]; 
			data[i][1] = data[i + 1][1];
			data[i][2] = data[i + 1][2];
		}

		return nTarefas; // retorna a vari�vel nTarefas
	}



	// Apagar (f)eitas        
	static int apagarFeitas(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, String nomeListas, int [] idTarefas) {
		System.out.println();

		idTarefas = new int [nTarefas];
		boolean todasApagadas = false;
		int quantTarefasFeitas = 0;

		// este ciclo percorre o nTarefas, em que drecrementa desde o n�mero que est� em nTarefas at� chegar � posi��o 0
		// verifica se em cada posi��o no array foiFeita � true e caso seja true guarda a posi��o no array idTarefas
		// por fim percorrre novamente o nTarefas - 1, come�a na primeira posi��o do array idTarefas e incrementa, atualizando assim a lista (remove os duplicados)
		for (int i = nTarefas - 1; i >= 0; i--) {
			if (foiFeita[i]) {
				idTarefas[i] = i;
				quantTarefasFeitas ++;
				todasApagadas = true;
				for (int k = idTarefas[i]; k < nTarefas - 1; k++) {
					tarefa[k] = tarefa[k + 1];
					temPrazo[k] = temPrazo[k + 1];
					foiFeita[k] = foiFeita[k + 1];
					data[k][0] = data[k + 1][0];
					data[k][1] = data[k + 1][1];
					data[k][2] = data[k + 1][2];
				}
			} 
		}

		nTarefas -= quantTarefasFeitas; // atualiza o nTarefas

		// imprime
		if (todasApagadas) {
			System.out.printf("Tarefas feitas apagadas com sucesso na lista \"%s\"!\n", nomeListas);
		} else {
			System.out.printf("N�o existe nenhuma tarefa feita para ser apagada na lista \"%s\"!\n", nomeListas);
		}

		System.out.println();

		return nTarefas; // retorna a vari�vel nTarefas
	}


	// ----------------------------------- M�todos do submenu (E)ditar tarefa ----------------------------------- \\

	// (E)ditar texto       
	static void editarTexto (String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, String nomeListas, int posicaoTarefa, String novaDescricao) {


		tarefa[posicaoTarefa - 1] = novaDescricao;	// atribui o valor da vari�vel novaDescricao � tarefa na posi�ao introduzida
		System.out.println();

		// imprime
		System.out.println("Descri��o alterada com sucesso!");
		System.out.println();
		System.out.println("Lista: " + nomeListas);
		System.out.println();
		System.out.println("\t Tarefa \t\t Data \t\t Feita");
		System.out.printf("%d: \t %-20s", posicaoTarefa, tarefa[posicaoTarefa - 1]);
		if (!temPrazo[posicaoTarefa - 1]) {
			System.out.printf("\t Sem Prazo \t %s", foiFeita[posicaoTarefa - 1] ? "X" : "");
		} else {
			System.out.printf("\t %02d/%02d/%d \t %s", data[posicaoTarefa - 1][0], data[posicaoTarefa - 1][1], data[posicaoTarefa - 1][2], foiFeita[posicaoTarefa - 1] ? "X" : "");
		}

		System.out.println();


		System.out.println();
	}



	// Adicionar data
	static void adicionarData(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, String nomeListas, int posicaoTarefa, int dataD, int dataM, int dataA) {

		temPrazo[posicaoTarefa - 1] = true;	// atribui o valor true � vari�vel temPrazo na posi��o introduzida
		data[posicaoTarefa - 1][0] = dataD;	// atribui o valor da vari�vel dataD � vari�vel data na posi��o introduzida
		data[posicaoTarefa - 1][1] = dataM;	// atribui o valor da vari�vel dataM � vari�vel data na posi��o introduzida
		data[posicaoTarefa - 1][2] = dataA;	// atribui o valor da vari�vel dataA � vari�vel data na posi��o introduzida
		System.out.println();

		// imprime
		System.out.println("Data adicionada com sucesso!");
		System.out.println();
		System.out.println("Lista: " + nomeListas);
		System.out.println();
		System.out.println("\t Tarefa \t\t Data \t\t Feita");
		System.out.printf("%d: \t %-20s", posicaoTarefa, tarefa[posicaoTarefa - 1]);
		if (!temPrazo[posicaoTarefa - 1]) {
			System.out.printf("\t Sem Prazo \t %s", foiFeita[posicaoTarefa - 1] ? "X" : "");
		} else {
			System.out.printf("\t %02d/%02d/%d \t %s", data[posicaoTarefa - 1][0], data[posicaoTarefa - 1][1], data[posicaoTarefa - 1][2], foiFeita[posicaoTarefa - 1] ? "X" : "");
		}

		System.out.println("\n");
	}



	// Remover data
	static void removerData(boolean [] temPrazo, int [][] data, int posicaoTarefa) {
		System.out.println();

		// caso a tarefa j� tenha uma prazo � colocado no array temPrazo o valor false e o valor 0 nas 3 posi��es do array data na posi��o introduzida
		temPrazo[posicaoTarefa - 1] = false;
		data[posicaoTarefa - 1][0] = 0;
		data[posicaoTarefa - 1][1] = 0;
		data[posicaoTarefa - 1][2] = 0;
		System.out.println("Data limite removida com sucesso!");
		System.out.println();
	}





	// ----------------------------------- Menu (C)opiar/Colar ----------------------------------- \\

	static int [] menuCopiarColar(int tamMax, int listasMax, String [][] tarefa, boolean [][] temPrazo, boolean [][] foiFeita, int [][][] data, int [] nTarefas, String [] nomeListas, int nListas, int listaAtiva, String [] tarefaTemp, boolean [] temPrazoTemp, boolean [] foiFeitaTemp, int [][] dataTemp, int nTarefasTemp, int [] atualizaMenuCopiarColar) {
		Scanner teclado = new Scanner(System.in);
		int posicaoM = 0;
		int posicaoN = 0;
		int [] posicoesMN = new int [2]; // array que ir� guardar a data fragmentada
		char opcaoCopiarColar = 0;

		do {
			System.out.println("******************************************");
			System.out.println("*  (S)elecionar lista ativa              *");
			System.out.println("*  (L)istar tarefas                      *");
			System.out.println("*  (C)opiar tarefas da posi��o m � n     *");
			System.out.println("*  Co(r)tar tarefas da posi��o m � n	 *");
			System.out.println("*  (I)nserir da mem�ria na posi��o n	 *");
			System.out.println("*                                        *");
			System.out.println("*  (V)oltar                              *");
			System.out.println("******************************************");

			System.out.print("Intoduza uma op��o: ");
			opcaoCopiarColar = teclado.next().charAt(0);
			teclado.nextLine();

			switch(opcaoCopiarColar) {
			case 's':
			case 'S':
				//(S)elecionar lista ativa

				// Verifica se existe alguma lista, atribui � vari�vel posicaoLista o valor retornado pelo m�todo introduzPosicaoLista,
				// verifica se a posi��o introduzida pelo utilizador � maior que o nTarefas
				// atribui � vari�vel listaAtiva o valor retornado pelo m�todo selecionarListaAtiva e chama o m�todo imprimeListas
				if (existeLista(nListas)) {
					int posicaoLista = introduzPosicaoLista();
					if (posicaoLista > nListas) {
						System.out.println("\nEssa lista n�o existe!\n");
					} else {
						listaAtiva = selecionarListaAtiva(listaAtiva, posicaoLista);
						imprimeListas(foiFeita, nListas, nomeListas, nTarefas, listasMax, listaAtiva);
					}
				}
				break;
			case 'l':
			case 'L':
				//(L)istar tarefas

				// Verifica se existe alguma lista e se existir chama o m�todo listarTarefas
				if (existeLista(nListas)) {
					listarTarefas(tarefa[listaAtiva], temPrazo[listaAtiva], foiFeita[listaAtiva], data[listaAtiva], nTarefas[listaAtiva], nomeListas[listaAtiva]);
				}
				break;
			case 'c':
			case 'C':
				//(C)opiar tarefas da posi��o m � n

				// Verifica se existe alguma lista, verifica se existem tarefas,
				// chama o m�todo posicoesMN e atribui � vari�vel posicaoM o valor da posi��o 0 do array posicoesMN
				// e � vari�vel posicaoN o valor da posi��o 1 do array posicoesMN, reseta a vari�vel nTarefasTemp com o valor 0
				// atribui � vari�vel nTarefasTemp o valor retornado pelo m�todo copiarTarefas e imprime "Tarefas copiadas com sucesso!"
				if (existeLista(nListas)) {
					if (existeTarefa(nTarefas[listaAtiva], nomeListas[listaAtiva])) {
						posicoesMN(posicaoM, posicaoN, nTarefas[listaAtiva], posicoesMN);
						posicaoM = posicoesMN[0];
						posicaoN = posicoesMN[1];
						nTarefasTemp = 0;
						nTarefasTemp = copiarTarefas(tarefa[listaAtiva], temPrazo[listaAtiva], foiFeita[listaAtiva], data[listaAtiva], tarefaTemp, temPrazoTemp, foiFeitaTemp, dataTemp, nTarefasTemp, posicaoM, posicaoN);
						System.out.println("\nTarefas copiadas com sucesso!\n");
					}
				}
				break;
			case 'r':
			case 'R':
				//(C)ortar tarefas da posi��o m � n

				// Verifica se existe alguma lista, verifica se existem tarefas,
				// chama o m�todo posicoesMN e atribui � vari�vel posicaoM o valor da posi��o 0 do array posicoesMN
				// e � vari�vel posicaoN o valor da posi��o 1 do array posicoesMN, reseta a vari�vel nTarefasTemp com o valor 0
				// atribui � vari�vel nTarefasTemp o valor retornado pelo m�todo copiarTarefas
				// atribui � vari�vel nTarefas da lista ativa o valor retornado pelo m�todo cortarTarefas e imprime "Tarefas cortadas com sucesso!"
				if (existeLista(nListas)) {
					if (existeTarefa(nTarefas[listaAtiva], nomeListas[listaAtiva])) {
						posicoesMN(posicaoM, posicaoN, nTarefas[listaAtiva], posicoesMN);
						posicaoM = posicoesMN[0];
						posicaoN = posicoesMN[1];
						nTarefasTemp = 0;
						nTarefasTemp = copiarTarefas(tarefa[listaAtiva], temPrazo[listaAtiva], foiFeita[listaAtiva], data[listaAtiva], tarefaTemp, temPrazoTemp, foiFeitaTemp, dataTemp, nTarefasTemp, posicaoM, posicaoN);
						nTarefas[listaAtiva] = cortarTarefas(nTarefas[listaAtiva], tarefa[listaAtiva], temPrazo[listaAtiva], foiFeita[listaAtiva], data[listaAtiva], posicaoM, posicaoN);
						System.out.println("\nTarefas cortadas com sucesso!\n");
					}
				}
				break;
			case 'i':
			case 'I':
				//(I)nserir da mem�ria na posi��o n

				// Verifica se existe alguma lista, verifica se existem tarefas tempor�rias,
				// caso existam, atribui � vari�vel posicaoN o valor retornado pelo m�todo introduzPosicaoN 
				// verifica se a quantidade de tarefas j� presentes na lista com as que pretende inserir ultrapassa o limite de tarefas por lista,
				// caso seja true imprime "N�o � possivel inserir as tarefas copiadas na lista", se for false atribui � vari�vel nTarefas da lista ativa o valor retornado pelo m�todo inserirTarefas
				if (existeLista(nListas)) {
					if (nTarefasTemp == 0) {
						System.out.println("\nN�o existem tarefas para serem inseridas!\n");
					} else {
						posicaoN = introduzPosicaoN();
						while(posicaoN - 1 > nTarefas[listaAtiva]){
							System.out.printf("\nN�o � possivel inserir tarefa(s) nessa posi��o!\n");
							posicaoN = introduzPosicaoN();
						}
						if ((nTarefas[listaAtiva] += nTarefasTemp) > tamMax) {
							nTarefas[listaAtiva] -= nTarefasTemp;
							System.out.println("\nN�o � possivel inserir as tarefas copiadas na lista \"" + nomeListas[listaAtiva] + "\"! (TAMANHO M�XIMO DA LISTA EXCEDIDO)\n");
						} else {
							nTarefas[listaAtiva] = inserirTarefas(nomeListas[listaAtiva], nTarefas[listaAtiva], tarefa[listaAtiva], temPrazo[listaAtiva], foiFeita[listaAtiva], data[listaAtiva], tarefaTemp, temPrazoTemp, foiFeitaTemp, dataTemp, nTarefasTemp, posicaoN);
						}
					}
				}
				break;
			case 'v':
			case 'V':
				//(V)oltar

				break;
			default:
				System.out.println("Op��o Inv�lida!");
			}
		} while (opcaoCopiarColar != 'v' && opcaoCopiarColar != 'V');

		atualizaMenuCopiarColar[0] = listaAtiva; // atribui o valor da vari�vel listaAtiva ao valor do array atualizaMenuCopiarColar na posi��o 0
		atualizaMenuCopiarColar[1] = nTarefasTemp; // atribui o valor da vari�vel nListas ao valor do array atualizaMenuCopiarColar na posi��o 1

		return atualizaMenuCopiarColar;	// retorna o array atualizaMenuCopiarColar com as vari�veis atualizadas
	}



	// ----------------------------------- M�todos do Menu (C)opiar/Colar -----------------------------------\\

	// (S)elecionar lista ativa j� foi feito no menu (G)erir Lista


	// (L)istar tarefas
	static void listarTarefas(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int nTarefas, String nomeListas) {
		imprimeTarefas(nomeListas, nTarefas, tarefa, temPrazo, foiFeita, data); // chama o metodo imprimeTarefas
	}



	// (C)opiar tarefas da posi��o m � n
	static int copiarTarefas(String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, String [] tarefaTemp, boolean [] temPrazoTemp, boolean [] foiFeitaTemp, int [][] dataTemp, int nTarefasTemp, int posicaoM, int posicaoN) {

		// Copia as tarefas da lista ativa entre a posicaoM e a posicaoN
		for(int i = 0, j = posicaoM - 1 ; j <= posicaoN - 1; i++, j++) {
			tarefaTemp[i] = tarefa[j];
			temPrazoTemp[i] = temPrazo[j];
			foiFeitaTemp[i] = foiFeita[j];
			dataTemp[i][0] = data[j][0]; 
			dataTemp[i][1] = data[j][1];
			dataTemp[i][2] = data[j][2];
			nTarefasTemp++;
		}

		return nTarefasTemp; // retorna o n�mero de tarefas copiadas
	}



	// (C)ortar itens da posi��o m � n
	static int cortarTarefas(int nTarefas, String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, int posicaoM, int posicaoN) {

		// Apaga as tarefas entre a posicaoM e a posicaoN
		for (int j = posicaoN - 1; j >= posicaoM - 1; j--)  {
			for (int i = j; i < nTarefas; i++) {
				tarefa[i] = tarefa[i + 1];
				temPrazo[i] = temPrazo[i + 1]; 
				foiFeita[i] = foiFeita[i + 1];
				data[i][0] = data[i + 1][0]; 
				data[i][1] = data[i + 1][1];
				data[i][2] = data[i + 1][2];
			}
			nTarefas--;
		}

		return nTarefas; // retorna a vari�vel nTarefas 
	}



	// (I)nserir da mem�ria na posi��o n
	static int inserirTarefas(String nomeListas, int nTarefas, String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data, String [] tarefaTemp, boolean [] temPrazoTemp, boolean [] foiFeitaTemp, int [][] dataTemp, int nTarefasTemp, int posicaoN) {

		// Insere as tarefas copiadas guardadas nas vari�veis tempor�rias a partir da posi��o indicada (posicaoN)
		for (int i = 0; i < nTarefasTemp; i++) {
			for (int j = nTarefas - 1; j > posicaoN - 1; j--) {
				tarefa[j] = tarefa[j - 1];
				temPrazo[j] = temPrazo[j - 1]; 
				foiFeita[j] = foiFeita[j - 1];
				data[j][0] = data[j - 1][0]; 
				data[j][1] = data[j - 1][1];
				data[j][2] = data[j - 1][2];
			}
			tarefa[posicaoN - 1] = tarefaTemp[i];
			temPrazo[posicaoN - 1] = temPrazoTemp[i];
			foiFeita[posicaoN - 1] = foiFeitaTemp[i];
			data[posicaoN - 1][0] = dataTemp[i][0];
			data[posicaoN - 1][1] = dataTemp[i][1];
			data[posicaoN - 1][2] = dataTemp[i][2];

			posicaoN += 1;
		}

		// imprime
		System.out.println();
		System.out.println("Lista: " + nomeListas);
		System.out.println();
		System.out.println("\t Tarefa \t\t Data \t\t Feita");
		for (int i = 0 ; i < nTarefas; i++) {
			System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
			if (!temPrazo[i]) {
				System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
			} else {
				System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
			}
			System.out.println();
		}

		System.out.println();

		return nTarefas; // retorna a vari�vel nTarefas 
	}





	// ------------- M�todos auxiliares ------------- \\



	// L� a posi��o da lista introduzida
	static int introduzPosicaoLista() {
		int posicaoLista = 0;

		do {
			System.out.println();
			System.out.print("Introduza o n�mero da lista (> 0): ");
			posicaoLista = lerInt();
		} while (posicaoLista <= 0);

		return posicaoLista;
	}



	// L� a posi��o M da tarefa introduzida
	static int introduzPosicaoM() {
		System.out.println();

		int posicaoM = 0;

		do {
			System.out.print("Introduza a posi��o M (> 0): ");
			posicaoM = lerInt();
		} while (posicaoM <= 0);

		return posicaoM;
	}



	// L� a posi��o N da tarefa introduzida
	static int introduzPosicaoN() {
		System.out.println();

		int posicaoN = 0;

		do {
			System.out.print("Introduza a posi��o N (> 0): ");
			posicaoN = lerInt();
		} while (posicaoN <= 0);

		return posicaoN;
	}



	// Valida posi��o M e N
	static int [] posicoesMN(int posicaoM, int posicaoN, int nTarefas, int [] posicoesMN) {

		do {

			posicaoM = introduzPosicaoM();
			while(posicaoM > nTarefas){
				System.out.printf("N�o existe nenhuma tarefa nessa posi��o!");
				posicaoM = introduzPosicaoM();
			}

			posicaoN = introduzPosicaoN();
			while(posicaoN > nTarefas){
				System.out.printf("N�o existe nenhuma tarefa nessa posi��o!");
				posicaoN = introduzPosicaoN();
			}

			if (posicaoM > posicaoN) {
				System.out.println("\nA posi��o M tem de ser inferior ou igual h� posi��o N!");
			}

		} while(posicaoM > posicaoN);

		posicoesMN[0] = posicaoM;
		posicoesMN[1] = posicaoN;

		return posicoesMN;
	}



	// L� o nome da lista introduzido
	static String introduzNomeLista() {
		Scanner teclado = new Scanner(System.in);

		System.out.println();
		System.out.print("Introduza o nome de uma nova lista: ");
		String nomeLista = teclado.nextLine();

		return nomeLista;
	}



	// L� o dia introduzido
	static String lerDia() {
		Scanner teclado = new Scanner(System.in);

		System.out.print("\nIntroduza o dia que pretende (dd/mm/aaaa): ");
		String dataIntroduzida = teclado.next();

		return dataIntroduzida;
	}



	// L� at� ao dia introduzido
	static String lerAteDia() {
		Scanner teclado = new Scanner(System.in);

		System.out.print("\nIntroduza o dia at� que pretende visualizar (dd/mm/aaaa): ");
		String dataIntroduzida = teclado.next();

		return dataIntroduzida;
	}



	// L� a descri��o da tarefa introduzida
	static String lerNovaDescricao() {
		Scanner teclado = new Scanner(System.in);

		System.out.print("\nIntroduza a descri��o da tarefa: ");
		String novaDescricao = teclado.nextLine();

		return novaDescricao;
	}



	// L� a nova data introduzida
	static String lerNovaData() {
		Scanner teclado = new Scanner(System.in);

		System.out.print("\nIntroduza o prazo que pretende (dd/mm/aaaa): ");
		String novaData = teclado.next();

		return novaData;
	}



	// L� a posi��o da tarefa introduzida
	static int lerPosicaoTarefa() {
		Scanner teclado = new Scanner(System.in);

		int posicaoTarefa = 0;

		do {
			System.out.println();
			System.out.print("Introduza a posi�ao da tarefa (> 0): ");
			posicaoTarefa = lerInt();
		} while (posicaoTarefa <= 0);

		return posicaoTarefa;
	}



	// Verirfica se existem listas
	static boolean existeLista(int nListas) {
		if (nListas == 0) {
			System.out.println("\nN�o existe nenhuma lista!\n");
			return false;
		} else {
			return true;
		}
	}



	// Verirfica se existem tarefas
	static boolean existeTarefa(int nTarefas, String nomeListas) {
		if (nTarefas != 0) {
			return true;
		} else {
			System.out.println();
			System.out.printf("N�o existem tarefas na lista \"%s\"!\n", nomeListas);
			System.out.println();
			return false;
		}
	}



	// Verifica se o ano introduzido � bissexto
	static boolean eBissexto(int dataA) {

		boolean eBissexto = false;

		if((dataA % 4 == 0 && dataA % 100 != 0) || (dataA % 400 == 0)) {
			return eBissexto = true;
		} else {
			return eBissexto = false;
		}

	}



	// Verifica se quer prazo ou n�o
	static char lerPrazo() {
		Scanner teclado = new Scanner(System.in);

		char prazo = 0;

		do {
			System.out.println();
			System.out.print("Deseja adicionar um prazo? (S/N): ");
			prazo = teclado.nextLine().charAt(0);
		} while (prazo != 's' && prazo != 'S' && prazo != 'n' && prazo != 'N');

		return prazo;
	}



	// Imprime as tarefas da lista ativa
	static void imprimeTarefas(String nomeListas, int nTarefas, String [] tarefa, boolean [] temPrazo, boolean [] foiFeita, int [][] data) {
		System.out.println();
		System.out.println("Lista: " + nomeListas);
		System.out.println();
		System.out.println("\t Tarefa \t\t Data \t\t Feita"); 
		for (int i = 0; i < nTarefas; i++) {
			System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
			if (!temPrazo[i]) {
				System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
			} else {
				System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
			}
			System.out.println();
		}
		System.out.println();
	}




	// Imprime todas as listas
	static void imprimeListas(boolean [][] foiFeita, int nListas, String [] nomeListas, int [] nTarefas, int listasMax, int listaAtiva) {
		System.out.println();

		int [] tarefasPorRealizar = new int [listasMax];
		int [] tarefasFeitas = new int [listasMax];

		System.out.printf("\t Nome da Lista  %19s %15s %9s %8s\n", "N� Tarefas", "Por Realizar", "Feitas", "Ativa");
		for (int i = 0; i < nListas; i++) {
			for (int j = 0; j < nTarefas[i]; j++) {
				if (!foiFeita[i][j]) {
					tarefasPorRealizar[i]++;
				} else {
					tarefasFeitas[i]++;
				}
			}

			System.out.printf("%d: \t %-20s %4s %13d %15d %9s\n", i + 1, nomeListas[i], nTarefas[i], tarefasPorRealizar[i], tarefasFeitas[i], i == listaAtiva ? "X" : "");

		} 

		System.out.println();
	}


	// Verifica se a lista j� est� cheia
	static boolean listaCheia(int nTarefas, int tamMax, String nomeListas) {
		boolean listaCheia = false;

		if (nTarefas >= tamMax) {
			System.out.printf("\nN�o � possivel adicionar mais tarefas porque a lista \"%s\" encontra-se cheia!\n\n", nomeListas);
			return listaCheia = true;
		} else {
			return listaCheia = false;
		}

	}


	// L� um n�mero inteiro
	static int lerInt() {
		Scanner teclado = new Scanner(System.in);

		while (!teclado.hasNextInt()) {
			teclado.next();
			System.out.println("Por favor introduza um n�mero!");
		}
		return teclado.nextInt();
	}



	// Valida��o da data
	static boolean validarData(boolean eBissexto, int dataD, int dataM, int dataA) {

		boolean dataValida = true;

		// Valida��o da data
		if ((eBissexto && dataM == 2 && dataD > 29) || (!eBissexto && dataM == 2 && dataD > 28) || (dataM == 1 && dataD > 31) || (dataM == 3 && dataD > 31)
				|| (dataM == 4 && dataD > 30) || (dataM == 5 && dataD > 31) || (dataM == 6 && dataD > 30) || (dataM == 7 && dataD > 31) || (dataM == 8 && dataD > 31)
				|| (dataM == 9 && dataD > 30) || (dataM == 10 && dataD > 31) || (dataM == 11 && dataD > 30) || (dataM == 12 && dataD > 31) || (dataM > 12)) {
			System.out.println("\nA data que introduziu � inv�lida!\n");
			return dataValida = false;
		} else {
			return dataValida = true;
		}

	}

}
