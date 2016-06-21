package tn.iit.quiz.quiz.entities;

/**
 * Created by MOHAMED ALI DRISS on 15/05/2016.
 */
import java.util.Collection;

public class Utilisateur {

    private int id;
    private String login;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Collection utilisateurTest;

    @Override
    public String toString() {
        return "Utilisateur{ login= " + login + ", nom= " + nom +
                ", prenom= " + prenom  +
                ", email= " + email  +
                ", password= " + password  +
                ", utilisateurTest= " + utilisateurTest +
                '}';
    }

    public Utilisateur(int id, String login, String nom, String prenom, String email, String password) {
        this.id=id;
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection getUtilisateurTest() {
        return utilisateurTest;
    }

    public void setUtilisateurTest(Collection utilisateurTest) {
        this.utilisateurTest = utilisateurTest;
    }
}
