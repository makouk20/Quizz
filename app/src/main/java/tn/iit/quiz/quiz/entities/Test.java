package tn.iit.quiz.quiz.entities;

/**
 * Created by MOHAMED ALI DRISS on 15/05/2016.
 */
import java.util.Collection;


public class Test {

    private int id;
    private String type;
    private String description;
    private String difficulte;



    private Collection utilisateurTest;
    private Collection<Qestion> qestion;

    public Test(int id, String type, String description, String difficulte) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.difficulte = difficulte;
    }

    public Test(Collection<Qestion> qestion, Collection utilisateurTest, String difficulte, String description, String type, int id) {
        this.qestion = qestion;
        this.utilisateurTest = utilisateurTest;
        this.difficulte = difficulte;
        this.description = description;
        this.type = type;
        this.id = id;
    }

    /** @pdGenerated default getter */
    public Collection<Qestion> getQestion() {
        if (qestion == null)
            qestion = new java.util.HashSet<Qestion>();
        return qestion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public Collection getUtilisateurTest() {
        return utilisateurTest;
    }

    public void setUtilisateurTest(Collection utilisateurTest) {
        this.utilisateurTest = utilisateurTest;
    }

    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorQestion() {
        if (qestion == null)
            qestion = new java.util.HashSet<Qestion>();
        return qestion.iterator();
    }

    /** @pdGenerated default setter
     * @param newQestion */
    public void setQestion(Collection<Qestion> newQestion) {
        removeAllQestion();
        for (java.util.Iterator iter = newQestion.iterator(); iter.hasNext();)
            addQestion((Qestion)iter.next());
    }

    /** @pdGenerated default add
     * @param newQestion */
    public void addQestion(Qestion newQestion) {
        if (newQestion == null)
            return;
        if (this.qestion == null)
            this.qestion = new java.util.HashSet<Qestion>();
        if (!this.qestion.contains(newQestion))
        {
            this.qestion.add(newQestion);
            newQestion.setTest(this);
        }
    }

    /** @pdGenerated default remove
     * @param oldQestion */
    public void removeQestion(Qestion oldQestion) {
        if (oldQestion == null)
            return;
        if (this.qestion != null)
            if (this.qestion.contains(oldQestion))
            {
                this.qestion.remove(oldQestion);
                oldQestion.setTest((Test)null);
            }
    }

    /** @pdGenerated default removeAll */
    public void removeAllQestion() {
        if (qestion != null)
        {
            Qestion oldQestion;
            for (java.util.Iterator iter = getIteratorQestion(); iter.hasNext();)
            {
                oldQestion = (Qestion)iter.next();
                iter.remove();
                oldQestion.setTest((Test)null);
            }
        }
    }

}