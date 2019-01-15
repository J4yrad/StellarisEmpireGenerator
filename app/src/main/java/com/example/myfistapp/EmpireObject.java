package com.example.myfistapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class NameModel implements Serializable{
    @SerializedName("names")
    private NameObject[] names;
    public NameObject[] getNames(){return this.names;}
    class NameObject{
        @SerializedName("EmpireName")
        String EmpireName;
        @SerializedName("RequiredAuthority")
        String[] RequiredAuthority;
        @SerializedName("RequiredCivics")
        String[] RequiredCivics;

        public String[] getRequiredAuthority() {
            return RequiredAuthority;
        }

        public String[] getRequiredCivics() {
            return RequiredCivics;
        }


        public String toString() {
            return this.EmpireName;
        }
    }
}

class HomeworldObject implements Serializable {
    @ColumnInfo(name = "homeworld_name")
    private String Name;
    @ColumnInfo(name = "type")
    private String Type;
    @ColumnInfo(name = "star")
    private String Star;
    @ColumnInfo(name = "special")
    private String SpecialSystem;

    public String toString(){ return this.Name; }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSpecialSystem(String specialSystem) {
        SpecialSystem = specialSystem;
    }

    public void setStar(String star) {
        Star = star;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getType(){ return this.Type;}
    public String getStar(){ return this.Star;}
    public String getSpecialSystem(){return  this.SpecialSystem;}
    HomeworldObject(String Type,String Name, String Star, String SpecialSystem){
        this.Type = Type;
        this.Name = Name;
        this.Star = Star;
        this.SpecialSystem = SpecialSystem;
    }
}
class CivicModel implements Serializable{
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
class TraitModel implements Serializable{
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


        public String[] showIncompatibleTraits(){
            return this.IncompatibleTraits;
        }
        public int getPointValue(){
            return this.pointValue;
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
        }
    }
}
@Entity(tableName = "empire_table")
public class EmpireObject implements Serializable{
    @NonNull
    @PrimaryKey
    @TypeConverters(IdConverter.class)
    private UUID Id;
    @ColumnInfo(name = "name")
    private String Name;
    @ColumnInfo(name = "empire_name")
    private String EmpireName;
    @ColumnInfo(name = "biography")
    private String Biography;
    @ColumnInfo(name = "namelist")
    private String NameList;
    @ColumnInfo(name = "cityset")
    private String Cityset;
    @ColumnInfo(name = "advisorvoice")
    private String AdvisorVoice;
    @ColumnInfo(name = "shipset")
    private String Shipset;
    @ColumnInfo(name = "authority")
    private String Authority;
    @TypeConverters(EthicsConverter.class)
    @ColumnInfo(name = "ethics")
    private String[] Ethics;
    @TypeConverters(PortraitConverter.class)
    @ColumnInfo(name = "portrait")
    private String[] Portrait;
    @ColumnInfo(name = "traits")
    @TypeConverters(TraitConverter.class)
    private TraitModel.TraitObject[] Traits;
    @ColumnInfo(name = "civics")
    @TypeConverters(CivicConverter.class)
    private CivicModel.CivicObject[] Civics;
    @Embedded
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
                                    String Shipset, String[] Ethics, TraitModel.TraitObject[] Traits, String Authority, CivicModel.CivicObject[] Civics, HomeworldObject Homeworld, String[] Portrait){
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
        this.Portrait = Portrait;
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
    public String[] getPortrait() {return this.Portrait;}
    public UUID getId(){
        return this.Id;
    }
    public String getName(){
        return this.Name;
    }
    public String getEmpireName(){
        return this.EmpireName;
    }
    public String getBiography() {
        return this.Biography;
    }
    public String getNameList(){
        return this.NameList;
    }
    public String getCityset(){
        return this.Cityset;
    }
    public  String getAdvisorVoice(){
        return this.AdvisorVoice;
    }
    public String getShipset(){
        return this.Shipset;
    }
    public HomeworldObject getHomeworld(){
        return this.Homeworld;
    }
    public String[] getEthics(){
        return this.Ethics;
    }
    public TraitModel.TraitObject[] getTraits(){
        return this.Traits;
    }
    public CivicModel.CivicObject[] getCivics(){
            return this.Civics;
    }
    public void setEmpireName(String name){
        this.EmpireName = name;
    }
    public void setBiography(String bio){
        this.Biography = bio;
    }
    public void setNameList(String namelist){
        this.NameList = namelist;
    }
    public void setCityset(String city){
        this.Cityset = getCityset();
    }
    public void setAdvisorVoice(String AdvisorVoice){
        this.AdvisorVoice = AdvisorVoice;
    }
    public void setShipset(String ships){
        this.Shipset = ships;
    }
    public void setHomeworld(HomeworldObject world){
        this.Homeworld = world;
    }
    public void setId(UUID id){
        this.Id = id;
    }
    public void setTraits(TraitModel.TraitObject[] traits){
        this.Traits = traits;
    }
    public void setCivics(CivicModel.CivicObject[] civics){
        this.Civics = civics;
    }
    public void setPortrait(String[] portrait){
        this.Portrait = portrait;
    }

    EmpireObject(){
        this.Id = UUID.randomUUID();
    }
}
