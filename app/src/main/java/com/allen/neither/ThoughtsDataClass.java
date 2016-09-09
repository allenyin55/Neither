package com.allen.neither;

/**
 * Created by Allen on 2016/9/9.
 */

public class ThoughtsDataClass {

    private int id;
    private String thoughts;

    public ThoughtsDataClass() {
    }

    public ThoughtsDataClass(String newThoughts) {
        super();
        thoughts = newThoughts;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setThoughts(String thoughts) {
        this.thoughts = thoughts;
    }

    public int getId() {
        return id;
    }

    public String getThoughts() {
        return thoughts;
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
