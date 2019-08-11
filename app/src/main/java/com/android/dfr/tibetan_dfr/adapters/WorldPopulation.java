package com.android.dfr.tibetan_dfr.adapters;

import java.util.ArrayList;

/**
 * Created by MinhThanh on 11/26/17.
 */

public class WorldPopulation {
    private String lineText;
    private String idText;
    private String[] dataIdBook = {"མད༌ཐཱ།","མཱཪ་ཀུ","ལོ་ཀུ","ཡོ་ཧ་ནན།","མཛད་འཕྲིན།","རོ་མཱ་པ།","ཀོ་རིན་ཐུ་པ་དང་པོ།","ཀོ་རིན་ཐུ་པ་གཉིས་པ།","ག་ལད་ཡཱ་པ།","ཨེ་ཕེ་སི་པ།","ཕི་ལིབ་པི་པ།","ཀོ་ལོ་སཱ་པ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་དང་པོ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་གཉིས་པ།","ཐི་མོ་ཐེ་དང་པོ།","ཐི་མོ་ཐེ་གཉིས་པ།","ཐེ་ཏུ།","ཕི་ལེ་མོན།","ཨིབ་རི་པ།","ཡ་ཀོབ།","པེ་ཏྲོ་དང་པོ།","པེ་ཏྲོ་གཉིས་པ།","ཡོ་ཧ་ནན་དང་པོ།","ཡོ་ཧ་ནན་གཉིས་པ།","ཡོ་ཧ་ནན་གསུམ་པ།","ཡ་ཧུ་དཱ།","མངོན་པ།"};
    public WorldPopulation(String lineText) {
        String[] splitIdText = lineText.split("-");
        String cupString = "";
        if (splitIdText.length > 2){
            String[] idSplit = splitIdText[3].split(" ");
            if (idSplit.length > 1){
                this.idText = changeTextID(splitIdText[0].replaceAll("\\s+","") + "-" + (Integer.parseInt(splitIdText[1]) + 9)) +" "+ (splitIdText[2]) + ":" + idSplit[0];
                for (int i = 0; i< idSplit.length ; i++){
                    if (i != 0){
                        cupString += idSplit[i].replace("null", " ");
                    }
                }
                this.lineText = cupString;
            }
        }

    }

    private String changeTextID(String chap){

        ArrayList<String> arrayVerse = new ArrayList<String>();

        for ( int i = 0; i < 27; i++ ){
            arrayVerse.add("verse-" + (i + 36));
        }
        for (int i = 0; i < arrayVerse.size(); i++) {
            if (arrayVerse.get(i).contains(chap)){
                return chap.replace(arrayVerse.get(i), dataIdBook[i] + ":");
            }
        }

        return dataIdBook[0];
    }

//    protected String getChapter(String chap){
//        String currentChap = "";
//        for (int i = 0; i < dataIdBook.length; i++) {
//            if (chap.equals(String.valueOf(i))){
//                return dataIdBook[i] + "\n";
//            }
//        }
//        return currentChap;
//    }


    public String getLineText() {
        return this.lineText;
    }

    public String getIdText(){
        return this.idText;
    }

}
