package com.example.myfistapp;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class HomeworldObject{
    private String Type,Name,Star,SpecialSystem;
    public String toString(){ return this.Name; }
    public String getType(){ return this.Type;}
    public String getStar(){ return this.Star;}
    public String getSpecialSystem(){return  this.SpecialSystem;}
    HomeworldObject(String type,String name, String star, String specialSystem){
        this.Type = type;
        this.Name = name;
        this.Star = star;
        this.SpecialSystem = specialSystem;
    }
}
class CivicModel{
    @SerializedName("civics")
    private CivicObject[] civics;
    public CivicObject[] getCivics(){
        return this.civics;
    }
    class CivicObject{
        @SerializedName("civicName")
        private String civicName;
        @SerializedName("IncompatibleAuthority")
        private String[] IncompatibleAuthority;
        @SerializedName("RequiredAuthority")
        private String[] RequiredAuthority;
        @SerializedName("IncompatibleCivics")
        private String[] IncompatibleCivics;
        @SerializedName("IncompatibleEthics")
        private String[] IncompatibleEthics;
        @SerializedName("RequiredEthics")
        private String RequiredEthics;

        public boolean equals(CivicObject civic){return this.civicName == civic.civicName;}
        public boolean equals(String civic){return this.civicName.equals(civic);}
        public String toString(){
            return this.civicName;
        }
        public String[] getRequiredAuthority(){
            return this.RequiredAuthority;
        }
        public String getRequiredEthics(){
            return this.RequiredEthics;
        }
        public String[] getIncompatibleAuthority(){
            return this.IncompatibleAuthority;
        }
        public String[] getIncompatibleEthics(){
            return this.IncompatibleEthics;
        }
        public String[] getIncompatibleCivics(){
            return this.IncompatibleCivics;
        }

    }
}
class TraitModel{
    @SerializedName("traits")
    private TraitObject[] traits;
    public TraitObject[] getTraits(){
        return this.traits;
    }
    class TraitObject {
        @SerializedName("traitName")
        private String traitName;
        @SerializedName("pointValue")
        private int pointValue;
        @SerializedName("IncompatibleTraits")
        private String[] IncompatibleTraits;

        private List<TraitObject> incompatibleTraits;

        public void addIncompatibleTrait(TraitObject trait){
            if (this.incompatibleTraits == null){
                this.incompatibleTraits = new ArrayList<>();
            }
            this.incompatibleTraits.add(trait);
        }
        public String[] showIncompatibleTraits(){
            return this.IncompatibleTraits;
        }
        public int getPointValue(){
            return this.pointValue;
        }
        public List<TraitObject> getIncompatibleTraits(){
            return this.incompatibleTraits;
        }

        public boolean equals(TraitObject obj) {
            if (this.traitName.equals(obj.traitName)) return true;
            else return false;
        }

         @NonNull
         public String toString() {
            return this.traitName;
        }

        TraitObject(String name, int pointValue) {
            this.traitName = name;
            this.pointValue = pointValue;
            this.incompatibleTraits = new ArrayList<>();
        }
    }
}
public class EmpireObject {
    private UUID Id;
    private String Name,EmpireName,Biography,NameList,Cityset,AdvisorVoice,Shipset,Authority;
    private String[] Ethics = new String[3];
    private TraitModel.TraitObject[] Traits;
    private CivicModel.CivicObject[] Civics;
    private HomeworldObject Homeworld;



    @NonNull
    public String toString() {

        return this.EmpireName;
    }
    public void setName(String name){
        this.Name = name;
    }
    public void setEthics(String[] ethics){
        this.Ethics = ethics;
    }
    public void setEmpireAttributes(String Name, String EmpireName, String Biography, String NameList, String Cityset, String AdvisorVoice,
                                    String Shipset, String[] Ethics, TraitModel.TraitObject[] Traits, String Authority, CivicModel.CivicObject[] Civics, HomeworldObject Homeworld){
        this.Name = Name;
        this.EmpireName = EmpireName;
        this.Biography = Biography;
        this.NameList = NameList;
        this.Cityset = Cityset;
        this.AdvisorVoice = AdvisorVoice;
        this.Shipset = Shipset;
        this.Ethics = Ethics;
        this.Traits = Traits;
        this.Authority = Authority;
        this.Civics = Civics;
        this.Homeworld = Homeworld;
    }

    public String[] getEmpireAttributes(){
        return new String[]{this.Name,this.Biography,this.NameList,this.Cityset,this.AdvisorVoice,this.Shipset};
    }
    public String[] getEmpireEthics(){
        return this.Ethics;
    }

    public String[] getEmpireTraits(){
        int place =0;
        String[] TraitString = new String[this.Traits.length];
        for(TraitModel.TraitObject trait: this.Traits){
            if(trait != null) TraitString[place] = trait.toString();
            else return TraitString;
            place++;
        }
        return TraitString;
    }
    public String[] getEmpireCivics(){
        int place =0;
        String[] CivicString = new String[this.Civics.length];
        for(CivicModel.CivicObject civic: this.Civics){
            if(civic != null) CivicString[place] = civic.toString();
            else return CivicString;
            place++;
        }
        return CivicString;
    }
    public void setAuthority(String gov){
        this.Authority = gov;
    }
    public String getAuthority(){
        return this.Authority;
    }


    EmpireObject(){
        this.Name = "TestName";
        this.EmpireName = "My Empire";
        this.Biography = "About My Civ";
        this.NameList = "NameList";
        this.Cityset = "My CitySet";
        this.AdvisorVoice = "My Advisor";
        this.Shipset = "My Shipset";
    }
}

