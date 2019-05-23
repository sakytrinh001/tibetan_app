package com.android.dfr.tibetan_dfr.adapters;

/**
 * Created by MinhThanh on 11/26/17.
 */

public class WorldPopulation {
    private String lineText;
    private String idText;
    private String[] dataIdBook = {"བཀོད་པ།","ཨེ་ཅིབ་ནས་ཐོན་པ།","རུ་ཐི།","ཨེ་ཛ་རཱ","ནེ་ཧེམ་ཡཱ ","ཨེ་སཱ་ཐེར།","གསུང་མགུར།","ལེགས་བཤད།","ཡོ་ནཱ ","མད་ཐཱའི", "མཱཪ་ཀུའིའི", "ལོ་ཀུའི", "ཡོ་ཧ་ནན་གྱི", "མཛད་འཕྲིན་གྱ", "རོ་མཱ་པའི", "ཀོ་རིན་ཐུ་པ་དང་པོའི", "ཀོ་རིན་ཐུ་པ་གཉིས་པའི", "ག་ལད་ཡཱ་པའི",
            "ཨེ་ཕེ་སི་པའི", "ཕི་ལིབ་པི་པའི", "ཀོ་ལོ་སཱ་པའི", "ཐེ་སཱ་ལོ་ནེ་ཀེ་དང་པོའི", "ཐེ་སཱ་ལོ་ནེ་ཀེ་གཉིས་པའི", "ཐི་མོ་ཐེ་དང་པོའི", "ཐི་མོ་ཐེ་གཉིས་པའི", "ཐེ་ཏུའི","ཕི་ལེ་མོན་གྱི", "ཨིབ་རི་པའི",
            "ཡ་ཀོབ་ཀྱི", "པེ་ཏྲོ་དང་པོའི", "པེ་ཏྲོ་གཉིས་པའི", "ཡོ་ཧ་ནན་དང་པོའི", "ཡོ་ཧ་ནན་གཉིས་པའི", "ཡོ་ཧ་ནན་གསུམ་པའི", "ཡ་ཧུ་དཱའི", "མངོན་པའི","མད་ཐཱ།","མཱཪ་ཀུ","ལོ་ཀུ",
            "ཡོ་ཧ་ནན།","མཛད་འཕྲིན།","རོ་མཱ་པ།","ག་ལད་ཡཱ་པ།","ཨེ་ཕེ་སི་པ།","ཕི་ལིབ་པི་པ།","ཀོ་ལོ་སཱ་པ།","ཐེ་ཏུ།","ཕི་ལེ་མོན།","ཨིབ་རི་པ།","ཡ་ཀོབ།","ཡ་ཧུ་དཱ།","མངོན་པ།",
            "ཀོ་རིན་ཐུ་པ་དང་པོ།","ཀོ་རིན་ཐུ་པ་གཉིས་པ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་དང་པོ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་གཉིས་པ།","ཐི་མོ་ཐེ་དང་པོ།","ཐི་མོ་ཐེ་གཉིས་པ།","པེ་ཏྲོ་དང་པོ།","པེ་ཏྲོ་གཉིས་པ།","ཡོ་ཧ་ནན་དང་པོ།",
            "ཡོ་ཧ་ནན་གཉིས་པ།","ཡོ་ཧ་ནན་གསུམ་པ།","བཀོད་པ།","ཨེ་ཅིབ་ནས་ཐོན་པ།","རུ་ཐི།","ཨེ་ཛ་རཱ།","ནེ་ཧེམ་ཡཱ།","ཨེ་སཱ་ཐེར།","གསུང་མགུར།","ལེགས་བཤད།","ཡོ་ནཱ།"};
    public WorldPopulation(String lineText) {
        String[] splitIdText = lineText.split("-");
        String cupString = "";
        if (splitIdText.length > 2){
            String[] idSplit = splitIdText[2].split(" ");
            if (idSplit.length > 1){
                this.idText = getChapter(splitIdText[0].replaceAll("\\s+","")) +" "+ splitIdText[1] + ":" + idSplit[0];
                for (int i = 0; i<idSplit.length ; i++){
                    if (i != 0){
                        cupString += idSplit[i].replace("null", " ");
                    }
                }
                this.lineText = cupString;
            }
        }

    }

    protected String getChapter(String chap){
        String currentChap = "";
        for (int i = 0; i < dataIdBook.length; i++) {
            if (chap.equals(String.valueOf(i))){
                return dataIdBook[i] + "\n";
            }
        }
        return currentChap;
    }


    public String getLineText() {
        return this.lineText;
    }

    public String getIdText(){
        return this.idText;
    }

}
