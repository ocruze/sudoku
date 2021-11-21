
/*
 * Ceci est un jeu de Sudoku qui génère une toute nouvelle grille (qui a une solution unique) à chaque fois.
 */

import java.util.Scanner;

public class Menu {

	public final static String INTRO = "\n"
			+ "   sudokusud kus     sud  usudokusu   usudo     oku     usu  oku     usu \n"
			+ "  dokusudok sudo     oku udokusudok  udokusu   usud     doku sud     dok \n"
			+ "   sud        us     sud  us    usu oku   oku    ku     usu   ku     usu \n"
			+ "     us       do     oku  do    dok su     ud    ud     dok   ud     dok \n"
			+ "      ok      us     sud  us    us  ok     ku    ku    kus    ku     usu \n"
			+ "        do    do     oku  do   ud   su     ud    ud    udo    ud     dok \n"
			+ "        usu   usu    sud  usu oku   oku   oku    ku  dok      kus    usu \n"
			+ "  dokusudok    okusudok   dokus      udokusu     ud  usud      dokusudo  \n"
			+ " kusudokusu     udokus    usud        usudo      ku   okus      sudoku   \n"
			+ "                                                                           ";

	public final static String MAINMENU = "\n1. Jouer\n" + "2. Afficher les règles\n" + "3. Quitter\n";

	public final static String SUBMENU = "\n1. Mettre une valeur ou changer la valeur existante dans la case\n"
			+ "2. Vider la case\n" + "3. Oops ! Je me suis trompé(e) de case\n"
			+ "4. Abandonner le jeu et voir la correction\n";

	public final static String DIFFICULTYMENU = "Veuillez choisir un niveau :\n\n1. Niveau facile\n2. Niveau moyen\n3. Niveau difficile\n";

	public final static String RULES = "Pour gagner, il suffit de compléter la grille qu'avec des nombres entre 1 et 9 inclus,\n en tenant compte de la contrainte suivante :\n"
			+ "Un nombre ne peut pas apparaître plus d'une fois sur la même ligne, ni sur la même colonne, ni sur la même région.";

	/**
	 * C'est ici que le jeu se déroule
	 */
	public static void theGame() {
		// déclaration de variables
		Scanner sc = new Scanner(System.in);
		int row, column;
		int num;
		int menuOption, subMenuOption;
		int difficultyLevel;

		int[][] solvedGrid = GenerateGrid.generateNewGrid();// une nouvelle grille résolue est générée
		int[][] unsolvedGrid = Meth.copyGrid(solvedGrid);// malgré son nom, cette grille est actuellement résolue, mais
															// des cases seront vidées dans quelques instants, la
															// rendant non résolue

		boolean changeRemovePermitted, horizontalAlreadyPresent, verticalAlreadyPresent, blockAlreadyPresent;
		// fin de déclaration de variables

		System.out.println(INTRO);

		do {
			System.out.println(MAINMENU);
			menuOption = Integer.parseInt(sc.nextLine());

			switch (menuOption) {
			case 1: {// jouer
				System.out.println(DIFFICULTYMENU);
				difficultyLevel = Integer.parseInt(sc.nextLine());
				switch (difficultyLevel) {// en fonction de la difficulté choisie par le joueur, un certain nombre de
											// cases sera vidé
				case 1:// facile
					GenerateGrid.putSomeBlanks(unsolvedGrid, 20);
					break;
				case 2:// moyen
					GenerateGrid.putSomeBlanks(unsolvedGrid, 40);
					break;
				case 3:// difficile
					GenerateGrid.putSomeBlanks(unsolvedGrid, 65);
					break;
				default:
					System.out.println("La valeur saisie ne correspond pas au menu.");
					break;
				}

				int[][] unsolvedGridBackup = Meth.copyGrid(unsolvedGrid);// une copie de la grille non résolue est faite

				do {// le jeu commence ici
					Meth.displayGrid(unsolvedGrid);
					System.out.println(
							"Dans quel case voulez-vous faire un changement ?\n(n° de colonne, puis n° de ligne, séparés par une entrée)");

					do {
						column = Integer.parseInt(sc.nextLine()) - 1;
						row = Integer.parseInt(sc.nextLine()) - 1;
						while (row < 0 || row > 8 || column < 0 || column > 8) {
							if (row < 0 || row > 8 || column < 0 || column > 8)
								System.out.println("Veuillez entrer des valeurs entre 1 et 9 inclus.");
							column = Integer.parseInt(sc.nextLine()) - 1;
							row = Integer.parseInt(sc.nextLine()) - 1;
						}
						changeRemovePermitted = Meth.changeRemovePermitted(unsolvedGridBackup, row, column);
						if (!changeRemovePermitted)
							System.out.println(
									"Vous ne pouvez pas changer la valeur d'une case prédéfinie. Veuillez réessayer.");
					} while (!changeRemovePermitted);

					do {
						System.out.println(SUBMENU);
						subMenuOption = Integer.parseInt(sc.nextLine());

						switch (subMenuOption) {
						case 1: {// mettre ou changer valeur
							System.out.println(
									"Quel nombre voulez-vous y entrer ?\nVous pouvez entrer 0 pour changer de case.");

							do {
								num = Integer.parseInt(sc.nextLine());
								if (num < 0 || num > 9)
									System.out.println("Veuillez entrer un nombre entre 1 et 9 inclus.");
								if (num == 0)
									break;
								horizontalAlreadyPresent = Meth.horizontalAlreadyPresent(unsolvedGrid, row, num);
								verticalAlreadyPresent = Meth.verticalAlreadyPresent(unsolvedGrid, column, num);
								blockAlreadyPresent = Meth.blockAlreadyPresent(unsolvedGrid, row, column, num);
								if (horizontalAlreadyPresent)
									System.out.println("Le nombre existe déjà sur la même ligne. Veuillez réessayer.");
								if (verticalAlreadyPresent)
									System.out
											.println("Le nombre existe déjà sur la même colonne. Veuillez réessayer.");
								if (blockAlreadyPresent)
									System.out.println("Le nombre existe déjà sur la même région. Veuillez réessayer.");
							} while (num < 0 || num > 9 || horizontalAlreadyPresent || verticalAlreadyPresent
									|| blockAlreadyPresent);

							Meth.changeValue(unsolvedGrid, row, column, num);
							menuOption = 1;
							subMenuOption = 3;
							break;
						}
						case 2:// vider case
							Meth.removeValue(unsolvedGrid, row, column);
							subMenuOption = 3;
							break;
						case 3:// resaisir les coordonnées
							menuOption = 1;
							subMenuOption = 3;
							break;
						case 4:// abandonner
							break;
						default:
							System.out.println("La valeur saisie ne correspond pas au menu.");
							break;
						}

					} while (subMenuOption != 3 && subMenuOption != 4);

				} while (!Meth.winGame(unsolvedGrid) && subMenuOption != 4);
				if (subMenuOption == 4) {
					System.out.println("Vous avez abandonné le jeu. Voici la correction de la grille : ");
					Meth.displayGrid(solvedGrid);

				} else {
					System.out.println("Bravo vous avez résolu la grille !");
					Meth.displayGrid(unsolvedGrid);

				}
				break;
			}
			case 2:// afficher les règles
				System.out.println(RULES);
				break;
			case 3:// quitter
				System.out.println("Au revoir !");
				break;
			default:
				System.out.println("La valeur saisie ne correspond pas au menu.");
				break;

			}
		} while (menuOption != 3);
		sc.close();

	}

}