package tn.iit.quiz.quiz.entities;

import java.util.List;

/**
 * Created by HP on 03/05/2016.
 */
public class Reconnaissance {
    private int id;
    private int id_categ;
    private String question;
    private String img;
    private List<String> list_proposition;
    private int reponse;

    public Reconnaissance(int id, int id_categ, String question, String img, List<String> list_proposition,int reponse) {
        super();
        this.id = id;
        this.id_categ = id_categ;
        this.question = question;
        this.img = img;
        this.list_proposition = list_proposition;
        this.reponse=reponse;
    }



    public Reconnaissance(int id) {
        this.id = id;
    }

    public int getReponse() {

        return reponse;
    }

    public void setReponse(int reponse) {
        this.reponse = reponse;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId_categ() {
        return id_categ;
    }
    public void setId_categ(int id_categ) {
        this.id_categ = id_categ;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public List<String> getList_proposition() {
        return list_proposition;
    }
    public void setList_proposition(List<String> list_proposition) {
        this.list_proposition = list_proposition;
    }
}
