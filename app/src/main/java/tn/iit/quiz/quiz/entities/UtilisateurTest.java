package tn.iit.quiz.quiz.entities;

/**
 * Created by MOHAMED ALI DRISS on 15/05/2016.
 */

public class UtilisateurTest {
    private int score;
    private Test Test_Utilisateur;
    private Utilisateur Utilisateur_Test;

    public UtilisateurTest(int score, Test test_Utilisateur, Utilisateur utilisateur_Test) {
        this.score = score;
        Test_Utilisateur = test_Utilisateur;
        Utilisateur_Test = utilisateur_Test;
    }

    public UtilisateurTest(int score) {
        this.score = score;
    }

    public Test getTest_Utilisateur() {
        return Test_Utilisateur;
    }

    public void setTest_Utilisateur(Test test_Utilisateur) {
        Test_Utilisateur = test_Utilisateur;
    }

    public Utilisateur getUtilisateur_Test() {
        return Utilisateur_Test;
    }

    public void setUtilisateur_Test(Utilisateur utilisateur_Test) {
        Utilisateur_Test = utilisateur_Test;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}