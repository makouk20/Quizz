package tn.iit.quiz.quiz.entities;

/**
 * Created by MOHAMED ALI DRISS on 15/05/2016.
 */

public class Qestion {

    private int id;
    private String qestion;
    private String urlimage;
    private int idtest;


    public java.util.Collection<Choix> choix;
    public Test test;

    public Qestion(int id, String qestion, String urlimage, int idtest) {
        this.id = id;
        this.qestion = qestion;
        this.urlimage = urlimage;
        this.idtest = idtest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQestion() {
        return qestion;
    }

    public void setQestion(String qestion) {
        this.qestion = qestion;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }

    public int getIdtest() {
        return idtest;
    }

    public void setIdtest(int idtest) {
        this.idtest = idtest;
    }

    public java.util.Collection<Choix> getChoix() {
        if (choix == null)
            choix = new java.util.HashSet<Choix>();
        return choix;
    }


    public java.util.Iterator getIteratorChoix() {
        if (choix == null)
            choix = new java.util.HashSet<Choix>();
        return choix.iterator();
    }

    /**
     * @param newChoix
     * @pdGenerated default setter
     */
    public void setChoix(java.util.Collection<Choix> newChoix) {
        removeAllChoix();
        for (java.util.Iterator iter = newChoix.iterator(); iter.hasNext(); )
            addChoix((Choix) iter.next());
    }

    /**
     * @param newChoix
     * @pdGenerated default add
     */
    public void addChoix(Choix newChoix) {
        if (newChoix == null)
            return;
        if (this.choix == null)
            this.choix = new java.util.HashSet<Choix>();
        if (!this.choix.contains(newChoix)) {
            this.choix.add(newChoix);
            newChoix.setQestion(this);
        }
    }

    /**
     * @param oldChoix
     * @pdGenerated default remove
     */
    public void removeChoix(Choix oldChoix) {
        if (oldChoix == null)
            return;
        if (this.choix != null)
            if (this.choix.contains(oldChoix)) {
                this.choix.remove(oldChoix);
                oldChoix.setQestion((Qestion) null);
            }
    }

    /**
     * @pdGenerated default removeAll
     */
    public void removeAllChoix() {
        if (choix != null) {
            Choix oldChoix;
            for (java.util.Iterator iter = getIteratorChoix(); iter.hasNext(); ) {
                oldChoix = (Choix) iter.next();
                iter.remove();
                oldChoix.setQestion((Qestion) null);
            }
        }
    }

    /**
     * @pdGenerated default parent getter
     */
    public Test getTest() {
        return test;
    }

    /**
     * @param newTest
     * @pdGenerated default parent setter
     */
    public void setTest(Test newTest) {
        if (this.test == null || !this.test.equals(newTest)) {
            if (this.test != null) {
                Test oldTest = this.test;
                this.test = null;
                oldTest.removeQestion(this);
            }
            if (newTest != null) {
                this.test = newTest;
                this.test.addQestion(this);
            }
        }
    }

}