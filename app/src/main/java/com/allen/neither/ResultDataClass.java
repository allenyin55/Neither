package com.allen.neither;



public final class ResultDataClass {

    private int id;
    private int yesResult;
    private int noResult;
    private int neiResult;

    public ResultDataClass() {
    }

    public ResultDataClass(String result) {
        super();
        switch(result){
            case "yes":
                this.yesResult++;
                break;
            case "no":
                this.noResult++;
                break;
            case "nei":
                this.neiResult++;
                break;
            default:
                break;
        }

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setYesResult(int yesResult) {
        this.yesResult = yesResult;
    }

    public void setNoResult(int noResult) {
        this.noResult = noResult;
    }

    public void setNeiResult(int neiResult) {
        this.neiResult = neiResult;
    }

    public int getId() {
        return id;
    }

    public int getNeiResult() {
        return neiResult;
    }

    public int getNoResult() {
        return noResult;
    }

    public int getYesResult() {
        return yesResult;
    }

    /**
     * For checking if the things get written into database
     * @Override
    * public String toString() {
    *    return "Result [id=" + id + ", yes=" + yesResult + ", no=" + noResult + ", neither" + neiResult
    * + "]";
    * }
    * */
}
