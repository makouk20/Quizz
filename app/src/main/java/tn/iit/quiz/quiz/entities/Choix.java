package tn.iit.quiz.quiz.entities;

/**
 * Created by MOHAMED ALI DRISS on 15/05/2016.
 */
public class Choix {
    private int id;
    private String textChoix;
    private boolean reponse;
    public Qestion qestion;


    /**
     * @pdGenerated default parent getter
     */
    public Qestion getQestion() {
        return qestion;
    }

    /**
     * @param newQestion
     * @pdGenerated default parent setter
     */
    public void setQestion(Qestion newQestion) {
        if (this.qestion == null || !this.qestion.equals(newQestion)) {
            if (this.qestion != null) {
                Qestion oldQestion = this.qestion;
                this.qestion = null;
                oldQestion.removeChoix(this);
            }
            if (newQestion != null) {
                this.qestion = newQestion;
                this.qestion.addChoix(this);
            }
        }
    }

}
